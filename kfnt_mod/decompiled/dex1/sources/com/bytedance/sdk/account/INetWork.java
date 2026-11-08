package com.bytedance.sdk.account;

import android.content.Context;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface INetWork {
    int checkResponseException(Context context, Throwable th);

    String executeGet(int i, String str) throws Exception;

    String executePost(int i, String str, Map<String, String> map) throws Exception;
}
