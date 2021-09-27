package com.example.myapplicationmobtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplicationmobtv.ui.main.MainFragment
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


    }


//    fun openNextFragment(id: Int) {
//        supportFragmentManager.beginTransaction()
//            .setReorderingAllowed(true)
//            .replace(R.id.mainFragment, SeriesFragment())
//            .addToBackStack(null)
//            .commit()
//    }

}