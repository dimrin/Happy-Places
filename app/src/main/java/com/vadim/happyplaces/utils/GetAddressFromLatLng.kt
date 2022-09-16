package com.vadim.happyplaces.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.io.IOException
import java.util.*

class GetAddressFromLatLng(
    context: Context,
    private val latitude: Double,
    private var longitude: Double
) {

    private val geocoder: Geocoder = Geocoder(context, Locale.getDefault())

    fun getAddress():String {
        try {
            val addressList: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)

            if (addressList != null && addressList.isNotEmpty()) {
                val address: Address = addressList[0]
                val sb = StringBuilder()
                for (i in 0..address.maxAddressLineIndex) {
                    sb.append(address.getAddressLine(i)).append(",")
                }
                sb.deleteCharAt(sb.length - 1)
                return sb.toString()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("HappyPlaces", "Unable connect to Geocoder")
        }

        return ""
    }
}