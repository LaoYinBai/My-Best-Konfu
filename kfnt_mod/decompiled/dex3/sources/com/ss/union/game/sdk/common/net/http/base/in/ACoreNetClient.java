package com.ss.union.game.sdk.common.net.http.base.in;

import android.content.Context;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ACoreNetClient {
    public abstract void cancel(String str);

    public abstract void cancelAll();

    public abstract void commonHeader(String str, String str2);

    public abstract Map<String, String> commonHeaders();

    public abstract void commonParam(String str, String str2);

    public abstract Map<String, String> commonUrlParams();

    public abstract ACoreRequestDownload download(String str);

    public abstract ACoreRequestGet get(String str);

    public abstract ACoreRequestHead head(String str);

    public abstract void init(Context context, String str);

    public abstract ACoreRequestPost post(String str);

    public abstract ACoreRequestTrace trace(String str);
}
