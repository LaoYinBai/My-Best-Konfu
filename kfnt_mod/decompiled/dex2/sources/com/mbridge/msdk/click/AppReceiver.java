package com.mbridge.msdk.click;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.widget.dialog.MBAlertDialog;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AppReceiver extends BroadcastReceiver {
    private static final String a = "AppReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        if (!TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED") || intent.getData() == null) {
            return;
        }
        try {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            CampaignEx campaignExJ = com.mbridge.msdk.foundation.db.g.b(i.a(context)).j(schemeSpecificPart);
            com.mbridge.msdk.foundation.db.e eVarA = com.mbridge.msdk.foundation.db.e.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            int iJ = eVarA.j(schemeSpecificPart);
            String strL = eVarA.l(schemeSpecificPart);
            String strD = eVarA.d(strL);
            String strE = eVarA.e(strL);
            String strG = eVarA.g(strL);
            String strF = eVarA.f(strL);
            String strK = eVarA.k(schemeSpecificPart);
            try {
                try {
                    Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
                    cls.getMethod("installed", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), strL);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e2) {
                    e2.printStackTrace();
                }
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
            switch (iJ) {
                case 0:
                    c.f(com.mbridge.msdk.foundation.controller.a.f().j(), schemeSpecificPart);
                    break;
                case 1:
                    Context contextC = com.mbridge.msdk.foundation.controller.a.f().c();
                    if (contextC instanceof Activity) {
                        MBAlertDialog mBAlertDialog = new MBAlertDialog(contextC, new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.click.a.1
                            final /* synthetic */ String a;
                            final /* synthetic */ Context b;
                            final /* synthetic */ String c;
                            final /* synthetic */ String d;
                            final /* synthetic */ String e;
                            final /* synthetic */ String f;
                            final /* synthetic */ String g;

                            AnonymousClass1(String schemeSpecificPart2, Context contextC2, String strL2, String strD2, String strE2, String strG2, String strF2) {
                                str = schemeSpecificPart2;
                                context = contextC2;
                                str = strL2;
                                str = strD2;
                                str = strE2;
                                str = strG2;
                                str = strF2;
                            }

                            @Override // com.mbridge.msdk.widget.dialog.a
                            public final void a() {
                                c.f(com.mbridge.msdk.foundation.controller.a.f().j(), str);
                                com.mbridge.msdk.foundation.same.report.d.a(context, str, str, str, 1, str, str, str);
                            }

                            @Override // com.mbridge.msdk.widget.dialog.a
                            public final void b() {
                                com.mbridge.msdk.foundation.same.report.d.a(context, str, str, str, 0, str, str, str);
                            }

                            @Override // com.mbridge.msdk.widget.dialog.a
                            public final void c() {
                                a();
                            }
                        });
                        mBAlertDialog.makeInstallAlert(strK);
                        mBAlertDialog.show();
                        break;
                    }
                    break;
            }
            if (campaignExJ != null) {
                b.b(campaignExJ, context, "install");
            }
            y.a(context, schemeSpecificPart2, schemeSpecificPart2 + "downloadType", schemeSpecificPart2 + "linkType", schemeSpecificPart2 + "rid", schemeSpecificPart2 + BidResponsedEx.KEY_CID, schemeSpecificPart2 + "isDowning", schemeSpecificPart2 + "process");
        } catch (Exception e5) {
            s.a(a, e5.getMessage());
        }
    }
}
