package com.huawei.hms.support.api.push.b.b;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.push.b.c.b;
import com.huawei.hms.support.log.HMSLog;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements Serializable {
    private String A;
    private String B;
    private String C;
    private String D;
    private String H;
    private String I;
    private String J;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private int j;
    private String k;
    private int l;
    private int m;
    private String n;
    private String q;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    public int a = 1;
    public String b = "";
    private String c = "";
    private String o = "";
    private String p = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String E = "";
    private int F = 1;
    private int G = 0;
    private int K = b.STYLE_1.ordinal();
    private int L = 0;
    private String[] M = null;
    private String[] N = null;
    private String[] O = null;
    private int P = 0;
    private String[] Q = null;
    private String R = "";
    private String S = "";

    public a() {
    }

    public a(byte[] bArr, byte[] bArr2) {
        try {
            this.I = new String(bArr, C.UTF8_NAME);
            this.J = new String(bArr2, C.UTF8_NAME);
        } catch (Exception unused) {
            HMSLog.e("PushSelfShowLog", "get msg byte arr error");
        }
    }

    public String a() {
        HMSLog.d("PushSelfShowLog", "msgId =" + this.o);
        return this.o;
    }

    public boolean b() throws JSONException {
        try {
            if (TextUtils.isEmpty(this.J)) {
                HMSLog.d("PushSelfShowLog", "token is null");
                return false;
            }
            this.k = this.J;
            if (TextUtils.isEmpty(this.I)) {
                HMSLog.d("PushSelfShowLog", "msg is null");
                return false;
            }
            JSONObject jSONObject = new JSONObject(this.I);
            this.j = jSONObject.getInt("msgType");
            if (this.j != 1) {
                HMSLog.d("PushSelfShowLog", "not a selefShowMsg");
                return false;
            }
            a(jSONObject);
            JSONObject jSONObject2 = jSONObject.getJSONObject(RemoteMessageConst.MessageBody.MSG_CONTENT);
            if (jSONObject2 == null) {
                HMSLog.i("PushSelfShowLog", "msgObj == null");
                return false;
            }
            if (!b(jSONObject2)) {
                return false;
            }
            if (jSONObject2.has("dispPkgName")) {
                this.p = jSONObject2.getString("dispPkgName");
            }
            this.m = jSONObject2.has("rtn") ? jSONObject2.getInt("rtn") : 1;
            this.l = jSONObject2.has("fm") ? jSONObject2.getInt("fm") : 1;
            c(jSONObject2);
            if (jSONObject2.has("extras")) {
                this.q = jSONObject2.getJSONArray("extras").toString();
            }
            return d(jSONObject2);
        } catch (JSONException unused) {
            HMSLog.d("PushSelfShowLog", "parse message exception.");
            return false;
        } catch (Exception e) {
            HMSLog.d("PushSelfShowLog", e.toString());
            return false;
        }
    }

    private void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("group")) {
            this.c = jSONObject.getString("group");
            HMSLog.d("PushSelfShowLog", "NOTIFY_GROUP:" + this.c);
        }
        if (jSONObject.has("badgeClass")) {
            this.b = jSONObject.getString("badgeClass");
            HMSLog.d("PushSelfShowLog", "BADGE_CLASS:" + this.b);
        }
        if (jSONObject.has("badgeAddNum")) {
            this.a = jSONObject.getInt("badgeAddNum");
            HMSLog.d("PushSelfShowLog", "BADGE_ADD_NUM:" + this.a);
        }
    }

    private boolean b(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(RemoteMessageConst.MSGID)) {
            Object obj = jSONObject.get(RemoteMessageConst.MSGID);
            if (obj instanceof String) {
                this.o = (String) obj;
                return true;
            }
            if (!(obj instanceof Integer)) {
                return true;
            }
            this.o = String.valueOf(((Integer) obj).intValue());
            return true;
        }
        HMSLog.i("PushSelfShowLog", "msgId == null");
        return false;
    }

    private void c(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("ap")) {
            String string = jSONObject.getString("ap");
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(string) && string.length() < 48) {
                int length = 48 - string.length();
                for (int i = 0; i < length; i++) {
                    sb.append(PushConstants.PUSH_TYPE_NOTIFY);
                }
                sb.append(string);
                this.n = sb.toString();
                return;
            }
            this.n = string.substring(0, 48);
        }
    }

    private boolean d(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        if (!jSONObject.has(RemoteMessageConst.MessageBody.PS_CONTENT) || (jSONObject2 = jSONObject.getJSONObject(RemoteMessageConst.MessageBody.PS_CONTENT)) == null) {
            return false;
        }
        this.r = jSONObject2.getString("cmd");
        if (jSONObject2.has("content")) {
            this.s = jSONObject2.getString("content");
        } else {
            this.s = "";
        }
        if (jSONObject2.has(RemoteMessageConst.Notification.NOTIFY_ICON)) {
            this.t = jSONObject2.getString(RemoteMessageConst.Notification.NOTIFY_ICON);
        } else {
            this.t = "" + this.o;
        }
        if (jSONObject2.has("statusIcon")) {
            this.v = jSONObject2.getString("statusIcon");
        }
        if (jSONObject2.has(RemoteMessageConst.Notification.NOTIFY_TITLE)) {
            this.u = jSONObject2.getString(RemoteMessageConst.Notification.NOTIFY_TITLE);
        }
        if (jSONObject2.has("notifyParam")) {
            i(jSONObject2);
        }
        if (jSONObject2.has(RemoteMessageConst.MessageBody.PARAM)) {
            return e(jSONObject2);
        }
        return false;
    }

    private boolean e(JSONObject jSONObject) throws JSONException {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(RemoteMessageConst.MessageBody.PARAM);
            if (jSONObject2.has("autoClear")) {
                this.h = jSONObject2.getInt("autoClear");
            } else {
                this.h = 0;
            }
            if (!PushConstants.EXTRA_APPLICATION_PENDING_INTENT.equals(this.r) && !"cosa".equals(this.r)) {
                if ("phone".equals(this.r)) {
                    if (jSONObject2.has("phoneNum")) {
                        this.y = jSONObject2.getString("phoneNum");
                        return true;
                    }
                    HMSLog.d("PushSelfShowLog", "phoneNum is null");
                    return false;
                }
                if ("url".equals(this.r)) {
                    g(jSONObject2);
                    return true;
                }
                if (!"rp".equals(this.r)) {
                    return true;
                }
                h(jSONObject2);
                return true;
            }
            f(jSONObject2);
            return true;
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "ParseParam error ", e);
            return false;
        }
    }

    private boolean f(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has(RemoteMessageConst.Notification.CLICK_ACTION)) {
            this.A = jSONObject.getString(RemoteMessageConst.Notification.CLICK_ACTION);
        }
        if (jSONObject.has(RemoteMessageConst.Notification.INTENT_URI)) {
            this.i = jSONObject.getString(RemoteMessageConst.Notification.INTENT_URI);
        }
        if (jSONObject.has("appPackageName")) {
            this.z = jSONObject.getString("appPackageName");
            return true;
        }
        HMSLog.d("PushSelfShowLog", "appPackageName is null");
        return false;
    }

    private boolean g(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("url")) {
            this.B = jSONObject.getString("url");
            if (jSONObject.has("inBrowser")) {
                this.F = jSONObject.getInt("inBrowser");
            }
            if (jSONObject.has("needUserId")) {
                this.G = jSONObject.getInt("needUserId");
            }
            if (jSONObject.has("sign")) {
                this.H = jSONObject.getString("sign");
            }
            if (!jSONObject.has("rpt") || !jSONObject.has("rpl")) {
                return true;
            }
            this.C = jSONObject.getString("rpl");
            this.D = jSONObject.getString("rpt");
            if (!jSONObject.has("rpct")) {
                return true;
            }
            this.E = jSONObject.getString("rpct");
            return true;
        }
        HMSLog.d("PushSelfShowLog", "url is null");
        return false;
    }

    private boolean h(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("rpt") && jSONObject.has("rpl")) {
            this.C = jSONObject.getString("rpl");
            this.D = jSONObject.getString("rpt");
            if (jSONObject.has("rpct")) {
                this.E = jSONObject.getString("rpct");
            }
            if (!jSONObject.has("needUserId")) {
                return true;
            }
            this.G = jSONObject.getInt("needUserId");
            return true;
        }
        HMSLog.d("PushSelfShowLog", "rpl or rpt is null");
        return false;
    }

    public byte[] c() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("autoClear", this.h);
            jSONObject4.put(NotifyType.SOUND, this.d);
            jSONObject4.put(CampaignEx.JSON_KEY_AD_R, this.e);
            jSONObject4.put("smsC", this.f);
            jSONObject4.put("mmsUrl", this.g);
            jSONObject4.put("url", this.B);
            jSONObject4.put("inBrowser", this.F);
            jSONObject4.put("needUserId", this.G);
            jSONObject4.put("sign", this.H);
            jSONObject4.put("rpl", this.C);
            jSONObject4.put("rpt", this.D);
            jSONObject4.put("rpct", this.E);
            jSONObject4.put("appPackageName", this.z);
            jSONObject4.put(RemoteMessageConst.Notification.CLICK_ACTION, this.A);
            jSONObject4.put(RemoteMessageConst.Notification.INTENT_URI, this.i);
            jSONObject4.put("phoneNum", this.y);
            jSONObject4.put("replyToSms", this.x);
            jSONObject4.put("smsNum", this.w);
            jSONObject3.put("cmd", this.r);
            jSONObject3.put("content", this.s);
            jSONObject3.put(RemoteMessageConst.Notification.NOTIFY_ICON, this.t);
            jSONObject3.put(RemoteMessageConst.Notification.NOTIFY_TITLE, this.u);
            jSONObject3.put("statusIcon", this.v);
            jSONObject3.put(RemoteMessageConst.MessageBody.PARAM, jSONObject4);
            jSONObject2.put("dispPkgName", this.p);
            jSONObject2.put(RemoteMessageConst.MSGID, this.o);
            jSONObject2.put("fm", this.l);
            jSONObject2.put("ap", this.n);
            jSONObject2.put("rtn", this.m);
            jSONObject2.put(RemoteMessageConst.MessageBody.PS_CONTENT, jSONObject3);
            if (this.q != null && this.q.length() > 0) {
                jSONObject2.put("extras", new JSONArray(this.q));
            }
            jSONObject.put("msgType", this.j);
            jSONObject.put(RemoteMessageConst.MessageBody.MSG_CONTENT, jSONObject2);
            jSONObject.put("group", this.c);
            jSONObject.put("badgeClass", this.b);
            jSONObject.put("badgeAddNum", this.a);
            return jSONObject.toString().getBytes(C.UTF8_NAME);
        } catch (UnsupportedEncodingException unused) {
            HMSLog.e("PushSelfShowLog", "getMsgData failed UnsupportedEncodingException");
            return new byte[0];
        } catch (JSONException unused2) {
            HMSLog.e("PushSelfShowLog", "getMsgData failed JSONException");
            return new byte[0];
        }
    }

    public byte[] d() {
        try {
            if (this.k != null && this.k.length() > 0) {
                return this.k.getBytes(C.UTF8_NAME);
            }
        } catch (Exception unused) {
            HMSLog.w("PushSelfShowLog", "getToken getByte failed ");
        }
        return new byte[0];
    }

    private boolean i(JSONObject jSONObject) throws JSONException {
        HMSLog.d("PushSelfShowLog", "enter parseNotifyParam");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("notifyParam");
            if (!jSONObject2.has(TtmlNode.TAG_STYLE)) {
                return false;
            }
            k(jSONObject2);
            j(jSONObject2);
            return true;
        } catch (JSONException e) {
            HMSLog.i("PushSelfShowLog", e.toString());
            return false;
        }
    }

    private void j(JSONObject jSONObject) throws JSONException {
        this.K = jSONObject.getInt(TtmlNode.TAG_STYLE);
        HMSLog.d("PushSelfShowLog", "style:" + this.K);
        b bVar = b.STYLE_1;
        if (this.K >= 0 && this.K < b.values().length) {
            bVar = b.values()[this.K];
        }
        switch (bVar) {
            case STYLE_4:
                if (jSONObject.has("iconCount")) {
                    this.P = jSONObject.getInt("iconCount");
                }
                if (this.P > 0) {
                    if (this.P > 6) {
                        this.P = 6;
                    }
                    HMSLog.d("PushSelfShowLog", "iconCount:" + this.P);
                    this.Q = new String[this.P];
                    int i = 0;
                    while (i < this.P) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("icon");
                        int i2 = i + 1;
                        sb.append(i2);
                        String string = sb.toString();
                        if (jSONObject.has(string)) {
                            this.Q[i] = jSONObject.getString(string);
                        }
                        i = i2;
                    }
                    break;
                }
                break;
            case STYLE_5:
                if (jSONObject.has("subTitle")) {
                    this.R = jSONObject.getString("subTitle");
                    HMSLog.d("PushSelfShowLog", "subTitle:" + this.R);
                    break;
                }
                break;
            case STYLE_6:
            case STYLE_8:
                if (jSONObject.has("bigPic")) {
                    this.S = jSONObject.getString("bigPic");
                    HMSLog.d("PushSelfShowLog", "bigPicUrl:" + this.S);
                    break;
                }
                break;
        }
    }

    private void k(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("btnCount")) {
            this.L = jSONObject.getInt("btnCount");
        }
        if (this.L > 0) {
            if (this.L > 3) {
                this.L = 3;
            }
            HMSLog.d("PushSelfShowLog", "btnCount:" + this.L);
            this.M = new String[this.L];
            this.N = new String[this.L];
            this.O = new String[this.L];
            int i = 0;
            while (i < this.L) {
                StringBuilder sb = new StringBuilder();
                sb.append("btn");
                int i2 = i + 1;
                sb.append(i2);
                sb.append("Text");
                String string = sb.toString();
                String str = "btn" + i2 + "Image";
                String str2 = "btn" + i2 + "Event";
                if (jSONObject.has(string)) {
                    this.M[i] = jSONObject.getString(string);
                }
                if (jSONObject.has(str)) {
                    this.N[i] = jSONObject.getString(str);
                }
                if (jSONObject.has(str2)) {
                    this.O[i] = jSONObject.getString(str2);
                }
                i = i2;
            }
        }
    }

    public String e() {
        return this.c;
    }

    public int f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.n;
    }

    public String i() {
        return this.p;
    }

    public String j() {
        return this.r;
    }

    public String k() {
        return this.s;
    }

    public String l() {
        return this.t;
    }

    public String m() {
        return this.u;
    }

    public String n() {
        return this.y;
    }

    public String o() {
        return this.z;
    }

    public String p() {
        return this.A;
    }
}
