package com.xiaomi.push;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class cy {

    public static class a extends cx {
        public a() {
            super(1);
        }

        @Override // com.xiaomi.push.cx
        public String a(Context context, String str, List<bf> list) {
            URL url;
            if (list == null) {
                url = new URL(str);
            } else {
                Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
                for (bf bfVar : list) {
                    builderBuildUpon.appendQueryParameter(bfVar.a(), bfVar.b());
                }
                url = new URL(builderBuildUpon.toString());
            }
            return bg.a(context, url);
        }
    }

    static int a(int i, int i2) {
        return (((i2 + 243) / 1448) * 132) + DownloadErrorCode.ERROR_TTNET_NOT_MODIFIED + i + i2;
    }

    static int a(int i, int i2, int i3) {
        return (((i2 + 200) / 1448) * 132) + DownloadErrorCode.ERROR_HTTPS_DATA + i2 + i + i3;
    }

    private static int a(cx cxVar, String str, List<bf> list, String str2) {
        if (cxVar.a() == 1) {
            return a(str.length(), a(str2));
        }
        if (cxVar.a() != 2) {
            return -1;
        }
        return a(str.length(), a(list), a(str2));
    }

    static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    static int a(List<bf> list) {
        int length = 0;
        for (bf bfVar : list) {
            if (!TextUtils.isEmpty(bfVar.a())) {
                length += bfVar.a().length();
            }
            if (!TextUtils.isEmpty(bfVar.b())) {
                length += bfVar.b().length();
            }
        }
        return length * 2;
    }

    public static String a(Context context, String str, List<bf> list) {
        return a(context, str, list, new a(), true);
    }

    public static String a(Context context, String str, List<bf> list, cx cxVar, boolean z) {
        cq cqVar;
        String strA;
        String str2;
        if (bg.b(context)) {
            try {
                ArrayList<String> arrayList = new ArrayList<>();
                if (z) {
                    cq cqVarM183a = cu.a().m183a(str);
                    if (cqVarM183a != null) {
                        arrayList = cqVarM183a.a(str);
                    }
                    cqVar = cqVarM183a;
                } else {
                    cqVar = null;
                }
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
                Iterator<String> it = arrayList.iterator();
                String str3 = null;
                while (it.hasNext()) {
                    String next = it.next();
                    ArrayList arrayList2 = list != null ? new ArrayList(list) : null;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    try {
                    } catch (IOException e) {
                        e = e;
                        strA = str3;
                    }
                    if (!cxVar.m192a(context, next, (List<bf>) arrayList2)) {
                        break;
                    }
                    strA = cxVar.a(context, next, (List<bf>) arrayList2);
                    try {
                    } catch (IOException e2) {
                        e = e2;
                    }
                    if (TextUtils.isEmpty(strA)) {
                        if (cqVar != null) {
                            str2 = strA;
                            try {
                                cqVar.a(next, System.currentTimeMillis() - jCurrentTimeMillis, a(cxVar, next, arrayList2, strA), null);
                            } catch (IOException e3) {
                                e = e3;
                                strA = str2;
                            }
                        } else {
                            str2 = strA;
                        }
                        str3 = str2;
                    } else {
                        if (cqVar == null) {
                            return strA;
                        }
                        try {
                            cqVar.a(next, System.currentTimeMillis() - jCurrentTimeMillis, a(cxVar, next, arrayList2, strA));
                            return strA;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (cqVar != null) {
                        str2 = strA;
                        cqVar.a(next, System.currentTimeMillis() - jCurrentTimeMillis, a(cxVar, next, arrayList2, strA), e);
                    } else {
                        str2 = strA;
                    }
                    e.printStackTrace();
                    str3 = str2;
                }
                return str3;
            } catch (MalformedURLException e5) {
                e5.printStackTrace();
            }
        }
        return null;
    }
}
