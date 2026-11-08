package com.sigmob.sdk.base.common;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.b.f;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.a.a;
import com.sigmob.sdk.common.a.c;
import com.sigmob.sdk.common.b.c;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.models.sigdsp.pb.Ad;
import com.sigmob.sdk.common.models.sigdsp.pb.AdCache;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.volley.toolbox.d;
import com.sigmob.volley.toolbox.f;
import com.sigmob.volley.toolbox.g;
import com.unity.purchasing.googleplay.Consts;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private static com.sigmob.sdk.b.f h;
    private static volatile com.sigmob.sdk.common.utils.g j;
    private String p;
    private String q;
    private static final Integer b = 0;
    private static final Integer c = 1;
    private static final Integer d = 2;
    private static final Integer e = 3;
    private static d f = null;
    private static Map<String, BaseAdUnit> g = new HashMap();
    private static HashMap<String, Integer> i = new HashMap<>();
    private Map<String, String> k = new HashMap();
    private HashMap<String, Long> l = new HashMap<>();
    private HashSet<a> m = new HashSet<>();
    final f.a a = new f.a() { // from class: com.sigmob.sdk.base.common.d.1
        @Override // com.sigmob.volley.toolbox.f.a
        public void a(com.sigmob.volley.toolbox.d dVar) {
            d.i.put(com.sigmob.sdk.common.utils.k.a(dVar.a), d.e);
            Iterator it = d.this.m.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                BaseAdUnit baseAdUnitA = aVar.a();
                if (baseAdUnitA != null) {
                    if (dVar.b == d.a.VIDEO) {
                        if (baseAdUnitA.getVideoPath().equals(dVar.c)) {
                            aVar.b(baseAdUnitA);
                        }
                    } else if (dVar.b == d.a.FILE && baseAdUnitA.getEndCardZipPath().equals(dVar.c)) {
                        aVar.e(baseAdUnitA);
                    }
                }
            }
            d.this.new c(dVar).executeOnExecutor(c.a.b().a(), new Object[0]);
        }

        @Override // com.sigmob.volley.toolbox.f.a
        public void a(com.sigmob.volley.toolbox.d dVar, long j2, long j3) {
            Iterator it = d.this.m.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                BaseAdUnit baseAdUnitA = aVar.a();
                if (baseAdUnitA != null && dVar.b == d.a.VIDEO && baseAdUnitA.getVideoPath().equals(dVar.c)) {
                    String strA = com.sigmob.sdk.common.utils.k.a(dVar.a);
                    Integer num = (Integer) d.i.get(strA);
                    Long l = (Long) d.this.l.get(strA);
                    if (j3 > 61440) {
                        if (num.intValue() == d.c.intValue()) {
                            d.i.put(strA, d.d);
                            d.this.l.put(strA, Long.valueOf(j3));
                            aVar.a(baseAdUnitA);
                        } else if (j3 - l.longValue() > 512000 && num.intValue() == d.d.intValue()) {
                            d.this.l.put(strA, Long.valueOf(j3));
                            aVar.d(baseAdUnitA);
                        }
                    }
                }
            }
        }

        @Override // com.sigmob.volley.toolbox.f.a
        public void b(com.sigmob.volley.toolbox.d dVar) {
            d.i.put(com.sigmob.sdk.common.utils.k.a(dVar.a), d.e);
            Iterator it = d.this.m.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                BaseAdUnit baseAdUnitA = aVar.a();
                if (baseAdUnitA != null) {
                    if (dVar.b == d.a.VIDEO) {
                        if (baseAdUnitA.getVideoPath().equals(dVar.c)) {
                            aVar.c(baseAdUnitA);
                        }
                    } else if (dVar.b == d.a.FILE && baseAdUnitA.getEndCardZipPath().equals(dVar.c)) {
                        aVar.f(baseAdUnitA);
                    }
                }
            }
        }

        @Override // com.sigmob.volley.toolbox.f.a
        public void c(com.sigmob.volley.toolbox.d dVar) {
            SigmobLog.e("onErrorResponse: ", dVar.i);
            d.i.put(com.sigmob.sdk.common.utils.k.a(dVar.a), d.e);
            Iterator it = d.this.m.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                BaseAdUnit baseAdUnitA = aVar.a();
                if (baseAdUnitA != null) {
                    if (baseAdUnitA.getVideoPath().equals(dVar.c) && dVar.b == d.a.VIDEO) {
                        aVar.c(baseAdUnitA);
                    } else if (baseAdUnitA.getEndCardZipPath().equals(dVar.c) && dVar.b == d.a.FILE) {
                        aVar.f(baseAdUnitA);
                    }
                }
            }
            d.this.new c(dVar).executeOnExecutor(c.a.b().a(), new Object[0]);
        }
    };
    private ArrayList<BaseAdUnit> n = new ArrayList<>();
    private List<BaseAdUnit> o = new ArrayList();
    private HashMap<String, WeakReference<b>> r = new HashMap<>();

    public interface a {
        BaseAdUnit a();

        void a(BaseAdUnit baseAdUnit);

        void b(BaseAdUnit baseAdUnit);

        void c(BaseAdUnit baseAdUnit);

        void d(BaseAdUnit baseAdUnit);

        void e(BaseAdUnit baseAdUnit);

        void f(BaseAdUnit baseAdUnit);
    }

    public interface b {
        void a(BaseAdUnit baseAdUnit);

        void a(BaseAdUnit baseAdUnit, SigmobError sigmobError, String str);
    }

    class c extends AsyncTask<Object, Void, String> {
        private com.sigmob.volley.toolbox.d b;

        c(com.sigmob.volley.toolbox.d dVar) {
            this.b = null;
            this.b = dVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Object... objArr) throws Throwable {
            if (this.b == null) {
                SigmobLog.e("Download Item is null");
                return SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD.toString();
            }
            if (this.b.i != null) {
                return this.b.i.toString();
            }
            String str = this.b.c;
            String strC = com.sigmob.sdk.common.utils.d.c(str);
            String strB = com.sigmob.sdk.common.utils.k.b(str);
            if (!TextUtils.isEmpty(this.b.d) && !this.b.d.equalsIgnoreCase(strB)) {
                this.b.g = 0;
                return SigmobError.ERROR_SIGMOB_FILE_MD5.toString();
            }
            if (!TextUtils.isEmpty(strC) && strC.equalsIgnoreCase("mp4")) {
                d.this.k.put(str, strB);
                this.b.g = 1;
                return null;
            }
            if (TextUtils.isEmpty(strC) || !strC.equalsIgnoreCase("tgz")) {
                this.b.g = 0;
                return SigmobError.ERROR_SIGMOB_INFORMATION_LOSE.toString();
            }
            try {
                com.sigmob.sdk.common.utils.e.a(new File(str), new File(str.replace(".tgz", "/")));
                this.b.g = 1;
                return null;
            } catch (Throwable th) {
                this.b.g = 0;
                SigmobLog.e(th.getMessage());
                return SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD.toString();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (!TextUtils.isEmpty(str)) {
                com.sigmob.sdk.common.utils.d.b(this.b.c);
            }
            Iterator it = new CopyOnWriteArrayList(d.this.n).iterator();
            while (it.hasNext()) {
                BaseAdUnit baseAdUnit = (BaseAdUnit) it.next();
                WeakReference weakReference = (WeakReference) d.this.r.get(baseAdUnit.getUuid());
                b bVar = weakReference != null ? (b) weakReference.get() : null;
                if (this.b.b == d.a.VIDEO && baseAdUnit.getVideoPath().equals(this.b.c)) {
                    t.a(this.b, baseAdUnit, str, false);
                } else if (this.b.b != d.a.FILE || !baseAdUnit.getEndCardZipPath().equals(this.b.c)) {
                }
                d.this.a(baseAdUnit, str, bVar);
            }
        }
    }

    /* renamed from: com.sigmob.sdk.base.common.d$d, reason: collision with other inner class name */
    private class AsyncTaskC0010d extends AsyncTask<Object, Void, ArrayList<com.sigmob.volley.toolbox.d>> {
        BaseAdUnit a;

        AsyncTaskC0010d(BaseAdUnit baseAdUnit) {
            this.a = null;
            this.a = baseAdUnit;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<com.sigmob.volley.toolbox.d> doInBackground(Object... objArr) throws Throwable {
            String strB;
            ArrayList<com.sigmob.volley.toolbox.d> arrayList = new ArrayList<>();
            if (this.a == null || this.a.getMaterial() == null) {
                return null;
            }
            MaterialMeta material = this.a.getMaterial();
            long length = 0;
            if (this.a.isVideoExist()) {
                strB = com.sigmob.sdk.common.utils.k.b(this.a.getVideoPath());
                if (TextUtils.isEmpty(this.a.getVideo_OriginMD5()) || this.a.getVideo_OriginMD5().equalsIgnoreCase(strB)) {
                    d.this.k.put(this.a.getVideoPath(), strB);
                    length = new File(this.a.getVideoPath()).length();
                } else {
                    com.sigmob.sdk.common.utils.d.b(this.a.getVideoPath());
                }
            } else {
                strB = null;
            }
            if (material.creative_type.intValue() == j.CreativeTypeVideo_Tar.a()) {
                boolean zCheckVideoValid = this.a.checkVideoValid();
                this.a.checkEndCardZipValid();
                com.sigmob.volley.toolbox.d dVar = new com.sigmob.volley.toolbox.d();
                com.sigmob.volley.toolbox.d dVar2 = new com.sigmob.volley.toolbox.d();
                this.a.isEndCardIndexExist();
                dVar.a = this.a.getEndcard_url();
                dVar.c = this.a.getEndCardZipPath();
                dVar.b = d.a.FILE;
                dVar.d = this.a.getEndCard_OriginMD5();
                arrayList.add(dVar);
                if (zCheckVideoValid) {
                    dVar2.a = this.a.getVideo_url();
                    dVar2.c = this.a.getVideoPath();
                    dVar2.b = d.a.VIDEO;
                    dVar2.d = strB;
                    dVar2.e = length;
                    dVar2.g = 1;
                    t.a(dVar2, this.a, (String) null, true);
                } else {
                    dVar2.a = this.a.getVideo_url();
                    dVar2.c = this.a.getVideoPath();
                    dVar2.b = d.a.VIDEO;
                    dVar2.d = this.a.getVideo_OriginMD5();
                    arrayList.add(dVar2);
                }
            } else if (this.a.isVideoExist()) {
                com.sigmob.volley.toolbox.d dVar3 = new com.sigmob.volley.toolbox.d();
                dVar3.a = this.a.getVideo_url();
                dVar3.c = this.a.getVideoPath();
                dVar3.b = d.a.VIDEO;
                dVar3.d = strB;
                dVar3.e = length;
                dVar3.g = 1;
                t.a(dVar3, this.a, (String) null, true);
            } else {
                com.sigmob.volley.toolbox.d dVar4 = new com.sigmob.volley.toolbox.d();
                dVar4.a = this.a.getVideo_url();
                dVar4.c = this.a.getVideoPath();
                dVar4.b = d.a.VIDEO;
                dVar4.d = this.a.getVideo_OriginMD5();
                arrayList.add(dVar4);
            }
            SigmobLog.d("cache() adUnit = [" + this.a.getCrid() + "] videoUrl = [" + this.a.getVideo_url() + "] endcardUrl = [" + this.a.getEndcard_url() + "]");
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<com.sigmob.volley.toolbox.d> arrayList) {
            boolean z;
            super.onPostExecute(arrayList);
            if (this.a == null) {
                return;
            }
            WeakReference weakReference = (WeakReference) d.this.r.get(this.a.getUuid());
            b bVar = weakReference != null ? (b) weakReference.get() : null;
            if (arrayList != null && arrayList.size() != 0) {
                com.sigmob.volley.toolbox.g gVarB = this.a.getPlayMode() != 0 ? n.b() : n.a();
                Iterator it = d.this.n.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((BaseAdUnit) it.next()).getUuid().equals(this.a.getUuid())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    d.this.n.add(this.a);
                }
                Iterator<com.sigmob.volley.toolbox.d> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    com.sigmob.volley.toolbox.d next = it2.next();
                    String strA = com.sigmob.sdk.common.utils.k.a(next.a);
                    SigmobLog.d("downloader add  item " + next.a);
                    if (d.i.get(strA) == null || d.i.get(strA) == d.e) {
                        d.i.put(strA, d.c);
                        gVarB.a(next, d.this.a);
                    }
                }
            } else if (this.a.isEndCardIndexExist() && this.a.isVideoExist()) {
                if (this.a.getPlayMode() == 0) {
                    d.this.b(this.a, bVar);
                } else if (bVar != null) {
                    bVar.a(this.a);
                }
            } else if (bVar != null) {
                bVar.a(this.a, SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD, "endCardIndexFile not exist");
            }
            MaterialMeta material = this.a.getMaterial();
            if (material == null || material.ad_privacy == null) {
                return;
            }
            String str = material.ad_privacy.privacy_template_url;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String strA2 = com.sigmob.sdk.common.utils.k.a(str);
            File fileG = com.sigmob.sdk.common.utils.d.g(com.sigmob.sdk.common.utils.d.b);
            File file = new File(fileG, strA2 + ".html");
            if (file.exists()) {
                SigmobLog.i("privacy_template_url:" + strA2 + " is exists");
                return;
            }
            try {
                if (fileG.exists() && fileG.isDirectory()) {
                    for (File file2 : fileG.listFiles()) {
                        if (file2.exists() && file2.isFile() && file2.delete()) {
                            SigmobLog.d("删除单个文件" + file2.getAbsolutePath() + "成功！");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            com.sigmob.volley.toolbox.d dVar = new com.sigmob.volley.toolbox.d();
            dVar.a = str;
            dVar.c = file.getAbsolutePath();
            dVar.b = d.a.OTHER;
            arrayList.add(dVar);
            n.a().a(dVar, new f.a() { // from class: com.sigmob.sdk.base.common.d.d.1
                @Override // com.sigmob.volley.toolbox.f.a
                public void a(com.sigmob.volley.toolbox.d dVar2) {
                    SigmobLog.i("onPostExecute onSuccess:" + dVar2.a);
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void a(com.sigmob.volley.toolbox.d dVar2, long j, long j2) {
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void b(com.sigmob.volley.toolbox.d dVar2) {
                    SigmobLog.i("onPostExecute onCancel:" + dVar2.a);
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void c(com.sigmob.volley.toolbox.d dVar2) {
                    SigmobLog.i("onPostExecute onErrorResponse:" + dVar2.a);
                }
            });
        }
    }

    private d() {
    }

    public static BaseAdUnit a(String str) {
        return g.get(str);
    }

    public static synchronized com.sigmob.sdk.common.utils.g a(Context context) {
        if (j == null) {
            File file = new File(com.sigmob.sdk.common.utils.d.c());
            synchronized (com.sigmob.sdk.common.utils.g.class) {
                if (j == null) {
                    j = new com.sigmob.sdk.common.utils.g(context.getApplicationContext()).a(file);
                }
            }
        }
        return j;
    }

    public static void a() {
        a("ads", null, null, null, null, new com.sigmob.sdk.common.a.d() { // from class: com.sigmob.sdk.base.common.d.3
            @Override // com.sigmob.sdk.common.a.d
            public void a(Error error) {
                new Handler().postDelayed(new Runnable() { // from class: com.sigmob.sdk.base.common.d.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.sigmob.sdk.common.a.c.a().b();
                        } catch (Throwable th) {
                            SigmobLog.e(th.getMessage());
                        }
                    }
                }, 1000L);
                SigmobLog.e(error.getMessage());
            }

            @Override // com.sigmob.sdk.common.a.d
            public void a(List list) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                for (int i2 = 0; i2 < list.size(); i2++) {
                    SigmobLog.d("ad Unit: " + ((BaseAdUnit) list.get(i2)).toString());
                }
            }
        });
    }

    public static void a(BaseAdUnit baseAdUnit) {
        if (baseAdUnit != null && baseAdUnit.getAd_type() == 1 && baseAdUnit.getPlayMode() != 0 && ((!baseAdUnit.isVideoExist() || !baseAdUnit.isEndCardIndexExist()) && baseAdUnit.getCreativeType() != j.CreativeTypeMRAID.a())) {
            n.b().b();
            i.clear();
            for (g.a aVar : n.a().c()) {
                if (aVar.c().equals(baseAdUnit.getVideo_url()) || aVar.c().equals(baseAdUnit.getEndcard_url())) {
                    if (!aVar.b()) {
                        aVar.g();
                        n.b().a(aVar.f(), aVar.e());
                    }
                }
            }
        }
        b(baseAdUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BaseAdUnit baseAdUnit, String str, b bVar) {
        if (str == null) {
            if (!baseAdUnit.isVideoExist() || !baseAdUnit.isEndCardIndexExist()) {
                return;
            } else {
                b(baseAdUnit, bVar);
            }
        } else if (bVar != null) {
            bVar.a(baseAdUnit, SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD, str);
        }
        this.n.remove(baseAdUnit);
        this.r.remove(baseAdUnit.getUuid());
    }

    public static void a(String str, String str2, String[] strArr, String str3, String str4, final com.sigmob.sdk.common.a.d dVar) {
        try {
            com.sigmob.sdk.common.a.a.a().a(str, null, str2, strArr, null, null, str3, str4, new a.InterfaceC0016a() { // from class: com.sigmob.sdk.base.common.d.5
                @Override // com.sigmob.sdk.common.a.a.InterfaceC0016a
                public void a(Error error) {
                    if (dVar != null) {
                        dVar.a(error);
                    }
                }

                @Override // com.sigmob.sdk.common.a.a.InterfaceC0016a
                public void a(List<Map> list) {
                    if (list == null || list.isEmpty()) {
                        if (dVar != null) {
                            dVar.a((List<?>) null);
                            return;
                        }
                        return;
                    }
                    try {
                        List<?> listB = d.b(list);
                        if (dVar != null) {
                            dVar.a(listB);
                        }
                    } catch (Throwable th) {
                        if (dVar != null) {
                            dVar.a(new Error(th.getMessage()));
                        }
                    }
                }
            });
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            if (dVar != null) {
                dVar.a(new Error(th.getMessage()));
            }
        }
    }

    public static File[] a(File[] fileArr, long j2, long j3) {
        if (fileArr == null || fileArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(fileArr));
        for (File file : fileArr) {
            long j4 = j2 - j3;
            if (file.exists() && j4 > file.lastModified()) {
                file.delete();
                arrayList.remove(file);
                SigmobLog.d("file delete " + file.getName());
            }
        }
        return (File[]) arrayList.toArray(new File[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<BaseAdUnit> b(List<Map> list) {
        Ad adDecode;
        SigmobLog.d(list.toString());
        ArrayList arrayList = new ArrayList();
        for (Map map : list) {
            try {
                Long l = (Long) map.get("create_time");
                String str = (String) map.get(Consts.INAPP_REQUEST_ID);
                String str2 = (String) map.get("load_id");
                byte[] bArr = (byte[]) map.get("ad");
                if (bArr != null && (adDecode = Ad.ADAPTER.decode(bArr)) != null) {
                    BaseAdUnit baseAdUnitAdUnit = BaseAdUnit.adUnit(adDecode, str, str2, null);
                    if (l != null) {
                        baseAdUnitAdUnit.setCreate_time(l.longValue());
                    }
                    arrayList.add(baseAdUnitAdUnit);
                }
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
        return arrayList;
    }

    public static void b() {
        a("ads", "create_time<'" + (System.currentTimeMillis() - 604800000) + "'", null, null, null, new com.sigmob.sdk.common.a.d() { // from class: com.sigmob.sdk.base.common.d.4
            @Override // com.sigmob.sdk.common.a.d
            public void a(Error error) {
                new Handler().postDelayed(new Runnable() { // from class: com.sigmob.sdk.base.common.d.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.sigmob.sdk.common.a.c.a().b();
                        } catch (Throwable th) {
                            SigmobLog.e(th.getMessage());
                        }
                    }
                }, 1000L);
                SigmobLog.e(error.getMessage());
            }

            @Override // com.sigmob.sdk.common.a.d
            public void a(List list) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                for (int i2 = 0; i2 < list.size(); i2++) {
                    d.d((BaseAdUnit) list.get(i2));
                }
            }
        });
    }

    public static void b(Context context) {
        f.a aVar = new f.a(context);
        try {
            aVar.a(new File(com.sigmob.sdk.common.utils.d.c()));
        } catch (Throwable th) {
            SigmobLog.e("initHttpProxyCacheServer fail ", th);
        }
        h = aVar.a();
    }

    public static void b(BaseAdUnit baseAdUnit) {
        if (baseAdUnit == null || TextUtils.isEmpty(baseAdUnit.getUuid())) {
            return;
        }
        g.put(baseAdUnit.getUuid(), baseAdUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final BaseAdUnit baseAdUnit, final b bVar) {
        if (baseAdUnit.getPlayMode() == 0) {
            baseAdUnit.insertToDB(new c.a() { // from class: com.sigmob.sdk.base.common.d.7
                @Override // com.sigmob.sdk.common.a.c.a
                public void onFailed(Throwable th) {
                    if (bVar != null) {
                        bVar.a(baseAdUnit, SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD, th.getMessage());
                    }
                }

                @Override // com.sigmob.sdk.common.a.c.a
                public void onSuccess() {
                    if (bVar != null) {
                        bVar.a(baseAdUnit);
                    }
                }
            });
        } else if (bVar != null) {
            bVar.a(baseAdUnit);
        }
    }

    public static void c() {
        try {
            int cacheTop = SDKConfig.sharedInstance().getCacheTop();
            int iA = com.sigmob.sdk.common.a.a.a().a("ads");
            SigmobLog.i("checkCache: count: " + iA + " top: " + cacheTop);
            if (iA > cacheTop) {
                a("ads", null, null, "create_time ASC", String.valueOf(iA - cacheTop), new com.sigmob.sdk.common.a.d() { // from class: com.sigmob.sdk.base.common.d.6
                    @Override // com.sigmob.sdk.common.a.d
                    public void a(Error error) {
                        SigmobLog.e(error.getMessage());
                    }

                    @Override // com.sigmob.sdk.common.a.d
                    public void a(List list) {
                        if (list == null || list.isEmpty()) {
                            return;
                        }
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            d.d((BaseAdUnit) list.get(i2));
                        }
                    }
                });
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    public static void c(Context context) {
        try {
            File[] fileArrA = com.sigmob.sdk.common.utils.d.a(com.sigmob.sdk.common.utils.d.f(com.sigmob.sdk.common.utils.d.a(context).getAbsolutePath()), 5);
            SigmobLog.i(fileArrA == null ? "Download APK files  is null" : "Download APK files remain num: " + fileArrA.length);
            if (fileArrA == null || fileArrA.length <= 0) {
                return;
            }
            for (File file : fileArrA) {
                if (System.currentTimeMillis() - file.lastModified() > 604800000) {
                    SigmobLog.i("Download APK files timeOut seven day and delete:" + file.getName() + Constants.COLON_SEPARATOR + file.delete());
                }
            }
        } catch (Throwable th) {
            SigmobLog.e("clean Download APK file error", th);
        }
    }

    public static void c(BaseAdUnit baseAdUnit) {
        if (baseAdUnit == null || TextUtils.isEmpty(baseAdUnit.getUuid())) {
            return;
        }
        g.remove(baseAdUnit.getUuid());
    }

    public static void d() {
        try {
            com.sigmob.sdk.common.utils.d.a(com.sigmob.sdk.common.utils.d.g(com.sigmob.sdk.common.utils.d.a).getAbsolutePath());
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    public static void d(final BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.common.a.a.a().a("ads", "crid ='" + baseAdUnit.getCrid() + "' and adslot_id='" + baseAdUnit.getAdslot_id() + "'", new c.a() { // from class: com.sigmob.sdk.base.common.d.2
            @Override // com.sigmob.sdk.common.a.c.a
            public void onFailed(Throwable th) {
                SigmobLog.e(th.getMessage());
            }

            @Override // com.sigmob.sdk.common.a.c.a
            public void onSuccess() throws Throwable {
                d.h(baseAdUnit);
            }
        });
    }

    public static com.sigmob.sdk.b.f e() {
        return h;
    }

    public static d f() {
        if (f == null) {
            synchronized (d.class) {
                if (f == null) {
                    f = new d();
                }
            }
        }
        return f;
    }

    public static void g() {
        String str;
        try {
            File[] fileArrA = com.sigmob.sdk.common.utils.d.a(a(com.sigmob.sdk.common.utils.d.f(com.sigmob.sdk.common.utils.d.b()), System.currentTimeMillis(), SDKConfig.sharedInstance().getSplashExpiredTime()), SDKConfig.sharedInstance().getSplashCacheTop());
            if (fileArrA == null) {
                str = "splash ad file list is null";
            } else {
                str = "splash ad file remain num: " + fileArrA.length;
            }
            SigmobLog.i(str);
        } catch (Throwable th) {
            SigmobLog.e("clean splash ad file error", th);
        }
    }

    public static void h() {
        String str;
        try {
            File[] fileArrA = com.sigmob.sdk.common.utils.d.a(com.sigmob.sdk.common.utils.d.f(com.sigmob.sdk.common.utils.d.c()), SDKConfig.sharedInstance().getNativeAdCacheTop());
            if (fileArrA == null) {
                str = "native ad file list is null";
            } else {
                str = "native ad file remain num: " + fileArrA.length;
            }
            SigmobLog.i(str);
        } catch (Throwable th) {
            SigmobLog.e("clean native ad file error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h(BaseAdUnit baseAdUnit) throws Throwable {
        int iA;
        int iA2 = 0;
        if (TextUtils.isEmpty(baseAdUnit.getEndcard_md5())) {
            iA = 0;
        } else {
            iA = com.sigmob.sdk.common.a.a.a().a("file_reference", "endcard_md5='" + baseAdUnit.getEndcard_md5() + "'");
        }
        if (!TextUtils.isEmpty(baseAdUnit.getVideo_md5())) {
            iA2 = com.sigmob.sdk.common.a.a.a().a("file_reference", "video_md5='" + baseAdUnit.getVideo_md5() + "'");
        }
        if (iA2 == 0) {
            com.sigmob.sdk.common.utils.d.b(baseAdUnit.getVideoPath());
            com.sigmob.sdk.common.utils.d.b(baseAdUnit.getVideoTmpPath());
        }
        if (iA == 0) {
            com.sigmob.sdk.common.utils.d.b(baseAdUnit.getEndCardZipPath());
            com.sigmob.sdk.common.utils.d.a(baseAdUnit.getEndCardDirPath());
        }
    }

    public static void i() {
        try {
            File[] fileArrF = com.sigmob.sdk.common.utils.d.f(com.sigmob.sdk.common.utils.d.a());
            ArrayList arrayList = new ArrayList(Arrays.asList(fileArrF));
            for (File file : fileArrF) {
                if (!file.getPath().endsWith(".tmp")) {
                    arrayList.remove(file);
                }
            }
            File[] fileArrA = com.sigmob.sdk.common.utils.d.a(a((File[]) arrayList.toArray(new File[0]), System.currentTimeMillis(), 604800000L), 5);
            SigmobLog.i(fileArrA == null ? "splash ad file list is null" : "splash ad file remain num: " + fileArrA.length);
        } catch (Throwable th) {
            SigmobLog.e("clean splash ad file error", th);
        }
    }

    public void a(a aVar) {
        if (aVar == null || this.m.contains(aVar)) {
            return;
        }
        this.m.add(aVar);
    }

    public void a(BaseAdUnit baseAdUnit, b bVar) {
        if (baseAdUnit == null) {
            SigmobLog.e("adUnit is null");
            if (bVar != null) {
                bVar.a(baseAdUnit, SigmobError.ERROR_SIGMOB_FILE_DOWNLOAD, "adUnit is null");
                return;
            }
            return;
        }
        if (bVar != null) {
            this.r.put(baseAdUnit.getUuid(), new WeakReference<>(bVar));
        }
        try {
            new AsyncTaskC0010d(baseAdUnit).executeOnExecutor(c.a.b().a(), new Object[0]);
        } catch (Throwable th) {
            SigmobLog.e("AdUnitCheckCacheTask execute error", th);
        }
    }

    public void b(a aVar) {
        if (aVar == null || !this.m.contains(aVar)) {
            return;
        }
        this.m.remove(aVar);
    }

    public void b(String str) {
        this.p = str;
    }

    public void c(String str) {
        this.q = str;
    }

    public String d(String str) {
        return this.k.get(str);
    }

    public void e(BaseAdUnit baseAdUnit) {
        if (baseAdUnit == null || TextUtils.isEmpty(baseAdUnit.getadslot_id()) || this.o == null) {
            return;
        }
        this.o.remove(baseAdUnit);
    }

    public void f(BaseAdUnit baseAdUnit) {
        if (baseAdUnit == null || TextUtils.isEmpty(baseAdUnit.getadslot_id())) {
            return;
        }
        if (this.o != null && this.o.size() > 29) {
            this.o = this.o.subList(this.o.size() - 29, this.o.size());
        }
        if (this.o != null) {
            this.o.add(baseAdUnit);
        }
    }

    public String j() {
        return this.p;
    }

    public String k() {
        return this.q;
    }

    public Map<String, AdCache> l() {
        HashMap map = new HashMap();
        if (this.o != null && this.o.size() > 0) {
            for (int i2 = 0; i2 < this.o.size(); i2++) {
                BaseAdUnit baseAdUnit = this.o.get(i2);
                map.put(baseAdUnit.getadslot_id(), new AdCache(Integer.valueOf(baseAdUnit.getAd_type()), Arrays.asList(baseAdUnit.getCrid())));
            }
        }
        return map;
    }
}
