package com.ss.android.downloadlib.utils;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.OpenAppResult;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OpenAppUtils {
    public static OpenAppResult tryOpenMarket(Context context, Uri uri) {
        return InnerOpenAppUtils.tryOpenMarket(context, uri);
    }

    public static OpenAppResult tryOpenMarket(Context context, String str) {
        return InnerOpenAppUtils.tryOpenMarket(context, str);
    }

    public static OpenAppResult tryOpenMarket(Context context, ModelBox modelBox, String str) {
        return InnerOpenAppUtils.tryOpenMarket(context, modelBox, str);
    }

    public static OpenAppResult tryOpenByPackage(String str) {
        return tryOpenByPackage(GlobalInfo.getContext(), str);
    }

    public static OpenAppResult tryOpenByPackage(Context context, String str) {
        return InnerOpenAppUtils.tryOpenByPackage(context, str);
    }

    public static OpenAppResult tryOpenByPackage(Context context, String str, InnerUnifyData innerUnifyData) {
        return InnerOpenAppUtils.tryOpenByPackage(context, str, innerUnifyData);
    }

    public static OpenAppResult tryOpenByPackage(String str, InnerUnifyData innerUnifyData) {
        return tryOpenByPackage(GlobalInfo.getContext(), str, innerUnifyData);
    }

    public static OpenAppResult tryOpenByUrl(String str) {
        return tryOpenByUrl(GlobalInfo.getContext(), str);
    }

    public static OpenAppResult tryOpenByUrl(Context context, String str) {
        return InnerOpenAppUtils.tryOpenByUrl(context, str);
    }

    public static OpenAppResult tryOpenByUrl(String str, @NonNull InnerUnifyData innerUnifyData) {
        return InnerOpenAppUtils.tryOpenByUrl(str, innerUnifyData);
    }

    public static OpenAppResult tryNewOpenByUrlOrPackage(@NonNull NativeDownloadModel nativeDownloadModel, String str, String str2) {
        return InnerOpenAppUtils.tryNewOpenByUrlOrPackage(nativeDownloadModel, str, str2);
    }

    public static boolean tryOpenByQuickAppUrl(Context context, String str) {
        return InnerOpenAppUtils.tryOpenByQuickAppUrl(context, str);
    }
}
