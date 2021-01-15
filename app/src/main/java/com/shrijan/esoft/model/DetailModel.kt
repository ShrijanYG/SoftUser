package com.shrijan.esoft.model

import android.os.Parcel
import android.os.Parcelable

data class DetailModel(var name:String?,var age:Int?,var address:String?,var gender:String?,var img:String?):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(age)
        parcel.writeString(address)
        parcel.writeString(gender)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailModel> {
        override fun createFromParcel(parcel: Parcel): DetailModel {
            return DetailModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailModel?> {
            return arrayOfNulls(size)
        }
    }
}