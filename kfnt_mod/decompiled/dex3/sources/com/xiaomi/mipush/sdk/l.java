package com.xiaomi.mipush.sdk;

import com.xiaomi.push.ho;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l {
    private static HashMap<e, a> a = new HashMap<>();

    static class a {
        public String a;
        public String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    static {
        a(e.ASSEMBLE_PUSH_HUAWEI, new a("com.xiaomi.assemble.control.HmsPushManager", "newInstance"));
        a(e.ASSEMBLE_PUSH_FCM, new a("com.xiaomi.assemble.control.FCMPushManager", "newInstance"));
        a(e.ASSEMBLE_PUSH_COS, new a("com.xiaomi.assemble.control.COSPushManager", "newInstance"));
        a(e.ASSEMBLE_PUSH_FTOS, new a("com.xiaomi.assemble.control.FTOSPushManager", "newInstance"));
    }

    public static at a(e eVar) {
        switch (eVar) {
            case ASSEMBLE_PUSH_HUAWEI:
                return at.UPLOAD_HUAWEI_TOKEN;
            case ASSEMBLE_PUSH_FCM:
                return at.UPLOAD_FCM_TOKEN;
            case ASSEMBLE_PUSH_COS:
                return at.UPLOAD_COS_TOKEN;
            case ASSEMBLE_PUSH_FTOS:
                return at.UPLOAD_FTOS_TOKEN;
            default:
                return null;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static a m85a(e eVar) {
        return a.get(eVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static ho m86a(e eVar) {
        return ho.AggregatePushSwitch;
    }

    private static void a(e eVar, a aVar) {
        if (aVar != null) {
            a.put(eVar, aVar);
        }
    }
}
