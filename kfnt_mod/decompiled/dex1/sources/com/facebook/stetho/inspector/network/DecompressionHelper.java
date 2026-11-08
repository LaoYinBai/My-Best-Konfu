package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.protocol.module.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DecompressionHelper {
    static final String DEFLATE_ENCODING = "deflate";
    static final String GZIP_ENCODING = "gzip";

    public static InputStream teeInputWithDecompression(NetworkPeerManager networkPeerManager, String str, InputStream inputStream, OutputStream outputStream, @Nullable String str2, ResponseHandler responseHandler) throws IOException {
        CountingOutputStream countingOutputStream;
        if (str2 != null) {
            boolean zEquals = "gzip".equals(str2);
            boolean zEquals2 = DEFLATE_ENCODING.equals(str2);
            if (zEquals || zEquals2) {
                countingOutputStream = new CountingOutputStream(outputStream);
                if (zEquals) {
                    outputStream = GunzippingOutputStream.create(countingOutputStream);
                } else if (zEquals2) {
                    outputStream = new InflaterOutputStream(countingOutputStream);
                }
            } else {
                CLog.writeToConsole(networkPeerManager, Console.MessageLevel.WARNING, Console.MessageSource.NETWORK, "Unsupported Content-Encoding in response for request #" + str + ": " + str2);
                countingOutputStream = null;
            }
        } else {
            countingOutputStream = null;
        }
        return new ResponseHandlingInputStream(inputStream, str, outputStream, countingOutputStream, networkPeerManager, responseHandler);
    }
}
