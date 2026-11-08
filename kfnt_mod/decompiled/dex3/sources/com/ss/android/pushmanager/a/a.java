package com.ss.android.pushmanager.a;

import android.content.Context;
import android.os.Bundle;
import com.ss.android.pushmanager.IMessageContext;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {

    /* renamed from: com.ss.android.pushmanager.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0036a extends com.ss.android.b.a.a {
        void a(Context context);
    }

    public interface b extends com.ss.android.b.a.a {
        IMessageContext a();

        void a(Context context, String str, String str2, String str3, long j, long j2, JSONObject jSONObject);

        void a(String str, Bundle bundle);

        String b();

        String c();

        String d();

        boolean e();

        Map<String, String> f();

        boolean g();
    }

    public static InterfaceC0036a a() {
        return (InterfaceC0036a) com.ss.android.b.a.b.a(InterfaceC0036a.class);
    }

    public static b b() {
        return (b) com.ss.android.b.a.b.a(b.class);
    }
}
