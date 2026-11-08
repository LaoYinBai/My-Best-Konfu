package com.mbridge.msdk.mbnative.e;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: NativeReportUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    public static synchronized void a(CampaignEx campaignEx, Context context, String str, com.mbridge.msdk.mbnative.d.a aVar) {
        if (!campaignEx.isReport()) {
            campaignEx.setReport(true);
            a(campaignEx, context, str);
            c(campaignEx, context, str);
            List<String> listA = a(campaignEx);
            if (listA != null && listA.size() != 0) {
                for (int i = 0; i < listA.size(); i++) {
                    String str2 = listA.get(i);
                    if (!TextUtils.isEmpty(str2)) {
                        com.mbridge.msdk.click.b.a(context, campaignEx, str, str2, false, false);
                    }
                }
            }
            b(campaignEx, context, str);
        }
        if (aVar != null && !campaignEx.isCallBackImpression()) {
            campaignEx.setCallBackImpression(true);
            aVar.onLoggingImpression(campaignEx.getType());
        }
    }

    private static synchronized void a(final CampaignEx campaignEx, final Context context, String str) {
        com.mbridge.msdk.foundation.controller.a.f().a(context);
        if (!TextUtils.isEmpty(campaignEx.getImpressionURL())) {
            new Thread(new Runnable() { // from class: com.mbridge.msdk.mbnative.e.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        m.a(i.a(context)).b(campaignEx.getId());
                    } catch (Exception unused) {
                        s.d("NativeReportUtils", "campain can't insert db");
                    }
                }
            }).start();
            com.mbridge.msdk.click.b.a(context, campaignEx, str, campaignEx.getImpressionURL(), false, true);
        }
        if (!TextUtils.isEmpty(str) && campaignEx.getNativeVideoTracking() != null && campaignEx.getNativeVideoTracking().n() != null) {
            com.mbridge.msdk.click.b.a(context, campaignEx, str, campaignEx.getNativeVideoTracking().n(), false, false);
        }
    }

    private static synchronized void b(CampaignEx campaignEx, Context context, String str) {
        if (campaignEx != null) {
            try {
                List<String> pv_urls = campaignEx.getPv_urls();
                if (pv_urls != null && pv_urls.size() > 0) {
                    Iterator<String> it = pv_urls.iterator();
                    while (it.hasNext()) {
                        com.mbridge.msdk.click.b.a(context, campaignEx, str, it.next(), false, true);
                    }
                }
            } catch (Throwable th) {
                s.a("NativeReportUtils", th.getMessage());
            }
        }
    }

    private static synchronized void c(CampaignEx campaignEx, Context context, String str) {
        if (!TextUtils.isEmpty(campaignEx.getOnlyImpressionURL()) && com.mbridge.msdk.mbnative.c.b.c != null && !com.mbridge.msdk.mbnative.c.b.c.containsKey(campaignEx.getOnlyImpressionURL())) {
            com.mbridge.msdk.mbnative.c.b.c.put(campaignEx.getOnlyImpressionURL(), Long.valueOf(System.currentTimeMillis()));
            com.mbridge.msdk.click.b.a(context, campaignEx, str, campaignEx.getOnlyImpressionURL(), false, true);
        }
    }

    private static List<String> a(CampaignEx campaignEx) {
        ArrayList arrayList = new ArrayList();
        if (campaignEx == null) {
            return arrayList;
        }
        String ad_url_list = campaignEx.getAd_url_list();
        if (TextUtils.isEmpty(ad_url_list)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(ad_url_list);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add((String) jSONArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
