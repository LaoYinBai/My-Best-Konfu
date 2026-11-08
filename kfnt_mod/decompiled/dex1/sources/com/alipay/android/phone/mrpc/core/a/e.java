package com.alipay.android.phone.mrpc.core.a;

import com.alipay.android.phone.mrpc.core.RpcException;
import java.util.ArrayList;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e extends b {

    /* renamed from: c, reason: collision with root package name */
    private int f2438c;
    private Object d;

    public e(int i, String str, Object obj) {
        super(str, obj);
        this.f2438c = i;
    }

    @Override // com.alipay.android.phone.mrpc.core.a.f
    public final void a(Object obj) {
        this.d = obj;
    }

    @Override // com.alipay.android.phone.mrpc.core.a.f
    public final byte[] a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.d != null) {
                arrayList.add(new BasicNameValuePair("extParam", com.alipay.a.a.f.a(this.d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.f2436a));
            StringBuilder sb = new StringBuilder();
            sb.append(this.f2438c);
            arrayList.add(new BasicNameValuePair("id", sb.toString()));
            new StringBuilder("mParams is:").append(this.f2437b);
            arrayList.add(new BasicNameValuePair("requestData", this.f2437b == null ? "[]" : com.alipay.a.a.f.a(this.f2437b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(this.f2437b);
            sb2.append(":");
            sb2.append(e);
            throw new RpcException(9, sb2.toString() == null ? "" : e.getMessage(), e);
        }
    }
}
