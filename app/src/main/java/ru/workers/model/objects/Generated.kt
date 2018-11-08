package ru.workers.model.objects

data class Created(
    val `data`: List<Data>,
    val errorCode: String,
    val errorText: String,
    val format: String,
    val metaData: MetaData,
    val queryText: String,
    val success: Boolean,
    val totalCount: String
)

data class Data(
    val NameRu: String,
    val tid: String
)

data class MetaData(
    val fields: List<Field>,
    val validations: Any
)

data class Field(
    val name: String,
    val type: String,
    val useNull: Boolean
)