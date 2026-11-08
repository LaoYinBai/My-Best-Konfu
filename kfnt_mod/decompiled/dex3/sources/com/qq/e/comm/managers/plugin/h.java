package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.util.SystemUtil;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h {
    private static String a = SystemUtil.buildNewPathByProcessName("e_qq_com_plugin");
    private static String b = SystemUtil.buildNewPathByProcessName("e_qq_com_dex");

    static File a(Context context) {
        return context.getDir(b, 0);
    }

    static File b(Context context) {
        return new File(context.getDir(a, 0), "update_lc");
    }

    static File c(Context context) {
        return new File(context.getDir(a, 0), "gdt_plugin.next");
    }

    static File d(Context context) {
        return new File(context.getDir(a, 0), "gdt_plugin.next.sig");
    }

    static File e(Context context) {
        return new File(context.getDir(a, 0), "gdt_plugin.jar");
    }

    static File f(Context context) {
        return new File(context.getDir(a, 0), "gdt_plugin.jar.sig");
    }

    static File g(Context context) {
        return new File(context.getDir(a, 0), "gdt_plugin.tmp");
    }

    static File h(Context context) {
        return new File(context.getDir(a, 0), "gdt_plugin.tmp.sig");
    }
}
