/**
 * <p>
 * Title: StringUtils.java
 * </p>
 * <p>
 * Description: TODO(describe the file)
 * </p>
 * <p>
 * <p>
 * </p>
 *
 * @Copyright: Copyright (c) 2015
 * @author xwc1125
 * @date 2015-7-13下午2:08:17
 * @version V1.0
 */
package com.xwc1125.chain5j.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Title: StringUtils
 * </p>
 *
 * @author xwc1125
 * @date 2015-7-13下午2:08:17
 */
public class StringUtils {

    /**
     * @param @param  str
     * @param @return
     * @return Boolean
     * @Title isNotEmpty
     * @Description isNotEmpty
     * @author xwc1125
     * @date 2016年4月1日 上午9:25:28
     */
    public static Boolean isNotEmpty(String... str) {
        if (str == null) {
            return false;
        }
        for (String s : str) {
            if (s == null || s.length() == 0 || s.trim().length() == 0 || s.equals("null") || s.equals("")) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param str       origin string
     * @param strLength need the length
     * @param subStr    the value need to fill
     * @param flag      true:fill in the left，false:fill in the right
     * @param
     * @return String
     * @Title deficiencyFill
     * @Description the length of string is not enough
     * @author xwc1125
     * @date 2016年1月19日 上午9:31:14
     */
    public static String deficiencyFill(String str, int strLength, String subStr, boolean flag) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            if (flag) {
                sb.append(subStr).append(str);
            } else {
                sb.append(str).append(subStr);
            }
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * @param ss
     * @return boolean
     * @Title isEmpty
     * @author xwc1125
     * @date 2016年1月22日 下午2:36:09
     */
    public static boolean isEmpty(String... ss) {
        if (ss == null) {
            return true;
        }
        for (String s : ss) {
            if (s == null || s.length() == 0 || s.trim().length() == 0 || s.equals("null") || s.equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Title: isContain
     * </p>
     * <p>
     * Description: Whether the object in arrays
     *
     * @param str
     * @param filter
     * @return
     * @author xwc1125
     * @date 2016-3-28上午11:52:31
     */
    public static Boolean isContain(Object str, Object... filter) {
        if (filter == null || filter.length == 0 || str == null) {
            return false;
        }
        List<Object> l = Arrays.asList(filter);
        if (l.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Description: list is contain the string
     *
     * @param str
     * @param filter
     * @return
     * @author xwc1125
     * @date 2016-3-28下午12:02:19
     */
    public static Boolean isListContain(String str, List<String> filter) {
        if (filter == null || filter.size() == 0 || str == null) {
            return false;
        }
        if (filter.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param tempString   string
     * @param splitPattern Separator
     * @param @return
     * @return List<String>
     * @Title str2List
     * @Description string to List
     * @author xwc1125
     * @date 2016年6月13日 下午6:07:42
     */
    public static List<String> str2List(String tempString, String splitPattern) {
        if (isEmpty(tempString)) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        String[] Str = tempString.split(splitPattern);
        list = Arrays.asList(Str);
        return list;
    }

    /**
     * user , to split string to arrays
     *
     * @param valStr
     * @return String[]
     */
    public static String[] StrList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    /**
     * @param @param  listStr
     * @param @return
     * @return List<String>
     * @Title getListFromListStr
     * @Description the list string to List
     * @author xwc1125
     * @date 2016年2月23日 上午9:26:37
     */
    public static List<String> getListFromListStr(String listStr) {
        List<String> receiversList = new ArrayList<String>();
        if (StringUtils.isEmpty(listStr)) {
            return receiversList;
        }
        try {
            listStr = listStr.replaceAll("\n", "");
            String[] arr = listStr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",");
            receiversList = Arrays.asList(arr);
        } catch (Exception e) {
            receiversList.add(listStr);
        }
        return receiversList;
    }

    /**
     * <p>
     * Title: strToUnicode
     * </p>
     * <p>
     * Description: string to Unicode
     * </p>
     * <p>
     * <p>
     * </p>
     *
     * @param str
     * @return
     * @author xwc1125
     * @date 2016年9月20日 下午5:08:24
     */
    public static String strToUnicode(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8);
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            j = (c & 0xFF);
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);

        }
        return (new String(sb));
    }

    /**
     * <p>
     * Title: unicodeToStr
     * </p>
     * <p>
     * Description: Unicode to string
     * </p>
     * <p>
     * <p>
     * </p>
     *
     * @param str
     * @return
     * @author xwc1125
     * @date 2016年9月20日 下午5:09:06
     */
    public static String unicodeToStr(String str) {
        str = (str == null ? "" : str);
        // If it is not a Unicode code code, return as it is
        if (str.indexOf("\\u") == -1) {
            return str;
        }

        StringBuffer sb = new StringBuffer(1000);

        for (int i = 0; i < str.length() - 6; ) {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++) {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar) {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }

                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }

    /**
     * Case the first word
     *
     * @param srcStr
     * @return
     */
    public static String firstCharacterToUpper(String srcStr) {
        if (isEmpty(srcStr)) {
            return "";
        }
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }

    /**
     * Replace the string and capitalize its next letter
     *
     * @param srcStr
     * @param org
     * @param ob
     * @return
     */
    public static String replaceUnderlineAndfirstToUpper(String srcStr, String org, String ob) {
        if (isEmpty(srcStr)) {
            return "";
        }
        String newString = "";
        int first = 0;
        while (srcStr.indexOf(org) != -1) {
            first = srcStr.indexOf(org);
            if (first != srcStr.length()) {
                newString = newString + srcStr.substring(0, first) + ob;
                srcStr = srcStr.substring(first + org.length(), srcStr.length());
                srcStr = firstCharacterToUpper(srcStr);
            }
        }
        newString = newString + srcStr;
        return newString;
    }

    public static String strJoin(CharSequence delimiter, Iterable<? extends CharSequence> elements) throws Exception {
        if (delimiter == null || elements == null) {
            throw new Exception("Param is empty");
        }

        StringBuffer joiner = new StringBuffer();

        for (CharSequence cs : elements) {
            joiner.append(cs).append(delimiter);
        }
        int aa = joiner.lastIndexOf(delimiter + "");
        return joiner.substring(0, aa);
    }

    /**
     * Number deficiency fill zero
     *
     * @param str       string(must be int)
     * @param strLength Number of zeros to be filled
     * @param isHigh    true：file in the left，false：file in the right
     * @return
     */
    public static String addZeroForNum(String str, int strLength, boolean isHigh) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                if (isHigh) {
                    sb.append("0").append(str);
                } else {
                    sb.append(str).append("0");
                }
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * Add zero to decimal data
     *
     * @param valueF
     * @param strLength
     * @param isHigh
     * @return
     */
    public static String addZeroForDouble(Double valueF, int strLength, boolean isHigh) {
        String numStr = valueF + "";
        String[] numArray = numStr.split("\\.");
        String numRight = numArray[0];
        String numLeft = numArray[1];
        if (isHigh) {
            //fill 0 in the left
            int strLen = numRight.length();
            if (strLen < strLength) {
                while (strLen < strLength) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("0").append(numRight);
                    numRight = sb.toString();
                    strLen = numRight.length();
                }
            }
        } else {
            //fill 0 in the right
            int strLen = numLeft.length();
            if (strLen < strLength) {
                while (strLen < strLength) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(numLeft).append("0");
                    numLeft = sb.toString();
                    strLen = numLeft.length();
                }
            }
        }
        if (isHigh) {
            return numRight + "." + numLeft;
        } else {
            return numRight + "" + numLeft;
        }
    }

    /**
     * remove emoji unicode
     *
     * @param str
     * @return
     */
    public static String removeEmojiUnicode(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("[^\\u0000-\\uFFFF]", "");
        return str;
    }
}
