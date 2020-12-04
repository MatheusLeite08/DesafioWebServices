package com.example.desafiowebservices.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiowebservices.entities.Msg
import com.example.desafiowebservices.entities.MsgCharacter
import com.example.desafiowebservices.services.Service
import kotlinx.coroutines.launch

class HomeViewModel (val service: Service): ViewModel() {

    var returnAPI = MutableLiveData<Msg>()

    fun getComicsList(){
        viewModelScope.launch {
            returnAPI.value = service.getComicsListAPI(
                2,
                20,
                "1",
                "6eb7e8896ec5850c52515a8a23ee97f0",
                "40a3aa568bb269dfad85ae0c4a297181"
            )

        }
    }
}