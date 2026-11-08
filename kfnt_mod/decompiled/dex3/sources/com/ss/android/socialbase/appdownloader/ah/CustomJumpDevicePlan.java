package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.taobao.accs.common.Constants;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CustomJumpDevicePlan extends AbsDevicePlan {
    private final JSONObject mAntiConfig;

    public CustomJumpDevicePlan(Context context, DownloadSetting downloadSetting, String str, JSONObject jSONObject) {
        super(context, downloadSetting, str);
        this.mAntiConfig = jSONObject;
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        String strOptString = this.mAntiConfig.optString("action");
        String strOptString2 = this.mAntiConfig.optString("category");
        int iOptInt = this.mAntiConfig.optInt(Constants.KEY_FLAGS, 1342210048);
        String strOptString3 = this.mAntiConfig.optString("path_extra_key");
        String strOptString4 = this.mAntiConfig.optString("path_data_key");
        JSONObject jSONObjectOptJSONObject = this.mAntiConfig.optJSONObject("extra");
        JSONObject jSONObjectOptJSONObject2 = this.mAntiConfig.optJSONObject("extra_type");
        if (TextUtils.isEmpty(strOptString)) {
            return null;
        }
        Intent intent = new Intent(strOptString);
        if (!TextUtils.isEmpty(strOptString2)) {
            intent.addCategory(strOptString2);
        }
        if (!TextUtils.isEmpty(strOptString4)) {
            try {
                intent.setData(Uri.parse(String.format(strOptString4, this.mJumpPath)));
            } catch (Throwable unused) {
            }
        }
        intent.setFlags(iOptInt);
        if (!TextUtils.isEmpty(strOptString3)) {
            intent.putExtra(strOptString3, this.mJumpPath);
        }
        putExtra(intent, jSONObjectOptJSONObject, jSONObjectOptJSONObject2);
        return intent;
    }

    private static void putExtra(@NonNull Intent intent, JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> itKeys;
        if (jSONObject == null || jSONObject2 == null || jSONObject.length() != jSONObject2.length() || intent == null || (itKeys = jSONObject.keys()) == null) {
            return;
        }
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String strOptString = jSONObject2.optString(next);
            if (strOptString != null) {
                parseValueAndPut(jSONObject, next, strOptString, intent);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseValueAndPut(org.json.JSONObject r2, java.lang.String r3, java.lang.String r4, android.content.Intent r5) {
        /*
            int r0 = r4.hashCode()
            switch(r0) {
                case -1325958191: goto L30;
                case -891985903: goto L26;
                case 104431: goto L1c;
                case 3327612: goto L12;
                case 64711720: goto L8;
                default: goto L7;
            }
        L7:
            goto L3a
        L8:
            java.lang.String r0 = "boolean"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L3a
            r4 = 1
            goto L3b
        L12:
            java.lang.String r0 = "long"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L3a
            r4 = 2
            goto L3b
        L1c:
            java.lang.String r0 = "int"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L3a
            r4 = 0
            goto L3b
        L26:
            java.lang.String r0 = "string"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L3a
            r4 = 4
            goto L3b
        L30:
            java.lang.String r0 = "double"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L3a
            r4 = 3
            goto L3b
        L3a:
            r4 = -1
        L3b:
            switch(r4) {
                case 0: goto L5f;
                case 1: goto L57;
                case 2: goto L4f;
                case 3: goto L47;
                case 4: goto L3f;
                default: goto L3e;
            }
        L3e:
            goto L66
        L3f:
            java.lang.String r2 = r2.optString(r3)
            r5.putExtra(r3, r2)
            goto L66
        L47:
            double r0 = r2.optDouble(r3)
            r5.putExtra(r3, r0)
            goto L66
        L4f:
            long r0 = r2.optLong(r3)
            r5.putExtra(r3, r0)
            goto L66
        L57:
            boolean r2 = r2.optBoolean(r3)
            r5.putExtra(r3, r2)
            goto L66
        L5f:
            int r2 = r2.optInt(r3)
            r5.putExtra(r3, r2)
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.ah.CustomJumpDevicePlan.parseValueAndPut(org.json.JSONObject, java.lang.String, java.lang.String, android.content.Intent):void");
    }
}
