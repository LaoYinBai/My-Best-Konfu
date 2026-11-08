package com.bytedance.mobile.cpsdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CPConfig {
    public String mAppID;
    public String mChannel;
    public String mDeviceID;
    public List<Long> mScenes;
    public String mSessionID;

    public static class Builder extends CPConfig {
        public Builder() {
            super();
        }

        public Builder addScene(CPScenes cPScenes) {
            this.mScenes.add(Long.valueOf(cPScenes.getScene()));
            return this;
        }

        public CPConfig build() {
            if (this.mAppID != null) {
                return this;
            }
            throw new IllegalArgumentException("appID must be set!");
        }

        public Builder setAppID(String str) {
            this.mAppID = str;
            return this;
        }

        public Builder setChannel(String str) {
            this.mChannel = str;
            return this;
        }

        public Builder setDeviceID(String str) {
            this.mDeviceID = str;
            return this;
        }

        public Builder setSecssionID(String str) {
            this.mSessionID = str;
            return this;
        }
    }

    public enum CPScenes {
        kCPSceneTypeGeneralPublicChat(1),
        kCPSceneTypeGeneralGroupChat(2),
        kCPSceneTypeGeneralPrivateChat(3),
        kCPSceneTypeRoleName(4);

        private long mScene;

        CPScenes(int i) {
            this.mScene = i;
        }

        public final long getScene() {
            return this.mScene;
        }
    }

    public enum CPVerrificationResult {
        CPVerificationResultPass(0),
        CPVerificationResultReject(1),
        CPVerificationResultSelf(2);

        private int mResult;

        CPVerrificationResult(int i) {
            this.mResult = i;
        }

        public final int getResult() {
            return this.mResult;
        }
    }

    public enum ContentType {
        kCPContentTypeText(1),
        kCPContentTypeAudio(2),
        kCPContentTypeImage(3),
        kCPContentTypeVideo(4);

        private long mContentType;

        ContentType(long j) {
            this.mContentType = j;
        }

        public final long getContentType() {
            return this.mContentType;
        }
    }

    private CPConfig() {
        this.mAppID = "";
        this.mChannel = "";
        this.mDeviceID = "";
        this.mSessionID = "";
        this.mScenes = new ArrayList();
    }

    public String[] toNativeValue() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(wrap(this.mAppID));
        arrayList.add(wrap(this.mChannel));
        arrayList.add(wrap(this.mDeviceID));
        arrayList.add(wrap(this.mSessionID));
        Iterator<Long> it = this.mScenes.iterator();
        while (it.hasNext()) {
            arrayList.add(wrap(it.next().intValue()));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    String wrap(int i) {
        return i == 0 ? "" : String.valueOf(i);
    }

    String wrap(String str) {
        return str == null ? "" : str.trim();
    }
}
