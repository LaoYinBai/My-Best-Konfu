package anet.channel.strategy.dispatch;

import android.os.Build;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {
    public static final String TAG = "amdc.DispatchParamBuilder";

    e() {
    }

    public static Map a(Map<String, Object> map) {
        IAmdcSign iAmdcSignB = a.b();
        if (iAmdcSignB == null || TextUtils.isEmpty(iAmdcSignB.getAppkey())) {
            ALog.e(TAG, "amdc sign is null or appkey is empty", null, new Object[0]);
            return null;
        }
        NetworkStatusHelper.NetworkStatus status = NetworkStatusHelper.getStatus();
        if (!NetworkStatusHelper.isConnected()) {
            ALog.e(TAG, "no network, don't send amdc request", null, new Object[0]);
            return null;
        }
        map.put("appkey", iAmdcSignB.getAppkey());
        map.put(DispatchConstants.VERSION, DispatchConstants.VER_CODE);
        map.put("platform", DispatchConstants.ANDROID);
        map.put(DispatchConstants.PLATFORM_VERSION, Build.VERSION.RELEASE);
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUserId())) {
            map.put("sid", GlobalAppRuntimeInfo.getUserId());
        }
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUtdid())) {
            map.put(DispatchConstants.DEVICEID, GlobalAppRuntimeInfo.getUtdid());
        }
        map.put("netType", status.toString());
        if (status.isWifi()) {
            map.put(DispatchConstants.BSSID, NetworkStatusHelper.getWifiBSSID());
        }
        map.put(DispatchConstants.CARRIER, NetworkStatusHelper.getCarrier());
        map.put(DispatchConstants.MNC, NetworkStatusHelper.getSimOp());
        map.put(DispatchConstants.LATITUDE, String.valueOf(a.f548a));
        map.put(DispatchConstants.LONGTITUDE, String.valueOf(a.f549b));
        map.putAll(a.c());
        map.put("channel", a.f550c);
        map.put(DispatchConstants.APP_NAME, a.d);
        map.put(DispatchConstants.APP_VERSION, a.e);
        map.put(DispatchConstants.STACK_TYPE, Integer.toString(a()));
        map.put(DispatchConstants.DOMAIN, b(map));
        map.put(DispatchConstants.SIGNTYPE, iAmdcSignB.useSecurityGuard() ? "sec" : "noSec");
        map.put(DispatchConstants.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        String strA = a(iAmdcSignB, map);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        map.put(DispatchConstants.SIGN, strA);
        return map;
    }

    private static int a() {
        switch (anet.channel.util.c.c()) {
        }
        return 4;
    }

    private static String b(Map map) {
        Set set = (Set) map.remove(DispatchConstants.HOSTS);
        StringBuilder sb = new StringBuilder();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    static String a(IAmdcSign iAmdcSign, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(anet.channel.strategy.utils.c.d(map.get("appkey")));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.DOMAIN)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.APP_NAME)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.APP_VERSION)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.BSSID)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get("channel")));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.DEVICEID)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.LATITUDE)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.LONGTITUDE)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.MACHINE)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get("netType")));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.OTHER)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get("platform")));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.PLATFORM_VERSION)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.PRE_IP)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get("sid")));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.TIMESTAMP)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.VERSION)));
        sb.append("&");
        sb.append(anet.channel.strategy.utils.c.d(map.get(DispatchConstants.SIGNTYPE)));
        try {
            return iAmdcSign.sign(sb.toString());
        } catch (Exception e) {
            ALog.e(TAG, "get sign failed", null, e, new Object[0]);
            return null;
        }
    }
}
