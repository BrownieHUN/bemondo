package com.brownie.rabaibemondo.model

import androidx.annotation.RawRes

data class Audio(
    val name: String,
    @RawRes val file: Int?
)