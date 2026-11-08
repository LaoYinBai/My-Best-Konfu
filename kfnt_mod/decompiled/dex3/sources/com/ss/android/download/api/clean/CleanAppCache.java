package com.ss.android.download.api.clean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.ss.android.download.api.utils.ToolUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanAppCache extends BaseCleanGroup implements Parcelable {
    public static final String CLEAN_APP_CACHE = "clean_app_cache";
    public static final Parcelable.Creator<CleanAppCache> CREATOR = new Parcelable.Creator<CleanAppCache>() { // from class: com.ss.android.download.api.clean.CleanAppCache.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanAppCache createFromParcel(Parcel parcel) {
            return new CleanAppCache(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanAppCache[] newArray(int i) {
            return new CleanAppCache[i];
        }
    };
    private String packageName;

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile
    public String getClassifyType() {
        return CLEAN_APP_CACHE;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public CleanAppCache() {
    }

    CleanAppCache(Parcel parcel) {
        this.packageName = parcel.readString();
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public Drawable getIcon(@Nullable Context context) {
        return ToolUtils.getIconByPackageName(context, this.packageName);
    }

    public String getName(@Nullable Context context) {
        String nameByPackageName = ToolUtils.getNameByPackageName(context, this.packageName);
        return !TextUtils.isEmpty(nameByPackageName) ? nameByPackageName : super.getName();
    }

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        try {
            json.putOpt("package_name", this.packageName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public void parse(JSONObject jSONObject) throws JSONException {
        super.parse(jSONObject);
        this.packageName = jSONObject.optString("package_name");
    }

    @Override // com.ss.android.download.api.clean.BaseCleanGroup, com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
    }
}
