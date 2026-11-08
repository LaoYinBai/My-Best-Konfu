package com.umeng.message.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UMJobIntentService extends JobIntentService {
    private static int a = 21000;
    private static HashMap<Class<? extends UMJobIntentService>, Integer> b = new HashMap<>();
    private static Object c = new Object();

    public static void enqueueWork(Context context, Class<? extends UMJobIntentService> cls, Intent intent) {
        int iIntValue;
        synchronized (c) {
            MLog.i("wuchi", "--->>> UMJobIntentService enqueueWork, cla is " + cls.getName());
            if (context != null && cls != null && intent != null) {
                if (!b.containsKey(cls)) {
                    iIntValue = a + b.size();
                    b.put(cls, Integer.valueOf(iIntValue));
                } else {
                    iIntValue = b.get(cls).intValue();
                }
                MLog.i("wuchi", "UMJobIntentService cla jobId is " + iIntValue);
                enqueueWork(context, cls, iIntValue, intent);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void enqueueWork(Context context, String str, Intent intent) {
        Class<?> cls;
        int iIntValue;
        synchronized (c) {
            MLog.i("wuchi", "--->>> UMJobIntentService enqueueWork, className is " + str);
            if (context == null || TextUtils.isEmpty(str) || intent == null) {
                return;
            }
            try {
                cls = Class.forName(str);
            } catch (ClassNotFoundException unused) {
                cls = null;
            }
            if (cls == null) {
                MLog.i("wuchi", "UMJobIntentService cla == null");
                return;
            }
            if (!b.containsKey(cls)) {
                iIntValue = a + b.size();
                b.put(cls, Integer.valueOf(iIntValue));
            } else {
                iIntValue = b.get(cls).intValue();
            }
            MLog.i("wuchi", "UMJobIntentService className jobId is " + iIntValue);
            enqueueWork(context, cls, iIntValue, intent);
        }
    }

    @Override // com.umeng.message.service.JobIntentService
    protected void a(Intent intent) throws Throwable {
        MLog.i("wuchi", "--->>> UMJobIntentService onHandleWork");
    }
}
