package com.example.halobundo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.halobundo.R
import com.example.halobundo.model.Article

class ArticleAdapter(private var data: List<Article>,
                         private val listener:(Article) -> Unit):
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){


        fun bindItem(data: Article, listener: (Article) -> Unit, context: Context) {
//            tv_rs.setText(data.place)
            itemView.setOnClickListener{
                listener(data)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_article, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int){
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int =data.size


}