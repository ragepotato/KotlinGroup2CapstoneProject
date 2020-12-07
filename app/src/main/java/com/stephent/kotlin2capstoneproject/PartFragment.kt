package com.stephent.kotlin2capstoneproject

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_list_view.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException
import kotlin.concurrent.thread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PartFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class PartFragment (val link: String ): Fragment() {


    private lateinit var recyclerViewA: RecyclerView
    private var titlesList = mutableListOf<String>()
    private var linkList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }





        val content = Content(link)
        content.execute()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.recycler_list_view, container, false)
        recyclerViewA = rootView.findViewById(R.id.recyclerView) as RecyclerView // Add this
        recyclerViewA.layoutManager = LinearLayoutManager(this.context)
        recyclerViewA.adapter = CardAdapter(titlesList, linkList)
        return rootView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    private fun addToList(title: String, link: String, image: Int) {
        titlesList.add(title)
        linkList.add(link)
        imagesList.add(image)

    }


    private inner class Content(val link: String) : AsyncTask<Void, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            recyclerView.adapter?.notifyDataSetChanged()
        }

        override fun doInBackground(vararg params: Void?): String? {

            try {
                var doc =
                    Jsoup.connect(link)
                        .get();
                var partElements: Elements = doc.select("h3._eYtD2XCVieq6emjKBH3m")

                var partLinks: Elements = doc.select("div._10wC0aXnrUKfdJ4Ssz-o14 a")
                getActivity()?.runOnUiThread {
                    for (i in partElements.indices) {
                        var partTitle = partElements.get(i).text()
                        var link = partLinks.get(i).toString().split("\"")
                        addToList(partTitle, link[1], R.mipmap.ic_launcher_round)
                    }
                    for (i in partElements.indices) {
                        var partTitle = partElements.get(i).text()
                        var link = partLinks.get(i).toString().split("\"")
                        addToList(partTitle, link[1], R.mipmap.ic_launcher_round)
                    }
                }


            } catch (e: IOException) {
                e.printStackTrace()
            }

            return ""
        }
    }




    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PartFragment("").apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
