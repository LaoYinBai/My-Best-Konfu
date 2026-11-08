package com.ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanFile extends BaseCleanItem implements Parcelable {
    public static final String CLEAN_FILE = "clean_file";
    public static final Parcelable.Creator<CleanFile> CREATOR = new Parcelable.Creator<CleanFile>() { // from class: com.ss.android.download.api.clean.CleanFile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanFile createFromParcel(Parcel parcel) {
            return new CleanFile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanFile[] newArray(int i) {
            return new CleanFile[i];
        }
    };
    private String classifyType;
    private String folderName;
    private boolean isEmptyFolder;
    private String parentFolderPath;

    @Override // com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getParentFolderPath() {
        return this.parentFolderPath;
    }

    public void setParentFolderPath(String str) {
        this.parentFolderPath = str;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String str) {
        this.folderName = str;
    }

    public boolean isEmptyFolder() {
        return this.isEmptyFolder;
    }

    public void setEmptyFolder(boolean z) {
        this.isEmptyFolder = z;
    }

    public String getClassifyType() {
        return this.classifyType;
    }

    public CleanFile() {
        this.classifyType = CLEAN_FILE;
    }

    @Override // com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        try {
            json.putOpt("parent_folder_path", this.parentFolderPath);
            json.putOpt("folder_name", this.folderName);
            json.putOpt("is_empty_folder", Boolean.valueOf(this.isEmptyFolder));
            json.putOpt("classify_type", getClassifyType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override // com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        this.parentFolderPath = jSONObject.optString("parent_folder_path");
        this.folderName = jSONObject.optString("folder_name");
        this.isEmptyFolder = jSONObject.optBoolean("is_empty_folder");
        this.classifyType = jSONObject.optString("classify_type");
    }

    @Override // com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.parentFolderPath);
        parcel.writeString(this.folderName);
        parcel.writeInt(this.isEmptyFolder ? 1 : 0);
        parcel.writeString(this.classifyType);
    }

    CleanFile(Parcel parcel) {
        this.classifyType = CLEAN_FILE;
        this.parentFolderPath = parcel.readString();
        this.folderName = parcel.readString();
        this.isEmptyFolder = parcel.readInt() == 1;
        this.classifyType = parcel.readString();
    }
}
