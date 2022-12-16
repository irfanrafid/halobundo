package com.example.halobundo.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.halobundo.R
import com.example.halobundo.adapter.AppointmentAdapter
import com.example.halobundo.adapter.ArticleAdapter
import com.example.halobundo.model.Appointment
import com.example.halobundo.model.Article
import com.example.halobundo.util.Preferences
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var dataList = ArrayList<Appointment>()

    private var articleList = ArrayList<Article>()

    private lateinit var preferences: Preferences
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var articleDatabaseReference: DatabaseReference
    private lateinit var appointmentDatabaseReffrence: DatabaseReference
    lateinit var tv_username: TextView
    lateinit var rv_appoitment: RecyclerView
    lateinit var rv_article: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_username = requireView().findViewById(R.id.tvUsername)
        rv_appoitment = requireView().findViewById(R.id.rv_appointment)
        rv_article = requireView().findViewById(R.id.rv_article)

        rv_appoitment.setHasFixedSize(true)
        rv_appoitment.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_article.layoutManager = LinearLayoutManager(context)



        preferences = Preferences(requireActivity().applicationContext)
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Appointment")
        articleDatabaseReference = FirebaseDatabase.getInstance().getReference("Article")
        appointmentDatabaseReffrence = FirebaseDatabase.getInstance().getReference("Appointment")


        tv_username.setText(preferences.getValues("nama"))

        getData()
    }


    private fun getData() {
        articleDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articleList.clear()

                for (getDataArticle in snapshot.children){
                    var article = getDataArticle.getValue(Article::class.java)
                    articleList.add(article!!)
                }

                rv_article.adapter = ArticleAdapter(articleList){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        mDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.children) {
                    var appointment = getdataSnapshot.getValue(Appointment::class.java)
                    dataList.add(appointment!!)
                }

                rv_appoitment.adapter = AppointmentAdapter(dataList) {

                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}