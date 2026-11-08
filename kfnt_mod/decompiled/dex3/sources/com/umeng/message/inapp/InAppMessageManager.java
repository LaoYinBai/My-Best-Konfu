package com.umeng.message.inapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.entity.UInAppMessage;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InAppMessageManager {
    static boolean a = false;
    static int b = 1800000;
    static int c = 1000;
    private static final String d = "com.umeng.message.inapp.InAppMessageManager";

    @SuppressLint({"StaticFieldLeak"})
    private static InAppMessageManager e = null;
    private static final String i = "tempkey";
    private static final String j = "tempvalue";
    private Context f;
    private String g;
    private UInAppHandler h = new UmengInAppClickHandler();

    private InAppMessageManager(Context context) {
        this.f = context;
    }

    public static InAppMessageManager getInstance(Context context) {
        if (e == null) {
            synchronized (InAppMessageManager.class) {
                if (e == null) {
                    e = new InAppMessageManager(context.getApplicationContext());
                }
            }
        }
        return e;
    }

    public void showCardMessage(Activity activity, String str, IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        new b(activity, str, iUmengInAppMsgCloseCallback).a();
    }

    public void setInAppMsgDebugMode(boolean z) {
        a = z;
    }

    public void setMainActivityPath(String str) {
        this.g = str;
    }

    String a() {
        return this.g;
    }

    public UInAppHandler getInAppHandler() {
        return this.h;
    }

    public void setInAppHandler(UInAppHandler uInAppHandler) {
        this.h = uInAppHandler;
    }

    public void setPlainTextSize(int i2, int i3, int i4) {
        if (i2 <= 0 || i3 <= 0 || i4 <= 0) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(d, 0, "纯文本字体大小不能小于0");
            return;
        }
        b(MsgConstant.KEY_PLAIN_TEXT_SIZE, i2 + Constants.ACCEPT_TIME_SEPARATOR_SP + i3 + Constants.ACCEPT_TIME_SEPARATOR_SP + i4);
    }

    String[] b() throws Throwable {
        String strA = a(MsgConstant.KEY_PLAIN_TEXT_SIZE, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return strA.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
    }

    void c() {
        b(MsgConstant.KEY_SPLASH_TS, System.currentTimeMillis() + "");
    }

    long d() {
        return Long.valueOf(a(MsgConstant.KEY_SPLASH_TS, com.sigmob.sdk.common.Constants.FAIL)).longValue();
    }

    void a(String str) {
        b("KEY_CARD_TS_" + str, System.currentTimeMillis() + "");
    }

    long b(String str) {
        return Long.valueOf(a("KEY_CARD_TS_" + str, com.sigmob.sdk.common.Constants.FAIL)).longValue();
    }

    void a(UInAppMessage uInAppMessage) {
        if (uInAppMessage == null) {
            b(MsgConstant.KEY_LAST_SPLASH_ID, "");
        } else if (uInAppMessage.getRaw() != null) {
            b(MsgConstant.KEY_LAST_SPLASH_ID, uInAppMessage.getRaw().toString());
        }
    }

    String e() {
        return a(MsgConstant.KEY_LAST_SPLASH_ID, "");
    }

    void a(UInAppMessage uInAppMessage, String str) {
        if (uInAppMessage == null) {
            b("KEY_LAST_CARD_ID_" + str, "");
            return;
        }
        if (uInAppMessage.getRaw() != null) {
            b("KEY_LAST_CARD_ID_" + str, uInAppMessage.getRaw().toString());
        }
    }

    String c(String str) {
        return a("KEY_LAST_CARD_ID_" + str, "");
    }

    void a(String str, int i2) {
        if (i2 == 0) {
            b(str, com.sigmob.sdk.common.Constants.FAIL);
        }
        if (i2 == 1) {
            b(str, (j(str) + 1) + "");
        }
    }

    private int j(String str) {
        return Integer.valueOf(a(str, com.sigmob.sdk.common.Constants.FAIL)).intValue();
    }

    void d(String str) {
        b(MsgConstant.KEY_CARD_LABEL_LIST, str);
    }

    String f() {
        return a(MsgConstant.KEY_CARD_LABEL_LIST, "");
    }

    void e(String str) {
        b(MsgConstant.KEY_LAST_VERSION_CODE, str);
    }

    String g() {
        return a(MsgConstant.KEY_LAST_VERSION_CODE, "");
    }

    void h() {
        b(MsgConstant.KEY_LAST_SHOW_SPLASH_TS, System.currentTimeMillis() + "");
    }

    long i() {
        return Long.parseLong(a(MsgConstant.KEY_LAST_SHOW_SPLASH_TS, com.sigmob.sdk.common.Constants.FAIL));
    }

    void f(String str) {
        b("KEY_LAST_SHOW_CARD_TS_" + str, System.currentTimeMillis() + "");
    }

    long g(String str) {
        return Long.parseLong(a("KEY_LAST_SHOW_CARD_TS_" + str, com.sigmob.sdk.common.Constants.FAIL));
    }

    boolean b(UInAppMessage uInAppMessage) {
        try {
            return System.currentTimeMillis() < new SimpleDateFormat(DateUtils.FORMAT_ONE, Locale.CHINA).parse(uInAppMessage.expire_time).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    boolean c(UInAppMessage uInAppMessage) {
        return uInAppMessage.show_times == 0 || j(uInAppMessage.msg_id) < uInAppMessage.show_times;
    }

    void a(final String str, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7, final int i8, final int i9) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.InAppMessageManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a aVarK = InAppMessageManager.this.k(str);
                    if (aVarK != null) {
                        a aVar = new a(str, i2, aVarK.d + i3, aVarK.e + i4, aVarK.f + i5, aVarK.g + i6, aVarK.h + i7, aVarK.i + i8, aVarK.j);
                        String[] strArr = {str};
                        ContentResolver contentResolver = InAppMessageManager.this.f.getContentResolver();
                        com.umeng.message.provider.a.a(InAppMessageManager.this.f);
                        contentResolver.update(com.umeng.message.provider.a.k, aVar.a(), "MsgId=?", strArr);
                    } else {
                        a aVar2 = new a(str, i2, i3, i4, i5, i6, i7, i8, i9);
                        ContentResolver contentResolver2 = InAppMessageManager.this.f.getContentResolver();
                        com.umeng.message.provider.a.a(InAppMessageManager.this.f);
                        contentResolver2.insert(com.umeng.message.provider.a.k, aVar2.a());
                    }
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(InAppMessageManager.d, 2, "store in app cache log success");
                } catch (Exception e2) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(InAppMessageManager.d, 0, "store in app cache log fail");
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a k(String str) {
        ContentResolver contentResolver = this.f.getContentResolver();
        com.umeng.message.provider.a.a(this.f);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.k, null, "MsgId=?", new String[]{str}, null);
        a aVar = cursorQuery.moveToFirst() ? new a(cursorQuery) : null;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9, types: [boolean] */
    ArrayList<a> j() throws Throwable {
        ArrayList<a> arrayList = new ArrayList<>();
        ?? MoveToNext = 0;
        MoveToNext = 0;
        try {
            try {
                ContentResolver contentResolver = this.f.getContentResolver();
                com.umeng.message.provider.a.a(this.f);
                Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.k, null, null, null, null);
                MoveToNext = 0;
                if (cursorQuery != null) {
                    try {
                        MoveToNext = cursorQuery.moveToFirst();
                    } catch (Exception e2) {
                        e = e2;
                        MoveToNext = cursorQuery;
                        e.printStackTrace();
                        if (MoveToNext != 0) {
                            MoveToNext.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        MoveToNext = cursorQuery;
                        if (MoveToNext != 0) {
                            MoveToNext.close();
                        }
                        throw th;
                    }
                }
                while (MoveToNext != 0) {
                    arrayList.add(new a(cursorQuery));
                    MoveToNext = cursorQuery.moveToNext();
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return arrayList;
    }

    boolean h(String str) {
        String[] strArr = {str};
        ContentResolver contentResolver = this.f.getContentResolver();
        com.umeng.message.provider.a.a(this.f);
        return contentResolver.delete(com.umeng.message.provider.a.k, "MsgId=?", strArr) == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    private String a(String str, String str2) throws Throwable {
        Cursor cursorQuery;
        ?? r0 = 0;
        r0 = 0;
        try {
            try {
                new ContentValues().put(i, str);
                ContentResolver contentResolver = this.f.getContentResolver();
                com.umeng.message.provider.a.a(this.f);
                cursorQuery = contentResolver.query(com.umeng.message.provider.a.c, new String[]{j}, "tempkey=?", new String[]{str}, null);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        if (cursorQuery == null) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return str2;
        }
        try {
            boolean zMoveToFirst = cursorQuery.moveToFirst();
            r0 = zMoveToFirst;
            if (zMoveToFirst) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex(j));
                str2 = string;
                r0 = string;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e3) {
            r0 = cursorQuery;
            e = e3;
            e.printStackTrace();
            if (r0 != 0) {
                r0.close();
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            r0 = cursorQuery;
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
        return str2;
    }

    private void b(final String str, final String str2) {
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.InAppMessageManager.2
            /* JADX WARN: Removed duplicated region for block: B:24:0x00cb  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    r10 = this;
                    r0 = 0
                    java.lang.String r7 = "tempkey=?"
                    r1 = 1
                    java.lang.String[] r8 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    r1 = 0
                    java.lang.String r2 = r2     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    r8[r1] = r2     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    com.umeng.message.inapp.InAppMessageManager r1 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.content.Context r1 = com.umeng.message.inapp.InAppMessageManager.a(r1)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    com.umeng.message.inapp.InAppMessageManager r2 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.content.Context r2 = com.umeng.message.inapp.InAppMessageManager.a(r2)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    com.umeng.message.provider.a.a(r2)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.net.Uri r2 = com.umeng.message.provider.a.c     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    java.lang.String r3 = "tempvalue"
                    java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    r6 = 0
                    r4 = r7
                    r5 = r8
                    android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    if (r1 != 0) goto L5d
                    android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempkey"
                    java.lang.String r3 = r2     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempvalue"
                    java.lang.String r3 = r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.inapp.InAppMessageManager r2 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r2 = com.umeng.message.inapp.InAppMessageManager.a(r2)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.inapp.InAppMessageManager r3 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r3 = com.umeng.message.inapp.InAppMessageManager.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.provider.a.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.net.Uri r3 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r2.insert(r3, r0)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    goto Lb3
                L5b:
                    r0 = move-exception
                    goto Lbf
                L5d:
                    boolean r0 = r1.moveToFirst()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    if (r0 == 0) goto L88
                    android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempvalue"
                    java.lang.String r3 = r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.inapp.InAppMessageManager r2 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r2 = com.umeng.message.inapp.InAppMessageManager.a(r2)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.inapp.InAppMessageManager r3 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r3 = com.umeng.message.inapp.InAppMessageManager.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.provider.a.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.net.Uri r3 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r2.update(r3, r0, r7, r8)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    goto Lb3
                L88:
                    android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempkey"
                    java.lang.String r3 = r2     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempvalue"
                    java.lang.String r3 = r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.inapp.InAppMessageManager r2 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r2 = com.umeng.message.inapp.InAppMessageManager.a(r2)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.inapp.InAppMessageManager r3 = com.umeng.message.inapp.InAppMessageManager.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r3 = com.umeng.message.inapp.InAppMessageManager.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.provider.a.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.net.Uri r3 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r2.insert(r3, r0)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                Lb3:
                    if (r1 == 0) goto Lc7
                    goto Lc4
                Lb6:
                    r1 = move-exception
                    r9 = r1
                    r1 = r0
                    r0 = r9
                    goto Lc9
                Lbb:
                    r1 = move-exception
                    r9 = r1
                    r1 = r0
                    r0 = r9
                Lbf:
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> Lc8
                    if (r1 == 0) goto Lc7
                Lc4:
                    r1.close()
                Lc7:
                    return
                Lc8:
                    r0 = move-exception
                Lc9:
                    if (r1 == 0) goto Lce
                    r1.close()
                Lce:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.inapp.InAppMessageManager.AnonymousClass2.run():void");
            }
        });
    }

    void i(final String str) {
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.InAppMessageManager.3
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.String] */
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                Cursor cursor = null;
                cursor = null;
                cursor = null;
                try {
                    try {
                        new ContentValues().put(InAppMessageManager.i, str);
                        ContentResolver contentResolver = InAppMessageManager.this.f.getContentResolver();
                        com.umeng.message.provider.a.a(InAppMessageManager.this.f);
                        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.c, new String[]{InAppMessageManager.j}, null, null, null);
                        if (cursorQuery != null) {
                            try {
                                String[] strArr = {str};
                                ContentResolver contentResolver2 = InAppMessageManager.this.f.getContentResolver();
                                com.umeng.message.provider.a.a(InAppMessageManager.this.f);
                                contentResolver2.delete(com.umeng.message.provider.a.c, "tempkey=?", strArr);
                                cursor = "tempkey=?";
                            } catch (Exception e2) {
                                e = e2;
                                cursor = cursorQuery;
                                e.printStackTrace();
                                if (cursor != null) {
                                    cursor.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor = cursorQuery;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        });
    }

    void a(final File file) {
        com.umeng.message.common.d.a(new Runnable() { // from class: com.umeng.message.inapp.InAppMessageManager.4
            @Override // java.lang.Runnable
            public void run() {
                if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (!file2.isDirectory()) {
                            file2.delete();
                        }
                    }
                    file.delete();
                }
            }
        });
    }
}
