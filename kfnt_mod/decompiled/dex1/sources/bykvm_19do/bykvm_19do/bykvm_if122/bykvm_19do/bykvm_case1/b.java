package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1;

import android.content.Context;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1809a = "b";

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b4 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.RequiresApi(api = 19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(android.content.Context r5, java.lang.String r6) {
        /*
            java.lang.String r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.b.f1809a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "checkPermissinKITKATNew，permission："
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.bytedance.msdk.adapter.util.Logger.e(r0, r1)
            r0 = 0
            r1 = 1
            int r2 = r6.hashCode()     // Catch: java.lang.Exception -> Lb6
            r3 = -1888586689(0xffffffff8f6e743f, float:-1.1756694E-29)
            r4 = 2
            if (r2 == r3) goto L41
            r3 = -63024214(0xfffffffffc3e53aa, float:-3.9529332E36)
            if (r2 == r3) goto L37
            r3 = -5573545(0xffffffffffaaf457, float:NaN)
            if (r2 == r3) goto L2d
            goto L4b
        L2d:
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r2 = r6.equals(r2)     // Catch: java.lang.Exception -> Lb6
            if (r2 == 0) goto L4b
            r2 = 2
            goto L4c
        L37:
            java.lang.String r2 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r2 = r6.equals(r2)     // Catch: java.lang.Exception -> Lb6
            if (r2 == 0) goto L4b
            r2 = 0
            goto L4c
        L41:
            java.lang.String r2 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r2 = r6.equals(r2)     // Catch: java.lang.Exception -> Lb6
            if (r2 == 0) goto L4b
            r2 = 1
            goto L4c
        L4b:
            r2 = -1
        L4c:
            if (r2 == 0) goto L65
            if (r2 == r1) goto L62
            if (r2 == r4) goto L5f
            java.lang.String r2 = "android.permission."
            java.lang.String r3 = "android:"
            java.lang.String r6 = r6.replaceFirst(r2, r3)     // Catch: java.lang.Exception -> Lb6
            java.lang.String r6 = r6.toLowerCase()     // Catch: java.lang.Exception -> Lb6
            goto L67
        L5f:
            java.lang.String r6 = "android:read_phone_state"
            goto L67
        L62:
            java.lang.String r6 = "android:fine_location"
            goto L67
        L65:
            java.lang.String r6 = "android:coarse_location"
        L67:
            java.lang.String r2 = "appops"
            java.lang.Object r2 = r5.getSystemService(r2)     // Catch: java.lang.Exception -> Lb6
            android.app.AppOpsManager r2 = (android.app.AppOpsManager) r2     // Catch: java.lang.Exception -> Lb6
            int r3 = android.os.Binder.getCallingUid()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = r5.getPackageName()     // Catch: java.lang.Exception -> Lb6
            int r5 = r2.checkOp(r6, r3, r5)     // Catch: java.lang.Exception -> Lb6
            java.lang.String r2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.b.f1809a     // Catch: java.lang.Exception -> Lb6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb6
            r3.<init>()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r4 = "checkPermissinKITKATNew，locationOp,permission："
            r3.append(r4)     // Catch: java.lang.Exception -> Lb6
            r3.append(r5)     // Catch: java.lang.Exception -> Lb6
            java.lang.String r4 = ","
            r3.append(r4)     // Catch: java.lang.Exception -> Lb6
            r3.append(r6)     // Catch: java.lang.Exception -> Lb6
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> Lb6
            com.bytedance.msdk.adapter.util.Logger.e(r2, r3)     // Catch: java.lang.Exception -> Lb6
            if (r5 == 0) goto Lb4
            java.lang.String r5 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.b.f1809a     // Catch: java.lang.Exception -> Lb2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb2
            r1.<init>()     // Catch: java.lang.Exception -> Lb2
            java.lang.String r2 = "checkPermissinKITKATNew，false,permission："
            r1.append(r2)     // Catch: java.lang.Exception -> Lb2
            r1.append(r6)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Exception -> Lb2
            com.bytedance.msdk.adapter.util.Logger.e(r5, r6)     // Catch: java.lang.Exception -> Lb2
            goto Lce
        Lb2:
            r5 = move-exception
            goto Lb8
        Lb4:
            r0 = 1
            goto Lce
        Lb6:
            r5 = move-exception
            r0 = 1
        Lb8:
            java.lang.String r6 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.b.f1809a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "权限检查出错时默认返回有权限，异常代码："
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.bytedance.msdk.adapter.util.Logger.e(r6, r5)
        Lce:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.b.a(android.content.Context, java.lang.String):boolean");
    }

    public static boolean b(Context context, String str) {
        return Build.VERSION.SDK_INT >= 19 ? a(context, str) : c(context, str);
    }

    private static boolean c(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
