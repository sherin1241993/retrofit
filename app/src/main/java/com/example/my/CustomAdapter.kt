package com.example.my

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row_posts.view.*


class CustomAdapter(val userList : ArrayList<DefultResponse>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_posts, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dataPosts = userList!![position]
            holder.bind(dataPosts)
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        class ViewHolder(itemView: View, var my_data: DefultResponse? = null) :
            RecyclerView.ViewHolder(itemView) {
            // catch the element
        fun bind(mydata : DefultResponse){
                val postId = itemView.tx_id as TextView
                val postTitle =itemView.tx_title as TextView
                val postBody = itemView.tx_body as TextView

                postId.text = mydata.userId.toString()
                postTitle.text = mydata.title
                postBody.text = mydata.body


        }

        }
    }