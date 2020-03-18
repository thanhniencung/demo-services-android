package com.code4func.helloandroid

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * A simple [Fragment] subclass.
 */
class DemoFragment : Fragment() {
    lateinit var tvTextView: TextView
    lateinit var btnClick: Button

    lateinit var callBack: ActivityCallBack

    interface ActivityCallBack {
        fun sendMessage(msg: String)
    }

    fun setActivityCallBack(callBack: ActivityCallBack) {
        this.callBack = callBack
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toast.makeText(activity, "onAttach", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "onAttach", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTextView = view.findViewById(R.id.tvTextView)
        btnClick = view.findViewById(R.id.btnClick)

        callBack.sendMessage("send from fragment")

        btnClick.setOnClickListener {
            tvTextView.text = "Update inside Fragment"
            callBack.sendMessage("send from fragment")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(activity, "onActivityCreated", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(activity, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(activity, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(activity, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(activity, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(activity, "onDestroyView", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        Toast.makeText(activity, "onDetach", Toast.LENGTH_SHORT).show()
    }

}
