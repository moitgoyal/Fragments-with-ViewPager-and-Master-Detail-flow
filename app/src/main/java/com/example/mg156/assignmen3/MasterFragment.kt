package com.example.mg156.assignmen3

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MasterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MasterFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MasterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(savedInstanceState != null){
                currentIndex = savedInstanceState.getInt("currentposition")
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val masterView = inflater.inflate(R.layout.fragment_master, container, false)

        val indexValue = masterView.findViewById(R.id.txtCurrentIndex) as TextView
        indexValue.setText("Current Index: " + currentIndex)
        val btnInc = masterView.findViewById(R.id.btnIncrease) as Button
        val btnDec = masterView.findViewById(R.id.btnDecrease) as Button
        btnInc.setOnClickListener(View.OnClickListener { v ->
            var i: Int = currentIndex
            if (i < 9) {
                i++
                currentIndex = i
                indexValue.setText("Current Index: " + i)
            }

            listener?.onFragmentInteraction(v,i)
        })
        btnDec.setOnClickListener(View.OnClickListener { v ->
            var i: Int = currentIndex
            if (i > 0) {
                i--
                currentIndex = i

                indexValue.setText("Current Index: " + i)
            }
            listener?.onFragmentInteraction(v,i)
        })
        return masterView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MasterFragment.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(v: View, index: Int)
    }

    override fun onSaveInstanceState(outstate: Bundle) {
        super.onSaveInstanceState(outstate)
        outstate.putInt("currentposition", currentIndex)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MasterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                MasterFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}
