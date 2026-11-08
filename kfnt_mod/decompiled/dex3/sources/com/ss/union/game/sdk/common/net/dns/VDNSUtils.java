package com.ss.union.game.sdk.common.net.dns;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Dns;
import com.bytedance.sdk.b.a;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VDNSUtils {
    private static final String a = "VDNSUtils";
    private static volatile boolean b;

    public static synchronized void init(final Context context, final String str) {
        LogUtils.log(a, "start init");
        b = true;
        a.a().a(new com.bytedance.sdk.b.e.a.a() { // from class: com.ss.union.game.sdk.common.net.dns.VDNSUtils.1
            public boolean g() {
                return false;
            }

            public Context a() {
                return context;
            }

            public String b() {
                return str;
            }

            public String[] c() {
                return new String[]{"dig.bdurl.net"};
            }

            public String[] d() {
                return new String[0];
            }
        });
    }

    static synchronized List<InetAddress> a(String str, String str2) {
        if (b && !TextUtils.isEmpty(str)) {
            LogUtils.log(a, "lookup hasInit true");
            return b(str, str2);
        }
        LogUtils.log(a, "lookup hasInit false " + str);
        return c(str, str2);
    }

    private static List<InetAddress> b(String str, String str2) {
        List<InetAddress> listA = a.a().a(str, str2);
        if (listA == null || listA.size() <= 0) {
            listA = Dns.SYSTEM.lookup(str2);
        }
        return listA == null ? new ArrayList() : listA;
    }

    private static List<InetAddress> c(String str, String str2) {
        List<InetAddress> listLookup = Dns.SYSTEM.lookup(str2);
        return listLookup == null ? new ArrayList() : listLookup;
    }
}
