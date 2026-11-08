package com.apm.insight.b;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import java.lang.reflect.Field;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static MessageQueue f2816a;

    /* renamed from: b, reason: collision with root package name */
    private static Field f2817b;

    /* renamed from: c, reason: collision with root package name */
    private static Field f2818c;

    protected static Message a(Message message) {
        if (f2818c != null) {
            try {
                return (Message) f2818c.get(message);
            } catch (Exception unused) {
                return null;
            }
        }
        try {
            f2818c = Class.forName("android.os.Message").getDeclaredField("next");
            f2818c.setAccessible(true);
            return (Message) f2818c.get(message);
        } catch (Exception unused2) {
            return null;
        }
    }

    public static Message a(MessageQueue messageQueue) {
        if (f2817b != null) {
            try {
                return (Message) f2817b.get(messageQueue);
            } catch (Exception unused) {
                return null;
            }
        }
        try {
            f2817b = Class.forName("android.os.MessageQueue").getDeclaredField("mMessages");
            f2817b.setAccessible(true);
            return (Message) f2817b.get(messageQueue);
        } catch (Exception unused2) {
            return null;
        }
    }

    public static MessageQueue a() {
        MessageQueue queue;
        if (f2816a == null && Looper.getMainLooper() != null) {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == Looper.myLooper()) {
                queue = Looper.myQueue();
            } else if (Build.VERSION.SDK_INT >= 23) {
                queue = mainLooper.getQueue();
            } else {
                try {
                    Field declaredField = mainLooper.getClass().getDeclaredField("mQueue");
                    declaredField.setAccessible(true);
                    f2816a = (MessageQueue) declaredField.get(mainLooper);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            f2816a = queue;
        }
        return f2816a;
    }

    public static JSONArray a(int i, long j) {
        MessageQueue messageQueueA = a();
        JSONArray jSONArray = new JSONArray();
        if (messageQueueA == null) {
            return jSONArray;
        }
        try {
            synchronized (messageQueueA) {
                Message messageA = a(messageQueueA);
                if (messageA == null) {
                    return jSONArray;
                }
                int i2 = 0;
                int i3 = 0;
                while (messageA != null && i2 < i) {
                    i2++;
                    i3++;
                    JSONObject jSONObjectA = a(messageA, j);
                    try {
                        jSONObjectA.put("id", i3);
                    } catch (JSONException unused) {
                    }
                    jSONArray.put(jSONObjectA);
                    messageA = a(messageA);
                }
                return jSONArray;
            }
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
            return jSONArray;
        }
    }

    private static JSONObject a(Message message, long j) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (message == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("when", message.getWhen() - j);
            if (message.getCallback() != null) {
                jSONObject.put(com.bytedance.bdturing.c.i.i, String.valueOf(message.getCallback()));
            }
            jSONObject.put("what", message.what);
            if (message.getTarget() != null) {
                jSONObject.put("target", String.valueOf(message.getTarget()));
            } else {
                jSONObject.put("barrier", message.arg1);
            }
            jSONObject.put("arg1", message.arg1);
            jSONObject.put("arg2", message.arg2);
            if (message.obj != null) {
                jSONObject.put("obj", message.obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
