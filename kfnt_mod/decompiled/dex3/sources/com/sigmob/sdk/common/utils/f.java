package com.sigmob.sdk.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.l;
import com.sigmob.sdk.common.utils.m;
import java.util.Calendar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f {
    private com.sigmob.sdk.common.utils.a a;
    private final Context b;
    private a c;
    private boolean d;
    private boolean e;
    private c f;

    public interface a {
        void a(com.sigmob.sdk.common.utils.a aVar, com.sigmob.sdk.common.utils.a aVar2);
    }

    private class b extends AsyncTask<Void, Void, Void> {
        private b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            f.this.c();
            f.this.d = false;
            return null;
        }
    }

    interface c {
        void a();
    }

    public f(Context context, a aVar) {
        m.a.a(context);
        this.b = context;
        this.c = aVar;
        this.a = a(this.b);
        if (this.a == null) {
            this.a = com.sigmob.sdk.common.utils.a.a();
        }
        b();
    }

    private static synchronized com.sigmob.sdk.common.utils.a a(Context context) {
        m.a.a(context);
        Calendar calendar = Calendar.getInstance();
        try {
            SharedPreferences sharedPreferencesA = p.a(context, "com.Sigmob.settings.identifier");
            String string = sharedPreferencesA.getString("privacy.identifier.ifa", "");
            String string2 = sharedPreferencesA.getString("privacy.identifier.Sigmob", "");
            long j = sharedPreferencesA.getLong("privacy.identifier.time", calendar.getTimeInMillis());
            boolean z = sharedPreferencesA.getBoolean("privacy.limit.ad.tracking", false);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                return new com.sigmob.sdk.common.utils.a(string, string2, z, j);
            }
        } catch (Throwable unused) {
            SigmobLog.e("Cannot read identifier from shared preferences");
        }
        return null;
    }

    private static synchronized void a(Context context, com.sigmob.sdk.common.utils.a aVar) {
        m.a.a(context);
        m.a.a(aVar);
        SharedPreferences.Editor editorEdit = p.a(context, "com.Sigmob.settings.identifier").edit();
        editorEdit.putBoolean("privacy.limit.ad.tracking", aVar.d);
        editorEdit.putString("privacy.identifier.ifa", aVar.b);
        editorEdit.putString("privacy.identifier.Sigmob", aVar.c);
        editorEdit.putLong("privacy.identifier.time", aVar.a.getTimeInMillis());
        editorEdit.apply();
    }

    private void a(com.sigmob.sdk.common.utils.a aVar) {
        com.sigmob.sdk.common.utils.a aVar2 = this.a;
        this.a = aVar;
        a(this.b, this.a);
        if (!this.a.equals(aVar2) || !this.e) {
            a(aVar2, this.a);
        }
        if (this.e) {
            return;
        }
        d();
    }

    private void a(com.sigmob.sdk.common.utils.a aVar, com.sigmob.sdk.common.utils.a aVar2) {
        m.a.a(aVar2);
        if (this.c != null) {
            this.c.a(aVar, aVar2);
        }
    }

    private void a(String str, String str2, boolean z, long j) {
        m.a.a(str);
        m.a.a(str2);
        a(new com.sigmob.sdk.common.utils.a(str, str2, z, j));
    }

    private void b() {
        if (this.d) {
            return;
        }
        this.d = true;
        new b().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        l.b bVarA;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (e()) {
            try {
                bVarA = l.a(this.b);
            } catch (Throwable unused) {
                bVarA = null;
            }
            if (bVarA != null) {
                com.sigmob.sdk.common.utils.a aVar = this.a;
                if (bVarA.a && aVar.c()) {
                    a(bVarA.b, com.sigmob.sdk.common.utils.a.b(), bVarA.a, timeInMillis);
                } else {
                    a(bVarA.b, aVar.c, bVarA.a, aVar.a.getTimeInMillis());
                }
            }
        }
    }

    private void d() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        this.e = true;
    }

    private boolean e() {
        return true;
    }

    public com.sigmob.sdk.common.utils.a a() {
        com.sigmob.sdk.common.utils.a aVar = this.a;
        b();
        return aVar;
    }
}
