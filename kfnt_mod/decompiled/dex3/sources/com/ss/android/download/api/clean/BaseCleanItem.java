package com.ss.android.download.api.clean;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseCleanItem implements Parcelable, IJsonable {
    public static final Parcelable.Creator<BaseCleanItem> CREATOR = new Parcelable.Creator<BaseCleanItem>() { // from class: com.ss.android.download.api.clean.BaseCleanItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseCleanItem createFromParcel(Parcel parcel) {
            return new BaseCleanItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseCleanItem[] newArray(int i) {
            return new BaseCleanItem[i];
        }
    };
    protected String absolutePath;
    private Drawable icon;
    boolean isCheck;
    private String name;
    protected long size;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseCleanItem() {
    }

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public void setAbsolutePath(String str) {
        this.absolutePath = str;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getName() {
        return this.name;
    }

    public String getSizeStr() {
        return getSizeStr(this.size);
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setDrawable(Drawable drawable) {
        this.icon = drawable;
    }

    private String getSizeStr(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j >= DownloadConstants.GB) {
            return decimalFormat.format(j / 1.0737418E9f) + "GB";
        }
        if (j >= 1048576) {
            return decimalFormat.format(j / 1048576.0f) + "MB";
        }
        if (j >= DownloadConstants.KB) {
            return decimalFormat.format(j / 1024.0f) + "KB";
        }
        return j + "B";
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("absolute_path", this.absolutePath);
            jSONObject.putOpt("size", Long.valueOf(this.size));
            jSONObject.putOpt("name", this.name);
            jSONObject.putOpt("is_check", Boolean.valueOf(this.isCheck));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.absolutePath = jSONObject.optString("absolute_path");
            this.size = jSONObject.optLong("size");
            this.name = jSONObject.optString("name");
            this.isCheck = jSONObject.optBoolean("is_check");
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.absolutePath);
        parcel.writeLong(this.size);
        parcel.writeString(this.name);
        parcel.writeInt(this.isCheck ? 1 : 0);
    }

    BaseCleanItem(Parcel parcel) {
        this.absolutePath = parcel.readString();
        this.size = parcel.readLong();
        this.name = parcel.readString();
        this.isCheck = parcel.readInt() == 1;
    }
}
