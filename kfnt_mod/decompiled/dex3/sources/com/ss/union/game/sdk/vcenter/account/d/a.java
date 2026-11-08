package com.ss.union.game.sdk.vcenter.account.d;

import android.os.RemoteException;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.account.b.a;
import com.ss.union.game.sdk.vcenter.account.callback.b;
import com.ss.union.game.sdk.vcenter.c;
import com.ss.union.game.sdk.vcenter.d;
import com.ss.union.game.sdk.vcenter.g;
import com.ss.union.game.sdk.vcenter.j;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements com.ss.union.game.sdk.vcenter.account.b.a {
    private d a;

    private a() {
        this.a = d.a();
    }

    public static a a() {
        return C0105a.a;
    }

    @Override // com.ss.union.game.sdk.vcenter.account.b.a
    public void a(final a.InterfaceC0103a interfaceC0103a) {
        if (!c.a().b()) {
            interfaceC0103a.a(false);
        } else {
            this.a.a((j) new j.a() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.1
                @Override // com.ss.union.game.sdk.vcenter.j
                public void a(String str) throws RemoteException {
                    try {
                        VLog.Account.d("onFetchSuccess: " + str);
                        String strOptString = new JSONObject(str).optString("accountType", "");
                        if (TextUtils.isEmpty(strOptString)) {
                            VLog.Account.d("invalid accountType: " + str);
                            return;
                        }
                        if (TextUtils.equals(strOptString, "none")) {
                            interfaceC0103a.a(false);
                        } else if (TextUtils.equals(strOptString, com.ss.union.game.sdk.account.a.a.y)) {
                            interfaceC0103a.a(false);
                        } else {
                            interfaceC0103a.a(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override // com.ss.union.game.sdk.vcenter.account.b.a
    public void a(final b bVar) {
        this.a.a((j) new j.a() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.2
            @Override // com.ss.union.game.sdk.vcenter.j
            public void a(String str) throws RemoteException {
                bVar.a(com.ss.union.game.sdk.vcenter.b.b.a(str));
            }
        });
    }

    @Override // com.ss.union.game.sdk.vcenter.account.b.a
    public void a(String str, final com.ss.union.game.sdk.vcenter.account.a.b bVar) {
        this.a.a(str, new com.ss.union.game.sdk.vcenter.account.c.b(new com.ss.union.game.sdk.vcenter.account.a.b() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.3
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(final int i, final User user) {
                if (bVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            bVar.a(i, user);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(final int i, final int i2, final String str2, final String str3) {
                if (bVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            bVar.a(i, i2, str2, str3);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.d
            public void a(final g gVar) {
                if (bVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.3.3
                        @Override // java.lang.Runnable
                        public void run() {
                            bVar.a(gVar);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.d
            public void b(final g gVar) {
                if (bVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.3.4
                        @Override // java.lang.Runnable
                        public void run() {
                            bVar.b(gVar);
                        }
                    });
                }
            }
        }));
    }

    @Override // com.ss.union.game.sdk.vcenter.account.b.a
    public void a(String str, final com.ss.union.game.sdk.vcenter.account.a.a aVar) {
        this.a.a(str, new com.ss.union.game.sdk.vcenter.account.c.c(new com.ss.union.game.sdk.vcenter.account.a.a() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.4
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(final int i, final User user) {
                if (aVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            aVar.a(i, user);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(final int i, final int i2, final String str2, final String str3) {
                if (aVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            aVar.a(i, i2, str2, str3);
                        }
                    });
                }
            }
        }));
    }

    @Override // com.ss.union.game.sdk.vcenter.account.b.a
    public void b(String str, final com.ss.union.game.sdk.vcenter.account.a.a aVar) {
        this.a.a(str, new com.ss.union.game.sdk.vcenter.account.c.a(new com.ss.union.game.sdk.vcenter.account.a.a() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.5
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(final int i, final User user) {
                if (aVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            aVar.a(i, user);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(final int i, final int i2, final String str2, final String str3) {
                if (aVar != null) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.d.a.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            aVar.a(i, i2, str2, str3);
                        }
                    });
                }
            }
        }));
    }

    /* renamed from: com.ss.union.game.sdk.vcenter.account.d.a$a, reason: collision with other inner class name */
    private static class C0105a {
        private static final a a = new a();

        private C0105a() {
        }
    }
}
