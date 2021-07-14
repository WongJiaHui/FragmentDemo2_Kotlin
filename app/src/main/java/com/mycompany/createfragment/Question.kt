package com.mycompany.createfragment

lateinit var currentQuestion: Question
lateinit var answers: MutableList<String>

var questionIndex = 0
var score: Int = 0

data class Question(
    val text :String,
    val answers: List<String>

)
