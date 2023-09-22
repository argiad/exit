package com.steegler.exit.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steegler.exit.data.entity.RateEntity
import com.steegler.exit.domain.BusinessLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateDetailsViewModel @Inject constructor(
    private var businessLogic: BusinessLogic,
) : ViewModel() {

    val rate: MutableLiveData<RateEntity> = MutableLiveData()

    fun fetchRate(rateID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = businessLogic.getRateBy(rateID)
            viewModelScope.launch (Dispatchers.Main) {
                rate.value = item
            }

        }

        viewModelScope.launch(Dispatchers.IO) {
            businessLogic.getUpdatesFor(rateID)
        }
    }
}