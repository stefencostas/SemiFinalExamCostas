package com.ucb.semifinal.costas

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ClassScheduleDialogFragment(private val classSchedule: ClassSchedule, private val position: Int) : DialogFragment() {

    companion object {
        fun newInstance(classSchedule: ClassSchedule, position: Int): ClassScheduleDialogFragment {
            val fragment = ClassScheduleDialogFragment(classSchedule, position)
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Options")
                .setItems(arrayOf("Edit", "Delete")) { _, which ->
                    when (which) {
                        0 -> (activity as Costas_DashboardActivity).onEditClick(classSchedule, position)
                        1 -> onDeleteClick(classSchedule, position)
                    }
                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }

    private fun onEditClick(classSchedule: ClassSchedule, position: Int) {
        (activity as Costas_DashboardActivity).onEditClick(classSchedule, position)
    }

    private fun onDeleteClick(classSchedule: ClassSchedule, position: Int) {
        (activity as Costas_DashboardActivity).onDeleteClick(position)
    }
}
