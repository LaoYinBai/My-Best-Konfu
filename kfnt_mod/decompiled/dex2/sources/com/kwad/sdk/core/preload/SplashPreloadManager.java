package com.kwad.sdk.core.preload;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import com.ksad.download.c.b;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.diskcache.a.c;
import com.kwad.sdk.core.report.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdResultData;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.n;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SplashPreloadManager {
    private HashMap<String, PreLoadItem> a;
    private List<String> b;
    private volatile SharedPreferences c;
    private final Object d;

    public static class PreLoadItem extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        public long cacheTime;
        public long expiredTime;
        public String preloadId;
    }

    public static class PreLoadPara extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        public int isValidReturned;
        public long spreadTime;
    }

    private static class a {
        private static final SplashPreloadManager a = new SplashPreloadManager();
    }

    private SplashPreloadManager() {
        this.d = new Object();
        this.a = new HashMap<>();
        this.b = new ArrayList();
        a();
    }

    @AnyThread
    private void a(AdInfo adInfo) {
        PreLoadItem preLoadItem = new PreLoadItem();
        preLoadItem.cacheTime = System.currentTimeMillis();
        preLoadItem.expiredTime = System.currentTimeMillis() + (adInfo.adPreloadInfo.validityPeriod * 1000);
        preLoadItem.preloadId = com.kwad.sdk.core.response.a.a.N(adInfo);
        synchronized (this.d) {
            this.a.put(adInfo.adPreloadInfo.preloadId, preLoadItem);
            if (!this.b.contains(adInfo.adPreloadInfo.preloadId)) {
                this.b.add(adInfo.adPreloadInfo.preloadId);
            }
        }
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putString(adInfo.adPreloadInfo.preloadId, preLoadItem.toJson().toString());
            editorEdit.apply();
        }
    }

    @AnyThread
    public static SplashPreloadManager b() {
        SplashPreloadManager splashPreloadManager = a.a;
        if (splashPreloadManager.c == null) {
            splashPreloadManager.a();
        }
        return splashPreloadManager;
    }

    @AnyThread
    private boolean b(String str) {
        if (str == null) {
            return false;
        }
        File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(str);
        StringBuilder sb = new StringBuilder();
        sb.append("check preloadId ");
        sb.append(str);
        sb.append(" file exists ");
        sb.append(fileB == null ? "null" : Boolean.valueOf(fileB.exists()));
        com.kwad.sdk.core.d.a.a("PreloadManager", sb.toString());
        return fileB != null && fileB.exists();
    }

    private void d() {
        Map<String, ?> all = this.c.getAll();
        ArrayList arrayList = new ArrayList();
        for (String str : all.keySet()) {
            PreLoadItem preLoadItem = new PreLoadItem();
            try {
                String str2 = (String) all.get(str);
                if (str2 != null) {
                    preLoadItem.parseJson(new JSONObject(str2));
                    if (TextUtils.isEmpty(preLoadItem.preloadId)) {
                        continue;
                    } else {
                        File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(preLoadItem.preloadId);
                        if (fileB == null || !fileB.exists()) {
                            arrayList.add(preLoadItem.preloadId);
                            com.kwad.sdk.core.d.a.a("PreloadManager", "Remove null file list " + preLoadItem.preloadId);
                        } else {
                            synchronized (this.d) {
                                this.a.put(str, preLoadItem);
                                if (!this.b.contains(str)) {
                                    this.b.add(str);
                                }
                            }
                        }
                    }
                } else {
                    continue;
                }
            } catch (JSONException e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }
        SharedPreferences.Editor editorEdit = this.c.edit();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            editorEdit.remove((String) it.next());
        }
        editorEdit.apply();
    }

    private void e() {
        int size;
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (this.d) {
            ArrayList<String> arrayList = new ArrayList();
            for (String str : this.a.keySet()) {
                PreLoadItem preLoadItem = this.a.get(str);
                if (preLoadItem != null && preLoadItem.expiredTime < jCurrentTimeMillis) {
                    arrayList.add(str);
                }
            }
            SharedPreferences.Editor editorEdit = this.c.edit();
            for (String str2 : arrayList) {
                this.b.remove(str2);
                this.a.remove(str2);
                editorEdit.remove(str2);
                com.kwad.sdk.core.diskcache.a.a.a().d(str2);
            }
            editorEdit.apply();
            size = this.b.size();
        }
        if (size > 30) {
            com.kwad.sdk.core.d.a.a("PreloadManager", "大于 30 按失效日期远近顺序移除");
            int i = size - 15;
            for (int i2 = 0; i2 < i; i2++) {
                long j = Long.MAX_VALUE;
                String str3 = "";
                synchronized (this.d) {
                    for (PreLoadItem preLoadItem2 : this.a.values()) {
                        if (preLoadItem2.expiredTime < j) {
                            j = preLoadItem2.expiredTime;
                            str3 = preLoadItem2.preloadId;
                        }
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        this.b.remove(str3);
                        this.a.remove(str3);
                        this.c.edit().remove(str3).apply();
                        com.kwad.sdk.core.d.a.a("PreloadManager", "移除 preloadId = " + str3 + " expiredTime =  " + j);
                    }
                }
                if (!TextUtils.isEmpty(str3)) {
                    com.kwad.sdk.core.diskcache.a.a.a().d(str3);
                }
            }
        }
    }

    @AnyThread
    public int a(AdResultData adResultData, boolean z) {
        Iterator<AdTemplate> it = adResultData.adTemplateList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AdTemplate next = it.next();
            if (next != null) {
                for (AdInfo adInfo : next.adInfoList) {
                    if (adInfo.adPreloadInfo != null && this.c != null) {
                        if (!b(adInfo.adPreloadInfo.preloadId)) {
                            String strA = com.kwad.sdk.core.response.a.a.P(adInfo) ? com.kwad.sdk.core.response.a.a.a(adInfo) : com.kwad.sdk.core.response.a.a.Q(adInfo) ? com.kwad.sdk.core.response.a.a.I(adInfo).materialUrl : null;
                            if (!TextUtils.isEmpty(strA)) {
                                String strN = com.kwad.sdk.core.response.a.a.N(adInfo);
                                if (adInfo.adPreloadInfo.preloadType != 1 || b.b(KsAdSDKImpl.get().getContext()) || z) {
                                    com.kwad.sdk.core.d.a.a("PreloadManager", "start Download preloadId " + strN + " true url " + strA);
                                    e();
                                    c.a aVar = new c.a();
                                    if (!n.a(strA, strN, aVar)) {
                                        d.a(next, 1, aVar.a);
                                    }
                                }
                            }
                        }
                        a(adInfo);
                        i++;
                    }
                }
            }
        }
        AdTemplate adTemplate = adResultData.adTemplateList.size() > 0 ? adResultData.adTemplateList.get(0) : null;
        if (i > 0) {
            d.a(adTemplate, i);
        }
        return i;
    }

    public File a(String str) {
        if (str == null) {
            return null;
        }
        com.kwad.sdk.core.d.a.a("PreloadManager", "getVideoFile preloadId " + str + "  url " + str);
        File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(str);
        if (fileB == null || !fileB.exists()) {
            return null;
        }
        return fileB;
    }

    public void a() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context != null) {
            this.c = context.getSharedPreferences("ksadsdk_splash_preload_id_list", 0);
            d();
        }
    }

    @AnyThread
    public boolean a(AdResultData adResultData) {
        PreLoadItem preLoadItem;
        boolean zB = false;
        zB = false;
        zB = false;
        if (!adResultData.adTemplateList.isEmpty()) {
            AdTemplate adTemplate = adResultData.adTemplateList.get(0);
            if (!adTemplate.adInfoList.isEmpty()) {
                AdInfo adInfo = adTemplate.adInfoList.get(0);
                if (adInfo.adPreloadInfo != null) {
                    String strN = com.kwad.sdk.core.response.a.a.N(adInfo);
                    zB = b(strN);
                    PreLoadPara preLoadPara = new PreLoadPara();
                    preLoadPara.isValidReturned = zB ? 1 : 0;
                    if (zB) {
                        synchronized (this.d) {
                            preLoadItem = this.a.get(strN);
                        }
                        if (preLoadItem != null) {
                            preLoadPara.spreadTime = preLoadItem.cacheTime;
                        }
                    }
                    com.kwad.sdk.core.d.a.a("PreloadManager", "check checked " + zB + " spreadTime " + preLoadPara.spreadTime);
                    com.kwad.sdk.core.report.a.b(adTemplate, preLoadPara.toJson());
                }
            }
        }
        return zB;
    }

    @AnyThread
    public boolean b(AdResultData adResultData) {
        if (!adResultData.adTemplateList.isEmpty()) {
            AdTemplate adTemplate = adResultData.adTemplateList.get(0);
            if (!adTemplate.adInfoList.isEmpty()) {
                return com.kwad.sdk.core.response.a.a.Q(adTemplate.adInfoList.get(0));
            }
        }
        return false;
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.d) {
            com.kwad.sdk.core.d.a.a("PreloadManager", "getPreloadIdList start ");
            for (int i = 0; i < this.b.size(); i++) {
                String str = this.b.get(i);
                File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(str);
                if (fileB != null && fileB.exists()) {
                    arrayList.add(str);
                }
            }
            com.kwad.sdk.core.d.a.a("PreloadManager", "getPreloadIdList end ");
        }
        com.kwad.sdk.core.d.a.a("PreloadManager", "getPreloadIdList " + this.b.size());
        return arrayList;
    }
}
