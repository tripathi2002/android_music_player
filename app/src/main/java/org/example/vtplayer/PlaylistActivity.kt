package org.example.vtplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.example.vtplayer.databinding.ActivityPlaylistBinding

class PlaylistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_VTPlayer)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_playlist)
        setContentView(binding.root)
    }

}