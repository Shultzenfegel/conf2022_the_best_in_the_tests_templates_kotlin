package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class OgrnParser: IDocParser {

    override fun parse(input: String): List<ExtractedDocument> {
        val onlyDigits = input.filter {it.isDigit() }
        return if (onlyDigits.matches(DocType.OGRN.normaliseRegex)) {
            listOf(
                ExtractedDocument(
                    docType = DocType.OGRN,
                    value = onlyDigits,
                    isValidSetup = true,
                    isValid = isValid(onlyDigits)
                )
            )
        } else emptyList()
    }

    private fun isValid(input: String): Boolean {
        return input.substring(0 until 12).toLong() % 11 % 10 == input[12].digitToInt().toLong()
    }
}
