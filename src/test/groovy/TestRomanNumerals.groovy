import groovy.util.GroovyTestCase

class TestRomanNumerals extends GroovyTestCase
{	
	
	def RomanNumeralTranslator trans
	
        void setUp() {	
	trans = new RomanNumeralTranslator()
	}

	void testNumeralIadditions() {
        assert trans.translate("I") == 1 
        assert trans.translate("II") == 2 
        assert trans.translate("III") == 3 
	assert trans.translate("VI") == 6	
	assert trans.translate("VII") == 7
	assert trans.translate("VIII") == 8
	assert trans.translate("XI") == 11 
	assert trans.translate("XIII") == 13 
	assert trans.translate("XVIII") == 18 
	}

	void testNumeralV() {
        assert trans.translate("V") == 5
	}
 
	void testNumeralISubtraction() {
	assert trans.translate("IV") == 4	
	assert trans.translate("IX") == 9
	assert trans.translate("XIV") == 14
	assert trans.translate("XIX") == 19
	}

	void testNumeralXAddition() {
        assert trans.translate("X") == 10 
        assert trans.translate("XX") == 20 
	}
	
	void testNumeralXSubtraction() {
        assert trans.translate("XL") == 40 
        assert trans.translate("XC") == 90 
	}

	void testNumeralL() {
        assert trans.translate("L") == 50 
	}

	void testNumeralC() {
        assert trans.translate("C") == 100 
	}

	void testNumeralCSubtraction() {
        assert trans.translate("CD") == 400 
        assert trans.translate("CM") == 900 
	}

	void testNumeralD() {
        assert trans.translate("D") == 500 
	}

	void testNumeralM() {
        assert trans.translate("M") == 1000 
	}

	void testLowerCaseNumeral() {
	assert trans.translate("x") == 10
	assert trans.translate("iv") == 4
	}

	void testComplexRomanNumeral() {
		assert trans.translate("CMXCVIII") == 998
		assert trans.translate("MDCCXII") ==1712 
	}

	void testGivenTooManyDuplicatableDigitsThenTheyAreIgnored() {
		assert trans.translate("IIII") == 3
	}

	void testGivenMoreThanOneSubtractionPerNumeralThenTheyAreIgnored()
	{
		assert trans.translate("XXC") == 90
	}

	void testGivenInvalidCharactersThenTheyAreIgnored()
	{
		assert trans.translate("QM8D!C#C]X~InI") ==1712 
	}
}
