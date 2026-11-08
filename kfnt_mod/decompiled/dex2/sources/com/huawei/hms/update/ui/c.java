package com.huawei.hms.update.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends b {
    @Override // com.huawei.hms.update.ui.b
    public AlertDialog a() {
        ProgressDialog progressDialog = new ProgressDialog(f(), g());
        progressDialog.setMessage(ResourceLoaderUtil.getString("hms_checking"));
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
