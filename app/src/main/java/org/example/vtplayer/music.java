package org.example.vtplayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class music extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_VTPlayer);
        setContentView(R.layout.activity_music);

        Toast.makeText(this, "Music activity loaded", Toast.LENGTH_SHORT).show();

        Button playButton = findViewById(R.id.shuffleBtn);
        playButton.setOnClickListener(view -> {
            Toast.makeText(music.this, "Play button clicked", Toast.LENGTH_SHORT).show();
        });

    }
}