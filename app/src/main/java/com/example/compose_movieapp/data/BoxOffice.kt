package com.example.compose_movieapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BoxOffice(
    @PrimaryKey val movieNm: String,
    val rnum: String,
    val rank: String,
    val rankInten: String,
    val rankOldAndNew: String,
    val movieCd: String,
    val openDt: String,
    val salesAmt: String,
    val salesShare: String,
    val salesInten: String,
    val salesChange: String,
    val salesAcc: String,
    val audiCnt: String,
    val audiInten: String,
    val audiChange: String,
    val audiAcc: String,
    val scrnCnt: String,
    val showCnt: String
)
