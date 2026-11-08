package com.bytedance.applog.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.util.f;
import com.baidu.mobads.sdk.internal.bh;
import com.bytedance.a.bf;
import com.bytedance.a.ch;
import com.bytedance.a.de;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.IPicker;
import com.ss.union.game.sdk.core.base.R;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SimulateLaunchActivity extends AppCompatActivity {
    public static final String KEY_QR_PARAM = "qr_param";

    /* renamed from: a, reason: collision with root package name */
    public b f3568a = b.QR;

    /* renamed from: b, reason: collision with root package name */
    public String f3569b;

    /* renamed from: c, reason: collision with root package name */
    public int f3570c;
    public int d;
    public String e;
    public String f;
    public String g;
    public String h;
    public TextView i;
    public c j;

    public enum b {
        QR,
        NO_QR
    }

    public class c extends AsyncTask<Void, Void, JSONObject> {
        public /* synthetic */ c(a aVar) {
        }

        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Void[] voidArr) {
            return SimulateLaunchActivity.this.f3568a == b.QR ? bf.a(SimulateLaunchActivity.this.f3569b, SimulateLaunchActivity.this.f, SimulateLaunchActivity.this.f3570c, SimulateLaunchActivity.this.d, SimulateLaunchActivity.this.g, SimulateLaunchActivity.this.e) : bf.a(this, SimulateLaunchActivity.this.f3569b, SimulateLaunchActivity.this.f, SimulateLaunchActivity.this.f3570c, SimulateLaunchActivity.this.d, SimulateLaunchActivity.this.g);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) throws NoSuchMethodException, SecurityException {
            JSONObject jSONObjectOptJSONObject;
            int iIndexOf;
            JSONObject jSONObject2 = jSONObject;
            if (jSONObject2 == null) {
                SimulateLaunchActivity.this.i.setText("启动失败,请按电脑提示检查原因然后重新扫码(response is null)");
                return;
            }
            String strOptString = jSONObject2.optString("message");
            String strOptString2 = jSONObject2.optString(HttpConstant.SET_COOKIE);
            int iOptInt = jSONObject2.optInt("status");
            if (strOptString2 != null && (iIndexOf = strOptString2.indexOf(f.f2657b)) >= 0) {
                strOptString2 = strOptString2.substring(0, iIndexOf);
            }
            if (SimulateLaunchActivity.this.f3568a == b.NO_QR && (jSONObjectOptJSONObject = jSONObject2.optJSONObject("data")) != null) {
                SimulateLaunchActivity.this.h = jSONObjectOptJSONObject.optString("mode", "").equals("log") ? "debug_log" : "bind_query";
            }
            IPicker picker = null;
            if ("debug_log".equals(SimulateLaunchActivity.this.h) && iOptInt == 0 && !TextUtils.isEmpty(strOptString2)) {
                AppLog.setRangersEventVerifyEnable(true, strOptString2);
                Intent launchIntentForPackage = SimulateLaunchActivity.this.getPackageManager().getLaunchIntentForPackage(SimulateLaunchActivity.this.getApplicationInfo().packageName);
                if (launchIntentForPackage == null) {
                    return;
                }
                launchIntentForPackage.setPackage(null);
                SimulateLaunchActivity.this.startActivity(launchIntentForPackage);
            } else {
                if (!bh.k.equals(strOptString) || TextUtils.isEmpty(strOptString2)) {
                    TextView textView = SimulateLaunchActivity.this.i;
                    StringBuilder sbA = com.bytedance.a.a.a("启动失败,请按电脑提示检查原因然后重新扫码(");
                    sbA.append(jSONObject2.toString());
                    sbA.append(")");
                    textView.setText(sbA.toString());
                    return;
                }
                Intent launchIntentForPackage2 = SimulateLaunchActivity.this.getPackageManager().getLaunchIntentForPackage(SimulateLaunchActivity.this.getApplicationInfo().packageName);
                ch.a("bind_query".equals(SimulateLaunchActivity.this.h));
                if (launchIntentForPackage2 == null) {
                    return;
                }
                launchIntentForPackage2.setPackage(null);
                SimulateLaunchActivity.this.startActivity(launchIntentForPackage2);
                if (AppLog.getInitConfig() != null && AppLog.getInitConfig().getPicker() != null) {
                    picker = AppLog.getInitConfig().getPicker();
                }
                if (picker != null) {
                    picker.setMarqueeCookie(strOptString2);
                }
                AppLog.startSimulator(strOptString2);
            }
            SimulateLaunchActivity.this.finish();
        }
    }

    public static void startSimulatorWithoutQR(@NonNull Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) SimulateLaunchActivity.class);
        intent.putExtra("url_prefix_no_qr", str);
        context.startActivity(intent);
    }

    public final void a() {
        String str = (String) AppLog.getHeaderValue("resolution", null, String.class);
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = str.split("x");
            this.d = Integer.valueOf(strArrSplit[0]).intValue();
            this.f3570c = Integer.valueOf(strArrSplit[1]).intValue();
        }
        this.f3569b = AppLog.getAid();
        this.g = AppLog.getDid();
        try {
            this.f = getPackageManager().getPackageInfo(getApplicationInfo().packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            this.f = "1.0.0";
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        TextView textView;
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.applog_activity_simulate);
        this.i = (TextView) findViewById(R.id.text_tip);
        if (!AppLog.hasStarted()) {
            this.i.setText("启动失败,请按电脑提示检查原因然后重新扫码(AppLog未初始化)");
            return;
        }
        Intent intent = getIntent();
        Uri data = intent.getData();
        a aVar = null;
        if (intent.hasExtra("url_prefix_no_qr")) {
            this.f3568a = b.NO_QR;
            bf.f = intent.getStringExtra("url_prefix_no_qr");
            a();
            this.j = new c(aVar);
            this.j.execute(new Void[0]);
            return;
        }
        if (data != null) {
            this.f3568a = b.QR;
            if (!AppLog.getAid().equals(data.getQueryParameter("aid"))) {
                this.i.setText("启动失败,请按电脑提示检查原因然后重新扫码(aid错误)");
                return;
            }
            this.h = data.getQueryParameter("type");
            if ("debug_log".equals(this.h)) {
                String queryParameter = data.getQueryParameter("url_prefix");
                de.a("urlPrefix=" + queryParameter, null);
                if (!TextUtils.isEmpty(queryParameter)) {
                    bf.f = queryParameter;
                    this.e = data.getQueryParameter(KEY_QR_PARAM);
                    a();
                    this.j = new c(aVar);
                    this.j.execute(new Void[0]);
                    return;
                }
                textView = this.i;
                str = "启动失败,无url_prefix参数";
            } else {
                textView = this.i;
                str = "启动失败,type参数错误";
            }
            textView.setText(str);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c cVar = this.j;
        if (cVar != null) {
            cVar.cancel(true);
            this.j = null;
        }
    }
}
