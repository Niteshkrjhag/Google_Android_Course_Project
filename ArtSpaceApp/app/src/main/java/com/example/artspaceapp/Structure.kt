package com.example.artspaceapp

data class Structure(
    val image:Int,
    val title: String,
    val author:String,
    val year: Int
)
object Temp{
    val a = Structure(R.drawable.img1,"4K Wallpaper","Nitesh",2022)
    val b = Structure(R.drawable.img2,"4K Wallpaper","Sumit",2021)
    val c = Structure(R.drawable.img3,"4K Wallpaper","Preeti",2020)
    val d = Structure(R.drawable.img4,"4K Wallpaper","Nehal",2019)
    val e = Structure(R.drawable.img5,"4K Wallpaper","Sourav",2018)
}