package com.kwad.sdk.home.download;

import android.text.TextUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.aq;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static volatile a l;
    private File e;
    private File f;
    private File g;
    private File h;
    private File i;
    private Stack<HomeApkBannerData> a = new Stack<>();
    private Stack<HomeApkBannerData> b = new Stack<>();
    private Map<String, Integer> c = new HashMap();
    private int j = 0;
    private boolean k = false;
    private String d = aq.d(KsAdSDKImpl.get().getContext());

    public a() {
        File file = new File(this.d);
        if (!file.exists()) {
            file.mkdir();
        }
        this.e = new File(this.d + File.separator + "download_uninstall");
        this.g = new File(this.d + File.separator + "download_uninstall_apk_data");
        if (!this.g.exists()) {
            this.g.mkdir();
        }
        this.h = new File(this.d + File.separator + "install_unopen_apk_data");
        if (!this.h.exists()) {
            this.h.mkdir();
        }
        this.f = new File(this.d + File.separator + "install_unopen");
        this.i = new File(this.d + File.separator + "app_has_show_banner_counts");
    }

    public static a a() {
        if (l == null) {
            synchronized (a.class) {
                if (l == null) {
                    l = new a();
                }
            }
        }
        return l;
    }

    private File a(HomeApkBannerData homeApkBannerData, boolean z) {
        return new File((z ? this.g : this.h).getPath() + File.separator + homeApkBannerData.mAdTemplateUniqueId);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00ac A[Catch: all -> 0x00a6, Exception -> 0x00a8, TRY_LEAVE, TryCatch #5 {Exception -> 0x00a8, blocks: (B:45:0x00a2, B:51:0x00ac), top: B:60:0x00a2, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.kwad.sdk.core.response.model.AdTemplate r7) {
        /*
            Method dump skipped, instructions count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.home.download.a.a(com.kwad.sdk.core.response.model.AdTemplate):void");
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(AdTemplate adTemplate) {
        String str;
        String str2;
        ObjectOutputStream objectOutputStream;
        synchronized (a.class) {
            HomeApkBannerData homeApkBannerDataConvertAdTemplateToHomeApkBannerData = HomeApkBannerData.convertAdTemplateToHomeApkBannerData(adTemplate);
            if (homeApkBannerDataConvertAdTemplateToHomeApkBannerData == null) {
                return;
            }
            boolean z = false;
            Iterator<HomeApkBannerData> it = this.a.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(homeApkBannerDataConvertAdTemplateToHomeApkBannerData.appPackageName, it.next().appPackageName)) {
                    it.remove();
                    z = true;
                }
            }
            if (z) {
                ObjectOutputStream objectOutputStream2 = null;
                try {
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.e));
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    objectOutputStream.writeObject(this.a);
                    try {
                        objectOutputStream.close();
                    } catch (Exception e2) {
                        str = "HomeApkBannerDataManager";
                        str2 = " removeApkDownloadedData close e" + e2;
                        com.kwad.sdk.core.d.a.a(str, str2);
                    }
                } catch (Exception e3) {
                    e = e3;
                    objectOutputStream2 = objectOutputStream;
                    com.kwad.sdk.core.d.a.a("HomeApkBannerDataManager", " removeApkDownloadedData e" + e);
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (Exception e4) {
                            str = "HomeApkBannerDataManager";
                            str2 = " removeApkDownloadedData close e" + e4;
                            com.kwad.sdk.core.d.a.a(str, str2);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream;
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (Exception e5) {
                            com.kwad.sdk.core.d.a.a("HomeApkBannerDataManager", " removeApkDownloadedData close e" + e5);
                        }
                    }
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00ac A[Catch: all -> 0x00a6, Exception -> 0x00a8, TRY_LEAVE, TryCatch #5 {Exception -> 0x00a8, blocks: (B:45:0x00a2, B:51:0x00ac), top: B:60:0x00a2, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(com.kwad.sdk.core.response.model.AdTemplate r7) {
        /*
            Method dump skipped, instructions count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.home.download.a.c(com.kwad.sdk.core.response.model.AdTemplate):void");
    }

    public void d(AdTemplate adTemplate) {
        String str;
        String str2;
        ObjectOutputStream objectOutputStream;
        synchronized (a.class) {
            HomeApkBannerData homeApkBannerDataConvertAdTemplateToHomeApkBannerData = HomeApkBannerData.convertAdTemplateToHomeApkBannerData(adTemplate);
            boolean z = false;
            Iterator<HomeApkBannerData> it = this.b.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(homeApkBannerDataConvertAdTemplateToHomeApkBannerData.appPackageName, it.next().appPackageName)) {
                    it.remove();
                    z = true;
                }
            }
            if (z) {
                ObjectOutputStream objectOutputStream2 = null;
                ObjectOutputStream objectOutputStream3 = null;
                try {
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.f));
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e) {
                    e = e;
                }
                try {
                    Object obj = this.b;
                    objectOutputStream.writeObject(obj);
                    try {
                        objectOutputStream.close();
                        objectOutputStream2 = obj;
                    } catch (Exception e2) {
                        str = "HomeApkBannerDataManager";
                        str2 = " removeInstalledData close e" + e2;
                        com.kwad.sdk.core.d.a.a(str, str2);
                    }
                } catch (Exception e3) {
                    e = e3;
                    objectOutputStream3 = objectOutputStream;
                    com.kwad.sdk.core.d.a.a("HomeApkBannerDataManager", " removeInstalledData e" + e);
                    objectOutputStream2 = objectOutputStream3;
                    if (objectOutputStream3 != null) {
                        try {
                            objectOutputStream3.close();
                            objectOutputStream2 = objectOutputStream3;
                        } catch (Exception e4) {
                            str = "HomeApkBannerDataManager";
                            str2 = " removeInstalledData close e" + e4;
                            com.kwad.sdk.core.d.a.a(str, str2);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream;
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (Exception e5) {
                            com.kwad.sdk.core.d.a.a("HomeApkBannerDataManager", " removeInstalledData close e" + e5);
                        }
                    }
                    throw th;
                }
            }
        }
    }
}
