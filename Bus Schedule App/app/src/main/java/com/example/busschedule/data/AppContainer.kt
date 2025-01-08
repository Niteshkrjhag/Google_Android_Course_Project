package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val itemsRepository:itemsRepository
}


//class AppDataContainer(private val context:Context):AppContainer{
//    override val itemsRepository: itemsRepository by lazy{
//        OfflineItemsRepository(BusScheduleDatabase.getDatabase(context).itemDao())
//    }
//}