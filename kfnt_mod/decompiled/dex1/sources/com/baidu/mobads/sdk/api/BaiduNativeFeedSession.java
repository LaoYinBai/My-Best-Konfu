package com.baidu.mobads.sdk.api;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduNativeFeedSession {
    private static HashMap<String, String> sessionHashMap = new HashMap<>();
    private static BaiduNativeFeedSession theInstance;

    private BaiduNativeFeedSession() {
    }

    public static synchronized BaiduNativeFeedSession getInstance() {
        if (theInstance == null) {
            theInstance = new BaiduNativeFeedSession();
        }
        return theInstance;
    }

    public int getSequenceId(int i) {
        int i2 = 1;
        if (i < 1) {
            return 1;
        }
        try {
            if (sessionHashMap.containsKey(i + "")) {
                int i3 = Integer.parseInt(sessionHashMap.get(i + "")) + 1;
                if (i3 >= 1) {
                    i2 = i3;
                }
                sessionHashMap.put(i + "", i2 + "");
            } else {
                sessionHashMap.put(i + "", "1");
            }
        } catch (Exception unused) {
        }
        return i2;
    }
}
