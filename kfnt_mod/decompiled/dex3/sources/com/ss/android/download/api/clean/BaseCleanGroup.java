package com.ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseCleanGroup extends CleanFolder implements Parcelable, IJsonable {
    public static final Parcelable.Creator<BaseCleanGroup> CREATOR = new Parcelable.Creator<BaseCleanGroup>() { // from class: com.ss.android.download.api.clean.BaseCleanGroup.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseCleanGroup createFromParcel(Parcel parcel) {
            return new BaseCleanGroup(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseCleanGroup[] newArray(int i) {
            return new BaseCleanGroup[i];
        }
    };
    List<CleanFolder> cleanFolders;

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<CleanFolder> getCleanFolders() {
        return this.cleanFolders;
    }

    public BaseCleanGroup() {
        this.cleanFolders = new ArrayList();
    }

    @Override // com.ss.android.download.api.clean.CleanFolder
    void setCheck(boolean z, boolean z2) {
        Iterator<CleanFolder> it = this.cleanFolders.iterator();
        while (it.hasNext()) {
            it.next().setCheck(z, false);
        }
        super.setCheck(z, z2);
    }

    void checkSelectedStatus(boolean z) {
        boolean z2 = z != this.isCheck;
        if (!z) {
            this.isCheck = false;
        } else {
            this.isCheck = true;
            Iterator<CleanFolder> it = this.cleanFolders.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!it.next().isCheck()) {
                    this.isCheck = false;
                    break;
                }
            }
        }
        if (!z2 || getParent() == null) {
            return;
        }
        getParent().checkSelectedStatus(this.isCheck);
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        JSONArray jSONArray = new JSONArray();
        Iterator<CleanFolder> it = this.cleanFolders.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        try {
            json.putOpt("clean_folders", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public void parse(JSONObject jSONObject) throws JSONException {
        CleanFolder cleanFolder;
        if (jSONObject != null) {
            super.parse(jSONObject);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("clean_folders");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    String strOptString = jSONObject2.optString("classify_type");
                    if (ApkCleanFile.APK_CLEAN_FILE.equals(strOptString)) {
                        cleanFolder = new ApkCleanFile();
                    } else if (CleanAppCache.CLEAN_APP_CACHE.equals(strOptString)) {
                        cleanFolder = new CleanAppCache();
                    } else {
                        cleanFolder = new CleanFolder();
                    }
                    cleanFolder.parse(jSONObject2);
                    cleanFolder.setParent(this);
                    this.cleanFolders.add(cleanFolder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BaseCleanGroup(Parcel parcel) {
        super(parcel);
        this.cleanFolders = new ArrayList();
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.cleanFolders.add((CleanFolder) parcel.readParcelable(CleanFolder.class.getClassLoader()));
        }
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (this.cleanFolders == null) {
            this.cleanFolders = new ArrayList();
        }
        parcel.writeInt(this.cleanFolders.size());
        Iterator<CleanFolder> it = this.cleanFolders.iterator();
        while (it.hasNext()) {
            parcel.writeParcelable(it.next(), 0);
        }
    }
}
