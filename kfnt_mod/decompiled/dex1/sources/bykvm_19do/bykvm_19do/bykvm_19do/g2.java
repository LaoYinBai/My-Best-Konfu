package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g2 extends d2 {
    private final Context e;

    g2(Context context) {
        super(false, false);
        this.e = context;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws Throwable {
        SharedPreferences sharedPreferences = this.e.getSharedPreferences("snssdk_openudid", 0);
        String string = sharedPreferences.getString("clientudid", null);
        if (!j2.a(string)) {
            try {
                String string2 = UUID.randomUUID().toString();
                try {
                    string = a("clientudid.dat", string2);
                } catch (Exception unused) {
                    string = string2;
                }
            } catch (Exception unused2) {
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("clientudid", string);
            editorEdit.apply();
        }
        jSONObject.put("clientudid", string);
        return true;
    }
}
