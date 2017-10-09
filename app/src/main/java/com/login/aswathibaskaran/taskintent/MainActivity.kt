package com.login.aswathibaskaran.taskintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    internal var msg: String? = null

    internal lateinit var GoToNewActivity: Button
    internal lateinit var GoToNewActivity1: Button
    internal lateinit var GoToNewActivity3: Button
    internal lateinit var GoToNewActivity2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GoToNewActivity = findViewById(R.id.btn0) as Button
        GoToNewActivity1 = findViewById(R.id.btn1) as Button
        GoToNewActivity2 = findViewById(R.id.btn2) as Button
        GoToNewActivity3 = findViewById(R.id.btn3) as Button

        GoToNewActivity.setOnClickListener {
            // Intent code for open new activity through intent.
            msg = "1"
            val intent = Intent(this@MainActivity, LoadImageTask::class.java)
            intent.putExtra(MESSAGE_KEY, msg)
            startActivity(intent)
        }
        GoToNewActivity1.setOnClickListener {
            // Intent code for open new activity through intent.
            msg = "2"
            val intent = Intent(this@MainActivity, LoadImageTask::class.java)
            intent.putExtra(MESSAGE_KEY, msg)
            startActivity(intent)
        }
        GoToNewActivity2.setOnClickListener {
            // Intent code for open new activity through intent.
            msg = "3"
            val intent = Intent(this@MainActivity, LoadImageTask::class.java)
            intent.putExtra(MESSAGE_KEY, msg)
            startActivity(intent)
        }
        GoToNewActivity3.setOnClickListener {
            // Intent code for open new activity through intent.
            msg = "4"
            val intent = Intent(this@MainActivity, LoadImageTask::class.java)
            intent.putExtra(MESSAGE_KEY, msg)
            startActivity(intent)
        }
    }

    companion object {

        val MESSAGE_KEY = "com.login.aswathibaskaran.taskintent.message_key"
    }
}