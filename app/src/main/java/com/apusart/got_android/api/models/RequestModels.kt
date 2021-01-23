package com.apusart.got_android.api.models

import com.google.gson.GsonBuilder
import retrofit2.Response
import java.lang.Exception

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR,
        PENDING
    }

    companion object {
        fun<T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun<T> error(message: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun<T> pending(data: T? = null): Resource<T> {
            return Resource(Status.PENDING, data, null)
        }
    }
}

fun <T> handleResource(
    res: Resource<T>,
    onSuccess: ((data: T?) -> Unit) = { _ -> },
    onPending: ((data: T?) -> Unit) = { _ -> },
    onError: ((message: String?, data: T?) -> Unit) = { _, _ -> }
) {
    when(res.status) {
        Resource.Status.SUCCESS -> onSuccess(res.data)
        Resource.Status.PENDING -> onPending(res.data)
        Resource.Status.ERROR -> onError(res.message, res.data)
    }
}

suspend fun<T> performRequest(func: suspend () -> Response<T>): Resource<T> {
    val response = func()

    val body = response.body()
    val errorBody = parseErrorBody(response.errorBody()?.string())

    if (!response.isSuccessful)
        return Resource.error(errorBody.message)

    return Resource.success(body!!)
}

fun parseErrorBody(errorBody: String?): ErrorBody {
    if (errorBody == null)
        return ErrorBody()
    val gson = GsonBuilder().create()

    return try {
        gson.fromJson(errorBody, ErrorBody::class.java)
    } catch (x: Exception) {
        ErrorBody(500, "Internal server error")
    }

}

data class ErrorBody(
    val code: Int? = -1,
    val message: String? = ""
)


data class Touple<A, B>(val a: A, val b: B)