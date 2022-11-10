package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class InnUlParser: IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        return emptyList()
    }

    fun getUlInnValue(innString: String):String{
        var controlSum = 0
        val filteredString = innString.filter { it.isDigit() }

        val indexValue = listOf(2,4,10,3,5,9,4,6,8,0)

        if (filteredString.length == 10) {
            for (index in indexValue.indices) {
                val digitFromString = filteredString[index].toString().toInt()
                val digitValue = indexValue[index] * digitFromString
                controlSum += digitValue
            }
        }

        var controlValue1 = controlSum % 11

        if (controlValue1 > 9) {
            controlValue1 = controlValue1 % 10
        }

        if (controlValue1 == filteredString[10].toString().toInt()
        ) {
            return filteredString
        } else
            return ""

    }
}