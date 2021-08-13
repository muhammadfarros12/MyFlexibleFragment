package com.farroos.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment() {

    lateinit var btnChoose: Button
    lateinit var btnClose: Button
    lateinit var rgOptions: RadioGroup
    lateinit var rbSaf: RadioButton
    lateinit var rbMou: RadioButton
    lateinit var rbLvg: RadioButton
    lateinit var rbMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMou = view.findViewById(R.id.rb_mou)
        rbMoyes = view.findViewById(R.id.rb_moyes)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = null
                when (checkedRadioButtonId) {
                    R.id.rb_saf -> coach = rbSaf.text.toString().trim()
                    R.id.rb_moyes -> coach = rbMoyes.text.toString().trim()
                    R.id.rb_mou -> coach = rbMou.text.toString().trim()
                    R.id.rb_lvg -> coach = rbLvg.text.toString().trim()
                }

                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()

            }
        }

        btnClose.setOnClickListener {
            dialog?.cancel()
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }


}