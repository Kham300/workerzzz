package ru.workers.api

import retrofit2.Call
import retrofit2.http.*
import ru.workers.model.objects.generated.Created
import ru.workers.model.objects.generated.Sections


interface IPlanApi {


//    @get: GET("/api/control/objects?SessionGUID=2f76c8e7-0bef-4192-8cae-f27febf6b101")
//    val objects: Observable<Created>


    @GET("/api/objects?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun getObjects(): Call<Created>?

    @GET("/api/roomTypes?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun getRooms(@Query("Object_id") objectId: String): Call<Created>

    @GET("/api/roomViews?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun getRoomsView(@Query("Object_id") objectId: String, @Query("RoomType_id") roomType: String): Call<Created>

    @GET("/api/workStages?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun getWorkStages(@Query("Object_id") objectId: String, @Query("RoomView_id") RoomViewId: String): Call<Created>

    @GET("/api/objectRow?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun getObjectRow(@Query("Object_id") objectId: String): Call<Created>

    @GET("/api/sections?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun getSections(@Query("Object_id") objectId: String, @Query("BuildingObjectRow_id") buildingObjRowId: String): Call<Sections>

    @FormUrlEncoded
    @POST("/api/planCommit?SessionGUID=918a7cf1-d02c-422e-b149-511b9fbd28dc")
    fun sendData(@FieldMap map: MutableMap<String, String>)

}