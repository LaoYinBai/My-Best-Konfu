package com.qq.e.comm.net.rr;

import com.qq.e.comm.net.rr.Request;
import java.io.IOException;
import java.net.HttpURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class S2SSRequest extends AbstractRequest {
    public S2SSRequest(String str, byte[] bArr) {
        super(str, Request.Method.POST, bArr);
    }

    @Override // com.qq.e.comm.net.rr.AbstractRequest, com.qq.e.comm.net.rr.Request
    public byte[] getPostData() throws Exception {
        return a.c(super.getPostData());
    }

    @Override // com.qq.e.comm.net.rr.Request
    public Response initResponse(HttpURLConnection httpURLConnection) throws IOException {
        return new S2SSResponse(httpURLConnection);
    }
}
