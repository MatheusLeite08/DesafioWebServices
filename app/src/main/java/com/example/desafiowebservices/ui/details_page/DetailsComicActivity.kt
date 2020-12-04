package com.example.desafiowebservices.ui.details_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Results
import com.example.desafiowebservices.services.service
import com.example.desafiowebservices.ui.home.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_comic.*

class DetailsComicActivity : AppCompatActivity() {

    val viewModel by viewModels<DetailsComicViewModel>{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailsComicViewModel(service) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_comic)

        setSupportActionBar(toolbarDetailsComicPage)

        toolbarDetailsComicPage.setNavigationOnClickListener {
            onBackPressed()
        }


        //Recepção dos dados da HQ selecionada na Home

        //Caso a HQ não tenha uma descrição, o app irá apresentar, por padrão, a descrição do Personagem fornecida pela API
        viewModel.returnCharacterAPI.observe(this){
            tv_comicDescription.text = it.data.results[0].description
        }

        //É necessário formatar a data fornecida pela API
        viewModel.publishedComic.observe(this){
            tv_publishedDate.text = it
        }

        val extras = intent.extras

        var coverComicPath = extras?.getString("coverComicPath")
        var coverComicExtension = extras?.getString("coverComicExtension")
        Picasso.get().load(coverComicPath + "." + coverComicExtension).into(iv_comicCover)
        Picasso.get().load(coverComicPath + "." + coverComicExtension).into(iv_backgroundDetailsComicPage)

        var titleComic = extras?.getString("titleComic")
        tv_comicTitle.text = titleComic

        var descriptionComic = extras?.getString("descriptionComic")
        tv_comicDescription.text = descriptionComic

        //Condicional que verifica se a API fornece uma descrição da HQ. Caso não forneça, a descrição
        //do personagem é requisitada
        if(descriptionComic === null) {
            viewModel.getCharacterAPI()
        }

        var date = extras?.getString("publishedComic")
        viewModel.formatPublishedComic(date)

        var priceComic = extras?.getString("priceComic")
        tv_price.text = priceComic

        var pageCountComic = extras?.getString("pageCountComic")
        tv_numberPages.text = pageCountComic



        card_comicCover.setOnClickListener {

            val intent = Intent(this, ComicCoverExpandedActivity::class.java)

            intent.putExtra("coverComic", coverComicPath + "." + coverComicExtension)

            startActivity(intent)

        }

    }

}