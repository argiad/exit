package com.steegler.exit.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steegler.exit.data.entity.RateEntity
import com.steegler.exit.domain.BusinessLogic
import com.steegler.exit.domain.repository.RatesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RateListViewModel @Inject constructor(
    private var businessLogic: BusinessLogic,
    private var repo: RatesRepo
) : ViewModel() {


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    val rates: LiveData<List<RateEntity>>
        get() {
            return repo.getAllItemsStream()
        }

    private val _groupRatesFlow = MutableStateFlow<Map<Char, List<RateEntity>>>(emptyMap())
    val groupedRates
        get() = _groupRatesFlow.asStateFlow()

    fun requestData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.emit(true)
            businessLogic.updateRates()
            delay(500)
            _isRefreshing.emit(false)

        }
    }

    init {
        rates.observeForever { rates ->
            val data = rates.reversed().groupBy { it.symbol.first() }.toSortedMap()
            viewModelScope.launch(Dispatchers.IO) { _groupRatesFlow.emit(data) }
        }
    }

}