package com.bytedance.sdk.permission.dynamic_permission.entity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DynamicPermissionResult {
    public String permissionName;
    public DynamicPermissionResultType permissionResultType;

    public DynamicPermissionResult(String str, DynamicPermissionResultType dynamicPermissionResultType) {
        this.permissionName = str;
        this.permissionResultType = dynamicPermissionResultType;
    }

    public DynamicPermissionResultType getResultType() {
        return this.permissionResultType;
    }

    public boolean isGranted() {
        return this.permissionResultType == DynamicPermissionResultType.GRANTED;
    }

    public boolean isSelectedNoPrompted() {
        return this.permissionResultType == DynamicPermissionResultType.SELECTED_NO_PROMPTED;
    }
}
