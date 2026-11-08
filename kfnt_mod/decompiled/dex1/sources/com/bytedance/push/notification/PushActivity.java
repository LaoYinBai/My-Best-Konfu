package com.bytedance.push.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.push.PushBody;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PushActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) throws JSONException {
        super.onCreate(bundle);
        a();
        finish();
    }

    private void a() throws JSONException {
        Intent intent = getIntent();
        if (intent == null) {
            com.bytedance.push.i.l().b("PushActivity", "intent is null");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str : extras.keySet()) {
                com.bytedance.push.i.l().a("PushActivity", "onCreate: key = " + str + " , val = " + extras.get(str));
            }
        }
        boolean zA = com.ss.android.push.a.a(intent, "from_notification", false);
        String stringExtra = intent.getStringExtra("push_body");
        if (!zA) {
            com.bytedance.push.i.l().b("PushActivity", "fromNotification = false");
            return;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            com.bytedance.push.i.l().b("PushActivity", "msgBody is null");
            return;
        }
        int iA = com.ss.android.push.a.a(intent, "message_from", 0);
        try {
            if (com.bytedance.push.third.e.a(getApplicationContext()).e(iA)) {
                JSONObject jSONObject = new JSONObject(stringExtra);
                jSONObject.put(PushBody.KEY_PASS_THROUGH, 0);
                stringExtra = jSONObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.bytedance.push.i.n().a(this, stringExtra, iA);
    }
}
