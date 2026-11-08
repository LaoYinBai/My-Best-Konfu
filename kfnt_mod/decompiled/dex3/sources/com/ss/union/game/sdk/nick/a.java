package com.ss.union.game.sdk.nick;

import android.text.TextUtils;
import com.ss.union.game.sdk.account.callback.INickNameUpdate;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final int a = 4;
    private static final String b = "NickName";

    private a() {
    }

    public static void a(String str) {
        com.ss.union.game.sdk.c.a.f(str);
    }

    public static void a(int i, com.ss.union.game.sdk.nick.a.a aVar) {
        UpdateNickNameFragment.a(i, aVar);
    }

    public static void a(int i, INickNameUpdate iNickNameUpdate) {
        UpdateNickNameFragment.a(i, iNickNameUpdate);
    }

    public static boolean a(User user) {
        if (b(user) && a()) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("curUser is null ? : ");
        sb.append(user == null);
        a(sb.toString());
        if (user != null) {
            a("curUser is isFirstLogin  : " + user.isFirstLogin);
        }
        return false;
    }

    private static boolean b(User user) {
        boolean z = (user != null && (user.isFirstLogin || TextUtils.isEmpty(user.nick_name))) && !(user != null && LGAccountConstant.LoginType.LOGIN_TYPE_DY.getName().equals(user.login_type));
        a("shouldShowNickNameDialogByCheckUserInfo result = " + z);
        return z;
    }

    private static boolean a() {
        boolean zIsUseNickNameSystem = ConfigManager.LoginConfig.isUseNickNameSystem();
        com.ss.union.game.sdk.c.a.e("isRequestNickNameApi = " + zIsUseNickNameSystem);
        return zIsUseNickNameSystem;
    }
}
