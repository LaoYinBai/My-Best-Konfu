package com.mbridge.msdk.foundation.same.net.e;

import com.mbridge.msdk.foundation.same.net.e.b;
import com.mbridge.msdk.foundation.same.net.i;
import com.mbridge.msdk.thrid.okhttp.Headers;
import com.mbridge.msdk.thrid.okhttp.MediaType;
import com.mbridge.msdk.thrid.okhttp.OkHttpClient;
import com.mbridge.msdk.thrid.okhttp.Protocol;
import com.mbridge.msdk.thrid.okhttp.Request;
import com.mbridge.msdk.thrid.okhttp.RequestBody;
import com.mbridge.msdk.thrid.okhttp.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: OkHttpStack.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c implements a {
    private final OkHttpClient a = b.a.a.a();

    private static RequestBody b(i iVar) {
        byte[] bArrE = iVar.e();
        if (bArrE == null) {
            return null;
        }
        return RequestBody.create((MediaType) null, bArrE);
    }

    @Override // com.mbridge.msdk.foundation.same.net.e.a
    public final com.mbridge.msdk.foundation.same.net.f.b a(i<?> iVar) throws IOException {
        int iH = iVar.h();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Protocol.HTTP_1_1);
        arrayList.add(Protocol.HTTP_2);
        long j = iH;
        OkHttpClient okHttpClientBuild = this.a.newBuilder().protocols(arrayList).readTimeout(j, TimeUnit.MILLISECONDS).connectTimeout(j, TimeUnit.MILLISECONDS).writeTimeout(j, TimeUnit.MILLISECONDS).build();
        Request.Builder builder = new Request.Builder();
        Map<String, String> mapD = iVar.d();
        for (String str : mapD.keySet()) {
            builder.addHeader(str, mapD.get(str));
        }
        for (Map.Entry<String, String> entry : iVar.d().entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        switch (iVar.a()) {
            case 0:
                builder.get();
                break;
            case 1:
                builder.post(b(iVar));
                break;
            case 2:
                builder.put(b(iVar));
                break;
            case 3:
                builder.delete();
                break;
            case 4:
                builder.head();
                break;
            case 5:
                builder.method("OPTIONS", null);
                break;
            case 6:
                builder.method("TRACE", null);
                break;
            case 7:
                builder.patch(b(iVar));
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        Response responseExecute = okHttpClientBuild.newCall(builder.url(iVar.b()).build()).execute();
        Headers headers = responseExecute.headers();
        ArrayList arrayList2 = new ArrayList();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String strName = headers.name(i);
            String strValue = headers.value(i);
            if (strName != null) {
                arrayList2.add(new com.mbridge.msdk.foundation.same.net.c.b(strName, strValue));
            }
        }
        return new com.mbridge.msdk.foundation.same.net.f.b(responseExecute.code(), arrayList2, responseExecute.body().byteStream());
    }
}
