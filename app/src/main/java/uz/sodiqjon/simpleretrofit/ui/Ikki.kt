package uz.sodiqjon.simpleretrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ikki.*
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.adapters.RecImageAdapter
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

        imageAdapter = RecImageAdapter()
        recyclerViewImage.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = imageAdapter
        }

    }
}


