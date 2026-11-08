package com.bytedance.push;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PushBody implements Parcelable {
    public static final Parcelable.Creator<PushBody> CREATOR = new Parcelable.Creator<PushBody>() { // from class: com.bytedance.push.PushBody.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PushBody createFromParcel(Parcel parcel) {
            return new PushBody(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PushBody[] newArray(int i) {
            return new PushBody[i];
        }
    };
    public static final String KEY_PASS_THROUGH = "pass_through";
    public static final int TYPE_IMAGE_BIG = 1;
    public static final int TYPE_IMAGE_NONE = 0;
    public static final int TYPE_IMAGE_SMALL = 2;
    public String extra;
    public final String groupId;
    public long id;
    public int imageType;
    public String imageUrl;
    public boolean mIsPassThough;
    public String mNotificationChannelId;
    public int messageType;
    public JSONObject msgData;
    public final boolean needFilterMsgByUid;
    public String open_url;
    public long rid64;
    public final boolean showWhen;
    public final String targetSecUid;
    public String text;
    public String title;
    public boolean useLED;
    public boolean useSound;
    public boolean useVibrator;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PushBody(JSONObject jSONObject) {
        this.msgData = jSONObject;
        this.open_url = jSONObject.optString("open_url");
        this.text = jSONObject.optString(com.baidu.mobads.sdk.internal.a.f3090b);
        this.title = jSONObject.optString(com.alipay.sdk.widget.d.m);
        this.imageUrl = jSONObject.optString("image_url");
        this.id = jSONObject.optLong("id", 0L);
        this.rid64 = jSONObject.optLong("rid64", 0L);
        this.useLED = optBoolean(jSONObject, "use_led", false);
        this.useSound = optBoolean(jSONObject, "sound", false);
        this.useVibrator = optBoolean(jSONObject, "use_vibrator", false);
        this.imageType = jSONObject.optInt("image_type", 0);
        this.mIsPassThough = jSONObject.optInt(KEY_PASS_THROUGH, 1) > 0;
        this.mNotificationChannelId = jSONObject.optString("notify_channel");
        this.messageType = jSONObject.optInt("msg_from");
        this.groupId = jSONObject.optString("group_id_str");
        this.showWhen = jSONObject.optInt("st", 1) > 0;
        this.targetSecUid = jSONObject.optString("ttpush_sec_target_uid");
        this.needFilterMsgByUid = jSONObject.optInt("ttpush_need_filter_uid", 0) > 0;
        this.extra = jSONObject.optString("extra_str");
    }

    private static boolean optBoolean(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return z;
        }
        int iOptInt = jSONObject.optInt(str, -1);
        if (iOptInt >= 1) {
            return true;
        }
        if (iOptInt == 0) {
            return false;
        }
        return jSONObject.optBoolean(str, z);
    }

    public String getNotificationChannel() {
        return this.mNotificationChannelId;
    }

    public String getOriginData() {
        if (this.msgData != null) {
            return this.msgData.toString();
        }
        return null;
    }

    public String getExtra() {
        return this.extra;
    }

    public boolean checkValid() {
        return (this.id <= 0 || TextUtils.isEmpty(this.open_url) || TextUtils.isEmpty(this.groupId)) ? false : true;
    }

    public String toString() {
        return "PushBody{groupId='" + this.groupId + "', extra='" + this.extra + "', mNotificationChannelId='" + this.mNotificationChannelId + "', mIsPassThough=" + this.mIsPassThough + ", msgData=" + this.msgData + ", text='" + this.text + "', title='" + this.title + "', imageUrl='" + this.imageUrl + "', imageType=" + this.imageType + ", id=" + this.id + ", open_url='" + this.open_url + "', useLED=" + this.useLED + ", useSound=" + this.useSound + ", useVibrator=" + this.useVibrator + ", messageType=" + this.messageType + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.rid64);
        parcel.writeString(this.groupId);
        parcel.writeByte(this.showWhen ? (byte) 1 : (byte) 0);
        parcel.writeString(this.targetSecUid);
        parcel.writeByte(this.needFilterMsgByUid ? (byte) 1 : (byte) 0);
        parcel.writeString(this.extra);
        parcel.writeString(this.mNotificationChannelId);
        parcel.writeByte(this.mIsPassThough ? (byte) 1 : (byte) 0);
        parcel.writeString(this.msgData.toString());
        parcel.writeString(this.text);
        parcel.writeString(this.title);
        parcel.writeString(this.imageUrl);
        parcel.writeInt(this.imageType);
        parcel.writeString(this.open_url);
        parcel.writeByte(this.useLED ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.useSound ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.useVibrator ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.messageType);
    }

    protected PushBody(Parcel parcel) {
        this.id = parcel.readLong();
        this.rid64 = parcel.readLong();
        this.groupId = parcel.readString();
        this.showWhen = parcel.readByte() != 0;
        this.targetSecUid = parcel.readString();
        this.needFilterMsgByUid = parcel.readByte() != 0;
        this.extra = parcel.readString();
        this.mNotificationChannelId = parcel.readString();
        this.mIsPassThough = parcel.readByte() != 0;
        try {
            this.msgData = new JSONObject(parcel.readString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.text = parcel.readString();
        this.title = parcel.readString();
        this.imageUrl = parcel.readString();
        this.imageType = parcel.readInt();
        this.open_url = parcel.readString();
        this.useLED = parcel.readByte() != 0;
        this.useSound = parcel.readByte() != 0;
        this.useVibrator = parcel.readByte() != 0;
        this.messageType = parcel.readInt();
    }
}
