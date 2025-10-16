package com.example.bususerapp.Retrofit

import com.example.bususerapp.Models.BusListStatusResponse
import com.example.bususerapp.Models.UserIdResponse
import com.example.bususerapp.Models.UserLoginResponse
import com.example.bususerapp.Models.UserRegisterResponse
import com.example.bususerapp.Resource
import com.example.driverapp.Data.Models.AreaResponse
import com.example.driverapp.Data.Models.BusLocationResponse
import com.example.driverapp.Data.Models.BusResponse
import javax.inject.Inject


class BusRepoImpl @Inject constructor(
    private val retrofitApi: RetrofitApi
) : BusRepository {

    override suspend fun getAreas(name : String): Resource<List<AreaResponse>> {
        return try {
            val response = retrofitApi.getArea(name = name )
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch areas: ${e.localizedMessage}")
        }
    }

    override suspend fun getBusStatus(startId: Int, endId: Int): Resource<BusListStatusResponse> {
        return try {
            val response = retrofitApi.getStatus(startId, endId)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch bus status: ${e.localizedMessage}")
        }
    }

    override suspend fun getBusDetails(busId: Int): Resource<BusResponse> {
        return try {
            val response = retrofitApi.getBusInfo(busId)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch bus details: ${e.localizedMessage}")
        }
    }

    override suspend fun getBusLocation(busId: Int): Resource<BusLocationResponse> {
        return try {
            val response = retrofitApi.getBusLocation(busId)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch bus location: ${e.localizedMessage}")
        }
    }

    override suspend fun loginUser(userLoginResponse: UserLoginResponse): Resource<UserIdResponse> {
        return try {
            val response = retrofitApi.getUserLogin(userLoginResponse)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Login failed: ${e.localizedMessage}")
        }
    }

    override suspend fun registerUser(userRegisterResponse: UserRegisterResponse): Resource<UserIdResponse> {
        return try {
            val response = retrofitApi.getUserRegister(userRegisterResponse)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Registration failed: ${e.localizedMessage}")
        }
    }
}