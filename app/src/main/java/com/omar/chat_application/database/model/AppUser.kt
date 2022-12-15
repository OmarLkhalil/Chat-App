package com.omar.chat_application.database.model

data class AppUser(
    var id: String? = null,
    var userName: String? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null
)
{
    companion object{
        const val COLLECTION_NAME = "users"
    }
}