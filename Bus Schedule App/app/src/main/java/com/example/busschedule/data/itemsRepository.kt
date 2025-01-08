package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface itemsRepository {

    fun getAllItemsStream():Flow<List<BusSchedule>>
    fun getItemStream(name:String):Flow<List<BusSchedule>?>
}