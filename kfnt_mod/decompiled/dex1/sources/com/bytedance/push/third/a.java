package com.bytedance.push.third;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.bytedance.push.utils.i;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a {

    /* renamed from: b, reason: collision with root package name */
    protected static final Set<Integer> f4968b = new CopyOnWriteArraySet();

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"UseSparseArrays"})
    protected Map<Integer, i<b>> f4969a = new HashMap();

    public Pair<String, String> a(int i, com.bytedance.push.d dVar) {
        return null;
    }

    protected abstract void a();

    public int e() {
        return -1;
    }

    public a() {
        a();
    }

    public IPushAdapter a(int i) {
        b bVarC = this.f4969a.get(Integer.valueOf(i)).c(new Object[0]);
        if (bVarC == null) {
            return null;
        }
        return bVarC.b();
    }

    public int a(String str) {
        Log.d("bdpush", "getChannelId is called:" + str);
        if (this.f4969a == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        for (Integer num : this.f4969a.keySet()) {
            if (str.equals(this.f4969a.get(num).c(new Object[0]).c())) {
                return num.intValue();
            }
        }
        return -1;
    }

    public Set<Integer> b() {
        if (this.f4969a == null) {
            return null;
        }
        return this.f4969a.keySet();
    }

    public boolean b(int i) {
        b bVarC = this.f4969a.get(Integer.valueOf(i)).c(new Object[0]);
        if (bVarC == null) {
            return false;
        }
        return bVarC.a();
    }

    public JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        for (Integer num : this.f4969a.keySet()) {
            if (b(num.intValue())) {
                jSONArray.put(num);
            }
        }
        return jSONArray;
    }

    public boolean b(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !"[]".equals(str)) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() <= 0) {
                    return false;
                }
                if (jSONArray.optInt(0) == -9307) {
                    return true;
                }
                String string = c().toString();
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (string.contains(jSONArray.optInt(i) + "")) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void a(String str, boolean z) {
        if (z) {
            PushSetting.getInstance().setPushChannelsJsonArray(str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.bytedance.push.utils.e.a("support:" + str);
        JSONArray jSONArray = null;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONArray == null) {
            return;
        }
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("BaseChannelHelper", "handlerApplogConfig: jsonArray = " + jSONArray);
        }
        f4968b.clear();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            int iOptInt = jSONArray.optInt(i);
            if (iOptInt > 0) {
                f4968b.add(Integer.valueOf(iOptInt));
            }
        }
    }

    protected static void d() {
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("BaseChannelHelper", "initAllowPushSet: mAllowPushSet = " + f4968b);
        }
        if (f4968b.isEmpty()) {
            a(PushSetting.getInstance().getPushChannelsJsonArray(), false);
        }
    }

    public static boolean c(int i) {
        d();
        return f4968b.contains(Integer.valueOf(i));
    }

    public String d(int i) {
        return this.f4969a.get(Integer.valueOf(i)) == null ? "unknown" : this.f4969a.get(Integer.valueOf(i)).c(new Object[0]).d();
    }
}
