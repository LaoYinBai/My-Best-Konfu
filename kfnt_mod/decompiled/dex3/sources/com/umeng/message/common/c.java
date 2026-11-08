package com.umeng.message.common;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final String a = "com.umeng.message.common.c";
    private static c b;
    private static Class e;
    private static Class f;
    private static Class g;
    private static Class h;
    private static Class i;
    private static Class j;
    private static Class k;
    private static Class l;
    private Context c;
    private String d;

    private c(Context context) {
        this.c = context.getApplicationContext();
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "packageName=" + this.c.getPackageName());
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb.append(".R$drawable");
            f = Class.forName(sb.toString());
        } catch (ClassNotFoundException e2) {
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e2.getMessage());
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.aq(k.c, 0, "\\|");
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb2.append(".R$layout");
            g = Class.forName(sb2.toString());
        } catch (ClassNotFoundException e3) {
            UMLog uMLog4 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e3.getMessage());
        }
        try {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb3.append(".R$id");
            e = Class.forName(sb3.toString());
        } catch (ClassNotFoundException e4) {
            UMLog uMLog5 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e4.getMessage());
        }
        try {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb4.append(".R$anim");
            h = Class.forName(sb4.toString());
        } catch (ClassNotFoundException e5) {
            UMLog uMLog6 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e5.getMessage());
        }
        try {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb5.append(".R$style");
            i = Class.forName(sb5.toString());
        } catch (ClassNotFoundException e6) {
            UMLog uMLog7 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e6.getMessage());
        }
        try {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb6.append(".R$string");
            j = Class.forName(sb6.toString());
        } catch (ClassNotFoundException e7) {
            UMLog uMLog8 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e7.getMessage());
        }
        try {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb7.append(".R$array");
            k = Class.forName(sb7.toString());
        } catch (ClassNotFoundException e8) {
            UMLog uMLog9 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e8.getMessage());
        }
        try {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb8.append(".R$raw");
            l = Class.forName(sb8.toString());
        } catch (ClassNotFoundException e9) {
            UMLog uMLog10 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e9.getMessage());
        }
    }

    public static c a(Context context) {
        if (b == null) {
            b = new c(context);
        }
        return b;
    }

    public int a(String str) {
        return a(h, str);
    }

    public int b(String str) {
        return a(e, str);
    }

    public int c(String str) throws Exception {
        return b(e, str);
    }

    public int d(String str) {
        return a(f, str);
    }

    public int e(String str) {
        return a(g, str);
    }

    public int f(String str) throws Exception {
        return b(g, str);
    }

    public int g(String str) {
        return a(i, str);
    }

    public int h(String str) {
        return a(j, str);
    }

    public int i(String str) {
        return a(k, str);
    }

    public int j(String str) {
        return a(l, str);
    }

    private int a(Class<?> cls, String str) {
        if (cls == null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "getRes(null," + str + l.t);
            throw new IllegalArgumentException("ResClass未初始化，请确保你已经添加了必要的资源。同时确保你在混淆文件中添加了" + this.c.getPackageName() + ".R$* 。 field=" + str);
        }
        try {
            return cls.getField(str).getInt(str);
        } catch (Exception e2) {
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "getRes(" + cls.getName() + ", " + str + l.t);
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "获取资源错误，确保你已经把res目录下的所有资源从SDK拷贝到了你的主工程");
            UMLog uMLog4 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, e2.getMessage());
            return -1;
        }
    }

    private int b(Class<?> cls, String str) throws Exception {
        if (cls == null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "getRes(null," + str + l.t);
            throw new IllegalArgumentException("ResClass未初始化，请确保你已经添加了必要的资源。同时确保你在混淆文件中添加了" + this.c.getPackageName() + ".R$* 。 field=" + str);
        }
        int i2 = cls.getField(str).getInt(str);
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, "getRes(" + cls.getName() + ", " + str + l.t);
        UMLog uMLog3 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, "获取资源错误，确保你已经把res目录下的所有资源从SDK拷贝到了你的主工程");
        return i2;
    }

    public void k(String str) {
        this.d = str;
    }

    public String a() {
        if (TextUtils.isEmpty(this.d)) {
            return this.c.getPackageName();
        }
        return this.d;
    }
}
