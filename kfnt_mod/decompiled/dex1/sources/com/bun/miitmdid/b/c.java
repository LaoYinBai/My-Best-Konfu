package com.bun.miitmdid.b;

import androidx.annotation.Keep;
import com.bun.miitmdid.b.b;
import java.util.HashMap;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private Map<String, String> f3279a = new HashMap();

    @Keep
    class a implements b.InterfaceC0091b {
        @Keep
        a(c cVar) {
        }

        @Override // com.bun.miitmdid.b.b.InterfaceC0091b
        @Keep
        public native void a(Exception exc, int i, String str);
    }

    @Keep
    public c() {
    }

    @Keep
    private native Map<String, String> a();

    @Keep
    public native boolean a(boolean z, String str, String str2, String str3, String str4);
}
