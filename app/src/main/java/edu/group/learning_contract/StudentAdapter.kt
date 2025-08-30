package edu.group.learning_contract

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import edu.group.learning_contract.databinding.ItemStudentBinding

// 1. Modified constructor to accept FragmentActivity
class StudentAdapter(
    private val studentList: List<Student>,
    private val fragmentActivity: FragmentActivity
) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.studentImage.setImageResource(student.imageRes)
            binding.studentName.text = student.name
            binding.studentStrand.text = student.strand
            binding.studentAddress.text = student.address

            // 2. Old text setting and switch logic is removed.

            // 3. Setup inner ViewPager2 and TabLayout
            val sectionsPagerAdapter = ContractSectionsPagerAdapter(fragmentActivity, student)
            binding.contractViewPager.adapter = sectionsPagerAdapter

            TabLayoutMediator(binding.contractTabLayout, binding.contractViewPager) { tab, position ->
                tab.text = sectionsPagerAdapter.getPageTitle(position)
            }.attach()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (studentList.isEmpty()) {
            // This case should ideally be handled by getItemCount returning 0
            // and MainActivity not even trying to set up the adapter if the list is empty.
            // However, if it reaches here, we might want to ensure ViewHolder doesn't crash.
            // For now, returning, assuming getItemCount and MainActivity handle empty states.
            return
        }

        // Your existing logic for handling phantom pages
        val actualItemCount = studentList.size
        val studentToBind: Student = when (position) {
            0 -> studentList[actualItemCount - 1] // Phantom start
            actualItemCount + 1 -> studentList[0] // Phantom end
            else -> studentList[position - 1]     // Actual student
        }
        holder.bind(studentToBind)
    }

    override fun getItemCount(): Int {
        return if (studentList.isEmpty()) 0 else studentList.size + 2
    }
}
