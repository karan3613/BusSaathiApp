package com.example.bususerapp.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bususerapp.Models.UserIdResponse
import com.example.bususerapp.Models.UserLoginResponse
import com.example.bususerapp.Models.UserRegisterResponse
import com.example.bususerapp.Resource
import com.example.bususerapp.Retrofit.BusRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class userIdResponseState(
    val isLoading: Boolean = false,
    val userIdResponse: UserIdResponse? = null,
    val error: String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository : BusRepoImpl
) : ViewModel() {
    private val _userResponse = MutableStateFlow(userIdResponseState())
    val userResponse: StateFlow<userIdResponseState> = _userResponse

    private val _userLoginResponse = MutableStateFlow(userIdResponseState())
    val userLoginResponse: StateFlow<userIdResponseState> = _userLoginResponse

    private val _isUsernameSaved = mutableStateOf(false)
    val isUsernameSaved: State<Boolean> = _isUsernameSaved

    fun verify_bus(busLoginResponse: UserLoginResponse) {
        viewModelScope.launch {
            val result = repository.loginUser(busLoginResponse)
            when(result) {
                is Resource.Error -> _userResponse.value = userIdResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _userResponse.value = userIdResponseState(isLoading = true)
                is Resource.Success -> {
                    _userResponse.value = userIdResponseState(userIdResponse = result.data)
                    _isUsernameSaved.value = true
                }
            }
        }
    }

    fun register_bus(userRegisterResponse : UserRegisterResponse){
        viewModelScope.launch {
            val result = repository.registerUser(userRegisterResponse)
            when(result){
                is Resource.Error -> _userResponse.value = userIdResponseState(error = result.message ?: "An unknown error occurred")
                is Resource.Loading -> _userResponse.value = userIdResponseState(isLoading = true)
                is Resource.Success ->{
                    _userResponse.value = userIdResponseState(userIdResponse = result.data)
                    _isUsernameSaved.value = true
                }
            }
        }
    }



}