package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.NativeResponse;
import com.baidu.mobads.sdk.api.RequestParameters;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private co f3225a;

    /* renamed from: b, reason: collision with root package name */
    private String f3226b;

    public interface a {
        void a();

        void a(int i, String str);

        void a(NativeResponse nativeResponse);

        void a(NativeResponse nativeResponse, int i);

        void a(List<NativeResponse> list);

        void b();

        void b(int i, String str);

        void b(NativeResponse nativeResponse);

        void c();
    }

    public interface b {
        void a(NativeResponse nativeResponse);
    }

    public e(Context context, String str, a aVar, boolean z, int i) {
        this(context, aVar, new co(context, str, IAdInterListener.AdProdType.PRODUCT_FEEDS, z, i));
    }

    public e(Context context, String str, a aVar, boolean z, int i, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (str2.equals(IAdInterListener.AdProdType.PRODUCT_INSITE)) {
                this.f3225a = new co(context, str, IAdInterListener.AdProdType.PRODUCT_INSITE, z, i);
            } else if (str2.equals(IAdInterListener.AdProdType.PRODUCT_SUG)) {
                this.f3225a = new co(context, str, IAdInterListener.AdProdType.PRODUCT_SUG, z, i);
            } else if (str2.equals(IAdInterListener.AdProdType.PRODUCT_PREROLL)) {
                this.f3225a = new co(context, str, "video", z, i);
            } else {
                this.f3225a = new co(context, str, IAdInterListener.AdProdType.PRODUCT_FEEDS, z, i);
            }
        }
        this.f3225a.a(aVar);
    }

    public e(Context context, a aVar, co coVar) {
        this.f3225a = coVar;
        this.f3225a.a(aVar);
    }

    public void a() {
        if (this.f3225a != null) {
            this.f3225a.d();
        }
    }

    public void a(b bVar) {
        if (this.f3225a != null) {
            this.f3225a.a(bVar);
        }
    }

    public void a(boolean z) {
        if (this.f3225a != null) {
            this.f3225a.c(z);
        }
    }

    public String a(RequestParameters requestParameters) {
        if (this.f3225a == null) {
            return null;
        }
        if (requestParameters == null) {
            requestParameters = new RequestParameters.Builder().build();
        }
        this.f3225a.f(this.f3226b);
        this.f3225a.a(requestParameters);
        return this.f3225a.m();
    }

    public void a(String str) {
        if (this.f3225a != null) {
            this.f3225a.b(str);
        }
    }

    public void b() {
        b((RequestParameters) null);
    }

    public void b(RequestParameters requestParameters) {
        if (requestParameters == null) {
            requestParameters = new RequestParameters.Builder().build();
        }
        if (this.f3225a != null) {
            this.f3225a.f(this.f3226b);
        }
        this.f3225a.a(requestParameters);
        this.f3225a.b_();
    }

    public void b(String str) {
        this.f3226b = str;
    }
}
