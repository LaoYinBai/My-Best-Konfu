package com.kwad.sdk.core.c;

import android.content.Context;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.request.model.TaskStat;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.t;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static volatile a a;
    private static SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd");

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private static String a(String str) {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences("ksadsdk_local_ad_task_info", 0).getString(str, null);
    }

    private static void a(String str, String str2) {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        context.getSharedPreferences("ksadsdk_local_ad_task_info", 0).edit().putString(str, str2).apply();
    }

    public static List<TaskStat> b() throws JSONException {
        if (KsAdSDKImpl.get().getContext() == null) {
            return null;
        }
        d();
        String strA = a("ksadsdk_local_ad_task_info_adstyle_data");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(strA);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                TaskStat taskStat = new TaskStat();
                taskStat.parseJson(jSONObject);
                arrayList.add(taskStat);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    private static void b(AdTemplate adTemplate) throws JSONException {
        TaskStat taskStat;
        int iW = d.w(adTemplate);
        int iT = d.t(adTemplate);
        List listB = b();
        if (listB != null && listB.size() != 0) {
            boolean z = false;
            Iterator it = listB.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TaskStat taskStat2 = (TaskStat) it.next();
                if (taskStat2.adStyle == iT && taskStat2.taskType == iW) {
                    taskStat2.count++;
                    z = true;
                    break;
                }
            }
            if (!z) {
                taskStat = new TaskStat(iT, iW, 1);
            }
            a("ksadsdk_local_ad_task_info_adstyle_data", t.b(listB).toString());
        }
        listB = new ArrayList();
        taskStat = new TaskStat(iT, iW, 1);
        listB.add(taskStat);
        a("ksadsdk_local_ad_task_info_adstyle_data", t.b(listB).toString());
    }

    public static void c() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        context.getSharedPreferences("ksadsdk_local_ad_task_info", 0).edit().clear().apply();
    }

    private static void d() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        long j = context.getSharedPreferences("ksadsdk_local_ad_task_info", 0).getLong("ksadsdk_local_ad_task_info_date", -1L);
        if (j > 0 && !b.format(new Date(j)).equals(b.format(new Date()))) {
            c();
        }
    }

    public void a(AdTemplate adTemplate) throws JSONException {
        d();
        b(adTemplate);
    }
}
