package com.example.android.movies.app.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.android.movies.databinding.FragmentSortDialogBinding
import com.example.android.movies.utils.ISortBy

/**
 * A simple [Fragment] subclass.
 * Use the [SortDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SortDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentSortDialogBinding
    private var listener: ISortBy? = null
    private var selectedSortType: String = "popularity.asc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortDialogBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        binding.btnDialogOk.setOnClickListener {
            listener?.sortBy(selectedSortType)
            dialog?.dismiss()
        }
    }

    private fun setupUI() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            selectedSortType = group.findViewById<RadioButton>(checkedId).tag as String
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ISortBy
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        const val TAG = "SimpleDialog"
        @JvmStatic
        fun newInstance() = SortDialogFragment()
    }
}