package codes.spectrum.conf2022.impl

import codes.spectrum.conf2022.doc_type.DocType
import codes.spectrum.conf2022.input.IDocParser
import codes.spectrum.conf2022.output.ExtractedDocument

class InnFlParser : IDocParser {
    override fun parse(input: String): List<ExtractedDocument> {
        val filteredString = input.filter { it.isDigit() }

        val isValid = RegionValidator.isValid(filteredString.take(2))
        if (filteredString.length == 12 && isValid) {
                return getPlInnValue(input)
            }  else if (filteredString.length == 10 && !isValid) {
            return listOf(
                ExtractedDocument(
                    docType = DocType.INN_UL,
                    value = filteredString,
                    isValidSetup = true,
                    isValid = false
                )
            )
        } else return emptyList()
    }

    fun getPlInnValue(innString: String): List<ExtractedDocument> {
        var resultString = ""

        var controlSum = 0
        var controlSum2 = 0
        val filteredString = innString.filter { it.isDigit() }

        val isValid = RegionValidator.isValid(filteredString.take(2))
        if (!isValid) {
            return emptyList()
        }

        val indexValue = listOf(7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0)
        val indexValue2 = listOf(3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0)

        /**
         * Вычисляется контрольная сумма по первым 11-ти знакам со следующими весовыми коэффициентами:
         */

        if (filteredString.length == 12) {
            for (index in indexValue.indices) {
                val digitFromString = filteredString[index].toString().toInt()
                val digitValue = indexValue[index] * digitFromString
                controlSum += digitValue
            }

            /**
             * Вычисляется контрольное число_1 как остаток от деления контрольной суммы на 11.
             */
            var controlValue1 = controlSum % 11

            /**
             * Если контрольное число_1 больше 9, то контрольное число_1 вычисляется как остаток от деления контрольного числа_1 на 10.
             */

            if (controlValue1 > 9) {
                controlValue1 = controlValue1 % 10
            }

            /**
             * Вычисляется контрольная сумма по 12-ти знакам со следующими весовыми коэффициентами:
             */

            for (index in indexValue2.indices) {
                val digitFromString = filteredString[index].toString().toInt()
                val digitValue = indexValue2[index] * digitFromString
                controlSum2 += digitValue
            }

            /**
             * Вычисляется контрольное число_2 как остаток от деления контрольной суммы на 11.
             */
            var controlValue2 = controlSum2 % 11

            /**
             *  Если контрольное число_2 больше 9, то контрольное число_2 вычисляется как остаток от деления контрольного числа_2
             */
            if (controlValue2 > 9) {
                controlValue2 = controlValue2 % 10
            }

            if (controlValue1 == filteredString[10].toString().toInt() &&
                controlValue2 == filteredString[11].toString().toInt()
            ) {
                return listOf(
                    ExtractedDocument(
                        docType = DocType.INN_FL,
                        value = filteredString,
                        isValidSetup = true,
                        isValid = true,
                    )
                )
            } else
                return listOf(
                    ExtractedDocument(
                        docType = DocType.INN_FL,
                        value = filteredString,
                        isValidSetup = true,
                        isValid = false,
                    )
                )
        }
        return emptyList()
    }

}