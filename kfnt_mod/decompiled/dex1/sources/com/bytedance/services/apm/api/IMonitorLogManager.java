package com.bytedance.services.apm.api;

import com.bytedance.news.common.service.manager.IService;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IMonitorLogManager extends IService {
    void deleteLegacyLogByIds(String str, String str2);

    void getLegacyLog(long j, long j2, String str, j jVar);

    List<JSONObject> getRecentUiActionRecords();
}
