package com.mbridge.msdk.widget.custom;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.b.c;
import com.mbridge.msdk.foundation.same.b.e;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.ApkDisplayInfo;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ogg.DefaultOggSeeker;
import com.mbridge.msdk.system.NoProGuard;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CustomViewMessageWrap implements NoProGuard {
    private ApkDisplayInfo apkDisplayInfo;
    private CampaignEx campaignEx;
    private String layoutFilePath;
    private Map<String, View> views;

    public boolean checkIsNull(Object obj) {
        return obj == null;
    }

    public CustomViewMessageWrap() {
    }

    public CustomViewMessageWrap(CampaignEx campaignEx, String str) {
        this.campaignEx = campaignEx;
        this.apkDisplayInfo = campaignEx == null ? null : campaignEx.getApkDisplayInfo();
        this.layoutFilePath = str;
    }

    public CampaignEx getCampaignEx() {
        return this.campaignEx;
    }

    public View getView(String str) {
        if (this.views != null) {
            return this.views.get(str);
        }
        return null;
    }

    public void putView(String str, View view) {
        if (this.views == null) {
            this.views = new HashMap();
        }
        this.views.put(str, view);
    }

    public String getApplicationName() {
        return checkIsNull(this.apkDisplayInfo) ? "" : this.apkDisplayInfo.getAppName();
    }

    public String getApplicationNameFromCampaign() {
        return checkIsNull(this.campaignEx) ? "" : this.campaignEx.getAppName();
    }

    public String getApplicationContext() {
        return checkIsNull(this.campaignEx) ? "" : this.campaignEx.getAppDesc();
    }

    public String getApplicationPrivacy() {
        return checkIsNull(this.apkDisplayInfo) ? "" : this.apkDisplayInfo.getPriUrl();
    }

    public String getApplicationUpdateTime() {
        return checkIsNull(this.apkDisplayInfo) ? "" : this.apkDisplayInfo.getUpdateTime();
    }

    public String getApplicationPermissions() {
        if (checkIsNull(this.apkDisplayInfo)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> permDescAllList = this.apkDisplayInfo.getPermDescAllList();
        if (permDescAllList != null && permDescAllList.size() > 0) {
            for (int i = 0; i < permDescAllList.size(); i++) {
                String str = permDescAllList.get(i);
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                    if (i < permDescAllList.size() - 1) {
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    public String getApplicationDeveloper() {
        return checkIsNull(this.apkDisplayInfo) ? "" : this.apkDisplayInfo.getDevName();
    }

    public String getApplicationVersion() {
        return checkIsNull(this.apkDisplayInfo) ? "" : this.apkDisplayInfo.getAppVersion();
    }

    public String getApplicationScore() {
        String str;
        Object[] objArr;
        if (checkIsNull(this.campaignEx)) {
            str = "已有 %s 人评分";
            objArr = new Object[]{String.valueOf(new Random().nextInt(10000) + DefaultOggSeeker.MATCH_BYTE_RANGE)};
        } else {
            str = "已有 %s 人评分";
            objArr = new Object[]{String.valueOf(this.campaignEx.getNumberRating())};
        }
        return String.format(str, objArr);
    }

    public String getApplicationLogo() {
        return checkIsNull(this.campaignEx) ? "" : this.campaignEx.getIconUrl();
    }

    public String getApplicationImage() {
        return checkIsNull(this.campaignEx) ? "" : this.campaignEx.getImageUrl();
    }

    public int getStarCount() {
        if (checkIsNull(this.campaignEx)) {
            return 5;
        }
        return (int) Math.ceil(this.campaignEx.getRating());
    }

    public String getLayoutFilePath() {
        return this.layoutFilePath;
    }

    public static final String getMessage(CustomViewMessageWrap customViewMessageWrap, String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(str) && customViewMessageWrap == null) {
            return null;
        }
        try {
            Method method = customViewMessageWrap.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            Object objInvoke = method.invoke(customViewMessageWrap, new Object[0]);
            return objInvoke != null ? String.valueOf(objInvoke) : "";
        } catch (IllegalAccessException e) {
            s.d("CustomViewMessageWrap", e.getMessage());
            return "";
        } catch (NoSuchMethodException e2) {
            s.d("CustomViewMessageWrap", e2.getMessage());
            return "";
        } catch (InvocationTargetException e3) {
            s.d("CustomViewMessageWrap", e3.getMessage());
            return "";
        }
    }

    public static final String getViewFilePath(String str) {
        if (!TextUtils.equals(str, "mbridge_download_dialog_view")) {
            return "";
        }
        return e.b(c.MBRIDGE_OTHER) + "/" + str + ".xml";
    }

    public static final void copyViewFile(Context context, String str) throws Throwable {
        copyAssetsDir2Phone(context, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void copyAssetsDir2Phone(android.content.Context r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.widget.custom.CustomViewMessageWrap.copyAssetsDir2Phone(android.content.Context, java.lang.String):void");
    }
}
