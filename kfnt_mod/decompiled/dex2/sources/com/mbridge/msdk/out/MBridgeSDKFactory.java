package com.mbridge.msdk.out;

import com.mbridge.msdk.system.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeSDKFactory {
    private static a instance;

    private MBridgeSDKFactory() {
    }

    public static a getMBridgeSDK() {
        if (instance == null) {
            synchronized (MBridgeSDKFactory.class) {
                if (instance == null) {
                    instance = new a();
                }
            }
        }
        return instance;
    }
}
