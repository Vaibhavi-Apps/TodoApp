package com.vppanchalofficial.reminder

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Singolton {
    companion object {
        fun callSnackBar(view: View,message:String) {
            val snackbar = Snackbar.make(view,message,Snackbar.LENGTH_LONG).show()
        }
    }
}
/*
open class Car {
     open fun cartype(){
        println("Print Car Type")
    }
}
class mycar : Car(){
    var type = "SportsCar"
    override fun cartype(){
        println("Print Car Type = $type")
    }
}
fun main(){
    val car = mycar()
    car.cartype()
}*/