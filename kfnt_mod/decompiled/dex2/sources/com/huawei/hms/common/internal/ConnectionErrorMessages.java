package com.huawei.hms.common.internal;

import android.app.Activity;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ConnectionErrorMessages {
    private static final Map<String, String> a = new HashMap();

    public static String getErrorTitle(Activity activity, int i) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        if (i != 9) {
            switch (i) {
                case 1:
                    return ResourceLoaderUtil.getString("hms_install_message");
                case 2:
                    return ResourceLoaderUtil.getString("hms_update_message");
                case 3:
                    return ResourceLoaderUtil.getString("hms_bindfaildlg_message");
                default:
                    HMSLog.e("HuaweiApiAvailability", "Unexpected error code " + i);
                    return null;
            }
        }
        HMSLog.e("HuaweiApiAvailability", "Huawei Mobile Services is invalid. Cannot recover.");
        return null;
    }

    public static String getErrorDialogButtonMessage(Activity activity, int i) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        switch (i) {
            case 1:
                return ResourceLoaderUtil.getString("hms_install");
            case 2:
                return ResourceLoaderUtil.getString("hms_update");
            default:
                return ResourceLoaderUtil.getString("hms_confirm");
        }
    }

    public static String getErrorMessage(Activity activity, int i) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        String string = ResourceLoaderUtil.getString("hms_update_title");
        switch (i) {
            case 1:
            case 2:
                return string;
            default:
                return null;
        }
    }
}
