package com.example.k2022_04_22_lab7

import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k2022_04_22_lab7.controllers.QTroller
import com.example.k2022_04_22_lab7.models.questions.AnswerObject
import com.example.k2022_04_22_lab7.models.score.ScoreViewModel
import java.util.zip.Inflater

lateinit var answers: MutableList<AnswerObject>
lateinit var score: ScoreViewModel
lateinit var qtroller: QTroller

class ItemAdapter(var a: MutableList<AnswerObject>, var s: ScoreViewModel) : RecyclerView.Adapter<ItemAdapter.RadioViewHolder> () {

    init {
        answers = a
        score = s
    }

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private var name : TextView = itemView.findViewById(R.id.answertext)

        fun bind(position: Int) {
            name.text = answers[position].getAnswer()
        }

        override fun onClick(p0: View?) {

            if ( answers[adapterPosition].getIsTrue() == "true" ) {
                score.incremenet()
            }

            else {
                score.decrement()
            }


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