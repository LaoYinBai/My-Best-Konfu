package com.meizu.cloud.pushsdk.platform.b;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends c<RegisterStatus> {
    private Handler h;
    private ScheduledExecutorService i;
    private int j;

    public b(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, aVar, scheduledExecutorService);
        this.i = (ScheduledExecutorService) com.meizu.cloud.pushsdk.c.b.a.b.a();
        this.h = new Handler(context.getMainLooper()) { // from class: com.meizu.cloud.pushsdk.platform.b.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    b.this.m();
                }
            }
        };
    }

    public b(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.g = z;
    }

    public b(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.j = 0;
    }

    private boolean a(String str, String str2, int i) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str2.startsWith(str) || System.currentTimeMillis() / 1000 > ((long) i);
    }

    protected void a(long j) {
        this.i.schedule(new Runnable() { // from class: com.meizu.cloud.pushsdk.platform.b.b.2
            @Override // java.lang.Runnable
            public void run() {
                com.meizu.cloud.pushsdk.base.c.a(b.this.a);
                b.this.h.sendEmptyMessage(0);
            }
        }, j, TimeUnit.SECONDS);
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    public void a(RegisterStatus registerStatus) {
        PlatformMessageSender.a(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), registerStatus);
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    public boolean a() {
        DebugLogger.e("Strategy", "isBrandMeizu " + MzSystemUtils.isBrandMeizu(this.a));
        return (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c)) ? false : true;
    }

    protected boolean a(String str, int i) {
        String strA = com.meizu.cloud.pushsdk.base.c.a(this.a);
        boolean zA = a(strA, str, i);
        return zA ? a(strA, com.meizu.cloud.pushsdk.platform.a.a(str), i) : zA;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    public Intent c() {
        Intent intent = new Intent();
        intent.putExtra("app_id", this.b);
        intent.putExtra(MBridgeConstans.APP_KEY, this.c);
        intent.putExtra("strategy_package_name", this.a.getPackageName());
        intent.putExtra("strategy_type", g());
        return intent;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected int g() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public RegisterStatus b() {
        String str;
        RegisterStatus registerStatus = new RegisterStatus();
        registerStatus.setCode("20001");
        if (!TextUtils.isEmpty(this.b)) {
            str = TextUtils.isEmpty(this.c) ? "appKey not empty" : "appId not empty";
            return registerStatus;
        }
        registerStatus.setMessage(str);
        return registerStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public RegisterStatus f() {
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public RegisterStatus e() {
        RegisterStatus registerStatus = new RegisterStatus();
        String strA = com.meizu.cloud.pushsdk.util.b.a(this.a, this.d);
        int iB = com.meizu.cloud.pushsdk.util.b.b(this.a, this.d);
        if (a(strA, iB)) {
            com.meizu.cloud.pushsdk.util.b.f(this.a, "", this.d);
            String strA2 = com.meizu.cloud.pushsdk.base.c.a(this.a);
            if (!TextUtils.isEmpty(strA2) || this.j >= 3) {
                this.j = 0;
                com.meizu.cloud.pushsdk.b.a.c cVarA = this.e.a(this.b, this.c, strA2);
                if (cVarA.b()) {
                    registerStatus = new RegisterStatus((String) cVarA.a());
                    DebugLogger.e("Strategy", "registerStatus " + registerStatus);
                    if (!TextUtils.isEmpty(registerStatus.getPushId())) {
                        com.meizu.cloud.pushsdk.util.b.f(this.a, registerStatus.getPushId(), this.d);
                        com.meizu.cloud.pushsdk.util.b.a(this.a, (int) ((System.currentTimeMillis() / 1000) + registerStatus.getExpireTime()), this.d);
                    }
                } else {
                    com.meizu.cloud.pushsdk.b.b.a aVarC = cVarA.c();
                    if (aVarC.a() != null) {
                        DebugLogger.e("Strategy", "status code=" + aVarC.b() + " data=" + aVarC.a());
                    }
                    registerStatus.setCode(String.valueOf(aVarC.b()));
                    registerStatus.setMessage(aVarC.c());
                    DebugLogger.e("Strategy", "registerStatus " + registerStatus);
                }
            } else {
                DebugLogger.i("Strategy", "after " + (this.j * 10) + " seconds start register");
                a((long) (this.j * 10));
                this.j = this.j + 1;
                registerStatus.setCode("20000");
                registerStatus.setMessage("deviceId is empty");
            }
        } else {
            registerStatus.setCode(BasicPushStatus.SUCCESS_CODE);
            registerStatus.setMessage("already register PushId,don't register frequently");
            registerStatus.setPushId(strA);
            registerStatus.setExpireTime((int) (iB - (System.currentTimeMillis() / 1000)));
        }
        return registerStatus;
    }
}
