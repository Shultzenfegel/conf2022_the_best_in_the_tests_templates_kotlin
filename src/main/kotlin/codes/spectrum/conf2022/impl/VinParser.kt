package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class VinParser: IDocParser {

    val northAmericaVin = (
            "(?<wmi>[A-HJ-NPR-Z\\d]{3})" +
                    "(?<vds>[A-HJ-NPR-Z\\d]{5})" +
                    "(?<check>[\\dX])" +
                    "(?<vis>(?<year>[A-HJ-NPR-Z\\d])" +
                    "(?<plant>[A-HJ-NPR-Z\\d])" +
                    "(?<seq>[A-HJ-NPR-Z\\d]{6}))"
            ).toRegex()

    val allOtherVins = (
            "(?<wmi>[A-HJ-NPR-Z\\d]{3})" +
                    "(?<vds>[A-HJ-NPR-Z\\d]{5})" +
                    "(?<check>[A-HJ-NPR-Z\\d])" +
                    "(?<vis>(?<year>[A-HJ-NPR-Z\\d])" +
                    "(?<plant>[A-HJ-NPR-Z\\d])" +
                    "(?<seq>[A-HJ-NPR-Z\\d]{6}))"
            ).toRegex()

    override fun parse(input: String): List<ExtractedDocument> {
        var isValidSetup: Boolean = false
        var isValid: Boolean = false

        var normalizedString: String = input.trim().uppercase()
        normalizedString.split("\\s".toRegex()).forEach {
            var matches = it.matches(DocType.VIN.normaliseRegex) && ((it.matches(northAmericaVin)) && (checkSum(it)) || (it.matches(allOtherVins)))
            if (matches)
                return listOf(ExtractedDocument(
                    docType = DocType.VIN,
                    value = it,
                    isValidSetup = isValidSetup,
                    isValid = isValid
                ))
        }
        return emptyList<ExtractedDocument>()
    }

    fun checkSum(str: String): Boolean {
        if (str.trim().length != 17) return false

        var sum: Int = 0
        for (i in str.indices) {
            val d = str[i].digitToIntOrNull()
            if (d != null) {
                sum += d * weights.getOrDefault(i + 1, 1)
            } else {
                val d = letterValues.get(str[i])
                if (d != null) {
                    sum += d * weights.getOrDefault(i + 1, 1)
                }
            }
        }
        val rem = sum % 11
        var checkDigit: String
        if (rem == 10) {
            checkDigit = "X"
        } else {
            checkDigit = rem.toString()
        }
        return str[8].toString() == checkDigit
    }

    companion object {
        private val letterValues = mapOf(
            'A' to 1,
            'B' to 2,
            'C' to 3,
            'D' to 4,
            'E' to 5,
            'F' to 6,
            'G' to 7,
            'H' to 8,

            'J' to 1,
            'K' to 2,
            'L' to 3,
            'M' to 4,
            'N' to 5,
            'P' to 7,
            'R' to 9,

            'S' to 2,
            'T' to 3,
            'U' to 4,
            'V' to 5,
            'W' to 6,
            'X' to 7,
            'Y' to 8,
            "Z" to 9
        )

        private val weights = mapOf(
            1 to 8,
            2 to 7,
            3 to 6,
            4 to 5,
            5 to 4,
            6 to 3,
            7 to 2,
            8 to 10,
            9 to 0,
            10 to 9,
            11 to 8,
            12 to 7,
            13 to 6,
            14 to 5,
            15 to 4,
            16 to 3,
            17 to 2
        )

    }

}

