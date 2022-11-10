package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class StsParser: IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        return emptyList()
    }
}