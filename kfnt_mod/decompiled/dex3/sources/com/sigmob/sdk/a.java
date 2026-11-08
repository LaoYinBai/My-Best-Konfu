package com.sigmob.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.c;
import com.sigmob.sdk.base.common.d;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.b.b;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.volley.t;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static a a;
    private SigmobError b;
    private b c = null;
    private Context d;

    private a(final Context context) {
        this.d = context;
        t.b = Constants.IS_TEST.booleanValue();
        d.b();
        d.c();
        d.d();
        d.i();
        d.h();
        if (Constants.IS_TEST.booleanValue()) {
            d.a();
        }
        d.c(context);
        com.sigmob.sdk.base.common.t.a(PointCategory.INIT, (LoadAdRequest) null, new t.a() { // from class: com.sigmob.sdk.a.1
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    HashMap map = new HashMap();
                    map.put("is_minor", com.sigmob.sdk.common.a.n().ap() ? Constants.FAIL : "1");
                    map.put("is_unpersonalized", com.sigmob.sdk.common.a.n().aq() ? Constants.FAIL : "1");
                    pointEntitySigmob.setOptions(map);
                }
            }
        });
        if (SDKConfig.sharedInstance().isEnable_permission()) {
            com.sigmob.sdk.base.common.t.a(PointCategory.PERMISSION, PointCategory.INIT, (BaseAdUnit) null, new t.a() { // from class: com.sigmob.sdk.a.2
                @Override // com.sigmob.sdk.base.common.t.a
                public void a(Object obj) {
                    if (obj instanceof PointEntitySigmob) {
                        ((PointEntitySigmob) obj).setPermission(com.sigmob.sdk.common.a.n().b(context));
                    }
                }
            });
        }
        com.sigmob.sdk.base.common.t.a(PointCategory.APP);
        this.b = c.a(this.d);
        d.b(this.d);
        d();
    }

    public static a a(Context context) {
        if (context == null) {
            SigmobLog.e("initialize: failed ", new IllegalArgumentException("context is null"));
            return null;
        }
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public static boolean a() {
        return a != null;
    }

    public static a b() {
        return a;
    }

    private void d() {
        this.c = new b(new Handler(Looper.getMainLooper())) { // from class: com.sigmob.sdk.a.3
            @Override // com.sigmob.sdk.common.b.b
            protected void a() {
                try {
                    com.sigmob.sdk.base.a.c.a_();
                } catch (Throwable th) {
                    SigmobLog.e("retryFaildTracking error " + th.getMessage());
                }
            }
        };
        this.c.a(SDKConfig.sharedInstance().getAdTrackerRetryInterval());
    }

    public SigmobError c() {
        return this.b;
    }
}
