package com.kwad.sdk.api.loader;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.core.IKsAdSDK;
import com.kwad.sdk.api.loader.SecurityChecker;
import com.kwad.sdk.api.loader.a;
import com.kwad.sdk.api.loader.e;
import com.kwad.sdk.api.loader.h;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class k {

    private static abstract class a<T> implements c<T> {
        c a;

        a(c cVar) {
            this.a = cVar;
        }

        @Override // com.kwad.sdk.api.loader.k.c
        public void a(Throwable th) {
            this.a.a(th);
        }
    }

    private static class b implements f<a.C0023a> {
        private String a;
        private IKsAdSDK b;

        b(String str, IKsAdSDK iKsAdSDK) {
            this.a = str;
            this.b = iKsAdSDK;
        }

        @Override // com.kwad.sdk.api.loader.k.f
        public void a(final c<a.C0023a> cVar) {
            try {
                new com.kwad.sdk.api.loader.e(this.a, this.b).a(new e.a() { // from class: com.kwad.sdk.api.loader.k.b.1
                    @Override // com.kwad.sdk.api.loader.e.a
                    public void a(a.b bVar) {
                        if (bVar == null || !bVar.a()) {
                            cVar.a((Throwable) new RuntimeException("UpdateData is illegal"));
                        } else {
                            cVar.a((c) bVar.c);
                        }
                    }

                    @Override // com.kwad.sdk.api.loader.e.a
                    public void a(Exception exc) {
                        cVar.a((Throwable) exc);
                    }
                });
            } catch (Exception e) {
                cVar.a(e);
            }
        }
    }

    public interface c<T> {
        void a(T t);

        void a(Throwable th);
    }

    private static class d implements f<a.C0023a> {
        f<a.C0023a> a;
        Context b;

        d(f<a.C0023a> fVar, Context context) {
            this.a = fVar;
            this.b = context;
        }

        @Override // com.kwad.sdk.api.loader.k.f
        public void a(final c<a.C0023a> cVar) {
            this.a.a(new a<a.C0023a>(cVar) { // from class: com.kwad.sdk.api.loader.k.d.1
                @Override // com.kwad.sdk.api.loader.k.c
                public void a(final a.C0023a c0023a) {
                    final File fileA = com.kwad.sdk.api.loader.g.a(d.this.b, c0023a.e);
                    final IKsAdSDK ksAdSDKImpl = Loader.get().getKsAdSDKImpl();
                    if (ksAdSDKImpl != null) {
                        ksAdSDKImpl.reportBatchEvent(60, null);
                    }
                    final long jCurrentTimeMillis = System.currentTimeMillis();
                    com.kwad.sdk.api.loader.h.a(c0023a.b, fileA.getPath(), new h.a() { // from class: com.kwad.sdk.api.loader.k.d.1.1
                        @Override // com.kwad.sdk.api.loader.h.a
                        public void a() {
                            try {
                                com.kwad.sdk.api.loader.g.a(fileA);
                            } catch (Exception unused) {
                            }
                            if (ksAdSDKImpl != null) {
                                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                                HashMap map = new HashMap();
                                map.put(ReportAction.KEY_DOWNLOAD_DURATION, Long.valueOf(jCurrentTimeMillis2));
                                ksAdSDKImpl.reportBatchEvent(62, map);
                            }
                            cVar.a((Throwable) new RuntimeException("Download failed."));
                        }

                        @Override // com.kwad.sdk.api.loader.h.a
                        public void a(File file) {
                            c0023a.f = file;
                            cVar.a((c) c0023a);
                            if (ksAdSDKImpl != null) {
                                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                                HashMap map = new HashMap();
                                map.put(ReportAction.KEY_DOWNLOAD_DURATION, Long.valueOf(jCurrentTimeMillis2));
                                ksAdSDKImpl.reportBatchEvent(61, map);
                            }
                        }
                    });
                }
            });
        }
    }

    private static class e implements f<Boolean> {
        f<a.C0023a> a;
        Context b;

        e(f<a.C0023a> fVar, Context context) {
            this.a = fVar;
            this.b = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(@NonNull Context context) {
            return context.getApplicationContext().getPackageName().equals(b(context));
        }

        private String b(@NonNull Context context) {
            int iMyPid = Process.myPid();
            String str = "";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(PushConstants.INTENT_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    str = runningAppProcessInfo.processName;
                }
            }
            return str;
        }

        @Override // com.kwad.sdk.api.loader.k.f
        public void a(final c<Boolean> cVar) {
            this.a.a(new c<a.C0023a>() { // from class: com.kwad.sdk.api.loader.k.e.1
                @Override // com.kwad.sdk.api.loader.k.c
                public void a(a.C0023a c0023a) {
                    try {
                        if (!e.this.a(e.this.b)) {
                            cVar.a((Throwable) new RuntimeException("not main process"));
                            return;
                        }
                        IKsAdSDK ksAdSDKImpl = Loader.get().getKsAdSDKImpl();
                        if (ksAdSDKImpl != null) {
                            ksAdSDKImpl.reportBatchEvent(63, null);
                        }
                        if (!com.kwad.sdk.api.loader.b.a(e.this.b, c0023a.f.getPath(), c0023a.e)) {
                            throw new RuntimeException("Apk pre install failed");
                        }
                        com.kwad.sdk.api.loader.f.a(e.this.b, com.kwad.sdk.api.loader.f.a, c0023a.e);
                        cVar.a((c) true);
                        com.kwad.sdk.api.loader.g.a(c0023a.f);
                        if (ksAdSDKImpl != null) {
                            ksAdSDKImpl.reportBatchEvent(64, null);
                        }
                    } catch (Exception e) {
                        cVar.a((Throwable) e);
                    }
                }

                @Override // com.kwad.sdk.api.loader.k.c
                public void a(Throwable th) {
                    cVar.a(th);
                }
            });
        }
    }

    public interface f<T> {
        void a(c<T> cVar);
    }

    private static class g implements f<a.C0023a> {
        f<a.C0023a> a;
        Context b;

        g(f<a.C0023a> fVar, Context context) {
            this.a = fVar;
            this.b = context;
        }

        @Override // com.kwad.sdk.api.loader.k.f
        public void a(final c<a.C0023a> cVar) {
            this.a.a(new a<a.C0023a>(cVar) { // from class: com.kwad.sdk.api.loader.k.g.1
                @Override // com.kwad.sdk.api.loader.k.c
                public void a(final a.C0023a c0023a) {
                    if (c0023a == null || c0023a.f == null) {
                        return;
                    }
                    SecurityChecker.a(c0023a.f, c0023a, new SecurityChecker.a() { // from class: com.kwad.sdk.api.loader.k.g.1.1
                        @Override // com.kwad.sdk.api.loader.SecurityChecker.a
                        public void a(Exception exc) {
                            cVar.a((Throwable) exc);
                        }

                        @Override // com.kwad.sdk.api.loader.SecurityChecker.a
                        public void a(boolean z, SecurityChecker.State state) {
                            if (z) {
                                cVar.a((c) c0023a);
                                return;
                            }
                            com.kwad.sdk.api.loader.g.a(c0023a.f);
                            cVar.a((Throwable) new RuntimeException("Security check failed. state = " + state));
                        }
                    });
                }
            });
        }
    }

    private static class h implements f<a.C0023a> {
        f<a.C0023a> a;
        Context b;

        h(f<a.C0023a> fVar, Context context) {
            this.a = fVar;
            this.b = context;
        }

        @Override // com.kwad.sdk.api.loader.k.f
        public void a(final c<a.C0023a> cVar) {
            this.a.a(new c<a.C0023a>() { // from class: com.kwad.sdk.api.loader.k.h.1
                @Override // com.kwad.sdk.api.loader.k.c
                public void a(a.C0023a c0023a) {
                    c cVar2;
                    RuntimeException runtimeException;
                    if (c0023a == null) {
                        return;
                    }
                    q.a(h.this.b, "interval", c0023a.d);
                    q.a(h.this.b, "lastUpdateTime", System.currentTimeMillis());
                    if (c0023a.b()) {
                        String strA = Loader.get().a(h.this.b);
                        com.kwad.sdk.api.loader.f.a(h.this.b, com.kwad.sdk.api.loader.f.b, "");
                        cVar2 = cVar;
                        runtimeException = new RuntimeException("DynamicType == -1, curVersion: " + strA);
                    } else {
                        if (com.kwad.sdk.api.loader.f.a(c0023a.e, Loader.get().a(h.this.b)) && c0023a.a()) {
                            cVar.a((c) c0023a);
                            Log.w("maple", "new Verson: " + c0023a.e);
                            return;
                        }
                        cVar2 = cVar;
                        runtimeException = new RuntimeException("No new sdkVersion. remote sdkVersion:" + c0023a.e + " currentDynamicVersion:" + Loader.get().a(h.this.b) + " dynamicType:" + c0023a.a);
                    }
                    cVar2.a((Throwable) runtimeException);
                }

                @Override // com.kwad.sdk.api.loader.k.c
                public void a(Throwable th) {
                    cVar.a(th);
                }
            });
        }
    }

    static f<Boolean> a(String str, IKsAdSDK iKsAdSDK) {
        Context context = Loader.get().getContext();
        return new e(new g(new d(new h(new b(str, iKsAdSDK), context), context), context), context);
    }
}
