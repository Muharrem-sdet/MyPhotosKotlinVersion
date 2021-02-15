package com.example.myphotoskotlinversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotoskotlinversion.model.Photo
import com.example.myphotoskotlinversion.network.GetDataService
import com.example.myphotoskotlinversion.network.RetrofitClient
import com.example.myphotoskotlinversion.views.PhotoListAdapter
import com.example.myphotoskotlinversion.views.PhotoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var photoViewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_View)
        photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)

        photoViewModel.retrievePhotos()
        photoViewModel.listLiveData.observe(this, Observer {
            createRecyclerView(it)
        })
    }

    fun createRecyclerView(photoList: MutableList<Photo>) {
        val layoutManager = LinearLayoutManager(this)
        val adapter = PhotoListAdapter(photoList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}