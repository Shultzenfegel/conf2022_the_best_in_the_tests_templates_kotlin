package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class DriveLicenseParser : IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        val prepared = input.filter { it.isDigit() }
        return if (prepared.matches(DocType.DRIVER_LICENSE.normaliseRegex)) {
            listOf(
                ExtractedDocument(
                    docType = DocType.DRIVER_LICENSE,
                    value = prepared,
                )
            )
        } else emptyList()
    }
}