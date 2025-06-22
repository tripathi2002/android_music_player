package org.example.vtplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.example.vtplayer.databinding.ActivityFavouriteBinding



class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_VTPlayer)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_playlist)
        setContentView(binding.root)
    }

}