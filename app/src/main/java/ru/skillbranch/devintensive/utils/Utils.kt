package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName == "" || fullName == " ") {
            return Pair(null, null)
        } else {

            val parts: List<String>? = fullName?.split(" ")

            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)

            return Pair(firstName, lastName)
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            return null
        }

        val firstInitial = firstName?.get(0)?.toUpperCase()
        val secondInitial = lastName?.get(0)?.toUpperCase()

        return "${firstInitial ?: ""}${secondInitial ?: ""}"
    }

    fun transliteration(payload: String, divider: String = " ") : String {
        var result = ""

        payload.forEach {
            result += when(it) {
                ' ' -> divider
                else -> translateChar(it)
            }
        }
        return result
    }

    private fun translateChar(char: Char): String {
        return "${if (char.isUpperCase()) transliterationMap.get(char.toLowerCase())?.toUpperCase()?: char else transliterationMap.get(char)?: char}"
    }

    private val transliterationMap: Map<Char, String> = hashMapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )
}