package uz.sodiqjon.simpleretrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import hide
import isVisible
import kotlinx.android.synthetic.main.fragment_uch.*
import show
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.adapters.RestaurantsAdapter
import uz.sodiqjon.simpleretrofit.core.ResourceFlow
import uz.sodiqjon.simpleretrofit.view_models.RestaurantsViewModel

class Uch : Fragment() {
    private val viewModel by activityViewModels<RestaurantsViewModel>()
    private lateinit var restaurantAdapter: RestaurantsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_uch, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        restaurantAdapter = RestaurantsAdapter()
        recyclerViewRestaurants.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = restaurantAdapter

        }
        viewModel.restaurants.observe(viewLifecycleOwner) { result ->
           result.data?.let { it -> restaurantAdapter.submitList(it) }
            progressBar.isVisible = result is ResourceFlow.Loading && result.data.isNullOrEmpty()
        }


    }
}