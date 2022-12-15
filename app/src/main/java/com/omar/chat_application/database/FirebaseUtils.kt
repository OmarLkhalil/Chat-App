package com.omar.chat_application.database

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.omar.chat_application.database.model.AppUser


fun addUserToFireStore(user: AppUser,
                       onSuccessListener: OnSuccessListener<Void>,
                       onFailureListener: OnFailureListener,
                       ){
    val db = Firebase.firestore
    val userCollection = db.collection(AppUser.COLLECTION_NAME)
    val userDocument   = userCollection.document(user.id!!)

    userDocument.set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun signIn(uid: String,
           onSuccessListener: OnSuccessListener<DocumentSnapshot>,
           onFailureListener: OnFailureListener
    ){
    val db = Firebase.firestore
    val userCollection = db.collection(AppUser.COLLECTION_NAME)
    userCollection.document(uid)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}