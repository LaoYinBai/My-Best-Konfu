package com.mbridge.msdk.foundation.download;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum DownloadResourceType {
    DOWNLOAD_RESOURCE_TYPE_VIDEO(0),
    DOWNLOAD_RESOURCE_TYPE_ZIP(1),
    DOWNLOAD_RESOURCE_TYPE_IMAGE(2),
    DOWNLOAD_RESOURCE_TYPE_HTML(3),
    DOWNLOAD_RESOURCE_TYPE_OTHER(4),
    DOWNLOAD_RESOURCE_TYPE_APK(5);

    public int resourceType;

    DownloadResourceType(int i) {
        this.resourceType = i;
    }

    public static DownloadResourceType getDownloadResourceType(int i) {
        switch (i) {
            case 0:
                return DOWNLOAD_RESOURCE_TYPE_VIDEO;
            case 1:
                return DOWNLOAD_RESOURCE_TYPE_ZIP;
            case 2:
                return DOWNLOAD_RESOURCE_TYPE_IMAGE;
            case 3:
                return DOWNLOAD_RESOURCE_TYPE_HTML;
            case 4:
                return DOWNLOAD_RESOURCE_TYPE_OTHER;
            case 5:
                return DOWNLOAD_RESOURCE_TYPE_APK;
            default:
                return null;
        }
    }
}
