package com.example.bususerapp.Retrofit

import com.example.bususerapp.Models.BusListStatusResponse
import com.example.bususerapp.Models.UserIdResponse
import com.example.bususerapp.Models.UserLoginResponse
import com.example.bususerapp.Models.UserRegisterResponse
import com.example.bususerapp.Resource
import com.example.driverapp.Data.Models.AreaResponse
import com.example.driverapp.Data.Models.BusLocationResponse
import com.example.driverapp.Data.Models.BusResponse

interface BusRepository {
    suspend fun getAreas(name:String): Resource<List<AreaResponse>>
    suspend fun getBusStatus(startId: Int, endId: Int): Resource<BusListStatusResponse>
    suspend fun getBusDetails(busId: Int): Resource<BusResponse>
    suspend fun getBusLocation(busId: Int): Resource<BusLocationResponse>
    suspend fun loginUser(userLoginResponse: UserLoginResponse): Resource<UserIdResponse>
    suspend fun registerUser(userRegisterResponse: UserRegisterResponse): Resource<UserIdResponse>
}