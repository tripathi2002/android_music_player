package org.example.vtplayer

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import org.example.vtplayer.databinding.ActivityMusicBinding
import org.example.vtplayer.ui.theme.VTPlayerTheme

class MainActivity : ComponentActivity() {
    private lateinit var  binding: ActivityMusicBinding

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestRuntimePermission()
        setTheme(R.style.Theme_VTPlayer)

        // Initialize Binding
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Toast.makeText(this, "Coming From Load Click", Toast.LENGTH_LONG).show()
        binding.shuffleBtn.setOnClickListener {
//            Toast.makeText(this@MainActivity, "Coming From Button Click Shuffle", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, PlayerActivity::class.java)
            startActivity(intent)
        }
        binding.favouriteBtn.setOnClickListener {
//            Toast.makeText(this@MainActivity, "Coming From Button Click Favourite", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.playlistBtn.setOnClickListener {
//            Toast.makeText(this@MainActivity, "Coming From Button Click Playlist", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, PlaylistActivity::class.java)
            startActivity(intent)
        }
    }

    // For requesting permission
    private  fun requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
            Toast.makeText(this, "Asking Permission ---> ", Toast.LENGTH_SHORT).show()
            permissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        }
        else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }

//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED){
////            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
////            permissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            Toast.makeText(this, "Asking Permission", Toast.LENGTH_SHORT).show()
//        }

    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray,
//
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray,
//        deviceId: Int
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
//        if (requestCode == 13) {
//
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//            else {
//
//                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
//                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
//            }
//        }
//    }

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