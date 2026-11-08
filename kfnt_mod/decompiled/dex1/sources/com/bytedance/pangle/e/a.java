package com.bytedance.pangle.e;

import com.bytedance.pangle.c;
import com.bytedance.pangle.plugin.PluginManager;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends c.a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f4585a;

    public static a b() {
        if (f4585a == null) {
            synchronized (a.class) {
                if (f4585a == null) {
                    f4585a = new a();
                }
            }
        }
        return f4585a;
    }

    @Override // com.bytedance.pangle.c
    public final boolean a(String str) {
        return PluginManager.getInstance().checkPluginInstalled(str);
    }

    @Override // com.bytedance.pangle.c
    public final int b(String str) {
        return PluginManager.getInstance().getPlugin(str).getVersion();
    }

    @Override // com.bytedance.pangle.c
    public final boolean c(String str) {
        return PluginManager.getInstance().syncInstall(new File(str));
    }
}
