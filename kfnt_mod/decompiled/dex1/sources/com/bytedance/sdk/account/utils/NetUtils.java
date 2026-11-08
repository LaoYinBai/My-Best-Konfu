package com.bytedance.sdk.account.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bytedance.sdk.account.INetWork;
import com.bytedance.sdk.account.utility.Logger;
import com.ss.android.account.TTAccountInit;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.conn.ConnectTimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class NetUtils {
    public static final int SC_BIND_EXCEPTION = 7;
    public static final int SC_CLIENT_PROTOCOL_EXCEPTION = 19;
    public static final int SC_CONNECT_EXCEPTION = 8;
    public static final int SC_CONNECT_TIMEOUT = 2;
    public static final int SC_EACCES = 36;
    public static final int SC_EADDRINUSE = 17;
    public static final int SC_EADDRNOTAVAIL = 16;
    public static final int SC_ECONNREFUSED = 13;
    public static final int SC_ECONNRESET = 12;
    public static final int SC_EDQUOT = 34;
    public static final int SC_EHOSTUNREACH = 14;
    public static final int SC_EIO = 37;
    public static final int SC_ENETUNREACH = 15;
    public static final int SC_ENOENT = 33;
    public static final int SC_EROFS = 35;
    public static final int SC_FILE_TOO_LARGE = 20;
    public static final int SC_IO_EXCEPTION = 4;
    public static final int SC_NO_HTTP_RESPONSE = 18;
    public static final int SC_NO_REOUTE_TO_HOST = 9;
    public static final int SC_NO_SPACE = 32;
    public static final int SC_PORT_UNREACHABLE = 10;
    public static final int SC_RESET_BY_PEER = 6;
    public static final int SC_SOCKET_EXCEPTION = 5;
    public static final int SC_SOCKET_TIMEOUT = 3;
    public static final int SC_UNKNOWN = 1;
    public static final int SC_UNKNOWN_HOST = 11;
    public static final String TAG = "NetUtils";

    public static int checkApiException(Context context, Throwable th) {
        int i;
        int iCheckResponseException;
        INetWork network = TTAccountInit.getConfig().getNetwork();
        if (network != null && (iCheckResponseException = network.checkResponseException(context, th)) < 0) {
            return iCheckResponseException;
        }
        if (th instanceof ConnectTimeoutException) {
            i = -13;
        } else if (th instanceof SocketTimeoutException) {
            i = -14;
        } else {
            if (th instanceof SocketException) {
                Logger.v(TAG, "api socket exception: " + th);
            } else if (th instanceof SSLPeerUnverifiedException) {
                i = -21;
                Logger.v(TAG, "api ssl exception: " + th);
            } else if (th instanceof IOException) {
                Logger.v(TAG, "api io exception: " + th);
            } else {
                i = -18;
                Logger.w(TAG, "api exception: " + th);
            }
            i = -15;
        }
        if (context == null) {
            return i;
        }
        if ((i == -15 || i == -14) && !isNetworkAvailable(context)) {
            return -12;
        }
        return i;
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
