package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.hm;
import com.xiaomi.push.hn;
import com.xiaomi.push.ht;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bo {

    /* renamed from: a, reason: collision with other field name */
    private static AtomicLong f939a = new AtomicLong(0);

    /* renamed from: a, reason: collision with other field name */
    private static SimpleDateFormat f938a = new SimpleDateFormat("yyyy/MM/dd");
    private static String a = f938a.format(Long.valueOf(System.currentTimeMillis()));

    private static ii a(String str, String str2, hm hmVar) {
        return new ii("-1", false).d(str).b(str2).a(com.xiaomi.push.y.a(it.a(hmVar))).c(ht.UploadTinyData.f509a);
    }

    public static synchronized String a() {
        String str;
        str = f938a.format(Long.valueOf(System.currentTimeMillis()));
        if (!TextUtils.equals(a, str)) {
            f939a.set(0L);
            a = str;
        }
        return str + Constants.ACCEPT_TIME_SEPARATOR_SERVER + f939a.incrementAndGet();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.ArrayList<com.xiaomi.push.ii> a(java.util.List<com.xiaomi.push.hn> r10, java.lang.String r11, java.lang.String r12, int r13) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            if (r10 != 0) goto L9
            java.lang.String r10 = "requests can not be null in TinyDataHelper.transToThriftObj()."
        L5:
            com.xiaomi.channel.commonutils.logger.b.d(r10)
            return r0
        L9:
            int r1 = r10.size()
            if (r1 != 0) goto L12
            java.lang.String r10 = "requests.length is 0 in TinyDataHelper.transToThriftObj()."
            goto L5
        L12:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.xiaomi.push.hm r2 = new com.xiaomi.push.hm
            r2.<init>()
            r3 = 0
            r4 = r2
            r2 = 0
            r5 = 0
        L20:
            int r6 = r10.size()
            if (r2 >= r6) goto Laf
            java.lang.Object r6 = r10.get(r2)
            com.xiaomi.push.hn r6 = (com.xiaomi.push.hn) r6
            if (r6 != 0) goto L30
            goto Lab
        L30:
            java.util.Map r7 = r6.m387a()
            if (r7 == 0) goto L73
            java.util.Map r7 = r6.m387a()
            java.lang.String r8 = "item_size"
            boolean r7 = r7.containsKey(r8)
            if (r7 == 0) goto L73
            java.util.Map r7 = r6.m387a()
            java.lang.String r8 = "item_size"
            java.lang.Object r7 = r7.get(r8)
            java.lang.String r7 = (java.lang.String) r7
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L59
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> L59
            goto L5a
        L59:
            r7 = 0
        L5a:
            java.util.Map r8 = r6.m387a()
            int r8 = r8.size()
            r9 = 1
            if (r8 != r9) goto L69
            r6.a(r0)
            goto L74
        L69:
            java.util.Map r8 = r6.m387a()
            java.lang.String r9 = "item_size"
            r8.remove(r9)
            goto L74
        L73:
            r7 = 0
        L74:
            if (r7 > 0) goto L7b
            byte[] r7 = com.xiaomi.push.it.a(r6)
            int r7 = r7.length
        L7b:
            if (r7 <= r13) goto L96
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "TinyData is too big, ignore upload request item:"
            r7.append(r8)
            java.lang.String r6 = r6.d()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r6)
            goto Lab
        L96:
            int r8 = r5 + r7
            if (r8 <= r13) goto La7
            com.xiaomi.push.ii r4 = a(r11, r12, r4)
            r1.add(r4)
            com.xiaomi.push.hm r4 = new com.xiaomi.push.hm
            r4.<init>()
            r5 = 0
        La7:
            r4.a(r6)
            int r5 = r5 + r7
        Lab:
            int r2 = r2 + 1
            goto L20
        Laf:
            int r10 = r4.a()
            if (r10 == 0) goto Lbc
            com.xiaomi.push.ii r10 = a(r11, r12, r4)
            r1.add(r10)
        Lbc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.bo.a(java.util.List, java.lang.String, java.lang.String, int):java.util.ArrayList");
    }

    public static void a(Context context, String str, String str2, long j, String str3) {
        hn hnVar = new hn();
        hnVar.d(str);
        hnVar.c(str2);
        hnVar.a(j);
        hnVar.b(str3);
        hnVar.a("push_sdk_channel");
        hnVar.g(context.getPackageName());
        hnVar.e(context.getPackageName());
        hnVar.a(true);
        hnVar.b(System.currentTimeMillis());
        hnVar.f(a());
        bp.a(context, hnVar);
    }

    public static boolean a(hn hnVar, boolean z) {
        String str;
        if (hnVar == null) {
            str = "item is null, verfiy ClientUploadDataItem failed.";
        } else if (!z && TextUtils.isEmpty(hnVar.f481a)) {
            str = "item.channel is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(hnVar.f488d)) {
            str = "item.category is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(hnVar.f487c)) {
            str = "item.name is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (!com.xiaomi.push.bm.m133a(hnVar.f488d)) {
            str = "item.category can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else if (!com.xiaomi.push.bm.m133a(hnVar.f487c)) {
            str = "item.name can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else {
            if (hnVar.f486b == null || hnVar.f486b.length() <= 10240) {
                return false;
            }
            str = "item.data is too large(" + hnVar.f486b.length() + "), max size for data is 10240 , verfiy ClientUploadDataItem failed.";
        }
        com.xiaomi.channel.commonutils.logger.b.m22a(str);
        return true;
    }

    public static boolean a(String str) {
        return !com.xiaomi.push.t.m648b() || Constants.HYBRID_PACKAGE_NAME.equals(str);
    }
}
