package com.huawei.hms.update.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.common.PackageConstants;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.UpdateBean;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UpdateManager {
    private static boolean a(Context context, String str) {
        PackageManagerHelper.PackageStates packageStates = new PackageManagerHelper(context).getPackageStates(str);
        HMSLog.i("UpdateManager", "app is: " + str + ";status is:" + packageStates);
        return PackageManagerHelper.PackageStates.ENABLED == packageStates;
    }

    private static boolean a(Context context) {
        return a(context, "com.android.vending") && b(context);
    }

    private static boolean b(Context context) {
        ResourceLoaderUtil.setmContext(context);
        boolean zA = new a().a(1000L, TimeUnit.MILLISECONDS, ResourceLoaderUtil.getString("hms_base_google"));
        HMSLog.i("UpdateManager", "ping google status is:" + zA);
        return zA;
    }

    public static void startUpdate(Activity activity, int i, UpdateBean updateBean) {
        Intent startUpdateIntent = getStartUpdateIntent(activity, updateBean);
        if (startUpdateIntent != null) {
            activity.startActivityForResult(startUpdateIntent, i);
        }
    }

    public static Intent getStartUpdateIntent(Activity activity, UpdateBean updateBean) {
        if (activity == null || updateBean == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(updateBean.getClientAppName())) {
            a(activity, (ArrayList<Integer>) arrayList);
        } else {
            b(activity, (ArrayList<Integer>) arrayList);
        }
        updateBean.setTypeList(arrayList);
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, com.huawei.hms.update.ui.a.a(((Integer) arrayList.get(0)).intValue()));
        intentStartBridgeActivity.putExtra(BridgeActivity.EXTRA_DELEGATE_UPDATE_INFO, updateBean);
        return intentStartBridgeActivity;
    }

    public static Intent getStartUpdateIntent(Context context, UpdateBean updateBean) {
        if (context == null || updateBean == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(updateBean.getClientAppName())) {
            a(context, (ArrayList<Integer>) arrayList);
        } else {
            b(context, (ArrayList<Integer>) arrayList);
        }
        updateBean.setTypeList(arrayList);
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(context, com.huawei.hms.update.ui.a.a(((Integer) arrayList.get(0)).intValue()));
        intentStartBridgeActivity.putExtra(BridgeActivity.EXTRA_DELEGATE_UPDATE_INFO, updateBean);
        return intentStartBridgeActivity;
    }

    public static Intent startUpdateIntent(Activity activity) {
        Intent intentStartBridgeActivity;
        if (activity == null) {
            return null;
        }
        UpdateBean updateBean = new UpdateBean();
        updateBean.setHmsOrApkUpgrade(true);
        updateBean.setClientPackageName(HMSPackageManager.getInstance(activity.getApplicationContext()).getHMSPackageName());
        updateBean.setClientVersionCode(HMSPackageManager.getInstance(activity.getApplicationContext()).getHmsVersionCode());
        updateBean.setClientAppId("C10132067");
        updateBean.setNeedConfirm(false);
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        updateBean.setClientAppName(ResourceLoaderUtil.getString("hms_update_title"));
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(updateBean.getClientAppName())) {
            a(activity, (ArrayList<Integer>) arrayList);
        } else {
            b(activity, (ArrayList<Integer>) arrayList);
        }
        updateBean.setTypeList(arrayList);
        if (arrayList.size() > 1) {
            intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, com.huawei.hms.update.ui.a.a(((Integer) arrayList.get(1)).intValue()));
        } else {
            intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, com.huawei.hms.update.ui.a.a(((Integer) arrayList.get(0)).intValue()));
        }
        intentStartBridgeActivity.putExtra(BridgeActivity.EXTRA_DELEGATE_UPDATE_INFO, updateBean);
        return intentStartBridgeActivity;
    }

    private static void a(Activity activity, ArrayList<Integer> arrayList) {
        if (!HMSPackageManager.getInstance(activity).getHMSPackageName().equalsIgnoreCase("com.huawei.hwid")) {
            arrayList.add(5);
            return;
        }
        if (SystemUtils.isEMUI() && SystemUtils.isChinaROM()) {
            if (a(activity, PackageConstants.SERVICES_PACKAGE_APPMARKET) && c(activity)) {
                arrayList.add(0);
                arrayList.add(6);
                return;
            } else {
                arrayList.add(6);
                return;
            }
        }
        if (a(activity, PackageConstants.SERVICES_PACKAGE_APPMARKET) && !PackageConstants.SERVICES_PACKAGE_APPMARKET.equals(activity.getPackageName())) {
            arrayList.add(5);
        } else if (a(activity)) {
            arrayList.add(2);
        } else {
            arrayList.add(6);
        }
    }

    private static void a(Context context, ArrayList<Integer> arrayList) {
        if (!HMSPackageManager.getInstance(context).getHMSPackageName().equalsIgnoreCase("com.huawei.hwid")) {
            arrayList.add(5);
            return;
        }
        if (SystemUtils.isEMUI() && SystemUtils.isChinaROM()) {
            if (a(context, PackageConstants.SERVICES_PACKAGE_APPMARKET) && c(context)) {
                arrayList.add(0);
                arrayList.add(6);
                return;
            } else {
                arrayList.add(6);
                return;
            }
        }
        if (a(context, PackageConstants.SERVICES_PACKAGE_APPMARKET) && !PackageConstants.SERVICES_PACKAGE_APPMARKET.equals(context.getPackageName())) {
            arrayList.add(5);
        } else if (a(context)) {
            arrayList.add(2);
        } else {
            arrayList.add(6);
        }
    }

    private static void b(Activity activity, ArrayList<Integer> arrayList) {
        if (a(activity, PackageConstants.SERVICES_PACKAGE_APPMARKET) && !PackageConstants.SERVICES_PACKAGE_APPMARKET.equals(activity.getPackageName())) {
            arrayList.add(5);
        } else {
            arrayList.add(4);
        }
    }

    private static void b(Context context, ArrayList<Integer> arrayList) {
        if (a(context, PackageConstants.SERVICES_PACKAGE_APPMARKET) && !PackageConstants.SERVICES_PACKAGE_APPMARKET.equals(context.getPackageName())) {
            arrayList.add(5);
        } else {
            arrayList.add(4);
        }
    }

    private static boolean c(Context context) {
        int packageVersionCode = new PackageManagerHelper(context).getPackageVersionCode(PackageConstants.SERVICES_PACKAGE_APPMARKET);
        HMSLog.i("UpdateManager", "getHiappVersion is " + packageVersionCode);
        return ((long) packageVersionCode) >= 70203000;
    }
}
