package edu.group.learning_contract

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.semantics.text
import androidx.recyclerview.widget.RecyclerView
import edu.group.learning_contract.databinding.ItemStudentBinding

class StudentAdapter(private val studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.studentImage.setImageResource(student.imageRes)
            binding.studentName.text = student.name
            binding.studentStrand.text = student.strand
            binding.studentAddress.text = student.address

            binding.studentMotivation.text =
                binding.root.context.getString(R.string.motivation, student.motivation)

            binding.studentHindrances.text =
                binding.root.context.getString(R.string.hindrances, student.hindrances)

            binding.studentGoals.text =
                binding.root.context.getString(R.string.goals, student.goals)

            binding.studentExpectations.text =
                binding.root.context.getString(R.string.expectations, student.expectations)

            binding.studentContributions.text =
                binding.root.context.getString(R.string.contributions, student.contribution)

            // Reset switch state for recycled views
            binding.contractSwitch.setOnCheckedChangeListener(null)
            binding.contractSwitch.isChecked = false
            binding.contractLayout.visibility = View.GONE

            binding.contractSwitch.setOnCheckedChangeListener { _, isChecked ->
                binding.contractLayout.visibility = if (isChecked) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (studentList.isEmpty()) {
            return // Should not happen if getItemCount is 0 for empty list
        }

        val actualItemCount = studentList.size
        val studentToBind: Student = when (position) {
            0 -> { // Phantom page at the start, show the last actual student
                studentList[actualItemCount - 1]
            }
            actualItemCount + 1 -> { // Phantom page at the end, show the first actual student
                studentList[0]
            }
            else -> { // Actual student pages (1 to actualItemCount)
                studentList[position - 1]
            }
        }
        holder.bind(studentToBind)
    }

    override fun getItemCount(): Int {
        // If the list is empty, return 0. Otherwise, size + 2 for phantom pages.
        return if (studentList.isEmpty()) 0 else studentList.size + 2
    }
}
