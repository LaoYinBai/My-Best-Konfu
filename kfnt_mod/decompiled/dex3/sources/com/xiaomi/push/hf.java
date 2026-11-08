package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hf {
    private static boolean a;

    static class a implements Runnable {
        private Context a;

        /* renamed from: a, reason: collision with other field name */
        private hi f468a;

        public a(Context context, hi hiVar) {
            this.f468a = hiVar;
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            hf.c(this.a, this.f468a);
        }
    }

    private static void a(Context context) {
        File file = new File(context.getFilesDir() + "/tdReadTemp");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static void a(Context context, hi hiVar) {
        ai.a(context).a(new a(context, hiVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x009d, code lost:
    
        r15 = "TinyData read from cache file failed cause lengthBuffer < 1 || too big. length:" + r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r12, com.xiaomi.push.hi r13, java.io.File r14, byte[] r15) throws java.lang.Throwable {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 4
            byte[] r2 = new byte[r1]
            r3 = 0
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld1
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld1
            r5.<init>(r14)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld1
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld1
            r3 = 0
        L14:
            r5 = 0
            r6 = 0
        L16:
            int r7 = r4.read(r2)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r8 = -1
            if (r7 != r8) goto L1f
            goto Laf
        L1f:
            if (r7 == r1) goto L37
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.<init>()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r1 = "TinyData read from cache file failed cause lengthBuffer error. size:"
            r15.append(r1)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.append(r7)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r15 = r15.toString()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
        L32:
            com.xiaomi.channel.commonutils.logger.b.d(r15)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            goto Laf
        L37:
            int r7 = com.xiaomi.push.ac.a(r2)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r8 = 1
            if (r7 < r8) goto L9d
            r8 = 10240(0x2800, float:1.4349E-41)
            if (r7 <= r8) goto L43
            goto L9d
        L43:
            byte[] r9 = new byte[r7]     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            int r10 = r4.read(r9)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            if (r10 == r7) goto L65
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.<init>()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r1 = "TinyData read from cache file failed cause buffer size not equal length. size:"
            r15.append(r1)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.append(r10)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r1 = "__length:"
            r15.append(r1)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.append(r7)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r15 = r15.toString()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            goto L32
        L65:
            byte[] r7 = com.xiaomi.push.h.a(r15, r9)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            if (r7 == 0) goto L96
            int r9 = r7.length     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            if (r9 != 0) goto L6f
            goto L96
        L6f:
            com.xiaomi.push.hn r9 = new com.xiaomi.push.hn     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r9.<init>()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            com.xiaomi.push.it.a(r9, r7)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r10 = "item_size"
            int r11 = r7.length     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r9.a(r10, r11)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r0.add(r9)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            int r5 = r5 + 1
            int r7 = r7.length     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            int r6 = r6 + r7
            r7 = 8
            if (r5 >= r7) goto L8e
            if (r6 < r8) goto L16
        L8e:
            com.xiaomi.push.hg.a(r12, r13, r0)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r0.clear()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            goto L14
        L96:
            java.lang.String r7 = "TinyData read from cache file failed cause decrypt fail"
            com.xiaomi.channel.commonutils.logger.b.d(r7)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            goto L16
        L9d:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.<init>()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r1 = "TinyData read from cache file failed cause lengthBuffer < 1 || too big. length:"
            r15.append(r1)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            r15.append(r7)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            java.lang.String r15 = r15.toString()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            goto L32
        Laf:
            com.xiaomi.push.hg.a(r12, r13, r0)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            if (r14 == 0) goto Lc5
            boolean r12 = r14.exists()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            if (r12 == 0) goto Lc5
            boolean r12 = r14.delete()     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
            if (r12 != 0) goto Lc5
            java.lang.String r12 = "TinyData delete reading temp file failed"
            com.xiaomi.channel.commonutils.logger.b.m22a(r12)     // Catch: java.lang.Throwable -> Lc9 java.lang.Exception -> Lcb
        Lc5:
            com.xiaomi.push.y.a(r4)
            goto Ld8
        Lc9:
            r12 = move-exception
            goto Ld9
        Lcb:
            r12 = move-exception
            r3 = r4
            goto Ld2
        Lce:
            r12 = move-exception
            r4 = r3
            goto Ld9
        Ld1:
            r12 = move-exception
        Ld2:
            com.xiaomi.channel.commonutils.logger.b.a(r12)     // Catch: java.lang.Throwable -> Lce
            com.xiaomi.push.y.a(r3)
        Ld8:
            return
        Ld9:
            com.xiaomi.push.y.a(r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hf.a(android.content.Context, com.xiaomi.push.hi, java.io.File, byte[]):void");
    }

    private static void b(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("mipush_extra", 4).edit();
        editorEdit.putLong("last_tiny_data_upload_timestamp", System.currentTimeMillis() / 1000);
        editorEdit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(android.content.Context r7, com.xiaomi.push.hi r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hf.c(android.content.Context, com.xiaomi.push.hi):void");
    }
}
