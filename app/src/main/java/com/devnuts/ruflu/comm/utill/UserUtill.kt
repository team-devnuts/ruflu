package com.devnuts.ruflu.comm.utill

import android.graphics.BitmapFactory
import android.location.Location
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.squareup.picasso.Picasso
import java.io.BufferedInputStream
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserUtill {

    companion object {

        fun getAge(birth: String): Int {
            var birthYear = TextUtils.substring(birth, 0, 4)
            var birthMMdd = TextUtils.substring(birth, 5, 8)

            var todayStr =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                } else {
                    // 추후에 sdk 26 이전 버전 처리
                    ""
                }

            var todayYear = TextUtils.substring(todayStr, 0, 4)
            var todayMMdd = TextUtils.substring(todayStr, 5, 8)

            var age = todayYear.toInt() - birthYear.toInt()

            return if (todayMMdd > birthMMdd) age - 1 else age
        }

        fun getDistanceToUser(
            mLatitude: Double,
            mLongitude: Double,
            yLatitude: Double,
            yLongitude: Double
        ): Float {
            val myLocation = Location("myLocation")
            val othLocation = Location("userLocation")

            myLocation.latitude = mLatitude
            myLocation.longitude = mLongitude
            othLocation.latitude = yLatitude
            othLocation.longitude = yLongitude

            val distance = myLocation.distanceTo(othLocation)
            Log.d("getDistanceToUser", "Distance : $distance")
            return distance
        }

        fun setImageBitmap(url: String, imgView: ImageView) {
            val handlerThread = HandlerThread("imgbackground")
            handlerThread.start()

            Handler(handlerThread.looper).post {

                try {
                    val url = URL(url)
                    val conn = url.openConnection()
                    conn.connect()
                    val bis = BufferedInputStream(conn.getInputStream())

                    val bm = BitmapFactory.decodeStream(bis)
                    Handler(Looper.getMainLooper()).post { imgView.setImageBitmap(bm) }

                    bis.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun setImageWithGlide(view: View, url: String, imageView: ImageView) {
            Glide.with(view)
                .load(url)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .signature(ObjectKey(System.currentTimeMillis()))
                )
                .into(imageView)
        }

        fun setImageWithPiccaso(view: View, url: String, imageView: ImageView) {
            Picasso.get()
                .load(url)
                .into(imageView)
        }
    }
}
