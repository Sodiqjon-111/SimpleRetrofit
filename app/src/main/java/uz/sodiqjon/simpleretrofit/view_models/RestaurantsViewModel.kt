package uz.sodiqjon.simpleretrofit.view_models

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData
import uz.sodiqjon.simpleretrofit.repositories.RestaurantRepository
import uz.sodiqjon.simpleretrofit.sevices.Auth
import uz.sodiqjon.simpleretrofit.sevices.RestaurantApi
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
//    private val repository: AuthRepo,
//    private val dataSource: AuthDataSource,
    //   private val api:RestaurantApi
    repository: RestaurantRepository
) : ViewModel() {

//    init {
//        repository.getRestaurants().asLiveData()
//    }


    val restaurants = repository.getRestaurants().asLiveData()
//
//    private val restaurantsLiveData = MutableLiveData<List<RestaurantsData>>()
//    val restaurants: LiveData<List<RestaurantsData>> = restaurantsLiveData
//
//
//    init {
//        viewModelScope.launch {
//            val restaurants = api.getRestaurans()
//            restaurantsLiveData.value = restaurants
//        }
//    }
}