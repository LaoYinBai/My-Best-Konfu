package com.sigmob.sdk.common.mta;

import android.location.Location;
import android.text.TextUtils;
import android.util.Base64;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.a;
import com.sigmob.sdk.common.e.f;
import com.ss.android.downloadlib.constants.DownloadConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PointEntityCommon extends PointEntitySuper {
    public String getBattery_level() {
        return String.format("%.2f", a.n().F());
    }

    public String getBattery_save_enabled() {
        return String.valueOf(a.n().H());
    }

    public String getBattery_state() {
        return String.valueOf(a.n().G());
    }

    public String getBrowser() {
        return f.c();
    }

    public String getCarrier() {
        return String.valueOf(a.n().T());
    }

    public String getCheight() {
        return String.valueOf(a.n().J());
    }

    public String getClientpixel() {
        return String.format("%sx%s", Integer.valueOf(a.n().ai().widthPixels), Integer.valueOf(a.n().ai().heightPixels));
    }

    public String getClienttype() {
        return a.e();
    }

    public String getCwidth() {
        return String.valueOf(a.n().K());
    }

    public String getDevice_type() {
        return a.n().E() ? "5" : DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR;
    }

    public String getDheight() {
        return String.valueOf(a.n().M());
    }

    public String getDwidth() {
        return String.valueOf(a.n().N());
    }

    public String getGameversion() {
        return a.n().R();
    }

    public String getIsEmulator() {
        return a.p() ? "1" : Constants.FAIL;
    }

    public String getLat() {
        Location locationAn = a.n().an();
        if (locationAn != null) {
            return String.valueOf(locationAn.getLatitude());
        }
        return null;
    }

    public String getLng() {
        Location locationAn = a.n().an();
        if (locationAn != null) {
            return String.valueOf(locationAn.getLongitude());
        }
        return null;
    }

    public String getOs() {
        return "2";
    }

    public String getPkgname() {
        return a.n().P();
    }

    public String getResolution() {
        return String.format("%sx%s", Integer.valueOf(a.n().aa().widthPixels), Integer.valueOf(a.n().aa().heightPixels));
    }

    public String getScreenangle() {
        return String.valueOf(Math.abs(a.n().Q().intValue() - 1) * 90);
    }

    public String getScreendensity() {
        return String.valueOf(a.n().I());
    }

    public String getTargetSdkVersion() {
        return a.n().O();
    }

    public String getUdid() {
        return a.n().t();
    }

    public String getWifi_id() {
        String strV = a.n().V();
        return TextUtils.isEmpty(strV) ? strV : Base64.encodeToString(a.n().V().getBytes(), 2);
    }

    public String getWifi_mac() {
        return a.n().U();
    }
}
