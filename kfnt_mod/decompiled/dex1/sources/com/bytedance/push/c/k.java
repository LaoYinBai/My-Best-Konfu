package com.bytedance.push.c;

import android.app.Service;
import android.content.Context;
import android.util.Pair;
import com.ss.android.pushmanager.MessageConstants;
import java.io.IOException;
import java.util.zip.DataFormatException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface k {

    /* renamed from: a, reason: collision with root package name */
    public static final String f4769a = MessageConstants.getSendPushTokenUrl();

    Pair<String, String> a(int i);

    com.ss.android.push.b<String, String, String> a();

    String a(Context context, int i);

    JSONObject a(byte[] bArr, boolean z) throws DataFormatException, IOException;

    void a(int i, String str, String str2);

    void a(Service service);

    void a(Context context, int i, String str);

    void a(Context context, com.bytedance.push.third.c cVar);

    void a(Context context, String str, int i, String str2);

    void a(Context context, String str, JSONObject jSONObject);

    boolean a(Context context);

    boolean a(Context context, String str);
}
