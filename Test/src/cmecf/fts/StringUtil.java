/* RPM Packages: ao-bkecf-web-* */
/* RPM Permissions: 444 */
/* RPM Owner: tomcat */
/* RPM Group: nobody */
/* RPM Flags: configure */

 package cmecf.fts;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 * Utilities to manipulate and handle strings
 */
public final class StringUtil
{

    // Class Variable(s)
    public static final String      DIGIT_CHARACTERS = "0123456789";
    public static final String       ALPHA_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String       ALPHA_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String    SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}\\|;':\",./<>?`~";
    public static final String WHITESPACE_CHARACTERS = "\t\n\f\r ";
    /**
     * Delimter characters include the space, tab, pipe, and comma.
     * These are characters commonly associated with delimiting data such as CSV.
     */
    public static final String DELIMITER_CHARACTERS  = " \t|,";


    // Class Constructor(s)
    private StringUtil()
    {

    }

    // Class Method(s)
    public static String escapeString(String value)
    {

        String sEscaped = "";

        if (value == null)
        {
            return (value);
        }

        int length = value.length();
        for (int i = 0; i < length; i++)
        {
            switch (value.charAt(i))
            {
                case ('\''):
                case ('"'):
                    sEscaped = sEscaped.concat("\\");
                    sEscaped = sEscaped.concat(value.substring(i, i+1));
                break;
                default:
                    sEscaped = sEscaped.concat(value.substring(i, i+1));
                break;
            }
        }

        return (sEscaped);

    }

    public static int[] findIntInString(String string_to_parse)
    {
        int[] nIntegers   = null;
        String sLocalCopy = null;

        if (string_to_parse != null)
        {
            sLocalCopy = string_to_parse.trim();
            if (sLocalCopy.length() > 0 && containsDigit(sLocalCopy))
            {
                ArrayList<Integer> intList = new ArrayList<Integer>();

                int charLength = sLocalCopy.length();
                for (int charIndex = 0; charIndex < charLength; charIndex++)
                {
                    if (!Character.isDigit(sLocalCopy.charAt(charIndex)))
                    {
                        continue;
                    }

                    int digit =
                        Character.digit(sLocalCopy.charAt(charIndex), 10);

                    intList.add(new Integer(digit));
                }

                int listSize = intList.size();
                nIntegers = new int[listSize];
                for (int intIndex = 0; intIndex < listSize; intIndex++)
                {
                    nIntegers[intIndex] = ((Integer) intList.get(intIndex)).intValue();
                }

                intList.clear();
                intList = null;
            }
            sLocalCopy = null;
        }

        return nIntegers;
    }

    public static int[] parseStringForInt(String string_to_parse, int number_of_ints)
    {

        int[]          nIntegers = null;
        int          nTokenIndex = -1;
        int          nTokenCount = -1;
        boolean        bContinue = false;
        String       sDelimiters = null;
        StringTokenizer stParser = null;

        if (string_to_parse != null)
        {
            bContinue = (containsDigit(string_to_parse.trim()) ? true : false);

            if (bContinue)
            {
                sDelimiters = ((new StringBuffer())
                            .append(ALPHA_LOWERCASE)
                            .append(ALPHA_UPPERCASE)
                            .append(SPECIAL_CHARACTERS)
                            .append(WHITESPACE_CHARACTERS))
                            .toString();

                stParser = new StringTokenizer(string_to_parse.trim(), sDelimiters);
                nIntegers = new int[stParser.countTokens()];

                while (stParser.hasMoreTokens())
                {
                    nIntegers[++nTokenIndex] = Integer.parseInt(stParser.nextToken());
                }

                nTokenIndex = -1;
                nTokenCount = nIntegers.length;
                if (nTokenCount != number_of_ints)
                {
                    nIntegers = null;
                }
            }
        }

        return (nIntegers);

    }

    public static String[] concatArrays(String[] array_one, String[] array_two)
    {

        int               nIndex = 0;
        int          nArrayIndex = 0;
        int      nArrayOneLength = -1;
        int      nArrayTwoLength = -1;
        String[] sCombinedArrays = null;

        if (array_one == null && array_two == null)
        {
            return (sCombinedArrays);
        } else if (array_one == null)
        {
            sCombinedArrays = array_two;
        } else if (array_two == null)
        {
            sCombinedArrays = array_one;
        } else
        {
            nArrayOneLength = array_one.length;
            nArrayTwoLength = array_two.length;
            sCombinedArrays = new String[nArrayOneLength + nArrayTwoLength];

            while (nIndex < nArrayOneLength)
            {
                sCombinedArrays[nArrayIndex++] = array_one[nIndex++];
            }
            nIndex = 0;
            while (nIndex < nArrayTwoLength)
            {
                sCombinedArrays[nArrayIndex++] = array_two[nIndex++];
            }
        }

        return (sCombinedArrays);

    }

    public static String stripDigit(String strip_string)
    {

        String         sDelimiters = null;
        String           sStripped = null;
        String          sLocalCopy = null;
        StringBuffer sbBuildPieces = null;
        StringTokenizer   stParser = null;

        if (strip_string != null)
        {
            sLocalCopy = (containsDigit(strip_string.trim()) ? strip_string : null);

            if (sLocalCopy != null)
            {
                sDelimiters = ((new StringBuffer()).append(DIGIT_CHARACTERS)).toString();

                sbBuildPieces = new StringBuffer();
                stParser = new StringTokenizer(sLocalCopy, sDelimiters);
                while (stParser.hasMoreTokens())
                {
                    sbBuildPieces.append(stParser.nextToken());
                }

                sStripped = sbBuildPieces.toString();
            } else
            {
              sStripped = strip_string;
            }
        }

        return (sStripped);

    }

    public static String stripAlpha(String strip_string)
    {

        String         sDelimiters = null;
        String           sStripped = null;
        String          sLocalCopy = null;
        StringBuffer sbBuildPieces = null;
        StringTokenizer   stParser = null;

        if (strip_string != null)
        {
            sLocalCopy = (containsAlpha(strip_string.trim()) ? strip_string : null);

            if (sLocalCopy != null)
            {
                sDelimiters = ((new StringBuffer()).append(ALPHA_LOWERCASE).append(ALPHA_UPPERCASE)).toString();

                sbBuildPieces = new StringBuffer();
                stParser = new StringTokenizer(sLocalCopy, sDelimiters);
                while (stParser.hasMoreTokens())
                {
                    sbBuildPieces.append(stParser.nextToken());
                }

                sStripped = sbBuildPieces.toString();
            } else
            {
                sStripped = strip_string;
            }
        }

        return (sStripped);

    }

    public static boolean containsDigit(String digit_string)
    {

        int             nIndex = -1;
        boolean bContainsDigit = false;
        String      sLocalCopy = null;

        if (digit_string != null)
        {
            sLocalCopy = ((digit_string != null) ? digit_string.trim() : "");

            for (++nIndex; !bContainsDigit && nIndex < sLocalCopy.length(); nIndex++)
            {
                if (Character.isDigit(sLocalCopy.charAt(nIndex)))
                {
                    bContainsDigit = true;
                }
            }
        }

        return (bContainsDigit);

    }

    public static boolean containsAlpha(String alpha_string)
    {

        int             nIndex = -1;
        boolean bContainsAlpha = false;
        String      sLocalCopy = null;

        if (alpha_string != null)
        {
            sLocalCopy = ((alpha_string != null) ? alpha_string.trim() : "");

            for (++nIndex; !bContainsAlpha && nIndex < sLocalCopy.length(); nIndex++)
            {
                if (Character.isLetter(sLocalCopy.charAt(nIndex)))
                {
                    bContainsAlpha = true;
                }
            }
        }

        return (bContainsAlpha);

    }

    /**
     * Normalizes and trims a string.
     *
     * @param TempString String to be nomalized and trimed.
     * @return String Returns normalized and trimmed string.
     */
    public static String normalizeTrimString(String TempString)
    {
        return StringUtil.normalizeString(TempString.trim());
    }

    /**
     * Strips \n \r \t and "extra spaces" (not single spaces) out of
     * a string.
     *
     * @param StringToStrip String to be stripped.
     * @return String Stripped string.
     */
    public static String normalizeString(String StringToStrip)
    {
        String[][] StringsToReplaceArray = new String[][] { {"\n", ""}, {"\r", ""} , {"\t", ""}, {"  ", " "} };
        String StringToModify = new String(StringToStrip);

        int ArrayLength = StringsToReplaceArray.length;
        for(int i = 0; i < ArrayLength; i++)
        {
            StringToModify = StringUtil.stripAndReplaceString(StringToModify,
                                                              StringsToReplaceArray[i][0],
                                                              StringsToReplaceArray[i][1]);
        }

        return StringToModify;
    }

    /**
     * Strip StringToStripOut out of StringToStrip (recursively)
     *
     * @param StringToStrip String to strip from.
     * @param StringToStripOut String to strip out of StringToStrip.
     * @return String Stripped string.
     */
    public static String stripString(String StringToStrip, String StringToStripOut)
    {
        return StringUtil.stripAndReplaceString(StringToStrip,
                                                StringToStripOut,
                                                "");
    }

    /**
     * Replace StringToStripOut with StringToReplaceWith in
     * StringToStrip until there are StringToStripOuts within StringToStrip.
     * If StringToStripOut exists as a substring of StringToReplaceWith then an
     * stripAndReplaceStringOnePass(...) is called instead in order to avoid
     * inifinite replacements of replacements.  If StringToStripOut is the
     * same String as StringToReplaceWith then StringToStrip is just returned.
     * If StringToStripOut is "" (empty string) then StringToStrip is just
     * returned.
     *
     * @param StringToStrip String to strip from.
     * @param StringToStripOut String to strip out of StringToStrip.
     * @param StringToReplaceWith String to replace StringToStripOut with.
     * @return String Stripped and replaced string.
     */
    public static String stripAndReplaceString(String StringToStrip,
            String StringToStripOut, String StringToReplaceWith)
    {
        if (StringToStripOut.equals(StringToReplaceWith)
            || StringToStripOut.equals(""))
        {
            return StringToStrip;
        }

        if (StringToReplaceWith.indexOf(StringToStripOut) != -1)
        {
            return StringUtil.stripAndReplaceStringOnePass(StringToStrip,
                StringToStripOut, StringToReplaceWith);
        }

        String StringToModify = new String(StringToStrip);

        int currentPositionMarker = 0;
        int stringToReplaceWithLength = StringToReplaceWith.length();
        int PositionOfString = -1;

        while ((PositionOfString = StringToModify.indexOf(StringToStripOut))
            != -1)
        {
            StringToModify = StringToModify.substring(0, PositionOfString)
                .concat(StringToReplaceWith)
                .concat(StringToModify.substring(
                PositionOfString + StringToStripOut.length()));
        }

        return StringToModify;
    }

    /**
     * Replace StringToStripOut with StringToReplaceWith in StringToStrip
     * until there are no StringToStripOuts within StringToStrip.  Only the
     * original StrintToStripOuts are replaced.  If StringToReplaceWith
     * contains StringToStripOut(s) then subsequent replacement(s) are not
     * made.
     *
     * @param StringToStrip String to strip from.
     * @param StringToStripOut String to strip out of StringToStrip.
     * @param StringToReplaceWith String to replace StringToStripOut with.
     * @return String Stripped and replaced string.
     */
    public static String stripAndReplaceStringOnePass(String StringToStrip,
            String StringToStripOut, String StringToReplaceWith)
    {
        if (StringToStripOut.equals(StringToReplaceWith)
            || StringToStripOut.equals(""))
        {
            return StringToStrip;
        }

        String StringToModify = new String(StringToStrip);

        int currentPositionMarker = 0;
        int stringToReplaceWithLength = StringToReplaceWith.length();
        int PositionOfString = -1;

        while ((PositionOfString = StringToModify.substring(
            currentPositionMarker).indexOf(StringToStripOut)) != -1)
        {
            PositionOfString = PositionOfString + currentPositionMarker;
            StringToModify = StringToModify.substring(0, PositionOfString)
                .concat(StringToReplaceWith)
                .concat(StringToModify.substring(
                PositionOfString + StringToStripOut.length()));

            currentPositionMarker
                = PositionOfString + stringToReplaceWithLength;
        }

        return StringToModify;
    }

    /**
     * This is a replacement/enhancement to stringtokenizer.  It will return all
     * fields including fields that are empty.  For example, assume that the
     * delimiting character is a |.  RETURN|a|b|||c will return "" for the two
     * fields between b and c.  StringTokenizer will return b and then c and
     * skip the two empty fields.
     *
     * @param srcStr source string
     * @param delimChar delimin character to search for in srcStr
     *
     * @return ListIterator pointing to ArrayList of Strings
     */
    public static ListIterator parseString(String srcStr, char delimChar)
    {

        ArrayList<String> aList = new ArrayList<String>();

        if (srcStr == null)
        {
            return (null);
        }
        StringBuffer strBuff = new StringBuffer();
        int strLength = srcStr.length();
        int i = 0;

        for (i = 0; i < strLength; i++)
        {
            if (srcStr.charAt(i) == delimChar)
            {
                if (i == 0)
                {
                    strBuff.append("");
                }
                else
                {
                    if(srcStr.charAt(i-1) == srcStr.charAt(i))
                    {
                        strBuff.append("");
                    }
                }
                aList.add(strBuff.toString());
                strBuff.delete(0, strBuff.length());
            }
            else
            {
                strBuff.append(srcStr.charAt(i));
            }
        }
        // this adds the last field of the string after the last delimiter
        // if the last field is empty do the if ie; 1234|1|
        if (srcStr.charAt(i-1) == delimChar)
        {
            if (strBuff.length() > 0)
            {
                strBuff.delete(0, strBuff.length());  // make sure it is an empty string.
            }
            strBuff.append("");

        }
        aList.add(strBuff.toString());

        return (aList.listIterator());

    }

    public static boolean isAsciiPrintable(String s)
    {
        if (s == null || s.length() == 0)
        {
            return false;
        }

        return isArrayAsciiPrintable(s.getBytes());
    }

    public static boolean isArrayAsciiPrintable(byte[] array)
    {
        if (array == null || array.length == 0)
        {
            return false;
        }

        boolean isPrintable = true;

        int arrayLength = array.length;
        for (int index = 0; isPrintable && index < arrayLength; index++)
        {
            if (!isAsciiPrintable(array[index]))
            {
                isPrintable = false;
            }
        }

        return isPrintable;
    }

    public static boolean isAsciiPrintable(byte c)
    {
        return isAsciiPrintable((int) c);
    }

    public static boolean isArrayAsciiPrintable(char[] array)
    {
        if (array == null || array.length == 0)
        {
            return false;
        }

        boolean isPrintable = true;

        int arrayLength = array.length;
        for (int index = 0; isPrintable && index < arrayLength; index++)
        {
            if (!isAsciiPrintable(array[index]))
            {
                isPrintable = false;
            }
        }

        return isPrintable;
    }

    public static boolean isAsciiPrintable(char c)
    {
        return isAsciiPrintable((int) c);
    }

    public static boolean isArrayAsciiPrintable(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return false;
        }

        boolean isPrintable = true;

        int arrayLength = array.length;
        for (int index = 0; isPrintable && index < arrayLength; index++)
        {
            if (!isAsciiPrintable(array[index]))
            {
                isPrintable = false;
            }
        }

        return isPrintable;
    }

    public static boolean isAsciiPrintable(int c)
    {
        return (c == 9 || c == 10 || c == 13 || (c >= 32 && c <= 126));
    }

    public static String firstWord(String text)
    {
        if (text == null)
        {
            return "";
        }

        String tempText = text.trim();
        int tempSpacePosition = tempText.indexOf(" ");

        if (tempSpacePosition != -1)
        {
            return tempText.substring(0, tempSpacePosition);
        }
        else
        {
            return tempText;
        }
    }

    public static String dropFirstWord(String text)
    {
        if (text == null)
        {
            return "";
        }

        String tempText = text.trim();
        int tempSpacePosition = tempText.indexOf(" ");

        if (tempSpacePosition != -1)
        {
            return tempText.substring(tempSpacePosition + 1);
        }
        else
        {
            return "";
        }
    }

    public static String allWords(String text, int maxLength)
    {
        if (text == null)
        {
            return "";
        }

        if (text.length() >= maxLength)
        {
            String tempText = text.substring(0, maxLength);

            // make sure that String ends in a complete word
            int tempIndex = tempText.lastIndexOf(" ");
            if (tempIndex > 0)
            {
                return tempText.substring(0, tempIndex).trim();
            }
            else
            {
                return tempText;
            }
        }
        else
        {
            return text;
        }
    }

    /**
     * Repeatedly append to the left side of the string to pad with a character,
     * until the resultant string is equals the new string length.
     *
     * @param stringToPad String to pad.
     * @param characterToPadWith Character to pad string with.
     * @param newStringLength Integer length of new string.
     * @return String Padded string.
     */
    public static String padLeft(String stringToPad,
            char characterToPadWith, int newStringLength)
    {
        if (stringToPad == null)
        {
            throw new java.lang.NullPointerException(new StringBuffer()
                    .append("Exception thrown in StringUtil")
                    .append(".padLeft(String stringToPad, . . .):  ")
                    .append("stringToPad cannot be null")
                    .toString());
        }

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < (newStringLength - stringToPad.length()); i++)
        {
            result.append(characterToPadWith);
        }

        result.append(stringToPad);


        return result.toString();
    }

    /**
     * Repeatedly append to the left side of stringToPad a space,
     * ' ', until the resultant String is atleast newStringLength
     * characters long.
     * @param stringToPad String to pad.
     * @param newStringLength Integer length of new string.
     * @return String
     */
    public static String padLeft(String stringToPad, int newStringLength)
    {
        return StringUtil.padLeft(stringToPad, ' ', newStringLength);
    }

    /**
     * Repeatedly append to the right side of the string to pad with a
     * given character, until the resultant string equals the new string length.
     *
     * @param stringToPad String to pad.
     * @param characterToPadWith Character to pad string with.
     * @param newStringLength Integer length of new padded string.
     * @return String Padded string.
     */
    public static String padRight(String stringToPad, char characterToPadWith,
            int newStringLength)
    {
        if (stringToPad == null)
        {
            throw new java.lang.NullPointerException(new StringBuffer()
                    .append("Exception thrown in StringUtil")
                    .append(".padRight(String stringToPad, . . .):  ")
                    .append("stringToPad cannot be null")
                    .toString());
        }

        StringBuffer result = new StringBuffer(stringToPad);

        for (int i = 0; i < (newStringLength - stringToPad.length()); i++)
        {
            result.append(characterToPadWith);
        }

        return result.toString();
    }

    /**
     * Repeatedly append to the right side of the string with a space,
     * ' ', until the resultant string equals the new string length.
     *
     * @param stringToPad String to pad.
     * @param newStringLength Integer length of new padded string.
     * @return String Padded string.
     */
    public static String padRight (String stringToPad, int newStringLength)
    {
        return StringUtil.padRight (stringToPad, ' ', newStringLength);
    }

    public static String repeat(String stringToRepeat, int numberOfRepeats)
    {
        StringBuffer tempResult = new StringBuffer();

        for (int i = 0; i < numberOfRepeats; i++)
        {
            tempResult.append(stringToRepeat);
        }

        return tempResult.toString();
    }

    /**
     * Remove the leading package name from the class name
     *
     * @param className the class name including package, e.g. java.lang.String
     * @return only the class name excluding the package, e.g. String
     */
    public static String trimLeadingPackage(String className)
    {
        String trimmedName = className;

        int lastIndexOfDot = className.lastIndexOf('.');
        if (lastIndexOfDot > -1)
        {
            trimmedName = null;
            trimmedName = className.substring(lastIndexOfDot + 1);
        }

        return trimmedName;
    }

    public static String trimLeft(String stringToTrim)
    {
        return StringUtil.trimLeft(stringToTrim, " ");
    }

    public static String trimRight(String stringToTrim)
    {
        return StringUtil.trimRight(stringToTrim, " ");
    }

    public static String trim(String stringToTrim, String stringToRemove)
    {
        return StringUtil.trimRight(StringUtil.trimLeft(stringToTrim,
        stringToRemove), stringToRemove);
    }

    public static String trimLeft(String stringToTrim, String stringToRemove)
    {
        while (stringToTrim.startsWith(stringToRemove))
        {
            stringToTrim = stringToTrim.substring(stringToRemove.length());
        }

        return stringToTrim;
    }

    public static String trimRight(String stringToTrim, String stringToRemove)
    {
        while (stringToTrim.endsWith(stringToRemove))
        {
            stringToTrim = stringToTrim.substring(0,
                stringToTrim.length() - stringToRemove.length());
        }

        return stringToTrim;
    }

    /**
     * Convert a string value into an array of strings using the
     * {@link DELIMITER_CHARACTERS <tt>DELIMITER_CHARACTERS</tt>} values.
     *
     * @param value string to convert into a string array
     * @return non-null string array >= 0
     */
    public static String[] toStringArray(String value)
    {
        return toStringArray(value, DELIMITER_CHARACTERS);
    }

    /**
     * Convert a string value into an array of strings using the
     * supplied delimter characters.
     *
     * @param value string to convert into a string array
     * @param delimiters used to tokenize the string into an array
     * @return non-null string array >= 0
     */
    public static String[] toStringArray(String value, String delimiters)
    {
        if (value == null)
        {
            return new String[0];
        }

        value = value.trim();
        if (value.length() == 0)
        {
            return new String[0];
        }

        List<String> list = new ArrayList<String>();

        StringTokenizer tokenizer =
            new StringTokenizer(value, delimiters);
        while (tokenizer.hasMoreTokens())
        {
            list.add(tokenizer.nextToken());
        }
        tokenizer = null;

        String[] strings = (String[]) list.toArray(new String[0]);

        list.clear();
        list = null;

        return strings;
    }

    /**
     * Convert a string array into a delimited string using the supplied
     * delimiter character.
     *
     * @param array value to convert into a delimited string
     * @param delimiter the character used to delimit the string
     * @return non-null string >= 0
     */
    public static String fromStringArray(String[] array, char delimiter)
    {
        String value = "";

        if (array == null || array.length == 0)
        {
            return value;
        }

        StringBuffer buffer = new StringBuffer();
        int length = array.length;
        for (int i = 0; i < length; i++)
        {
            buffer.append(array[i]);
            if (i + 1 < length)
            {
                buffer.append(delimiter);
            }
        }

        value = buffer.toString();
        buffer = null;

        return value;
    }

    /**
     * Count number of stringToCounts from stringToCountFrom.
     * @param stringToCountFrom
     * @param stringToCount
     * @return
     */
    public static int countOccurances(String stringToCountFrom, String stringToCount)
    {
        String _S = stringToCountFrom;
        int _Count = 0;
        int _StartOfNewString = 0;
        while ((_StartOfNewString = _S.indexOf (stringToCount, _StartOfNewString )) != -1)
        {
            _Count++;
            _StartOfNewString += stringToCount.length ();
        }
        return _Count;
    }

    /**
     * UndoMeta
     */
    public static String UndoMeta(String str)
    {
        String _str = str.replaceAll ( "&&\\#035;035;", "\\#" );
        _str = _str.replaceAll ( "&\\#124;", "\\|");
        _str = _str.replaceAll ( "&\\#167;", "\\§");
        _str = _str.replaceAll ( "&\\#035;", "\\#");
        _str = _str.replaceAll ( "&\\#036;", "\\$");
        _str = _str.replaceAll ( "&\\#037;", "\\%");
        _str = _str.replaceAll ( "&\\#039;", "\\'");
        _str = _str.replaceAll ( "&\\#060;", "<" );
        _str = _str.replaceAll ( "&\\#062;", ">" );
        _str = _str.replaceAll ( "&lt;", "<" );
        _str = _str.replaceAll ( "&gt;", ">" );
        _str = _str.replaceAll ( "&\\#064;", "\\@");
        _str = _str.replaceAll ( "&\\#092;", "\\\\");
        _str = _str.replaceAll ( "&\\#096;", "\\`");
        _str = _str.replaceAll ( "&quot;", "\"" );
        _str = _str.replaceAll ( "<!--", "&lt;!--" );
        _str = _str.replaceAll ( "-->", "--&gt;" );

        return _str;
    }

    /**
     * Meta
     */

    public static String Meta(String str)
    {
        return ( Meta(str, true) );
    }

    public static String Meta(String str, Boolean filterHtml)
    {
        if (str == null) return "";
        String _str = UndoMeta( str );
        _str = _str.replaceAll ( "\"", "&quot;"  );
        _str = _str.replaceAll ( "\\#", "&\\#035;" );
        _str = _str.replaceAll ( "\\$", "&\\#036;" );
        _str = _str.replaceAll ( "\\%", "&\\#037;" );
        _str = _str.replaceAll ( "\\'", "&\\#039;" );
        _str = _str.replaceAll ( "\\@", "&\\#064;" );
        _str = _str.replaceAll ( "\\`", "&\\#096;" );
        _str = _str.replaceAll ( "\\|", "&\\#124;" );
        _str = _str.replaceAll ( "\\§", "&\\#167;" );
        _str = _str.replaceAll ( "\\\\", "&\\#092;" );

        _str = _str.replaceAll ( "[^a-zA-Z0-9,.;\\#\\(\\)_\\-\\+!& \\/\\<\\>\n\\=\\:\\^\\*\\[\\]\\{\\}\\~\\?]+", "" );
        _str = _str.replaceAll ( "\\?&\\#167;", "&\\#167;" );

        if (filterHtml) {
            return (FilterHtml(_str));
        } else {
	    _str = _str.replaceAll ( "&lt;!--", "<!--" );
	    _str = _str.replaceAll ( "--&gt;", "-->" );
            _str = _str.replaceAll ( "<", "&lt;"  );
            _str = _str.replaceAll ( ">", "&gt;"  );
            return (_str);
        }

    }

    /**
     * Filter allowed tags.
     */

    public static String FilterHtml(String str)
    {
                str = str.replaceAll("(?i)<i>", "TempTagBegItalic")
                        .replaceAll("(?i)</i>", "TempTagEndItalic")
                        .replaceAll("(?i)<b>", "TempTagBeginBb")
                        .replaceAll("(?i)</b>", "TempTagEndBb")
                        .replaceAll("(?i)<big>","TempTagBeginBig")
                        .replaceAll("(?i)</big>","TempTagEndBig")
                        .replaceAll("(?i)<em>","TempTagBeginEm")
                        .replaceAll("(?i)</em>","TempTagEndEm")
                        .replaceAll("(?i)<small>","TempTagBeginSmall")
                        .replaceAll("(?i)</small>","TempTagEndSmall")
                        .replaceAll("(?i)<strong>","TempTagBeginStrong")
                        .replaceAll("(?i)</strong>","TempTagEndStrong")
                        .replaceAll("(?i)<sub>","TempTagBeginSub")
                        .replaceAll("(?i)</sub>","TempTagEndSub")
                        .replaceAll("(?i)<sup>","TempTagBeginSup")
                        .replaceAll("(?i)</sup>","TempTagEndSup")
                        .replaceAll("(?i)<ins>","TempTagBeginIns")
                        .replaceAll("(?i)</ins>","TempTagEndIns")
                        .replaceAll("(?i)<del>","TempTagBeginDel")
                        .replaceAll("(?i)</del>","TempTagEndDel")
                        .replaceAll("(?i)<s>","TempTagBeginSs")
                        .replaceAll("(?i)</s>","TempTagEndSs")
                        .replaceAll("(?i)<strike>","TempTagBeginStrike")
                        .replaceAll("(?i)</strike>","TempTagEndStrike")
                        .replaceAll("(?i)<u>","TempTagBeginUu")
                        .replaceAll("(?i)</u>","TempTagEndUu")
                        .replaceAll("(?i)<tt>","TempTagBeginTt")
                        .replaceAll("(?i)</tt>","TempTagEndTt")
                        .replaceAll("(?i)<address>","TempTagBeginAddress")
                        .replaceAll("(?i)</address>","TempTagEndAddress")
                        .replaceAll("(?i)<blockquote>","TempTagBeginBq")
                        .replaceAll("(?i)</blockquote>","TempTagEndBq")
                        .replaceAll("(?i)<q>","TempTagBeginQq")
                        .replaceAll("(?i)</q>","TempTagEndQq")
                        .replaceAll("(?i)<cite>","TempTagBeginCite")
                        .replaceAll("(?i)</cite>","TempTagEndCite")
                        .replaceAll("(?i)<dfn>","TempTagBeginDfn")
                        .replaceAll("(?i)</dfn>","TempTagEndDfn")
                        .replaceAll("(?i)<p>","TempTagBeginPp")
                        .replaceAll("(?i)</p>","TempTagEndPp")
                        .replaceAll("(?i)<hr>","TempTagBeginHr")
                        .replaceAll("(?i)<hr />","TempTagEndHr")
                        .replaceAll("(?i)<br>","TempTagBeginBr")
                        .replaceAll("(?i)<br />","TempTagEndBr")
                        .replaceAll("(?i)<center>","TempTagBeginCenter")
                        .replaceAll("(?i)</center>","TempTagEndCenter")
                        .replaceAll("(?i)<h1>","TempTagBeginH1")
                        .replaceAll("(?i)</h1>","TempTagEndH1")
                        .replaceAll("(?i)<h2>","TempTagBeginH2")
                        .replaceAll("(?i)</h2>","TempTagEndH2")
                        .replaceAll("(?i)<h3>","TempTagBeginH3")
                        .replaceAll("(?i)</h3>","TempTagEndH3")
                        .replaceAll("(?i)<font ","TempTagBeginFont")
                        .replaceAll("(?i)</font>","TempTagEndFont")
                        .replaceAll("(?i)<a href","TempTagBeginAa")
                        .replaceAll("(?i)</a>","TempTagEndAa")
                        .replaceAll("(?i)<span", "TempTagBeginSpan")
                        .replaceAll("(?i)</span>", "TempTagEndSpan");

                int indx1 = str.indexOf("TempTagBeginFont");
                int indx2 = -1;
                String frontS = "";
                String backS = "";
                while (indx1 >= 0 ) {
                        indx2 = str.indexOf(">", indx1);
                        frontS = str.substring(0,indx2);
                        backS = str.substring(indx2).replaceFirst(">",
                                "TempTagEndFA");
                        str = frontS + backS;
                        indx1 = str.indexOf("TempTagBeginFont",indx2+15);
                }

                indx1 = str.indexOf("TempTagBeginAa");
                indx2 = -1;
                while (indx1 >= 0 ) {
                        indx2 = str.indexOf(">", indx1);
                        frontS = str.substring(0,indx2);
                        backS = str.substring(indx2).replaceFirst(">",
                                "TempTagEndFA");
                        str = frontS + backS;
                        indx1 = str.indexOf("TempTagBeginAa",indx2+15);
                }

                indx1 = str.indexOf("TempTagBeginSpan");
                indx2 = -1;
                while (indx1 >= 0 ) {
                        indx2 = str.indexOf(">", indx1);
                        frontS = str.substring(0,indx2);
                        backS = str.substring(indx2).replaceFirst(">",
                                "TempTagEndFA");
                        str = frontS + backS;
                        indx1 = str.indexOf("TempTagBeginSpan",indx2+15);
                }

                str = str.replaceAll("<", "&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("TempTagBegItalic", "<i>")
                        .replaceAll("TempTagEndItalic", "</i>")
                        .replaceAll("TempTagBeginBb", "<b>")
                        .replaceAll("TempTagEndBb", "</b>")
                        .replaceAll("TempTagBeginBig", "<big>")
                        .replaceAll("TempTagEndBig", "</big>")
                        .replaceAll("TempTagBeginEm", "<em>")
                        .replaceAll("TempTagEndEm", "</em>")
                        .replaceAll("TempTagBeginSmall", "<small>")
                        .replaceAll("TempTagEndSmall", "</small>")
                        .replaceAll("TempTagBeginStrong", "<strong>")
                        .replaceAll("TempTagEndStrong", "</strong>")
                        .replaceAll("TempTagBeginSub", "<sub>")
                        .replaceAll("TempTagEndSub", "</sub>")
                        .replaceAll("TempTagBeginSup", "<sup>")
                        .replaceAll("TempTagEndSup", "</sup>")
                        .replaceAll("TempTagBeginIns", "<ins>")
                        .replaceAll("TempTagEndIns", "</ins>")
                        .replaceAll("TempTagBeginDel", "<del>")
                        .replaceAll("TempTagEndDel", "</del>")
                        .replaceAll("TempTagBeginSs", "<s>")
                        .replaceAll("TempTagEndSs", "</s>")
                        .replaceAll("TempTagBeginStrike", "<strike>")
                        .replaceAll("TempTagEndStrike", "</strike>")
                        .replaceAll("TempTagBeginUu", "<u>")
                        .replaceAll("TempTagEndUu", "</u>")
                        .replaceAll("TempTagBeginTt", "<tt>")
                        .replaceAll("TempTagEndTt", "</tt>")
                        .replaceAll("TempTagBeginAddress", "<address>")
                        .replaceAll("TempTagEndAddress", "</address>")
                        .replaceAll("TempTagBeginBq", "<blockquote>")
                        .replaceAll("TempTagEndBq", "</blockquote>")
                        .replaceAll("TempTagBeginQq", "<q>")
                        .replaceAll("TempTagEndQq", "</q>")
                        .replaceAll("TempTagBeginCite", "<cite>")
                        .replaceAll("TempTagEndCite", "</cite>")
                        .replaceAll("TempTagBeginDfn", "<dfn>")
                        .replaceAll("TempTagEndDfn", "</dfn>")
                        .replaceAll("TempTagBeginPp", "<p>")
                        .replaceAll("TempTagEndPp", "</p>")
                        .replaceAll("TempTagBeginHr", "<hr>")
                        .replaceAll("TempTagEndHr", "<hr />")
                        .replaceAll("TempTagBeginBr", "<br>")
                        .replaceAll("TempTagEndBr", "<br />")
                        .replaceAll("TempTagBeginCenter", "<center>")
                        .replaceAll("TempTagEndCenter", "</center>")
                        .replaceAll("TempTagBeginH1", "<h1>")
                        .replaceAll("TempTagEndH1", "</h1>")
                        .replaceAll("TempTagBeginH2", "<h2>")
                        .replaceAll("TempTagEndH2", "</h2>")
                        .replaceAll("TempTagBeginH3", "<h3>")
                        .replaceAll("TempTagEndH3", "</h3>")
                        .replaceAll("TempTagBeginFont", "<font ")
                        .replaceAll("TempTagEndFont", "</font>")
                        .replaceAll("TempTagBeginAa", "<a href")
                        .replaceAll("TempTagEndAa", "</a>")
                        .replaceAll("TempTagEndFA", ">")
                        .replaceAll("TempTagBeginSpan", "<span ")
                        .replaceAll("TempTagEndSpan", "</span>")
			//adding for issue 30408
			.replaceAll("&quot;highlight&quot;", "\"highlight\"");

        return str;
    }

}
