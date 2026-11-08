package com.huawei.updatesdk.service.c;

import android.content.Context;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static String[] a;

    public static String[] a() {
        if (a == null) {
            a = a(com.huawei.updatesdk.a.b.a.a.a().b());
        }
        return (String[]) a.clone();
    }

    private static String[] a(Context context) throws NoSuchMethodException, SecurityException {
        return new String[]{b(context) + File.separator + "Android" + File.separator + "data" + File.separator + context.getPackageName() + File.separator + "files"};
    }

    private static String b(Context context) throws NoSuchMethodException, SecurityException {
        StorageVolume[] storageVolumeArr;
        boolean zBooleanValue;
        String str;
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        StorageVolume[] storageVolumeArr2 = new StorageVolume[0];
        try {
            Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
            method.setAccessible(true);
            storageVolumeArr = (StorageVolume[]) method.invoke(storageManager, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("StorageManage", "can not find method:getVolumeList", e);
            storageVolumeArr = storageVolumeArr2;
        }
        if (storageVolumeArr == null || storageVolumeArr.length <= 0) {
            return null;
        }
        Method methodB = b();
        Method methodC = c();
        for (StorageVolume storageVolume : storageVolumeArr) {
            if (methodB != null) {
                try {
                    zBooleanValue = ((Boolean) methodB.invoke(storageVolume, new Object[0])).booleanValue();
                } catch (IllegalAccessException | InvocationTargetException e2) {
                    com.huawei.updatesdk.a.a.b.a.a.a.a("StorageManage", "can not invoke method:getVolumeList", e2);
                }
            } else {
                zBooleanValue = false;
            }
            try {
                str = (String) methodC.invoke(storageVolume, new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException e3) {
                com.huawei.updatesdk.a.a.b.a.a.a.a("StorageManage", "can not invoke method:getPath", e3);
                str = "";
            }
            if (!zBooleanValue && !str.contains("usb")) {
                return str;
            }
        }
        return null;
    }

    private static Method b() throws NoSuchMethodException, SecurityException {
        Method method;
        Method method2 = null;
        try {
            method = StorageVolume.class.getMethod("isRemovable", new Class[0]);
        } catch (Exception e) {
            e = e;
        }
        try {
            method.setAccessible(true);
            return method;
        } catch (Exception e2) {
            method2 = method;
            e = e2;
            com.huawei.updatesdk.a.a.b.a.a.a.a("StorageManage", "can not find method:isRemovable", e);
            return method2;
        }
    }

    private static Method c() throws NoSuchMethodException, SecurityException {
        Method method;
        Method method2 = null;
        try {
            method = StorageVolume.class.getMethod("getPath", new Class[0]);
        } catch (Exception e) {
            e = e;
        }
        try {
            method.setAccessible(true);
            return method;
        } catch (Exception e2) {
            method2 = method;
            e = e2;
            com.huawei.updatesdk.a.a.b.a.a.a.a("StorageManage", "can not find method:getPath", e);
            return method2;
        }
    }
}
