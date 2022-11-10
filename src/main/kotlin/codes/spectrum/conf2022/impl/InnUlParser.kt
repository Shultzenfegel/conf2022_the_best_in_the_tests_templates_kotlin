package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class InnUlParser: IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        return return listOf(
            ExtractedDocument(
                docType = DocType.INN_UL,
                value = getUlInnValue(input),
                isValidSetup = true,
                isValid = getUlInnValue(input).matches(DocType.GRZ.normaliseRegex),
            )
        )
    }

    fun getUlInnValue(innString: String):String{
        var controlSum = 0
        val filteredString = innString.filter { it.isDigit() }

        val isValid = RegionValidator.isValid(filteredString.take(2))
        if (!isValid){
            return ""
        }

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