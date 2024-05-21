package com.oimg.paseaasturias.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oimg.paseaasturias.data.Utils
import com.oimg.paseaasturias.domain.model.SelectionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectionDetailViewModel @Inject constructor(): ViewModel() {

    lateinit var selection: SelectionModel

}