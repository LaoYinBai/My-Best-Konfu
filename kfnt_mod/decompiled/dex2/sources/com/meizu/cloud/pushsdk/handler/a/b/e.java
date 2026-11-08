package com.meizu.cloud.pushsdk.handler.a.b;

import android.text.TextUtils;
import com.heytap.mcssdk.mode.CommandMessage;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private int a;
    private String b = String.valueOf(-1);
    private String c = "";
    private String d = "";
    private int e = -1;
    private String f = "";

    public static class a {
        public String a;
        public String b;
        String c;

        public a(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(CommandMessage.CODE)) {
                    a(jSONObject.getString(CommandMessage.CODE));
                }
                if (!jSONObject.isNull("message")) {
                    b(jSONObject.getString("message"));
                }
                if (jSONObject.isNull("value")) {
                    return;
                }
                c(jSONObject.getString("value"));
            } catch (JSONException e) {
                DebugLogger.e("SecurityMessage", "covert json error " + e.getMessage());
            }
        }

        public String a() {
            return this.c;
        }

        public void a(String str) {
            this.a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }

        public String toString() {
            return "PublicKeyStatus{code='" + this.a + "', message='" + this.b + "', publicKey='" + this.c + "'}";
        }
    }

    public static String a(MessageV3 messageV3) throws JSONException {
        String string;
        JSONObject jSONObject;
        String str;
        String notificationMessage = messageV3.getNotificationMessage();
        String string2 = null;
        try {
            try {
            } catch (Throwable th) {
                if (TextUtils.isEmpty(null)) {
                    try {
                        new JSONObject(notificationMessage).getString("se");
                    } catch (Exception unused) {
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
        }
        if (TextUtils.isEmpty(notificationMessage)) {
            string = string2;
        } else {
            try {
                JSONObject jSONObject2 = new JSONObject(notificationMessage).getJSONObject("data");
                if (!jSONObject2.isNull(PushConstants.EXTRA)) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject(PushConstants.EXTRA);
                    if (!jSONObject3.isNull("se")) {
                        string2 = jSONObject3.getString("se");
                    }
                }
            } catch (JSONException e) {
                DebugLogger.e("SecurityMessage", "parse notification message error " + e.getMessage());
                if (TextUtils.isEmpty(null)) {
                    jSONObject = new JSONObject(notificationMessage);
                    str = "se";
                }
            }
            if (TextUtils.isEmpty(string2)) {
                jSONObject = new JSONObject(notificationMessage);
                str = "se";
                string = jSONObject.getString(str);
            }
            string = string2;
        }
        DebugLogger.i("SecurityMessage", "encrypt message " + string);
        return string;
    }

    public static boolean a(String str, MessageV3 messageV3) {
        String str2;
        String str3;
        e eVarE = e(str);
        DebugLogger.e("SecurityMessage", "securityMessage " + eVarE);
        if (System.currentTimeMillis() / 1000 > eVarE.a()) {
            str2 = "SecurityMessage";
            str3 = "message expire";
        } else if (!messageV3.getTitle().contains(eVarE.c())) {
            str2 = "SecurityMessage";
            str3 = "invalid title";
        } else if (!messageV3.getContent().contains(eVarE.d())) {
            str2 = "SecurityMessage";
            str3 = "invalid content";
        } else if (!String.valueOf(-1).equals(eVarE.b()) && !eVarE.b().equals(messageV3.getTaskId())) {
            str2 = "SecurityMessage";
            str3 = "invalid taskId";
        } else if (eVarE.e() != -1) {
            switch (eVarE.e()) {
                case 1:
                    if (!messageV3.getActivity().contains(eVarE.f())) {
                        str2 = "SecurityMessage";
                        str3 = "invalid click activity";
                        break;
                    } else {
                        return true;
                    }
                case 2:
                    if (!messageV3.getWebUrl().contains(eVarE.f())) {
                        str2 = "SecurityMessage";
                        str3 = "invalid web url";
                        break;
                    } else {
                        return true;
                    }
                case 3:
                    if (!MzPushMessage.fromMessageV3(messageV3).getSelfDefineContentString().contains(eVarE.f())) {
                        str2 = "SecurityMessage";
                        str3 = "invalid self define";
                        break;
                    } else {
                        return true;
                    }
                default:
                    return true;
            }
        } else {
            str2 = "SecurityMessage";
            str3 = "invalid click type";
        }
        DebugLogger.e(str2, str3);
        return false;
    }

    private static e e(String str) {
        e eVar = new e();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull("tt")) {
                eVar.a(jSONObject.getInt("tt"));
            }
            if (!jSONObject.isNull("ti")) {
                eVar.a(jSONObject.getString("ti"));
            }
            if (!jSONObject.isNull("tl")) {
                eVar.b(jSONObject.getString("tl"));
            }
            if (!jSONObject.isNull("cont")) {
                eVar.c(jSONObject.getString("cont"));
            }
            if (!jSONObject.isNull("ct")) {
                eVar.b(jSONObject.getInt("ct"));
            }
            if (!jSONObject.isNull("pm")) {
                eVar.d(jSONObject.getString("pm"));
            }
        } catch (Exception e) {
            DebugLogger.e("SecurityMessage", "parse decryptSign error " + e.getMessage());
        }
        return eVar;
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public void b(int i) {
        this.e = i;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.f = str;
    }

    public int e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String toString() {
        return "SecurityMessage{timestamp=" + this.a + ", taskId='" + this.b + "', title='" + this.c + "', content='" + this.d + "', clickType=" + this.e + ", params='" + this.f + "'}";
    }
}
