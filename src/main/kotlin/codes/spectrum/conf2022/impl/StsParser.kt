package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class StsParser: IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        val prepared = input.replace("""[^\dA-ZА-ЯЁ]""".toRegex(option = RegexOption.IGNORE_CASE), "")
        val find = """(\d{2}[\dА-ЯA-Z]{2}\d{6})""".toRegex().find(prepared)?.groupValues?.get(1)
        return if(find != null) {
            listOf(
                ExtractedDocument(
                    docType = DocType.STS,
                    value = find,
                    isValidSetup = true,
                    isValid = find.matches(DocType.STS.normaliseRegex),
                )
            )
        } else {
            emptyList()
        }
    }
}