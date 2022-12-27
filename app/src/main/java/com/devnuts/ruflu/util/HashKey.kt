package com.devnuts.ruflu.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Base64.NO_WRAP
import com.kakao.util.helper.Utility.getPackageInfo
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import timber.log.Timber

class HashKey {

    fun getHashKey(context: Context): String? {
        val packageInfo: PackageInfo

        try {
            if (Build.VERSION.SDK_INT >= 28) {
                packageInfo = getPackageInfo(context, PackageManager.GET_SIGNING_CERTIFICATES)
                val signatures = packageInfo.signingInfo.apkContentsSigners
                val md = MessageDigest.getInstance("SHA")

                for (signature in signatures) {
                    md.update(signature.toByteArray())
                    Timber.tag("KeyHash").d(Base64.encodeToString(md.digest(), Base64.DEFAULT))
                    return String(Base64.encode(md.digest(), NO_WRAP))
                }
            } else {
                packageInfo =
                    getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

                for (signature in packageInfo.signatures) {
                    try {
                        val md: MessageDigest = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        Timber.tag("KeyHash")
                            .d(Base64.encodeToString(md.digest(), Base64.DEFAULT))
                    } catch (e: NoSuchAlgorithmException) {
                        Timber.tag("KeyHash")
                            .e(e, "Unable to get MessageDigest. signature=$signature")
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}
