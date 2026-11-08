package com.mbridge.msdk.c;

import android.content.Context;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.w;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ShortCutsDataManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static int a = 3;
    public static int b = 1;
    public static int c = 4;
    public static int d = 5;
    private static String e = "ShortCutsDataManager";
    private com.mbridge.msdk.click.b f;

    /* compiled from: ShortCutsDataManager.java */
    /* renamed from: com.mbridge.msdk.c.a$a, reason: collision with other inner class name */
    private static class C0106a {
        private static a a = new a();
    }

    private a() {
    }

    public static a a() {
        return C0106a.a;
    }

    private void a(Context context, CampaignEx campaignEx, int i) {
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        if (this.f == null) {
            this.f = new com.mbridge.msdk.click.b(context, aVarB.g());
        }
        if (i == 1) {
            this.f.c(campaignEx);
        } else if (i == 2) {
            this.f.a(campaignEx);
        }
    }

    static /* synthetic */ List a(a aVar, Context context, List list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            CampaignEx campaignEx = (CampaignEx) list.get(i);
            if (campaignEx != null) {
                boolean zC = w.c(context, campaignEx.getPackageName());
                if (campaignEx.getWtick() == 1 || !zC) {
                    arrayList.add(campaignEx);
                    if (campaignEx != null && campaignEx.getLinkType() == 3) {
                        switch (campaignEx.getJmPd()) {
                            case 0:
                                break;
                            case 1:
                                aVar.a(context, campaignEx, 2);
                                break;
                            default:
                                if (o.C(context) == 9) {
                                    aVar.a(context, campaignEx, 2);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
