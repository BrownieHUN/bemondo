package com.brownie.rabaibemondo.data

import com.brownie.rabaibemondo.R
import com.brownie.rabaibemondo.model.Audio

object DataSource {
    val szignal = listOf(
        Audio("Nincs", null),
        Audio("MÁV", R.raw.mav_szignal),
        Audio("GySEV", R.raw.gysev_szignal),
        Audio("IC", R.raw.ic_szignal)
    )

    val vonatnem = listOf(
        Audio("EuroNight",R.raw.en_vonat)
    )
}