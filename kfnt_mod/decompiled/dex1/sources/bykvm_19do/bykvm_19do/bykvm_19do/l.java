package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.baidu.mobads.sdk.internal.av;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class l extends d2 {
    private final Context e;

    l(Context context) {
        super(false, false);
        this.e = context;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws Throwable {
        String string;
        String string2;
        try {
            string = Settings.Secure.getString(this.e.getContentResolver(), av.f);
        } catch (Exception e) {
            l0.c("", e);
            string = null;
        }
        if (!j2.a(string) || "9774d56d682e549c".equals(string)) {
            SharedPreferences sharedPreferences = this.e.getSharedPreferences("snssdk_openudid", 0);
            string2 = sharedPreferences.getString("openudid", null);
            if (!j2.a(string2)) {
                string2 = new BigInteger(64, new SecureRandom()).toString(16);
                if (string2.charAt(0) == '-') {
                    string2 = string2.substring(1);
                }
                int length = 13 - string2.length();
                if (length > 0) {
                    StringBuilder sb = new StringBuilder();
                    while (length > 0) {
                        sb.append('F');
                        length--;
                    }
                    sb.append(string2);
                    string2 = sb.toString();
                }
                try {
                    string2 = a("openudid.dat", string2);
                } catch (Exception unused) {
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("openudid", string2);
                editorEdit.apply();
            }
        } else {
            string2 = string;
        }
        jSONObject.put("openudid", string2);
        return true;
    }
}
