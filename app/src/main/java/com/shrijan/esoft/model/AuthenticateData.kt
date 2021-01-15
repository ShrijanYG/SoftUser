package com.shrijan.esoft.model

import android.os.Parcel
import android.os.Parcelable

class AuthenticateData (val un:String?,val pw:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(un)
        parcel.writeString(pw)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthenticateData> {
        override fun createFromParcel(parcel: Parcel): AuthenticateData {
            return AuthenticateData(parcel)
        }

        override fun newArray(size: Int): Array<AuthenticateData?> {
            return arrayOfNulls(size)
        }
    }
}