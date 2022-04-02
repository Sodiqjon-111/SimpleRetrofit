package uz.sodiqjon.simpleretrofit.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.DEFAULT_SPAN_COUNT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_bir.*
import kotlinx.android.synthetic.main.fragment_ikki.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.adapters.RecImageAdapter
import uz.sodiqjon.simpleretrofit.adapters.RecUserAdapter
import uz.sodiqjon.simpleretrofit.data.model.ImageModel
import uz.sodiqjon.simpleretrofit.data.model.UserModel
import uz.sodiqjon.simpleretrofit.sevices.Api
import java.lang.System.load

class Ikki : Fragment() {

    private lateinit var imageAdapter: RecImageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ikki, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: Api = retrofit.create(Api::class.java)


        imageAdapter = RecImageAdapter()
        recyclerViewImage.apply {
            layoutManager =
                GridLayoutManager(requireContext(),3,LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = imageAdapter
        }

        service.getImages().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(
                call: Call<List<ImageModel>>,
                response: Response<List<ImageModel>>
            ) {
                Log.d(ContentValues.TAG, "********************")
                if (response.isSuccessful) {

                    response.body()?.let { imageAdapter.updateList(it) }
                } else {
                    Toast.makeText(requireContext(), response.body().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.d(ContentValues.TAG, "88888888888888888888888")
                Toast.makeText(requireContext(), "---------Failure-----------", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }
}


