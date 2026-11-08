package com.meizu.cloud.pushsdk.platform.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends c<SubAliasStatus> {
    private String h;
    private int i;
    private String j;
    private final Map<String, Boolean> k;

    public d(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, aVar, scheduledExecutorService);
    }

    public d(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.g = z;
    }

    public d(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.k = new HashMap();
    }

    public d(Context context, String str, String str2, String str3, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, aVar, scheduledExecutorService);
        this.h = str3;
    }

    private void b(boolean z) {
        this.k.put(this.d + "_" + this.i, Boolean.valueOf(z));
    }

    private void f(String str) {
        com.meizu.cloud.pushsdk.util.b.g(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), str);
    }

    private String o() {
        return com.meizu.cloud.pushsdk.util.b.g(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName());
    }

    private boolean p() {
        Boolean bool = this.k.get(this.d + "_" + this.i);
        return bool == null || bool.booleanValue();
    }

    private boolean q() {
        return !this.f && PushConstants.PUSH_PACKAGE_NAME.equals(this.d);
    }

    public void a(int i) {
        this.i = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    public void a(SubAliasStatus subAliasStatus) {
        PlatformMessageSender.a(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), subAliasStatus);
    }

    public void a(String str) {
        this.j = str;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected boolean a() {
        return (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.h)) ? false : true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected Intent c() {
        if (this.i == 2) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra("app_id", this.b);
        intent.putExtra(MBridgeConstans.APP_KEY, this.c);
        intent.putExtra("strategy_package_name", this.a.getPackageName());
        intent.putExtra("push_id", this.h);
        intent.putExtra("strategy_type", g());
        intent.putExtra("strategy_child_type", this.i);
        intent.putExtra("strategy_params", this.j);
        return intent;
    }

    public void e(String str) {
        this.h = str;
    }

    @Override // com.meizu.cloud.pushsdk.platform.b.c
    protected int g() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public SubAliasStatus b() {
        String str;
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setCode("20001");
        if (TextUtils.isEmpty(this.b)) {
            str = "appId not empty";
        } else {
            if (!TextUtils.isEmpty(this.c)) {
                if (TextUtils.isEmpty(this.h)) {
                    str = "pushId not empty";
                }
                return subAliasStatus;
            }
            str = "appKey not empty";
        }
        subAliasStatus.setMessage(str);
        return subAliasStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public SubAliasStatus e() {
        com.meizu.cloud.pushsdk.b.a.c cVarC;
        String str;
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setPushId(this.h);
        subAliasStatus.setMessage("");
        switch (this.i) {
            case 0:
                if (this.j.equals(o()) && !p()) {
                    subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
                    str = this.j;
                    subAliasStatus.setAlias(str);
                    cVarC = null;
                    break;
                } else {
                    b(true);
                    if (q()) {
                        f(this.j);
                    }
                    cVarC = this.e.c(this.b, this.c, this.h, this.j);
                    break;
                }
                break;
            case 1:
                if (TextUtils.isEmpty(o()) && !p()) {
                    subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
                    str = "";
                    subAliasStatus.setAlias(str);
                    cVarC = null;
                    break;
                } else {
                    b(true);
                    if (q()) {
                        f("");
                    }
                    cVarC = this.e.d(this.b, this.c, this.h, this.j);
                    break;
                }
                break;
            case 2:
                subAliasStatus.setAlias(o());
                subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
            default:
                cVarC = null;
                break;
        }
        if (cVarC != null) {
            if (cVarC.b()) {
                subAliasStatus = new SubAliasStatus((String) cVarC.a());
                DebugLogger.e("Strategy", "network subAliasStatus " + subAliasStatus);
                if (BasicPushStatus.SUCCESS_CODE.equals(subAliasStatus.getCode())) {
                    b(false);
                }
            } else {
                com.meizu.cloud.pushsdk.b.b.a aVarC = cVarC.c();
                if (aVarC.a() != null) {
                    DebugLogger.e("Strategy", "status code=" + aVarC.b() + " data=" + aVarC.a());
                }
                subAliasStatus.setCode(String.valueOf(aVarC.b()));
                subAliasStatus.setMessage(aVarC.c());
                DebugLogger.e("Strategy", "subAliasStatus " + subAliasStatus);
            }
        }
        return subAliasStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public SubAliasStatus f() {
        if (this.i != 2) {
            return null;
        }
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
        subAliasStatus.setPushId(this.h);
        subAliasStatus.setAlias(o());
        subAliasStatus.setMessage("check alias success");
        return subAliasStatus;
    }
}
