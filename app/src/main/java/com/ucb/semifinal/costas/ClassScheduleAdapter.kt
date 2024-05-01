package com.ucb.semifinal.costas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClassScheduleAdapter(
    private val classScheduleList: MutableList<ClassSchedule>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ClassScheduleAdapter.ClassScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_class_schedule, parent, false)
        return ClassScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassScheduleViewHolder, position: Int) {
        val classSchedule = classScheduleList[position]
        holder.bind(classSchedule)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(classSchedule, position)
        }

        holder.itemView.setOnLongClickListener {
            itemClickListener.onItemLongPress(classSchedule, position)
            true
        }
    }

    override fun getItemCount(): Int = classScheduleList.size

    inner class ClassScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTimeTextView: TextView = itemView.findViewById(R.id.textView_dateTime)
        private val subjectCodeTextView: TextView = itemView.findViewById(R.id.textView_subjectCode)
        private val subjectDescriptionTextView: TextView = itemView.findViewById(R.id.textView_subjectDescription)
        private val roomNumberTextView: TextView = itemView.findViewById(R.id.textView_roomNumber)

        fun bind(classSchedule: ClassSchedule) {
            dateTimeTextView.text = classSchedule.dateTime
            subjectCodeTextView.text = classSchedule.subjectCode
            subjectDescriptionTextView.text = classSchedule.subjectDescription
            roomNumberTextView.text = classSchedule.roomNumber
        }
    }

    interface OnItemClickListener {
        fun onItemClick(classSchedule: ClassSchedule, position: Int)
        fun onItemLongPress(classSchedule: ClassSchedule, position: Int)
    }

}
