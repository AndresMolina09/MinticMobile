package com.example.aplicacion_4b_g7.data.repositories

import android.graphics.Bitmap
import com.example.aplicacion_4b_g7.USER_COLLECTION
import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.lang.Exception

class LoginRepository(private val memoryDataSource: MemoryDataSource,
                      private val authService: FirebaseAuth, private val db: FirebaseFirestore,
                      private val storage: FirebaseStorage) {

    suspend fun login(email: String, password: String){
        try{
            authService.signInWithEmailAndPassword(email, password).await()
        }catch (e: FirebaseAuthException){
            throw  Exception(e.message)
        }
    }

    suspend fun logOut(){
        authService.signOut()
    }

    suspend fun getCurrentUser(): UserModel?{
        val user =  authService.currentUser
        if (user != null){
            var photo: String? = null
            if(user.photoUrl != null){
                photo = user.photoUrl.toString()
            }
            val info = db.collection(USER_COLLECTION).document(user.uid).get().await()
            return UserModel(user.uid, user.displayName!!, user.email!!,info.get("gender").toString(), photo)
        }
        return null
    }

    suspend fun signUp(email: String, name: String, password: String, gender: String){
        try{
            authService.createUserWithEmailAndPassword(email, password).await()
            val user = authService.currentUser!!
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user.updateProfile(profileUpdate).await()
            val userInfo = hashMapOf(
                "gender" to gender
            )
            db.collection(USER_COLLECTION).document(user.uid).set(userInfo).await()
        }catch (e: FirebaseAuthException){
            throw Exception(e.message)
        }
    }

    suspend fun uploadImage(image: Bitmap): UserModel?{
        val user = authService.currentUser
        if(user != null){
            val ref = storage.reference
            val imageRef = ref.child("${user.uid}.jpg")
            val baos: ByteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            imageRef.putBytes(baos.toByteArray())
            val url = imageRef.downloadUrl.await()
            val profileUpdate = userProfileChangeRequest {
                photoUri = url
            }
            user.updateProfile(profileUpdate).await()
        }

        return this.getCurrentUser()
    }

}