package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tachikoma.core.utility.UriUtil;
import com.xiaomi.push.fq;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class m {
    private static l a;

    /* renamed from: a, reason: collision with other field name */
    private static a f961a;

    public interface a {
        void a();
    }

    public static synchronized l a(Context context) {
        if (a != null) {
            return a;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
        String string = sharedPreferences.getString("uuid", null);
        String string2 = sharedPreferences.getString("token", null);
        String string3 = sharedPreferences.getString("security", null);
        String string4 = sharedPreferences.getString("app_id", null);
        String string5 = sharedPreferences.getString("app_token", null);
        String string6 = sharedPreferences.getString("package_name", null);
        String string7 = sharedPreferences.getString("device_id", null);
        int i = sharedPreferences.getInt("env_type", 1);
        if (!TextUtils.isEmpty(string7) && com.xiaomi.push.i.m436a(string7)) {
            string7 = com.xiaomi.push.i.k(context);
            sharedPreferences.edit().putString("device_id", string7).commit();
        }
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return null;
        }
        String strK = com.xiaomi.push.i.k(context);
        if (!"com.xiaomi.xmsf".equals(context.getPackageName()) && !TextUtils.isEmpty(strK) && !TextUtils.isEmpty(string7) && !string7.equals(strK)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("read_phone_state permission changes.");
        }
        a = new l(string, string2, string3, string4, string5, string6, i);
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0077 A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0086 A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c4 A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f4 A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0165 A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01a4 A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ae A[Catch: all -> 0x0231, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x003b, B:8:0x0045, B:10:0x005b, B:12:0x0066, B:14:0x0077, B:15:0x007c, B:17:0x0086, B:18:0x008b, B:21:0x0093, B:24:0x009c, B:27:0x00a5, B:28:0x00b0, B:34:0x00c4, B:36:0x00cd, B:38:0x00f4, B:40:0x0100, B:41:0x0113, B:43:0x011d, B:45:0x0123, B:46:0x0137, B:48:0x013d, B:49:0x0142, B:51:0x0165, B:52:0x016e, B:54:0x01a4, B:55:0x01a8, B:57:0x01ae, B:59:0x01bb, B:61:0x01d9, B:62:0x01ef, B:65:0x021d, B:31:0x00bc), top: B:71:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized com.xiaomi.push.service.l a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.m.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String):com.xiaomi.push.service.l");
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m637a(Context context) {
        StringBuilder sb;
        String str;
        String strA = com.xiaomi.push.service.a.a(context).a();
        if (com.xiaomi.push.ab.b()) {
            sb = new StringBuilder();
            sb.append(UriUtil.HTTP_PREFIX);
            sb.append(fq.b);
            str = ":9085";
        } else if (com.xiaomi.push.o.China.name().equals(strA)) {
            sb = new StringBuilder();
            str = "https://cn.register.xmpush.xiaomi.com";
        } else if (com.xiaomi.push.o.Global.name().equals(strA)) {
            sb = new StringBuilder();
            str = "https://register.xmpush.global.xiaomi.com";
        } else if (com.xiaomi.push.o.Europe.name().equals(strA)) {
            sb = new StringBuilder();
            str = "https://fr.register.xmpush.global.xiaomi.com";
        } else if (com.xiaomi.push.o.Russia.name().equals(strA)) {
            sb = new StringBuilder();
            str = "https://ru.register.xmpush.global.xiaomi.com";
        } else if (com.xiaomi.push.o.India.name().equals(strA)) {
            sb = new StringBuilder();
            str = "https://idmb.register.xmpush.global.xiaomi.com";
        } else {
            sb = new StringBuilder();
            sb.append(UriUtil.HTTPS_PREFIX);
            str = com.xiaomi.push.ab.m90a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com";
        }
        sb.append(str);
        sb.append("/pass/v2/register");
        return sb.toString();
    }

    public static void a() {
        if (f961a != null) {
            f961a.a();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m638a(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        a = null;
        a();
    }

    public static void a(Context context, l lVar) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_account", 0).edit();
        editorEdit.putString("uuid", lVar.f960a);
        editorEdit.putString("security", lVar.c);
        editorEdit.putString("token", lVar.b);
        editorEdit.putString("app_id", lVar.d);
        editorEdit.putString("package_name", lVar.f);
        editorEdit.putString("app_token", lVar.e);
        editorEdit.putString("device_id", com.xiaomi.push.i.k(context));
        editorEdit.putInt("env_type", lVar.a);
        editorEdit.commit();
        a();
    }

    public static void a(a aVar) {
        f961a = aVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static boolean m639a(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
