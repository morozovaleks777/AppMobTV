package com.example.myapplicationmobtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.myapplicationmobtv.ui.main.MainFragment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.nio.charset.Charset

var feed: Feed?=null
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        val obj = getJSONFromAssets()
        val jsonAdapter: JsonAdapter<Feed> = Moshi.Builder().build().adapter(Feed::class.java)
        feed = obj?.let { jsonAdapter.fromJson(it) }

    }
    fun getJSONFromAssets(): String? {

        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myFeedJSONFile =this.assets?.open("feed.json")
            val size = myFeedJSONFile?.available()
            val buffer = size?.let { ByteArray(it) }
            myFeedJSONFile?.read(buffer)
            myFeedJSONFile?.close()
            json = buffer?.let { String(it, charset) }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

//    fun openNextFragment(id: Int) {
//        supportFragmentManager.beginTransaction()
//            .setReorderingAllowed(true)
//            .replace(R.id.mainFragment, SeriesFragment())
//            .addToBackStack(null)
//            .commit()
//    }

}