package com.umeng.message.tag;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements TagFilter {
    private static final String a = "com.umeng.message.tag.a";
    private static int b = 256;

    @Override // com.umeng.message.tag.TagFilter
    public boolean filter(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        if (str == null || str.length() <= b) {
            return true;
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, String.format("The length of %s exceeds allowed max length %i", str, Integer.valueOf(b)));
        return false;
    }
}
