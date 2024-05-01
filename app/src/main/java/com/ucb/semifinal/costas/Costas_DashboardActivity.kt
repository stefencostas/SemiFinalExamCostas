package com.ucb.semifinal.costas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Costas_DashboardActivity : AppCompatActivity(), ClassScheduleAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    lateinit var classScheduleList: MutableList<ClassSchedule>
    lateinit var adapter: ClassScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costas_dashboard)

        classScheduleList = mutableListOf(
            ClassSchedule(
                dateTime = "Monday 9:00 AM - 10:30 AM",
                subjectCode = "IT-INTPROG32",
                subjectDescription = "Integrative Programming",
                roomNumber = "Room 803"
            ),
            ClassSchedule(
                dateTime = "Tuesday 9:00 AM - 10:30 AM",
                subjectCode = "CC-TECHNO32",
                subjectDescription = "Technopreneurship",
                roomNumber = "Room 218"
            )
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ClassScheduleAdapter(classScheduleList, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(classSchedule: ClassSchedule, position: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Class Schedule Details")
        val message = "Date & Time: ${classSchedule.dateTime}\n\n" +
                "Subject Code: ${classSchedule.subjectCode}\n\n" +
                "Subject Description: ${classSchedule.subjectDescription}\n\n" +
                "Room Number: ${classSchedule.roomNumber}"
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton("Close") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }


    override fun onItemLongPress(classSchedule: ClassSchedule, position: Int) {
        val dialog = ClassScheduleDialogFragment.newInstance(classSchedule, position)
        dialog.show(supportFragmentManager, "ClassScheduleDialogFragment")
    }

    fun onEditClick(classSchedule: ClassSchedule, position: Int) {
        val dialog = EditClassScheduleDialogFragment.newInstance(classSchedule, position)
        dialog.show(supportFragmentManager, "EditClassScheduleDialogFragment")
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onDeleteClick(position: Int) {
        classScheduleList.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Class Schedule deleted successfully.", Toast.LENGTH_SHORT).show()
    }

}
