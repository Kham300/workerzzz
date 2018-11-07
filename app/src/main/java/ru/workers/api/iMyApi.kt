package ru.workers.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import ru.workers.model.objects.Data
import ru.workers.model.objects.Json4Kotlin_Base


interface iMyApi {
//    @get: GET("/api/control/objects?SessionGUID=2f76c8e7-0bef-4192-8cae-f27febf6b101")
//    val objects: Observable<Json4Kotlin_Base>

    @GET("/api/control/objects?SessionGUID=2f76c8e7-0bef-4192-8cae-f27febf6b101")
    fun getData(): Call<Json4Kotlin_Base>?
}