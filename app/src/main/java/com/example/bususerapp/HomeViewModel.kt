package com.example.bususerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bususerapp.Models.BusListStatusResponse
import com.example.bususerapp.Retrofit.BusRepoImpl
import com.example.driverapp.Data.Models.AreaResponse
import com.example.driverapp.Data.Models.BusLocationResponse
import com.example.driverapp.Data.Models.BusResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class busResponseState(
    val isLoading: Boolean = false,
    val busResponse: BusResponse? = null,
    val error: String = ""
)
data class busLocationResponseState(
    val isLoading: Boolean = false,
    val busLocationResponse: BusLocationResponse? = null,
    val error: String = ""
)

data class busListStatusResponseState(
    val isLoading: Boolean = false,
    val busListStatusResponse: BusListStatusResponse? = null,
    val error: String = ""
)
data class AreaResponseState(
    val isLoading: Boolean = false,
    val busDestinationResponse: List<AreaResponse>? = null,
    val error: String = ""
)


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  repository : BusRepoImpl
) : ViewModel()
{

    private val _busResponse = MutableStateFlow(busResponseState())
    val busResponse: StateFlow<busResponseState> = _busResponse

    private val _busLocation = MutableStateFlow(busLocationResponseState())
    val busLocation: StateFlow<busLocationResponseState> = _busLocation

    private val _busListStatus = MutableStateFlow(busListStatusResponseState())
    val busListStatus : StateFlow<busListStatusResponseState> = _busListStatus

    private val _startDestinations = MutableStateFlow(AreaResponseState()) // Using StateFlow
    val startDestinations: StateFlow<AreaResponseState> = _startDestinations

    private val _endDestinations = MutableStateFlow(AreaResponseState()) // StateFlow for end destinations
    val endDestinations: StateFlow<AreaResponseState> = _endDestinations


    fun get_buses(startId : Int , endId : Int){
        viewModelScope.launch {
            val result = repository.getBusStatus(startId , endId)
            when(result){
                is Resource.Error -> _busListStatus.value = busListStatusResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _busListStatus.value = busListStatusResponseState(isLoading = true)
                is Resource.Success -> _busListStatus.value = busListStatusResponseState(busListStatusResponse = result.data)
            }

        }
    }
    fun get_area_start(name : String ){
        viewModelScope.launch {
            val result = repository.getAreas(name)
            delay(3000)
            when(result){
                is Resource.Error -> _startDestinations.value = AreaResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _startDestinations.value = AreaResponseState(isLoading = true)
                is Resource.Success -> _startDestinations.value = AreaResponseState(busDestinationResponse = result.data ?: emptyList())
            }
        }
    }
    fun get_area_end(name: String){
        viewModelScope.launch {
            val result = repository.getAreas(name)
            delay(3000)
            when(result){
                is Resource.Error -> _endDestinations.value = AreaResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _endDestinations.value = AreaResponseState(isLoading = true)
                is Resource.Success -> _endDestinations.value = AreaResponseState(busDestinationResponse = result.data ?: emptyList())

            }
        }
    }


    fun get_details(busId: Int) {
        viewModelScope.launch {
            val result = repository.getBusDetails(busId)
            when(result){
                is Resource.Error -> _busResponse.value = busResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _busResponse.value = busResponseState(isLoading = true)
                is Resource.Success -> _busResponse.value = busResponseState(busResponse = result.data)
            }
        }
    }
    fun get_location(busId: Int){
        viewModelScope.launch {
            val result = repository.getBusLocation(busId)
            when(result){
                is Resource.Error -> _busLocation.value = busLocationResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _busLocation.value = busLocationResponseState(isLoading = true)
                is Resource.Success -> _busLocation.value = busLocationResponseState(busLocationResponse = result.data)
            }
        }
    }

}