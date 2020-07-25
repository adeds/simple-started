package id.adeds.started.data.model

import id.adeds.started.util.Status

data class BaseModel<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
)