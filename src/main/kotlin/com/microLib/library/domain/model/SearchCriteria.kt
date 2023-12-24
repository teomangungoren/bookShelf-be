package com.microLib.library.domain.model


data class SearchCriteria(
    val key:String,
    val operation:String,
    val value:Any
)