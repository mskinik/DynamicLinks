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
        Log.d("TAG", "onCreate: girdi")
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
                        /*
                        Log.d(TAG, "subscribeDynamicLinks: 1=${deepLink?.authority}  2=${deepLink?.encodedAuthority}" +
                                "3=${deepLink?.lastPathSegment} 4=${deepLink?.encodedFragment} 5=${deepLink?.userInfo} 6=${deepLink?.fragment} 7=${deepLink?.port} " +
                                "7=${deepLink?.encodedAuthority}" +
                                "8=${deepLink?.path}  9=${deepLink?.scheme}  10=${deepLink?.schemeSpecificPart} 11=${deepLink?.encodedSchemeSpecificPart}" +
                                "12= ${deepLink?.pathSegments} 13= ${deepLink.toString()} 14= ${deepLink?.queryParameterNames} 15= ${deepLink?.query} 16=${deepLink?.queryParameterNames}" +
                                "17= ${deepLink?.getQueryParameter("promotionrate")}")

                         */
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