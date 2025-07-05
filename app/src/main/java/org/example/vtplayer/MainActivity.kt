package org.example.vtplayer

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.example.vtplayer.databinding.ActivityMusicBinding
import org.example.vtplayer.ui.theme.VTPlayerTheme
import java.io.File
import kotlin.system.exitProcess

//class MainActivity : ComponentActivity() {
class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMusicBinding
    private  lateinit var  toggle: ActionBarDrawerToggle
    private lateinit var musicAdapter: MusicAdapter

    // access from anywhere companion object
    companion object {
        lateinit var MusicListMA : ArrayList<Music>
    }

    // giving permission
//    private val permissionLauncher = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeLayout()
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

        binding.navView.setNavigationItemSelectedListener {
//            Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show()
            when(it.itemId)
            {
                R.id.navFeedback -> Toast.makeText(baseContext, "Feedback", Toast.LENGTH_SHORT).show()
                R.id.navSettings -> Toast.makeText(baseContext, "Settings", Toast.LENGTH_SHORT).show()
                R.id.navAbout -> Toast.makeText(baseContext, "About", Toast.LENGTH_SHORT).show()
                R.id.navExit -> exitProcess(1)
            }
            true
        }

    }

    // For requesting permission
    private  fun requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
            Toast.makeText(this, "Please Provide Permissions", Toast.LENGTH_SHORT).show()
//            permissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        }
//        else {
//            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
//        }

//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED){
////            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
////            permissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            Toast.makeText(this, "Asking Permission", Toast.LENGTH_SHORT).show()
//        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        Toast.makeText(this, "Permission Granted Result", Toast.LENGTH_SHORT).show()
        if (requestCode == 13) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            else {
//                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
//                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
            }
        }
    }
//
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item))
//            return true
//        return super.onOptionsItemSelected(item)


    private fun initializeLayout(){
        requestRuntimePermission()
        // setTheme(R.style.Theme_VTPlayer)'
        setTheme(R.style.coolPinkNav)

        // Initialize Binding
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // for nav drawer
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val musicList = ArrayList<String>()
//        musicList.add("1 Song")
//        musicList.add("2 Song")
//        musicList.add("3 Song")
//        musicList.add("4 Song")

        MusicListMA = getAllAudio()

        binding.musicRV.setHasFixedSize(true)
        binding.musicRV.setItemViewCacheSize(13)
        binding.musicRV.layoutManager = LinearLayoutManager(this@MainActivity)
//        musicAdapter = MusicAdapter(this@MainActivity, musicList)
        musicAdapter = MusicAdapter(this@MainActivity, MusicListMA)
        binding.musicRV.adapter = musicAdapter
        binding.totalSongs.text =  "Total Songs : "+ musicAdapter.itemCount
    }

    private fun getAllAudio(): ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA)
        val cursor = this.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null,
            MediaStore.Audio.Media.DATE_ADDED + " DESC", null)

        if(cursor != null) {
            if (cursor.moveToFirst())
                do {
                    val titleC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val albumC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val artistC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val pathC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val durationC = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
//                    val albumIdC = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)).toString()
//                    val uri = Uri.parse( "content://media/external/audio/albumart")

//                    val uri = Uri.parse("content://media/external/audio/albumart")
//                    val artUriC = Uri.withAppendedPath(uri, albumIdC).toString()

//                    val music = Music(id = idC, title = titleC, album = albumC, artist = artistC, path = pathC, duration = durationC, artUri = artUriC)

                    val music = Music(id = idC, title = titleC, album = albumC, artist = artistC, path = pathC, duration = durationC)
                    val file = File(music.path)
                    if(file.exists())
                        tempList.add(music)

                } while (cursor.moveToNext())
            cursor.close()
        }
        return tempList
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