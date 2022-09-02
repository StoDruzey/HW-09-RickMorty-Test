package com.example.hw09rickmortytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hw09rickmortytest.network.ApiClient
import com.example.hw09rickmortytest.network.CharacterResponce
import com.example.hw09rickmortytest.network.MainAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacters("1")

        client.enqueue(object : retrofit2.Callback<CharacterResponce>{
            // on successful responce we log the result
            override fun onResponse(
                call: Call<CharacterResponce>,
                response: Response<CharacterResponce>
            ) {
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())

                    val result = response.body()?.result
                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponce>, t: Throwable) {
                Log.e("failed", ""+t.message)
            }
        })


    }
}