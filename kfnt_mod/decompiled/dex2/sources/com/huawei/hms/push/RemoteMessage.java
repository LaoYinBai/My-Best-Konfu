package com.huawei.hms.push;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.utils.DateUtil;
import com.huawei.hms.push.utils.JsonUtil;
import com.huawei.hms.support.api.push.PushException;
import com.huawei.hms.support.api.push.c.a;
import com.huawei.hms.support.api.push.c.a.a.b;
import com.huawei.hms.support.log.HMSLog;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class RemoteMessage implements Parcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    private static final String[] a = new String[0];
    private static final int[] b = new int[0];
    private static final long[] c = new long[0];
    private static final HashMap<String, Object> d = new HashMap<>(6);
    private static final HashMap<String, Object> e;
    private static final HashMap<String, Object> f;
    private static final HashMap<String, Object> g;
    private static final HashMap<String, Object> h;
    private Bundle i;
    private Notification j;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    static {
        d.put(RemoteMessageConst.FROM, "");
        d.put(RemoteMessageConst.COLLAPSE_KEY, "");
        d.put(RemoteMessageConst.SEND_TIME, "");
        d.put(RemoteMessageConst.TTL, Integer.valueOf(RemoteMessageConst.DEFAULT_TTL));
        d.put(RemoteMessageConst.URGENCY, 2);
        d.put(RemoteMessageConst.ORI_URGENCY, 2);
        d.put(RemoteMessageConst.SEND_MODE, 0);
        d.put(RemoteMessageConst.RECEIPT_MODE, 0);
        e = new HashMap<>(8);
        e.put(RemoteMessageConst.Notification.TITLE_LOC_KEY, "");
        e.put(RemoteMessageConst.Notification.BODY_LOC_KEY, "");
        e.put(RemoteMessageConst.Notification.NOTIFY_ICON, "");
        e.put(RemoteMessageConst.Notification.TITLE_LOC_ARGS, a);
        e.put(RemoteMessageConst.Notification.BODY_LOC_ARGS, a);
        e.put(RemoteMessageConst.Notification.TICKER, "");
        e.put(RemoteMessageConst.Notification.NOTIFY_TITLE, "");
        e.put("content", "");
        f = new HashMap<>(8);
        f.put("icon", "");
        f.put("color", "");
        f.put(RemoteMessageConst.Notification.SOUND, "");
        f.put(RemoteMessageConst.Notification.DEFAULT_LIGHT_SETTINGS, 1);
        f.put(RemoteMessageConst.Notification.LIGHT_SETTINGS, b);
        f.put(RemoteMessageConst.Notification.DEFAULT_SOUND, 1);
        f.put(RemoteMessageConst.Notification.DEFAULT_VIBRATE_TIMINGS, 1);
        f.put(RemoteMessageConst.Notification.VIBRATE_TIMINGS, c);
        g = new HashMap<>(8);
        g.put(RemoteMessageConst.Notification.TAG, "");
        g.put(RemoteMessageConst.Notification.WHEN, "");
        g.put(RemoteMessageConst.Notification.LOCAL_ONLY, 1);
        g.put(RemoteMessageConst.Notification.BADGE_SET_NUM, "");
        g.put("priority", "");
        g.put(RemoteMessageConst.Notification.AUTO_CANCEL, 1);
        g.put(RemoteMessageConst.Notification.VISIBILITY, "");
        g.put(RemoteMessageConst.Notification.CHANNEL_ID, "");
        h = new HashMap<>(3);
        h.put(RemoteMessageConst.Notification.CLICK_ACTION, "");
        h.put(RemoteMessageConst.Notification.INTENT_URI, "");
        h.put("url", "");
        CREATOR = new Parcelable.Creator<RemoteMessage>() { // from class: com.huawei.hms.push.RemoteMessage.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public RemoteMessage createFromParcel(Parcel parcel) {
                return new RemoteMessage(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public RemoteMessage[] newArray(int i) {
                return new RemoteMessage[i];
            }
        };
    }

    public RemoteMessage(Bundle bundle) {
        this.i = a(bundle);
    }

    public RemoteMessage(Parcel parcel) {
        this.i = parcel.readBundle();
        this.j = (Notification) parcel.readSerializable();
    }

    private Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        JSONObject jSONObjectB = b(bundle);
        JSONObject jSONObjectA = a(jSONObjectB);
        String string = JsonUtil.getString(jSONObjectA, "data", null);
        bundle2.putString(RemoteMessageConst.DEVICE_TOKEN, bundle.getString(RemoteMessageConst.DEVICE_TOKEN));
        JSONObject jSONObjectB2 = b(jSONObjectA);
        JSONObject jSONObjectC = c(jSONObjectB2);
        JSONObject jSONObjectD = d(jSONObjectB2);
        if (bundle.getInt(RemoteMessageConst.INPUT_TYPE) == 1 && a.a(jSONObjectA, jSONObjectB2, string)) {
            bundle2.putString("data", com.huawei.hms.support.api.push.c.a.a.a.a(bundle.getByteArray(RemoteMessageConst.MSGBODY)));
            return bundle2;
        }
        String string2 = bundle.getString(RemoteMessageConst.TO);
        String string3 = bundle.getString(RemoteMessageConst.MSGTYPE);
        String string4 = JsonUtil.getString(jSONObjectA, RemoteMessageConst.MSGID, null);
        bundle2.putString(RemoteMessageConst.TO, string2);
        bundle2.putString("data", string);
        bundle2.putString(RemoteMessageConst.MSGID, string4);
        bundle2.putString(RemoteMessageConst.MSGTYPE, string3);
        JsonUtil.transferJsonObjectToBundle(jSONObjectB, bundle2, d);
        bundle2.putBundle(RemoteMessageConst.NOTIFICATION, a(jSONObjectB, jSONObjectA, jSONObjectB2, jSONObjectC, jSONObjectD));
        return bundle2;
    }

    private Bundle a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Bundle bundle = new Bundle();
        JsonUtil.transferJsonObjectToBundle(jSONObject3, bundle, e);
        JsonUtil.transferJsonObjectToBundle(jSONObject4, bundle, f);
        JsonUtil.transferJsonObjectToBundle(jSONObject, bundle, g);
        JsonUtil.transferJsonObjectToBundle(jSONObject5, bundle, h);
        bundle.putInt(RemoteMessageConst.Notification.NOTIFY_ID, JsonUtil.getInt(jSONObject2, RemoteMessageConst.Notification.NOTIFY_ID, 0));
        return bundle;
    }

    public String getFrom() {
        return this.i.getString(RemoteMessageConst.FROM);
    }

    public String getTo() {
        return this.i.getString(RemoteMessageConst.TO);
    }

    public String getData() {
        return this.i.getString("data");
    }

    public Map<String, String> getDataOfMap() {
        HashMap map = new HashMap();
        String string = this.i.getString("data");
        if (string != null && !string.trim().isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String strValueOf = String.valueOf(itKeys.next());
                    map.put(strValueOf, String.valueOf(jSONObject.get(strValueOf)));
                }
            } catch (JSONException unused) {
                HMSLog.w("RemoteMessage", "JSONException: get data from map failed");
            }
        }
        return map;
    }

    public String getCollapseKey() {
        return this.i.getString(RemoteMessageConst.COLLAPSE_KEY);
    }

    public String getMessageId() {
        return this.i.getString(RemoteMessageConst.MSGID);
    }

    public String getMessageType() {
        return this.i.getString(RemoteMessageConst.MSGTYPE);
    }

    public long getSentTime() {
        try {
            String string = this.i.getString(RemoteMessageConst.SEND_TIME);
            if (TextUtils.isEmpty(string)) {
                return 0L;
            }
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            HMSLog.w("RemoteMessage", "NumberFormatException: get sendTime error.");
            return 0L;
        }
    }

    public int getTtl() {
        return this.i.getInt(RemoteMessageConst.TTL);
    }

    public int getSendMode() {
        return this.i.getInt(RemoteMessageConst.SEND_MODE);
    }

    public int getReceiptMode() {
        return this.i.getInt(RemoteMessageConst.RECEIPT_MODE);
    }

    public int getOriginalUrgency() {
        int i = this.i.getInt(RemoteMessageConst.ORI_URGENCY);
        if (i == 1 || i == 2) {
            return i;
        }
        return 0;
    }

    public int getUrgency() {
        int i = this.i.getInt(RemoteMessageConst.URGENCY);
        if (i == 1 || i == 2) {
            return i;
        }
        return 0;
    }

    public String getToken() {
        return this.i.getString(RemoteMessageConst.DEVICE_TOKEN);
    }

    public Notification getNotification() {
        Bundle bundle = this.i.getBundle(RemoteMessageConst.NOTIFICATION);
        if (this.j == null && bundle != null) {
            this.j = new Notification(bundle);
        }
        if (this.j == null) {
            this.j = new Notification(new Bundle());
        }
        return this.j;
    }

    public static class Notification implements Serializable {
        private final long[] A;
        private final String B;
        private final String a;
        private final String b;
        private final String[] c;
        private final String d;
        private final String e;
        private final String[] f;
        private final String g;
        private final String h;
        private final String i;
        private final String j;
        private final String k;
        private final String l;
        private final String m;
        private final Uri n;
        private final int o;
        private final String p;
        private final int q;
        private final int r;
        private final int s;
        private final int[] t;
        private final String u;
        private final int v;
        private final String w;
        private final int x;
        private final String y;
        private final String z;

        private Notification(Bundle bundle) {
            this.a = bundle.getString(RemoteMessageConst.Notification.NOTIFY_TITLE);
            this.d = bundle.getString("content");
            this.b = bundle.getString(RemoteMessageConst.Notification.TITLE_LOC_KEY);
            this.e = bundle.getString(RemoteMessageConst.Notification.BODY_LOC_KEY);
            this.c = bundle.getStringArray(RemoteMessageConst.Notification.TITLE_LOC_ARGS);
            this.f = bundle.getStringArray(RemoteMessageConst.Notification.BODY_LOC_ARGS);
            this.g = bundle.getString("icon");
            this.j = bundle.getString("color");
            this.h = bundle.getString(RemoteMessageConst.Notification.SOUND);
            this.i = bundle.getString(RemoteMessageConst.Notification.TAG);
            this.m = bundle.getString(RemoteMessageConst.Notification.CHANNEL_ID);
            this.k = bundle.getString(RemoteMessageConst.Notification.CLICK_ACTION);
            this.l = bundle.getString(RemoteMessageConst.Notification.INTENT_URI);
            this.o = bundle.getInt(RemoteMessageConst.Notification.NOTIFY_ID);
            String string = bundle.getString("url");
            this.n = !TextUtils.isEmpty(string) ? Uri.parse(string) : null;
            this.p = bundle.getString(RemoteMessageConst.Notification.NOTIFY_ICON);
            this.q = bundle.getInt(RemoteMessageConst.Notification.DEFAULT_LIGHT_SETTINGS);
            this.r = bundle.getInt(RemoteMessageConst.Notification.DEFAULT_SOUND);
            this.s = bundle.getInt(RemoteMessageConst.Notification.DEFAULT_VIBRATE_TIMINGS);
            this.t = bundle.getIntArray(RemoteMessageConst.Notification.LIGHT_SETTINGS);
            this.u = bundle.getString(RemoteMessageConst.Notification.WHEN);
            this.v = bundle.getInt(RemoteMessageConst.Notification.LOCAL_ONLY);
            this.w = bundle.getString(RemoteMessageConst.Notification.BADGE_SET_NUM, null);
            this.x = bundle.getInt(RemoteMessageConst.Notification.AUTO_CANCEL);
            this.y = bundle.getString("priority", null);
            this.z = bundle.getString(RemoteMessageConst.Notification.TICKER);
            this.A = bundle.getLongArray(RemoteMessageConst.Notification.VIBRATE_TIMINGS);
            this.B = bundle.getString(RemoteMessageConst.Notification.VISIBILITY, null);
        }

        public String getTitle() {
            return this.a;
        }

        public String getTitleLocalizationKey() {
            return this.b;
        }

        public String[] getTitleLocalizationArgs() {
            if (this.c == null) {
                return null;
            }
            return (String[]) this.c.clone();
        }

        public String getBody() {
            return this.d;
        }

        public String getBodyLocalizationKey() {
            return this.e;
        }

        public String[] getBodyLocalizationArgs() {
            if (this.f == null) {
                return null;
            }
            return (String[]) this.f.clone();
        }

        public String getIcon() {
            return this.g;
        }

        public Uri getImageUrl() {
            if (this.p == null) {
                return null;
            }
            return Uri.parse(this.p);
        }

        public String getSound() {
            return this.h;
        }

        public String getTag() {
            return this.i;
        }

        public String getColor() {
            return this.j;
        }

        public String getClickAction() {
            return this.k;
        }

        public String getIntentUri() {
            return this.l;
        }

        public String getChannelId() {
            return this.m;
        }

        public Uri getLink() {
            return this.n;
        }

        public int getNotifyId() {
            return this.o;
        }

        public boolean isDefaultLight() {
            return this.q == 1;
        }

        public boolean isDefaultSound() {
            return this.r == 1;
        }

        public boolean isDefaultVibrate() {
            return this.s == 1;
        }

        public Long getWhen() {
            if (!TextUtils.isEmpty(this.u)) {
                try {
                    return Long.valueOf(DateUtil.parseUtcToMillisecond(this.u));
                } catch (StringIndexOutOfBoundsException unused) {
                    HMSLog.w("RemoteMessage", "StringIndexOutOfBoundsException: parse when failed.");
                } catch (ParseException unused2) {
                    HMSLog.w("RemoteMessage", "ParseException: parse when failed.");
                }
            }
            return null;
        }

        public int[] getLightSettings() {
            if (this.t == null) {
                return null;
            }
            return (int[]) this.t.clone();
        }

        public boolean isLocalOnly() {
            return this.v == 1;
        }

        public Integer getBadgeNumber() {
            return a(this.w);
        }

        public boolean isAutoCancel() {
            return this.x == 1;
        }

        public Integer getImportance() {
            return a(this.y);
        }

        public String getTicker() {
            return this.z;
        }

        public long[] getVibrateConfig() {
            if (this.A == null) {
                return null;
            }
            return (long[]) this.A.clone();
        }

        public Integer getVisibility() {
            return a(this.B);
        }

        private Integer a(String str) {
            if (str != null) {
                try {
                    return Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    HMSLog.w("RemoteMessage", "NumberFormatException: get " + str + " failed.");
                }
            }
            return null;
        }
    }

    public static class Builder {
        private final Bundle a = new Bundle();
        private final Map<String, String> b = new HashMap();

        public Builder(String str) {
            this.a.putString(RemoteMessageConst.TO, str);
        }

        public RemoteMessage build() throws JSONException {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            try {
                for (Map.Entry<String, String> entry : this.b.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                try {
                    String string = this.a.getString(RemoteMessageConst.MSGID);
                    String string2 = jSONObject.toString();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(RemoteMessageConst.COLLAPSE_KEY, this.a.getString(RemoteMessageConst.COLLAPSE_KEY));
                    jSONObject2.put(RemoteMessageConst.TTL, this.a.getInt(RemoteMessageConst.TTL));
                    jSONObject2.put(RemoteMessageConst.SEND_MODE, this.a.getInt(RemoteMessageConst.SEND_MODE));
                    jSONObject2.put(RemoteMessageConst.RECEIPT_MODE, this.a.getInt(RemoteMessageConst.RECEIPT_MODE));
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject.length() != 0) {
                        jSONObject3.put("data", string2);
                    }
                    jSONObject3.put(RemoteMessageConst.MSGID, string);
                    jSONObject2.put(RemoteMessageConst.MessageBody.MSG_CONTENT, jSONObject3);
                    bundle.putByteArray(RemoteMessageConst.MSGBODY, jSONObject2.toString().getBytes(b.a));
                    bundle.putString(RemoteMessageConst.TO, this.a.getString(RemoteMessageConst.TO));
                    bundle.putString(RemoteMessageConst.MSGTYPE, this.a.getString(RemoteMessageConst.MSGTYPE));
                    return new RemoteMessage(bundle);
                } catch (JSONException unused) {
                    HMSLog.w("RemoteMessage", "JSONException: parse message body failed.");
                    throw new PushException(PushException.EXCEPTION_SEND_FAILED);
                }
            } catch (JSONException unused2) {
                HMSLog.w("RemoteMessage", "JSONException: parse data to json failed.");
                throw new PushException(PushException.EXCEPTION_SEND_FAILED);
            }
        }

        public Builder addData(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("add data failed, key is null.");
            }
            this.b.put(str, str2);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.b.clear();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.b.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder clearData() {
            this.b.clear();
            return this;
        }

        public Builder setMessageId(String str) {
            this.a.putString(RemoteMessageConst.MSGID, str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.a.putString(RemoteMessageConst.MSGTYPE, str);
            return this;
        }

        public Builder setTtl(int i) {
            if (i < 1 || i > 1296000) {
                throw new IllegalArgumentException("ttl must be greater than or equal to 1 and less than or equal to 1296000");
            }
            this.a.putInt(RemoteMessageConst.TTL, i);
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.a.putString(RemoteMessageConst.COLLAPSE_KEY, str);
            return this;
        }

        public Builder setSendMode(int i) {
            if (i != 0 && i != 1) {
                throw new IllegalArgumentException("send mode can only be 0 or 1.");
            }
            this.a.putInt(RemoteMessageConst.SEND_MODE, i);
            return this;
        }

        public Builder setReceiptMode(int i) {
            if (i != 1 && i != 0) {
                throw new IllegalArgumentException("receipt mode can only be 0 or 1.");
            }
            this.a.putInt(RemoteMessageConst.RECEIPT_MODE, i);
            return this;
        }
    }

    private static JSONObject b(Bundle bundle) {
        try {
            return new JSONObject(com.huawei.hms.support.api.push.c.a.a.a.a(bundle.getByteArray(RemoteMessageConst.MSGBODY)));
        } catch (JSONException unused) {
            HMSLog.w("RemoteMessage", "JSONException:parse message body failed.");
            return null;
        }
    }

    private static JSONObject a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.MSG_CONTENT);
        }
        return null;
    }

    private static JSONObject b(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.PS_CONTENT);
        }
        return null;
    }

    private static JSONObject c(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.NOTIFY_DETAIL);
        }
        return null;
    }

    private static JSONObject d(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.PARAM);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.i);
        parcel.writeSerializable(this.j);
    }
}
