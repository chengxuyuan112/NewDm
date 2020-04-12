package com.xteamsoft.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class ConvertUtils {
    private static final char[] hexDigits = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] bitmap2Bytes(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat) {
        if (paramBitmap == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        paramBitmap.compress(paramCompressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Drawable bitmap2Drawable(Resources paramResources, Bitmap paramBitmap) {
        return (Drawable) ((paramBitmap == null) ? null : new BitmapDrawable(paramResources, paramBitmap));
    }

    public static byte[] bits2Bytes(String paramString) {
        int k = paramString.length() % 8;
        int j = paramString.length() / 8;
        int i = j;
        String str = paramString;
        if (k != 0) {
            for (i = k; i < 8; i++)
                paramString = "0" + paramString;
            i = j + 1;
            str = paramString;
        }
        byte[] arrayOfByte = new byte[i];
        for (j = 0; j < i; j++) {
            for (k = 0; k < 8; k++) {
                arrayOfByte[j] = (byte) (arrayOfByte[j] << 1);
                arrayOfByte[j] = (byte) (arrayOfByte[j] | str.charAt(j * 8 + k) - 48);
            }
        }
        return arrayOfByte;
    }

    @SuppressLint({"DefaultLocale"})
    public static String byte2FitMemorySize(long paramLong) {
        return (paramLong < 0L) ? "shouldn't be less than zero!" : ((paramLong < 1024L) ? String.format("%.3fB", new Object[]{Double.valueOf(paramLong + 5.0E-4D)}) : ((paramLong < 1048576L) ? String.format("%.3fKB", new Object[]{Double.valueOf((paramLong / 1024L) + 5.0E-4D)}) : ((paramLong < 1073741824L) ? String.format("%.3fMB", new Object[]{Double.valueOf((paramLong / 1048576L) + 5.0E-4D)}) : String.format("%.3fGB", new Object[]{Double.valueOf((paramLong / 1073741824L) + 5.0E-4D)}))));
    }

    public static double byte2MemorySize(long paramLong, ConstUtils.MemoryUnit paramMemoryUnit) {
        if (paramLong < 0L)
            return -1.0D;
//        switch (paramMemoryUnit) {
//            default:
//                return paramLong;
//            //case SEC:
//            case 100:
//                return paramLong / 1024.0D;
//            //case MIN:
//            case 110:
//                return paramLong / 1048576.0D;
//            //case HOUR:
//            case 120:
//                break;
//        }
        return paramLong / 1.073741824E9D;
    }

    public static Bitmap bytes2Bitmap(byte[] paramArrayOfbyte) {
        return (paramArrayOfbyte == null || paramArrayOfbyte.length == 0) ? null : BitmapFactory.decodeByteArray(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    }

    public static String bytes2Bits(byte[] paramArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        int j = paramArrayOfbyte.length;
        for (int i = 0; i < j; i++) {
            byte b = paramArrayOfbyte[i];
            for (int k = 7; k >= 0; k--) {
                byte b1;
                if ((b >> k & 0x1) == 0) {
                    b1 = 48;
                } else {
                    b1 = 49;
                }
                stringBuilder.append(b1);
            }
        }
        return stringBuilder.toString();
    }

    public static char[] bytes2Chars(byte[] paramArrayOfbyte) {
        char[] arrayOfChar = null;
        if (paramArrayOfbyte != null) {
            int i = paramArrayOfbyte.length;
            if (i > 0) {
                char[] arrayOfChar1 = new char[i];
                int j = 0;
                while (true) {
                    arrayOfChar = arrayOfChar1;
                    if (j < i) {
                        arrayOfChar1[j] = (char) (paramArrayOfbyte[j] & 0xFF);
                        j++;
                        continue;
                    }
                    return arrayOfChar;
                }
            }
        }
        return arrayOfChar;
    }

    public static Drawable bytes2Drawable(Resources paramResources, byte[] paramArrayOfbyte) {
        return (paramResources == null) ? null : bitmap2Drawable(paramResources, bytes2Bitmap(paramArrayOfbyte));
    }

    public static String bytes2HexString(byte[] paramArrayOfbyte) {
        if (paramArrayOfbyte != null) {
            int i = paramArrayOfbyte.length;
            if (i > 0) {
                char[] arrayOfChar = new char[i << 1];
                int j = 0;
                int k = 0;
                while (j < i) {
                    int m = k + 1;
                    arrayOfChar[k] = hexDigits[paramArrayOfbyte[j] >>> 4 & 0xF];
                    k = m + 1;
                    arrayOfChar[m] = hexDigits[paramArrayOfbyte[j] & 0xF];
                    j++;
                }
                return new String(arrayOfChar);
            }
        }
        return null;
    }

    public static InputStream bytes2InputStream(byte[] paramArrayOfbyte) {
        return (paramArrayOfbyte == null || paramArrayOfbyte.length <= 0) ? null : new ByteArrayInputStream(paramArrayOfbyte);
    }

    public static OutputStream bytes2OutputStream(byte[] paramArrayOfbyte) {
        byte[] arrayOfByte1;
        if (paramArrayOfbyte == null || paramArrayOfbyte.length <= 0)
            return null;
        IOException iOException = null;
        byte[] arrayOfByte2 = null;
        try {
            IOException iOException1 = null;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            arrayOfByte1 = paramArrayOfbyte;
            iOException1.printStackTrace();
            // CloseUtils.closeIO((Closeable)paramArrayOfbyte);
            return null;
        } catch (Exception e) {

        } finally {

        }
        // CloseUtils.closeIO(new Closeable[] { (Closeable)arrayOfByte1 });
        throw null;
    }

    public static byte[] chars2Bytes(char[] paramArrayOfchar) {
        if (paramArrayOfchar == null || paramArrayOfchar.length <= 0)
            return null;
        int j = paramArrayOfchar.length;
        byte[] arrayOfByte = new byte[j];
        int i = 0;
        while (true) {
            byte[] arrayOfByte1 = arrayOfByte;
            if (i < j) {
                arrayOfByte[i] = (byte) paramArrayOfchar[i];
                i++;
                continue;
            }
            return arrayOfByte1;
        }
    }

    public static int dp2px(float paramFloat) {
        return (int) (paramFloat * (Utils.getContext().getResources().getDisplayMetrics()).density + 0.5F);
    }

    public static Bitmap drawable2Bitmap(Drawable paramDrawable) {
        return (paramDrawable == null) ? null : ((BitmapDrawable) paramDrawable).getBitmap();
    }

    public static byte[] drawable2Bytes(Drawable paramDrawable, Bitmap.CompressFormat paramCompressFormat) {
        return (paramDrawable == null) ? null : bitmap2Bytes(drawable2Bitmap(paramDrawable), paramCompressFormat);
    }

    private static int hex2Dec(char paramChar) {
        if (paramChar >= '0' && paramChar <= '9')
            return paramChar - 48;
        if (paramChar >= 'A' && paramChar <= 'F')
            return paramChar - 65 + 10;
        throw new IllegalArgumentException();
    }

    public static byte[] hexString2Bytes(String paramString) {
        if (StringUtils.isSpace(paramString))
            return null;
        int j = paramString.length();
        int i = j;
        String str = paramString;
        if (j % 2 != 0) {
            str = "0" + paramString;
            i = j + 1;
        }
        char[] arrayOfChar = str.toUpperCase().toCharArray();
        byte[] arrayOfByte = new byte[i >> 1];
        j = 0;
        while (true) {
            byte[] arrayOfByte1 = arrayOfByte;
            if (j < i) {
                arrayOfByte[j >> 1] = (byte) (hex2Dec(arrayOfChar[j]) << 4 | hex2Dec(arrayOfChar[j + 1]));
                j += 2;
                continue;
            }
            return arrayOfByte1;
        }
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream paramInputStream) {
        if (paramInputStream == null)
            return null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] arrayOfByte = new byte[1024];
            while (true) {
                int i = paramInputStream.read(arrayOfByte, 0, 1024);
                if (i != -1) {
                    byteArrayOutputStream.write(arrayOfByte, 0, i);
                    continue;
                }
                return byteArrayOutputStream;
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(new Closeable[]{paramInputStream});
        }
    }

    public static byte[] inputStream2Bytes(InputStream paramInputStream) {
        return (paramInputStream == null) ? null : input2OutputStream(paramInputStream).toByteArray();
    }

    public static String inputStream2String(InputStream paramInputStream, String paramString) {
        if (paramInputStream == null || StringUtils.isSpace(paramString))
            return null;
        try {
            return new String(inputStream2Bytes(paramInputStream), paramString);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
            return null;
        }
    }

    public static long memorySize2Byte(long paramLong, ConstUtils.MemoryUnit paramMemoryUnit) {
//        if (paramLong < 0L)
//            return -1L;
//        switch (paramMemoryUnit) {
//            default:
//                return paramLong;
//            case SEC:
//                return paramLong * 1024L;
//            case MIN:
//                return paramLong * 1048576L;
//            case HOUR:
//                break;
//        }
        return paramLong * 1073741824L;
    }

    @SuppressLint({"DefaultLocale"})
    public static String millis2FitTimeSpan(long paramLong, int paramInt) {
        if (paramLong <= 0L || paramInt <= 0)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        int[] arrayOfInt = new int[5];
        arrayOfInt[0] = 86400000;
        arrayOfInt[1] = 3600000;
        arrayOfInt[2] = 60000;
        arrayOfInt[3] = 1000;
        arrayOfInt[4] = 1;
        int i = Math.min(paramInt, 5);
        paramInt = 0;
        while (paramInt < i) {
            long l = paramLong;
            if (paramLong >= arrayOfInt[paramInt]) {
                long l1 = paramLong / arrayOfInt[paramInt];
                l = paramLong - arrayOfInt[paramInt] * l1;
                (new String[5])[0] = "天";
                (new String[5])[1] = "小时";
                (new String[5])[2] = "分钟";
                (new String[5])[3] = "秒";
                (new String[5])[4] = "毫秒";
                stringBuilder.append(l1).append((new String[5])[paramInt]);
            }
            paramInt++;
            paramLong = l;
        }
        return stringBuilder.toString();
    }

    public static long millis2TimeSpan(long paramLong, ConstUtils.TimeUnit paramTimeUnit) {
        switch (paramTimeUnit) {
            default:
                return paramLong;
            case SEC:
                return paramLong / 1000L;
            case MIN:
                return paramLong / 60000L;
            case HOUR:
                return paramLong / 3600000L;
            case DAY:
                break;
        }
        return paramLong / 86400000L;
    }

    public static byte[] outputStream2Bytes(OutputStream paramOutputStream) {
        return (paramOutputStream == null) ? null : ((ByteArrayOutputStream) paramOutputStream).toByteArray();
    }

    public static String outputStream2String(OutputStream paramOutputStream, String paramString) {
        if (paramOutputStream == null || StringUtils.isSpace(paramString))
            return null;
        try {
            return new String(outputStream2Bytes(paramOutputStream), paramString);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
            return null;
        }
    }

    public static int px2dp(float paramFloat) {
        return (int) (paramFloat / (Utils.getContext().getResources().getDisplayMetrics()).density + 0.5F);
    }

    public static int px2sp(float paramFloat) {
        return (int) (paramFloat / (Utils.getContext().getResources().getDisplayMetrics()).scaledDensity + 0.5F);
    }

    public static int sp2px(float paramFloat) {
        return (int) (paramFloat * (Utils.getContext().getResources().getDisplayMetrics()).scaledDensity + 0.5F);
    }

    public static InputStream string2InputStream(String paramString1, String paramString2) {
        if (paramString1 == null || StringUtils.isSpace(paramString2))
            return null;
        try {
            return new ByteArrayInputStream(paramString1.getBytes(paramString2));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
            return null;
        }
    }

    public static OutputStream string2OutputStream(String paramString1, String paramString2) {
        if (paramString1 == null || StringUtils.isSpace(paramString2))
            return null;
        try {
            return bytes2OutputStream(paramString1.getBytes(paramString2));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
            return null;
        }
    }

    public static long timeSpan2Millis(long paramLong, ConstUtils.TimeUnit paramTimeUnit) {
        switch (paramTimeUnit) {
            default:
                return paramLong;
            case SEC:
                return paramLong * 1000L;
            case MIN:
                return paramLong * 60000L;
            case HOUR:
                return paramLong * 3600000L;
            case DAY:
                break;
        }
        return paramLong * 86400000L;
    }

    public static Bitmap view2Bitmap(View paramView) {
        if (paramView == null)
            return null;
        Bitmap bitmap = Bitmap.createBitmap(paramView.getWidth(), paramView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable drawable = paramView.getBackground();
        if (drawable != null) {
            drawable.draw(canvas);
            paramView.draw(canvas);
            return bitmap;
        }
        canvas.drawColor(-1);
        paramView.draw(canvas);
        return bitmap;
    }

    public ByteArrayInputStream output2InputStream(OutputStream paramOutputStream) {
        return (paramOutputStream == null) ? null : new ByteArrayInputStream(((ByteArrayOutputStream) paramOutputStream).toByteArray());
    }
}
