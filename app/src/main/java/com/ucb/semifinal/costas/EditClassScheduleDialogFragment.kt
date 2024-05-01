package com.ucb.semifinal.costas

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EditClassScheduleDialogFragment(private val classSchedule: ClassSchedule, private val position: Int) : DialogFragment() {

    companion object {
        fun newInstance(classSchedule: ClassSchedule, position: Int): EditClassScheduleDialogFragment {
            val fragment = EditClassScheduleDialogFragment(classSchedule, position)
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_edit_class_schedule, null)

        val dateTimeEditText = view.findViewById<EditText>(R.id.edit_text_date_time)
        val subjectCodeEditText = view.findViewById<EditText>(R.id.edit_text_subject_code)
        val subjectDescriptionEditText = view.findViewById<EditText>(R.id.edit_text_subject_description)
        val roomNumberEditText = view.findViewById<EditText>(R.id.edit_text_room_number)

        dateTimeEditText.setText(classSchedule.dateTime)
        subjectCodeEditText.setText(classSchedule.subjectCode)
        subjectDescriptionEditText.setText(classSchedule.subjectDescription)
        roomNumberEditText.setText(classSchedule.roomNumber)

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
            .setPositiveButton("Save") { dialog, _ ->
                classSchedule.dateTime = dateTimeEditText.text.toString()
                classSchedule.subjectCode = subjectCodeEditText.text.toString()
                classSchedule.subjectDescription = subjectDescriptionEditText.text.toString()
                classSchedule.roomNumber = roomNumberEditText.text.toString()

                (activity as Costas_DashboardActivity).classScheduleList[position] = classSchedule
                (activity as Costas_DashboardActivity).adapter.notifyItemChanged(position)

                dialog.dismiss()
            }
            .setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }
}