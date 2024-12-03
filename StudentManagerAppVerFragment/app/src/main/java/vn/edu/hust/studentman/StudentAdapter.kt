package vn.edu.hust.studentman

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(
  context: Context,
  private val students: MutableList<StudentModel>
) : ArrayAdapter<StudentModel>(context, 0, students) {

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val student = students[position]

    val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.layout_student_item, parent, false)

    val textStudentName = view.findViewById<TextView>(R.id.text_student_name)
    val textStudentId = view.findViewById<TextView>(R.id.text_student_id)

    textStudentName.text = student.studentName
    textStudentId.text = student.studentId

    return view
  }
}
