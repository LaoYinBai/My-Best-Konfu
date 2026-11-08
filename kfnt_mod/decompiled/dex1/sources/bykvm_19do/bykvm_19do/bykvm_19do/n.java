package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class n extends d2 {
    n() {
        super(true, false);
    }

    private boolean b() {
        return (!TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.contains("Flyme")) || com.bytedance.b.a.ag.equals(Build.USER);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String c() throws IOException {
        String str;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.build.version.emui").getInputStream()), 1024);
            try {
                String line = bufferedReader2.readLine();
                try {
                    bufferedReader2.close();
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused) {
                    }
                    return line;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = line;
                    str = bufferedReader;
                    bufferedReader = bufferedReader2;
                    try {
                        l0.a(th);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return str;
                    } catch (Throwable th2) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            str = 0;
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException, IOException {
        StringBuilder sb = new StringBuilder(16);
        if (a()) {
            sb.append("MIUI-");
        } else if (b()) {
            sb.append("FLYME-");
        } else {
            String strC = c();
            if (i.a(strC)) {
                sb.append("EMUI-");
            }
            if (!TextUtils.isEmpty(strC)) {
                sb.append(strC);
                sb.append("-");
            }
        }
        sb.append(Build.VERSION.INCREMENTAL);
        jSONObject.put("rom", sb.toString());
        return true;
    }

    private boolean a() {
        try {
            return Class.forName("miui.os.Build").getName().length() > 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
