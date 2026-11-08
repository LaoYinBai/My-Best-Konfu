package com.mbridge.msdk.mbnative.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.entity.j;
import com.mbridge.msdk.foundation.entity.n;
import com.mbridge.msdk.foundation.same.e.a;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbnative.a.f;
import com.mbridge.msdk.out.AdMobClickListener;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.Frame;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativePreloadController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final String d = "c";
    private static Map<String, Map<Long, Object>> e = new HashMap();
    private static Map<String, Boolean> f = new HashMap();
    private static Map<String, n> g = new HashMap();
    private static Map<String, Integer> h = new HashMap();
    private static Map<String, Integer> i = new HashMap();
    private static c j = null;
    private static int w = -1;
    private static int x = -2;
    private int A;
    protected List<Integer> c;
    private com.mbridge.msdk.b.c k;
    private com.mbridge.msdk.click.b l;
    private com.mbridge.msdk.b.d m;
    private String n;
    private Handler o;
    private i r;
    private Map<String, Object> s;
    private List<Integer> t;
    private List<Integer> u;
    private boolean v;
    private boolean y;
    private int z;
    Queue<Integer> a = null;
    Queue<Long> b = null;
    private int p = 0;
    private String q = "";
    private Map<String, Boolean> C = new HashMap();
    private com.mbridge.msdk.foundation.same.e.b B = new com.mbridge.msdk.foundation.same.e.b(com.mbridge.msdk.foundation.controller.a.f().j());

    public c() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.o = new Handler() { // from class: com.mbridge.msdk.mbnative.c.c.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:83:0x021f  */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.util.Map<java.lang.String, java.lang.Object> r21, int r22, com.mbridge.msdk.out.AdMobClickListener r23) {
        /*
            Method dump skipped, instructions count: 1123
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbnative.c.c.a(java.util.Map, int, com.mbridge.msdk.out.AdMobClickListener):void");
    }

    private int a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.opt(i2);
                    if (2 == jSONObject.optInt("id", 0)) {
                        return jSONObject.optInt("ad_num");
                    }
                }
            }
        } catch (Exception e2) {
            s.d(d, com.mbridge.msdk.mbnative.b.a.a(e2));
        }
        return 0;
    }

    private void a(int i2, com.mbridge.msdk.b.d dVar, String str, String str2, com.mbridge.msdk.a.a.a aVar, AdMobClickListener adMobClickListener) {
        if (this.a == null || this.a.size() <= 0) {
            return;
        }
        try {
            int iIntValue = this.a.poll().intValue();
            long jLongValue = MBridgeConstans.REQUEST_TIME_OUT;
            if (this.b != null && this.b.size() > 0) {
                jLongValue = this.b.poll().longValue();
            }
            s.b(d, "preload start queue adsource = " + iIntValue);
            a(iIntValue, jLongValue, str, str2, this.s, i2, dVar, aVar, adMobClickListener);
        } catch (Throwable unused) {
            s.d(d, "queue poll exception");
        }
    }

    private void a(int i2, long j2, String str, String str2, Map<String, Object> map, int i3, com.mbridge.msdk.b.d dVar, com.mbridge.msdk.a.a.a aVar, AdMobClickListener adMobClickListener) throws JSONException {
        com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA;
        if (i2 != 1 && (bVarA = f.a(i2)) != null) {
            List<Campaign> listB = bVarA.b(str, this.A);
            if (listB != null && listB.size() > 0) {
                a(true, aVar, (String) null);
            }
        }
        switch (i2) {
            case 1:
                a(i2, j2, i3, str, str2, true, aVar, adMobClickListener);
                break;
            case 2:
                a(2, j2, i3, dVar, str, str2, aVar, false, adMobClickListener);
                break;
            default:
                a(i2, j2, i3, dVar, str, str2, aVar, false, adMobClickListener);
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x02cd A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02dc A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x030d A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x031d A[Catch: Exception -> 0x0524, LOOP:1: B:137:0x0317->B:139:0x031d, LOOP_END, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0335 A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0346 A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0366 A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x039e A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03f5 A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0400 A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x04d9 A[Catch: Exception -> 0x0524, TryCatch #5 {Exception -> 0x0524, blocks: (B:3:0x0010, B:5:0x005c, B:7:0x0066, B:9:0x0070, B:11:0x007a, B:13:0x0084, B:15:0x0090, B:16:0x009a, B:18:0x00a6, B:19:0x00b0, B:21:0x00bc, B:23:0x00c9, B:25:0x00cf, B:26:0x00d8, B:28:0x00f1, B:29:0x00f8, B:31:0x00fe, B:32:0x0103, B:34:0x012c, B:35:0x0131, B:42:0x014a, B:45:0x0152, B:47:0x015a, B:49:0x0160, B:51:0x0165, B:53:0x0169, B:57:0x017f, B:59:0x0187, B:61:0x018d, B:64:0x0194, B:65:0x01aa, B:67:0x01ae, B:68:0x01c6, B:70:0x01d0, B:72:0x01e0, B:73:0x01f8, B:76:0x0209, B:78:0x020f, B:80:0x0217, B:82:0x0221, B:84:0x0229, B:114:0x027a, B:115:0x0285, B:124:0x029e, B:126:0x02cd, B:127:0x02d4, B:129:0x02dc, B:131:0x02f5, B:132:0x02fe, B:134:0x030d, B:136:0x0313, B:137:0x0317, B:139:0x031d, B:140:0x032f, B:142:0x0335, B:143:0x033e, B:145:0x0346, B:147:0x0350, B:148:0x0353, B:154:0x0366, B:156:0x036e, B:157:0x0375, B:161:0x0382, B:165:0x03b6, B:167:0x03f5, B:168:0x03fa, B:170:0x0400, B:172:0x040a, B:174:0x0416, B:175:0x043a, B:177:0x0444, B:179:0x0450, B:180:0x0474, B:181:0x047b, B:187:0x04a6, B:188:0x04a9, B:190:0x04d9, B:192:0x04e3, B:197:0x0508, B:193:0x04ef, B:196:0x04ff, B:164:0x039e, B:149:0x0358, B:150:0x035d, B:91:0x0239, B:93:0x0241, B:108:0x0268, B:118:0x0292, B:198:0x0510), top: B:213:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x04fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(int r21, long r22, int r24, com.mbridge.msdk.b.d r25, java.lang.String r26, java.lang.String r27, com.mbridge.msdk.a.a.a r28, boolean r29, com.mbridge.msdk.out.AdMobClickListener r30) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 1344
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbnative.c.c.a(int, long, int, com.mbridge.msdk.b.d, java.lang.String, java.lang.String, com.mbridge.msdk.a.a.a, boolean, com.mbridge.msdk.out.AdMobClickListener):void");
    }

    public static void a(int i2, String str) {
        n nVar;
        if (g.containsKey(str)) {
            nVar = g.get(str);
        } else {
            nVar = new n();
        }
        int iIntValue = h.get(str).intValue();
        int iIntValue2 = i.containsKey(str) ? i.get(str).intValue() : 1;
        switch (i2) {
            case 1:
                int iB = nVar.b() + iIntValue;
                if (iB > iIntValue2) {
                    iB = 0;
                }
                nVar.b(iB);
                break;
            case 2:
                int iA = nVar.a() + iIntValue;
                if (iA > iIntValue2) {
                    iA = 0;
                }
                nVar.a(iA);
                break;
        }
        g.put(str, nVar);
    }

    public static void b(int i2, String str) {
        if (g.containsKey(str)) {
            n nVar = g.get(str);
            switch (i2) {
                case 1:
                    nVar.b(0);
                    break;
                case 2:
                    nVar.a(0);
                    break;
            }
            g.put(str, nVar);
        }
    }

    /* compiled from: NativePreloadController.java */
    public class a extends com.mbridge.msdk.mbnative.f.a.b implements com.mbridge.msdk.foundation.same.e.c {
        private int b;
        private int c;
        private int g;
        private int h;
        private int i;
        private AdMobClickListener j;
        private Runnable m;
        private com.mbridge.msdk.a.a.a o;
        private List<String> k = null;
        private boolean l = false;
        private boolean n = true;

        public final void a(AdMobClickListener adMobClickListener) {
            this.j = adMobClickListener;
        }

        public final void a(List<String> list) {
            this.k = list;
        }

        public final void c(int i) {
            this.i = i;
        }

        public final void d(int i) {
            this.g = i;
        }

        public final void e(int i) {
            this.h = i;
        }

        public final void f(int i) {
            this.c = i;
        }

        public a(int i) {
            this.b = i;
        }

        public final void a(com.mbridge.msdk.a.a.a aVar) {
            this.o = aVar;
        }

        public final void b(boolean z) {
            this.n = z;
        }

        @Override // com.mbridge.msdk.foundation.same.e.c
        public final void a(boolean z) {
            this.l = z;
        }

        @Override // com.mbridge.msdk.mbnative.f.a.b
        public final void a(List<com.mbridge.msdk.foundation.same.net.c.b> list, final CampaignUnit campaignUnit) {
            c.this.y = true;
            c.this.a(true, this.o, (String) null);
            c.this.a(new Thread(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.c.a.1
                @Override // java.lang.Runnable
                public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                    boolean z;
                    if (MBridgeConstans.PRELOAD_RESULT_IN_SUBTHREAD && Looper.myLooper() == null) {
                        Looper.prepare();
                        z = true;
                    } else {
                        z = false;
                    }
                    if (a.this.m != null) {
                        s.b(c.d, "REMOVE CANCEL TASK ON SUCCESS");
                        c.this.o.removeCallbacks(a.this.m);
                    }
                    s.d(c.d, "onSuccess");
                    w.a((List<CampaignEx>) campaignUnit.getAds());
                    if (c.e.containsKey("0_" + a.this.d)) {
                        c.e.remove("0_" + a.this.d);
                    }
                    if (a.this.i > 0) {
                        if (campaignUnit.ads.size() > a.this.i) {
                            a.this.c = a.this.i;
                        } else {
                            a.this.c = campaignUnit.ads.size();
                        }
                    } else if (a.this.i == -1) {
                        a.this.c = 0;
                    } else if (a.this.i == -3) {
                        a.this.c = campaignUnit.ads.size();
                    } else if (a.this.i == -2) {
                        if (campaignUnit.getTemplate() == 3) {
                            if (a.this.h != 0) {
                                a.this.c = a.this.h;
                            }
                        } else if (a.this.g != 0) {
                            a.this.c = a.this.g;
                        }
                        if (a.this.c <= 0) {
                            a.this.c = ((Integer) c.h.get(a.this.d)).intValue();
                        }
                    }
                    if (campaignUnit.ads.size() < a.this.c) {
                        a.this.c = campaignUnit.ads.size();
                    }
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    boolean z2 = false;
                    for (int i = 0; i < campaignUnit.ads.size(); i++) {
                        CampaignEx campaignEx = campaignUnit.ads.get(i);
                        campaignEx.setCampaignUnitId(a.this.d);
                        boolean zC = w.c(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx.getPackageName());
                        if (zC && com.mbridge.msdk.foundation.controller.a.e() != null) {
                            com.mbridge.msdk.foundation.controller.a.e().add(new j(campaignEx.getId(), campaignEx.getPackageName()));
                            z2 = true;
                        }
                        if (arrayList.size() < a.this.c && campaignEx.getOfferType() != 99) {
                            if (w.b(campaignEx)) {
                                campaignEx.setRtinsType(zC ? 1 : 2);
                            }
                            if (campaignEx.getWtick() == 1 || !zC) {
                                arrayList.add(campaignEx);
                                if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                                    arrayList2.add(campaignEx);
                                }
                            } else if (w.b(campaignEx)) {
                                arrayList.add(campaignEx);
                                if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                                    arrayList2.add(campaignEx);
                                }
                            } else {
                                w.a(a.this.d, campaignEx, com.mbridge.msdk.foundation.same.a.D);
                            }
                        }
                        c.this.a(c.this.v, campaignEx);
                    }
                    c.a(c.this, arrayList2, a.this.d);
                    if (z2) {
                        com.mbridge.msdk.foundation.controller.a.f().h();
                    }
                    com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA = f.a(campaignUnit.getAds().get(0) != null ? campaignUnit.getAds().get(0).getType() : 1);
                    if (bVarA != null) {
                        bVarA.a((com.mbridge.msdk.mbnative.a.b<String, List<Campaign>>) a.this.d, (String) arrayList);
                    }
                    c.a(a.this.b, a.this.d);
                    if (Looper.myLooper() == null || !z) {
                        return;
                    }
                    Looper.loop();
                }
            }));
        }

        @Override // com.mbridge.msdk.mbnative.f.a.b
        public final void a(final int i, final String str) {
            c.this.a(new Thread(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.c.a.2
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z;
                    if (MBridgeConstans.PRELOAD_RESULT_IN_SUBTHREAD && Looper.myLooper() == null) {
                        Looper.prepare();
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!a.this.l) {
                        if (a.this.m != null) {
                            s.b(c.d, "REMOVE CANCEL TASK ON onFailed");
                            c.this.o.removeCallbacks(a.this.m);
                        }
                        if (a.this.f() == 1 || a.this.n) {
                            c.this.a(str, a.this.f(), a.this.d, a.this.e, a.this.o, a.this.j);
                        }
                    } else if (a.this.n) {
                        c.this.a(str, a.this.f(), a.this.d, a.this.e, a.this.o, a.this.j);
                    }
                    if (i == -1) {
                        c.b(a.this.b, a.this.d);
                    }
                    if (Looper.myLooper() == null || !z) {
                        return;
                    }
                    Looper.loop();
                }
            }));
        }

        public final void a(Runnable runnable) {
            this.m = runnable;
        }

        @Override // com.mbridge.msdk.mbnative.f.a.b
        public final void b(final List<Frame> list) {
            c.this.a(new Thread(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.c.a.3
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z;
                    if (MBridgeConstans.PRELOAD_RESULT_IN_SUBTHREAD && Looper.myLooper() == null) {
                        Looper.prepare();
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!a.this.l && a.this.m != null) {
                        s.b(c.d, "REMOVE CANCEL TASK ON onAdLoaded");
                        c.this.o.removeCallbacks(a.this.m);
                    }
                    if (list != null && list.size() > 0) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            Iterator<CampaignEx> it2 = ((Frame) it.next()).getCampaigns().iterator();
                            while (it2.hasNext()) {
                                c.this.a(c.this.v, it2.next());
                            }
                        }
                        HashMap map = new HashMap();
                        map.put(Long.valueOf(System.currentTimeMillis()), list);
                        if (c.e.containsKey("1_" + a.this.d)) {
                            c.e.remove("1_" + a.this.d);
                        }
                        c.e.put("1_" + a.this.d, map);
                    }
                    if (Looper.myLooper() == null || !z) {
                        return;
                    }
                    Looper.loop();
                }
            }));
        }
    }

    public final void a(String str, int i2, String str2, String str3, com.mbridge.msdk.a.a.a aVar, AdMobClickListener adMobClickListener) {
        Log.e(d, str);
        try {
            if ((this.a != null && this.a.size() <= 0) || this.a == null) {
                a(false, aVar, str);
            } else {
                a(i2, this.m, str2, str3, aVar, adMobClickListener);
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    /* compiled from: NativePreloadController.java */
    public class b implements Runnable {
        private int b;
        private com.mbridge.msdk.foundation.same.e.c c;
        private int d;
        private String e;
        private String f;
        private com.mbridge.msdk.a.a.a g;
        private boolean h = false;
        private AdMobClickListener i;

        public final void a(AdMobClickListener adMobClickListener) {
            this.i = adMobClickListener;
        }

        public final void a(boolean z) {
            this.h = z;
        }

        public final void a(com.mbridge.msdk.a.a.a aVar) {
            this.g = aVar;
        }

        public final void a(String str) {
            this.e = str;
        }

        public b(int i, com.mbridge.msdk.foundation.same.e.c cVar, int i2, String str, String str2) {
            this.b = i;
            this.c = cVar;
            this.d = i2;
            this.e = str;
            this.f = str2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            s.b(c.d, "cancel task adsource is = " + this.b);
            this.c.a(true);
            switch (this.b) {
                case 1:
                    c.this.y = true;
                    if (this.h || this.d == 1) {
                        c.this.a("REQUEST_TIMEOUT", this.d, this.e, this.f, this.g, this.i);
                        break;
                    }
                    break;
                case 2:
                    c.this.a("REQUEST_TIMEOUT", this.d, this.e, this.f, this.g, this.i);
                    break;
            }
        }
    }

    public static Map<String, Map<Long, Object>> a() {
        return e;
    }

    public static Map<String, Boolean> b() {
        return f;
    }

    public static Map<String, n> c() {
        return g;
    }

    public static Map<String, Integer> d() {
        return h;
    }

    public static Map<String, Integer> e() {
        return i;
    }

    private void a(final int i2, final long j2, final int i3, final String str, final String str2, final boolean z, final com.mbridge.msdk.a.a.a aVar, final AdMobClickListener adMobClickListener) {
        final UUID uuidC = o.C();
        if (uuidC == null) {
            this.C.put(str + "_" + z + "_ttc", false);
            this.C.put(str + "_" + z + "_post", false);
        } else {
            this.C.put(uuidC + str + "_" + z + "_ttc", false);
            this.C.put(uuidC + str + "_" + z + "_post", false);
        }
        final com.mbridge.msdk.foundation.same.e.a aVar2 = new com.mbridge.msdk.foundation.same.e.a() { // from class: com.mbridge.msdk.mbnative.c.c.2
            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void cancelTask() {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void pauseTask(boolean z2) {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void runTask() {
                if (c.this.r == null) {
                    c.this.r = i.a(com.mbridge.msdk.foundation.controller.a.f().j());
                }
                com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(c.this.r);
                cVarA.a();
                c.this.q = cVarA.a(str);
                if (c.this.C == null || c.this.C.isEmpty()) {
                    return;
                }
                if (uuidC == null) {
                    if (c.this.C.containsKey(str + "_" + z + "_ttc")) {
                        c.this.C.put(str + "_" + z + "_ttc", true);
                        return;
                    }
                }
                if (uuidC != null) {
                    if (c.this.C.containsKey(uuidC + str + "_" + z + "_ttc")) {
                        c.this.C.put(uuidC + str + "_" + z + "_ttc", true);
                    }
                }
            }
        };
        this.o.postDelayed(new com.mbridge.msdk.foundation.same.e.a() { // from class: com.mbridge.msdk.mbnative.c.c.3
            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void cancelTask() {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void pauseTask(boolean z2) {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void runTask() {
                boolean zBooleanValue = false;
                if (c.this.C != null && !c.this.C.isEmpty()) {
                    if (c.this.C.containsKey(str + "_" + z + "_ttc")) {
                        boolean zBooleanValue2 = ((Boolean) c.this.C.get(str + "_" + z + "_ttc")).booleanValue();
                        c.this.C.remove(str + "_" + z + "_ttc");
                        zBooleanValue = zBooleanValue2;
                    }
                    if (c.this.C.containsKey(uuidC + str + "_" + z + "_ttc")) {
                        zBooleanValue = ((Boolean) c.this.C.get(uuidC + str + "_" + z + "_ttc")).booleanValue();
                        c.this.C.remove(uuidC + str + "_" + z + "_ttc");
                    }
                }
                if (zBooleanValue) {
                    return;
                }
                c.this.o.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.c.3.1
                    @Override // java.lang.Runnable
                    public final void run() throws JSONException {
                        if (c.this.C != null && !c.this.C.isEmpty()) {
                            if (c.this.C.containsKey(str + "_" + z + "_post")) {
                                c.this.C.put(str + "_" + z + "_post", true);
                            }
                            if (c.this.C.containsKey(uuidC + str + "_" + z + "_post")) {
                                c.this.C.put(uuidC + str + "_" + z + "_post", true);
                            }
                        }
                        if (c.this.B != null) {
                            c.this.B.a(aVar2);
                        }
                        c.this.a(i2, j2, i3, c.this.m, str, str2, aVar, z, adMobClickListener);
                    }
                });
            }
        }, 90000L);
        a.b bVar = new a.b() { // from class: com.mbridge.msdk.mbnative.c.c.4
            @Override // com.mbridge.msdk.foundation.same.e.a.b
            public final void a(a.EnumC0111a enumC0111a) {
                if (enumC0111a == a.EnumC0111a.FINISH) {
                    c.this.o.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.c.4.1
                        @Override // java.lang.Runnable
                        public final void run() throws JSONException {
                            boolean zBooleanValue = false;
                            if (c.this.C != null && !c.this.C.isEmpty()) {
                                if (c.this.C.containsKey(str + "_" + z + "_post")) {
                                    boolean zBooleanValue2 = ((Boolean) c.this.C.get(str + "_" + z + "_post")).booleanValue();
                                    c.this.C.remove(str + "_" + z + "_post");
                                    zBooleanValue = zBooleanValue2;
                                }
                                if (c.this.C.containsKey(uuidC + str + "_" + z + "_post")) {
                                    zBooleanValue = ((Boolean) c.this.C.get(uuidC + str + "_" + z + "_post")).booleanValue();
                                    c.this.C.remove(uuidC + str + "_" + z + "_post");
                                }
                            }
                            if (zBooleanValue) {
                                return;
                            }
                            c.this.a(i2, j2, i3, c.this.m, str, str2, aVar, z, adMobClickListener);
                        }
                    });
                }
            }
        };
        if (this.B == null) {
            this.B = new com.mbridge.msdk.foundation.same.e.b(com.mbridge.msdk.foundation.controller.a.f().j());
        }
        if (this.B != null) {
            this.B.a(aVar2, bVar);
        }
    }

    public final void a(boolean z, com.mbridge.msdk.a.a.a aVar, String str) {
        if (z) {
            if (aVar == null || aVar.a()) {
                return;
            }
            aVar.a(true);
            aVar.onPreloadSucceed();
            return;
        }
        if (aVar == null || aVar.a()) {
            return;
        }
        aVar.a(true);
        aVar.onPreloadFaild(str);
    }

    public final void a(boolean z, Campaign campaign) {
        if (campaign == null) {
            return;
        }
        if (z && campaign.getIconDrawable() == null) {
            campaign.loadIconUrlAsyncWithBlock(null);
        }
        if (z && campaign.getBigDrawable() == null) {
            campaign.loadImageUrlAsyncWithBlock(null);
        }
    }

    public final void a(Thread thread) {
        if (!MBridgeConstans.PRELOAD_RESULT_IN_SUBTHREAD) {
            thread.run();
        } else {
            thread.start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0088 A[Catch: Exception -> 0x0086, TRY_LEAVE, TryCatch #1 {Exception -> 0x0086, blocks: (B:3:0x0002, B:9:0x007e, B:12:0x0088, B:5:0x0008, B:7:0x002a), top: B:19:0x0002, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.mbridge.msdk.mbnative.c.c r11, java.util.List r12, java.lang.String r13) throws java.lang.IllegalAccessException, java.lang.ClassNotFoundException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            if (r12 == 0) goto L88
            int r11 = r12.size()     // Catch: java.lang.Exception -> L86
            if (r11 <= 0) goto L88
            java.lang.String r11 = "com.mbridge.msdk.nativex.view.MBMediaView"
            java.lang.Class.forName(r11)     // Catch: java.lang.Exception -> L7e
            java.lang.String r11 = "com.mbridge.msdk.videocommon.download.b"
            java.lang.Class.forName(r11)     // Catch: java.lang.Exception -> L7e
            java.lang.String r11 = "com.mbridge.msdk.videocommon.download.b"
            java.lang.Class r11 = java.lang.Class.forName(r11)     // Catch: java.lang.Exception -> L7e
            java.lang.String r0 = "getInstance"
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L7e
            java.lang.reflect.Method r0 = r11.getMethod(r0, r2)     // Catch: java.lang.Exception -> L7e
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L7e
            r3 = 0
            java.lang.Object r0 = r0.invoke(r3, r2)     // Catch: java.lang.Exception -> L7e
            if (r0 == 0) goto L99
            java.lang.String r2 = "com.mbridge.msdk.videocommon.listener.a"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Exception -> L7e
            java.lang.String r4 = "createUnitCache"
            r5 = 5
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch: java.lang.Exception -> L7e
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r1] = r7     // Catch: java.lang.Exception -> L7e
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r8 = 1
            r6[r8] = r7     // Catch: java.lang.Exception -> L7e
            java.lang.Class<java.util.List> r7 = java.util.List.class
            r9 = 2
            r6[r9] = r7     // Catch: java.lang.Exception -> L7e
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L7e
            r10 = 3
            r6[r10] = r7     // Catch: java.lang.Exception -> L7e
            r7 = 4
            r6[r7] = r2     // Catch: java.lang.Exception -> L7e
            java.lang.reflect.Method r2 = r11.getMethod(r4, r6)     // Catch: java.lang.Exception -> L7e
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L7e
            com.mbridge.msdk.foundation.controller.a r5 = com.mbridge.msdk.foundation.controller.a.f()     // Catch: java.lang.Exception -> L7e
            android.content.Context r5 = r5.j()     // Catch: java.lang.Exception -> L7e
            r4[r1] = r5     // Catch: java.lang.Exception -> L7e
            r4[r8] = r13     // Catch: java.lang.Exception -> L7e
            r4[r9] = r12     // Catch: java.lang.Exception -> L7e
            java.lang.Integer r12 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Exception -> L7e
            r4[r10] = r12     // Catch: java.lang.Exception -> L7e
            r4[r7] = r3     // Catch: java.lang.Exception -> L7e
            r2.invoke(r0, r4)     // Catch: java.lang.Exception -> L7e
            java.lang.String r12 = "load"
            java.lang.Class[] r2 = new java.lang.Class[r8]     // Catch: java.lang.Exception -> L7e
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r2[r1] = r3     // Catch: java.lang.Exception -> L7e
            java.lang.reflect.Method r11 = r11.getMethod(r12, r2)     // Catch: java.lang.Exception -> L7e
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch: java.lang.Exception -> L7e
            r12[r1] = r13     // Catch: java.lang.Exception -> L7e
            r11.invoke(r0, r12)     // Catch: java.lang.Exception -> L7e
            goto L99
        L7e:
            java.lang.String r11 = com.mbridge.msdk.mbnative.c.c.d     // Catch: java.lang.Exception -> L86
            java.lang.String r12 = "please import the videocommon aar"
            com.mbridge.msdk.foundation.tools.s.d(r11, r12)     // Catch: java.lang.Exception -> L86
            goto L99
        L86:
            r11 = move-exception
            goto L90
        L88:
            java.lang.String r11 = com.mbridge.msdk.mbnative.c.c.d     // Catch: java.lang.Exception -> L86
            java.lang.String r12 = "onload 不用下载视频素材 size为0"
            com.mbridge.msdk.foundation.tools.s.b(r11, r12)     // Catch: java.lang.Exception -> L86
            goto L99
        L90:
            java.lang.String r12 = com.mbridge.msdk.mbnative.c.c.d
            java.lang.String r11 = com.mbridge.msdk.mbnative.b.a.a(r11)
            com.mbridge.msdk.foundation.tools.s.d(r12, r11)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbnative.c.c.a(com.mbridge.msdk.mbnative.c.c, java.util.List, java.lang.String):void");
    }
}
