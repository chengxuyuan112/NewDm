package com.xteamsoft.baselibrary.utils;

public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean equals(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
        if (paramCharSequence1 != paramCharSequence2) {
            if (paramCharSequence1 != null && paramCharSequence2 != null) {
                int i = paramCharSequence1.length();
                if (i == paramCharSequence2.length()) {
                    if (paramCharSequence1 instanceof String && paramCharSequence2 instanceof String)
                        return paramCharSequence1.equals(paramCharSequence2);
                    int j = 0;
                    while (true) {
                        if (j < i) {
                            if (paramCharSequence1.charAt(j) != paramCharSequence2.charAt(j))
                                return false;
                            j++;
                            continue;
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static boolean equalsIgnoreCase(String paramString1, String paramString2) {
        boolean bool = false;
        if (paramString1 != paramString2) {
            boolean bool1 = bool;
            if (paramString2 != null) {
                bool1 = bool;
                if (paramString1.length() == paramString2.length()) {
                    bool1 = bool;
                    if (paramString1.regionMatches(true, 0, paramString2, 0, paramString2.length()))
                        return true;
                }
            }
            return bool1;
        }
        return true;
    }

    public static boolean isEmpty(CharSequence paramCharSequence) {
        return (paramCharSequence == null || paramCharSequence.length() == 0);
    }

    public static boolean isSpace(String paramString) {
        return (paramString == null || paramString.trim().length() == 0);
    }

    public static int length(CharSequence paramCharSequence) {
        return (paramCharSequence == null) ? 0 : paramCharSequence.length();
    }

    public static String lowerFirstLetter(String paramString) {
        return (isEmpty(paramString) || !Character.isUpperCase(paramString.charAt(0))) ? paramString : (String.valueOf((char)(paramString.charAt(0) + 32)) + paramString.substring(1));
    }

    public static String null2Length0(String paramString) {
        String str = paramString;
        if (paramString == null)
            str = "";
        return str;
    }

    public static String reverse(String paramString) {
        int j = length(paramString);
        if (j <= 1)
            return paramString;
        char[] arrayOfChar = paramString.toCharArray();
        for (int i = 0; i < j >> 1; i++) {
            char c = arrayOfChar[i];
            arrayOfChar[i] = arrayOfChar[j - i - 1];
            arrayOfChar[j - i - 1] = c;
        }
        return new String(arrayOfChar);
    }

    public static String toDBC(String paramString) {
        if (isEmpty(paramString))
            return paramString;
        char[] arrayOfChar = paramString.toCharArray();
        int i = 0;
        int j = arrayOfChar.length;
        while (i < j) {
            if (arrayOfChar[i] == ' ') {
            arrayOfChar[i] = ' ';
        } else if ('!'<= arrayOfChar[i] && arrayOfChar[i] <= '~') {
            arrayOfChar[i] = (char)(arrayOfChar[i] - 65248);
        } else {
            arrayOfChar[i] = arrayOfChar[i];
        }
        i++;
    }
    return new String(arrayOfChar);
}

    public static String toSBC(String paramString) {
        if (isEmpty(paramString))
            return paramString;
        char[] arrayOfChar = paramString.toCharArray();
        int i = 0;
        int j = arrayOfChar.length;
        while (i < j) {
            if (arrayOfChar[i] == ' ') {
                arrayOfChar[i] = ' ';
            } else if ('!' <= arrayOfChar[i] && arrayOfChar[i] <= '~') {
                arrayOfChar[i] = (char)(arrayOfChar[i] + 65248);
            } else {
                arrayOfChar[i] = arrayOfChar[i];
            }
            i++;
        }
        return new String(arrayOfChar);
    }

    public static String upperFirstLetter(String paramString) {
        return (isEmpty(paramString) || !Character.isLowerCase(paramString.charAt(0))) ? paramString : (String.valueOf((char)(paramString.charAt(0) - 32)) + paramString.substring(1));
    }
}
