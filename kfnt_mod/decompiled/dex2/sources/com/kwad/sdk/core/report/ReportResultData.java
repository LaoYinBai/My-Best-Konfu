package com.kwad.sdk.core.report;

import com.kwad.sdk.core.network.BaseResultData;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ReportResultData extends BaseResultData implements com.kwad.sdk.core.b {
    private static final int CODE_RESULT_CHEATING_FLOW = 110009;

    public boolean isCheatingFlow() {
        return this.result == CODE_RESULT_CHEATING_FLOW;
    }
}
