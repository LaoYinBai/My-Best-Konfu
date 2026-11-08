package com.sigmob.devicehelper.oaId.helpers;

import android.content.Context;
import com.sigmob.logger.SigmobLog;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j {
    private Context a;
    private Class b;
    private Object c;
    private Method d;

    public j(Context context) {
        try {
            this.b = Class.forName("com.android.id.impl.IdProviderImpl");
            this.c = this.b.newInstance();
            this.a = context;
            this.d = this.b.getMethod("getOAID", Context.class);
        } catch (Exception e) {
            SigmobLog.e(e.getMessage());
        }
    }

    private String a(Context context, Method method) {
        if (this.c != null && method != null) {
            try {
                return (String) method.invoke(this.c, context);
            } catch (Exception e) {
                SigmobLog.e(e.getMessage());
            }
        }
        return null;
    }

    public String a() {
        return a(this.a, this.d);
    }
}
