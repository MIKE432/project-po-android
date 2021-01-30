package com.apusart.got_android.api.repositories

import com.apusart.got_android.api.models.Book
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.services.BookService

class BookRepository {
    private val service = BookService()

    suspend fun getBookByOwner(ownerId: Int): Resource<Book> {
        return service.getBookByOwnerId(ownerId);
    }
}