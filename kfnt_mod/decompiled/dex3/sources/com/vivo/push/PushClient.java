package com.vivo.push;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.text.TextUtils;
import com.vivo.push.d;
import com.vivo.push.util.VivoPushException;
import com.vivo.push.util.y;
import java.util.ArrayList;
import java.util.List;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushClient {
    public static final String DEFAULT_REQUEST_ID = "1";
    private static volatile PushClient sPushClient;

    public String getVersion() {
        return "2.9.0.0";
    }

    private PushClient(Context context) {
        d.a().a(context);
    }

    public static synchronized PushClient getInstance(Context context) {
        if (sPushClient == null) {
            sPushClient = new PushClient(context.getApplicationContext());
        }
        return sPushClient;
    }

    public void initialize() {
        d.a().a(new com.vivo.push.b.f());
    }

    public void checkManifest() throws PackageManager.NameNotFoundException, VivoPushException {
        d dVarA = d.a();
        if (dVarA.e != null) {
            y.c(dVarA.e);
        }
    }

    private void checkParam(String str) {
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException("PushManager String param should not be " + str);
    }

    public void bindAlias(String str, IPushActionListener iPushActionListener) {
        checkParam(str);
        d dVarA = d.a();
        if (dVarA.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(dVarA.i) && dVarA.i.equals(str)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        com.vivo.push.b.a aVar = new com.vivo.push.b.a(true, dVarA.e.getPackageName(), arrayList);
        aVar.h = 100;
        if (dVarA.j) {
            if (!dVarA.f()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!d.a(dVarA.c)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            dVarA.c = SystemClock.elapsedRealtime();
            String strA = dVarA.a(new d.a(aVar, iPushActionListener));
            aVar.e = strA;
            if (TextUtils.isEmpty(dVarA.h)) {
                dVarA.a(strA, 30001);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                dVarA.a(strA, 30002);
                return;
            } else if (str.length() > 70) {
                dVarA.a(strA, 30003);
                return;
            } else {
                dVarA.a(aVar);
                dVarA.c(strA);
                return;
            }
        }
        dVarA.a(aVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    public void unBindAlias(String str, IPushActionListener iPushActionListener) {
        checkParam(str);
        d dVarA = d.a();
        if (dVarA.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(dVarA.i)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        com.vivo.push.b.a aVar = new com.vivo.push.b.a(false, dVarA.e.getPackageName(), arrayList);
        aVar.h = 100;
        if (dVarA.j) {
            if (!dVarA.f()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!d.a(dVarA.d)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            dVarA.d = SystemClock.elapsedRealtime();
            String strA = dVarA.a(new d.a(aVar, iPushActionListener));
            aVar.e = strA;
            if (TextUtils.isEmpty(dVarA.h)) {
                dVarA.a(strA, 30001);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                dVarA.a(strA, 30002);
                return;
            } else if (str.length() > 70) {
                dVarA.a(strA, 30003);
                return;
            } else {
                dVarA.a(aVar);
                dVarA.c(strA);
                return;
            }
        }
        dVarA.a(aVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void turnOnPush(com.vivo.push.IPushActionListener r11) {
        /*
            r10 = this;
            com.vivo.push.d r0 = com.vivo.push.d.a()
            android.content.Context r1 = r0.e
            r2 = 102(0x66, float:1.43E-43)
            if (r1 != 0) goto L10
            if (r11 == 0) goto Lf
            r11.onStateChanged(r2)
        Lf:
            return
        L10:
            java.lang.String r1 = r0.e()
            r0.h = r1
            java.lang.String r1 = r0.h
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r3 = 0
            if (r1 != 0) goto L25
            if (r11 == 0) goto L24
            r11.onStateChanged(r3)
        L24:
            return
        L25:
            long r4 = r0.a
            boolean r1 = com.vivo.push.d.a(r4)
            if (r1 != 0) goto L35
            if (r11 == 0) goto L34
            r0 = 1002(0x3ea, float:1.404E-42)
            r11.onStateChanged(r0)
        L34:
            return
        L35:
            long r4 = android.os.SystemClock.elapsedRealtime()
            r0.a = r4
            android.content.Context r1 = r0.e
            java.lang.String r1 = r1.getPackageName()
            android.content.Context r4 = r0.e
            r5 = 0
            if (r4 != 0) goto L4d
            if (r11 == 0) goto Lcb
            r11.onStateChanged(r2)
            goto Lcb
        L4d:
            com.vivo.push.b.b r2 = new com.vivo.push.b.b
            r4 = 1
            r2.<init>(r4, r1)
            r2.i = r5
            r2.d = r5
            r2.c = r5
            r1 = 100
            r2.h = r1
            boolean r1 = r0.j
            if (r1 == 0) goto L74
            boolean r1 = r0.f()
            if (r1 != 0) goto L6f
            if (r11 == 0) goto Lcb
            r1 = 101(0x65, float:1.42E-43)
            r11.onStateChanged(r1)
            goto Lcb
        L6f:
            com.vivo.push.d$a r5 = r0.a(r2, r11)
            goto Lcb
        L74:
            android.content.Context r1 = r0.e
            int r6 = r2.g
            r7 = 2
            r8 = -1
            if (r6 != r8) goto Lba
            java.lang.String r6 = r2.f
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 == 0) goto L9b
            java.lang.String r6 = "BaseAppCommand"
            java.lang.String r9 = "pkg name is null"
            com.vivo.push.util.n.a(r6, r9)
            java.lang.String r6 = r2.b
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 == 0) goto L9b
            java.lang.String r1 = "BaseAppCommand"
            java.lang.String r4 = "src is null"
            com.vivo.push.util.n.a(r1, r4)
            goto Lbc
        L9b:
            java.lang.String r9 = "com.vivo.pushservice.action.RECEIVE"
            boolean r9 = com.vivo.push.util.r.a(r1, r6, r9)
            if (r9 == 0) goto La4
            r8 = 0
        La4:
            java.lang.String r9 = "com.vivo.pushclient.action.RECEIVE"
            boolean r1 = com.vivo.push.util.r.a(r1, r6, r9)
            if (r1 == 0) goto Lad
            goto Lae
        Lad:
            r4 = r8
        Lae:
            r2.g = r4
            java.lang.String r1 = r2.i
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto Lba
            r2.g = r7
        Lba:
            int r8 = r2.g
        Lbc:
            if (r8 != r7) goto Lc3
            com.vivo.push.d$a r5 = r0.a(r2, r11)
            goto Lcb
        Lc3:
            r0.a(r2)
            if (r11 == 0) goto Lcb
            r11.onStateChanged(r3)
        Lcb:
            if (r5 == 0) goto Ld7
            com.vivo.push.d$1 r11 = new com.vivo.push.d$1
            r11.<init>()
            r5.a = r11
            r5.a()
        Ld7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.PushClient.turnOnPush(com.vivo.push.IPushActionListener):void");
    }

    public void turnOffPush(IPushActionListener iPushActionListener) {
        final d dVarA = d.a();
        if (dVarA.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        if ("".equals(dVarA.h)) {
            iPushActionListener.onStateChanged(0);
            return;
        }
        if (!d.a(dVarA.b)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
                return;
            }
            return;
        }
        dVarA.b = SystemClock.elapsedRealtime();
        String packageName = dVarA.e.getPackageName();
        d.a aVar = null;
        if (dVarA.e != null) {
            final com.vivo.push.b.b bVar = new com.vivo.push.b.b(false, packageName);
            bVar.d = null;
            bVar.c = null;
            bVar.i = null;
            bVar.h = 100;
            if (dVarA.j) {
                if (dVarA.f()) {
                    aVar = new d.a(bVar, iPushActionListener);
                    final String strA = dVarA.a(aVar);
                    bVar.e = strA;
                    aVar.b = new Runnable() { // from class: com.vivo.push.d.4
                        final /* synthetic */ com.vivo.push.b.b a;
                        final /* synthetic */ String b;

                        AnonymousClass4(final com.vivo.push.b.b bVar2, final String strA2) {
                            bVar = bVar2;
                            str = strA2;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            d.this.a(bVar);
                            d.this.c(str);
                        }
                    };
                } else if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
            } else {
                dVarA.a(bVar2);
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(0);
                }
            }
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(102);
        }
        if (aVar != null) {
            aVar.a = new IPushActionListener() { // from class: com.vivo.push.d.3
                AnonymousClass3() {
                }

                @Override // com.vivo.push.IPushActionListener
                public final void onStateChanged(int i) {
                    if (i == 0) {
                        d.this.h = "";
                        d.this.g.a("APP_TOKEN", "");
                        d.this.d();
                        d.this.g.c("APP_TAGS");
                        return;
                    }
                    d.this.h = null;
                    d.this.g.c("APP_TOKEN");
                }
            };
            aVar.a();
        }
    }

    public String getAlias() {
        return d.a().i;
    }

    public String getRegId() {
        return d.a().e();
    }

    public void setTopic(String str, IPushActionListener iPushActionListener) {
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(str);
        d.a().a(arrayList, iPushActionListener);
    }

    public void delTopic(String str, IPushActionListener iPushActionListener) {
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(str);
        d.a().b(arrayList, iPushActionListener);
    }

    public List<String> getTopics() {
        return d.a().b();
    }

    public void setSystemModel(boolean z) {
        d.a().f = z;
    }

    public boolean isSupport() {
        return d.a().c();
    }
}
