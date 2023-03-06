package com.example.keepfit.SingletonPersistance

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.charset.Charset

class CachePersistance(
    private val file: File
){

    fun read():CacheData{
        if(!file.exists()){
            this.write(CacheData())
        }
        return Json.decodeFromString<CacheData>(string = file.readText(Charset.forName("UTF-8")))
    }

    fun write(data:CacheData):Unit{
        val currentData:String = Json.encodeToString(value = data)
        file.writeText(currentData, Charset.forName("UTF-8"))
    }

}