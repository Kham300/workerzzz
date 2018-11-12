package ru.workers.model.objects.generated

import com.google.gson.annotations.SerializedName


data class Created(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("errorCode")
        val errorCode: String,
        @SerializedName("errorText")
        val errorText: String,
        @SerializedName("format")
        val format: String,
        @SerializedName("metaData")
        val metaData: MetaData,
        @SerializedName("queryText")
        val queryText: String,
        @SerializedName("success")
        val success: Boolean,
        @SerializedName("totalCount")
        val totalCount: String
)

data class Data(
        @SerializedName("NameRu")
        val nameRu: String,
        @SerializedName("tid")
        val tid: String
){
        override fun toString(): String {
                return nameRu
        }
}

data class MetaData(
        @SerializedName("fields")
        val fields: List<Field>,
        @SerializedName("validations")
        val validations: Any?
)

data class Field(
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("useNull")
        val useNull: Boolean
)