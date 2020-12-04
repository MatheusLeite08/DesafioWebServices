package com.example.desafiowebservices.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Results
import com.example.desafiowebservices.services.service
import com.example.desafiowebservices.ui.details_page.DetailsComicActivity

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), ComicsListAdapter.onComicClickListener {

    private lateinit var comicsListAdapter: ComicsListAdapter
    private lateinit var comicsListLayoutManager: RecyclerView.LayoutManager

    val viewModel by viewModels<HomeViewModel>{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(service) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbarHomePage)

        comicsListLayoutManager = GridLayoutManager(this, 3)
        rv_ComicsListHome.layoutManager = comicsListLayoutManager
        comicsListAdapter = ComicsListAdapter(this)
        rv_ComicsListHome.adapter = comicsListAdapter

        viewModel.returnAPI.observe(this){
            var comicsList = it.data.results
            comicsListAdapter.addList(comicsList)
        }

        viewModel.getComicsList()

    }

    override fun comicClick(position: Int) {

        viewModel.returnAPI.observe(this){
            val comicsList = it.data.results
            val comic = comicsList.get(position)


            val intent = Intent(this, DetailsComicActivity::class.java)

            intent.putExtra("coverComicPath", comic.thumbnail.path)
            intent.putExtra("coverComicExtension", comic.thumbnail.extension)
            intent.putExtra("titleComic", comic.title)
            intent.putExtra("descriptionComic", comic.description)
            intent.putExtra("publishedComic", comic.dates[0].date)
            intent.putExtra("priceComic", (comic.prices[0].price).toString())
            intent.putExtra("pageCountComic", (comic.pageCount).toString())

            startActivity(intent)

        }
    }
}