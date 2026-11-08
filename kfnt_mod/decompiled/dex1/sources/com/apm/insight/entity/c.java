package com.apm.insight.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.h;
import com.bytedance.pangle.provider.ContentProviderManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends a {

    /* renamed from: c, reason: collision with root package name */
    private String f2837c;

    public c(String str) {
        this.f2837c = str;
    }

    @NonNull
    public static c a(@NonNull StackTraceElement stackTraceElement, @NonNull String str, @Nullable String str2, @NonNull String str3, boolean z, String str4, String str5) {
        c cVar = new c(str5);
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();
        cVar.a("event_type", (Object) "exception");
        cVar.a(com.bytedance.b.a.a.d.o, (Object) str5);
        cVar.a("timestamp", Long.valueOf(System.currentTimeMillis()));
        cVar.a("crash_time", Long.valueOf(System.currentTimeMillis()));
        cVar.a("class_ref", (Object) className);
        cVar.a(com.alipay.sdk.packet.e.q, (Object) methodName);
        cVar.a("line_num", Integer.valueOf(lineNumber));
        cVar.a("stack", (Object) str);
        cVar.a("exception_type", (Object) 1);
        cVar.a("ensure_type", (Object) str4);
        cVar.a("is_core", Integer.valueOf(z ? 1 : 0));
        cVar.a("message", (Object) str2);
        cVar.a(ContentProviderManager.PLUGIN_PROCESS_NAME, (Object) com.apm.insight.l.a.c(h.g()));
        cVar.a("crash_thread_name", (Object) str3);
        d.b(cVar.h());
        return cVar;
    }
}
