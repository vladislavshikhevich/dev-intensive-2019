package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.*

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME
) {

    fun askQuestion() : String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>> {
        return if (!question.validate(answer)) {
            "${question.errorMessage}\n${question.question}" to status.color
        }
        else if (question.answers.contains(answer.toLowerCase()) && question.validate(answer)) {
            question = question.nextQuestion()
            if(question == Question.IDLE)
                "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color
            else
                "Отлично - ты справился\n${question.question}" to status.color
        }
        else {
            if(question == Question.IDLE)
                return "На этом все, вопросов больше нет" to status.color
            val isNewCycle = status == Status.CRITICAL
            status = status.nextStatus()
            return if (isNewCycle) {
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex)
                values()[this.ordinal + 1]
            else
                values()[0]
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {

            override val errorMessage: String
                get() = "Имя должно начинаться с заглавной буквы"

            override fun nextQuestion(): Question = PROFESSION

            override fun validate(answer: String): Boolean {
                return answer.isStartsUpperCase()
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {

            override val errorMessage: String
                get() = "Профессия должна начинаться со строчной буквы"

            override fun nextQuestion(): Question = MATERIAL

            override fun validate(answer: String): Boolean {
                return answer.isStartsLowerCase()
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {

            override val errorMessage: String
                get() = "Материал не должен содержать цифр"

            override fun nextQuestion(): Question = BDAY

            override fun validate(answer: String): Boolean {
                return !answer.containsDigit()
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {

            override val errorMessage: String
                get() = "Год моего рождения должен содержать только цифры"

            override fun nextQuestion(): Question = SERIAL

            override fun validate(answer: String): Boolean {
                return answer.isOnlyDigits()
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {

            override val errorMessage: String
                get() = "Серийный номер содержит только цифры, и их 7"

            override fun nextQuestion(): Question = IDLE

            override fun validate(answer: String): Boolean {
                return answer.isSeriesValidate()
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {

            override val errorMessage: String
                get() = ""

            override fun nextQuestion(): Question = IDLE

            override fun validate(answer: String): Boolean {
                return true
            }
        };

        abstract val errorMessage: String

        abstract fun nextQuestion(): Question

        abstract fun validate(answer: String): Boolean
    }
}