package uz.sodiqjon.simpleretrofit.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_bir.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.adapters.RecUserAdapter
import uz.sodiqjon.simpleretrofit.data.model.BaseResponse
import uz.sodiqjon.simpleretrofit.data.model.UserModel
import uz.sodiqjon.simpleretrofit.sevices.Api


class Bir : Fragment() {

    private lateinit var userAdapter: RecUserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_bir, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh.isRefreshing(

        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: Api = retrofit.create(Api::class.java)


        userAdapter = RecUserAdapter()
        recyclerViewUsers.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = userAdapter
        }

        service.getUsers().enqueue(object :Callback<List<UserModel>>{
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                Log.d(TAG, "********************")
               if (response.isSuccessful){
                   response.body()?.let { userAdapter.updateList(it) }
               } else{
                   Toast.makeText(requireContext(), response.body().toString(), Toast.LENGTH_SHORT).show()
               }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.d(TAG, "88888888888888888888888")
                Toast.makeText(requireContext(), "---------Failure-----------", Toast.LENGTH_SHORT).show()
            }
        })

    }

}