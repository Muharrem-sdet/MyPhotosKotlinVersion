package com.example.myphotoskotlinversion.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myphotoskotlinversion.model.Photo
import com.example.myphotoskotlinversion.network.GetDataService
import com.example.myphotoskotlinversion.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoViewModel: ViewModel(){

    private val _listLiveData : MutableLiveData<MutableList<Photo>> = MutableLiveData()
    val listLiveData : LiveData<MutableList<Photo>>
    get() = _listLiveData

    fun retrievePhotos(){
        val getDataService = RetrofitClient.instance?.create(GetDataService::class.java)
        val callPhotos = getDataService?.getPhotos()
        callPhotos?.enqueue(object: Callback<MutableList<Photo>> {
            override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                t.stackTrace
            }
            override fun onResponse(
                call: Call<MutableList<Photo>>,
                response: Response<MutableList<Photo>>
            ) {
                _listLiveData.value = response.body()
                if (_listLiveData.value != null) {
                    for(photo in _listLiveData.value!!){
                        Log.d("MyPhotos", "My photo: ${photo.title}")
                    }
                }
            }
        })
    }
}