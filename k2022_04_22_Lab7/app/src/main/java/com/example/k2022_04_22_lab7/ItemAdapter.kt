package com.example.k2022_04_22_lab7

import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

lateinit var answers: List<String>
class ItemAdapter(var a: List<String>) : RecyclerView.Adapter<ItemAdapter.RadioViewHolder> () {

    init {
        answers = a
    }

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var name : TextView = itemView.findViewById(R.id.answertext)
        var whichCard: Int = 0

        fun bind(position: Int) {
            name.text = answers[position]

            whichCard = position
        }

        override fun onClick(p0: View?) {
            Toast.makeText(p0?.context, "Hello: " + whichCard.toString(), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return answers.size
    }

}