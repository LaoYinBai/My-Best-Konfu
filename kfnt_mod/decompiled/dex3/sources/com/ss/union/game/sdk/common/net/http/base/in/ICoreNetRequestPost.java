package com.ss.union.game.sdk.common.net.http.base.in;

import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetFileWrapper;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ICoreNetRequestPost<T extends ICoreNetRequestPost> extends ICoreNetRequest<T> {
    T enableGzip(boolean z);

    Map<String, CoreNetFileWrapper> fileParam();

    Map<String, List<CoreNetFileWrapper>> fileParams();

    T forceMultipart(boolean z);

    boolean isEnableGzip();

    boolean isForceMultipart();

    T param(String str, double d);

    T param(String str, float f);

    T param(String str, int i);

    T param(String str, long j);

    T param(String str, File file);

    T param(String str, File file, CoreNetFileWrapper.MediaType mediaType);

    T param(String str, String str2);

    T param(String str, List<File> list);

    T param(String str, boolean z);

    Map<String, String> param();

    T upBytes(byte[] bArr);

    T upBytes(byte[] bArr, CoreNetFileWrapper.MediaType mediaType);

    byte[] upBytes();

    T upFile(File file);

    T upFile(File file, CoreNetFileWrapper.MediaType mediaType);

    File upFile();

    T upJson(String str);

    T upJson(JSONArray jSONArray);

    T upJson(JSONObject jSONObject);

    String upJson();

    T upString(String str);

    T upString(String str, CoreNetFileWrapper.MediaType mediaType);

    String upString();
}
