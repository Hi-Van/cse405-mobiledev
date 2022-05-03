package com.example.k2022_03_08_rv

import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.k2022_03_08_rv.controller.RadioController
import com.example.k2022_03_08_rv.model.RadioStation
import com.example.k2022_03_08_rv.model.RadioStations
import java.util.zip.Inflater


lateinit var rc : RadioController

class RadioAdapter(var controller: RadioController) : RecyclerView.Adapter<RadioAdapter.RadioViewHolder> () {

    init {
        rc = controller
    }

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var name : TextView = itemView.findViewById(R.id.name_text)

        fun bind(position: Int) {
            val source = rc.sources().getStations()[position]
            name.text = source.name
        }

        override fun onClick(p0: View?) {
            val source = rc.sources().getStations()[adapterPosition + 1]

            rc.pause()
            rc.stop()
            rc.set(adapterPosition + 1)
            rc.setUp()

            if ( rc.isPlaying() ) {
                rc.play()
            }

            Toast.makeText(p0?.context, "Hello: $adapterPosition", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.radio_card, parent, false)

         return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return rc.sources().getStations().size
    }

}