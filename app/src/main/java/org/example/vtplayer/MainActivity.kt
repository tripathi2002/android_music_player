package org.example.vtplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.vtplayer.databinding.ActivityMusicBinding
import org.example.vtplayer.ui.theme.VTPlayerTheme

class MainActivity : ComponentActivity() {
    private lateinit var  binding: ActivityMusicBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_VTPlayer)

        // Initialize Binding
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Toast.makeText(this, "Coming From Load Click", Toast.LENGTH_LONG).show()
        binding.shuffleBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Coming From Button Click Shuffle", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, PlayerActivity::class.java)
            startActivity(intent)
        }
        binding.favouriteBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Coming From Button Click Favourite", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.playlistBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Coming From Button Click Playlist", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, PlaylistActivity::class.java)
            startActivity(intent)
        }



//        enableEdgeToEdge()
//        setContent {
//            VTPlayerTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VTPlayerTheme {
        Greeting("Android")
    }
}