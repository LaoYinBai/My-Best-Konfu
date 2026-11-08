package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.appsflyer.j;
import com.baidu.mobads.sdk.internal.ae;
import com.bytedance.msdk.api.AdError;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class m extends AsyncTask<String, Void, String> {

    /* renamed from: ʽ, reason: contains not printable characters */
    private WeakReference<Context> f237;

    /* renamed from: ˋ, reason: contains not printable characters */
    private boolean f239;

    /* renamed from: ˏ, reason: contains not printable characters */
    Map<String, String> f241;

    /* renamed from: ॱ, reason: contains not printable characters */
    String f242;

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private URL f243;

    /* renamed from: ᐝ, reason: contains not printable characters */
    private HttpURLConnection f244;

    /* renamed from: ˎ, reason: contains not printable characters */
    private String f240 = "";

    /* renamed from: ˊ, reason: contains not printable characters */
    private boolean f238 = false;

    /* renamed from: ʻ, reason: contains not printable characters */
    private boolean f235 = true;

    /* renamed from: ʼ, reason: contains not printable characters */
    private boolean f236 = true;

    @Override // android.os.AsyncTask
    protected final void onCancelled() {
    }

    m(Context context, boolean z) {
        this.f239 = false;
        this.f237 = new WeakReference<>(context);
        this.f239 = z;
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        if (this.f242 == null) {
            this.f242 = new JSONObject(this.f241).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: ˊ, reason: contains not printable characters and merged with bridge method [inline-methods] */
    public final String doInBackground(String... strArr) {
        if (this.f239) {
            return null;
        }
        try {
            this.f243 = new URL(strArr[0]);
            if (this.f235) {
                r.m212().m225(this.f243.toString(), this.f242);
                int length = this.f242.getBytes("UTF-8").length;
                StringBuilder sb = new StringBuilder("call = ");
                sb.append(this.f243);
                sb.append("; size = ");
                sb.append(length);
                sb.append(" byte");
                sb.append(length > 1 ? "s" : "");
                sb.append("; body = ");
                sb.append(this.f242);
                j.AnonymousClass2.m182(sb.toString());
            }
            this.f244 = (HttpURLConnection) this.f243.openConnection();
            this.f244.setReadTimeout(AdError.ERROR_CODE_THIRD_SDK_INIT_FAIL);
            this.f244.setConnectTimeout(AdError.ERROR_CODE_THIRD_SDK_INIT_FAIL);
            this.f244.setRequestMethod("POST");
            this.f244.setDoInput(true);
            this.f244.setDoOutput(true);
            this.f244.setRequestProperty("Content-Type", ae.d);
            OutputStream outputStream = this.f244.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(this.f242);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            this.f244.connect();
            int responseCode = this.f244.getResponseCode();
            if (this.f236) {
                AppsFlyerLib.getInstance();
                this.f240 = AppsFlyerLib.m68(this.f244);
            }
            if (this.f235) {
                r.m212().m222(this.f243.toString(), responseCode, this.f240);
            }
            if (responseCode == 200) {
                AFLogger.afInfoLog("Status 200 ok");
                Context context = this.f237.get();
                if (this.f243.toString().startsWith(ServerConfigHandler.getUrl(AppsFlyerLib.f81)) && context != null) {
                    SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
                    editorEdit.putBoolean("sentRegisterRequestToAF", true);
                    editorEdit.apply();
                    AFLogger.afDebugLog("Successfully registered for Uninstall Tracking");
                }
            } else {
                this.f238 = true;
            }
        } catch (Throwable th) {
            StringBuilder sb2 = new StringBuilder("Error while calling ");
            sb2.append(this.f243.toString());
            AFLogger.afErrorLog(sb2.toString(), th);
            this.f238 = true;
        }
        return this.f240;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: ˋ, reason: contains not printable characters and merged with bridge method [inline-methods] */
    public final void onPostExecute(String str) {
        if (this.f238) {
            AFLogger.afInfoLog("Connection error: ".concat(String.valueOf(str)));
        } else {
            AFLogger.afInfoLog("Connection call succeeded: ".concat(String.valueOf(str)));
        }
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    final void m189() {
        this.f235 = false;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final HttpURLConnection m190() {
        return this.f244;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final void m187() {
        this.f236 = false;
    }
}
