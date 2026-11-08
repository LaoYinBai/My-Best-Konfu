package com.ss.union.game.sdk.core.base.event;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.StringUtils;
import com.ss.union.game.sdk.core.base.event.TeaSafeThread;
import com.umeng.message.MsgConstant;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PageStater {
    static final long a = 864000000;
    private static volatile EventRequestTraceCallback b;
    private static INetUploadStrategy c = new i();
    private static Handler d = new Handler(Looper.getMainLooper());

    public interface EventRequestTraceCallback {
        void onEventDeleteResult(boolean z, String str, long j);

        void onEventDiscard(int i);

        void onEventExpired(List<Long> list);

        void onEventInsertResult(boolean z, String str, long j);

        void onLogRequestResult(boolean z, List<Long> list);
    }

    public static void init(Context context, InitConfig initConfig) {
        if (initConfig == null) {
            initConfig = new InitConfig();
        }
        c = initConfig.a;
        a.a(context);
        TeaSafeThread.getInst().install(new TeaSafeThread.ExceptionHandler() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.1
            @Override // com.ss.union.game.sdk.core.base.event.TeaSafeThread.ExceptionHandler
            public void handlerException(Thread thread, Throwable th) {
                g.b("catchEventException", th);
            }
        });
        d.a(5L);
    }

    public static void onEvent(final String str, final Map<String, String> map) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                if (map != null) {
                    for (Map.Entry entry : map.entrySet()) {
                        String str2 = (String) entry.getKey();
                        Object value = entry.getValue();
                        if ((value instanceof String) || (value instanceof Number) || (value instanceof Boolean)) {
                            try {
                                jSONObject.put(str2, value);
                            } catch (Exception e) {
                                g.a("事件参数解析失败", e);
                            }
                        } else {
                            g.b("丢失事件参数: key=" + str2 + ",value=" + value, null);
                        }
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("event_value", jSONObject.toString());
                    jSONObject2.put(f.a, jCurrentTimeMillis);
                } catch (Exception e2) {
                    g.a("事件参数解析失败", e2);
                }
                d.a(new b(str, jSONObject2));
            }
        });
    }

    public static void onEvent(final String str, JSONObject jSONObject) {
        final JSONObject jSONObjectA = a(jSONObject);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.3
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                if (StringUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject2 = jSONObjectA;
                if (jSONObject2 == null) {
                    jSONObject2 = new JSONObject();
                }
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("event_value", jSONObject2.toString());
                    jSONObject3.put(f.a, jCurrentTimeMillis);
                } catch (Exception e) {
                    g.a("事件参数解析失败", e);
                }
                d.a(new b(str, jSONObject2));
            }
        });
    }

    private static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<String> itKeys = jSONObject.keys();
        if (itKeys == null) {
            return null;
        }
        while (itKeys.hasNext()) {
            linkedList.add(itKeys.next());
        }
        try {
            return new JSONObject(jSONObject, (String[]) linkedList.toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public static void onEvent(final String str, final Bundle bundle) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.4
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                if (bundle != null) {
                    for (String str2 : bundle.keySet()) {
                        Object obj = bundle.get(str2);
                        if ((obj instanceof String) || (obj instanceof Number) || (obj instanceof Boolean)) {
                            try {
                                jSONObject.put(str2, obj);
                            } catch (Exception e) {
                                g.a("事件参数解析失败", e);
                            }
                        } else {
                            g.b("丢失事件参数: key=" + str2 + ",value=" + obj, null);
                        }
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("event_value", jSONObject.toString());
                    jSONObject2.put(f.a, jCurrentTimeMillis);
                } catch (Exception e2) {
                    g.a("事件参数解析失败", e2);
                }
                d.a(new b(str, jSONObject2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final String str, final JSONObject jSONObject) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.5
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                if (jSONObject != null) {
                    try {
                        jSONObject.put(f.a, jCurrentTimeMillis);
                    } catch (Exception unused) {
                    }
                }
                d.a(new b(str, jSONObject));
            }
        });
    }

    public static void eagerFlush() {
        TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.6
            @Override // java.lang.Runnable
            public void run() {
                d.b();
            }
        });
    }

    public static void registerLogRequestCallback(EventRequestTraceCallback eventRequestTraceCallback) {
        b = eventRequestTraceCallback;
    }

    static INetUploadStrategy a() {
        return c;
    }

    static void a(final boolean z, final List<Long> list) {
        a(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.7
            @Override // java.lang.Runnable
            public void run() {
                EventRequestTraceCallback eventRequestTraceCallback = PageStater.b;
                if (eventRequestTraceCallback != null) {
                    eventRequestTraceCallback.onLogRequestResult(z, list);
                }
            }
        });
    }

    static void a(final boolean z, final String str, final long j) {
        a(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.8
            @Override // java.lang.Runnable
            public void run() {
                EventRequestTraceCallback eventRequestTraceCallback = PageStater.b;
                if (eventRequestTraceCallback != null) {
                    eventRequestTraceCallback.onEventInsertResult(z, str, j);
                }
            }
        });
    }

    static void b(final boolean z, final String str, final long j) {
        a(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.PageStater.9
            @Override // java.lang.Runnable
            public void run() {
                EventRequestTraceCallback eventRequestTraceCallback = PageStater.b;
                if (eventRequestTraceCallback != null) {
                    eventRequestTraceCallback.onEventDeleteResult(z, str, j);
                }
            }
        });
    }

    private static void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            d.post(runnable);
        }
    }

    public static class V1 {
        public static void onEvent(String str, String str2) throws JSONException {
            onEvent(str, str2, null, 0, 0L, "", null);
        }

        public static void onEvent(String str, String str2, String str3) throws JSONException {
            onEvent(str, str2, str3, 0, 0L, "", null);
        }

        public static void onEvent(String str, String str2, String str3, int i) throws JSONException {
            onEvent(str, str2, str3, i, 0L, "", null);
        }

        public static void onEvent(String str, String str2, String str3, int i, long j) throws JSONException {
            onEvent(str, str2, str3, i, j, "", null);
        }

        public static void onEvent(String str, String str2, String str3, int i, long j, String str4) throws JSONException {
            onEvent(str, str2, str3, i, j, str4, null);
        }

        public static void onEvent(String str, String str2, String str3, int i, long j, String str4, Map<String, Object> map) throws JSONException {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("value", j);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("event_type_value", str2);
                }
                if (i != 0) {
                    jSONObject.put("error_code", i);
                }
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject.put("logid", str4);
                }
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("event_value", str3);
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put(MsgConstant.INAPP_LABEL, str);
                }
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (!TextUtils.isEmpty(key) && value != null) {
                            jSONObject.put(key, value);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                g.b("onEvent fail", e);
            }
            PageStater.b(str2, jSONObject);
        }
    }
}
