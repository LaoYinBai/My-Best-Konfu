package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultLoadControl;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class InstallActivateReminderConfigItem extends b<InstallActivateReminderConfig> {

    public static class InstallActivateReminderConfig extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        private static final long serialVersionUID = -6457271849826128465L;
        public int noticeTotalCount = 3;
        public int perAppNoticeCount = 2;
        public int noticeAppearTime = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS;
        public int noticeContinueTime = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS;
    }

    public InstallActivateReminderConfigItem() {
        super("installActivateReminderConfig", new InstallActivateReminderConfig());
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences.Editor editor) {
        String strB;
        String string;
        if (a() == null || a().toJson() == null) {
            strB = b();
            string = "";
        } else {
            strB = b();
            string = a().toJson().toString();
        }
        editor.putString(strB, string);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences sharedPreferences) {
        InstallActivateReminderConfig installActivateReminderConfigA = a();
        if (installActivateReminderConfigA == null) {
            installActivateReminderConfigA = new InstallActivateReminderConfig();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(sharedPreferences.getString(b(), ""));
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        if (jSONObject != null) {
            installActivateReminderConfigA.parseJson(jSONObject);
        }
        a((InstallActivateReminderConfigItem) installActivateReminderConfigA);
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(b())) == null) {
            a((InstallActivateReminderConfigItem) c());
            return;
        }
        InstallActivateReminderConfig installActivateReminderConfig = new InstallActivateReminderConfig();
        installActivateReminderConfig.parseJson(jSONObjectOptJSONObject);
        a((InstallActivateReminderConfigItem) installActivateReminderConfig);
    }
}
