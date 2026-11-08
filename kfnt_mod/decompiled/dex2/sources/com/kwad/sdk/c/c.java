package com.kwad.sdk.c;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import androidx.annotation.NonNull;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.c.d;
import com.kwad.sdk.core.NetworkMonitor;
import com.kwad.sdk.crash.utils.h;
import com.kwad.sdk.utils.q;
import com.kwad.sdk.utils.t;
import com.kwai.filedownloader.r;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static volatile c a;
    private d b;
    private Context d;
    private b e;
    private List<com.kwad.sdk.c.kwai.d> f;
    private List<com.kwad.sdk.c.kwai.d> g;
    private List<com.kwad.sdk.c.kwai.d> h;
    private List<com.kwad.sdk.c.kwai.d> k;
    private com.kwad.sdk.c.a.c m;
    private final Map<String, String> c = new HashMap();
    private volatile boolean j = false;
    private final LinkedHashSet<String> l = new LinkedHashSet<>();
    private final NetworkMonitor.a n = new NetworkMonitor.a() { // from class: com.kwad.sdk.c.c.2
        @Override // com.kwad.sdk.core.NetworkMonitor.a
        public void a(NetworkMonitor.NetworkState networkState) {
            if ((networkState == NetworkMonitor.NetworkState.NETWORK_WIFI || networkState == NetworkMonitor.NetworkState.NETWORK_MOBILE) && c.this.g != null && c.this.g.size() > 0) {
                c.this.b();
            }
        }
    };
    private final Lock i = new ReentrantLock();

    private c() {
    }

    private WebResourceResponse a(String str, String str2) {
        synchronized (this.c) {
            String str3 = this.c.get(str);
            com.kwad.sdk.core.d.a.a("WebResourceResponse", str3 + " | " + str2 + " | " + str + "| packageStatusMap size:" + this.c.size());
            if (TextUtils.isEmpty(str3)) {
                b(str);
                return null;
            }
            synchronized (this.b) {
                com.kwad.sdk.c.kwai.d dVarC = c(str);
                if (dVarC == null) {
                    return null;
                }
                return this.b.a(str2, dVarC, str3);
            }
        }
    }

    public static synchronized c a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    @NonNull
    private List<com.kwad.sdk.c.kwai.d> a(File file) {
        FileInputStream fileInputStream;
        String strB;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            return new ArrayList();
        }
        try {
            strB = h.b(new InputStreamReader(fileInputStream));
        } catch (IOException e2) {
            e2.printStackTrace();
            strB = null;
        }
        return t.a(strB, new com.kwad.sdk.core.c<com.kwad.sdk.c.kwai.d>() { // from class: com.kwad.sdk.c.c.1
            @Override // com.kwad.sdk.core.c
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public com.kwad.sdk.c.kwai.d a() {
                return new com.kwad.sdk.c.kwai.d();
            }
        });
    }

    private void a(List<com.kwad.sdk.c.kwai.d> list) {
        List<com.kwad.sdk.c.kwai.d> list2;
        if (list.size() <= 0) {
            return;
        }
        this.k = new ArrayList();
        for (com.kwad.sdk.c.kwai.d dVar : list) {
            for (com.kwad.sdk.c.kwai.d dVar2 : this.g) {
                if (TextUtils.equals(dVar.d, dVar2.d)) {
                    if (dVar.c().equals(dVar2.c())) {
                        if (this.h == null) {
                            this.h = new ArrayList();
                        }
                        list2 = this.h;
                    } else {
                        list2 = this.k;
                    }
                    list2.add(dVar);
                }
            }
        }
        a(this.g, this.h);
        a(list, this.k);
        b(this.k);
    }

    private void a(List<com.kwad.sdk.c.kwai.d> list, List<com.kwad.sdk.c.kwai.d> list2) {
        if (list == null || list2 == null) {
            return;
        }
        for (com.kwad.sdk.c.kwai.d dVar : list2) {
            for (com.kwad.sdk.c.kwai.d dVar2 : list) {
                if (a(dVar, dVar2)) {
                    list.remove(dVar2);
                }
            }
        }
    }

    private boolean a(com.kwad.sdk.c.kwai.d dVar, com.kwad.sdk.c.kwai.d dVar2) {
        return TextUtils.equals(dVar.d, dVar2.d) && TextUtils.equals(dVar.c(), dVar2.c());
    }

    private boolean a(com.kwad.sdk.c.kwai.d dVar, String str) {
        return dVar.g == 3 && TextUtils.equals(dVar.b(), str);
    }

    private void b(Context context) {
        this.f = c(context);
        if (this.f == null || this.f.size() <= 0) {
            return;
        }
        for (com.kwad.sdk.c.kwai.d dVar : this.f) {
            synchronized (this.c) {
                this.c.put(dVar.b(), dVar.b);
                if (dVar.i) {
                    this.l.add(dVar.b());
                }
            }
        }
    }

    private void b(List<com.kwad.sdk.c.kwai.d> list) {
        Iterator<com.kwad.sdk.c.kwai.d> it = list.iterator();
        while (it.hasNext()) {
            String strA = com.kwad.sdk.c.b.a.a(this.d, it.next().f());
            if (!TextUtils.isEmpty(strA)) {
                q.c(new File(strA));
            }
        }
    }

    private com.kwad.sdk.c.kwai.d c(String str) {
        for (int i = 0; i < this.f.size(); i++) {
            if (TextUtils.equals(str, this.f.get(i).a)) {
                return this.f.get(i);
            }
        }
        return null;
    }

    private List<com.kwad.sdk.c.kwai.d> c() {
        List<com.kwad.sdk.c.kwai.c> list = com.kwad.sdk.core.config.b.aP().h5PreloadConfigs;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.kwad.sdk.c.kwai.c cVar : list) {
            for (com.kwad.sdk.c.kwai.d dVar : cVar.a()) {
                dVar.a(cVar.b());
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    private List<com.kwad.sdk.c.kwai.d> c(Context context) {
        String strC = com.kwad.sdk.c.b.a.c(context);
        if (strC == null) {
            return null;
        }
        File file = new File(strC);
        if (file.exists()) {
            return a(file);
        }
        return null;
    }

    private void d() {
        if (e()) {
            NetworkMonitor.a().a(this.n);
        }
    }

    private boolean d(com.kwad.sdk.c.kwai.d dVar) {
        if (dVar.g != 1) {
            return com.ksad.download.c.b.b(this.d) && dVar.g == 2;
        }
        return true;
    }

    private void e(com.kwad.sdk.c.kwai.d dVar) {
        if (this.g == null) {
            return;
        }
        int iIndexOf = this.g.indexOf(dVar);
        if (iIndexOf >= 0) {
            this.g.remove(iIndexOf);
        }
        if (this.j) {
            d();
        }
    }

    private boolean e() {
        if (this.g == null || this.g.size() <= 0) {
            return false;
        }
        Iterator<com.kwad.sdk.c.kwai.d> it = this.g.iterator();
        while (it.hasNext()) {
            if (it.next().g != 3) {
                return true;
            }
        }
        return false;
    }

    private void f(com.kwad.sdk.c.kwai.d dVar) throws IOException {
        boolean zCreateNewFile;
        com.kwad.sdk.c.b.b.a(dVar, 3);
        com.kwad.sdk.core.d.a.a("updatePackageIndexFile", dVar.a + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + dVar.e);
        File file = new File(com.kwad.sdk.c.b.a.c(this.d));
        if (!file.exists()) {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e) {
                com.kwad.sdk.c.b.b.a(dVar, 3, e.getMessage());
                zCreateNewFile = false;
            }
            if (!zCreateNewFile) {
                return;
            }
        }
        if (this.f == null) {
            this.f = a(file);
        }
        int iIndexOf = this.f.indexOf(dVar);
        if (iIndexOf >= 0) {
            this.f.get(iIndexOf).b(dVar.e);
        } else {
            this.f.add(dVar);
        }
        String string = t.b(this.f).toString();
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    try {
                        fileOutputStream.write(string.getBytes());
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        com.kwad.sdk.core.d.a.e("updatePackageIndexFile", "write packageIndex file error");
                        fileOutputStream.close();
                    }
                } finally {
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Exception unused2) {
            com.kwad.sdk.core.d.a.e("updatePackageIndexFile", "read packageIndex file error");
        }
    }

    public WebResourceResponse a(String str) {
        String strA = this.b.a(str);
        if (!TextUtils.isEmpty(strA)) {
            this.l.add(strA);
            return a(strA, str);
        }
        Iterator<String> it = this.l.iterator();
        while (it.hasNext()) {
            WebResourceResponse webResourceResponseA = a(it.next(), str);
            if (webResourceResponseA != null) {
                return webResourceResponseA;
            }
        }
        return null;
    }

    public void a(Context context) {
        if (com.kwad.sdk.core.config.b.J()) {
            this.d = context;
            this.b = new d(context);
            this.e = new b(context);
            r.a(context);
            b(context);
            this.m = new d.a();
        }
    }

    public void a(com.kwad.sdk.c.kwai.d dVar) throws IOException {
        if (dVar != null && this.m.a(dVar)) {
            this.i.lock();
            String strA = this.e.a(dVar, false);
            this.i.unlock();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            com.kwad.sdk.core.d.a.a("installPackage", HiAnalyticsConstant.HaKey.BI_KEY_VERSION + dVar.c());
            f(dVar);
            if (this.f == null) {
                this.f = new ArrayList();
            }
            this.f.add(dVar);
            synchronized (this.c) {
                this.c.put(dVar.b(), dVar.b);
                if (dVar.i) {
                    this.l.add(dVar.b());
                }
                com.kwad.sdk.c.b.b.a(dVar, 4);
            }
        }
    }

    public void b() {
        if (com.kwad.sdk.core.config.b.J()) {
            if (this.f == null) {
                this.f = c(this.d);
            }
            boolean z = this.f == null;
            List<com.kwad.sdk.c.kwai.d> listC = c();
            if (listC == null) {
                return;
            }
            this.g = new ArrayList();
            if (listC.size() > 0) {
                this.g.addAll(listC);
            }
            if (!z) {
                a(this.f);
            }
            ArrayList arrayList = new ArrayList(this.g.size());
            for (com.kwad.sdk.c.kwai.d dVar : this.g) {
                if (dVar.h == 1) {
                    arrayList.add(dVar);
                }
            }
            this.g.clear();
            this.g.addAll(arrayList);
            for (int i = 0; i < this.g.size(); i++) {
                com.kwad.sdk.c.kwai.d dVar2 = this.g.get(i);
                if (d(dVar2)) {
                    new a(this.d).a(dVar2, new com.kwad.sdk.c.a.a(this));
                }
                if (i == this.g.size() - 1) {
                    if (d(dVar2)) {
                        this.j = true;
                    } else {
                        d();
                    }
                }
            }
            this.c.clear();
            if (this.h == null || this.h.size() <= 0) {
                return;
            }
            for (com.kwad.sdk.c.kwai.d dVar3 : this.h) {
                synchronized (this.c) {
                    this.c.put(dVar3.b(), dVar3.b);
                }
            }
        }
    }

    public void b(com.kwad.sdk.c.kwai.d dVar) throws IOException {
        com.kwad.sdk.core.d.a.a("download", FirebaseAnalytics.Param.SUCCESS);
        if (this.g == null) {
            return;
        }
        int iIndexOf = this.g.indexOf(dVar);
        if (iIndexOf >= 0) {
            dVar = this.g.remove(iIndexOf);
        }
        com.kwad.sdk.core.d.a.a("performDownloadSuccess", "installPackage");
        a(dVar);
        if (this.j) {
            d();
        }
    }

    public void b(String str) {
        if (this.g == null || this.g.size() <= 0) {
            return;
        }
        for (com.kwad.sdk.c.kwai.d dVar : this.g) {
            if (a(dVar, str)) {
                new a(this.d).a(dVar, new com.kwad.sdk.c.a.a(this));
            }
        }
    }

    public void c(com.kwad.sdk.c.kwai.d dVar) {
        com.kwad.sdk.core.d.a.a("download", "failure");
        e(dVar);
    }
}
