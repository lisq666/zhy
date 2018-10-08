package com.example.utils;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	/**
	 * 替换指定位置字符
	 * 
	 * @param input 原始输入字符
	 * @param start 开始位置,1~N
	 * @param end 结束位置,1~N
	 * @param regex 待替换字符,默认*
	 * @return
	 */
	public static String replaceString(String input, Integer start, Integer end, String regex) {

		if (input == null || input.trim().length() == 0) {
			return "";
		}
		if (regex == null || regex.trim().length() == 0) {
			regex = "*";
		}
		StringBuffer sbf = new StringBuffer(input);
		for (int i = start - 1; i < end; i++) {
			sbf.replace(i, i + 1, regex);
		}
		return sbf.toString();
	}
	
    /**
     * 删除结尾的字符,空格 全半角逗号 |
     *
     * @param input input
     * @return String
     */
    public static String rightTrim(String input) {
        if (input == null || input.trim().length() == 0) {
            return "";
        }
        int position = input.length();
        while (true) {
            char _char = input.charAt(position - 1);
            if (_char == ' ' || _char == ',' || _char == '，' || _char == '|') {
                position--;
            } else {
                break;
            }
        }
        if (position == input.length()) {
            return input;
        } else {
            return input.substring(0, position);
        }
    }

    /**
     * 清除回车
     *
     * @param input input
     * @return String
     */
    public static String clearBr(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        return input.replace("\r\n", "");
    }

    /**
     * 不区分中英文的截字
     *
     * @param input      传入参数
     * @param startIndex 起始位置
     * @param length     截取长度
     * @return String
     */
    public static String cutString(String input, int startIndex, int length) {
        if (input == null || input.length() == 0) {
            return "";
        }
        if (startIndex >= 0 && length > 0 && startIndex + length <= input.length()) {
            return input.substring(startIndex, length);
        }
        return input;
    }

    /**
     * 不区分中英文的截字
     *
     * @param input  String
     * @param length int
     * @return String
     */
    public static String cutString(String input, int length) {
        return cutString(input, 0, length);
    }

    /**
     * 中英文字符串截取
     *
     * @param input String
     * @param len   Integer
     * @param tail  结尾 ...
     * @return String
     */
    public static String cutStringCN(String input, Integer len, String tail) {
        if (input == null || input.length() == 0) {
            return "";
        }

        int l = input.length();

        for (int i = 0; i < l && i < len; i++) {
            if (input.charAt(i) > 0xFF)
                len--;
        }
        if (l <= len) {
            return input;
        }
        String result = input.substring(0, len);
        return tail == null ? result : result + tail;
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }


    /**
     * 中英文字符串截取
     *
     * @param input input
     * @param len   len
     * @return String
     */
    public static String cutStringCN(String input, int len) {
        return cutStringCN(input, len, null);
    }

    /**
     * 转全角的函数(SBC case) To全角字符串(半角转全角) 全角空格为12288,半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param input String
     * @return String
     */
    public static String toWide(String input) {
        if (input == null) {
            return null;
        }
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }

    /**
     * 转半角的函数(DBC case) To半角字符串 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param input String
     * @return String
     */
    public static String toNarrow(String input) {
        if (input == null) {
            return null;
        }
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 删除最后一个字符
     *
     * @param input String
     * @return String
     */
    public static String clearLastChar(String input) {
        if (input == null || input.trim().length() == 0) {
            return "";
        }
        return input.substring(0, input.length() - 1);
    }

    /**
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
     */
    static final char DBC_CHAR_START = 33; // 半角!

    /**
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
     */
    static final char DBC_CHAR_END = 126; // 半角~

    /**
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
     */
    static final char SBC_CHAR_START = 65281; // 全角！

    /**
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
     */
    static final char SBC_CHAR_END = 65374; // 全角～

    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     */
    static final int CONVERT_STEP = 65248; // 全角半角转换间隔

    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     */
    static final char SBC_SPACE = 12288; // 全角空格 12288

    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     */
    static final char DBC_SPACE = ' '; // 半角空格

    /**
     * <PRE>
     * 半角字符->全角字符转换
     * 只处理空格，!到&tilde;之间的字符，忽略其他
     * </PRE>
     */
    public static String bj2qj(String src) {
        if (src == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (char aCa : ca) {
            if (aCa == DBC_SPACE) { // 如果是半角空格，直接用全角空格替代
                buf.append(SBC_SPACE);
            } else if ((aCa >= DBC_CHAR_START) && (aCa <= DBC_CHAR_END)) { // 字符是!到~之间的可见字符
                buf.append((char) (aCa + CONVERT_STEP));
            } else { // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
                buf.append(aCa);
            }
        }
        return buf.toString();
    }

    /**
     * <PRE>
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * </PRE>
     */
    public static String qj2bj(String src) {
        if (src == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) { // 如果是全角空格
                buf.append(DBC_SPACE);
            } else { // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 获取query的map
     *
     * @param query ?后面的
     * @return Map
     */
    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    /**
     * 去除中文标点
     *
     * @param input
     * @return
     */
    public static String trimChineseSymbol(String input) {
        if (input == null)
            return null;
        Pattern p = Pattern.compile("[<>《》（）()-—\"“”._】【」「+•]");// 对应的标点
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String first = m.replaceAll(""); // 把英文标点符号替换成空，即去掉英文标点符号
            return first;
        }
        return input;
    }


    public static boolean isObjectEmpty(Object object) {
        if (null == object) {
            return true;
        }

        if ((object instanceof String)) {
            if ("".equals(((String) object).trim())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotObjectEmpty(Object object) {
        return !isObjectEmpty(object);
    }

    /***********
     * escape java ignore java control char
     ***************/

    private static String[][] JAVA_CTRL_CHARS_ESCAPE_IGNORE = {
            {"\b", "\b"},
            {"\n", "\n"},
            {"\t", "\t"},
            {"\f", "\f"},
            {"\r", "\r"}
    };


    /**
     * Used to invert an escape array into an unescape array
     *
     * @param array String[][] to be inverted
     * @return String[][] inverted array
     */
    public static String[][] invert(final String[][] array) {
        final String[][] newarray = new String[array.length][2];
        for (int i = 0; i < array.length; i++) {
            newarray[i][0] = array[i][1];
            newarray[i][1] = array[i][0];
        }
        return newarray;
    }

    /****************************************/
    /**
     * 过滤掉超过3个字节的UTF8字符
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }

            b += 256; // 去掉符号位

            if (((b >> 5) ^ 0x6) == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            } else if (((b >> 4) ^ 0xE) == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            } else if (((b >> 3) ^ 0x1E) == 0) {
                i += 4;
            } else if (((b >> 2) ^ 0x3E) == 0) {
                i += 5;
            } else if (((b >> 1) ^ 0x7E) == 0) {
                i += 6;
            } else {
                buffer.put(bytes[i++]);
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }

}
