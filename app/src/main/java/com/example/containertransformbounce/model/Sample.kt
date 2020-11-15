package com.example.containertransformbounce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sample(val id: Int, val title: String, val description: String) : Parcelable