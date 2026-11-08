package com.kwad.sdk.core.config;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static final Map<String, com.kwad.sdk.core.config.item.b> a = new ConcurrentHashMap();

    private static void a(SharedPreferences.Editor editor) {
        if (editor != null) {
            Iterator<String> it = a.keySet().iterator();
            while (it.hasNext()) {
                a.get(it.next()).a(editor);
            }
        }
    }

    private static void a(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            Iterator<String> it = a.keySet().iterator();
            while (it.hasNext()) {
                try {
                    a.get(it.next()).a(sharedPreferences);
                } catch (Exception e) {
                    com.kwad.sdk.core.d.a.b(e);
                }
            }
        }
    }

    public static <T> void a(@NonNull com.kwad.sdk.core.config.item.b<T> bVar) {
        a.put(bVar.b(), bVar);
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        for (String str : a.keySet()) {
            com.kwad.sdk.core.config.item.b bVar = a.get(str);
            if (jSONObject.has(str)) {
                bVar.a(jSONObject);
            }
        }
    }

    @WorkerThread
    public static synchronized boolean a(Context context) {
        if (context == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("ksadsdk_config", 0).edit();
        a(editorEdit);
        return editorEdit.commit();
    }

    @WorkerThread
    public static synchronized void b(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("ksadsdk_config", 0);
            if (sharedPreferences != null) {
                a(sharedPreferences);
            }
        }
    }
}
