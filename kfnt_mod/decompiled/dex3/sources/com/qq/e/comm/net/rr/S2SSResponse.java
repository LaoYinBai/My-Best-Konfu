package com.qq.e.comm.net.rr;

import com.qq.e.comm.net.rr.a;
import java.io.IOException;
import java.net.HttpURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class S2SSResponse extends AbstractResponse {
    public S2SSResponse(HttpURLConnection httpURLConnection) throws IOException {
        super(httpURLConnection);
    }

    @Override // com.qq.e.comm.net.rr.AbstractResponse, com.qq.e.comm.net.rr.Response
    public byte[] getBytesContent() throws IllegalStateException, IOException {
        try {
            return a.d(super.getBytesContent());
        } catch (a.b e) {
            e.printStackTrace();
            return null;
        }
    }
}
