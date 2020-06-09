package com.aboust.timeago.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aboust.timeago.test

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test(this)
    }
}