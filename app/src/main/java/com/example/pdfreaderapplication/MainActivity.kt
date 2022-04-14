package com.example.pdfreaderapplication

import android.Manifest

import android.content.Intent

import android.os.Bundle
import android.os.Environment
import android.util.Log

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File


class MainActivity : AppCompatActivity() {

    private val TAG: String ="check"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Home"

        Log.i(TAG, "oncreate")


        runtimePermission()


    }

    override fun onStart() {
        super.onStart()
        runtimePermission()
        Log.i(TAG, "onStart: ")
    }

    private fun runtimePermission() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(
                //for SinglePermission
                object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {

                       executiveTask()


                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {

                        Toast.makeText(this@MainActivity,"Permission is Required",Toast.LENGTH_SHORT).show()


                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {

                        token?.continuePermissionRequest()


                    }








                }






            ).check()
    }

    private fun executiveTask() {
        val intent = Intent(this@MainActivity,PdfViewActivity::class.java)
        startActivity(intent)

    }


}






