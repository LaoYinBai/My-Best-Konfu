package com.huawei.agconnect.core.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.huawei.agconnect.core.ServiceDiscovery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private Context a;

    b(Context context) {
        this.a = context;
    }

    public List<com.huawei.agconnect.core.a> a() throws PackageManager.NameNotFoundException, ClassNotFoundException {
        List<String> listB = b();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = listB.iterator();
        while (it.hasNext()) {
            com.huawei.agconnect.core.b bVarA = a(it.next());
            if (bVarA != null) {
                bVarA.a(this.a);
                List<com.huawei.agconnect.core.a> listB2 = bVarA.b(this.a);
                if (listB2 != null) {
                    arrayList.addAll(listB2);
                }
            }
        }
        return arrayList;
    }

    private <T extends com.huawei.agconnect.core.b> T a(String str) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName(str);
            if (!com.huawei.agconnect.core.b.class.isAssignableFrom(cls)) {
                Log.e("ServiceRegistrarParser", cls + " must extends from ServiceRegistrar.");
                return null;
            }
            return (T) Class.forName(str).newInstance();
        } catch (ClassNotFoundException e) {
            Log.e("ServiceRegistrarParser", "Can not found service class, " + e.getMessage());
            return null;
        } catch (Exception e2) {
            Log.e("ServiceRegistrarParser", "instantiate service class exception " + e2.getLocalizedMessage());
            return null;
        }
    }

    private List<String> b() throws PackageManager.NameNotFoundException {
        ArrayList arrayList = new ArrayList();
        Bundle bundleC = c();
        if (bundleC == null) {
            return arrayList;
        }
        HashMap map = new HashMap();
        for (String str : bundleC.keySet()) {
            if ("com.huawei.agconnect.core.ServiceRegistrar".equals(bundleC.getString(str))) {
                String[] strArrSplit = str.split(":");
                if (strArrSplit.length == 2) {
                    try {
                        map.put(strArrSplit[0], Integer.valueOf(strArrSplit[1]));
                    } catch (NumberFormatException e) {
                        Log.e("ServiceRegistrarParser", "registrar configuration format error:" + e.getMessage());
                    }
                } else if (strArrSplit.length == 1) {
                    map.put(strArrSplit[0], 1000);
                } else {
                    Log.e("ServiceRegistrarParser", "registrar configuration error, " + str);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(map.entrySet());
        Collections.sort(arrayList2, new a());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(((Map.Entry) it.next()).getKey());
        }
        return arrayList;
    }

    private Bundle c() throws PackageManager.NameNotFoundException {
        ServiceInfo serviceInfo;
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            serviceInfo = packageManager.getServiceInfo(new ComponentName(this.a, (Class<?>) ServiceDiscovery.class), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ServiceRegistrarParser", "get ServiceDiscovery exception." + e.getLocalizedMessage());
        }
        if (serviceInfo == null) {
            Log.e("ServiceRegistrarParser", "Can not found ServiceDiscovery service.");
            return null;
        }
        return serviceInfo.metaData;
    }

    private static class a implements Serializable, Comparator<Map.Entry<String, Integer>> {
        private static final long serialVersionUID = 44523;

        private a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
            return entry.getValue().intValue() - entry2.getValue().intValue();
        }
    }
}
