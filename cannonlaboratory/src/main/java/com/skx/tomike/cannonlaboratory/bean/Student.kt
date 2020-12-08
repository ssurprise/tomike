package com.skx.tomike.cannonlaboratory.bean

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
class Student() : Parcelable {
    var id: Int = 0
    var name: String? = ""

    var birth: String? = ""
    var sex: String? = ""

    var clazz: String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        birth = parcel.readString()
        sex = parcel.readString()
        clazz = parcel.readString()
    }

    companion object : Parceler<Student> {

        override fun Student.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeString(birth)
            parcel.writeString(sex)
            parcel.writeString(clazz)
        }

        override fun create(parcel: Parcel): Student {
            return Student(parcel)
        }
    }
}