package com.example.pdfreaderapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.github.barteksc.pdfviewer.PDFView

class PdfViewActivity : AppCompatActivity() {
    lateinit var pdfView: PDFView
    lateinit var storagelauncher:ActivityResultLauncher<Intent>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        supportActionBar?.title="Pdf Viewer"

        pdfView=findViewById(R.id.pdfView)

        val intent=this.intent
        if (intent!=null){
            val action = intent.action
            if (Intent.ACTION_VIEW == action){
                val uri = intent.data
                uri?.let {it -> pdfView.fromUri(it).load()}

            }
        }


        storagelauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback {
            val selectedpdf= it.data?.data
            selectedpdf?.let { it1 -> showpdffromUri(it1) }


        })

        selectedpdffromStorage()



    }



    private fun selectedpdffromStorage() {
        Toast.makeText(this@PdfViewActivity,"Select a Pdf File",Toast.LENGTH_SHORT).show()
        val browserStorage=Intent(Intent.ACTION_GET_CONTENT)
        browserStorage.type="application/pdf"
        browserStorage.addCategory(Intent.CATEGORY_OPENABLE)
        storagelauncher.launch(browserStorage)

    }


    private fun showpdffromUri(uri:Uri){

        pdfView.fromUri(uri)

            .spacing(10).load()

    }


}