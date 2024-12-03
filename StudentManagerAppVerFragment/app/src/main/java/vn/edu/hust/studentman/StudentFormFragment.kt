package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentFormFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_form, container, false)

        var studentName: String = ""
        var studentId: String = ""
        var pos: Int = -1

        arguments?.let {
            studentName = it.getString("studentName", "")
            studentId = it.getString("studentId", "")
            pos = it.getInt("position", -1)
        }

        val editTextName = view.findViewById<EditText>(R.id.edit_text_name)
        val editTextId = view.findViewById<EditText>(R.id.edit_text_id)

        editTextName.setText(studentName)
        editTextId.setText(studentId)

        view.findViewById<Button>(R.id.button_ok).setOnClickListener {
            if (editTextName.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Please enter student name!", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (editTextId.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Please enter student ID!", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            val args = Bundle()
            args.putString("studentName", editTextName.text.toString())
            args.putString("studentId", editTextId.text.toString())
            args.putInt("position", pos)
            findNavController().navigate(
                R.id.action_studentFormFragment_to_studentListFragment,
                args
            )
        }

        view.findViewById<Button>(R.id.button_cancel).setOnClickListener {
            findNavController().navigate(R.id.action_studentFormFragment_to_studentListFragment)
        }

        return view
    }
}