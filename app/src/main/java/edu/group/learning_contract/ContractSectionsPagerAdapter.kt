package edu.group.learning_contract

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContractSectionsPagerAdapter(
    activity: FragmentActivity,
    private val student: Student
) : FragmentStateAdapter(activity) {

    // Define the order and titles of the tabs
    private val tabTitles = listOf("Motivation", "Hindrances", "Goals", "Expectations", "Contributions")
    private val sections = listOf(
        student.motivation,
        student.hindrances,
        student.goals,
        student.expectations,
        student.contribution // Assuming 'contribution' is the correct field name in Student class
    )

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun createFragment(position: Int): Fragment {
        return ContractSectionFragment.newInstance(sections[position])
    }

    fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}