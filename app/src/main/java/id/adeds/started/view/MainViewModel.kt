package id.adeds.started.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.adeds.started.data.model.BaseModel
import id.adeds.started.data.model.User
import id.adeds.started.data.repository.ApiCallback
import id.adeds.started.data.repository.MyRepository
import id.adeds.started.util.Status
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MyRepository) : ViewModel() {
    val results: MutableLiveData<BaseModel<List<User>>> = MutableLiveData()

    fun call() {
        results.postValue(BaseModel(Status.LOADING))
        viewModelScope.launch {
            repo.getDataFromNetwork(object : ApiCallback<List<User>> {
                override fun onSuccess(data: List<User>) {
                    results.postValue(BaseModel(Status.SUCCESS, data))
                }

                override fun onFailed(e: Throwable) {
                    results.postValue(BaseModel(Status.FAILED, null, e.message))
                }

            })
        }
    }
}