package com.meizu.cloud.pushsdk.platform.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends c<SubTagsStatus> {
    private String h;
    private int i;
    private String j;

    public e(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, aVar, scheduledExecutorService);
    }

    public e(Context context, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.g = z;
    }

    public e(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.i = 3;
    }

    public e(Context context, String str, String str2, String str3, com.meizu.cloud.pushsdk.platform.a.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, aVar, scheduledExecutorService);
        this.h = str3;
    }

    public void a(int i) {
        this.i = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    public void a(SubTagsStatus subTagsStatus) {
        PlatformMessageSender.a(this.a, !TextUtils.isEmpty(this.d) ? this.d : this.a.getPackageName(), subTagsStatus);
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
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public SubTagsStatus b() {
        String str;
        SubTagsStatus subTagsStatus = new SubTagsStatus();
        subTagsStatus.setCode("20001");
        if (TextUtils.isEmpty(this.b)) {
            str = "appId not empty";
        } else {
            if (!TextUtils.isEmpty(this.c)) {
                if (TextUtils.isEmpty(this.h)) {
                    str = "pushId not empty";
                }
                return subTagsStatus;
            }
            str = "appKey not empty";
        }
        subTagsStatus.setMessage(str);
        return subTagsStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public SubTagsStatus e() {
        com.meizu.cloud.pushsdk.b.a.c cVarA;
        String str;
        StringBuilder sb;
        String str2;
        SubTagsStatus subTagsStatus = new SubTagsStatus();
        switch (this.i) {
            case 0:
                cVarA = this.e.a(this.b, this.c, this.h, this.j);
                break;
            case 1:
                cVarA = this.e.b(this.b, this.c, this.h, this.j);
                break;
            case 2:
                cVarA = this.e.d(this.b, this.c, this.h);
                break;
            case 3:
                cVarA = this.e.e(this.b, this.c, this.h);
                break;
            default:
                cVarA = null;
                break;
        }
        if (cVarA == null) {
            DebugLogger.e("Strategy", "network anResponse is null");
            return null;
        }
        if (cVarA.b()) {
            subTagsStatus = new SubTagsStatus((String) cVarA.a());
            str = "Strategy";
            sb = new StringBuilder();
            str2 = "network subTagsStatus ";
        } else {
            com.meizu.cloud.pushsdk.b.b.a aVarC = cVarA.c();
            if (aVarC.a() != null) {
                DebugLogger.e("Strategy", "status code=" + aVarC.b() + " data=" + aVarC.a());
            }
            subTagsStatus.setCode(String.valueOf(aVarC.b()));
            subTagsStatus.setMessage(aVarC.c());
            str = "Strategy";
            sb = new StringBuilder();
            str2 = "subTagsStatus ";
        }
        sb.append(str2);
        sb.append(subTagsStatus);
        DebugLogger.e(str, sb.toString());
        return subTagsStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.platform.b.c
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public SubTagsStatus f() {
        return null;
    }
}
