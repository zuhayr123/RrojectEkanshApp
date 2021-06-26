package com.laaltentech.abou.fitnessapp.bottomnav.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QuizQuestionData")
class QuizQuestionData {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var questionObservation: String = "No Data"

    var optionVata: String = "No Option"

    var optionPitta: String = "No Option"

    var optionKapha: String = "No Option"

    var selectedOption: String = "0"
}
