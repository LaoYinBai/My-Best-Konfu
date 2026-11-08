package com.huawei.hms.support.api.client;

import com.huawei.hms.core.aidl.d;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface AidlApiClient extends ApiClient {
    List<String> getApiNameList();

    d getService();
}
