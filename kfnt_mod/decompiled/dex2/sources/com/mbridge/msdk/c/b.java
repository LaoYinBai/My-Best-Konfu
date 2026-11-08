package com.mbridge.msdk.c;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.kwad.v8.Platform;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.activity.MBCommonActivity;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.c.c;
import com.mbridge.msdk.foundation.same.report.d;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ShortCutsManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static volatile b b;
    private WeakReference<Context> a;
    private Handler c = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.c.b.3
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 10000) {
                return;
            }
            try {
                int i = message.arg1;
                Object obj = message.obj;
                b.a(b.this, i, obj instanceof String ? (String) obj : null);
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    };

    private String b(Context context) {
        return "";
    }

    private b(Context context) {
        this.a = new WeakReference<>(context);
    }

    public static b a(Context context) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b(context);
                }
            }
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        Context context = this.a.get();
        return context != null && context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public final void a(final CampaignEx campaignEx, final Activity activity) throws Throwable {
        final Context context = this.a.get();
        if (context == null) {
            return;
        }
        boolean z = false;
        if (context != null) {
            f fVarA = f.a(i.a(context));
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            List<CampaignEx> listB = fVarA.b(aVarB.g(), 0);
            if (listB != null && listB.size() > 0) {
                Iterator<CampaignEx> it = listB.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CampaignEx next = it.next();
                    if (next.getId().equals(campaignEx.getId()) && next.getAppName().equals(campaignEx.getAppName())) {
                        z = true;
                        break;
                    }
                }
            }
        }
        if (z) {
            a(activity);
        } else if (!TextUtils.isEmpty(campaignEx.getIconUrl())) {
            com.mbridge.msdk.foundation.same.c.b.a(context).a(campaignEx.getIconUrl(), new c() { // from class: com.mbridge.msdk.c.b.1
                @Override // com.mbridge.msdk.foundation.same.c.c
                public final void onFailedLoad(String str, String str2) {
                    b.this.a(activity);
                }

                @Override // com.mbridge.msdk.foundation.same.c.c
                public final void onSuccessLoad(Bitmap bitmap, String str) {
                    f fVarA2 = f.a(i.a(context));
                    com.mbridge.msdk.b.a aVarB2 = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
                    if (aVarB2 == null) {
                        aVarB2 = com.mbridge.msdk.b.b.a().b();
                    }
                    b.this.a(context, aVarB2);
                    if (b.this.a("com.android.launcher.permission.INSTALL_SHORTCUT")) {
                        int iB = b.this.b(campaignEx.getAppName());
                        b.a(b.this, context, bitmap, campaignEx, activity);
                        b.a(b.this, campaignEx, aVarB2, fVarA2, iB);
                    }
                }
            });
        } else {
            a(activity);
        }
    }

    private void a(Context context, CampaignEx campaignEx, int i) {
        Intent intent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", campaignEx.getAppName());
        Intent intent2 = new Intent(context, (Class<?>) MBCommonActivity.class);
        intent2.setAction("android.intent.action.VIEW");
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        context.sendBroadcast(intent);
        f fVarA = f.a(i.a(context));
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        String strG = aVarB.g();
        if (!a("com.android.launcher.permission.READ_SETTINGS") && !a("com.google.android.launcher.permission.READ_SETTINGS")) {
            a(campaignEx, 2, -1);
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_deleted", (Integer) 1);
            fVarA.a(campaignEx.getId(), contentValues);
        } else {
            if (b(campaignEx.getAppName()) < i) {
                a(campaignEx, 2, 1);
                campaignEx.setIsDeleted(1);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("is_deleted", (Integer) 1);
                fVarA.a(campaignEx.getId(), contentValues2);
            } else {
                a(campaignEx, 2, 0);
            }
        }
        fVarA.d(strG);
    }

    public final int a() throws Throwable {
        Context context = this.a.get();
        if (context == null) {
            return 0;
        }
        f fVarA = f.a(i.a(context));
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        List<CampaignEx> listB = fVarA.b(aVarB.g(), 0);
        if (listB == null || listB.size() <= 0) {
            return 0;
        }
        return listB.size();
    }

    private boolean a(Context context, String str) {
        boolean z = false;
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        String strC = TextUtils.isEmpty(null) ? c(context) : null;
        if (!TextUtils.isEmpty(strC)) {
            try {
                Cursor cursorA = a(strC, str, context);
                if (cursorA != null && cursorA.getCount() > 0) {
                    z = true;
                }
                if (cursorA != null && !cursorA.isClosed()) {
                    cursorA.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    private Cursor a(String str, String str2, Context context) {
        return context.getContentResolver().query(Uri.parse(str), new String[]{"title"}, "title=?", new String[]{str2}, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(String str) {
        Context context = this.a.get();
        if (context == null || !(a("com.android.launcher.permission.READ_SETTINGS") || a("com.google.android.launcher.permission.READ_SETTINGS"))) {
            return 0;
        }
        String strC = c(context);
        if (!TextUtils.isEmpty(strC)) {
            try {
                Cursor cursorA = a(strC, str, context);
                if (cursorA != null && cursorA.getCount() > 0) {
                    return cursorA.getCount();
                }
                if (cursorA != null && !cursorA.isClosed()) {
                    cursorA.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public final String b() {
        ResolveInfo resolveInfoResolveActivity;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        Context context = this.a.get();
        return (context == null || (resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0)) == null || resolveInfoResolveActivity.activityInfo == null || resolveInfoResolveActivity.activityInfo.packageName.equals(Platform.ANDROID)) ? "" : resolveInfoResolveActivity.activityInfo.packageName;
    }

    private String c(Context context) {
        String strB = b(context);
        if (strB == null || strB.trim().equals("")) {
            String str = b() + ".permission.READ_SETTINGS";
            strB = "";
        }
        if (TextUtils.isEmpty(strB)) {
            int i = Build.VERSION.SDK_INT;
            strB = i < 8 ? "com.android.launcher.settings" : i < 19 ? "com.android.launcher2.settings" : "com.android.launcher3.settings";
        }
        return "content://" + strB + "/favorites?notify=true";
    }

    public final String c() {
        return "[" + (a("com.android.launcher.permission.INSTALL_SHORTCUT") ? "1" : PushConstants.PUSH_TYPE_NOTIFY) + "," + (a("com.android.launcher.permission.UNINSTALL_SHORTCUT") ? "1" : PushConstants.PUSH_TYPE_NOTIFY) + "," + ((a("com.android.launcher.permission.READ_SETTINGS") || a("com.google.android.launcher.permission.READ_SETTINGS")) ? "1" : PushConstants.PUSH_TYPE_NOTIFY) + "]";
    }

    private void a(CampaignEx campaignEx, int i, int i2) {
        if (this.a.get() == null || campaignEx.getIsDeleted() == 1) {
            return;
        }
        d.a(this.a.get(), campaignEx, i, i2);
    }

    public final void a(com.mbridge.msdk.b.a aVar) {
        f fVarA;
        List<CampaignEx> listB;
        try {
            Context context = this.a.get();
            if (context == null || (listB = (fVarA = f.a(i.a(context))).b(aVar.g(), 0)) == null || listB.size() <= 0) {
                return;
            }
            for (CampaignEx campaignEx : listB) {
                if (campaignEx.getIsAddSuccesful() == 0 && campaignEx.getIsDeleted() == 0) {
                    if (b(campaignEx.getAppName()) != 0) {
                        a(campaignEx, 1, 1);
                        com.mbridge.msdk.foundation.a.a.a.a().a(com.mbridge.msdk.a.SHORTCUTS_CTIME, String.valueOf(System.currentTimeMillis()));
                    } else {
                        a(campaignEx, 1, 0);
                    }
                    campaignEx.setIsAddSuccesful(-1);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("is_add_sucesful", Integer.valueOf(campaignEx.getIsAddSuccesful()));
                    fVarA.a(campaignEx.getId(), contentValues);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(final int i, final String str) {
        try {
            if (this.a.get() == null) {
                return;
            }
            new Thread(new Runnable() { // from class: com.mbridge.msdk.c.b.2
                @Override // java.lang.Runnable
                public final void run() {
                    com.mbridge.msdk.b.a aVarB = str != null ? com.mbridge.msdk.b.b.a().b(str) : null;
                    if (aVarB == null) {
                        aVarB = com.mbridge.msdk.b.b.a().b();
                    }
                    b.this.a(aVarB);
                    Message messageObtain = Message.obtain();
                    messageObtain.arg1 = i;
                    messageObtain.obj = str;
                    b.this.c.sendEmptyMessage(10000);
                }
            }).start();
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, com.mbridge.msdk.b.a aVar) {
        List<CampaignEx> listB;
        if (TextUtils.isEmpty(aVar.g()) || (listB = f.a(i.a(context)).b(aVar.g(), 0)) == null || listB.size() <= 0 || !a("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
            return;
        }
        for (CampaignEx campaignEx : listB) {
            int iB = b(campaignEx.getAppName());
            Context context2 = this.a.get();
            if (context2 != null) {
                if (a("com.android.launcher.permission.READ_SETTINGS") || a("com.google.android.launcher.permission.READ_SETTINGS")) {
                    if (a(context2, campaignEx.getAppName())) {
                        a(context2, campaignEx, iB);
                    }
                } else if (a("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
                    a(context2, campaignEx, iB);
                }
            }
        }
    }

    static /* synthetic */ void a(b bVar, Context context, Bitmap bitmap, CampaignEx campaignEx, Activity activity) {
        JSONObject jSONObjectCampaignToJsonObject;
        if (!TextUtils.isEmpty(campaignEx.getAppName()) && bitmap != null) {
            try {
                Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                intent.putExtra("android.intent.extra.shortcut.NAME", campaignEx.getAppName());
                intent.putExtra("duplicate", false);
                intent.putExtra("android.intent.extra.shortcut.ICON", Bitmap.createScaledBitmap(bitmap, 140, 140, true));
                Intent intent2 = new Intent(context, (Class<?>) MBCommonActivity.class);
                intent2.putExtra("intent_flag", "shortcuts");
                try {
                    jSONObjectCampaignToJsonObject = CampaignEx.campaignToJsonObject(campaignEx);
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONObjectCampaignToJsonObject = null;
                }
                if (jSONObjectCampaignToJsonObject != null) {
                    intent2.putExtra("intent_jsonobject", jSONObjectCampaignToJsonObject.toString());
                }
                intent2.setFlags(32768);
                intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
                context.sendBroadcast(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        bVar.a(activity);
    }

    static /* synthetic */ void a(b bVar, CampaignEx campaignEx, com.mbridge.msdk.b.a aVar, f fVar, int i) {
        campaignEx.setTimestamp(0L);
        if (!bVar.a("com.android.launcher.permission.READ_SETTINGS") && !bVar.a("com.google.android.launcher.permission.READ_SETTINGS")) {
            campaignEx.setIsAddSuccesful(1);
            bVar.a(campaignEx, 1, -1);
            campaignEx.setIsAddSuccesful(-1);
            fVar.a(campaignEx, aVar.g(), 0);
            com.mbridge.msdk.foundation.a.a.a.a().a(com.mbridge.msdk.a.SHORTCUTS_CTIME, String.valueOf(System.currentTimeMillis()));
            return;
        }
        if (bVar.b(campaignEx.getAppName()) > i) {
            campaignEx.setIsAddSuccesful(1);
            fVar.a(campaignEx, aVar.g(), 0);
            bVar.a(campaignEx, 1, 1);
            campaignEx.setIsAddSuccesful(-1);
            com.mbridge.msdk.foundation.a.a.a.a().a(com.mbridge.msdk.a.SHORTCUTS_CTIME, String.valueOf(System.currentTimeMillis()));
            return;
        }
        campaignEx.setIsAddSuccesful(0);
        fVar.a(campaignEx, aVar.g(), 0);
        com.mbridge.msdk.foundation.a.a.a.a().a(com.mbridge.msdk.a.SHORTCUTS_CTIME, String.valueOf(System.currentTimeMillis()));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005c A[Catch: Exception -> 0x006b, TRY_LEAVE, TryCatch #0 {Exception -> 0x006b, blocks: (B:2:0x0000, B:7:0x000e, B:9:0x0018, B:20:0x005c, B:12:0x0023, B:14:0x003f, B:15:0x004d), top: B:27:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.mbridge.msdk.c.b r7, int r8, java.lang.String r9) {
        /*
            java.lang.ref.WeakReference<android.content.Context> r0 = r7.a     // Catch: java.lang.Exception -> L6b
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Exception -> L6b
            android.content.Context r0 = (android.content.Context) r0     // Catch: java.lang.Exception -> L6b
            if (r0 != 0) goto Lb
            goto L73
        Lb:
            r1 = 0
            if (r9 == 0) goto L16
            com.mbridge.msdk.b.b r1 = com.mbridge.msdk.b.b.a()     // Catch: java.lang.Exception -> L6b
            com.mbridge.msdk.b.a r1 = r1.b(r9)     // Catch: java.lang.Exception -> L6b
        L16:
            if (r1 != 0) goto L20
            com.mbridge.msdk.b.b r9 = com.mbridge.msdk.b.b.a()     // Catch: java.lang.Exception -> L6b
            com.mbridge.msdk.b.a r1 = r9.b()     // Catch: java.lang.Exception -> L6b
        L20:
            if (r1 != 0) goto L23
            goto L59
        L23:
            int r9 = r1.e()     // Catch: java.lang.Exception -> L6b
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L6b
            r3 = 0
            com.mbridge.msdk.foundation.a.a.a r5 = com.mbridge.msdk.foundation.a.a.a.a()     // Catch: java.lang.Exception -> L6b
            java.lang.String r6 = "mb_shortcuts_ctime"
            java.lang.String r5 = r5.b(r6)     // Catch: java.lang.Exception -> L6b
            java.lang.String r6 = ""
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Exception -> L6b
            if (r5 != 0) goto L4d
            com.mbridge.msdk.foundation.a.a.a r3 = com.mbridge.msdk.foundation.a.a.a.a()     // Catch: java.lang.Exception -> L6b
            java.lang.String r4 = "mb_shortcuts_ctime"
            java.lang.String r3 = r3.b(r4)     // Catch: java.lang.Exception -> L6b
            long r3 = java.lang.Long.parseLong(r3)     // Catch: java.lang.Exception -> L6b
        L4d:
            r5 = 0
            long r1 = r1 - r3
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            long r3 = (long) r9     // Catch: java.lang.Exception -> L6b
            int r9 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r9 <= 0) goto L59
            r9 = 1
            goto L5a
        L59:
            r9 = 0
        L5a:
            if (r9 == 0) goto L73
            android.os.Handler r9 = new android.os.Handler     // Catch: java.lang.Exception -> L6b
            r9.<init>()     // Catch: java.lang.Exception -> L6b
            com.mbridge.msdk.c.b$4 r1 = new com.mbridge.msdk.c.b$4     // Catch: java.lang.Exception -> L6b
            r1.<init>()     // Catch: java.lang.Exception -> L6b
            long r7 = (long) r8     // Catch: java.lang.Exception -> L6b
            r9.postDelayed(r1, r7)     // Catch: java.lang.Exception -> L6b
            goto L73
        L6b:
            r7 = move-exception
            boolean r8 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r8 == 0) goto L73
            r7.printStackTrace()
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.c.b.a(com.mbridge.msdk.c.b, int, java.lang.String):void");
    }
}
