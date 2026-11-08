package b.b.a.j;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends c {

    /* renamed from: a, reason: collision with root package name */
    public final AccountManager f1103a;

    /* renamed from: b, reason: collision with root package name */
    public Account f1104b;

    /* renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap<String, String> f1105c = new ConcurrentHashMap<>();

    /* renamed from: b.b.a.j.a$a, reason: collision with other inner class name */
    public class RunnableC0024a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Account f1106a;

        public RunnableC0024a(Account account) {
            this.f1106a = account;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a.this.f1105c != null && a.this.f1105c.size() > 0 && a.this.f1103a != null) {
                    for (Map.Entry<String, String> entry : a.this.f1105c.entrySet()) {
                        if (entry != null) {
                            a.this.f1103a.setUserData(this.f1106a, entry.getKey(), entry.getValue());
                        }
                    }
                    a.this.f1105c.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public a(Context context) {
        this.f1103a = AccountManager.get(context);
    }

    @Override // b.b.a.j.c
    public String a(String str) {
        Account account = this.f1104b;
        if (account == null) {
            return this.f1105c.get(str);
        }
        try {
            return this.f1103a.getUserData(account, str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // b.b.a.j.c
    public void a(String str, String str2) {
        Account account = this.f1104b;
        if (account == null) {
            this.f1105c.put(str, str2);
            return;
        }
        if (str == null || str2 == null) {
            return;
        }
        try {
            this.f1103a.setUserData(account, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // b.b.a.j.c
    public String[] b(String str) {
        String strA = a(str);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return strA.split("\n");
    }

    @Override // b.b.a.j.c
    public void a(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return;
        }
        a(str, TextUtils.join("\n", strArr));
    }

    public void a(Account account) {
        if (account != null) {
            this.f1104b = account;
            ConcurrentHashMap<String, String> concurrentHashMap = this.f1105c;
            if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
                return;
            }
            this.e.post(new RunnableC0024a(account));
        }
    }

    @Override // b.b.a.j.c
    @SuppressLint({"MissingPermission"})
    public void c(String str) {
        ConcurrentHashMap<String, String> concurrentHashMap = this.f1105c;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(str)) {
            this.f1105c.remove(str);
        }
        try {
            if (this.f1104b != null && this.f1103a != null) {
                this.f1103a.setUserData(this.f1104b, str, null);
            }
        } catch (Exception unused) {
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.c(str);
        }
    }
}
