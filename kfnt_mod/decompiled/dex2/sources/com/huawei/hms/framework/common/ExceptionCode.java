package com.huawei.hms.framework.common;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ExceptionCode {
    public static final int CANCEL = 1104;
    private static final String CONNECT = "connect";
    public static final int CONNECTION_ABORT = 110205;
    public static final int CONNECTION_REFUSED = 110209;
    public static final int CONNECTION_RESET = 110204;
    public static final int CONNECT_FAILED = 110206;
    public static final int CRASH_EXCEPTION = 1103;
    public static final int INTERRUPT_CONNECT_CLOSE = 110214;
    public static final int INTERRUPT_EXCEPTION = 110213;
    public static final int NETWORK_IO_EXCEPTION = 1102;
    public static final int NETWORK_UNREACHABLE = 110208;
    private static final String READ = "read";
    public static final int READ_ERROR = 110203;
    public static final int ROUTE_FAILED = 110207;
    public static final int SOCKET_CLOSE = 110215;
    public static final int SOCKET_CONNECT_TIMEOUT = 110221;
    public static final int SOCKET_READ_TIMEOUT = 110223;
    public static final int SOCKET_TIMEOUT = 110200;
    public static final int SOCKET_WRITE_TIMEOUT = 110225;
    public static final int SSL_HANDSHAKE_EXCEPTION = 110211;
    public static final int SSL_PEERUNVERIFIED_EXCEPTION = 110212;
    public static final int SSL_PROTOCOL_EXCEPTION = 110210;
    public static final int UNABLE_TO_RESOLVE_HOST = 110202;
    public static final int UNEXPECTED_EOF = 110201;
    private static final String WRITE = "write";

    private static String checkExceptionContainsKey(Exception exc, String... strArr) {
        String strCheckStrContainsKey = checkStrContainsKey(StringUtils.toLowerCase(exc.getMessage()), strArr);
        if (!TextUtils.isEmpty(strCheckStrContainsKey)) {
            return strCheckStrContainsKey;
        }
        for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
            strCheckStrContainsKey = checkStrContainsKey(StringUtils.toLowerCase(stackTraceElement.toString()), strArr);
            if (!TextUtils.isEmpty(strCheckStrContainsKey)) {
                return strCheckStrContainsKey;
            }
        }
        return strCheckStrContainsKey;
    }

    private static String checkStrContainsKey(String str, String... strArr) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return str2;
            }
        }
        return "";
    }

    public static int getErrorCodeFromException(Exception exc) {
        if (exc == null) {
            return NETWORK_IO_EXCEPTION;
        }
        if (!(exc instanceof IOException)) {
            return CRASH_EXCEPTION;
        }
        String message = exc.getMessage();
        if (message == null) {
            return NETWORK_IO_EXCEPTION;
        }
        String lowerCase = StringUtils.toLowerCase(message);
        int errorCodeFromMsg = getErrorCodeFromMsg(lowerCase);
        return errorCodeFromMsg != 1102 ? errorCodeFromMsg : exc instanceof SocketTimeoutException ? getErrorCodeSocketTimeout(exc) : exc instanceof ConnectException ? CONNECT_FAILED : exc instanceof NoRouteToHostException ? ROUTE_FAILED : exc instanceof SSLProtocolException ? SSL_PROTOCOL_EXCEPTION : exc instanceof SSLHandshakeException ? SSL_HANDSHAKE_EXCEPTION : exc instanceof SSLPeerUnverifiedException ? SSL_PEERUNVERIFIED_EXCEPTION : exc instanceof UnknownHostException ? UNABLE_TO_RESOLVE_HOST : exc instanceof InterruptedIOException ? lowerCase.contains("connection has been shut down") ? INTERRUPT_CONNECT_CLOSE : INTERRUPT_EXCEPTION : errorCodeFromMsg;
    }

    private static int getErrorCodeFromMsg(String str) {
        return str.contains("unexpected end of stream") ? UNEXPECTED_EOF : str.contains("unable to resolve host") ? UNABLE_TO_RESOLVE_HOST : str.contains("read error") ? READ_ERROR : str.contains("connection reset") ? CONNECTION_RESET : str.contains("software caused connection abort") ? CONNECTION_ABORT : str.contains("failed to connect to") ? CONNECT_FAILED : str.contains("connection refused") ? CONNECTION_REFUSED : str.contains("connection timed out") ? SOCKET_CONNECT_TIMEOUT : str.contains("no route to host") ? ROUTE_FAILED : str.contains("network is unreachable") ? NETWORK_UNREACHABLE : str.contains("socket closed") ? SOCKET_CLOSE : NETWORK_IO_EXCEPTION;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int getErrorCodeSocketTimeout(java.lang.Exception r3) {
        /*
            java.lang.String r0 = "connect"
            java.lang.String r1 = "read"
            java.lang.String r2 = "write"
            java.lang.String[] r0 = new java.lang.String[]{r0, r1, r2}
            java.lang.String r3 = checkExceptionContainsKey(r3, r0)
            int r0 = r3.hashCode()
            r1 = 3496342(0x355996, float:4.899419E-39)
            if (r0 == r1) goto L36
            r1 = 113399775(0x6c257df, float:7.3103804E-35)
            if (r0 == r1) goto L2c
            r1 = 951351530(0x38b478ea, float:8.605591E-5)
            if (r0 == r1) goto L22
            goto L40
        L22:
            java.lang.String r0 = "connect"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L40
            r3 = 0
            goto L41
        L2c:
            java.lang.String r0 = "write"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L40
            r3 = 2
            goto L41
        L36:
            java.lang.String r0 = "read"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L40
            r3 = 1
            goto L41
        L40:
            r3 = -1
        L41:
            switch(r3) {
                case 0: goto L50;
                case 1: goto L4c;
                case 2: goto L48;
                default: goto L44;
            }
        L44:
            r3 = 110200(0x1ae78, float:1.54423E-40)
            return r3
        L48:
            r3 = 110225(0x1ae91, float:1.54458E-40)
            return r3
        L4c:
            r3 = 110223(0x1ae8f, float:1.54455E-40)
            return r3
        L50:
            r3 = 110221(0x1ae8d, float:1.54453E-40)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.common.ExceptionCode.getErrorCodeSocketTimeout(java.lang.Exception):int");
    }
}
