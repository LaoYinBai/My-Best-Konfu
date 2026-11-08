package com.umeng.commonsdk.config;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static final long a = 1000;
    public static final String b = "_LAST_FIELD";
    public static final String c = "1073741823";
    public static final String d = "536870911";
    public static final String e = "262143";
    public static final String f = "2047";
    public static final int g = 64;
    private static Map<String, String[]> l = new HashMap();
    public static String[] h = new String[a.values().length];
    public static String[] i = new String[b.values().length];
    public static String[] j = new String[c.values().length];
    public static String[] k = new String[EnumC0139d.values().length];

    public enum a {
        header_utoken,
        header_cpu,
        header_mccmnc,
        header_sub_os_name,
        header_sub_os_version,
        header_device_type,
        header_device_id_imei,
        header_device_id_mac,
        header_device_id_android_id,
        header_device_id_serialNo,
        header_bulid,
        header_os_version,
        header_resolution,
        header_mc,
        header_timezone,
        header_local_info,
        header_carrier,
        header_access,
        header_tracking_imei,
        header_tracking_android_id,
        header_tracking_utdid,
        header_tracking_idmd5,
        header_tracking_idfa,
        header_tracking_mac,
        header_tracking_serial,
        header_tracking_uuid,
        header_tracking_uop,
        header_tracking_oldumid,
        header_tracking_newumid,
        header_tracking_umtt,
        _LAST_FIELD
    }

    public enum b {
        inner_rs,
        inner_by,
        inner_gp,
        inner_to,
        inner_mo,
        inner_ca,
        inner_fl,
        inner_gdf_r,
        inner_thm_z,
        inner_dsk_s,
        inner_sd,
        inner_build,
        inner_sr,
        inner_scr,
        inner_sinfo,
        inner_winfo,
        inner_input,
        inner_bt,
        inner_mem,
        inner_cpu,
        inner_rom,
        inner_bstn,
        inner_cam,
        inner_appls,
        inner_lbs,
        internal_run_server,
        internal_imsi,
        internal_meid,
        tp_tp,
        _LAST_FIELD
    }

    public enum c {
        push_cpu,
        push_imei,
        push_mac,
        push_android_id,
        push_serialNo,
        push_settings_android_id,
        push_network_access_mode,
        push_on_line,
        push_time_zone,
        push_locale_info,
        push_resolution,
        push_operator,
        push_utdid,
        push_service_work,
        push_service_name,
        push_intent_exist,
        push_data_data,
        push_notification_enabled,
        _LAST_FIELD
    }

    /* renamed from: com.umeng.commonsdk.config.d$d, reason: collision with other inner class name */
    public enum EnumC0139d {
        share_device_id,
        share_imsi,
        share_iccid,
        share_sn,
        share_net_accmode,
        share_android_id,
        share_wifi_mac,
        share_sd_size,
        share_ssid,
        share_resolution,
        share_conn_type,
        _LAST_FIELD
    }

    static {
        if (h != null && h.length > 0) {
            for (int i2 = 0; i2 < a.values().length; i2++) {
                h[i2] = a.values()[i2].toString();
            }
            if (l != null) {
                l.put(a.class.getName(), h);
            }
        }
        if (i != null && i.length > 0) {
            for (int i3 = 0; i3 < b.values().length; i3++) {
                i[i3] = b.values()[i3].toString();
            }
            if (l != null) {
                l.put(b.class.getName(), i);
            }
        }
        if (j != null && j.length > 0) {
            for (int i4 = 0; i4 < c.values().length; i4++) {
                j[i4] = c.values()[i4].toString();
            }
            if (l != null) {
                l.put(c.class.getName(), j);
            }
        }
        if (k == null || k.length <= 0) {
            return;
        }
        for (int i5 = 0; i5 < EnumC0139d.values().length; i5++) {
            k[i5] = EnumC0139d.values()[i5].toString();
        }
        if (l != null) {
            l.put(EnumC0139d.class.getName(), k);
        }
    }

    public static boolean a(String str) {
        return (str == null || str.length() <= 0 || b.equalsIgnoreCase(str)) ? false : true;
    }

    public static String[] b(String str) {
        if (str == null || str.length() <= 0 || !l.containsKey(str)) {
            return null;
        }
        return l.get(str);
    }
}
