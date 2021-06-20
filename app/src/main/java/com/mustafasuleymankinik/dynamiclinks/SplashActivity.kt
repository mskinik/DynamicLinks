package com.mustafasuleymankinik.dynamiclinks

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GeneralManager.instance?.createUserId()
        subscribeDynamicLinks()
        val handler = Handler(Looper.getMainLooper()).postDelayed({ subscribeDynamicLinks() }, 3000)

    }
    fun subscribeDynamicLinks(
    ){
        Firebase.dynamicLinks
                .getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link
                        deepLink?.getQueryParameter("promotionrate")?.toInt()?.let { DynamicLinksManager.instance?.saveData(it) }
                        when(deepLink?.lastPathSegment){
                            Page.CAMPAIGN_ACT.value -> startActivity(Intent(this@SplashActivity, CampaignActivity::class.java))
                            else -> startActivity(Intent(this@SplashActivity,ErrorActivity::class.java))
                        }

                    }
                }
                .addOnFailureListener(this) { e -> Log.w(TAG, "getDynamicLink:onFailure", e) }
    }
}