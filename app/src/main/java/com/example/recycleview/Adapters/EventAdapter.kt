package com.example.recycleview.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.databinding.CategoryItemBinding
import com.example.recycleview.databinding.EventitemBinding
import com.example.recycleview.pojo.Event
import com.example.recycleview.pojo.TypeXX
import com.example.recycleview.pojo.events

class EventAdapter:RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    inner class EventViewHolder(val binding: EventitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var eventList = ArrayList<Event>()
    var onItemclick: ((Event) -> Unit)? = null
    fun seteventList(eventList: List<Event>) {
        this.eventList = eventList as ArrayList<Event>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            EventitemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        Glide.with(holder.itemView).load(eventList[position].eventImage)
            .into(holder.binding.imgEvent)
        holder.binding.tvEventName.text = eventList[position].title
        holder.itemView.setOnClickListener {
            onItemclick!!.invoke(eventList[position])
        }



    }
    override fun getItemCount(): Int {
        return eventList.size
    }
}