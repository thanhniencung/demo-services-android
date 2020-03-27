package com.code4func.helloandroid.recyclerview

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code4func.helloandroid.R
import kotlinx.android.synthetic.main.adapter_letter.view.*

typealias OnItemClicked = (letter: Letter) -> Unit

class LetterAdapter(val dataSet: List<Letter>,
                    val itemClickListener: OnItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HeaderViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
    }

    inner class LetterViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        init {
            viewHolder.setOnClickListener {
                itemClickListener(dataSet[adapterPosition])
            }

            itemView.btnChangeColor.setOnClickListener {
                //itemView.setBackgroundColor(Color.BLACK)
                dataSet[adapterPosition].color = Color.BLACK
                notifyItemChanged(adapterPosition)
            }
        }

        fun bind(letter: Letter) {
            itemView.tvLetter.text = letter.name
            itemView.setBackgroundColor(letter.color)
        }
    }

    override fun getItemViewType(position: Int) = if (position == 0) 0 else 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val headerViewHolderTemplate = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_header, parent, false)

            HeaderViewHolder(headerViewHolderTemplate)
        } else {
            val letterViewHolderTemplate = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_letter, parent, false)

            LetterViewHolder(letterViewHolderTemplate)
        }
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LetterViewHolder) {
            Log.d("@@@@", "bind($position)")
            holder.bind(dataSet[position])
        }
    }

}