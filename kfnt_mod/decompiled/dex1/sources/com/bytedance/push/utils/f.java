package com.bytedance.push.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import com.bytedance.push.utils.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f4996a;

    /* renamed from: b, reason: collision with root package name */
    private static List<ServiceInfo> f4997b;

    /* renamed from: c, reason: collision with root package name */
    private static List<ActivityInfo> f4998c;
    private static List<ActivityInfo> d;
    private static List<ProviderInfo> e;

    private static List<String> a(Context context) throws PackageManager.NameNotFoundException {
        String[] strArr;
        if (f4996a == null) {
            synchronized (f.class) {
                if (f4996a == null && (strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions) != null) {
                    f4996a = Arrays.asList(strArr);
                }
            }
        }
        return f4996a;
    }

    public static boolean a(Context context, String str, String str2, List<String> list) throws PackageManager.NameNotFoundException {
        List<String> listA = a(context);
        if (listA == null || listA.isEmpty()) {
            com.bytedance.push.i.l().b(str, str2 + ".You need to declare permission(s) {" + list + "} in AndroidManifest.xml");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : list) {
            if (!listA.contains(str3)) {
                arrayList.add(str3);
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        com.bytedance.push.i.l().b(str, str2 + ".You need to declare permission(s) {" + arrayList + "} in AndroidManifest.xml");
        return false;
    }

    public static boolean b(Context context, String str, String str2, List<a> list) throws PackageManager.NameNotFoundException {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ServiceInfo> listB = b(context);
        if (listB == null || listB.size() == 0) {
            com.bytedance.push.i.l().b(str, str2 + ". You need to declare service(s) {" + list + "} in AndroidManifest.xml");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ServiceInfo> it = listB.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ServiceInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.f4985b)) {
                    boolean zEquals = TextUtils.equals(aVar.f4986c, next.processName);
                    boolean zEquals2 = !TextUtils.isEmpty(aVar.d) ? TextUtils.equals(next.permission, aVar.d) : true;
                    if (!zEquals) {
                        com.bytedance.push.i.l().b(str, str2 + " service configure error: " + aVar.f4985b + " should be declared in process " + aVar.f4986c + ", but now in " + next.processName);
                        z2 = false;
                    }
                    if (!zEquals2) {
                        com.bytedance.push.i.l().b(str, str2 + " service configure error: " + aVar.f4985b + " need permission(s) {" + aVar.d + "}, but now " + next.permission);
                        z2 = false;
                    }
                    if (aVar.f4984a != null) {
                        boolean z3 = true;
                        for (a.b bVar : aVar.f4984a) {
                            if (bVar.f4988a != null) {
                                for (String str3 : bVar.f4988a) {
                                    if (!a(context, aVar.f4985b, bVar, str3)) {
                                        com.bytedance.push.i.l().b(str, str2 + aVar.f4985b + " need to declare {" + str3 + "} action(s) in AndroidManifest.xml");
                                        z3 = false;
                                    }
                                }
                            }
                        }
                        if (!z3) {
                            z2 = false;
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.l().b(str, str2 + " need to declare service(s) {" + arrayList + "} in AndroidManifest.xml");
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ServiceInfo> b(Context context) throws PackageManager.NameNotFoundException {
        ServiceInfo[] serviceInfoArr;
        if (f4997b == null) {
            synchronized (f.class) {
                if (f4997b == null && (serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 516).services) != null) {
                    f4997b = Arrays.asList(serviceInfoArr);
                }
            }
        }
        return f4997b;
    }

    public static boolean c(Context context, String str, String str2, List<a> list) throws PackageManager.NameNotFoundException {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ActivityInfo> listC = c(context);
        if (listC == null || listC.size() == 0) {
            com.bytedance.push.i.l().b(str, str2 + " error configuration:" + list);
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ActivityInfo> it = listC.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ActivityInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.f4985b)) {
                    if (!TextUtils.equals(aVar.f4986c, next.processName)) {
                        com.bytedance.push.i.l().b(str, str2 + " receiver configure error: " + aVar.f4985b + " should be in" + aVar.f4986c + ",but now in " + next.processName + " process");
                        z2 = false;
                    }
                    if (!(!TextUtils.isEmpty(aVar.d) ? TextUtils.equals(next.permission, aVar.d) : true)) {
                        com.bytedance.push.i.l().b(str, str2 + " receiver configure error: " + aVar.f4985b + " need permission " + aVar.d + ", but now the permission is:" + next.permission);
                        z2 = false;
                    }
                    if (aVar.f4984a != null) {
                        boolean z3 = true;
                        for (a.b bVar : aVar.f4984a) {
                            if (bVar.f4988a != null) {
                                for (String str3 : bVar.f4988a) {
                                    if (!b(context, aVar.f4985b, bVar, str3)) {
                                        com.bytedance.push.i.l().b(str, str2 + " " + aVar.f4985b + " configure error，need action : " + str3);
                                        z3 = false;
                                    }
                                }
                            }
                        }
                        if (!z3) {
                            z2 = false;
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.l().b(str, str2 + " receiver configure error: " + arrayList);
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ActivityInfo> c(Context context) throws PackageManager.NameNotFoundException {
        ActivityInfo[] activityInfoArr;
        if (f4998c == null) {
            synchronized (f.class) {
                if (f4998c == null && (activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 514).receivers) != null) {
                    f4998c = Arrays.asList(activityInfoArr);
                }
            }
        }
        return f4998c;
    }

    private static boolean b(Context context, String str, a.b bVar, String str2) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (bVar.f4989b != null) {
            Iterator<String> it = bVar.f4989b.iterator();
            while (it.hasNext()) {
                intent.addCategory(it.next());
            }
        }
        intent.setAction(str2);
        if (bVar.f4990c != null) {
            if (!TextUtils.isEmpty(bVar.d)) {
                intent.setDataAndType(bVar.f4990c, bVar.d);
            } else {
                intent.setData(bVar.f4990c);
            }
        } else if (!TextUtils.isEmpty(bVar.d)) {
            intent.setType(bVar.d);
        }
        List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        if (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.size() <= 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
            if (resolveInfo.activityInfo != null && TextUtils.equals(resolveInfo.activityInfo.name, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, String str, a.b bVar, String str2) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (bVar.f4989b != null) {
            Iterator<String> it = bVar.f4989b.iterator();
            while (it.hasNext()) {
                intent.addCategory(it.next());
            }
        }
        intent.setAction(str2);
        if (bVar.f4990c != null) {
            intent.setData(bVar.f4990c);
        }
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            if (resolveInfo.serviceInfo != null && (TextUtils.isEmpty(str) || TextUtils.equals(resolveInfo.serviceInfo.name, str))) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(Context context, String str, a.b bVar, String str2) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (bVar.f4989b != null) {
            Iterator<String> it = bVar.f4989b.iterator();
            while (it.hasNext()) {
                intent.addCategory(it.next());
            }
        }
        intent.setAction(str2);
        if (bVar.f4990c != null) {
            intent.setData(bVar.f4990c);
        }
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 576);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (resolveInfo.activityInfo != null && TextUtils.equals(resolveInfo.activityInfo.name, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(Context context, String str, String str2, List<a> list) throws PackageManager.NameNotFoundException {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ActivityInfo> listD = d(context);
        if (listD == null || listD.size() == 0) {
            com.bytedance.push.i.l().b(str, str2 + ". You need to declare activity(s) {" + list + "} in AndroidManifest.xml");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ActivityInfo> it = listD.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ActivityInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.f4985b)) {
                    if (!TextUtils.equals(aVar.f4986c, next.processName)) {
                        com.bytedance.push.i.l().b(str, str2 + aVar.f4985b + " should be declared in process " + aVar.f4986c + ", but now in process " + next.processName);
                        z2 = false;
                    }
                    if (!(!TextUtils.isEmpty(aVar.d) ? TextUtils.equals(next.permission, aVar.d) : true)) {
                        com.bytedance.push.i.l().b(str, str2 + aVar.f4985b + " need permission(s) {" + aVar.d + "}, but now is " + next.permission);
                        z2 = false;
                    }
                    if (aVar.f4984a != null) {
                        boolean z3 = true;
                        for (a.b bVar : aVar.f4984a) {
                            if (bVar.f4988a != null) {
                                for (String str3 : bVar.f4988a) {
                                    if (!c(context, aVar.f4985b, bVar, str3)) {
                                        com.bytedance.push.i.l().b(str, str2 + " " + aVar.f4985b + " need action " + str3);
                                        z3 = false;
                                    }
                                }
                            }
                        }
                        if (!z3) {
                            z2 = false;
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.l().b(str, str2 + ".You should declare activities {" + arrayList + "} in AndroidManifest.xml");
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ActivityInfo> d(Context context) throws PackageManager.NameNotFoundException {
        ActivityInfo[] activityInfoArr;
        if (d == null) {
            synchronized (f.class) {
                if (d == null && (activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), InputDeviceCompat.SOURCE_DPAD).activities) != null) {
                    d = Arrays.asList(activityInfoArr);
                }
            }
        }
        return d;
    }

    public static boolean e(Context context, String str, String str2, List<a> list) throws PackageManager.NameNotFoundException {
        boolean z;
        boolean z2;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ProviderInfo> listE = e(context);
        if (listE == null || listE.size() == 0) {
            com.bytedance.push.i.l().b(str, str2 + " need to declare ContentProvider(s) " + list);
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z3 = true;
        for (a aVar : list) {
            Iterator<ProviderInfo> it = listE.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = z3;
                    z2 = false;
                    break;
                }
                ProviderInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.f4985b)) {
                    if (!TextUtils.equals(aVar.f4986c, next.processName)) {
                        com.bytedance.push.i.l().b(str, str2 + aVar.f4985b + " should be declared in process " + aVar.f4986c + ", but now is " + next.processName);
                        z3 = false;
                    }
                    if (!TextUtils.isEmpty(aVar.e) ? TextUtils.equals(next.authority, aVar.e) : true) {
                        z = z3;
                        z2 = true;
                    } else {
                        com.bytedance.push.i.l().b(str, str2 + aVar.f4985b + " need permission(s) {" + aVar.e + "}, but now is" + next.authority);
                        z2 = true;
                        z = false;
                    }
                }
            }
            if (!z2) {
                arrayList.add(aVar);
            }
            z3 = z;
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.l().b(str, str2 + " .You should declare activities {" + arrayList + "} in AndroidManifest.xml");
        }
        return z3 && arrayList.isEmpty();
    }

    private static List<ProviderInfo> e(Context context) throws PackageManager.NameNotFoundException {
        ProviderInfo[] providerInfoArr;
        if (e == null) {
            synchronized (f.class) {
                if (e == null && (providerInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 520).providers) != null) {
                    e = Arrays.asList(providerInfoArr);
                }
            }
        }
        return e;
    }
}
