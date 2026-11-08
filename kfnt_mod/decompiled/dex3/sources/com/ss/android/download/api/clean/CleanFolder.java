package com.ss.android.download.api.clean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanFolder extends CleanFile implements Parcelable {
    public static final String CLEAN_FOLDER = "clean_folder";
    public static final Parcelable.Creator<CleanFolder> CREATOR = new Parcelable.Creator<CleanFolder>() { // from class: com.ss.android.download.api.clean.CleanFolder.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanFolder createFromParcel(Parcel parcel) {
            return new CleanFolder(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleanFolder[] newArray(int i) {
            return new CleanFolder[i];
        }
    };
    private List<CleanFile> cleanFiles;
    private Map<String, CleanFile> cleanFilesMap;
    private String folderName;
    private boolean isExpand;
    private BaseCleanGroup parent;

    @Override // com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.ss.android.download.api.clean.CleanFile
    public String getClassifyType() {
        return CLEAN_FOLDER;
    }

    @Override // com.ss.android.download.api.clean.CleanFile
    public String getFolderName() {
        return TextUtils.isEmpty(this.folderName) ? "其他文件" : this.folderName;
    }

    @Override // com.ss.android.download.api.clean.CleanFile
    public void setFolderName(String str) {
        this.folderName = str;
    }

    public List<CleanFile> getCleanFiles() {
        return this.cleanFiles;
    }

    public CleanFolder() {
        this.cleanFiles = new ArrayList();
        this.cleanFilesMap = new HashMap();
    }

    @Override // com.ss.android.download.api.clean.BaseCleanItem
    public String getName() {
        String name = super.getName();
        return TextUtils.isEmpty(name) ? "其他文件" : name;
    }

    public boolean addCleanFile(CleanFile cleanFile) {
        if (cleanFile == null || this.cleanFilesMap.containsKey(cleanFile.absolutePath.toLowerCase())) {
            return false;
        }
        this.cleanFilesMap.put(cleanFile.absolutePath.toLowerCase(), cleanFile);
        this.cleanFiles.add(cleanFile);
        this.size += cleanFile.size;
        return true;
    }

    public void removeCleanFile(CleanFile cleanFile, boolean z) {
        if (cleanFile == null) {
            return;
        }
        if (z) {
            this.cleanFiles.remove(cleanFile);
        }
        this.cleanFilesMap.remove(cleanFile.absolutePath.toLowerCase());
        this.size -= cleanFile.size;
    }

    public BaseCleanGroup getParent() {
        return this.parent;
    }

    public void setParent(BaseCleanGroup baseCleanGroup) {
        this.parent = baseCleanGroup;
    }

    @Override // com.ss.android.download.api.clean.BaseCleanItem
    public void setCheck(boolean z) {
        setCheck(z, true);
    }

    void setCheck(boolean z, boolean z2) {
        this.isCheck = z;
        if (getParent() == null || !z2) {
            return;
        }
        getParent().checkSelectedStatus(z);
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    public void setExpand(boolean z) {
        this.isExpand = z;
    }

    @Override // com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        try {
            json.put("folder_name", this.folderName);
            json.put("is_expand", this.isExpand);
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, CleanFile> entry : this.cleanFilesMap.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue().toJson());
            }
            json.put("clean_file_list", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override // com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public void parse(JSONObject jSONObject) {
        CleanFile cleanFile;
        if (jSONObject != null) {
            super.parse(jSONObject);
            this.folderName = jSONObject.optString("folder_name");
            this.isExpand = jSONObject.optBoolean("is_expand");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("clean_file_list");
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(next);
                try {
                    String strOptString = jSONObjectOptJSONObject2.optString("classify_type");
                    if (ApkCleanFile.APK_CLEAN_FILE.equals(strOptString)) {
                        cleanFile = new ApkCleanFile();
                    } else if (CleanAppCache.CLEAN_APP_CACHE.equals(strOptString)) {
                        cleanFile = new CleanAppCache();
                    } else if (CLEAN_FOLDER.equals(strOptString)) {
                        cleanFile = new CleanFolder();
                    } else {
                        cleanFile = new CleanFile();
                    }
                    cleanFile.parse(jSONObjectOptJSONObject2);
                    if ((cleanFile instanceof CleanFolder) && (this instanceof BaseCleanGroup)) {
                        ((CleanFolder) cleanFile).setParent((BaseCleanGroup) this);
                    }
                    this.cleanFiles.add(cleanFile);
                    this.cleanFilesMap.put(next, cleanFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.folderName);
        parcel.writeInt(this.isExpand ? 1 : 0);
        parcel.writeInt(this.cleanFilesMap.size());
        for (Map.Entry<String, CleanFile> entry : this.cleanFilesMap.entrySet()) {
            String key = entry.getKey();
            CleanFile value = entry.getValue();
            parcel.writeString(key);
            parcel.writeString(value.getClassifyType());
            parcel.writeParcelable(value, 0);
        }
    }

    CleanFolder(Parcel parcel) {
        Parcelable parcelable;
        super(parcel);
        this.cleanFiles = new ArrayList();
        this.cleanFilesMap = new HashMap();
        this.folderName = parcel.readString();
        this.isExpand = parcel.readInt() == 1;
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            String string = parcel.readString();
            String string2 = parcel.readString();
            if (ApkCleanFile.APK_CLEAN_FILE.equals(string2)) {
                parcelable = parcel.readParcelable(ApkCleanFile.class.getClassLoader());
            } else if (CleanAppCache.CLEAN_APP_CACHE.equals(string2)) {
                parcelable = parcel.readParcelable(CleanAppCache.class.getClassLoader());
            } else if (CLEAN_FOLDER.equals(string2)) {
                parcelable = parcel.readParcelable(CleanFolder.class.getClassLoader());
            } else {
                parcelable = parcel.readParcelable(CleanFile.class.getClassLoader());
            }
            this.cleanFilesMap.put(string, (CleanFile) parcelable);
        }
    }
}
