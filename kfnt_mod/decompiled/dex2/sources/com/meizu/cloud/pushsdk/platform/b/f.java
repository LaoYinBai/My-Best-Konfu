package com.meizu.cloud.pushsdk.platform.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends c<PushSwitchStatus> {
    private String h;
    private int i;
    private boolean j;
    private final Map<String, Boolean> k;

    public f(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, aVar, scheduledExecutorService);
    }

    public f(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.g = z;
    }

    public f(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.i = 0;
        this.k = new HashMap();
    }

    public f(Context context, String str, String str2, String str3, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, aVar, scheduledExecutorService);
        this.h = str3;
    }

    private com.meizu.cloud.pushsdk.b.a.c<String> b(PushSwitchStatus pushSwitchStatus) {
        boolean zP;
        boolean zP2;
        boolean zR;
        switch (this.i) {
            case 0:
                pushSwitchStatus.setMessage("SWITCH_NOTIFICATION");
                if (p() != this.j || t()) {
                    f(true);
                    c(this.j);
                    return this.e.a(this.b, this.c, this.h, this.i, this.j);
                }
                zP = this.j;
                pushSwitchStatus.setSwitchNotificationMessage(zP);
                zR = r();
                pushSwitchStatus.setSwitchThroughMessage(zR);
                return null;
            case 1:
                pushSwitchStatus.setMessage("SWITCH_THROUGH_MESSAGE");
                if (r() != this.j || t()) {
                    f(true);
                    d(this.j);
                    return this.e.a(this.b, this.c, this.h, this.i, this.j);
                }
                zP2 = p();
                pushSwitchStatus.setSwitchNotificationMessage(zP2);
                zR = this.j;
                pushSwitchStatus.setSwitchThroughMessage(zR);
                return null;
            case 2:
                pushSwitchStatus.setMessage("CHECK_PUSH");
                if (!q() || !s() || t()) {
                    f(true);
                    return this.e.c(this.b, this.c, this.h);
                }
                zP = p();
                pushSwitchStatus.setSwitchNotificationMessage(zP);
                zR = r();
                pushSwitchStatus.setSwitchThroughMessage(zR);
                return null;
            case 3:
                pushSwitchStatus.setMessage("SWITCH_ALL");
                if (p() != this.j || r() != this.j || t()) {
                    f(true);
                    e(this.j);
                    return this.e.a(this.b, this.c, this.h, this.j);
                }
                zP2 = this.j;
                pushSwitchStatus.setSwitchNotificationMessage(zP2);
                zR = this.j;
                pushSwitchStatus.setSwitchThroughMessage(zR);
                return null;
            default:
                return null;
        }
    }

    private void c(boolean z) {
        com.meizu.cloud.pushsdk.util.b.a(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), z);
    }

    private void d(boolean z) {
        com.meizu.cloud.pushsdk.util.b.b(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), z);
    }

    private void e(boolean z) {
        com.meizu.cloud.pushsdk.util.b.a(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), z);
        com.meizu.cloud.pushsdk.util.b.b(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), z);
    }

    private void f(boolean z) {
        this.k.put(this.d + "_" + this.i, Boolean.valueOf(z));
    }

    private void o() {
        Context context;
        int i;
        int i2 = this.i;
        if (i2 != 3) {
            switch (i2) {
                case 0:
                case 1:
                    context = this.a;
                    i = this.i;
                    break;
                default:
                    return;
            }
        } else {
            PlatformMessageSender.a(this.a, 0, this.j, this.d);
            context = this.a;
            i = 1;
        }
        PlatformMessageSender.a(context, i, this.j, this.d);
    }

    private boolean p() {
        return com.meizu.cloud.pushsdk.util.b.e(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName());
    }

    private boolean q() {
        return com.meizu.cloud.pushsdk.util.b.f(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName());
    }

    private boolean r() {
        return com.meizu.cloud.pushsdk.util.b.h(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName());
    }

    private boolean s() {
        return com.meizu.cloud.pushsdk.util.b.i(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName());
    }

    private boolean t() {
        Boolean bool = this.k.get(this.d + "_" + this.i);
        boolean z = bool == null || bool.booleanValue();
        DebugLogger.e("Strategy", "isSyncPushStatus " + this.d + " switch type->" + this.i + " flag->" + z);
        return z;
    }

    public void a(int i) {
        this.i = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    public void a(PushSwitchStatus pushSwitchStatus) {
        PlatformMessageSender.a(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), pushSwitchStatus);
    }

    public void a(String str) {
        this.h = str;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected boolean a() {
        return (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.h)) ? false : true;
    }

    public void b(boolean z) {
        this.j = z;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected Intent c() {
        Intent intent = new Intent();
        intent.putExtra("app_id", this.b);
        intent.putExtra(MBridgeConstans.APP_KEY, this.c);
        intent.putExtra("strategy_package_name", this.a.getPackageName());
        intent.putExtra("push_id", this.h);
        intent.putExtra("strategy_type", g());
        intent.putExtra("strategy_child_type", this.i);
        intent.putExtra("strategy_params", this.j ? "1" : PushConstants.PUSH_TYPE_NOTIFY);
        return intent;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected int g() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public PushSwitchStatus b() {
        String str;
        PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
        pushSwitchStatus.setCode("20001");
        if (TextUtils.isEmpty(this.b)) {
            str = "appId not empty";
        } else {
            if (!TextUtils.isEmpty(this.c)) {
                if (TextUtils.isEmpty(this.h)) {
                    str = "pushId not empty";
                }
                return pushSwitchStatus;
            }
            str = "appKey not empty";
        }
        pushSwitchStatus.setMessage(str);
        return pushSwitchStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public PushSwitchStatus e() {
        PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
        pushSwitchStatus.setPushId(this.h);
        pushSwitchStatus.setCode(BasicPushStatus.SUCCESS_CODE);
        com.meizu.cloud.pushsdk.b.a.c<String> cVarB = b(pushSwitchStatus);
        if (cVarB != null) {
            if (cVarB.b()) {
                PushSwitchStatus pushSwitchStatus2 = new PushSwitchStatus(cVarB.a());
                DebugLogger.e("Strategy", "network pushSwitchStatus " + pushSwitchStatus2);
                if (BasicPushStatus.SUCCESS_CODE.equals(pushSwitchStatus.getCode())) {
                    f(false);
                    DebugLogger.e("Strategy", "update local switch preference");
                    pushSwitchStatus.setSwitchNotificationMessage(pushSwitchStatus2.isSwitchNotificationMessage());
                    pushSwitchStatus.setSwitchThroughMessage(pushSwitchStatus2.isSwitchThroughMessage());
                    c(pushSwitchStatus2.isSwitchNotificationMessage());
                    d(pushSwitchStatus2.isSwitchThroughMessage());
                }
            } else {
                com.meizu.cloud.pushsdk.b.b.a aVarC = cVarB.c();
                if (aVarC.a() != null) {
                    DebugLogger.e("Strategy", "status code=" + aVarC.b() + " data=" + aVarC.a());
                }
                pushSwitchStatus.setCode(String.valueOf(aVarC.b()));
                pushSwitchStatus.setMessage(aVarC.c());
                DebugLogger.e("Strategy", "pushSwitchStatus " + pushSwitchStatus);
            }
        }
        DebugLogger.e("Strategy", "enableRpc " + this.g + " isSupportRemoteInvoke " + this.f);
        if (this.g && !this.f) {
            o();
        }
        return pushSwitchStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public PushSwitchStatus f() {
        switch (this.i) {
            case 0:
                c(this.j);
                break;
            case 1:
                d(this.j);
                break;
            case 2:
            case 3:
                e(this.j);
                break;
        }
        return null;
    }
}
