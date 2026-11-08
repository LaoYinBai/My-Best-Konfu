package b.b.a.a;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static volatile a f1029a;

    /* renamed from: b, reason: collision with root package name */
    public volatile boolean f1030b = false;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f1031c = false;
    public ConcurrentHashMap<String, String> d = new ConcurrentHashMap<>();
    public WeakReference<Context> e;
    public volatile String f;

    public a(Context context) {
        String userData;
        this.e = new WeakReference<>(context);
        if (a()) {
            String string = this.e.get().getSharedPreferences("debug_new_user_mode_sp", 0).getString("new_user_mode_json_str", "");
            if (TextUtils.isEmpty(string)) {
                Context context2 = this.e.get();
                try {
                    userData = AccountManager.get(context2).getUserData(b.a(context2), "new_user_mode_account");
                } catch (Throwable th) {
                    th.printStackTrace();
                    userData = "";
                }
                string = (userData == null || !userData.startsWith("newUserModeUtil:")) ? "" : userData.substring(16);
            }
            try {
                a(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editorEdit = this.e.get().getSharedPreferences("debug_new_user_mode_sp", 0).edit();
            editorEdit.putString("new_user_mode_json_str", string);
            editorEdit.apply();
        }
    }

    public static a a(Context context) {
        if (f1029a == null) {
            synchronized (a.class) {
                if (f1029a == null) {
                    f1029a = new a(context);
                }
            }
        }
        return f1029a;
    }

    public final boolean a() {
        if (TextUtils.isEmpty(this.f)) {
            this.f = "local_test";
        }
        return "local_test".equals(this.f);
    }

    public final void a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.f1030b = jSONObject.optBoolean("is_new_user_mode", false);
        this.f1031c = jSONObject.optBoolean("auto_mode", false);
        JSONObject jSONObject2 = new JSONObject(jSONObject.optString("debug_custom_param", ""));
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            this.d.put(next, jSONObject2.optString(next));
        }
    }
}
