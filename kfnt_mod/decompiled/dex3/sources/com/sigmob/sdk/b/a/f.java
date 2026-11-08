package com.sigmob.sdk.b.a;

import android.text.TextUtils;
import com.sigmob.sdk.b.o;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f implements c {
    private String b(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || iLastIndexOf <= str.lastIndexOf(47) || (iLastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(iLastIndexOf + 1, str.length());
    }

    @Override // com.sigmob.sdk.b.a.c
    public String a(String str) {
        String strB = b(str);
        String strD = o.d(str);
        if (TextUtils.isEmpty(strB)) {
            return strD;
        }
        return strD + "." + strB;
    }
}
