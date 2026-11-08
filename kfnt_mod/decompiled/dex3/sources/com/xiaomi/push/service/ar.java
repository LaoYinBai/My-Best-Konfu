package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.push.hp;
import com.xiaomi.push.hq;
import com.xiaomi.push.hs;
import com.xiaomi.push.hu;
import com.xiaomi.push.ig;
import com.xiaomi.push.ih;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ar {
    public static int a(aq aqVar, hp hpVar) {
        String strA = a(hpVar);
        int i = 0;
        switch (hpVar) {
            case MISC_CONFIG:
                i = 1;
                break;
        }
        return aqVar.f890a.getInt(strA, i);
    }

    private static String a(hp hpVar) {
        return "oc_version_" + hpVar.a();
    }

    private static List<Pair<Integer, Object>> a(List<hu> list, boolean z) {
        Pair pair;
        if (com.xiaomi.push.ad.a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (hu huVar : list) {
            int iA = huVar.a();
            hq hqVarA = hq.a(huVar.b());
            if (hqVarA != null) {
                if (z && huVar.f515a) {
                    arrayList.add(new Pair(Integer.valueOf(iA), null));
                } else {
                    switch (hqVarA) {
                        case INT:
                            pair = new Pair(Integer.valueOf(iA), Integer.valueOf(huVar.c()));
                            break;
                        case LONG:
                            pair = new Pair(Integer.valueOf(iA), Long.valueOf(huVar.m403a()));
                            break;
                        case STRING:
                            pair = new Pair(Integer.valueOf(iA), huVar.m404a());
                            break;
                        case BOOLEAN:
                            pair = new Pair(Integer.valueOf(iA), Boolean.valueOf(huVar.g()));
                            break;
                        default:
                            pair = null;
                            break;
                    }
                    arrayList.add(pair);
                }
            }
        }
        return arrayList;
    }

    public static void a(aq aqVar, hp hpVar, int i) {
        aqVar.f890a.edit().putInt(a(hpVar), i).commit();
    }

    public static void a(aq aqVar, ig igVar) {
        aqVar.b(a(igVar.a(), true));
        aqVar.b();
    }

    public static void a(aq aqVar, ih ihVar) {
        for (hs hsVar : ihVar.a()) {
            if (hsVar.a() > a(aqVar, hsVar.m399a())) {
                a(aqVar, hsVar.m399a(), hsVar.a());
                aqVar.a(a(hsVar.f507a, false));
            }
        }
        aqVar.b();
    }
}
