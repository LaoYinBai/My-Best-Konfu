package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.NoPorGuard;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashSet;
import java.util.Set;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ClientConfigManagerImpl implements d {
    private static final String TAG = "ClientConfigManager";
    private static volatile ClientConfigManagerImpl sClientConfigManagerImpl;
    private a mAppConfigSettings;
    private Context mContext;
    private e mPushConfigSettings;

    public Set<String> getBlackEventList() {
        return null;
    }

    private ClientConfigManagerImpl(Context context) {
        this.mContext = ContextDelegate.getContext(context).getApplicationContext();
        this.mAppConfigSettings = new a(this.mContext);
        this.mPushConfigSettings = new e(this.mContext);
    }

    public static synchronized ClientConfigManagerImpl getInstance(Context context) {
        if (sClientConfigManagerImpl == null) {
            sClientConfigManagerImpl = new ClientConfigManagerImpl(context);
        }
        return sClientConfigManagerImpl;
    }

    public boolean isEnablePush() {
        prepareAppConfig();
        com.vivo.push.model.a aVarC = this.mAppConfigSettings.c(this.mContext.getPackageName());
        if (aVarC != null) {
            return "1".equals(aVarC.b);
        }
        return true;
    }

    private void prepareAppConfig() {
        if (this.mAppConfigSettings == null) {
            this.mAppConfigSettings = new a(this.mContext);
        } else {
            this.mAppConfigSettings.c();
        }
    }

    public void clearPush() {
        this.mAppConfigSettings.d();
    }

    @Override // com.vivo.push.cache.d
    public boolean isInBlackList(long j) {
        String strC = preparePushConfigSettings().c("BL");
        if (!TextUtils.isEmpty(strC)) {
            for (String str : strC.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                try {
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(str) && Long.parseLong(str) == j) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNotifyStyle() {
        try {
            String strC = preparePushConfigSettings().c("DPL");
            if (!TextUtils.isEmpty(strC)) {
                try {
                    return Integer.parseInt(strC);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        return 0;
    }

    public boolean isCancleBroadcastReceiver() throws NumberFormatException {
        int i;
        String strC = preparePushConfigSettings().c("PSM");
        if (TextUtils.isEmpty(strC)) {
            i = 0;
        } else {
            try {
                i = Integer.parseInt(strC);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return (i & 4) != 0;
    }

    private e preparePushConfigSettings() {
        if (this.mPushConfigSettings == null) {
            this.mPushConfigSettings = new e(this.mContext);
        } else {
            this.mPushConfigSettings.c();
        }
        return this.mPushConfigSettings;
    }

    public String getSuitTag() {
        return preparePushConfigSettings().c("CSPT");
    }

    public boolean isDebug() {
        this.mAppConfigSettings.c();
        return a.a(this.mAppConfigSettings.b());
    }

    public boolean isDebug(int i) {
        return a.a(i);
    }

    public String getValueByKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        this.mPushConfigSettings.c();
        return this.mPushConfigSettings.c(str);
    }

    public Set<Long> getWhiteLogList() {
        HashSet hashSet = new HashSet();
        String valueByKey = getValueByKey("WLL");
        if (!TextUtils.isEmpty(valueByKey)) {
            for (String str : valueByKey.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                try {
                    hashSet.add(Long.valueOf(Long.parseLong(str)));
                } catch (Exception unused) {
                }
            }
        }
        n.d(TAG, " initWhiteLogList " + hashSet);
        return hashSet;
    }
}
