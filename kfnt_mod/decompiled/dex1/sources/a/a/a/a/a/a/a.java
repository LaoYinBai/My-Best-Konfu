package a.a.a.a.a.a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import com.bytedance.sdk.permission.dynamic_permission.callback.DynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.callback.SingleDynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements IDynamicPermission {

    /* renamed from: a, reason: collision with root package name */
    public IDynamicPermission f318a;

    /* renamed from: a.a.a.a.a.a.a$a, reason: collision with other inner class name */
    public static class C0000a {

        /* renamed from: a, reason: collision with root package name */
        public static a f319a = new a();
    }

    public a() {
        this.f318a = a.a.a.a.a.c.a.a() ? new b() : new c();
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    @SuppressLint({"WrongConstant"})
    public boolean isGranted(Context context, String str) {
        return this.f318a.isGranted(context, str);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public boolean isSelectedNoPrompt(Activity activity, String str) {
        return this.f318a.isSelectedNoPrompt(activity, str);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestPermission(Context context, DynamicPermissionCallback dynamicPermissionCallback, String... strArr) {
        this.f318a.requestPermission(context, dynamicPermissionCallback, strArr);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestPermission(Context context, String... strArr) {
        this.f318a.requestPermission(context, strArr);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestSinglePermission(Context context, String str, SingleDynamicPermissionCallback singleDynamicPermissionCallback) {
        this.f318a.requestSinglePermission(context, str, singleDynamicPermissionCallback);
    }
}
