package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.io.File;
import java.security.NoSuchAlgorithmException;

/* compiled from: DomainSameSDKTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    public static boolean a = false;

    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r12, final java.lang.String r13, final com.mbridge.msdk.foundation.entity.CampaignEx r14, final boolean r15) throws java.security.NoSuchAlgorithmException {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.tools.f.a(java.lang.String, java.lang.String, com.mbridge.msdk.foundation.entity.CampaignEx, boolean):void");
    }

    private static void a(final CampaignEx campaignEx, final String str, final boolean z) throws NoSuchAlgorithmException {
        String akdlui;
        if (campaignEx == null || TextUtils.isEmpty(campaignEx.getAkdlui())) {
            akdlui = str;
        } else {
            akdlui = campaignEx.getAkdlui();
            if (TextUtils.isEmpty(akdlui)) {
                akdlui = SameMD5.getMD5(str);
            }
        }
        try {
            a(str, 2, campaignEx);
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (!aa.b(contextJ)) {
                com.mbridge.msdk.click.c.a(contextJ, str, akdlui);
                return;
            }
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "isDowning", Long.valueOf(System.currentTimeMillis()));
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "process", Integer.valueOf(Process.myPid()));
            new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.f.2
                @Override // java.lang.Runnable
                public final void run() throws NoSuchAlgorithmException {
                    f.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, z);
                }
            }).start();
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    public static void a(final Context context, final CampaignEx campaignEx, final String str, final boolean z) throws NoSuchAlgorithmException {
        String md5;
        if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getAkdlui())) {
            md5 = campaignEx.getAkdlui();
        } else {
            md5 = SameMD5.getMD5(str);
        }
        try {
            final String str2 = md5;
            com.mbridge.msdk.optimize.a.a(q.a("/apk", context, new boolean[1]), com.mbridge.msdk.click.c.a(str), str, new com.mbridge.msdk.optimize.b() { // from class: com.mbridge.msdk.foundation.tools.f.3
                @Override // com.mbridge.msdk.optimize.b
                public final void a(int i) {
                }

                @Override // com.mbridge.msdk.optimize.b
                public final void a() {
                    if (z) {
                        com.mbridge.msdk.click.b.a(campaignEx, context, "start");
                    } else {
                        com.mbridge.msdk.click.b.a(campaignEx, context, "shortcuts_start");
                    }
                }

                @Override // com.mbridge.msdk.optimize.b
                public final void a(File file) {
                    y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "isDowning", 0L);
                    y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "process", 0);
                    com.mbridge.msdk.click.b.a(campaignEx, context, TtmlNode.END);
                    com.mbridge.msdk.foundation.db.g.b(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(campaignEx);
                    if (file.exists() && z) {
                        com.mbridge.msdk.click.c.a(context, Uri.fromFile(file), str, str2);
                        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2, file.getAbsolutePath());
                    } else {
                        if (z) {
                            return;
                        }
                        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2, file.getAbsolutePath());
                    }
                }

                @Override // com.mbridge.msdk.optimize.b
                public final void b() {
                    com.mbridge.msdk.click.c.a(context, str, str2);
                }
            });
        } catch (Throwable unused) {
            com.mbridge.msdk.click.c.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, md5);
        }
    }

    public static void a(String str, int i, CampaignEx campaignEx) throws NoSuchAlgorithmException {
        String md5 = "";
        if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getAkdlui())) {
            md5 = campaignEx.getAkdlui();
        } else if (TextUtils.isEmpty("")) {
            md5 = SameMD5.getMD5(str);
        }
        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "downloadType", Integer.valueOf(i));
        if (campaignEx != null) {
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "linkType", Integer.valueOf(campaignEx.getLinkType()));
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "rid", campaignEx.getRequestId());
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "rid_n", campaignEx.getRequestIdNotice());
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + BidResponsedEx.KEY_CID, campaignEx.getId());
            return;
        }
        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "linkType", -1);
        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "rid", "");
        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + "rid_n", "");
        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), md5 + BidResponsedEx.KEY_CID, "");
    }
}
