package com.example.mg156.assignmen3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.SupportActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragment_front.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [fragment_front.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentFront : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_front, container, false)
        val btnAbout = view.findViewById(R.id.btnAboutMe) as Button
        val btnPager = view.findViewById(R.id.btnViewPager) as Button
        val btnMovie = view.findViewById(R.id.btnMovieDetails) as Button

        //val customOnClickListener =  view.context

        btnAbout.setOnClickListener(View.OnClickListener { v -> listener?.onFragmentInteraction(v) })
        btnPager.setOnClickListener(View.OnClickListener { v -> listener?.onFragmentInteraction(v) })
        btnMovie.setOnClickListener(View.OnClickListener { v -> listener?.onFragmentInteraction(v) })
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(v: View)
    }

    //interface CustomOnClickListener {
      //  fun onClicked(v: View)
    //}

    companion object {
        @JvmStatic
        fun newInstance(param1: String) : FragmentFront{
            val args = Bundle()
            args.putSerializable(param1,"fragmentFront")
            val fragment = FragmentFront()
            fragment.arguments = args
            return fragment
        }

    }

    //fun buttonClicked(v: View) {
      //  customOnClickListener?.onClicked(v)
    //}
}
