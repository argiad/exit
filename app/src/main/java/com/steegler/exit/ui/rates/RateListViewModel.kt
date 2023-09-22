package com.steegler.exit.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steegler.exit.data.entity.RateEntity
import com.steegler.exit.domain.BusinessLogic
import com.steegler.exit.domain.repository.RatesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RateListViewModel @Inject constructor(
    private var businessLogic: BusinessLogic,
    private var repo: RatesRepo
) : ViewModel() {


    val rates: LiveData<List<RateEntity>>
        get() {
            return repo.getAllItemsStream()
        }

    fun requestData() {
        viewModelScope.launch(Dispatchers.IO) {
            businessLogic.updateRates()
        }
    }

}