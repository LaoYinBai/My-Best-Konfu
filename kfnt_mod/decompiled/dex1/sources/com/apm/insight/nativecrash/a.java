package com.apm.insight.nativecrash;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.apm.insight.l.o;
import com.appsflyer.share.Constants;
import com.bytedance.pangle.provider.ContentProviderManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f2979a;

    public a(File file) {
        String strA;
        File fileC = o.c(file);
        if (!fileC.exists() || fileC.length() == 0 || (strA = NativeImpl.a(fileC.getAbsolutePath())) == null) {
            return;
        }
        String[] strArrSplit = strA.split("\n");
        this.f2979a = new HashMap();
        for (String str : strArrSplit) {
            String[] strArrSplit2 = str.split("=");
            if (strArrSplit2.length == 2) {
                this.f2979a.put(strArrSplit2[0], strArrSplit2[1]);
            }
        }
    }

    public boolean a() {
        return (this.f2979a == null || this.f2979a.isEmpty() || TextUtils.isEmpty(this.f2979a.get(ContentProviderManager.PLUGIN_PROCESS_NAME)) || TextUtils.isEmpty(this.f2979a.get("crash_thread_name")) || TextUtils.isEmpty(this.f2979a.get(Constants.URL_MEDIA_SOURCE)) || TextUtils.isEmpty(this.f2979a.get("tid")) || TextUtils.isEmpty(this.f2979a.get("start_time")) || TextUtils.isEmpty(this.f2979a.get("crash_time")) || TextUtils.isEmpty(this.f2979a.get("signal_line"))) ? false : true;
    }

    @Nullable
    public String b() {
        return this.f2979a.get("signal_line");
    }

    public Map<String, String> c() {
        return this.f2979a;
    }
}
