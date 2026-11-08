package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.sigmob.sdk.common.mta.PointType;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ah;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.k;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b {
    private static int a;
    private Context b;
    private u c;
    private p d;
    private com.tencent.bugly.crashreport.common.strategy.a e;
    private o f;
    private BuglyStrategy.a g;

    public b(int i, Context context, u uVar, p pVar, com.tencent.bugly.crashreport.common.strategy.a aVar, BuglyStrategy.a aVar2, o oVar) {
        a = i;
        this.b = context;
        this.c = uVar;
        this.d = pVar;
        this.e = aVar;
        this.g = aVar2;
        this.f = oVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b <= jCurrentTimeMillis - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> listB;
        String[] strArrSplit;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0 && (listB = b(arrayList)) != null && listB.size() > 0) {
            Collections.sort(listB);
            CrashDetailBean crashDetailBean3 = null;
            for (int i = 0; i < listB.size(); i++) {
                CrashDetailBean crashDetailBean4 = listB.get(i);
                if (i == 0) {
                    crashDetailBean3 = crashDetailBean4;
                } else if (crashDetailBean4.s != null && (strArrSplit = crashDetailBean4.s.split("\n")) != null) {
                    for (String str : strArrSplit) {
                        if (!crashDetailBean3.s.contains(str)) {
                            crashDetailBean3.t++;
                            crashDetailBean3.s += str + "\n";
                        }
                    }
                }
            }
            crashDetailBean2 = crashDetailBean3;
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean2 = crashDetailBean;
        }
        for (a aVar2 : list) {
            if (!aVar2.e && !aVar2.d) {
                String str2 = crashDetailBean2.s;
                StringBuilder sb = new StringBuilder();
                sb.append(aVar2.b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean2.t++;
                    crashDetailBean2.s += aVar2.b + "\n";
                }
            }
        }
        if (crashDetailBean2.r != crashDetailBean.r) {
            String str3 = crashDetailBean2.s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean2.t++;
                crashDetailBean2.s += crashDetailBean.r + "\n";
            }
        }
        return crashDetailBean2;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return a(crashDetailBean, -123456789);
    }

    public final boolean a(CrashDetailBean crashDetailBean, int i) throws Throwable {
        if (crashDetailBean == null) {
            return true;
        }
        if (c.m != null && !c.m.isEmpty()) {
            x.c("Crash filter for crash stack is: %s", c.m);
            if (crashDetailBean.q.contains(c.m)) {
                x.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (c.n != null && !c.n.isEmpty()) {
            x.c("Crash regular filter for crash stack is: %s", c.n);
            if (Pattern.compile(c.n).matcher(crashDetailBean.q).find()) {
                x.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        int i2 = crashDetailBean.b;
        String str = crashDetailBean.n;
        String str2 = crashDetailBean.p;
        String str3 = crashDetailBean.q;
        long j = crashDetailBean.r;
        String str4 = crashDetailBean.m;
        String str5 = crashDetailBean.e;
        String str6 = crashDetailBean.c;
        if (this.f != null) {
            o oVar = this.f;
            String str7 = crashDetailBean.z;
            if (!oVar.c()) {
                x.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.b != 2) {
            r rVar = new r();
            rVar.b = 1;
            rVar.c = crashDetailBean.z;
            rVar.d = crashDetailBean.A;
            rVar.e = crashDetailBean.r;
            this.d.b(1);
            this.d.a(rVar);
            x.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            x.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> listB = b();
        ArrayList arrayList = null;
        if (listB != null && listB.size() > 0) {
            arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            arrayList.addAll(a(listB));
            listB.removeAll(arrayList);
            if (!com.tencent.bugly.b.c && c.d) {
                boolean z = false;
                for (a aVar : listB) {
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            z = true;
                        }
                        arrayList2.add(aVar);
                    }
                }
                if (z || arrayList2.size() >= c.c) {
                    x.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean crashDetailBeanA = a(arrayList2, crashDetailBean);
                    for (a aVar2 : arrayList2) {
                        if (aVar2.a != crashDetailBeanA.a) {
                            arrayList.add(aVar2);
                        }
                    }
                    d(crashDetailBeanA);
                    c(arrayList);
                    x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
        }
        d(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            c(arrayList);
        }
        x.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final List<CrashDetailBean> a() throws Throwable {
        StrategyBean strategyBeanC = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (strategyBeanC == null) {
            x.d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!strategyBeanC.g) {
            x.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            x.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jB = z.b();
        List<a> listB = b();
        if (listB == null || listB.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<a> it = listB.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.b < jB - c.g) {
                it.remove();
                arrayList.add(next);
            } else if (next.d) {
                if (next.b >= jCurrentTimeMillis - 86400000) {
                    it.remove();
                } else if (!next.e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f >= 3 && next.b < jCurrentTimeMillis - 86400000) {
                it.remove();
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            c(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        List<CrashDetailBean> listB2 = b(listB);
        if (listB2 != null && listB2.size() > 0) {
            String str = com.tencent.bugly.crashreport.common.info.a.b().j;
            Iterator<CrashDetailBean> it2 = listB2.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f)) {
                    it2.remove();
                    arrayList2.add(next2);
                }
            }
        }
        if (arrayList2.size() > 0) {
            d(arrayList2);
        }
        return listB2;
    }

    public final void b(CrashDetailBean crashDetailBean) {
        if (this.f != null) {
            o oVar = this.f;
            int i = crashDetailBean.b;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (c.l) {
            x.a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            a(arrayList, 3000L, z, crashDetailBean.b == 7, z);
        }
    }

    public final void a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        al alVar;
        if (com.tencent.bugly.crashreport.common.info.a.a(this.b).e && this.c != null) {
            if (z3 || this.c.b(c.a)) {
                StrategyBean strategyBeanC = this.e.c();
                if (!strategyBeanC.g) {
                    x.d("remote report is disable!", new Object[0]);
                    x.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
                    return;
                }
                if (list == null || list.size() == 0) {
                    return;
                }
                try {
                    String str = this.c.a ? strategyBeanC.s : strategyBeanC.t;
                    String str2 = this.c.a ? StrategyBean.c : StrategyBean.a;
                    int i = this.c.a ? 830 : 630;
                    Context context = this.b;
                    com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
                    if (context == null || list == null || list.size() == 0 || aVarB == null) {
                        x.d("enEXPPkg args == null!", new Object[0]);
                        alVar = null;
                    } else {
                        alVar = new al();
                        alVar.a = new ArrayList<>();
                        Iterator<CrashDetailBean> it = list.iterator();
                        while (it.hasNext()) {
                            alVar.a.add(a(context, it.next(), aVarB));
                        }
                    }
                    if (alVar == null) {
                        x.d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] bArrA = com.tencent.bugly.proguard.a.a((k) alVar);
                    if (bArrA == null) {
                        x.d("send encode fail!", new Object[0]);
                        return;
                    }
                    am amVarA = com.tencent.bugly.proguard.a.a(this.b, i, bArrA);
                    if (amVarA == null) {
                        x.d("request package is null.", new Object[0]);
                        return;
                    }
                    t tVar = new t() { // from class: com.tencent.bugly.crashreport.crash.b.1
                        @Override // com.tencent.bugly.proguard.t
                        public final void a(boolean z4) {
                            b bVar = b.this;
                            b.a(z4, (List<CrashDetailBean>) list);
                        }
                    };
                    if (z) {
                        this.c.a(a, amVarA, str, str2, tVar, j, z2);
                    } else {
                        this.c.a(a, amVarA, str, str2, tVar, false);
                    }
                } catch (Throwable th) {
                    x.e("req cr error %s", th.toString());
                    if (x.b(th)) {
                        return;
                    }
                    th.printStackTrace();
                }
            }
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            x.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                x.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.d = z;
                x.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                c.a().a(it.next());
            }
            x.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        x.b("[crash] upload fail.", new Object[0]);
    }

    public final void c(CrashDetailBean crashDetailBean) {
        int i;
        Map<String, String> mapOnCrashHandleStart;
        String strSubstring;
        HashMap map;
        if (crashDetailBean == null) {
            return;
        }
        if (this.g == null && this.f == null) {
            return;
        }
        try {
            x.a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
            switch (crashDetailBean.b) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 1;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 3;
                    break;
                case 5:
                    i = 5;
                    break;
                case 6:
                    i = 6;
                    break;
                case 7:
                    i = 7;
                    break;
                default:
                    return;
            }
            int i2 = crashDetailBean.b;
            String str = crashDetailBean.n;
            String str2 = crashDetailBean.p;
            String str3 = crashDetailBean.q;
            long j = crashDetailBean.r;
            byte[] bArrOnCrashHandleStart2GetExtraDatas = null;
            if (this.f != null) {
                o oVar = this.f;
                String strB = this.f.b();
                if (strB != null) {
                    map = new HashMap(1);
                    map.put("userData", strB);
                } else {
                    map = null;
                }
                mapOnCrashHandleStart = map;
            } else {
                mapOnCrashHandleStart = this.g != null ? this.g.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q) : null;
            }
            if (mapOnCrashHandleStart != null && mapOnCrashHandleStart.size() > 0) {
                crashDetailBean.N = new LinkedHashMap(mapOnCrashHandleStart.size());
                for (Map.Entry<String, String> entry : mapOnCrashHandleStart.entrySet()) {
                    if (!z.a(entry.getKey())) {
                        String key = entry.getKey();
                        if (key.length() > 100) {
                            key = key.substring(0, 100);
                            x.d("setted key length is over limit %d substring to %s", 100, key);
                        }
                        if (!z.a(entry.getValue()) && entry.getValue().length() > 30000) {
                            strSubstring = entry.getValue().substring(entry.getValue().length() - 30000);
                            x.d("setted %s value length is over limit %d substring", key, 30000);
                        } else {
                            strSubstring = entry.getValue();
                        }
                        crashDetailBean.N.put(key, strSubstring);
                        x.a("add setted key %s value size:%d", key, Integer.valueOf(strSubstring.length()));
                    }
                }
            }
            x.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
            if (this.f != null) {
                bArrOnCrashHandleStart2GetExtraDatas = this.f.a();
            } else if (this.g != null) {
                bArrOnCrashHandleStart2GetExtraDatas = this.g.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
            }
            crashDetailBean.S = bArrOnCrashHandleStart2GetExtraDatas;
            if (crashDetailBean.S != null) {
                if (crashDetailBean.S.length > 30000) {
                    x.d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.S.length), 30000);
                }
                x.a("add extra bytes %d ", Integer.valueOf(crashDetailBean.S.length));
            }
        } catch (Throwable th) {
            x.d("crash handle callback somthing wrong! %s", th.getClass().getName());
            if (x.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private static ContentValues e(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            contentValues.put("_up", Integer.valueOf(crashDetailBean.d ? 1 : 0));
            contentValues.put("_me", Integer.valueOf(crashDetailBean.j ? 1 : 0));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", z.a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) z.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void d(CrashDetailBean crashDetailBean) {
        ContentValues contentValuesE;
        if (crashDetailBean == null || (contentValuesE = e(crashDetailBean)) == null) {
            return;
        }
        long jA = p.a().a("t_cr", contentValuesE, (o) null, true);
        if (jA >= 0) {
            x.c("insert %s success!", "t_cr");
            crashDetailBean.a = jA;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> b(java.util.List<com.tencent.bugly.crashreport.crash.a> r13) throws java.lang.Throwable {
        /*
            r12 = this;
            r0 = 0
            if (r13 == 0) goto Le0
            int r1 = r13.size()
            if (r1 != 0) goto Lb
            goto Le0
        Lb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.Iterator r13 = r13.iterator()
        L14:
            boolean r2 = r13.hasNext()
            if (r2 == 0) goto L30
            java.lang.Object r2 = r13.next()
            com.tencent.bugly.crashreport.crash.a r2 = (com.tencent.bugly.crashreport.crash.a) r2
            java.lang.String r3 = " or _id"
            r1.append(r3)
            java.lang.String r3 = " = "
            r1.append(r3)
            long r2 = r2.a
            r1.append(r2)
            goto L14
        L30:
            java.lang.String r13 = r1.toString()
            int r2 = r13.length()
            r3 = 4
            if (r2 <= 0) goto L3f
            java.lang.String r13 = r13.substring(r3)
        L3f:
            r7 = r13
            r13 = 0
            r1.setLength(r13)
            com.tencent.bugly.proguard.p r4 = com.tencent.bugly.proguard.p.a()     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lc8
            java.lang.String r5 = "t_cr"
            r6 = 0
            r8 = 0
            r9 = 0
            r10 = 1
            android.database.Cursor r2 = r4.a(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lc8
            if (r2 != 0) goto L5a
            if (r2 == 0) goto L59
            r2.close()
        L59:
            return r0
        L5a:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            r4.<init>()     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
        L5f:
            boolean r5 = r2.moveToNext()     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            if (r5 == 0) goto L8f
            com.tencent.bugly.crashreport.crash.CrashDetailBean r5 = a(r2)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            if (r5 == 0) goto L6f
            r4.add(r5)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            goto L5f
        L6f:
            java.lang.String r5 = "_id"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> Ld9
            long r5 = r2.getLong(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> Ld9
            java.lang.String r7 = " or _id"
            r1.append(r7)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> Ld9
            java.lang.String r7 = " = "
            r1.append(r7)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> Ld9
            r1.append(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> Ld9
            goto L5f
        L87:
            java.lang.String r5 = "unknown id!"
            java.lang.Object[] r6 = new java.lang.Object[r13]     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            goto L5f
        L8f:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            int r5 = r1.length()     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            if (r5 <= 0) goto Lbd
            java.lang.String r8 = r1.substring(r3)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            com.tencent.bugly.proguard.p r6 = com.tencent.bugly.proguard.p.a()     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            java.lang.String r7 = "t_cr"
            r9 = 0
            r10 = 0
            r11 = 1
            int r1 = r6.a(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            java.lang.String r3 = "deleted %s illegle data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            java.lang.String r6 = "t_cr"
            r5[r13] = r6     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            r13 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            r5[r13] = r1     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
            com.tencent.bugly.proguard.x.d(r3, r5)     // Catch: java.lang.Throwable -> Lc3 java.lang.Throwable -> Ld9
        Lbd:
            if (r2 == 0) goto Lc2
            r2.close()
        Lc2:
            return r4
        Lc3:
            r13 = move-exception
            goto Lca
        Lc5:
            r13 = move-exception
            r2 = r0
            goto Lda
        Lc8:
            r13 = move-exception
            r2 = r0
        Lca:
            boolean r1 = com.tencent.bugly.proguard.x.a(r13)     // Catch: java.lang.Throwable -> Ld9
            if (r1 != 0) goto Ld3
            r13.printStackTrace()     // Catch: java.lang.Throwable -> Ld9
        Ld3:
            if (r2 == 0) goto Ld8
            r2.close()
        Ld8:
            return r0
        Ld9:
            r13 = move-exception
        Lda:
            if (r2 == 0) goto Ldf
            r2.close()
        Ldf:
            throw r13
        Le0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b(java.util.List):java.util.List");
    }

    private static a b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            aVar.e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private List<a> b() throws Throwable {
        Cursor cursorA;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursorA = p.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
                if (cursorA == null) {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    return null;
                }
                try {
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (cursorA.moveToNext()) {
                            a aVarB = b(cursorA);
                            if (aVarB != null) {
                                arrayList.add(aVarB);
                            } else {
                                try {
                                    long j = cursorA.getLong(cursorA.getColumnIndex("_id"));
                                    sb.append(" or _id");
                                    sb.append(" = ");
                                    sb.append(j);
                                } catch (Throwable unused) {
                                    x.d("unknown id!", new Object[0]);
                                }
                            }
                        }
                        String string = sb.toString();
                        if (string.length() > 0) {
                            x.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", string.substring(4), (String[]) null, (o) null, true)));
                        }
                        if (cursorA != null) {
                            cursorA.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        if (cursorA != null) {
                            cursorA.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorA;
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            cursorA = cursor;
        }
    }

    private static void c(List<a> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (a aVar : list) {
            sb.append(" or _id");
            sb.append(" = ");
            sb.append(aVar.a);
        }
        String string = sb.toString();
        if (string.length() > 0) {
            string = string.substring(4);
        }
        String str = string;
        sb.setLength(0);
        try {
            x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", str, (String[]) null, (o) null, true)));
        } catch (Throwable th) {
            if (x.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private static void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (CrashDetailBean crashDetailBean : list) {
                    sb.append(" or _id");
                    sb.append(" = ");
                    sb.append(crashDetailBean.a);
                }
                String string = sb.toString();
                if (string.length() > 0) {
                    string = string.substring(4);
                }
                sb.setLength(0);
                x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", string, (String[]) null, (o) null, true)));
            } catch (Throwable th) {
                if (x.a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    private static ak a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) throws Throwable {
        aj ajVarA;
        aj ajVarA2;
        aj ajVar;
        if (context == null || crashDetailBean == null || aVar == null) {
            x.d("enExp args == null", new Object[0]);
            return null;
        }
        ak akVar = new ak();
        switch (crashDetailBean.b) {
            case 0:
                akVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                akVar.a = crashDetailBean.j ? "201" : PointType.ANTI_SPAM;
                break;
            case 2:
                akVar.a = crashDetailBean.j ? "202" : PointType.ANTI_SPAM_TOUCH;
                break;
            case 3:
                akVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                akVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                akVar.a = crashDetailBean.j ? "207" : PointType.WIND_ACTIVE;
                break;
            case 6:
                akVar.a = crashDetailBean.j ? "206" : "106";
                break;
            case 7:
                akVar.a = crashDetailBean.j ? "208" : "108";
                break;
            default:
                x.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        akVar.b = crashDetailBean.r;
        akVar.c = crashDetailBean.n;
        akVar.d = crashDetailBean.o;
        akVar.e = crashDetailBean.p;
        akVar.g = crashDetailBean.q;
        akVar.h = crashDetailBean.y;
        akVar.i = crashDetailBean.c;
        akVar.j = null;
        akVar.l = crashDetailBean.m;
        akVar.m = crashDetailBean.e;
        akVar.f = crashDetailBean.A;
        akVar.t = com.tencent.bugly.crashreport.common.info.a.b().i();
        akVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            akVar.o = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.i.entrySet()) {
                ah ahVar = new ah();
                ahVar.a = entry.getValue().a;
                ahVar.c = entry.getValue().c;
                ahVar.d = entry.getValue().b;
                ahVar.b = aVar.r();
                akVar.o.add(ahVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            akVar.p = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry2 : crashDetailBean.h.entrySet()) {
                ah ahVar2 = new ah();
                ahVar2.a = entry2.getValue().a;
                ahVar2.c = entry2.getValue().c;
                ahVar2.d = entry2.getValue().b;
                akVar.p.add(ahVar2);
            }
        }
        if (crashDetailBean.j) {
            akVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (akVar.q == null) {
                    akVar.q = new ArrayList<>();
                }
                try {
                    akVar.q.add(new aj((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    akVar.q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(akVar.k);
            objArr[1] = Integer.valueOf(akVar.q != null ? akVar.q.size() : 0);
            x.c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.w != null) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            try {
                akVar.q.add(new aj((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                akVar.q = null;
            }
        }
        if (!z.a(crashDetailBean.T)) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            try {
                ajVar = new aj((byte) 1, "crashInfos.txt", crashDetailBean.T.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                ajVar = null;
            }
            if (ajVar != null) {
                x.c("attach crash infos", new Object[0]);
                akVar.q.add(ajVar);
            }
        }
        if (crashDetailBean.U != null) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            aj ajVarA3 = a("backupRecord.zip", context, crashDetailBean.U);
            if (ajVarA3 != null) {
                x.c("attach backup record", new Object[0]);
                akVar.q.add(ajVarA3);
            }
        }
        if (crashDetailBean.x != null && crashDetailBean.x.length > 0) {
            aj ajVar2 = new aj((byte) 2, "buglylog.zip", crashDetailBean.x);
            x.c("attach user log", new Object[0]);
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            akVar.q.add(ajVar2);
        }
        if (crashDetailBean.b == 3) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            if (crashDetailBean.N != null && crashDetailBean.N.containsKey("BUGLY_CR_01")) {
                try {
                    akVar.q.add(new aj((byte) 1, "anrMessage.txt", crashDetailBean.N.get("BUGLY_CR_01").getBytes("utf-8")));
                    x.c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                    akVar.q = null;
                }
                crashDetailBean.N.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null && (ajVarA2 = a("trace.zip", context, crashDetailBean.v)) != null) {
                x.c("attach traces", new Object[0]);
                akVar.q.add(ajVarA2);
            }
        }
        if (crashDetailBean.b == 1) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            if (crashDetailBean.v != null && (ajVarA = a("tomb.zip", context, crashDetailBean.v)) != null) {
                x.c("attach tombs", new Object[0]);
                akVar.q.add(ajVarA);
            }
        }
        if (aVar.C != null && !aVar.C.isEmpty()) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = aVar.C.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            try {
                akVar.q.add(new aj((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                x.c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.length > 0) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            akVar.q.add(new aj((byte) 1, "userExtraByteData", crashDetailBean.S));
            x.c("attach extraData", new Object[0]);
        }
        akVar.r = new HashMap();
        Map<String, String> map = akVar.r;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.B);
        map.put("A9", sb2.toString());
        Map<String, String> map2 = akVar.r;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.C);
        map2.put("A11", sb3.toString());
        Map<String, String> map3 = akVar.r;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(crashDetailBean.D);
        map3.put("A10", sb4.toString());
        akVar.r.put("A23", crashDetailBean.f);
        akVar.r.put("A7", aVar.f);
        akVar.r.put("A6", aVar.s());
        akVar.r.put("A5", aVar.r());
        akVar.r.put("A22", aVar.h());
        Map<String, String> map4 = akVar.r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.F);
        map4.put("A2", sb5.toString());
        Map<String, String> map5 = akVar.r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.E);
        map5.put("A1", sb6.toString());
        akVar.r.put("A24", aVar.h);
        Map<String, String> map6 = akVar.r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.G);
        map6.put("A17", sb7.toString());
        akVar.r.put("A3", aVar.k());
        akVar.r.put("A16", aVar.m());
        akVar.r.put("A25", aVar.n());
        akVar.r.put("A14", aVar.l());
        akVar.r.put("A15", aVar.w());
        Map<String, String> map7 = akVar.r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(aVar.x());
        map7.put("A13", sb8.toString());
        akVar.r.put("A34", crashDetailBean.z);
        if (aVar.x != null) {
            akVar.r.put("productIdentify", aVar.x);
        }
        try {
            akVar.r.put("A26", URLEncoder.encode(crashDetailBean.H, "utf-8"));
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            akVar.r.put("A27", crashDetailBean.J);
            akVar.r.put("A28", crashDetailBean.I);
            Map<String, String> map8 = akVar.r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.k);
            map8.put("A29", sb9.toString());
        }
        akVar.r.put("A30", crashDetailBean.K);
        Map<String, String> map9 = akVar.r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.L);
        map9.put("A18", sb10.toString());
        Map<String, String> map10 = akVar.r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.M);
        map10.put("A36", sb11.toString());
        Map<String, String> map11 = akVar.r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(aVar.q);
        map11.put("F02", sb12.toString());
        Map<String, String> map12 = akVar.r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(aVar.r);
        map12.put("F03", sb13.toString());
        akVar.r.put("F04", aVar.e());
        Map<String, String> map13 = akVar.r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(aVar.s);
        map13.put("F05", sb14.toString());
        akVar.r.put("F06", aVar.p);
        akVar.r.put("F08", aVar.v);
        akVar.r.put("F09", aVar.w);
        Map<String, String> map14 = akVar.r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(aVar.t);
        map14.put("F10", sb15.toString());
        if (crashDetailBean.O >= 0) {
            Map<String, String> map15 = akVar.r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.O);
            map15.put("C01", sb16.toString());
        }
        if (crashDetailBean.P >= 0) {
            Map<String, String> map16 = akVar.r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.P);
            map16.put("C02", sb17.toString());
        }
        if (crashDetailBean.Q != null && crashDetailBean.Q.size() > 0) {
            for (Map.Entry<String, String> entry3 : crashDetailBean.Q.entrySet()) {
                akVar.r.put("C03_" + entry3.getKey(), entry3.getValue());
            }
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Map.Entry<String, String> entry4 : crashDetailBean.R.entrySet()) {
                akVar.r.put("C04_" + entry4.getKey(), entry4.getValue());
            }
        }
        akVar.s = null;
        if (crashDetailBean.N != null && crashDetailBean.N.size() > 0) {
            akVar.s = crashDetailBean.N;
            x.a("setted message size %d", Integer.valueOf(akVar.s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.e();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        objArr2[7] = Boolean.valueOf(crashDetailBean.b == 1);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(akVar.r.size());
        x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return akVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.tencent.bugly.proguard.aj a(java.lang.String r5, android.content.Context r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            r0 = 0
            r1 = 0
            if (r7 == 0) goto Ld9
            if (r6 != 0) goto L8
            goto Ld9
        L8:
            java.lang.String r2 = "zip %s"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r1] = r7
            com.tencent.bugly.proguard.x.c(r2, r4)
            java.io.File r2 = new java.io.File
            r2.<init>(r7)
            java.io.File r7 = new java.io.File
            java.io.File r6 = r6.getCacheDir()
            r7.<init>(r6, r5)
            r5 = 5000(0x1388, float:7.006E-42)
            boolean r5 = com.tencent.bugly.proguard.z.a(r2, r7, r5)
            if (r5 != 0) goto L30
            java.lang.String r5 = "zip fail!"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.d(r5, r6)
            return r0
        L30:
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L88 java.lang.Throwable -> L8b
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Throwable -> L8b
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
        L3e:
            int r4 = r6.read(r2)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            if (r4 <= 0) goto L4b
            r5.write(r2, r1, r4)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            r5.flush()     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            goto L3e
        L4b:
            byte[] r5 = r5.toByteArray()     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            java.lang.String r2 = "read bytes :%d"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            int r4 = r5.length     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            r3[r1] = r4     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            com.tencent.bugly.proguard.x.c(r2, r3)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            com.tencent.bugly.proguard.aj r2 = new com.tencent.bugly.proguard.aj     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            r3 = 2
            java.lang.String r4 = r7.getName()     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            r2.<init>(r3, r4, r5)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> Lb7
            r6.close()     // Catch: java.io.IOException -> L6b
            goto L75
        L6b:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)
            if (r6 != 0) goto L75
            r5.printStackTrace()
        L75:
            boolean r5 = r7.exists()
            if (r5 == 0) goto L85
            java.lang.String r5 = "del tmp"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r6)
            r7.delete()
        L85:
            return r2
        L86:
            r5 = move-exception
            goto L8d
        L88:
            r5 = move-exception
            r6 = r0
            goto Lb8
        L8b:
            r5 = move-exception
            r6 = r0
        L8d:
            boolean r2 = com.tencent.bugly.proguard.x.a(r5)     // Catch: java.lang.Throwable -> Lb7
            if (r2 != 0) goto L96
            r5.printStackTrace()     // Catch: java.lang.Throwable -> Lb7
        L96:
            if (r6 == 0) goto La6
            r6.close()     // Catch: java.io.IOException -> L9c
            goto La6
        L9c:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)
            if (r6 != 0) goto La6
            r5.printStackTrace()
        La6:
            boolean r5 = r7.exists()
            if (r5 == 0) goto Lb6
            java.lang.String r5 = "del tmp"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r6)
            r7.delete()
        Lb6:
            return r0
        Lb7:
            r5 = move-exception
        Lb8:
            if (r6 == 0) goto Lc8
            r6.close()     // Catch: java.io.IOException -> Lbe
            goto Lc8
        Lbe:
            r6 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r6)
            if (r0 != 0) goto Lc8
            r6.printStackTrace()
        Lc8:
            boolean r6 = r7.exists()
            if (r6 == 0) goto Ld8
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r0 = "del tmp"
            com.tencent.bugly.proguard.x.c(r0, r6)
            r7.delete()
        Ld8:
            throw r5
        Ld9:
            java.lang.String r5 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.d(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.proguard.aj");
    }

    public static void a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        String str5;
        com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
        if (aVarB == null) {
            return;
        }
        x.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        x.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        x.e("# PKG NAME: %s", aVarB.c);
        x.e("# APP VER: %s", aVarB.j);
        x.e("# LAUNCH TIME: %s", z.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a)));
        x.e("# CRASH TYPE: %s", str);
        x.e("# CRASH TIME: %s", str2);
        x.e("# CRASH PROCESS: %s", str3);
        if (thread != null) {
            x.e("# CRASH THREAD: %s", thread.getName());
        }
        if (crashDetailBean != null) {
            x.e("# REPORT ID: %s", crashDetailBean.c);
            Object[] objArr = new Object[2];
            objArr[0] = aVarB.g;
            objArr[1] = aVarB.x().booleanValue() ? "ROOTED" : "UNROOT";
            x.e("# CRASH DEVICE: %s %s", objArr);
            x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.B), Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D));
            x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.E), Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G));
            if (!z.a(crashDetailBean.J)) {
                x.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.J, crashDetailBean.I);
            } else if (crashDetailBean.b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.N == null) {
                    str5 = "null";
                } else {
                    str5 = crashDetailBean.N.get("BUGLY_CR_01");
                }
                objArr2[0] = str5;
                x.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!z.a(str4)) {
            x.e("# CRASH STACK: ", new Object[0]);
            x.e(str4, new Object[0]);
        }
        x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }
}
