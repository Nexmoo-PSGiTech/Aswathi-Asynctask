package com.login.aswathibaskaran.taskintent

import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView

class LoadImageTask : Activity() {

     lateinit var imageView: ImageView

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val msg = intent.getStringExtra(MESSAGE_KEY)
        when (msg) {
            "1" -> URL = "https://upload.wikimedia.org/wikipedia/commons/4/40/AlcedoHercules.jpg"
            "2" -> URL = "https://upload.wikimedia.org/wikipedia/commons/6/64/Foods_%28cropped%29.jpg"
            "3" -> URL = "https://upload.wikimedia.org/wikipedia/commons/2/25/Plum_blossom_on_the_first_day_of_spring_-_geograph.org.uk_-_1214413.jpg"
            "4" -> URL = "https://upload.wikimedia.org/wikipedia/commons/1/14/Mirabellen.jpg"
        }
        setContentView(R.layout.image)
        imageView = findViewById(R.id.imageView) as ImageView

        // Create an object for subclass of AsyncTask
        val task = GetXMLTask()
        // Execute the task
        task.execute(*arrayOf<String>(URL.toString()))
    }

    private inner class GetXMLTask : AsyncTask<String, Void, Bitmap>() {
        private val dialog = ProgressDialog(this@LoadImageTask)
        override fun onPreExecute() {
            // Display the progress dialog on async task start
            dialog.setMessage("Downloading.., please wait.")
            dialog.show()
        }

        override fun doInBackground(vararg urls: String): Bitmap? {
            var map: Bitmap? = null
            for (url in urls) {
                map = downloadImage(url)
            }
            return map
        }

        // Sets the Bitmap returned by doInBackground
        override fun onPostExecute(result: Bitmap) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
            imageView.setImageBitmap(result)
        }

        // Creates Bitmap from InputStream and returns it
        private fun downloadImage(url: String): Bitmap? {
            var bitmap: Bitmap? = null
            var stream: InputStream? = null
            val bmOptions = BitmapFactory.Options()
            bmOptions.inSampleSize = 1

            try {
                stream = getHttpConnection(url)
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions)
                stream!!.close()
            }
            catch (e1: IOException) {
                e1.printStackTrace()
            }

            return bitmap
        }

        // Makes HttpURLConnection and returns InputStream

        private fun getHttpConnection(urlString: String): InputStream? {
            var stream: InputStream? = null
            val url = URL(urlString)
            val connection = url.openConnection()

            try {
                val httpConnection = connection as HttpURLConnection
                httpConnection.requestMethod = "GET"
                httpConnection.connect()

                if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.inputStream
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return stream
        }
    }

    companion object {

        val MESSAGE_KEY = "com.login.aswathibaskaran.taskintent.message_key"

        var URL: String? = null
    }
}