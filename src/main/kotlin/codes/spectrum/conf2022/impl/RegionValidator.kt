package codes.spectrum.conf2022.impl

object RegionValidator {
    fun isValid(region: String): Boolean {
        require(region.length == 2 && region.all { it.isDigit() })

        return region !in invalid
    }

    private val invalid = listOf("00", "90", "91", "93", "94", "96", "97", "98", "99")
}