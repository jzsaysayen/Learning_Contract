package edu.group.learning_contract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContractSectionFragment : Fragment() {

    private var textContent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textContent = it.getString(ARG_TEXT_CONTENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contract_section, container, false)
        val textView: TextView = view.findViewById(R.id.sectionTextView)
        textView.text = textContent
        return view
    }

    companion object {
        private const val ARG_TEXT_CONTENT = "text_content"

        @JvmStatic
        fun newInstance(textContent: String) =
            ContractSectionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXT_CONTENT, textContent)
                }
            }
    }
}