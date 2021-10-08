package com.example.myapplicationmobtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationmobtv.databinding.MainActivityBinding
import com.example.myapplicationmobtv.main_screen.MainFragment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity(){
    private val viewBinding: MainActivityBinding by viewBinding()
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

        val titleTextView=viewBinding.titleTextView
        val textView=viewBinding.textView
        titleTextView.text= feed?.providerName
        textView.text= feed?.lastUpdated
        Log.d("Tag","feed?.lastUpdated + "+ feed?.lastUpdated)
        titleTextView.setOnClickListener {
            Toast.makeText(this,"Roku", Toast.LENGTH_SHORT).show()
        }

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

companion object{
    var feed: Feed?=null
}

}