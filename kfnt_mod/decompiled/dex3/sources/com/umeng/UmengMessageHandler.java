package com.umeng;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.ss.android.b.a.b;
import com.ss.android.pushmanager.a.a;
import com.um.UmPushAdapter;
import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengMessageHandler extends UmengMessageService {
    @Override // com.umeng.message.service.JobIntentService, android.app.Service
    public void onCreate() {
        try {
            super.onCreate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        try {
            super.onStart(intent, i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.umeng.message.service.JobIntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            int iOnStartCommand = super.onStartCommand(intent, i, i2);
            if (((a.b) b.a(a.b.class)).g()) {
                return 2;
            }
            return iOnStartCommand;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @Override // com.umeng.message.service.JobIntentService, android.app.Service
    public void onDestroy() {
        try {
            super.onDestroy();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.umeng.message.UmengMessageService
    public void onMessage(Context context, Intent intent) {
        JSONObject jSONObjectA;
        try {
            String stringExtra = intent.getStringExtra("body");
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            if (i.l().a() && stringExtra != null) {
                i.l().a("UmengPush", "message=" + stringExtra);
                i.l().a("UmengPush", "custom=" + uMessage.custom);
                i.l().a("UmengPush", "title=" + uMessage.title);
                i.l().a("UmengPush", "text=" + uMessage.text);
            }
            String str = uMessage.custom;
            if (TextUtils.isEmpty(str) || (jSONObjectA = i.m().a(str.getBytes(), false)) == null) {
                return;
            }
            i.n().a(jSONObjectA.toString(), UmPushAdapter.getUmPush(), stringExtra);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
