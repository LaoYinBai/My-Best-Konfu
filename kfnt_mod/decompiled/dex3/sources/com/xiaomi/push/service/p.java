package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Pair;
import com.xiaomi.push.ga;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class p {

    /* renamed from: a, reason: collision with other field name */
    private static final Map<String, byte[]> f966a = new HashMap();
    private static ArrayList<Pair<String, byte[]>> a = new ArrayList<>();

    public static void a(Context context, int i, String str) {
        synchronized (f966a) {
            for (String str2 : f966a.keySet()) {
                a(context, str2, f966a.get(str2), i, str);
            }
            f966a.clear();
        }
    }

    public static void a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, y.a(str));
    }

    public static void a(XMPushService xMPushService) {
        try {
            synchronized (f966a) {
                for (String str : f966a.keySet()) {
                    y.a(xMPushService, str, f966a.get(str));
                }
                f966a.clear();
            }
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.d("fail to deal with pending register request. " + e);
            xMPushService.a(10, e);
        }
    }

    public static void a(String str, byte[] bArr) {
        synchronized (f966a) {
            f966a.put(str, bArr);
        }
    }

    public static void b(XMPushService xMPushService) throws InterruptedException {
        ArrayList<Pair<String, byte[]>> arrayList;
        try {
            synchronized (a) {
                arrayList = a;
                a = new ArrayList<>();
            }
            boolean z = Thread.currentThread() == Looper.getMainLooper().getThread();
            Iterator<Pair<String, byte[]>> it = arrayList.iterator();
            while (it.hasNext()) {
                Pair<String, byte[]> next = it.next();
                y.a(xMPushService, (String) next.first, (byte[]) next.second);
                if (!z) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.d("meet error when process pending message. " + e);
            xMPushService.a(10, e);
        }
    }

    public static void b(String str, byte[] bArr) {
        synchronized (a) {
            a.add(new Pair<>(str, bArr));
            if (a.size() > 50) {
                a.remove(0);
            }
        }
    }
}
