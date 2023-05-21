package com.anurag.maverickmachinetest.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import java.io.IOException
import java.io.InputStream


object AppUtils {

    fun isNetworkAvailable(context: Context): Boolean{

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = cm?.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getJsonFromAssets(context: Context, fileName: String): String? {

        var jsonString = ""
        try {
            val inputStream : InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}