package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class GrzParser: IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        val prepared = input.replace("""[^\dA-ZА-ЯЁ]""".toRegex(option = RegexOption.IGNORE_CASE), "")
        val find = """(\D\d{3}\D{2}\d{2,3})""".toRegex().find(prepared)?.groupValues?.get(1)
        return if(find != null) {
            val normal = find.uppercase().map { replaces[it] ?: it }.joinToString(separator = "")
            listOf(
                ExtractedDocument(
                    docType = DocType.GRZ,
                    value = normal,
                    isValidSetup = true,
                    isValid = normal.matches(DocType.GRZ.normaliseRegex) && !normal.matches("""^\D000.*|.*\D0{2,3}$""".toRegex()),
                )
            )
        } else {
            emptyList()
        }
    }

    companion object {
        private val replaces = mapOf(
            'A' to 'А',
            'B' to 'В',
            'E' to 'Е',
            'K' to 'К',
            'M' to 'М',
            'H' to 'Н',
            'O' to 'О',
            'P' to 'Р',
            'C' to 'С',
            'T' to 'Т',
            'Y' to 'У',
            'X' to 'Х',
        )
    }
}