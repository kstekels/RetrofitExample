package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.retrofitexample.network.ApiClient
import com.example.retrofitexample.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacter("1")
        client.enqueue(object : retrofit2.Callback<CharacterResponse> {

            // on successful response we log the result
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("characters", " " + response.body())

                    val result = response.body()?.result
                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
                }
            }
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("failed", " " + t.message)
            }

        })
    }
}