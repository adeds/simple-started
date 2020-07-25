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
    val results: MutableLiveData<BaseModel<User>> = MutableLiveData()

    fun call(param: String) {
        results.postValue(BaseModel(Status.LOADING))
        viewModelScope.launch {
            repo.getDataFromNetwork(param, object : ApiCallback<User> {
                override fun onSuccess(data: User) {
                    results.postValue(BaseModel(Status.SUCCESS, data))
                }

                override fun onFailed(e: Throwable) {
                    results.postValue(BaseModel(Status.FAILED, null, e.message))
                }

            })
        }
    }
}