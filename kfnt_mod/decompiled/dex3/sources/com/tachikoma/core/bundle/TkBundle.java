package com.tachikoma.core.bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TkBundle {
    private String bundleId;
    private String filePath;
    private String script;
    private long taskId = -1;
    private String version;
    private int versionCode;

    public String getBundleId() {
        return this.bundleId;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getScript() {
        return this.script;
    }

    public long getTaskId() {
        return this.taskId;
    }

    public String getVersion() {
        return this.version;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setBundleId(String str) {
        this.bundleId = str;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setScript(String str) {
        this.script = str;
    }

    public void setTaskId(long j) {
        this.taskId = j;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }
}
