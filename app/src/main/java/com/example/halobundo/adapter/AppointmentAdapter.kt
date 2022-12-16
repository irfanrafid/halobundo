package com.example.halobundo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.halobundo.R
import androidx.recyclerview.widget.RecyclerView
import com.example.halobundo.model.Appointment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AppointmentAdapter(private var data: List<Appointment>,
                         private val listener:(Appointment) -> Unit):
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tv_rs: TextView = view.findViewById(R.id.tvhospital)


        fun bindItem(data:Appointment, listener: (Appointment) -> Unit, context: Context) {
            tv_rs.setText(data.place)
            itemView.setOnClickListener{
                listener(data)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppointmentAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_appointment, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: AppointmentAdapter.ViewHolder, position: Int){
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int =data.size


}
