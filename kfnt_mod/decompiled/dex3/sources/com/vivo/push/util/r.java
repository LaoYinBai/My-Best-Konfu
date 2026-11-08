package com.vivo.push.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class r {
    public static Boolean a;

    public static com.vivo.push.model.b a(Context context) throws PackageManager.NameNotFoundException {
        com.vivo.push.model.b bVarC;
        com.vivo.push.model.b bVarC2;
        Context applicationContext = ContextDelegate.getContext(context).getApplicationContext();
        com.vivo.push.model.b bVarC3 = c(applicationContext);
        if (bVarC3 != null) {
            n.d("PushPackageUtils", "get system push info :" + bVarC3);
            return bVarC3;
        }
        List<String> listD = d(applicationContext);
        com.vivo.push.model.b bVarC4 = c(applicationContext, applicationContext.getPackageName());
        if (listD.size() <= 0) {
            if (bVarC4 != null && bVarC4.f) {
                bVarC3 = bVarC4;
            }
            n.a("PushPackageUtils", "findAllPushPackages error: find no package!");
        } else {
            com.vivo.push.model.b bVar = null;
            String strA = x.b(applicationContext).a("com.vivo.push.cur_pkg", null);
            if (TextUtils.isEmpty(strA) || !a(applicationContext, strA, "com.vivo.pushservice.action.METHOD") || (bVarC = c(applicationContext, strA)) == null || !bVarC.f) {
                bVarC = null;
            }
            if (bVarC4 == null || !bVarC4.f) {
                bVarC4 = null;
            }
            if (bVarC == null) {
                bVarC = null;
            }
            if (bVarC4 != null && (bVarC == null || (!bVarC4.e ? bVarC.e || bVarC4.b > bVarC.b : bVarC.e && bVarC4.b > bVarC.b))) {
                bVarC = bVarC4;
            }
            HashMap map = new HashMap();
            if (bVarC == null) {
                bVarC = null;
            } else if (!bVarC.e) {
                bVar = bVarC;
                bVarC = null;
            }
            int size = listD.size();
            com.vivo.push.model.b bVar2 = bVar;
            com.vivo.push.model.b bVar3 = bVarC;
            bVarC3 = bVar2;
            for (int i = 0; i < size; i++) {
                String str = listD.get(i);
                if (!TextUtils.isEmpty(str) && (bVarC2 = c(applicationContext, str)) != null) {
                    map.put(str, bVarC2);
                    if (bVarC2.f) {
                        if (bVarC2.e) {
                            if (bVar3 == null || bVarC2.b > bVar3.b) {
                                bVar3 = bVarC2;
                            }
                        } else if (bVarC3 == null || bVarC2.b > bVarC3.b) {
                            bVarC3 = bVarC2;
                        }
                    }
                }
            }
            if (bVarC3 == null) {
                n.d("PushPackageUtils", "findSuitablePushPackage, all push app in balck list.");
                bVarC3 = bVar3;
            }
        }
        if (bVarC3 == null) {
            n.b(applicationContext, "查找最优包为空!");
            n.d("PushPackageUtils", "finSuitablePushPackage is null");
        } else if (bVarC3.e) {
            n.a(applicationContext, "查找最优包为:" + bVarC3.a + com.umeng.message.proguard.l.s + bVarC3.b + ", Black)");
            n.d("PushPackageUtils", "finSuitablePushPackage" + bVarC3.a + com.umeng.message.proguard.l.s + bVarC3.b + ", Black)");
        } else {
            n.a(applicationContext, "查找最优包为:" + bVarC3.a + com.umeng.message.proguard.l.s + bVarC3.b + com.umeng.message.proguard.l.t);
            n.d("PushPackageUtils", "finSuitablePushPackage" + bVarC3.a + com.umeng.message.proguard.l.s + bVarC3.b + com.umeng.message.proguard.l.t);
        }
        return bVarC3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.r.b(android.content.Context):java.lang.String");
    }

    private static com.vivo.push.model.b c(Context context) throws PackageManager.NameNotFoundException {
        String strB = b(context);
        ApplicationInfo applicationInfo = null;
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        com.vivo.push.model.b bVar = new com.vivo.push.model.b(strB);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(strB, 128);
            if (packageInfo != null) {
                bVar.c = packageInfo.versionCode;
                bVar.d = packageInfo.versionName;
                applicationInfo = packageInfo.applicationInfo;
            }
            if (applicationInfo != null) {
                bVar.b = y.a(context, strB);
            }
            bVar.e = a(context, bVar.b);
            bVar.f = a(context, strB);
            return bVar;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            n.d("PushPackageUtils", "PackageManager NameNotFoundException is null");
            return null;
        }
    }

    private static com.vivo.push.model.b c(Context context, String str) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = null;
        if (!TextUtils.isEmpty(str)) {
            if (a(context, str, "com.vivo.pushservice.action.METHOD") || a(context, str, "com.vivo.pushservice.action.RECEIVE")) {
                com.vivo.push.model.b bVar = new com.vivo.push.model.b(str);
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
                    if (packageInfo != null) {
                        bVar.c = packageInfo.versionCode;
                        bVar.d = packageInfo.versionName;
                        applicationInfo = packageInfo.applicationInfo;
                    }
                    if (applicationInfo != null) {
                        bVar.b = y.a(context, str);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    n.a("PushPackageUtils", "getPushPackageInfo exception: ", e);
                }
                bVar.f = a(context, str);
                bVar.e = a(context, bVar.b);
                return bVar;
            }
        }
        return null;
    }

    @TargetApi(4)
    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return false;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 576);
        if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
            n.a("PushPackageUtils", "isEnablePush error: can not find push service.");
            return false;
        }
        int size = listQueryIntentServices.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = listQueryIntentServices.get(i);
            if (resolveInfo != null && resolveInfo.serviceInfo != null) {
                String str2 = resolveInfo.serviceInfo.name;
                boolean z2 = resolveInfo.serviceInfo.exported;
                if ("com.vivo.push.sdk.service.PushService".equals(str2) && z2) {
                    boolean z3 = resolveInfo.serviceInfo.enabled;
                    int componentEnabledSetting = packageManager.getComponentEnabledSetting(new ComponentName(str, "com.vivo.push.sdk.service.PushService"));
                    boolean z4 = true;
                    if (componentEnabledSetting != 1 && (componentEnabledSetting != 0 || !z3)) {
                        z4 = false;
                    }
                    z = z4;
                }
            }
        }
        return z;
    }

    private static boolean a(Context context, long j) {
        com.vivo.push.cache.d dVarA = com.vivo.push.cache.b.a().a(context);
        if (dVarA != null) {
            return dVarA.isInBlackList(j);
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean a(Context context, String str, String str2) {
        List<ResolveInfo> listQueryBroadcastReceivers;
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        } catch (Exception unused) {
            listQueryBroadcastReceivers = null;
        }
        return listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0;
    }

    private static List<String> d(Context context) {
        List<ResolveInfo> listQueryIntentServices;
        f.a("findAllCoreClientPush");
        ArrayList arrayList = new ArrayList();
        try {
            listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent("com.vivo.pushservice.action.PUSH_SERVICE"), 576);
        } catch (Exception unused) {
            listQueryIntentServices = null;
        }
        if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
            int size = listQueryIntentServices.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = listQueryIntentServices.get(i);
                if (resolveInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        if (arrayList.size() <= 0) {
            n.d("PushPackageUtils", "get all push packages is null");
        }
        return arrayList;
    }

    public static String b(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            byte[] bArrDigest = MessageDigest.getInstance("SHA256").digest(signatureArr[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String upperCase = Integer.toHexString(b & UByte.MAX_VALUE).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append(Constants.FAIL);
                }
                stringBuffer.append(upperCase);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            n.a("PushPackageUtils", e);
            return null;
        }
    }
}
