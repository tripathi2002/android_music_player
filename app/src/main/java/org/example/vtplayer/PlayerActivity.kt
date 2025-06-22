package org.example.vtplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.example.vtplayer.databinding.ActivityPlayerBinding


class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_VTPlayer)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_playlist)
        setContentView(binding.root)
    }

}