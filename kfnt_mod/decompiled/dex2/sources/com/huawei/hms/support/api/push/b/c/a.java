package com.huawei.hms.support.api.push.b.c;

import android.R;
import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import com.kwad.v8.Platform;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r11v26, types: [int] */
    /* JADX WARN: Type inference failed for: r11v29, types: [int] */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v7, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r13v9, types: [java.lang.StringBuilder] */
    private static int a(Context context, String str, String str2, Object obj) throws IllegalAccessException, IllegalArgumentException {
        Class<?> cls;
        ?? r0 = 0;
        try {
            String str3 = context.getPackageName() + ".R";
            HMSLog.d("PushSelfShowLog", "try to refrect " + str3 + " typeName is " + ((String) str2));
            Class<?>[] classes = Class.forName(str3).getClasses();
            StringBuilder sb = new StringBuilder();
            sb.append("sonClassArr length ");
            sb.append(classes.length);
            HMSLog.d("PushSelfShowLog", sb.toString());
            cls = null;
            int length = classes.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Class<?> cls2 = classes[i];
                    String strSubstring = cls2.getName().substring(str3.length() + 1);
                    HMSLog.d("PushSelfShowLog", "sonTypeClass,query sonclass is " + strSubstring + " sonClass.getName() is" + cls2.getName());
                    if (str2.equals(strSubstring)) {
                        cls = cls2;
                        break;
                    }
                    i++;
                }
            }
        } catch (ClassNotFoundException e) {
            e = e;
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (IllegalArgumentException e3) {
            e = e3;
        } catch (IndexOutOfBoundsException e4) {
            e = e4;
        } catch (NoSuchFieldException e5) {
            e = e5;
        }
        try {
            if (cls != null) {
                ?? r11 = cls.getField(str).getInt(obj);
                HMSLog.d("PushSelfShowLog", "refect res id is " + r11);
                context = r11;
            } else {
                HMSLog.d("PushSelfShowLog", "sonTypeClass is null");
                String str4 = context.getPackageName() + ".R$" + ((String) str2);
                HMSLog.d("PushSelfShowLog", "try to refrect 2 " + str4 + " typeName is " + ((String) str2));
                ?? r112 = Class.forName(str4).getField(str).getInt(obj);
                HMSLog.d("PushSelfShowLog", " refect res id 2 is " + r112);
                context = r112;
            }
            return context;
        } catch (ClassNotFoundException e6) {
            r0 = context;
            e = e6;
            HMSLog.e("PushSelfShowLog", "ClassNotFound failed,", e);
            return r0;
        } catch (IllegalAccessException e7) {
            r0 = context;
            e = e7;
            HMSLog.e("PushSelfShowLog", "IllegalAccessException failed,", e);
            return r0;
        } catch (IllegalArgumentException e8) {
            r0 = context;
            e = e8;
            HMSLog.e("PushSelfShowLog", "IllegalArgumentException failed,", e);
            return r0;
        } catch (IndexOutOfBoundsException e9) {
            r0 = context;
            e = e9;
            HMSLog.e("PushSelfShowLog", "IndexOutOfBoundsException failed,", e);
            return r0;
        } catch (NoSuchFieldException e10) {
            r0 = context;
            e = e10;
            HMSLog.e("PushSelfShowLog", "NoSuchFieldException failed,", e);
            return r0;
        }
    }

    private static int b(Context context, com.huawei.hms.support.api.push.b.b.a aVar) {
        if (context == null || aVar == null) {
            HMSLog.i("PushSelfShowLog", "enter getSmallIconId, context or msg is null");
            return 0;
        }
        int i = context.getApplicationInfo().icon;
        if (i != 0) {
            return i;
        }
        int identifier = context.getResources().getIdentifier("btn_star_big_on", "drawable", Platform.ANDROID);
        HMSLog.d("PushSelfShowLog", "icon is btn_star_big_on ");
        if (identifier != 0) {
            return identifier;
        }
        HMSLog.d("PushSelfShowLog", "icon is sym_def_app_icon ");
        return R.drawable.sym_def_app_icon;
    }

    public static Bitmap a(Context context, com.huawei.hms.support.api.push.b.b.a aVar) {
        Bitmap bitmap;
        Bitmap bitmapDecodeResource = null;
        if (context == null || aVar == null) {
            return null;
        }
        try {
            if (aVar.l() != null && aVar.l().length() > 0) {
                int iA = 0;
                if (!aVar.l().equals("" + aVar.a())) {
                    iA = a(context, aVar.l(), "drawable", new R.drawable());
                    if (iA == 0) {
                        iA = context.getResources().getIdentifier(aVar.l(), "drawable", Platform.ANDROID);
                    }
                    HMSLog.d("PushSelfShowLog", "msg.notifyIcon is " + aVar.l() + ",and defaultIcon is " + iA);
                }
                if (iA != 0) {
                    try {
                        bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), iA);
                    } catch (OutOfMemoryError unused) {
                        HMSLog.e("PushSelfShowLog", "generateBitmap OutOfMemoryError");
                    }
                }
            }
            if (HwBuildEx.VERSION.EMUI_SDK_INT >= 11) {
                HMSLog.i("PushSelfShowLog", "huawei phone, and emui5.0, need not show large icon.");
                return bitmapDecodeResource;
            }
            if (bitmapDecodeResource != null || "com.huawei.android.pushagent".equals(aVar.i())) {
                bitmap = bitmapDecodeResource;
            } else {
                HMSLog.i("PushSelfShowLog", "get left bitmap from " + aVar.i());
                bitmap = ((BitmapDrawable) context.getPackageManager().getApplicationIcon(aVar.i())).getBitmap();
            }
            return bitmap;
        } catch (PackageManager.NameNotFoundException e) {
            HMSLog.e("PushSelfShowLog", "" + e.toString(), e);
            return null;
        } catch (Exception e2) {
            HMSLog.e("PushSelfShowLog", "" + e2.toString(), e2);
            return null;
        }
    }

    @TargetApi(23)
    private static Icon c(Context context, com.huawei.hms.support.api.push.b.b.a aVar) {
        if (context == null || aVar == null) {
            HMSLog.e("PushSelfShowLog", "getSmallIcon, context is null");
            return null;
        }
        if (Build.VERSION.SDK_INT < 23) {
            HMSLog.i("PushSelfShowLog", "getSmallIcon failed, Build.VERSION less than 23");
            return null;
        }
        try {
            return Icon.createWithResource(aVar.i(), context.getPackageManager().getApplicationInfo(aVar.i(), 0).icon);
        } catch (PackageManager.NameNotFoundException e) {
            HMSLog.e("PushSelfShowLog", e.toString());
            return null;
        } catch (Exception e2) {
            HMSLog.e("PushSelfShowLog", e2.toString(), e2);
            return null;
        }
    }

    private static boolean d(Context context, com.huawei.hms.support.api.push.b.b.a aVar) {
        return !"com.huawei.android.pushagent".equals(aVar.i()) && Build.VERSION.SDK_INT >= 23 && (com.huawei.hms.support.api.push.b.d.a.a(context) || com.huawei.hms.support.api.push.b.d.a.b(context));
    }

    @TargetApi(23)
    public static void a(Context context, Notification.Builder builder, com.huawei.hms.support.api.push.b.b.a aVar) {
        if (context == null || builder == null || aVar == null) {
            HMSLog.e("PushSelfShowLog", "msg is null");
            return;
        }
        if (d(context, aVar)) {
            HMSLog.i("PushSelfShowLog", "get small icon from " + aVar.i());
            Icon iconC = c(context, aVar);
            if (iconC != null) {
                builder.setSmallIcon(iconC);
                return;
            } else {
                builder.setSmallIcon(b(context, aVar));
                return;
            }
        }
        builder.setSmallIcon(b(context, aVar));
    }
}
