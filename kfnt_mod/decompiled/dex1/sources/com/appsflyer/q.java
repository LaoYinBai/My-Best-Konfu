package com.appsflyer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class q implements b {

    /* renamed from: ˊ, reason: contains not printable characters */
    private b f252 = this;

    interface b {
        /* renamed from: ˊ */
        Class<?> mo204(String str) throws ClassNotFoundException;
    }

    enum e {
        UNITY("android_unity", "com.unity3d.player.UnityPlayer"),
        REACT_NATIVE("android_reactNative", "com.facebook.react.ReactApplication"),
        CORDOVA("android_cordova", "org.apache.cordova.CordovaActivity"),
        SEGMENT("android_segment", "com.segment.analytics.integrations.Integration"),
        COCOS2DX("android_cocos2dx", "org.cocos2dx.lib.Cocos2dxActivity"),
        DEFAULT("android_native", "android_native");


        /* renamed from: ʻ, reason: contains not printable characters */
        private String f260;

        /* renamed from: ʼ, reason: contains not printable characters */
        private String f261;

        e(String str, String str2) {
            this.f261 = str;
            this.f260 = str2;
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final String m205() {
        for (e eVar : e.values()) {
            if (m203(eVar.f260)) {
                return eVar.f261;
            }
        }
        return e.DEFAULT.f261;
    }

    q() {
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private boolean m203(String str) {
        try {
            this.f252.mo204(str);
            StringBuilder sb = new StringBuilder("Class: ");
            sb.append(str);
            sb.append(" is found.");
            AFLogger.afRDLog(sb.toString());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return false;
        }
    }

    @Override // com.appsflyer.q.b
    /* renamed from: ˊ, reason: contains not printable characters */
    public final Class<?> mo204(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
