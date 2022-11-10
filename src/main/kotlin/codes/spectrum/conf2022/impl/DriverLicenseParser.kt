package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class DriverLicenseParser : IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        val prepared = input.filter { it.isDigit() }
        return if (prepared.matches(DocType.DRIVER_LICENSE.normaliseRegex)) {
            val isValid = RegionValidator.isValid(prepared.take(2))
            listOf(
                ExtractedDocument(
                    docType = DocType.DRIVER_LICENSE,
                    value = prepared,
                    isValidSetup = true,
                    isValid = isValid,
                )
            )
        } else emptyList()
    }

    private fun isRegionValid(region: String): Boolean = region != "00"
}