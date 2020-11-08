package apachecommons.lang;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void test_emptyBlank()
    {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("    "));

        assertTrue(StringUtils.isAllBlank(null, "", "    "));
        assertFalse(StringUtils.isAllBlank(null, "", "    ", "A"));

        assertTrue(StringUtils.isAnyBlank(null, "", "    "));
        assertFalse(StringUtils.isAnyBlank("A", "B", "    C"));

        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("   "));

        assertTrue(StringUtils.isAllEmpty(null, ""));
        assertFalse(StringUtils.isAllEmpty(null, "", " "));

        assertTrue(StringUtils.isAnyEmpty(null, ""));
        assertTrue(StringUtils.isAnyEmpty(null, "", " "));
        assertFalse(StringUtils.isAnyEmpty("A", "B", " C"));

        assertTrue(StringUtils.isWhitespace(""));
        //Space
        assertTrue(StringUtils.isWhitespace(" "));
        //Tab
        assertTrue(StringUtils.isWhitespace("   "));
        assertFalse(StringUtils.isWhitespace("a"));
    }

    @Test
    public void test_lowerUpperCases()
    {
        assertTrue(StringUtils.isAllLowerCase("asbc"));
        assertFalse(StringUtils.isAllLowerCase("asbc "));
        assertFalse(StringUtils.isAllLowerCase("A"));

        assertTrue(StringUtils.isAllUpperCase("ABCD"));
        assertFalse(StringUtils.isAllUpperCase("ABC "));
        assertFalse(StringUtils.isAllUpperCase("a"));

        assertTrue(StringUtils.isMixedCase("ABCDabcd"));
        //as long as there are both upper and lower case, true
        assertTrue(StringUtils.isMixedCase("ABCDabcd  ?;''1234"));
        //Only upper/lower case, false
        assertFalse(StringUtils.isMixedCase("ABCD"));
        assertFalse(StringUtils.isMixedCase("abcd"));
        assertFalse(StringUtils.isMixedCase(""));
        assertFalse(StringUtils.isMixedCase(null));
        assertFalse(StringUtils.isMixedCase("123456"));
        assertFalse(StringUtils.isMixedCase("!@#$%^&"));

        assertEquals("ABCØ", StringUtils.upperCase("abcø"));
        assertEquals("ABC", StringUtils.upperCase("ABC"));
        assertEquals("ABC123", StringUtils.upperCase("abc123"));

        assertEquals("abcø", StringUtils.lowerCase("ABCØ"));
        assertEquals("abc", StringUtils.lowerCase("abc"));
        assertEquals("abc123", StringUtils.lowerCase("ABC123"));

        assertEquals("abcDEF", StringUtils.swapCase("ABCdef"));
        assertEquals("abcDEF  123!@#$?", StringUtils.swapCase("ABCdef  123!@#$?"));

        assertEquals("Abc", StringUtils.capitalize("abc"));
        assertEquals("ABC", StringUtils.capitalize("aBC"));
    }

    @Test
    public void test_index()
    {
        assertEquals(-1, StringUtils.indexOf(null, "a"));
        assertEquals(-1, StringUtils.indexOf("", "a"));
        assertEquals(0, StringUtils.indexOf("abc", ""));

        //Take the first index
        assertEquals(0, StringUtils.indexOf("abaca", "a"));
        assertEquals(2, StringUtils.indexOf("abaca", "ac"));

        assertEquals(2, StringUtils.indexOf("abaca", "a", 1));
        assertEquals(5, StringUtils.indexOf("abacaab", "ab", 1));

        assertEquals(1, StringUtils.indexOfIgnoreCase("abaca", "B"));

        assertEquals(0, StringUtils.indexOfAny("abc", "k", "a", "b"));
        assertEquals(1, StringUtils.indexOfAny("abc", "j", "k", "b"));

        assertEquals(4, StringUtils.lastIndexOf("abaca", "a"));
        assertEquals(4, StringUtils.lastIndexOfIgnoreCase("abaca", "A"));

        //Searches a CharSequence to find the first index of any character not in the given set of characters.
        assertEquals(1, StringUtils.indexOfAnyBut("abaca", "a"));
        assertEquals(3, StringUtils.indexOfAnyBut("abaca", "ab"));

        //Compares all CharSequences in an array and returns the index at which the CharSequences begin to differ.
        assertEquals(1, StringUtils.indexOfDifference("abaca", "a"));
        assertEquals(0, StringUtils.indexOfDifference("abaca", "ba"));
        assertEquals(3, StringUtils.indexOfDifference("abaca", "aba"));
    }

    @Test
    public void test_equalCompare()
    {
        assertTrue(StringUtils.equals(null, null));
        assertTrue(StringUtils.equals("", ""));
        assertTrue(StringUtils.equals("ab", "ab"));
        assertFalse(StringUtils.equals(null, "ab"));
        assertFalse(StringUtils.equals("ab", null));
        assertFalse(StringUtils.equals("aa", "ab"));

        assertTrue(StringUtils.equalsIgnoreCase("ab", "ab"));
        assertTrue(StringUtils.equalsIgnoreCase("ab", "Ab"));
        assertTrue(StringUtils.equalsIgnoreCase("ab", "AB"));

        assertTrue(StringUtils.equalsAny(null, null, ""));
        assertTrue(StringUtils.equalsAny("ab", "ab", "ac"));
        assertFalse(StringUtils.equalsAny("ab", "aa", "ac"));

        assertTrue(StringUtils.equalsAnyIgnoreCase("ab", "AB", "ac"));

        assertEquals(0, StringUtils.compare(null, null));
        assertEquals(0, StringUtils.compare("a", "a"));
        assertEquals(1, StringUtils.compare("a", null));
        assertEquals(-1, StringUtils.compare("a", "b"));
        assertEquals(1, StringUtils.compare("b", "a"));

        //null is greater/less
        assertEquals(0, StringUtils.compare("a", "a", false));
        assertEquals(1, StringUtils.compare("a", null, true));
        assertEquals(-1, StringUtils.compare("a", null, false));

        assertEquals(0, StringUtils.compareIgnoreCase("a", "A"));
        assertEquals(0, StringUtils.compareIgnoreCase("a", "a"));
    }

    @Test
    public void test_length()
    {
        assertEquals(0, StringUtils.length(null));
        assertEquals(0, StringUtils.length(""));
        assertEquals(1, StringUtils.length("1"));
    }

    @Test
    public void test_trim()
    {
        assertEquals(null, StringUtils.trim(null));
        assertEquals("", StringUtils.trim(""));
        assertEquals("", StringUtils.trim("  "));
        assertEquals("a", StringUtils.trim("a "));
        assertEquals("a", StringUtils.trim("  a"));
        assertEquals("a", StringUtils.trim(" a  "));
        assertEquals("a b", StringUtils.trim(" a b  "));

        assertEquals("", StringUtils.trimToEmpty(null));
        assertEquals("", StringUtils.trimToEmpty(""));

        assertEquals(null, StringUtils.trimToNull(null));
        assertEquals(null, StringUtils.trimToNull(""));
        assertEquals(null, StringUtils.trimToNull("   "));
        assertEquals("a", StringUtils.trimToNull("  a  "));
    }

    @Test
    public void test_pad()
    {
        assertEquals(null, StringUtils.leftPad(null, 3));
        assertEquals("   ", StringUtils.leftPad("", 3));
        assertEquals("  a", StringUtils.leftPad("a", 3));
        assertEquals(" aa", StringUtils.leftPad("aa", 3));
        assertEquals("aaa", StringUtils.leftPad("aaa", 3));

        assertEquals("!!!", StringUtils.leftPad("", 3, "!"));

        assertEquals(null, StringUtils.rightPad(null, 3));
        assertEquals("   ", StringUtils.rightPad("", 3));
        assertEquals("a  ", StringUtils.rightPad("a", 3));
        assertEquals("aa ", StringUtils.rightPad("aa", 3));
        assertEquals("aaa", StringUtils.rightPad("aaa", 3));

        assertEquals("!!!", StringUtils.rightPad("", 3, "!"));
    }

    @Test
    public void test_abbreviate()
    {
        //Abbreviates a String using ellipses.
        assertEquals("Hi, this is...", StringUtils.abbreviate("Hi, this is abc", 14));
    }

    @Test
    public void test_leftRightMidCenter()
    {
        assertEquals(null, StringUtils.left(null, 3));
        assertEquals("", StringUtils.left("abcdefg", -1));
        assertEquals("", StringUtils.left("abcdefg", 0));
        assertEquals("abc", StringUtils.left("abcdefg", 3));
        assertEquals("abcdefg", StringUtils.left("abcdefg", 7));
        assertEquals("abcdefg", StringUtils.left("abcdefg", 100));

        assertEquals(null, StringUtils.right(null, 3));
        assertEquals("", StringUtils.right("abcdefg", -1));
        assertEquals("", StringUtils.right("abcdefg", 0));
        assertEquals("efg", StringUtils.right("abcdefg", 3));
        assertEquals("abcdefg", StringUtils.right("abcdefg", 7));
        assertEquals("abcdefg", StringUtils.right("abcdefg", 100));

        assertEquals("bcd", StringUtils.mid("abcdefg", 1, 3));
        assertEquals("cde", StringUtils.mid("abcdefg", 2, 3));

        assertEquals(" ab ", StringUtils.center("ab", 4));
    }
}
