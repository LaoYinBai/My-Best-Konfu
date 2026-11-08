package com.ss.android.download.api.config;

import com.ss.android.socialbase.downloader.exception.BaseException;
import java.io.File;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IApkUpdateHandler {
    JSONObject getApkUpdateConfig(String str);

    void tryApkUpdate(String str, String str2, File file, JSONObject jSONObject) throws BaseException;
}
