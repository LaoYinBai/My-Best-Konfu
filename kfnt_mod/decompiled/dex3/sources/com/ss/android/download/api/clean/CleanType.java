package com.ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanType extends BaseCleanGroup implements Parcelable {
    public static final Parcelable.Creator<CleanType> CREATOR = new Parcelable.Creator<CleanType>() { // from class: com.ss.android.download.api.clean.CleanType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanType createFromParcel(Parcel parcel) {
            return new CleanType(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanType[] newArray(int i) {
            return new CleanType[i];
        }
    };
    private Map<String, CleanAppCache> appCacheItems;
    private int type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        public static final int APK = -1;
        public static final int CACHE = -3;
        public static final int LOG = -2;
        public static final int OTHERS = -5;
        public static final int REMAIN = -4;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public Map<String, CleanAppCache> getAppCacheItems() {
        return this.appCacheItems;
    }

    public CleanType() {
        this.appCacheItems = new HashMap();
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.BaseCleanItem
    public String getName() {
        switch (this.type) {
            case -5:
                return "系统盘垃圾";
            case -4:
                return "卸载残留垃圾";
            case -3:
                return "应用缓存垃圾";
            case -2:
                return "广告垃圾";
            case -1:
                return "无用的安装包";
            default:
                return "";
        }
    }

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        try {
            json.putOpt("type", Integer.valueOf(this.type));
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, CleanAppCache> entry : this.appCacheItems.entrySet()) {
                try {
                    jSONObject.putOpt(entry.getKey(), entry.getValue().toJson());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            json.putOpt("app_cache_list", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return json;
    }

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public void parse(JSONObject jSONObject) throws JSONException {
        super.parse(jSONObject);
        this.type = jSONObject.optInt("type");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("app_cache_list");
        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
            return;
        }
        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(next);
            CleanAppCache cleanAppCache = new CleanAppCache();
            cleanAppCache.parse(jSONObjectOptJSONObject2);
            this.appCacheItems.put(next, cleanAppCache);
        }
    }

    CleanType(Parcel parcel) {
        super(parcel);
        this.appCacheItems = new HashMap();
        this.type = parcel.readInt();
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.appCacheItems.put(parcel.readString(), (CleanAppCache) parcel.readParcelable(CleanAppCache.class.getClassLoader()));
        }
    }

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.type);
        parcel.writeInt(this.appCacheItems.size());
        for (Map.Entry<String, CleanAppCache> entry : this.appCacheItems.entrySet()) {
            try {
                String key = entry.getKey();
                CleanAppCache value = entry.getValue();
                parcel.writeString(key);
                parcel.writeParcelable(value, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
