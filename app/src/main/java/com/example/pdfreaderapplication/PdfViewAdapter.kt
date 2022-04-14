package com.example.pdfreaderapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class PdfViewAdapter(val context: Context, val pdfFiles: ArrayList<File>) :
    RecyclerView.Adapter<PdfViewAdapter.PdfViewHolder>() {


    class PdfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pdfName: TextView
        var container: CardView

        init {
            pdfName = itemView.findViewById(R.id.id_textpdf)
            container = itemView.findViewById(R.id.id_cardcontainer)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        return PdfViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_booklist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {
        holder.pdfName.text = pdfFiles[position].name
        holder.pdfName.isSelected = true

    }

    override fun getItemCount(): Int {
        return pdfFiles.size
    }


}