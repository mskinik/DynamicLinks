package com.mustafasuleymankinik.dynamiclinks

import android.util.Log
import kotlin.random.Random

/**
 * Created by mustafasuleymankinik on 17.06.2021.
 */
class GeneralManager {
    var id:Int = 0
    companion object{
        var instance: GeneralManager? = null
            get() {
               if(field ==null){
                   field = GeneralManager()
           }
               return field
           }
    }
    fun createUserId(){
        id = Random.nextInt(1000,10000)
        Log.d("TAG", "createUserId: $id")
    }
    fun getUserId():Int{
        Log.d("TAG", "getUserId: $id")
        return id
    }
}