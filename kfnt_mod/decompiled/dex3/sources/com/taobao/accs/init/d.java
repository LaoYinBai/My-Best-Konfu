package com.taobao.accs.init;

import android.text.TextUtils;
import com.alibaba.analytics.utils.AppInfoUtil;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.utl.ALog;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class d implements IAppReceiver {
    d() {
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onUnbindUser(int i) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("Launcher_InitAccs", "onUnbindUser, errorCode:" + i, new Object[0]);
        }
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onUnbindApp(int i) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("Launcher_InitAccs", "onUnbindApp,  errorCode:" + i, new Object[0]);
        }
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onSendData(String str, int i) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("Launcher_InitAccs", "onSendData,  dataId:" + str + " errorCode:" + i, new Object[0]);
        }
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onData(String str, String str2, byte[] bArr) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            StringBuilder sb = new StringBuilder();
            sb.append("onData,  userId:");
            sb.append(str);
            sb.append("dataId:");
            sb.append(str2);
            sb.append(" dataLen:");
            sb.append(bArr == null ? 0 : bArr.length);
            ALog.d("Launcher_InitAccs", sb.toString(), new Object[0]);
        }
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onBindUser(String str, int i) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("Launcher_InitAccs", "onBindUser, userId:" + str + " errorCode:" + i, new Object[0]);
        }
        if (i == 300) {
            ACCSManager.bindApp(Launcher_InitAccs.mContext, AppInfoUtil.getAppkey(), Launcher_InitAccs.mTtid, null);
        }
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onBindApp(int i) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("Launcher_InitAccs", "onBindApp,  errorCode:" + i, new Object[0]);
        }
        if (i == 200) {
            if (!TextUtils.isEmpty(Launcher_InitAccs.mUserId)) {
                ACCSManager.bindUser(Launcher_InitAccs.mContext, Launcher_InitAccs.mUserId, Launcher_InitAccs.mForceBindUser);
                Launcher_InitAccs.mForceBindUser = false;
            } else if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d("Launcher_InitAccs", "onBindApp,  bindUser userid :" + Launcher_InitAccs.mUserId, new Object[0]);
            }
        }
    }

    @Override // com.taobao.accs.IAppReceiver
    public String getService(String str) {
        return Launcher_InitAccs.SERVICES.get(str);
    }

    @Override // com.taobao.accs.IAppReceiver
    public Map<String, String> getAllServices() {
        return Launcher_InitAccs.SERVICES;
    }
}
