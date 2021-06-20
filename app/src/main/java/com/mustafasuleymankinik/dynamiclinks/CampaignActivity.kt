package com.mustafasuleymankinik.dynamiclinks

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class CampaignActivity : AppCompatActivity() {
    private lateinit var discount:TextView
    private lateinit var header:TextView
    private lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign)
        discount = findViewById(R.id.tv_discount_rate)
        header = findViewById(R.id.tv_campaign_page_header)
        imageView = findViewById(R.id.iv_campaign_icon)
        header.text = String.format(resources.getString(R.string.welcome),GeneralManager.instance?.getUserId())
        DynamicLinksManager.instance?.getData()?.let {
            discount.text = String.format(resources.getString(R.string.discount),it)
            if(it!=0){
                imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.discount))
            }
        }




    }
}