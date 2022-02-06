package com.example.my

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row_comment.view.*


class CustomComments(val commentList : ArrayList<specificComment>): RecyclerView.Adapter<CustomComments.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_comment, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataComments = commentList!![position]
        holder.bind(dataComments)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class ViewHolder(itemView: View, var my_data: specificComment? = null) :
        RecyclerView.ViewHolder(itemView) {
        // catch the element
        @SuppressLint("SetTextI18n")
        fun bind(mydata : specificComment){
            val Id = itemView.tx_comment_id as TextView
            val postName =itemView.tx_comment_name as TextView
            val postEmail = itemView.tx_comment_email as TextView
            val postBody = itemView.tx_comment_body as TextView


            Id.text ="userid:"+ mydata.id.toString()
            postName.text = "name :"+mydata.name
            postEmail.text ="Email:"+ mydata.email
            postBody.text = "Body : " + mydata.body

        }

    }
}