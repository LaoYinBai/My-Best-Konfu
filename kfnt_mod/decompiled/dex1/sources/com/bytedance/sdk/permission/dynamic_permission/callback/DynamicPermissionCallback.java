package com.bytedance.sdk.permission.dynamic_permission.callback;

import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResult;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface DynamicPermissionCallback {
    void onResult(Map<String, DynamicPermissionResult> map, boolean z);
}
