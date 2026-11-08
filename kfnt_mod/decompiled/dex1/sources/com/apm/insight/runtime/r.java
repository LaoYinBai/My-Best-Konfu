package com.apm.insight.runtime;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.common.utility.date.DateDef;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class r {

    /* renamed from: a, reason: collision with root package name */
    private static File f3062a;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f3063a;

        /* renamed from: b, reason: collision with root package name */
        public String f3064b;

        /* renamed from: c, reason: collision with root package name */
        public long f3065c;

        a(String str) {
            String[] strArrSplit = str.split("\\s+");
            if (strArrSplit.length != 3) {
                com.apm.insight.b.a().a("NPTH_CATCH", new RuntimeException("err ProcessTrack line:" + str));
                return;
            }
            this.f3063a = strArrSplit[0];
            this.f3064b = strArrSplit[1];
            try {
                this.f3065c = Long.parseLong(strArrSplit[2]);
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", new RuntimeException("err ProcessTrack line:" + str, th));
            }
        }
    }

    @Nullable
    private static File a() {
        if (f3062a == null) {
            String strC = com.apm.insight.l.a.c(com.apm.insight.h.g());
            if (strC == null) {
                return null;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            f3062a = new File(com.apm.insight.l.o.j(com.apm.insight.h.g()), "apminsight/ProcessTrack/" + ((jCurrentTimeMillis - (jCurrentTimeMillis % DateDef.DAY)) / DateDef.DAY) + '/' + strC.replace(':', '_') + ".txt");
        }
        return f3062a;
    }

    public static File a(long j) {
        return new File(com.apm.insight.l.o.j(com.apm.insight.h.g()), "apminsight/ProcessTrack/" + ((j - (j % DateDef.DAY)) / DateDef.DAY));
    }

    @NonNull
    public static HashMap<String, a> a(long j, String str) throws Throwable {
        File file = new File(com.apm.insight.l.o.j(com.apm.insight.h.g()), "apminsight/ProcessTrack/" + ((j - (j % DateDef.DAY)) / DateDef.DAY));
        String[] list = file.list();
        HashMap<String, a> map = new HashMap<>();
        if (list != null) {
            for (String str2 : list) {
                File file2 = new File(file, str2);
                long length = file2.length();
                try {
                    JSONArray jSONArrayA = com.apm.insight.l.i.a(file2, length > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? length - PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED : 0L);
                    int length2 = jSONArrayA.length() - 1;
                    while (true) {
                        if (length2 >= 0) {
                            String strOptString = jSONArrayA.optString(length2);
                            if (!TextUtils.isEmpty(strOptString) && strOptString.startsWith(str)) {
                                map.put(str2.replace('_', ':').replace(".txt", ""), new a(strOptString));
                                break;
                            }
                            length2--;
                        }
                    }
                } catch (IOException unused) {
                }
            }
        }
        return map;
    }

    public static void a(String str, String str2) {
        try {
            File fileA = a();
            if (fileA != null) {
                com.apm.insight.l.i.a(fileA, str + ' ' + str2 + ' ' + System.currentTimeMillis() + '\n', true);
            }
        } catch (Throwable unused) {
        }
    }
}
