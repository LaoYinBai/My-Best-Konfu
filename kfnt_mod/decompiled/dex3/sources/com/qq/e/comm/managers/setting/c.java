package com.qq.e.comm.managers.setting;

import com.qq.e.comm.constants.Constants;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends e {
    public c() throws JSONException {
        a();
    }

    private void a() throws JSONException {
        a(Constants.KEYS.SDKServerGetADReportSamplingRate, 1);
        a(Constants.KEYS.SDKServerExpReportSamplingRate, 1);
        a(Constants.KEYS.SDKServerClickReportSamplingRate, 100);
        a(Constants.KEYS.RequireWindowFocus, 1);
        a(Constants.KEYS.SHOW_LOGO, 1);
        a(Constants.KEYS.INNER_BROWSER_SCHEME, "weixin,tel,openapp.jdmobile");
        a(Constants.KEYS.FLOW_CONTROL, 1);
        a(Constants.KEYS.GDT_SDK_IDENTITY, 1);
        a(Constants.KEYS.GDT_SDK_CHANNEL, 1);
        a(Constants.KEYS.GDT_SDK_EX1, "");
        a(Constants.KEYS.GDT_SDK_EX2, "");
        a(Constants.KEYS.Banner_RF, 30000);
        a(Constants.KEYS.SPLASH_LOADTIMEOUT, 3000);
        a(Constants.KEYS.SPLASH_EXPOSURE_TIME, 5000);
        a(Constants.KEYS.SPLASH_NETWORK_PERMISION, 26);
        a(Constants.KEYS.SPLASH_MAX_REQUEST_NUM, 100);
        a(Constants.KEYS.FORCE_EXPOSURE, 1);
    }
}
