package ru.workers.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.workers.model.objects.Created


interface IPlanApi {


//    @get: GET("/api/control/objects?SessionGUID=2f76c8e7-0bef-4192-8cae-f27febf6b101")
//    val objects: Observable<Created>


    @GET("/api/objects?SessionGUID=2f76c8e7-0bef-4192-8cae-f27febf6b101")
    fun getObjects(): Call<Created>?

    @GET("/api/roomTypes?SessionGUID=2f76c8e7-0bef-4192-8cae-f27febf6b101")
    fun getRooms(@Query("Object_id") objectId: String): Call<Created>
    @GET("/api/roomViews")
    fun getRoomsView(@Query("Object_id") objectId: String, @Query("RoomType") roomType: String, @Query("SessionGUID")sessinGuid: String): Call<Created>

    @GET("/api/workStages")
    fun getWorkStages(@Query("Object_id") objectId: String, @Query("RoomView_id") RoomViewId: String, @Query("SessionGUID")sessinGuid: String): Call<Created>

    @GET("/api/objectRow")
    fun getObjectRow(@Query("Object_id") objectId: String, @Query("SessionGUID")sessinGuid: String): Call<Created>
}