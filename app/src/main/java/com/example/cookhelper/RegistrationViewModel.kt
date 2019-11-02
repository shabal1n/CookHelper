package com.example.cookhelper


import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.cookhelper.base.BaseViewModel

class RegistrationViewModel(private val repository: FileUploadRepository): BaseViewModel(){

    val downloadUriLiveData = MutableLiveData<Uri>()

    fun uploadFile(uri: Uri) {
        makeRequest({repository.uploadFile(uri)}){ res->
            unwrap(res){
                downloadUriLiveData.value = it
            }
        }

    }

}