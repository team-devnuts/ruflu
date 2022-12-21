package com.devnuts.ruflu.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import com.kakao.util.helper.Utility.getPackageInfo
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashKey {

    fun getHashKey(context: Context): String? {

        var packageInfo: PackageInfo? = null
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                packageInfo = getPackageInfo(context, PackageManager.GET_SIGNING_CERTIFICATES)
                val signatures = packageInfo.signingInfo.apkContentsSigners
                val md = MessageDigest.getInstance("SHA")
                for (signature in signatures) {
                    md.update(signature.toByteArray())
                    Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
                    return String(Base64.encode(md.digest(), NO_WRAP))
                }
            } else {
                val packageInfo =
                    getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

                for (signature in packageInfo!!.signatures) {
                    try {
                        var md: MessageDigest = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
                    } catch (e: NoSuchAlgorithmException) {
                        Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}
