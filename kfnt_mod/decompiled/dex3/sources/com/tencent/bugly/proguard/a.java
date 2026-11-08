package com.tencent.bugly.proguard;

import android.content.Context;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    i c;
    private HashMap<String, Object> d;

    public static ag a(int i) {
        if (i == 1) {
            return new af();
        }
        if (i == 3) {
            return new ae();
        }
        return null;
    }

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new i();
    }

    public void a(String str) {
        this.b = str;
    }

    public static aq a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        aq aqVar = new aq();
        aqVar.a = userInfoBean.e;
        aqVar.e = userInfoBean.j;
        aqVar.d = userInfoBean.c;
        aqVar.c = userInfoBean.d;
        aqVar.g = com.tencent.bugly.crashreport.common.info.a.b().i();
        aqVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case 1:
                aqVar.b = (byte) 1;
                break;
            case 2:
                aqVar.b = (byte) 4;
                break;
            case 3:
                aqVar.b = (byte) 2;
                break;
            case 4:
                aqVar.b = (byte) 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    aqVar.b = (byte) userInfoBean.b;
                    break;
                } else {
                    x.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                    return null;
                }
                break;
        }
        aqVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            Map<String, String> map = aqVar.f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.q >= 0) {
            Map<String, String> map2 = aqVar.f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.r.entrySet()) {
                aqVar.f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.s.entrySet()) {
                aqVar.f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = aqVar.f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.l);
        map3.put("A36", sb3.toString());
        Map<String, String> map4 = aqVar.f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.g);
        map4.put("F02", sb4.toString());
        Map<String, String> map5 = aqVar.f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.h);
        map5.put("F03", sb5.toString());
        aqVar.f.put("F04", userInfoBean.j);
        Map<String, String> map6 = aqVar.f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.i);
        map6.put("F05", sb6.toString());
        aqVar.f.put("F06", userInfoBean.m);
        Map<String, String> map7 = aqVar.f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.k);
        map7.put("F10", sb7.toString());
        x.c("summary type %d vm:%d", Byte.valueOf(aqVar.b), Integer.valueOf(aqVar.f.size()));
        return aqVar;
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals(PushMultiProcessSharedProvider.BOOLEAN_TYPE)) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals(PushMultiProcessSharedProvider.FLOAT_TYPE)) {
                str = PushMultiProcessSharedProvider.FLOAT_TYPE;
            } else if (str.equals("java.lang.Long") || str.equals(PushMultiProcessSharedProvider.LONG_TYPE)) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else {
                if (str.equals("java.lang.Character")) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                }
                if (str.equals("java.lang.String")) {
                    str = PushMultiProcessSharedProvider.STRING_TYPE;
                } else if (str.equals("java.util.List")) {
                    str = "list";
                } else if (str.equals("java.util.Map")) {
                    str = "map";
                }
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = arrayList.get(i2);
            if (str2.equals("list")) {
                int i3 = i2 - 1;
                arrayList.set(i3, "<" + arrayList.get(i3));
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("map")) {
                int i4 = i2 - 1;
                arrayList.set(i4, "<" + arrayList.get(i4) + Constants.ACCEPT_TIME_SEPARATOR_SP);
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("Array")) {
                int i5 = i2 - 1;
                arrayList.set(i5, "<" + arrayList.get(i5));
                arrayList.set(0, arrayList.get(0) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    public <T> void a(String str, T t) throws UnsupportedEncodingException {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        j jVar = new j();
        jVar.a(this.b);
        jVar.a(t, 0);
        byte[] bArrA = l.a(jVar.a());
        HashMap<String, byte[]> map = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        a(arrayList, t);
        map.put(a(arrayList), bArrA);
        this.d.remove(str);
        this.a.put(str, map);
    }

    public static ar a(List<UserInfoBean> list, int i) {
        com.tencent.bugly.crashreport.common.info.a aVarB;
        if (list == null || list.size() == 0 || (aVarB = com.tencent.bugly.crashreport.common.info.a.b()) == null) {
            return null;
        }
        aVarB.t();
        ar arVar = new ar();
        arVar.b = aVarB.d;
        arVar.c = aVarB.h();
        ArrayList<aq> arrayList = new ArrayList<>();
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            aq aqVarA = a(it.next());
            if (aqVarA != null) {
                arrayList.add(aqVarA);
            }
        }
        arVar.d = arrayList;
        arVar.e = new HashMap();
        arVar.e.put("A7", aVarB.f);
        arVar.e.put("A6", aVarB.s());
        arVar.e.put("A5", aVarB.r());
        Map<String, String> map = arVar.e;
        StringBuilder sb = new StringBuilder();
        sb.append(aVarB.p());
        map.put("A2", sb.toString());
        Map<String, String> map2 = arVar.e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(aVarB.p());
        map2.put("A1", sb2.toString());
        arVar.e.put("A24", aVarB.h);
        Map<String, String> map3 = arVar.e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(aVarB.q());
        map3.put("A17", sb3.toString());
        arVar.e.put("A15", aVarB.w());
        Map<String, String> map4 = arVar.e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(aVarB.x());
        map4.put("A13", sb4.toString());
        arVar.e.put("F08", aVarB.v);
        arVar.e.put("F09", aVarB.w);
        Map<String, String> mapE = aVarB.E();
        if (mapE != null && mapE.size() > 0) {
            for (Map.Entry<String, String> entry : mapE.entrySet()) {
                arVar.e.put("C04_" + entry.getKey(), entry.getValue());
            }
        }
        switch (i) {
            case 1:
                arVar.a = (byte) 1;
                return arVar;
            case 2:
                arVar.a = (byte) 2;
                return arVar;
            default:
                x.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
    }

    public static <T extends k> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T tNewInstance = cls.newInstance();
            i iVar = new i(bArr);
            iVar.a("utf-8");
            tNewInstance.a(iVar);
            return tNewInstance;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static am a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
        StrategyBean strategyBeanC = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (aVarB == null || strategyBeanC == null) {
            x.e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            am amVar = new am();
            synchronized (aVarB) {
                amVar.a = 1;
                amVar.b = aVarB.f();
                amVar.c = aVarB.c;
                amVar.d = aVarB.j;
                amVar.e = aVarB.l;
                aVarB.getClass();
                amVar.f = "2.6.5";
                amVar.g = i;
                amVar.h = bArr == null ? "".getBytes() : bArr;
                amVar.i = aVarB.g;
                amVar.j = aVarB.h;
                amVar.k = new HashMap();
                amVar.l = aVarB.e();
                amVar.m = strategyBeanC.p;
                amVar.o = aVarB.h();
                amVar.p = com.tencent.bugly.crashreport.common.info.b.e(context);
                amVar.q = System.currentTimeMillis();
                amVar.r = aVarB.k();
                amVar.s = aVarB.j();
                amVar.t = aVarB.m();
                amVar.u = aVarB.l();
                amVar.v = aVarB.n();
                amVar.w = amVar.p;
                aVarB.getClass();
                amVar.n = "com.tencent.bugly";
                amVar.k.put("A26", aVarB.y());
                Map<String, String> map = amVar.k;
                StringBuilder sb = new StringBuilder();
                sb.append(aVarB.z);
                map.put("F11", sb.toString());
                Map<String, String> map2 = amVar.k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(aVarB.y);
                map2.put("F12", sb2.toString());
                amVar.k.put("G1", aVarB.u());
                if (aVarB.B) {
                    amVar.k.put("G2", aVarB.K());
                    amVar.k.put("G3", aVarB.L());
                    amVar.k.put("G4", aVarB.M());
                    amVar.k.put("G5", aVarB.N());
                    amVar.k.put("G6", aVarB.O());
                    amVar.k.put("G7", Long.toString(aVarB.P()));
                }
                amVar.k.put("D3", aVarB.k);
                if (com.tencent.bugly.b.b != null) {
                    for (com.tencent.bugly.a aVar : com.tencent.bugly.b.b) {
                        if (aVar.versionKey != null && aVar.version != null) {
                            amVar.k.put(aVar.versionKey, aVar.version);
                        }
                    }
                }
                amVar.k.put("G15", z.b("G15", ""));
                amVar.k.put("D4", z.b("D4", com.sigmob.sdk.common.Constants.FAIL));
            }
            u uVarA = u.a();
            if (uVarA != null && !uVarA.a && bArr != null) {
                amVar.h = z.a(amVar.h, 2, 1, strategyBeanC.u);
                if (amVar.h == null) {
                    x.e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map<String, String> mapD = aVarB.D();
            if (mapD != null) {
                for (Map.Entry<String, String> entry : mapD.entrySet()) {
                    amVar.k.put(entry.getKey(), entry.getValue());
                }
            }
            return amVar;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    public byte[] a() throws UnsupportedEncodingException {
        j jVar = new j(0);
        jVar.a(this.b);
        jVar.a((Map) this.a, 0);
        return l.a(jVar.a());
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap map = new HashMap(1);
        HashMap map2 = new HashMap(1);
        map2.put("", new byte[0]);
        map.put("", map2);
        this.a = this.c.a((Map) map, 0, false);
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.b();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", (String) obj);
            return dVar.a();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static an a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.b();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object objB = dVar.b("detail", new an());
                an anVar = an.class.isInstance(objB) ? (an) an.class.cast(objB) : null;
                if (!z && anVar != null && anVar.c != null && anVar.c.length > 0) {
                    x.c("resp buf %d", Integer.valueOf(anVar.c.length));
                    anVar.c = z.b(anVar.c, 2, 1, StrategyBean.d);
                    if (anVar.c == null) {
                        x.e("resp sbuffer error!", new Object[0]);
                        return null;
                    }
                }
                return anVar;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(k kVar) {
        try {
            j jVar = new j();
            jVar.a("utf-8");
            kVar.a(jVar);
            return jVar.b();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
