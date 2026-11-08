package com.bytedance.push.f;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import com.bytedance.common.push.utility.a.c;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static c.a f4804a = new c.a() { // from class: com.bytedance.push.f.f.1
        @Override // com.bytedance.common.push.utility.a.c.a
        public void handleMsg(Message message) throws JSONException {
            f.b(message);
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static Handler f4805b = new com.bytedance.common.push.utility.a.c(e.a(), f4804a);

    /* renamed from: c, reason: collision with root package name */
    @SuppressLint({"UseSparseArrays"})
    private static final Map<Integer, Boolean> f4806c = new ConcurrentHashMap(2);

    static void a(int i, String str) throws JSONException {
        String str2;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("log", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (i) {
            case 301:
                str2 = "network error";
                break;
            case 302:
                str2 = "server return error";
                break;
            case 303:
                str2 = "server return empty allow_push_list";
                break;
            default:
                str2 = "update_sender api return empty";
                break;
        }
        a(i, jSONObject, str2);
        com.bytedance.push.utils.e.b("Monitor", "Push Sender Monitor: update sender error, reason: " + str2);
    }

    static void a() throws JSONException {
        com.bytedance.push.utils.e.c("Monitor", "Push Sender Monitor: update sender success");
        a(0, (JSONObject) null, "update sender success");
    }

    private static void a(int i, JSONObject jSONObject, String str) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e.a("push_monitor_update_sender", jSONObject2, null, jSONObject);
    }

    static void a(boolean z, String str) throws JSONException {
        if (z) {
            com.bytedance.push.utils.e.c("Monitor", "Push Sender Monitor: get sender success");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 107);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("sender", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        e.a("push_monitor_register_result", jSONObject, null, jSONObject2);
        com.bytedance.push.utils.e.b("Monitor", "Push Sender Monitor: This device does not support the sender/channel delivered by the server");
    }

    static void a(int i) {
        synchronized (f4806c) {
            if (f4806c.get(Integer.valueOf(i)) != null) {
                return;
            }
            f4806c.put(Integer.valueOf(i), true);
            f4805b.sendMessageDelayed(f4805b.obtainMessage(1001, Integer.valueOf(i)), TimeUnit.MINUTES.toMillis(1L));
        }
    }

    static void a(int i, int i2, String str, String str2) throws JSONException {
        synchronized (f4806c) {
            Boolean bool = f4806c.get(Integer.valueOf(i));
            if (bool != null && bool.booleanValue()) {
                f4806c.put(Integer.valueOf(i), false);
                c(i);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("pushType", i);
                    jSONObject.put("status", i2);
                    jSONObject.put("3rd_code", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("msg", str2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.bytedance.push.utils.e.b("Monitor", com.bytedance.push.third.e.a(com.ss.android.message.a.a()).d(i) + " register failed，reason: " + str2);
                e.a("push_monitor_register_result", jSONObject, null, jSONObject2);
            }
        }
    }

    static void b(int i) throws JSONException {
        synchronized (f4806c) {
            Boolean bool = f4806c.get(Integer.valueOf(i));
            if (bool != null && bool.booleanValue()) {
                f4806c.put(Integer.valueOf(i), false);
                c(i);
                String str = com.bytedance.push.third.e.a(com.ss.android.message.a.a()).d(i) + " register success";
                com.bytedance.push.utils.e.c("Monitor", "Push Sender Monitor: " + i + " register success");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("status", 0);
                    jSONObject.put("pushType", i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                e.a("push_monitor_register_result", jSONObject, null, null);
            }
        }
    }

    static void b() throws JSONException {
        com.bytedance.push.utils.e.c("Monitor", "Push switcher monitor：the out-app switcher upload success");
        a(0, "the out-app switcher upload success", (JSONObject) null);
    }

    static void b(int i, String str) throws JSONException {
        String str2;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("log", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (i) {
            case 301:
                str2 = "network error";
                break;
            case 302:
                str2 = "Server return not success";
                break;
            default:
                str2 = "Server return empty response";
                break;
        }
        a(i, str2, jSONObject);
        com.bytedance.push.utils.e.b("Monitor", "Push switcher monitor：upload error，reason: " + str2);
    }

    private static void a(int i, String str, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e.a("outer_switch_upload_result", jSONObject2, null, jSONObject);
    }

    private static void c(int i) {
        f4805b.removeMessages(1001, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Message message) throws JSONException {
        if (message != null && message.what == 1001) {
            String str = com.bytedance.push.third.e.a(com.ss.android.message.a.a()).d(((Integer) message.obj).intValue()) + " register timeout";
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", 108);
                jSONObject.put("pushType", message.obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            com.bytedance.push.utils.e.b("Monitor", "Push Sender Monitor:" + str);
            e.a("push_monitor_register_result", jSONObject, null, null);
        }
    }
}
