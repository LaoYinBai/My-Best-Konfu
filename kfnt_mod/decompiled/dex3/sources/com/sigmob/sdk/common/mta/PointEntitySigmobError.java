package com.sigmob.sdk.common.mta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PointEntitySigmobError extends PointEntitySigmob {
    private String b;

    public static PointEntitySigmobError SigmobError(String str, int i, String str2) {
        PointEntitySigmobError pointEntitySigmobError = new PointEntitySigmobError();
        pointEntitySigmobError.setAc_type("9");
        pointEntitySigmobError.setCategory(str);
        pointEntitySigmobError.setSub_category(String.valueOf(i));
        pointEntitySigmobError.setError_message(str2);
        return pointEntitySigmobError;
    }

    @Override // com.sigmob.sdk.common.mta.PointEntitySigmob
    public String getError_message() {
        return this.b;
    }

    @Override // com.sigmob.sdk.common.mta.PointEntitySigmob
    public void setError_message(String str) {
        this.b = str;
    }
}
