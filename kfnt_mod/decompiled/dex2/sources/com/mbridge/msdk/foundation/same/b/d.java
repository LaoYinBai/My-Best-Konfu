package com.mbridge.msdk.foundation.same.b;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MBridgeDirContext.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d extends b {
    public d(String str) {
        super(str);
    }

    @Override // com.mbridge.msdk.foundation.same.b.b
    protected final List<a> b() {
        ArrayList<a> arrayList = new ArrayList<>();
        a(arrayList, c.MBRIDGE_RES_MANAGER_DIR, "mb/res");
        a(arrayList, c.AD_MOVIES, "mb/res/Movies").a(c.MBRIDGE_VC, "mb/res/.MBridge_VC");
        a aVarA = a(arrayList, c.AD_MBRIDGE_700, "mb/res/.mbridge700");
        aVarA.a(c.MBRIDGE_700_IMG, "mb/res/img");
        aVarA.a(c.MBRIDGE_700_RES, "mb/res/res");
        aVarA.a(c.MBRIDGE_700_HTML, "mb/res/html");
        a(arrayList, c.MBRIDGE_OTHER, "mb/other").a(c.MBRIDGE_700_APK, "mb/apk");
        a(arrayList, c.MBRIDGE_CRASH_INFO, "mb/crashinfo");
        a(arrayList, c.MBRIDGE_OTHER, "mb/other");
        return arrayList;
    }
}
