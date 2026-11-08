package com.bytedance.push.g;

import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.common.push.utility.i;
import com.bytedance.common.push.utility.j;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.push.utils.RomVersionParamHelper;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final com.bytedance.push.d f4815a;

    public a(com.bytedance.push.d dVar) {
        this.f4815a = dVar;
    }

    public Map<String, String> a() {
        Map<String, String> mapA;
        HashMap map = new HashMap();
        PushSetting.getInstance().getSSIDs(map);
        HashMap map2 = new HashMap();
        map2.put("push_sdk_version", String.valueOf(30200));
        map2.put("push_sdk_version_name", "3.2.0-honor");
        String str = (String) map.get("install_id");
        if (!i.a(str)) {
            map2.put("iid", str);
        }
        String str2 = (String) map.get(com.bytedance.b.a.c.h.x);
        if (!i.a(str2)) {
            map2.put(com.bytedance.b.a.c.h.x, str2);
        }
        String strC = com.bytedance.common.push.utility.g.c(this.f4815a.f4770a);
        if (!i.a(strC)) {
            map2.put("ac", strC);
        }
        String str3 = this.f4815a.g;
        if (str3 != null) {
            map2.put("channel", str3);
        }
        map2.put("aid", String.valueOf(this.f4815a.f4771b));
        String str4 = this.f4815a.i;
        if (str4 != null) {
            map2.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, str4);
        }
        map2.put("version_code", String.valueOf(this.f4815a.f4772c));
        map2.put("version_name", this.f4815a.e);
        map2.put("update_version_code", String.valueOf(this.f4815a.d));
        map2.put("device_type", Build.MODEL);
        map2.put("device_brand", Build.BRAND);
        map2.put("device_manufacturer", Build.MANUFACTURER);
        map2.put("language", Locale.getDefault().getLanguage());
        map2.put("os_api", String.valueOf(Build.VERSION.SDK_INT));
        try {
            String strSubstring = Build.VERSION.RELEASE;
            if (strSubstring != null && strSubstring.length() > 10) {
                strSubstring = strSubstring.substring(0, 10);
            }
            map2.put("os_version", strSubstring);
        } catch (Exception unused) {
        }
        int iA = j.a(this.f4815a.f4770a);
        if (iA > 0) {
            map2.put("dpi", String.valueOf(iA));
        }
        map2.put("rom", com.ss.android.message.a.b.f());
        map2.put("os", DispatchConstants.ANDROID);
        map2.put("package", this.f4815a.f4770a.getPackageName());
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        if (timeZone != null) {
            map2.put("tz_offset", String.valueOf(timeZone.getOffset(System.currentTimeMillis()) / 1000));
            map2.put("tz_name", timeZone.getID());
        }
        String language = Locale.getDefault().getLanguage();
        if (!i.a(language)) {
            map2.put("language", language);
        }
        String country = Locale.getDefault().getCountry();
        if (!i.a(country)) {
            map2.put("region", country.toLowerCase());
        }
        try {
            map2.put("country", b());
            if (this.f4815a.r != null && (mapA = this.f4815a.r.a()) != null) {
                map2.putAll(mapA);
            }
            map2.put("rom_version", RomVersionParamHelper.a());
        } catch (Exception unused2) {
        }
        return map2;
    }

    private String b() {
        String country = Resources.getSystem().getConfiguration().locale.getCountry();
        return !TextUtils.isEmpty(country) ? country.toUpperCase() : "";
    }
}
