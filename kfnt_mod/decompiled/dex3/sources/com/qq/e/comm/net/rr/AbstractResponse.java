package com.qq.e.comm.net.rr;

import com.ss.union.game.sdk.feedback.picture.select.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class AbstractResponse implements Response {
    private final HttpURLConnection a;
    private int b;

    public AbstractResponse(HttpURLConnection httpURLConnection) throws IOException {
        this.b = 0;
        if (httpURLConnection == null) {
            throw new IOException("AbstractResponse parameter is null");
        }
        this.a = httpURLConnection;
        this.b = httpURLConnection.getResponseCode();
    }

    @Override // com.qq.e.comm.net.rr.Response
    public void close() throws IllegalStateException, IOException {
        this.a.disconnect();
    }

    @Override // com.qq.e.comm.net.rr.Response
    public byte[] getBytesContent() throws IllegalStateException, IOException {
        if (200 != getStatusCode()) {
            return null;
        }
        InputStream streamContent = getStreamContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[d.d];
        while (true) {
            int i = streamContent.read(bArr);
            if (i <= 0) {
                streamContent.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    @Override // com.qq.e.comm.net.rr.Response
    public int getStatusCode() {
        return this.b;
    }

    @Override // com.qq.e.comm.net.rr.Response
    public InputStream getStreamContent() throws IllegalStateException, IOException {
        return this.a.getInputStream();
    }

    @Override // com.qq.e.comm.net.rr.Response
    public String getStringContent() throws IOException {
        return getStringContent("UTF-8");
    }

    @Override // com.qq.e.comm.net.rr.Response
    public String getStringContent(String str) throws IllegalStateException, IOException {
        byte[] bytesContent = getBytesContent();
        String contentEncoding = null;
        if (bytesContent == null) {
            return null;
        }
        if (bytesContent.length == 0) {
            return "";
        }
        try {
            contentEncoding = this.a.getContentEncoding();
        } catch (Throwable unused) {
        }
        if (contentEncoding != null) {
            str = contentEncoding;
        }
        return new String(bytesContent, str);
    }
}
