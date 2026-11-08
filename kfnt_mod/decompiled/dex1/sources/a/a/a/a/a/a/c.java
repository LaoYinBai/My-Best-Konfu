package a.a.a.a.a.a;

import android.app.Activity;
import android.content.Context;
import com.bytedance.sdk.permission.dynamic_permission.callback.DynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.callback.SingleDynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission;
import com.bytedance.sdk.permission.dynamic_permission.ui.DynamicPermissionDelegateActivity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements IDynamicPermission {

    /* renamed from: a, reason: collision with root package name */
    public a.a.a.a.a.b.a f320a;

    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    @android.annotation.SuppressLint({"WrongConstant"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isGranted(android.content.Context r8, java.lang.String r9) {
        /*
            r7 = this;
            boolean r0 = a.a.a.a.a.c.a.a()
            r1 = 0
            if (r0 != 0) goto L4f
            int r0 = android.os.Process.myPid()
            int r2 = android.os.Process.myUid()
            java.lang.String r3 = r8.getPackageName()
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 23
            r6 = -1
            if (r4 < r5) goto L4c
            int r0 = r8.checkPermission(r9, r0, r2)
            if (r0 != r6) goto L21
            goto L3b
        L21:
            java.lang.String r9 = android.app.AppOpsManager.permissionToOp(r9)
            if (r9 != 0) goto L28
            goto L4c
        L28:
            if (r3 != 0) goto L3d
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            java.lang.String[] r0 = r0.getPackagesForUid(r2)
            if (r0 == 0) goto L3b
            int r2 = r0.length
            if (r2 > 0) goto L38
            goto L3b
        L38:
            r3 = r0[r1]
            goto L3d
        L3b:
            r8 = -1
            goto L4d
        L3d:
            java.lang.Class<android.app.AppOpsManager> r0 = android.app.AppOpsManager.class
            java.lang.Object r8 = r8.getSystemService(r0)
            android.app.AppOpsManager r8 = (android.app.AppOpsManager) r8
            if (r8 == 0) goto L4c
            int r8 = r8.noteProxyOpNoThrow(r9, r3)
            goto L4d
        L4c:
            r8 = 0
        L4d:
            if (r8 != 0) goto L50
        L4f:
            r1 = 1
        L50:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.a.a.a.c.isGranted(android.content.Context, java.lang.String):boolean");
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public boolean isSelectedNoPrompt(Activity activity, String str) {
        return a.a.a.a.a.c.b.a(activity, str);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestPermission(Context context, DynamicPermissionCallback dynamicPermissionCallback, String... strArr) {
        this.f320a = new d(strArr, dynamicPermissionCallback);
        DynamicPermissionDelegateActivity.a(context);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestPermission(Context context, String... strArr) {
        this.f320a = new d(strArr, null);
        DynamicPermissionDelegateActivity.a(context);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestSinglePermission(Context context, String str, SingleDynamicPermissionCallback singleDynamicPermissionCallback) {
        this.f320a = new e(str, singleDynamicPermissionCallback);
        DynamicPermissionDelegateActivity.a(context);
    }
}
