package com.vivo.push.util;

import android.content.Context;
import com.vivo.push.NoPorGuard;
import java.lang.reflect.Method;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ContextDelegate {
    private static final String TAG = "ContextDelegate";
    private static ContextDelegate mContextDelegate;
    private Method mCreateCredentialProtectedStorageContext;
    private Method mCreateDeviceProtectedStorageContext;
    private Boolean mIsFbeProj = null;

    private ContextDelegate() {
    }

    public static ContextDelegate getInstance() {
        if (mContextDelegate == null) {
            synchronized (ContextDelegate.class) {
                if (mContextDelegate == null) {
                    mContextDelegate = new ContextDelegate();
                }
            }
        }
        return mContextDelegate;
    }

    public Context createDeviceProtectedStorageContext(Context context) {
        try {
            if (this.mCreateDeviceProtectedStorageContext == null) {
                this.mCreateDeviceProtectedStorageContext = Context.class.getMethod("createDeviceProtectedStorageContext", new Class[0]);
            }
            return (Context) this.mCreateDeviceProtectedStorageContext.invoke(context, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return context;
        }
    }

    public Context createCredentialProtectedStorageContext(Context context) {
        try {
            if (this.mCreateCredentialProtectedStorageContext == null) {
                this.mCreateCredentialProtectedStorageContext = Context.class.getMethod("createCredentialProtectedStorageContext", new Class[0]);
            }
            return (Context) this.mCreateCredentialProtectedStorageContext.invoke(context, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return context;
        }
    }

    public static Context getContext(Context context) {
        return !getInstance().isFBEProject() ? context : getInstance().createDeviceProtectedStorageContext(context);
    }

    public boolean isFBEProject() {
        if (this.mIsFbeProj == null) {
            this.mIsFbeProj = Boolean.valueOf("file".equals(i.a("ro.crypto.type", "unknow")));
            n.b(TAG, "mIsFbeProj = " + this.mIsFbeProj.toString());
        }
        if (this.mIsFbeProj == null) {
            return false;
        }
        return this.mIsFbeProj.booleanValue();
    }
}
