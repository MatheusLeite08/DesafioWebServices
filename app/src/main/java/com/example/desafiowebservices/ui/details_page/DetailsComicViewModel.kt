package com.example.desafiowebservices.ui.details_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiowebservices.entities.Msg
import com.example.desafiowebservices.entities.MsgCharacter
import com.example.desafiowebservices.services.Service
import kotlinx.coroutines.launch

class DetailsComicViewModel (val service: Service): ViewModel() {

    var returnCharacterAPI = MutableLiveData<MsgCharacter>()
    var publishedComic = MutableLiveData<String>()

    fun getCharacterAPI(){
        viewModelScope.launch {
            returnCharacterAPI.value = service.getCharacterAPI(
                0,
                15,
                "1",
                "6eb7e8896ec5850c52515a8a23ee97f0",
                "40a3aa568bb269dfad85ae0c4a297181"
            )
        }
    }

    fun formatPublishedComic(date: String?){
        var year = "${date?.get(0)}" + "${date?.get(1)}" + "${date?.get(2)}" + "${date?.get(3)}"
        var month = "${date?.get(5)}" + "${date?.get(6)}"
        var day = "${date?.get(8)}" + "${date?.get(9)}"

        when(month){
            "01" -> month = "January"
            "02" -> month = "February"
            "03" -> month = "March"
            "04" -> month = "April"
            "05" -> month = "May"
            "06" -> month = "June"
            "07" -> month = "July"
            "08" -> month = "August"
            "09" -> month = "September"
            "10" -> month = "October"
            "11" -> month = "November"
            "12" -> month = "December"
            else -> month = " "
        }

        publishedComic.value = month + " " + day + ", " + year

    }

}