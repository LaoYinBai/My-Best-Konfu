package com.baidu.mobads.sdk.api;

import com.baidu.mobads.sdk.internal.ae;
import com.baidu.mobads.sdk.internal.c;
import com.baidu.mobads.sdk.internal.d;
import com.baidu.mobads.sdk.internal.m;
import dalvik.system.DexClassLoader;

@Route(path = c.a.f3200a)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdservRemoteLoaderImpl implements m {
    @Override // com.baidu.mobads.sdk.internal.m
    public void startLoadRemotePhp(double d, ae.b bVar) {
        d.a().a(d, bVar);
    }

    @Override // com.baidu.mobads.sdk.internal.m
    public DexClassLoader getClassLoaderFromJar(String str, String str2, String str3, ClassLoader classLoader) {
        return d.a().a(str, str2, str3, classLoader);
    }
}
