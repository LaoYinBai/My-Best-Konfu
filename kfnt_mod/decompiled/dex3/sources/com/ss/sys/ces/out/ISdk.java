package com.ss.sys.ces.out;

import android.content.Context;
import com.ss.sys.ces.utils.NetInterface;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ISdk {
    public static final int REGION_DEBUG = 4;
    public static final int REGION_INTERNEL = 0;
    public static final int REGION_SINGAPOER = 2;
    public static final int REGION_TEST = 1;
    public static final int REGION_UNSET = 255;
    public static final int REGION_USA_EAST = 3;
    public static final int URL_TYPE_MAINDOMAIN = 1025;
    public static final int URL_TYPE_PODS = 1027;
    public static final int URL_TYPE_SELAS = 1026;

    void InitSelas();

    void SetRegionType(int i);

    String debugEntry(Context context, int i);

    byte[] encode(byte[] bArr);

    String onEvent();

    String pullSg();

    String pullVer(String str);

    void reportNow(String str);

    void setCustomInfo(HashMap<String, Object> map);

    void setEfficientDebug(boolean z);

    void setInterface(NetInterface netInterface);

    void setNetwork(boolean z);

    void setParams(String str, String str2);

    void setParams(String str, String str2, boolean z);

    void setSdkExpandInstance(ISdkEx iSdkEx);

    void setSession(String str);

    void setUrlInterface(int i, String str);
}
