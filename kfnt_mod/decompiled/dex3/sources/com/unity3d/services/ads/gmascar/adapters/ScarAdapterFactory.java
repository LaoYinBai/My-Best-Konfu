package com.unity3d.services.ads.gmascar.adapters;

import com.unity3d.scar.adapter.common.IAdsErrorHandler;
import com.unity3d.scar.adapter.common.IScarAdapter;
import com.unity3d.scar.adapter.v2000.ScarAdapter;
import com.unity3d.services.core.log.DeviceLog;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ScarAdapterFactory {
    public static final int CODE_19_2 = 201604000;
    public static final int CODE_19_5 = 203404000;
    public static final int CODE_19_7 = 204204000;
    public static final int CODE_20_0 = 210402000;

    public IScarAdapter createScarAdapter(long j, IAdsErrorHandler iAdsErrorHandler) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (j >= 210402000) {
            return new ScarAdapter(iAdsErrorHandler);
        }
        if (j >= 203404000 && j <= 204204000) {
            return new com.unity3d.scar.adapter.v1950.ScarAdapter(iAdsErrorHandler);
        }
        if (j >= 201604000) {
            return new com.unity3d.scar.adapter.v1920.ScarAdapter(iAdsErrorHandler);
        }
        DeviceLog.debug("SCAR version %s is not supported.", Long.valueOf(j));
        return null;
    }
}
