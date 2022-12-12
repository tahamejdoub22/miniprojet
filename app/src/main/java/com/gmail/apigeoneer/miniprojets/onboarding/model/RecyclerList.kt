package com.gmail.apigeoneer.miniprojets.onboarding.model

import com.google.gson.annotations.SerializedName

class RecyclerList(val data :ArrayList<RecyclerData>,val success:Boolean,val count:Int)
data class RecyclerData(val id: String,val materialImage: String,val materialName:String,val type: List<String>)
