package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class PassportParser : IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        val prepared = input.filter { it.isDigit() }
        return if (prepared.matches(DocType.PASSPORT_RF.normaliseRegex)) {
            val isValid = RegionValidator.isValid(prepared.take(2)) && isLevelValid(prepared.substring(2, 3))
            listOf(
                ExtractedDocument(
                    docType = DocType.PASSPORT_RF,
                    value = prepared,
                    isValidSetup = true,
                    isValid = isValid,
                )
            )
        } else emptyList()
    }

    private fun isLevelValid(level: String): Boolean = level.toInt() in 0..3
}