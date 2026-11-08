package com.huawei.hms.framework.common.hianalytics;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class LinkedHashMapPack {
    private LinkedHashMap<String, String> map = new LinkedHashMap<>();

    public LinkedHashMap<String, String> getAll() {
        return this.map;
    }

    public LinkedHashMapPack put(String str, long j) {
        if (str != null) {
            this.map.put(str, "" + j);
        }
        return this;
    }

    public LinkedHashMapPack put(String str, String str2) {
        if (str != null && str2 != null) {
            this.map.put(str, str2);
        }
        return this;
    }

    public LinkedHashMapPack put(String str, boolean z) {
        LinkedHashMap<String, String> linkedHashMap;
        String str2;
        if (str != null) {
            if (z) {
                linkedHashMap = this.map;
                str2 = "1";
            } else {
                linkedHashMap = this.map;
                str2 = PushConstants.PUSH_TYPE_NOTIFY;
            }
            linkedHashMap.put(str, str2);
        }
        return this;
    }
}
