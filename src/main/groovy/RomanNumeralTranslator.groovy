class RomanNumeralTranslator {
	final ONES_SUM_INDEX = 0
	final TENS_SUM_INDEX = 1
	final HUNDREDS_SUM_INDEX = 2
	final THOUSANDS_SUM_INDEX = 3

	final REPEATABLE_DIGITS =       ["I", "X", "C", "M"]
	final REPEATABLE_DIGIT_VALUES = [  1,  10, 100, 1000]
	final REPEATABLE_DIGIT_SUBTRACTOR_INDEX = [-1, ONES_SUM_INDEX, TENS_SUM_INDEX, HUNDREDS_SUM_INDEX]

	final NON_REPEATABLE_DIGITS =       ["V", "L", "D"]
	final NON_REPEATABLE_DIGIT_VALUES = [  5,  50, 500]
	final NON_REPEATABLE_DIGIT_SUBTRACTOR_INDEX = [ONES_SUM_INDEX, TENS_SUM_INDEX, HUNDREDS_SUM_INDEX]

	int translate(String numeralString) {
		total = 0
        	digitSums = [0, 0, 0, 0]
		numeralString.toUpperCase().each() { digit-> 
			if (!processRepeatableDigit(digit)) {
				processNonRepeatableDigit(digit)
			}
		}
		digitSums.each() { sum-> total += sum }
		total
	}
	
	void zeroAllRepeatableDigitSums()
	{
	}

	boolean  processRepeatableDigit(String digit) {

		def digitIndex = REPEATABLE_DIGITS.indexOf(digit)
		if (digitIndex > -1) {
			handleSubtractions(REPEATABLE_DIGIT_SUBTRACTOR_INDEX[digitIndex])
			digitSums[digitIndex] += REPEATABLE_DIGIT_VALUES[digitIndex]
			if (tooManyDuplicatedDigits(digitSums[digitIndex],
			    REPEATABLE_DIGIT_VALUES[digitIndex])) {
				digitSums[digitIndex] = REPEATABLE_DIGIT_VALUES[digitIndex] * 3
			}
		}
		return digitIndex > -1
	}

	boolean tooManyDuplicatedDigits(int digitSum, int digitValue) {
		digitSum > digitValue * 3
	}

	void handleSubtractions(int indexOfSubtractionSum) {
		if (indexOfSubtractionSum > -1) 
		{
			if (moreThanOneSubtractionOnDigit(digitSums[indexOfSubtractionSum],
					REPEATABLE_DIGIT_VALUES[indexOfSubtractionSum])) {
				digitSums[indexOfSubtractionSum] = 
						REPEATABLE_DIGIT_VALUES[indexOfSubtractionSum]
			}
			total -= digitSums[indexOfSubtractionSum]
			digitSums[indexOfSubtractionSum] = 0
		}
	}

	boolean moreThanOneSubtractionOnDigit(digitSum, digitValue) {
		digitSum > digitValue
	}

	void processNonRepeatableDigit(String digit) {
		def digitIndex = NON_REPEATABLE_DIGITS.indexOf(digit)
		if (digitIndex > -1) {
			handleSubtractions(NON_REPEATABLE_DIGIT_SUBTRACTOR_INDEX[digitIndex])
			total += NON_REPEATABLE_DIGIT_VALUES[digitIndex]
		}
	}

	def total
        def digitSums
}
