package com.example.bususerapp.Retrofit

import com.example.bususerapp.Models.BusListStatusResponse
import com.example.bususerapp.Models.UserIdResponse
import com.example.bususerapp.Models.UserLoginResponse
import com.example.bususerapp.Models.UserRegisterResponse
import com.example.driverapp.Data.Models.AreaResponse
import com.example.driverapp.Data.Models.BusLocationResponse
import com.example.driverapp.Data.Models.BusResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitApi {
    @GET("/area/get")
    suspend fun getArea(
        @Query("query") name : String
    ): List<AreaResponse>

    @GET("/area/route/buses/{start_id}/{end_id}")
    suspend fun getStatus(
        @Path("start_id") start_id: Int,
        @Path("end_id") end_id: Int
    ): BusListStatusResponse

    @GET("/bus/get/details/{bus_id}")
    suspend fun getBusInfo(
        @Path("bus_id") bus_id : Int
    ) : BusResponse

    @POST("/busLocation/get/{bus_id}")
    suspend fun getBusLocation(
        @Path("bus_id") bus_id : Int
    ) : BusLocationResponse

    @POST("/user/login")
    suspend fun getUserLogin(
    @Body userLoginResponse: UserLoginResponse
    ) : UserIdResponse

    @POST("/user/login")
    suspend fun getUserRegister(
        @Body userRegisterResponse: UserRegisterResponse
    ) : UserIdResponse




}