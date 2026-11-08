package com.mbridge.msdk.mbjscommon.authority.jscommon;

import android.text.TextUtils;
import android.util.Base64;
import com.mbridge.msdk.foundation.controller.authoritycontroller.a;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.h;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PrivateAuthorityJSBridge extends AbsMbridgeDownload {
    private static final String TAG = "PrivateAuthorityJSBridge";

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
    }

    public void getPrivateAuthorityStatus(Object obj, String str) throws JSONException {
        String strC = a.a().c();
        s.a(TAG, "GET authorityStatusString:" + strC);
        h.a().a(obj, TextUtils.isEmpty(strC) ? "" : Base64.encodeToString(strC.getBytes(), 2));
    }

    public void setPrivateAuthorityStatus(Object obj, String str) {
        s.a(TAG, "SET authorityStatusString:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            try {
                a.a().b(str);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } finally {
            finishActivity(obj);
        }
    }

    private void finishActivity(Object obj) {
        s.a(TAG, "close activity" + this.mContext);
    }
}
