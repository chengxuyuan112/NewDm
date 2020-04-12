package com.xteamsoft.baselibrary.utils;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
    private CloseUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void closeIO(Closeable... paramVarArgs) {
        if (paramVarArgs != null) {
            int j = paramVarArgs.length;
            int i = 0;
            while (true) {
                if (i < j) {
                    Closeable closeable = paramVarArgs[i];
                    if (closeable != null)
                        try {
                            closeable.close();
                        } catch (IOException iOException) {
                            iOException.printStackTrace();
                        }
                    i++;
                    continue;
                }
                return;
            }
        }
    }

    public static void closeIOQuietly(Closeable... paramVarArgs) {
        if (paramVarArgs != null) {
            int j = paramVarArgs.length;
            int i = 0;
            while (true) {
                if (i < j) {
                    Closeable closeable = paramVarArgs[i];
                    if (closeable != null)
                        try {
                            closeable.close();
                        } catch (IOException iOException) {}
                    i++;
                    continue;
                }
                return;
            }
        }
    }
}
