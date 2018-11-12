package ru.workers.model.objects.generated
import com.google.gson.annotations.SerializedName


data class Sections(
    @SerializedName("data")
    val `data`: List<Data2>,
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("errorText")
    val errorText: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("metaData")
    val metaData: MetaData2,
    @SerializedName("queryText")
    val queryText: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("totalCount")
    val totalCount: String
)

data class MetaData2(
    @SerializedName("fields")
    val fields: List<Field2>,
    @SerializedName("validations")
    val validations: Any?
)

data class Field2(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("useNull")
    val useNull: Boolean
)

data class Data2(
    @SerializedName("BuildingObjectRow_id")
    val buildingObjectRowId: String,
    @SerializedName("Floors")
    val floors: String,
    @SerializedName("SectionName")
    val sectionName: String,
    @SerializedName("tid")
    val tid: String
){
    override fun toString(): String {
        return sectionName
    }

}