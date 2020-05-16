package com.example.transition

import android.content.Context

fun getHoloLiveMember(context: Context): List<Item> {

    val hololive = context.assets.open("HoloLiveMember.csv").bufferedReader().useLines {
        it.toList()
    }

    return hololive.map { line ->
        val (name, url) = line.split(",")
        Item(name, url)
    }
}