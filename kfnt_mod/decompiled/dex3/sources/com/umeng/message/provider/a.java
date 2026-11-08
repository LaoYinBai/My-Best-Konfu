package com.umeng.message.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import com.umeng.message.common.UmengMessageDeviceConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static String a;
    public static Uri b;
    public static Uri c;
    public static Uri d;
    public static Uri e;
    public static Uri f;
    public static Uri g;
    public static Uri h;
    public static Uri i;
    public static Uri j;
    public static Uri k;
    private static Context l;
    private static a m;
    private static String n;

    private a() {
    }

    public static a a(Context context) {
        l = context;
        if (m == null) {
            m = new a();
            n = UmengMessageDeviceConfig.getPackageName(context);
            a = n + ".umeng.message";
            b = Uri.parse("content://" + a + C0151a.a);
            c = Uri.parse("content://" + a + C0151a.b);
            d = Uri.parse("content://" + a + C0151a.c);
            e = Uri.parse("content://" + a + C0151a.d);
            f = Uri.parse("content://" + a + C0151a.e);
            g = Uri.parse("content://" + a + C0151a.f);
            h = Uri.parse("content://" + a + C0151a.g);
            i = Uri.parse("content://" + a + C0151a.h);
            j = Uri.parse("content://" + a + C0151a.i);
            k = Uri.parse("content://" + a + C0151a.j);
        }
        return m;
    }

    /* renamed from: com.umeng.message.provider.a$a, reason: collision with other inner class name */
    public static class C0151a implements BaseColumns {
        public static final String a = "/MessageStores/";
        public static final String b = "/MsgTemps/";
        public static final String c = "/MsgAlias/";
        public static final String d = "/MsgAliasDeleteAll/";
        public static final String e = "/MsgLogStores/";
        public static final String f = "/MsgLogIdTypeStores/";
        public static final String g = "/MsgLogStoreForAgoos/";
        public static final String h = "/MsgLogIdTypeStoreForAgoos/";
        public static final String i = "/MsgConfigInfos/";
        public static final String j = "/InAppLogStores/";
        public static final String k = "vnd.android.cursor.dir/vnd.umeng.message";
        public static final String l = "vnd.android.cursor.item/vnd.umeng.message";
        private static final String m = "content://";

        private C0151a() {
        }
    }
}
