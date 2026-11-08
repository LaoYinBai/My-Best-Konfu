package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements c {

    /* renamed from: b, reason: collision with root package name */
    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.a f1433b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1434c;
    private b d;
    private Context f;
    private e g;
    private int r;

    /* renamed from: a, reason: collision with root package name */
    private long f1432a = 0;
    private boolean e = false;
    private int h = 0;
    private long i = 19700101000L;
    private int j = 0;
    private HashMap<String, Integer> k = new HashMap<>();
    private HashMap<String, Integer> l = new HashMap<>();
    private int m = 0;
    private HashMap<String, Integer> n = new HashMap<>();
    private HashMap<String, Integer> o = new HashMap<>();
    private boolean p = true;
    private Map<String, Integer> q = new HashMap();
    Handler s = new a(Looper.getMainLooper());

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 10000) {
                return;
            }
            f.this.b(message.arg1 != 0);
        }
    }

    private f() {
    }

    private boolean b(int i) {
        return i >= 200 && i < 400;
    }

    private void i() {
        SharedPreferences sharedPreferences = this.f.getSharedPreferences(f(), 0);
        this.h = sharedPreferences.getInt("tnc_probe_cmd", 0);
        this.i = sharedPreferences.getLong("tnc_probe_version", 19700101000L);
    }

    private void j() {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "resetTNCControlState");
        this.j = 0;
        this.k.clear();
        this.l.clear();
        this.m = 0;
        this.n.clear();
        this.o.clear();
    }

    public b b() {
        return this.d;
    }

    public d c() {
        e eVar = this.g;
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    public e d() {
        return this.g;
    }

    public Map<String, String> e() {
        d dVarC = c();
        if (dVarC != null) {
            return dVarC.d;
        }
        return null;
    }

    public String f() {
        return "ttnet_tnc_config" + this.r;
    }

    public boolean g() {
        return this.f1434c;
    }

    public void h() {
        this.q.clear();
    }

    private void b(String str) {
        Map<String, String> mapE;
        if (TextUtils.isEmpty(str) || (mapE = e()) == null || !mapE.containsValue(str)) {
            return;
        }
        if (this.q.get(str) == null) {
            this.q.put(str, 1);
        } else {
            this.q.put(str, Integer.valueOf(this.q.get(str).intValue() + 1));
        }
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str) && this.q.containsKey(str)) {
            this.q.put(str, 0);
        }
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.a a() {
        return this.f1433b;
    }

    private boolean c(String str) {
        Map<String, String> mapE = e();
        if (mapE == null) {
            return false;
        }
        String str2 = mapE.get(str);
        if (TextUtils.isEmpty(str2) || this.q.get(str2) == null || this.q.get(str2).intValue() < 3) {
            return false;
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "handleHostMapping, TNC host faild num over limit: " + str);
        return true;
    }

    public void a(boolean z) {
        this.f1434c = z;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public synchronized void a(Context context, boolean z) {
        if (!this.e) {
            this.f = context;
            this.p = z;
            this.g = new e(context, z, this.r);
            if (z) {
                i();
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "initTnc, isMainProc: " + z + " probeCmd: " + this.h + " probeVersion: " + this.i);
            this.f1433b = h.a().a(this.r, this.f);
            this.e = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (c() == null) {
            return;
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doUpdateRemote, " + z);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (!z && this.f1432a + (r0.k * 1000) > jElapsedRealtime) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doUpdateRemote, time limit");
        } else {
            this.f1432a = jElapsedRealtime;
            h.a().a(this.r, this.f).a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.e.a(this.f));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L101
            java.lang.String r0 = "/network/get_network"
            boolean r0 = r6.contains(r0)
            if (r0 != 0) goto L101
            java.lang.String r0 = "/get_domains/v4"
            boolean r0 = r6.contains(r0)
            if (r0 != 0) goto L101
            java.lang.String r0 = "/ies/speed"
            boolean r0 = r6.contains(r0)
            if (r0 == 0) goto L20
            goto L101
        L20:
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L32
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L32
            java.lang.String r2 = r1.getProtocol()     // Catch: java.lang.Throwable -> L32
            java.lang.String r1 = r1.getHost()     // Catch: java.lang.Throwable -> L30
            r0 = r1
            goto L37
        L30:
            r1 = move-exception
            goto L34
        L32:
            r1 = move-exception
            r2 = r0
        L34:
            r1.printStackTrace()
        L37:
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L101
            java.lang.String r1 = "http"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L4d
            java.lang.String r1 = "https"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L101
        L4d:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L55
            goto L101
        L55:
            boolean r1 = r5.c(r0)
            if (r1 == 0) goto L72
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "handleHostMapping, TNC host faild num over limit: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "TNCManager"
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a(r1, r0)
            return r6
        L72:
            java.util.Map r1 = r5.e()
            if (r1 == 0) goto Leb
            boolean r3 = r1.containsKey(r0)
            if (r3 != 0) goto L7f
            goto Leb
        L7f:
            java.lang.Object r1 = r1.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L8c
            return r6
        L8c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "handleHostMapping, match, origin: "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "TNCManager"
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a(r4, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r4 = "://"
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "://"
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            boolean r2 = r6.startsWith(r0)
            if (r2 == 0) goto Ld4
            java.lang.String r6 = r6.replaceFirst(r0, r1)
        Ld4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "handleHostMapping, target: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TNCManager"
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a(r1, r0)
            return r6
        Leb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "handleHostMapping, nomatch: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "TNCManager"
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a(r1, r0)
        L101:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.f.a(java.lang.String):java.lang.String");
    }

    public synchronized void a(y yVar, a0 a0Var) {
        if (yVar == null || a0Var == null) {
            return;
        }
        if (this.p) {
            if (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.e.a(this.f)) {
                URL urlO = null;
                try {
                    urlO = yVar.g().o();
                } catch (Exception unused) {
                }
                if (urlO == null) {
                    return;
                }
                String protocol = urlO.getProtocol();
                String host = urlO.getHost();
                String path = urlO.getPath();
                String strA = a(yVar);
                int iK = a0Var.k();
                if (HttpConstant.HTTP.equals(protocol) || "https".equals(protocol)) {
                    if (TextUtils.isEmpty(strA)) {
                        return;
                    }
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "onResponse, url: " + protocol + HttpConstant.SCHEME_SPLIT + host + "#" + strA + "#" + iK);
                    d dVarC = c();
                    if (dVarC != null && dVarC.f1427b) {
                        a(a0Var, host);
                    }
                    if (dVarC == null) {
                        return;
                    }
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "onResponse, url matched: " + protocol + HttpConstant.SCHEME_SPLIT + host + "#" + strA + "#" + iK + " " + this.j + "#" + this.k.size() + "#" + this.l.size() + " " + this.m + "#" + this.n.size() + "#" + this.o.size());
                    if (iK > 0) {
                        if (b(iK)) {
                            if (this.j > 0 || this.m > 0) {
                                j();
                            }
                            d(host);
                        } else if (!a(iK)) {
                            this.m++;
                            this.n.put(path, 0);
                            this.o.put(strA, 0);
                            if (this.m >= dVarC.h && this.n.size() >= dVarC.i && this.o.size() >= dVarC.j) {
                                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "onResponse, url doUpdate: " + protocol + HttpConstant.SCHEME_SPLIT + host + "#" + strA + "#" + iK);
                                a(false, 0L);
                                j();
                            }
                            b(host);
                        }
                    }
                }
            }
        }
    }

    private String a(y yVar) {
        if (yVar == null || yVar.g() == null || yVar.g().o() == null) {
            return "";
        }
        try {
            return InetAddress.getByName(yVar.g().o().getHost()).getHostAddress();
        } catch (Exception unused) {
            return "";
        }
    }

    public synchronized void a(y yVar, Exception exc) {
        if (yVar != null) {
            if (yVar.g() != null && exc != null) {
                if (this.p) {
                    if (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.e.a(this.f)) {
                        URL urlO = null;
                        try {
                            urlO = yVar.g().o();
                        } catch (Exception unused) {
                        }
                        if (urlO == null) {
                            return;
                        }
                        String protocol = urlO.getProtocol();
                        String host = urlO.getHost();
                        String path = urlO.getPath();
                        String strA = a(yVar);
                        if (HttpConstant.HTTP.equals(protocol) || "https".equals(protocol)) {
                            d dVarC = c();
                            if (dVarC == null) {
                                return;
                            }
                            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "onError, url matched: " + protocol + HttpConstant.SCHEME_SPLIT + host + "#" + strA + "# " + this.j + "#" + this.k.size() + "#" + this.l.size() + " " + this.m + "#" + this.n.size() + "#" + this.o.size());
                            this.j = this.j + 1;
                            this.k.put(path, 0);
                            this.l.put(strA, 0);
                            if (this.j >= dVarC.e && this.k.size() >= dVarC.f && this.l.size() >= dVarC.g) {
                                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "onError, url doUpate: " + protocol + HttpConstant.SCHEME_SPLIT + host + "#" + strA);
                                a(false, 0L);
                                j();
                            }
                            b(host);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0 r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.f.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0, java.lang.String):void");
    }

    private void a(boolean z, long j) {
        if (this.s.hasMessages(NetCallback.ERROR_CODE_REQUEST_SUCCESS)) {
            return;
        }
        Message messageObtainMessage = this.s.obtainMessage();
        messageObtainMessage.what = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        messageObtainMessage.arg1 = z ? 1 : 0;
        if (j > 0) {
            this.s.sendMessageDelayed(messageObtainMessage, j);
        } else {
            this.s.sendMessage(messageObtainMessage);
        }
    }

    private boolean a(int i) {
        if (i < 100 || i >= 1000) {
            return true;
        }
        d dVarC = c();
        if (dVarC == null || TextUtils.isEmpty(dVarC.m)) {
            return false;
        }
        String str = dVarC.m;
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(i);
        return str.contains(sb.toString());
    }

    public f(int i) {
        this.r = i;
    }
}
