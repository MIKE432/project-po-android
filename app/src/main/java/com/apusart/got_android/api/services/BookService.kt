package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.*

class BookService {
    private val service = RetrofitImpl.retrofit

    suspend fun getBookByOwnerId(id: Int): Resource<Book> {
        return performRequest { service.getBookByOwnerId(id) }
    }
}
