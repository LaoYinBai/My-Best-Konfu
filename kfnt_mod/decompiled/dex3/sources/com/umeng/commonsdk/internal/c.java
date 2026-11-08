package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.UMConfigureImpl;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.common.ULog;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements UMLogDataProtocol {
    public static final String a = "umeng_sp_zdata";
    public static final String b = "umeng_zdata";
    public static final String c = "umeng_zdata_req_ts";
    private static final String e = "info";
    private static final String f = "stat";
    private static Class<?> g = null;
    private static Method h = null;
    private static Method i = null;
    private static final String j = "com.umeng.umzid.Spy";
    private Context d;

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j2) {
        return null;
    }

    static {
        b();
    }

    private static void b() {
        try {
            Class<?> cls = Class.forName(j);
            if (cls != null) {
                g = cls;
                Method declaredMethod = g.getDeclaredMethod("getID", new Class[0]);
                if (declaredMethod != null) {
                    h = declaredMethod;
                }
                Method declaredMethod2 = g.getDeclaredMethod("getVersion", new Class[0]);
                if (declaredMethod2 != null) {
                    i = declaredMethod2;
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static String c() {
        if (g == null || h == null) {
            return "";
        }
        try {
            return (String) h.invoke(g, new Object[0]);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a() {
        if (g == null || i == null) {
            return "";
        }
        try {
            return (String) i.invoke(g, new Object[0]);
        } catch (Throwable unused) {
            return "";
        }
    }

    public c(Context context) {
        if (context != null) {
            this.d = context.getApplicationContext();
        }
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i2) throws Throwable {
        ULog.i("walle", "[internal] workEvent");
        try {
            switch (i2) {
                case a.e /* 32769 */:
                    ULog.i("walle", "[internal] workEvent send envelope");
                    Class<?> cls = Class.forName("com.umeng.commonsdk.internal.UMInternalManagerAgent");
                    if (cls != null) {
                        cls.getMethod("sendInternalEnvelopeByStateful2", Context.class).invoke(cls, this.d);
                        break;
                    }
                    break;
                case a.g /* 32771 */:
                    ULog.i("walle", "[internal] workEvent cache battery, event is " + obj.toString());
                    Class<?> cls2 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (cls2 != null) {
                        cls2.getMethod("saveBattery", Context.class, String.class).invoke(cls2, this.d, (String) obj);
                        break;
                    }
                    break;
                case a.h /* 32772 */:
                    ULog.i("walle", "[internal] workEvent cache station, event is " + obj.toString());
                    Class<?> cls3 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (cls3 != null) {
                        cls3.getMethod("saveBaseStationStrength", Context.class, String.class).invoke(cls3, this.d, (String) obj);
                        break;
                    }
                    break;
                case a.j /* 32774 */:
                    Class<?> cls4 = Class.forName("com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent");
                    if (cls4 != null) {
                        cls4.getMethod("wifiChange", Context.class).invoke(cls4, this.d);
                        break;
                    }
                    break;
                case a.k /* 32775 */:
                    Class<?> cls5 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (cls5 != null) {
                        cls5.getMethod("saveUA", Context.class, String.class).invoke(cls5, this.d, (String) obj);
                        break;
                    }
                    break;
                case a.l /* 32776 */:
                    SharedPreferences sharedPreferences = this.d.getApplicationContext().getSharedPreferences("info", 0);
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putString(f, (String) obj).commit();
                        break;
                    }
                    break;
                case a.m /* 32777 */:
                    ULog.i("walle", "[internal] workEvent send envelope");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(o.aA, a.d);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(o.ao, new JSONObject());
                    JSONObject jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.d, jSONObject, jSONObject2);
                    if (jSONObjectBuildEnvelopeWithExtHeader != null && !jSONObjectBuildEnvelopeWithExtHeader.has(com.umeng.commonsdk.framework.c.c)) {
                        ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                        break;
                    }
                    break;
                case a.n /* 32778 */:
                    if (this.d != null) {
                        SharedPreferences sharedPreferences2 = this.d.getSharedPreferences(a, 0);
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        String strC = c();
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        if (!TextUtils.isEmpty(strC) && sharedPreferences2 != null) {
                            SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
                            editorEdit.putString(b, strC);
                            editorEdit.putLong(c, jCurrentTimeMillis2 - jCurrentTimeMillis);
                            editorEdit.commit();
                        }
                        if (UMConfigure.sShouldCollectZData) {
                            UMConfigureImpl.removeInterruptFlag();
                            break;
                        }
                    }
                    break;
            }
        } catch (Throwable unused) {
        }
    }
}
