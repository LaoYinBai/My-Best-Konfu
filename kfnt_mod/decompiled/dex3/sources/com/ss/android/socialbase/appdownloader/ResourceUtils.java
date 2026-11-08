package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ResourceUtils {
    public static int getLayoutId(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "layout", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getStringId(Context context, String str) {
        try {
            return context.getResources().getIdentifier(str, PushMultiProcessSharedProvider.STRING_TYPE, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getStringId(String str) {
        return getStringId(DownloadComponentManager.getAppContext(), str);
    }

    public static int getDrawableId(String str) {
        try {
            return getDrawableId(str, DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getDrawableId(String str, String str2) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "drawable", str2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getStyleId(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "style", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getId(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, AdDownloadModel.JsonKey.ID, DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getColorId(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "color", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getAttrId(Context context, String str) {
        try {
            getAttrId(str, context.getPackageName());
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getAttrId(String str, String str2) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "attr", str2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getArrayId(String str) {
        try {
            return DownloadComponentManager.getAppContext().getResources().getIdentifier(str, "array", DownloadComponentManager.getAppContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
