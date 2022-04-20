package uz.sodiqjon.simpleretrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hide
import kotlinx.android.synthetic.main.fragment_bir.*
import kotlinx.coroutines.launch
import show
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.adapters.RecUserAdapter
import uz.sodiqjon.simpleretrofit.core.Resource
import uz.sodiqjon.simpleretrofit.data.model.UserModel
import uz.sodiqjon.simpleretrofit.view_models.UserViewModel


class Bir : Fragment() {
    private val viewModel by activityViewModels<UserViewModel>()
    private lateinit var userAdapter: RecUserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bir, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUsers()
        }

        userAdapter = RecUserAdapter()
        recyclerViewUsers.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = userAdapter

        }

        viewModel.userList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    if (!swipeRefreshLayout.isRefreshing) {
                        progressBar.show()
                    }
                }
                is Resource.Success -> {
                    userAdapter.updateList(it.data)
                    insertUsers(it.data)
                    progressBar.hide()
                    swipeRefreshLayout.isRefreshing = false
                }
                is Resource.Failure -> {
                    loadFromDb()
                    swipeRefreshLayout.isRefreshing = false
                    progressBar.hide()
                }
            }
        }
    }

    private fun loadFromDb() {
        lifecycleScope.launch {
            userAdapter.updateList(viewModel.getUserDetails())

        }
    }

    private fun insertUsers(data: List<UserModel>) {
        data.forEach {
            lifecycleScope.launch {
                viewModel.saveUsers(it)
            }
        }
    }

}