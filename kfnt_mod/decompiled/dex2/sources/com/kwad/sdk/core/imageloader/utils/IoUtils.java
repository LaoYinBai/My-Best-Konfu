package com.kwad.sdk.core.imageloader.utils;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class IoUtils {
    public static final int CONTINUE_LOADING_PERCENTAGE = 75;
    public static final int DEFAULT_BUFFER_SIZE = 32768;
    public static final int DEFAULT_IMAGE_TOTAL_SIZE = 512000;

    public interface CopyListener {
        boolean onBytesCopied(int i, int i2);
    }

    private IoUtils() {
    }

    public static void closeSilently(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean copyStream(InputStream inputStream, OutputStream outputStream, CopyListener copyListener) {
        return copyStream(inputStream, outputStream, copyListener, 32768);
    }

    public static boolean copyStream(InputStream inputStream, OutputStream outputStream, CopyListener copyListener, int i) throws IOException {
        int iAvailable = inputStream.available();
        if (iAvailable <= 0) {
            iAvailable = DEFAULT_IMAGE_TOTAL_SIZE;
        }
        byte[] bArr = new byte[i];
        if (shouldStopLoading(copyListener, 0, iAvailable)) {
            return false;
        }
        int i2 = 0;
        do {
            int i3 = inputStream.read(bArr, 0, i);
            if (i3 == -1) {
                outputStream.flush();
                return true;
            }
            outputStream.write(bArr, 0, i3);
            i2 += i3;
        } while (!shouldStopLoading(copyListener, i2, iAvailable));
        return false;
    }

    public static String inputStreamToString(InputStream inputStream) throws Throwable {
        InputStreamReader inputStreamReader;
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader2 = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, C.UTF8_NAME);
            while (true) {
                try {
                    int i = inputStreamReader.read(cArr, 0, cArr.length);
                    if (i < 0) {
                        closeSilently(inputStreamReader);
                        return sb.toString();
                    }
                    sb.append(cArr, 0, i);
                } catch (Exception unused) {
                    if (inputStreamReader != null) {
                        closeSilently(inputStreamReader);
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader2 = inputStreamReader;
                    if (inputStreamReader2 != null) {
                        closeSilently(inputStreamReader2);
                    }
                    throw th;
                }
            }
        } catch (Exception unused2) {
            inputStreamReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void readAndCloseStream(InputStream inputStream) throws IOException {
        do {
            try {
            } catch (IOException unused) {
            } catch (Throwable th) {
                closeSilently(inputStream);
                throw th;
            }
        } while (inputStream.read(new byte[32768], 0, 32768) != -1);
        closeSilently(inputStream);
    }

    private static boolean shouldStopLoading(CopyListener copyListener, int i, int i2) {
        return (copyListener == null || copyListener.onBytesCopied(i, i2) || (i * 100) / i2 >= 75) ? false : true;
    }
}
