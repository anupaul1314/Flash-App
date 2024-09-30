package com.example.flash.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flash.data.InternetItems
import com.example.flash.network.FlashApi
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

class FlashViewModal: ViewModel() {

    private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "cart")

    lateinit var internetJob:Job
    lateinit var screenJob:Job

    sealed interface ItemUiState {
        data class Success(val items: List<InternetItems>): ItemUiState
        object Loading: ItemUiState
        object Error: ItemUiState
    }

    private val _user = MutableStateFlow<FirebaseUser?> (null)
    val user: MutableStateFlow<FirebaseUser?> get() = _user
    fun setUser(user: FirebaseUser) {
        _user.value = user
    }


    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: MutableStateFlow<String> get() = _phoneNumber
    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    private val _otp = MutableStateFlow("")
    val otp: MutableStateFlow<String> get() = _otp
    fun setOtp(otp:String) {
        _otp.value = otp
    }

    private val _verificationId = MutableStateFlow("")
    val verificationId: MutableStateFlow<String> get() = _verificationId
    fun setVerifiactionId(verificationId: String) {
        _verificationId.value = verificationId
    }

    private val _cartItems = MutableStateFlow<List<InternetItems>>(emptyList())
    val cartItems: StateFlow<List<InternetItems>> get() = _cartItems.asStateFlow()

    fun addToCart(items: InternetItems) {
        _cartItems.value = _cartItems.value + items
    }
    fun removeFromCart(items: InternetItems) {
        _cartItems.value = _cartItems.value - items
    }

    private val _uistate = MutableStateFlow(FlashUiState())
    val uiState:StateFlow<FlashUiState> = _uistate.asStateFlow()
    fun updateClickText(updatedText:String){
        _uistate.update {
            it.copy(
                clickStatus = updatedText
            )
        }
    }

    fun updateSelectedCategory(updatedCategory: Int){
        _uistate.update {
            it.copy(
                selectedCategory = updatedCategory
            )
        }
    }

    val _isVisible = MutableStateFlow<Boolean>(true)
    val isVisible = _isVisible

    fun toggleVisibility() {
        _isVisible.value = false
    }

    var itemUiState:ItemUiState by mutableStateOf(ItemUiState.Loading)
        private set

    fun getFlashItems() {
        internetJob = viewModelScope.launch {
            try {
                val listResult = FlashApi.retrofitService.getItems()
                itemUiState = ItemUiState.Success(listResult)
            }
            catch (exception:Exception) {
                itemUiState = ItemUiState.Error
                toggleVisibility()
                screenJob.cancel()
            }
        }
    }
    init {
        screenJob = viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            toggleVisibility()
        }
        getFlashItems()
    }

}

