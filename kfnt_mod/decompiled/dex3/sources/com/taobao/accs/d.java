package com.taobao.accs;

import androidx.annotation.Keep;
import com.taobao.accs.data.Message;
import com.taobao.aranger.annotation.method.oneway;
import com.taobao.aranger.annotation.type.ClassName;

@ClassName("com.taobao.accs.internal.ConnectionServiceImpl")
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface d {
    @oneway
    void a();

    @oneway
    void a(int i);

    void a(AccsClientConfig accsClientConfig);

    @oneway
    void a(Message message);

    @oneway
    void a(Message message, int i);

    @oneway
    void a(Message message, boolean z);

    boolean a(String str);

    boolean a(String str, String str2);

    String b(String str);

    @oneway
    void b();

    String c();

    @oneway
    void c(String str);

    String d();

    @oneway
    void d(String str);

    String e();

    boolean e(String str);

    boolean f();

    boolean f(String str);
}
