package com.mustafasuleymankinik.dynamiclinks

/**
 * Created by mustafasuleymankinik on 17.06.2021.
 */
class DynamicLinksManager {
    var discountRate:Int=0
    companion object {
        var instance:DynamicLinksManager?=null
            get() {
                if(field==null){
                    instance = DynamicLinksManager()
                }
                return field
            }
    }
    fun saveData(rate:Int){
        discountRate=rate

    }
    fun getData():Int{
        return discountRate
    }
}