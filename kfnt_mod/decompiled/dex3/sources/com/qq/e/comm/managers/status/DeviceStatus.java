package com.qq.e.comm.managers.status;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.managers.setting.GlobalSetting;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import com.ss.android.socialbase.downloader.segment.Segment;
import com.umeng.message.MsgConstant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DeviceStatus {
    private String a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean j;
    private AtomicInteger k;
    private Context l;
    public final String model = Build.MODEL;

    public DeviceStatus(Context context) {
        this.l = context.getApplicationContext();
        a(context);
    }

    private int a(float f, int i) {
        return (this.l.getApplicationInfo().flags & 8192) != 0 ? (int) (i / f) : i;
    }

    private void a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.d = getVersion() > 3 ? displayMetrics.densityDpi : 120;
        this.b = getVersion() > 3 ? a(displayMetrics.density, displayMetrics.widthPixels) : displayMetrics.widthPixels;
        this.c = getVersion() > 3 ? a(displayMetrics.density, displayMetrics.heightPixels) : displayMetrics.heightPixels;
    }

    public Carrier getCarrier() {
        String operator = getOperator();
        if (operator != null) {
            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007") || operator.equals("46020")) {
                return Carrier.CMCC;
            }
            if (operator.equals("46001") || operator.equals("46006")) {
                return Carrier.UNICOM;
            }
            if (operator.equals("46003") || operator.equals("46005")) {
                return Carrier.TELECOM;
            }
        }
        return Carrier.UNKNOWN;
    }

    public String getDataNet() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) this.l.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return null;
        }
        int type = activeNetworkInfo.getType();
        String str = type != 0 ? type != 1 ? "unknow" : "wi" : "ed";
        this.h = str;
        return str;
    }

    public int getDeviceDensity() {
        return this.d;
    }

    public int getDeviceHeight() {
        return this.c;
    }

    public String getDeviceId() {
        if (!GlobalSetting.isAgreePrivacyStrategy() || !GlobalSetting.isAgreeReadDeviceId()) {
            return "";
        }
        String str = this.i;
        if (str != null) {
            return str;
        }
        if (GDTADManager.getInstance().getSM().getInteger("imeion", 1) != 1 || !hasReadPhoneStatePermission()) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.l.getSystemService("phone");
            String deviceId = telephonyManager == null ? null : telephonyManager.getDeviceId();
            this.i = deviceId;
            if (deviceId == null) {
                deviceId = "";
            }
            this.i = deviceId;
        } catch (Exception e) {
            GDTLogger.d("Get imei encounter error: " + e.getMessage());
        }
        return this.i;
    }

    public String getDeviceIdMD5() {
        String deviceId = getDeviceId();
        return TextUtils.isEmpty(deviceId) ? "" : Md5Util.encode(deviceId.toLowerCase());
    }

    public int getDeviceWidth() {
        return this.b;
    }

    public Map<String, String> getLacAndCeilId() {
        int networkId;
        GsmCellLocation gsmCellLocation;
        String operator = getOperator();
        HashMap map = new HashMap();
        if (GDTADManager.getInstance().getSM().getInteger("cellon", 1) == 1 && this.l.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && !StringUtil.isEmpty(operator) && !"null".equalsIgnoreCase(operator)) {
            int cid = 0;
            try {
                if (Integer.parseInt(operator.substring(0, 3)) == 460) {
                    TelephonyManager telephonyManager = (TelephonyManager) this.l.getSystemService("phone");
                    CellLocation cellLocation = telephonyManager.getCellLocation();
                    if (cellLocation instanceof CdmaCellLocation) {
                        CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                        networkId = cdmaCellLocation.getNetworkId();
                        cid = cdmaCellLocation.getBaseStationId();
                    } else if (!(cellLocation instanceof GsmCellLocation) || (gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation()) == null) {
                        networkId = 0;
                    } else {
                        int lac = gsmCellLocation.getLac();
                        cid = gsmCellLocation.getCid();
                        networkId = lac;
                    }
                    map.put("lac", networkId + "");
                    map.put("cellid", "" + cid);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return map;
    }

    public String getLanguage() {
        if (this.a == null) {
            String lowerCase = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
            this.a = lowerCase;
            if (lowerCase.length() == 0) {
                this.a = Segment.JsonKey.END;
            }
        }
        return this.a;
    }

    public NetworkType getNetworkType() throws NumberFormatException {
        int i;
        String dataNet = getDataNet();
        if (dataNet != null && dataNet.equals("wi")) {
            return NetworkType.WIFI;
        }
        try {
            i = Integer.parseInt(getPhoneNet());
        } catch (NumberFormatException unused) {
            i = 0;
        }
        switch (i) {
            case 1:
            case 2:
            case 16:
                return NetworkType.NET_2G;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 17:
                return NetworkType.NET_3G;
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
                return NetworkType.NET_4G;
            default:
                return NetworkType.UNKNOWN;
        }
    }

    public String getOS() {
        return "android";
    }

    public String getOperator() {
        try {
            this.f = ((TelephonyManager) this.l.getSystemService("phone")).getNetworkOperator();
        } catch (Exception unused) {
        }
        return this.f;
    }

    public String getPhoneNet() {
        Context appContext = GDTADManager.getInstance().getAppContext();
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) appContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                int networkType = ((TelephonyManager) appContext.getSystemService("phone")).getNetworkType();
                if (networkType == 0) {
                    networkType = activeNetworkInfo.getSubtype();
                }
                this.g = "" + networkType;
            }
        } catch (Exception unused) {
        }
        return this.g;
    }

    public String getScreenOrientation() {
        this.e = this.l.getResources().getConfiguration().orientation == 2 ? Constants.LANDSCAPE : "p";
        return this.e;
    }

    public int getVersion() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Exception unused) {
            return 3;
        }
    }

    public boolean hasReadPhoneStatePermission() {
        if (Build.VERSION.SDK_INT < 23) {
            this.j = true;
            return true;
        }
        if (this.k == null) {
            this.k = new AtomicInteger(GDTADManager.getInstance().getSM().getInteger("rpspc", Integer.MAX_VALUE));
        }
        if (this.k.getAndDecrement() > 0) {
            this.j = this.l.checkSelfPermission(MsgConstant.PERMISSION_READ_PHONE_STATE) == 0;
        }
        return this.j;
    }
}
