package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.content.res.Resources;
import com.kwad.v8.Platform;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* compiled from: DomainSameTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    public static void a(Context context, CampaignEx campaignEx) {
        s.b("DomainSameTool", "updateInstallList 开始 更新本机已安装广告列表");
        if (context == null) {
            s.b("DomainSameTool", "updateInstallList 列表为空 不做更新本机已安装广告列表");
            return;
        }
        com.mbridge.msdk.foundation.db.m mVarA = com.mbridge.msdk.foundation.db.m.a(com.mbridge.msdk.foundation.db.i.a(context));
        boolean z = false;
        if (campaignEx != null) {
            if (w.c(context, campaignEx.getPackageName())) {
                if (com.mbridge.msdk.foundation.controller.a.e() != null) {
                    com.mbridge.msdk.foundation.controller.a.e().add(new com.mbridge.msdk.foundation.entity.j(campaignEx.getId(), campaignEx.getPackageName()));
                }
                z = true;
            } else if (mVarA != null && !mVarA.a(campaignEx.getId())) {
                com.mbridge.msdk.foundation.entity.i iVar = new com.mbridge.msdk.foundation.entity.i();
                iVar.a(campaignEx.getId());
                iVar.a(campaignEx.getFca());
                iVar.b(campaignEx.getFcb());
                iVar.d(0);
                iVar.c(0);
                iVar.a(System.currentTimeMillis());
                mVarA.a(iVar);
            }
        }
        if (z) {
            s.b("DomainSameTool", "更新安装列表");
            com.mbridge.msdk.foundation.controller.a.f().h();
        }
    }

    public static String a(int i) {
        try {
            JSONArray jSONArray = new JSONArray();
            List<Long> listI = com.mbridge.msdk.foundation.controller.a.f().i();
            if (listI != null && listI.size() > 0) {
                int size = listI.size();
                for (int i2 = (size <= i || i == 0) ? 0 : size - i; i2 < size; i2++) {
                    jSONArray.put(listI.get(i2));
                }
            }
            return jSONArray.length() > 0 ? w.a(jSONArray) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a(List<CampaignEx> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            CampaignEx campaignEx = list.get(i);
            if (!w.c(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx.getPackageName()) && campaignEx.getOfferType() == 99) {
                arrayList.add(campaignEx);
            }
        }
        com.mbridge.msdk.foundation.db.q.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(arrayList);
    }

    public static boolean a(Context context) {
        Throwable th;
        boolean z;
        if (context == null) {
            return false;
        }
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", Platform.ANDROID);
            z = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
                if ("1".equals(str)) {
                    return false;
                }
                if (PushConstants.PUSH_TYPE_NOTIFY.equals(str)) {
                    return true;
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                s.b("DomainSameTool", th.getMessage(), th);
                return z;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }
}
