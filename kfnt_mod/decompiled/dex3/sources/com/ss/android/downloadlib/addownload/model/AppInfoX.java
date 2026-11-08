package com.ss.android.downloadlib.addownload.model;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInfoX {
    public String appDesc;
    public String appIcon;
    public String appName;
    public long cid;
    public long convertId;
    public String developer;
    public String packageName;
    public final List<Pair<String, String>> permissions = new ArrayList();
    public String privacyPolicyUrl;
    public int versionCode;
    public String versionName;

    public static long getId(long j, long j2) {
        return j > 0 ? j : j2;
    }

    public long getId() {
        return getId(this.convertId, this.cid);
    }
}
