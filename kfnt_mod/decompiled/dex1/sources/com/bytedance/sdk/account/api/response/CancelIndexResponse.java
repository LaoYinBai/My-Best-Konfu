package com.bytedance.sdk.account.api.response;

import androidx.annotation.Nullable;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CancelIndexResponse extends BaseApiResponse {

    @Nullable
    public Boolean auditStatus;
    public List<Conditions> conditionsList;
    public boolean hasMobile;
    public boolean identified;
    public int protocol;
    public JSONObject rawData;

    public static class Conditions {
        public String text;
    }

    public CancelIndexResponse(boolean z, int i) {
        super(z, i);
    }
}
