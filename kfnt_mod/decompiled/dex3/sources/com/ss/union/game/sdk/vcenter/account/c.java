package com.ss.union.game.sdk.vcenter.account;

import com.bytedance.applog.AppLog;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.vcenter.b.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public static a.C0106a a() {
        a.C0106a c0106a = new a.C0106a();
        c0106a.c(AppLog.getDid());
        c0106a.d(GlobalApplicationUtils.getContext().getPackageName());
        if (LGAccountDataUtil.getCurrentUserLocal() == null) {
            return c0106a.a(false);
        }
        String str = LGAccountDataUtil.getCurrentUserLocal().galaxy_open_id;
        a.C0106a c0106aB = c0106a.a(true).b(LGAccountDataUtil.isVisitor());
        if (str == null) {
            str = "";
        }
        return c0106aB.a(str);
    }
}
