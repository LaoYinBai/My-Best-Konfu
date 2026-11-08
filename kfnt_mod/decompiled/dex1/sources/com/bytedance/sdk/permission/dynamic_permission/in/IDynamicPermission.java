package com.bytedance.sdk.permission.dynamic_permission.in;

import android.app.Activity;
import android.content.Context;
import com.bytedance.sdk.permission.dynamic_permission.callback.DynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.callback.SingleDynamicPermissionCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IDynamicPermission {
    boolean isGranted(Context context, String str);

    boolean isSelectedNoPrompt(Activity activity, String str);

    void requestPermission(Context context, DynamicPermissionCallback dynamicPermissionCallback, String... strArr);

    void requestPermission(Context context, String... strArr);

    void requestSinglePermission(Context context, String str, SingleDynamicPermissionCallback singleDynamicPermissionCallback);
}
