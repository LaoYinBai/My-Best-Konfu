package com.ss.sys.ck;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Keep;
import c.a.a.a.a.at;
import com.ss.sys.ck.a;
import com.ss.union.game.sdk.core.R;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SCCheckUtils {
    private static volatile SCCheckUtils sCkInstance;
    public Activity activity;
    public Context ctx;
    private e dlg;
    private SCCheckListener listener;
    private float percent;
    private String url;
    protected int versionCode;
    protected int appID = 0;
    protected String appName = "";
    protected String iid = "";
    protected String deviceID = "";
    protected String channel = "";
    protected String language = "";
    protected String session = "";
    protected int challenge_code = 0;
    private HashMap<String, String> maps = new HashMap<>();

    class a implements DialogInterface.OnCancelListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            SCCheckUtils.this.dlgDismiss();
            SCCheckListener listener = SCCheckUtils.this.getListener();
            if (listener != null) {
                listener.dialogOnClose(1);
                listener.dialogOnResult(false, "");
            }
        }
    }

    class b implements DialogInterface.OnKeyListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getRepeatCount() != 0) {
                return false;
            }
            SCCheckUtils.this.dlgDismiss();
            SCCheckListener listener = SCCheckUtils.this.getListener();
            if (listener != null) {
                listener.dialogOnClose(2);
                listener.dialogOnResult(false, "");
            }
            return true;
        }
    }

    class c implements Runnable {

        class a implements a.d {
            a(c cVar) {
            }

            @Override // com.ss.sys.ck.a.d
            public void a() {
                SCCheckUtils.getInstance().reloadUrl();
            }

            @Override // com.ss.sys.ck.a.d
            public void b() {
                SCCheckUtils.getInstance().dlgDismiss();
            }
        }

        class b implements DialogInterface.OnKeyListener {
            final /* synthetic */ com.ss.sys.ck.a a;

            b(com.ss.sys.ck.a aVar) {
                this.a = aVar;
            }

            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4 || keyEvent.getRepeatCount() != 0) {
                    return false;
                }
                this.a.dismiss();
                SCCheckUtils.this.dlgDismiss();
                return true;
            }
        }

        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String string;
            String string2;
            String string3;
            try {
                if (SCCheckUtils.this.activity.isFinishing()) {
                    return;
                }
                try {
                    string = SCCheckUtils.this.ctx.getResources().getString(R.string.btn_text_message);
                } catch (Throwable unused) {
                    string = "NETWORK ERROR, TRY LATTER";
                }
                try {
                    string2 = SCCheckUtils.this.ctx.getResources().getString(R.string.btn_text_continue);
                } catch (Throwable unused2) {
                    string2 = "CONTINUE";
                }
                try {
                    string3 = SCCheckUtils.this.ctx.getResources().getString(R.string.btn_text_cancel);
                } catch (Throwable unused3) {
                    string3 = "CONTINUE";
                }
                com.ss.sys.ck.a aVar = new com.ss.sys.ck.a(SCCheckUtils.getInstance().activity, string, string2, string3);
                aVar.a(new a(this));
                aVar.setOnKeyListener(new b(aVar));
                aVar.show();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    static class d implements Runnable {
        final /* synthetic */ JSONObject a;

        d(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                SCCheckUtils.getInstance().dlgDismiss();
                boolean z = this.a.getInt("result") == 0;
                SCCheckListener listener = SCCheckUtils.getInstance().getListener();
                if (listener != null) {
                    listener.dialogOnClose(0);
                    listener.dialogOnResult(z, "");
                }
            } catch (Throwable unused) {
            }
        }
    }

    private SCCheckUtils(Context context, String str, int i, String str2, String str3, String str4, String str5) {
        this.versionCode = 0;
        this.ctx = context.getApplicationContext();
        RegisterJSBridge();
        try {
            updateHttpParams(str, i, str2, str3, str4, str5);
            this.versionCode = this.ctx.getPackageManager().getPackageInfo(this.ctx.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void RegisterJSBridge() {
        com.ss.sys.ck.c.a("TTCK", (Class<?>) SCCheckUtils.class);
    }

    public static void closeDialog(WebView webView, JSONObject jSONObject, com.ss.sys.ck.d dVar) {
        try {
            getInstance().activity.runOnUiThread(new d(jSONObject));
        } catch (Throwable unused) {
        }
    }

    public static SCCheckUtils getInstance() {
        return sCkInstance;
    }

    public static SCCheckUtils getInstance(Context context, String str, int i, String str2, String str3, String str4, String str5) {
        synchronized (SCCheckUtils.class) {
            if (sCkInstance == null) {
                at.b(i);
                sCkInstance = new SCCheckUtils(context, str, i, str2, str3, str4, str5);
                com.ss.sys.ck.b.c();
                com.ss.sys.ck.b.d();
            }
        }
        return sCkInstance;
    }

    public static SCCheckUtils getInstance(Context context, String str, int i, String str2, String str3, String str4, String str5, int i2) {
        synchronized (SCCheckUtils.class) {
            if (sCkInstance == null) {
                at.a(i2);
                at.b(i);
                sCkInstance = new SCCheckUtils(context, str, i, str2, str3, str4, str5);
                com.ss.sys.ck.b.c();
                com.ss.sys.ck.b.d();
            }
        }
        return sCkInstance;
    }

    protected void dlgDismiss() {
        e eVar = this.dlg;
        if (eVar != null) {
            eVar.dismiss();
            this.dlg = null;
        }
    }

    public String getAid() {
        try {
            return "" + getInstance().appID;
        } catch (Throwable unused) {
            return "";
        }
    }

    public String getChannel() {
        try {
            return getInstance().channel;
        } catch (Throwable unused) {
            return "";
        }
    }

    public String getDid() {
        try {
            return getInstance().deviceID;
        } catch (Throwable unused) {
            return "";
        }
    }

    public String getIid() {
        try {
            return getInstance().iid;
        } catch (Throwable unused) {
            return "";
        }
    }

    protected SCCheckListener getListener() {
        return this.listener;
    }

    public String getSession() {
        try {
            return getInstance().session;
        } catch (Throwable unused) {
            return "";
        }
    }

    public String getUid() {
        return "";
    }

    protected void netWorkError() {
        try {
            if (this.activity != null) {
                this.activity.runOnUiThread(new c());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void popupCheckCode(Activity activity, String str, int i, SCCheckListener sCCheckListener) {
        this.activity = activity;
        this.listener = sCCheckListener;
        this.challenge_code = i;
        e eVar = this.dlg;
        if ((eVar == null || !eVar.isShowing()) && !activity.isFinishing()) {
            dlgDismiss();
            e eVar2 = new e(activity, 0, 0, activity.getLayoutInflater().inflate(R.layout.sec_dlg, (ViewGroup) null), R.style.DialogTheme, this.url + "&challenge_code=" + i, str);
            this.dlg = eVar2;
            eVar2.a(i);
            this.dlg.show();
            this.dlg.setOnCancelListener(new a());
            this.dlg.setOnKeyListener(new b());
        }
    }

    protected void reloadUrl() {
        SCWebView sCWebView;
        String str = this.url + "&challenge_code=" + this.challenge_code;
        e eVar = this.dlg;
        if (eVar == null || (sCWebView = eVar.a) == null) {
            return;
        }
        sCWebView.loadUrl(str);
    }

    public void setDialogTouch(boolean z) {
        e eVar = this.dlg;
        if (eVar != null) {
            eVar.setCanceledOnTouchOutside(z);
        }
    }

    public boolean updateHttpParams(String str, int i, String str2, String str3, String str4, String str5) {
        this.language = str;
        if (str == null || TextUtils.isEmpty(str)) {
            String language = Locale.getDefault().getLanguage();
            this.language = language;
            if (language == null) {
                language = "zh-cn";
            }
            this.language = language;
            if (language.equalsIgnoreCase("zh")) {
                try {
                    this.language = Locale.getDefault().getCountry().equalsIgnoreCase("tw") ? "zh-tw" : "zh-cn";
                } catch (Throwable unused) {
                    this.language = "zh-cn";
                }
            }
        }
        this.appID = i;
        this.iid = str3;
        if (str2 != null && !TextUtils.isEmpty(str2)) {
            this.appName = str2;
        }
        if (str4 != null && !TextUtils.isEmpty(str4)) {
            this.deviceID = str4;
        }
        if (str5 != null && !TextUtils.isEmpty(str5)) {
            this.channel = str5;
        }
        this.url = com.ss.sys.ck.b.b() + "?aid=" + this.appID + "&lang=" + this.language + "&app_name=" + this.appName + "&iid=" + this.iid + "&vc=" + this.versionCode + "&did=" + this.deviceID + "&ch=" + this.channel + "&os=0";
        return true;
    }
}
