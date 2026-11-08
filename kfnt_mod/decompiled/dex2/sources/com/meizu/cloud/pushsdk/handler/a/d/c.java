package com.meizu.cloud.pushsdk.handler.a.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.meizu.cloud.pushsdk.handler.a.a<RegisterStatus> {
    public c(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String mzPushServicePackageName = MzSystemUtils.getMzPushServicePackageName(d());
        if (com.meizu.cloud.pushsdk.util.b.l(d(), mzPushServicePackageName)) {
            com.meizu.cloud.pushsdk.util.b.c(d(), mzPushServicePackageName, false);
            if (TextUtils.isEmpty(com.meizu.cloud.pushsdk.util.b.k(d(), mzPushServicePackageName))) {
                String strB = b();
                if (TextUtils.isEmpty(strB)) {
                    return;
                }
                com.meizu.cloud.pushsdk.util.b.j(d(), mzPushServicePackageName, strB);
            }
        }
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 512;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.a.a
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(RegisterStatus registerStatus) {
        com.meizu.cloud.pushsdk.base.f.a().execute(new Runnable() { // from class: com.meizu.cloud.pushsdk.handler.a.d.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.a.a
    public void a(RegisterStatus registerStatus, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (c() == null || registerStatus == null) {
            return;
        }
        c().a(d(), registerStatus);
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean a(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start RegisterStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_REGISTER_STATUS.equals(k(intent));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.a.a
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public RegisterStatus c(Intent intent) {
        RegisterStatus registerStatus = (RegisterStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_REGISTER_STATUS);
        if (!TextUtils.isEmpty(registerStatus.getPushId())) {
            com.meizu.cloud.pushsdk.util.b.f(d(), registerStatus.getPushId(), d().getPackageName());
            com.meizu.cloud.pushsdk.util.b.a(d(), (int) ((System.currentTimeMillis() / 1000) + registerStatus.getExpireTime()), d().getPackageName());
        }
        return registerStatus;
    }
}
