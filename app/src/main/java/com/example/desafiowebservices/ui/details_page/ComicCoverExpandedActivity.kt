package com.example.desafiowebservices.ui.details_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiowebservices.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comic_cover_expanded.*
import kotlinx.android.synthetic.main.activity_details_comic.*

class ComicCoverExpandedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_cover_expanded)

        iv_closeComicCoverExpanded.setOnClickListener {
            onBackPressed()
        }

        val extras = intent.extras

        var coverComicExpanded = extras?.getString("coverComic")
        Picasso.get().load(coverComicExpanded).into(iv__comicCoverExpanded)

    }
}