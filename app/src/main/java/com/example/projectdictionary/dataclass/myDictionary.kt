package com.example.projectdictionary.dataclass

import io.realm.RealmObject

open class myDictionary (
    var word: String = "",
    var meaning: String =""
): RealmObject()