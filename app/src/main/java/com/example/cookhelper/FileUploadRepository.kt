package com.example.cookhelper

import android.net.Uri
import com.example.cookhelper.entities.AsyncResult
import com.example.cookhelper.extensions.uploadFile
import com.google.firebase.storage.StorageReference

class FileUploadRepository(private val storage: StorageReference){

    suspend fun uploadFile(uri: Uri): AsyncResult<Uri> {
        return storage.uploadFile(uri)
    }

}