package com.example.busschedule.ui

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication

//object AppViewModelProvider {
//
//    val Factory =
//        viewModelFactory {
//            initializer {
//                BusScheduleViewModel(
//                    savedStateHandle = this.createSavedStateHandle(),
//                    itemsRepository = BusScheduleApplication().container.itemsRepository
//                )
//            }
//        }
//
//
//}