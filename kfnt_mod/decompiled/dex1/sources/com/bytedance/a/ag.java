package com.bytedance.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.bytedance.applog.AppLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ag implements Handler.Callback {

    /* renamed from: a, reason: collision with root package name */
    public dg f3341a;

    /* renamed from: b, reason: collision with root package name */
    public Handler f3342b;

    /* renamed from: c, reason: collision with root package name */
    public Map<String, a> f3343c;
    public Set<String> d;
    public String e = "";

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public long f3344a;

        /* renamed from: b, reason: collision with root package name */
        public String f3345b;

        /* renamed from: c, reason: collision with root package name */
        public JSONObject f3346c;

        public a(long j, String str, JSONObject jSONObject) {
            this.f3344a = j;
            this.f3345b = str;
            this.f3346c = jSONObject;
        }

        public String toString() {
            StringBuilder sbA = com.bytedance.a.a.a("ProfileDataWrapper{timeStamp=");
            sbA.append(this.f3344a);
            sbA.append(", apiName='");
            sbA.append(this.f3345b);
            sbA.append('\'');
            sbA.append(", jsonObject=");
            sbA.append(this.f3346c);
            sbA.append('}');
            return sbA.toString();
        }
    }

    public ag(dg dgVar) {
        this.f3341a = dgVar;
        HandlerThread handlerThread = new HandlerThread("bd_tracker_profile");
        handlerThread.start();
        this.f3342b = new Handler(handlerThread.getLooper(), this);
        this.f3343c = new HashMap();
        this.d = new HashSet();
    }

    public final void a(int i, a aVar) {
        if (AppLog.isPrivacyMode()) {
            return;
        }
        Handler handler = this.f3342b;
        handler.sendMessage(handler.obtainMessage(i, aVar));
    }

    public final void a(a aVar) throws JSONException {
        if (this.f3341a == null) {
            return;
        }
        StringBuilder sbA = com.bytedance.a.a.a("__profile_");
        sbA.append(aVar.f3345b);
        dn dnVar = new dn(sbA.toString(), aVar.f3346c.toString());
        ArrayList<bu> arrayList = new ArrayList<>();
        this.f3341a.n.b(dnVar);
        this.f3341a.b(dnVar);
        arrayList.add(dnVar);
        this.f3341a.b().b(arrayList);
        this.f3342b.sendMessageDelayed(this.f3342b.obtainMessage(106), 500L);
    }

    public void a(JSONObject jSONObject) {
        a(100, new a(System.currentTimeMillis(), "set", jSONObject));
    }

    public void b(JSONObject jSONObject) {
        a(102, new a(System.currentTimeMillis(), "set_once", jSONObject));
    }

    public void c(JSONObject jSONObject) {
        a(103, new a(System.currentTimeMillis(), "increment", jSONObject));
    }

    public void d(JSONObject jSONObject) {
        a(104, new a(System.currentTimeMillis(), "unset", jSONObject));
    }

    public void e(JSONObject jSONObject) {
        a(105, new a(System.currentTimeMillis(), "append", jSONObject));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) throws JSONException {
        switch (message.what) {
            case 100:
                a aVar = (a) message.obj;
                String str = this.e;
                boolean zEquals = str != null ? str.equals(AppLog.getSsid()) : false;
                this.e = AppLog.getSsid();
                Iterator<String> itKeys = aVar.f3346c.keys();
                boolean z = false;
                boolean z2 = true;
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!this.f3343c.containsKey(next) || this.f3343c.get(next) == null) {
                        z = true;
                    } else {
                        a aVar2 = this.f3343c.get(next);
                        if (System.currentTimeMillis() - aVar2.f3344a >= 60000) {
                            z = true;
                        }
                        try {
                            if (!m.a(aVar.f3346c, aVar2.f3346c, (String) null)) {
                            }
                        } catch (Exception e) {
                            de.a("", e);
                        }
                        this.f3343c.put(next, aVar);
                    }
                    z2 = false;
                    this.f3343c.put(next, aVar);
                }
                if (zEquals && !z && z2) {
                    Log.i("ProfileController", "handlerSet: not report:" + zEquals + ",overOneMin:" + z + ",:sameValue:" + z2);
                } else {
                    a(aVar);
                }
                return true;
            case 101:
            default:
                return true;
            case 102:
                a aVar3 = (a) message.obj;
                String str2 = this.e;
                boolean zEquals2 = str2 != null ? str2.equals(AppLog.getSsid()) : false;
                this.e = AppLog.getSsid();
                Iterator<String> itKeys2 = aVar3.f3346c.keys();
                boolean z3 = true;
                while (itKeys2.hasNext()) {
                    String next2 = itKeys2.next();
                    if (!this.d.contains(next2)) {
                        z3 = false;
                    }
                    this.d.add(next2);
                }
                if (zEquals2 && z3) {
                    Log.i("ProfileController", "handlerSet: not report isSameSsid:" + zEquals2 + ",hasSend:" + z3);
                } else {
                    a(aVar3);
                }
                return true;
            case 103:
                a((a) message.obj);
                return true;
            case 104:
                a((a) message.obj);
                return true;
            case 105:
                a((a) message.obj);
                return true;
            case 106:
                dg dgVar = this.f3341a;
                if (dgVar != null && dgVar.i.i() != 0) {
                    ArrayList<bu> arrayListA = this.f3341a.b().a();
                    if (!arrayListA.isEmpty()) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("magic_tag", "ss_app_log");
                            jSONObject.put("header", AppLog.getHeader());
                            jSONObject.put("time_sync", bf.f3399b);
                            jSONObject.put("local_time", System.currentTimeMillis() / 1000);
                            JSONArray jSONArray = new JSONArray();
                            Iterator<bu> it = arrayListA.iterator();
                            while (it.hasNext()) {
                                jSONArray.put(it.next().f());
                            }
                            jSONObject.put("event_v3", jSONArray);
                            byte[] bArrA = m.a(jSONObject.toString());
                            this.f3341a.b().a(arrayListA);
                            if (bf.a(new String[]{this.f3341a.c().getProfileUri()}, bArrA, this.f3341a.e) != 200) {
                                this.f3341a.b().b(arrayListA);
                            }
                        } catch (JSONException e2) {
                            de.a("", e2);
                        }
                    }
                }
                return true;
        }
    }
}
