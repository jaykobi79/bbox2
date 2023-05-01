package com.sgtsoft.bbox2.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

// M3uService.kt
interface M3uService {
    @GET
    suspend fun getM3uFile(@Url url: String): ResponseBody
}
