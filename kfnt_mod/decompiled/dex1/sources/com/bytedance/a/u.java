package com.bytedance.a;

import android.content.SharedPreferences;
import android.os.Handler;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.alink.IALinkListener;
import com.bytedance.common.utility.date.DateDef;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0003J\u0006\u0010\u0015\u001a\u00020\bJ\u0010\u0010\u0016\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J*\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001c\u001a\u00020\u0004J*\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010 \u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0012\u0010!\u001a\u00020\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u000eH\u0007J\u000e\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/bytedance/applog/alink/ALinkManager;", "", "()V", "CALL_ACTIVITY_LAUNCHED", "", "CALL_ID_LOADED", "readClipTime", "sActivityLaunched", "", "sClipboardEnabled", "sColdStarted", "Ljava/util/concurrent/atomic/AtomicBoolean;", "sIdLoaded", "sLinkListener", "Lcom/bytedance/applog/alink/IALinkListener;", "destroy", "", "fillAttributionRequest", "Lcom/bytedance/applog/alink/model/AttributionRequest;", "deviceManager", "Lcom/bytedance/applog/manager/DeviceManager;", "getClipboardEnabled", "mergeTracerData", "onColdStart", "netHandler", "Landroid/os/Handler;", "config", "Lcom/bytedance/applog/UriConfig;", "caller", "onDeepLinked", "paramsLink", "Lorg/json/JSONObject;", "uriConfig", "setALinkListener", "aLinkListener", "setClipboardEnabled", "clipboardEnabled", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class u {

    /* renamed from: a, reason: collision with root package name */
    public static IALinkListener f3540a;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f3542c;
    public static boolean d;
    public static boolean e;
    public static int f;
    public static final u g = new u();

    /* renamed from: b, reason: collision with root package name */
    public static AtomicBoolean f3541b = new AtomicBoolean(false);

    public static final class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Handler f3543a;

        /* renamed from: b, reason: collision with root package name */
        public final /* synthetic */ UriConfig f3544b;

        /* renamed from: c, reason: collision with root package name */
        public final /* synthetic */ be f3545c;

        public a(Handler handler, UriConfig uriConfig, be beVar, Ref.ObjectRef objectRef) {
            this.f3543a = handler;
            this.f3544b = uriConfig;
            this.f3545c = beVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            u.g.a(this.f3543a, this.f3544b, this.f3545c, 1);
        }
    }

    public static final class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ae f3546a;

        public c(ae aeVar) {
            this.f3546a = aeVar;
        }

        @Override // java.lang.Runnable
        public final void run() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$link_type", "direct");
            AppLog.onEventV3("$invoke", jSONObject);
            u uVar = u.g;
            IALinkListener iALinkListener = u.f3540a;
            if (iALinkListener != null) {
                iALinkListener.onALinkData(this.f3546a.b(), null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(@org.b.a.e com.bytedance.a.be r13) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.u.a(com.bytedance.a.be):void");
    }

    @MainThread
    public final void a(@org.b.a.e IALinkListener iALinkListener) {
        f3540a = iALinkListener;
    }

    public final void a(boolean z) {
        e = z;
    }

    public final boolean a() {
        return e;
    }

    public static final class b implements bn<at> {
        @Override // com.bytedance.a.bn
        @WorkerThread
        public void a(int i, @org.b.a.d String str) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            u uVar = u.g;
            IALinkListener iALinkListener = u.f3540a;
            if (iALinkListener != null) {
                iALinkListener.onAttributionData(null, new Exception(str));
            }
        }

        @Override // com.bytedance.a.bn
        public void a(bd bdVar) throws JSONException {
            SharedPreferences.Editor editorEdit;
            SharedPreferences.Editor editorPutString;
            at atVar = (at) bdVar;
            Intrinsics.checkParameterIsNotNull(atVar, "data");
            boolean z = atVar.F;
            atVar.F = false;
            Intrinsics.checkParameterIsNotNull("deferred_deep_link", "key");
            Intrinsics.checkParameterIsNotNull(atVar, "data");
            JSONObject jSONObjectA = atVar.a();
            jSONObjectA.put("expire_ts", -1L);
            SharedPreferences sharedPreferences = p.f3536a;
            if (sharedPreferences != null && (editorEdit = sharedPreferences.edit()) != null && (editorPutString = editorEdit.putString("deferred_deep_link", jSONObjectA.toString())) != null) {
                editorPutString.apply();
            }
            if (atVar.m && z) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("$link_type", "deferred");
                AppLog.onEventV3("$invoke", jSONObject);
                u uVar = u.g;
                IALinkListener iALinkListener = u.f3540a;
                if (iALinkListener != null) {
                    iALinkListener.onAttributionData(atVar.b(), null);
                }
            }
        }
    }

    public static final class d implements bn<ae> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Ref.ObjectRef f3547a;

        /* renamed from: b, reason: collision with root package name */
        public final /* synthetic */ be f3548b;

        /* renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ae f3549c;

        public d(Ref.ObjectRef objectRef, be beVar, ae aeVar) {
            this.f3547a = objectRef;
            this.f3548b = beVar;
            this.f3549c = aeVar;
        }

        @Override // com.bytedance.a.bn
        @WorkerThread
        public void a(int i, @org.b.a.d String str) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            if (this.f3549c == null) {
                u uVar = u.g;
                IALinkListener iALinkListener = u.f3540a;
                if (iALinkListener != null) {
                    iALinkListener.onALinkData(null, new Exception(str));
                }
            }
        }

        @Override // com.bytedance.a.bn
        public void a(bd bdVar) throws JSONException {
            SharedPreferences.Editor editorEdit;
            SharedPreferences.Editor editorPutString;
            ae aeVar = (ae) bdVar;
            Intrinsics.checkParameterIsNotNull(aeVar, "data");
            String str = (String) this.f3547a.element;
            Intrinsics.checkExpressionValueIsNotNull(str, "token");
            aeVar.a(str);
            long jCurrentTimeMillis = System.currentTimeMillis() + DateDef.MONTH;
            Intrinsics.checkParameterIsNotNull("deep_link", "key");
            Intrinsics.checkParameterIsNotNull(aeVar, "data");
            JSONObject jSONObjectA = aeVar.a();
            jSONObjectA.put("expire_ts", jCurrentTimeMillis);
            SharedPreferences sharedPreferences = p.f3536a;
            if (sharedPreferences != null && (editorEdit = sharedPreferences.edit()) != null && (editorPutString = editorEdit.putString("deep_link", jSONObjectA.toString())) != null) {
                editorPutString.apply();
            }
            u.g.a(this.f3548b);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("$link_type", "direct");
            AppLog.onEventV3("$invoke", jSONObject);
            u uVar = u.g;
            IALinkListener iALinkListener = u.f3540a;
            if (iALinkListener != null) {
                iALinkListener.onALinkData(aeVar.b(), null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(@org.b.a.e android.os.Handler r9, @org.b.a.d com.bytedance.applog.UriConfig r10, @org.b.a.e com.bytedance.a.be r11, int r12) {
        /*
            Method dump skipped, instructions count: 727
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.u.a(android.os.Handler, com.bytedance.applog.UriConfig, com.bytedance.a.be, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(@org.b.a.d org.json.JSONObject r10, @org.b.a.e com.bytedance.a.be r11, @org.b.a.d com.bytedance.applog.UriConfig r12, @org.b.a.e android.os.Handler r13) {
        /*
            r9 = this;
            java.lang.String r0 = "paramsLink"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.lang.String r0 = "uriConfig"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            java.lang.String r1 = "tr_token"
            java.lang.String r1 = r10.optString(r1)
            r0.element = r1
            java.lang.String r1 = "ALink:DeepLinked,token="
            java.lang.StringBuilder r1 = com.bytedance.a.a.a(r1)
            java.lang.Object r2 = r0.element
            java.lang.String r2 = (java.lang.String) r2
            r1.append(r2)
            java.lang.String r2 = ",requesting to:"
            r1.append(r2)
            java.lang.String r2 = r12.getAlinkQueryUri()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            com.bytedance.a.de.a(r1, r2)
            java.util.concurrent.atomic.AtomicBoolean r1 = com.bytedance.a.u.f3541b
            r3 = 1
            r1.set(r3)
            java.lang.String r1 = "deep_link"
            java.lang.Class<com.bytedance.a.ae> r3 = com.bytedance.a.ae.class
            java.lang.String r4 = "key"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r4)
            java.lang.String r4 = "clazz"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r4)
            android.content.SharedPreferences r4 = com.bytedance.a.p.f3536a
            if (r4 == 0) goto L77
            java.lang.String r1 = r4.getString(r1, r2)
            if (r1 == 0) goto L77
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L77
            r4.<init>(r1)     // Catch: org.json.JSONException -> L77
            java.lang.String r1 = "expire_ts"
            r5 = 0
            long r7 = r4.optLong(r1, r5)
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 <= 0) goto L70
            long r5 = java.lang.System.currentTimeMillis()
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 < 0) goto L70
            goto L77
        L70:
            com.bytedance.a.bd$a r1 = com.bytedance.a.bd.G
            com.bytedance.a.bd r1 = r1.a(r4, r3)
            goto L78
        L77:
            r1 = r2
        L78:
            com.bytedance.a.ae r1 = (com.bytedance.a.ae) r1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "cache data = "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.bytedance.a.de.a(r3, r2)
            if (r1 == 0) goto La7
            java.lang.String r2 = r1.r
            java.lang.Object r3 = r0.element
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto La7
            if (r13 == 0) goto Le1
            com.bytedance.a.u$c r10 = new com.bytedance.a.u$c
            r10.<init>(r1)
            r13.post(r10)
            goto Le1
        La7:
            com.bytedance.a.aj r2 = new com.bytedance.a.aj
            r2.<init>()
            if (r11 == 0) goto Lcc
            java.lang.String r3 = com.bytedance.applog.AppLog.getAid()
            r2.f3352a = r3
            java.lang.String r3 = r11.f()
            r2.f3353b = r3
            java.lang.Object r3 = r0.element
            java.lang.String r3 = (java.lang.String) r3
            r2.e = r3
            java.lang.String r3 = r11.h()
            r2.f3354c = r3
            java.lang.String r3 = r11.e()
            r2.d = r3
        Lcc:
            com.bytedance.applog.AppLog.getUserUniqueID()
            r2.a(r10)
            if (r13 == 0) goto Le1
            com.bytedance.a.bx r10 = new com.bytedance.a.bx
            com.bytedance.a.u$d r3 = new com.bytedance.a.u$d
            r3.<init>(r0, r11, r1)
            r10.<init>(r12, r2, r3)
            r13.post(r10)
        Le1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.u.a(org.json.JSONObject, com.bytedance.a.be, com.bytedance.applog.UriConfig, android.os.Handler):void");
    }
}
