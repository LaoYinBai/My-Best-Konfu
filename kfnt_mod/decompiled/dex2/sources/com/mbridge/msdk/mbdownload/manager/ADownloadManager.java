package com.mbridge.msdk.mbdownload.manager;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.e;
import com.mbridge.msdk.foundation.db.g;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.mbdownload.DownloadNetWorkChangeReceiver;
import com.mbridge.msdk.mbdownload.f;
import com.mbridge.msdk.out.IDownloadListener;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ADownloadManager {
    private ConcurrentHashMap<String, com.mbridge.msdk.mbdownload.manager.a> apkConcurrentHashMap;
    private ConcurrentHashMap<IDownloadListener, b> listenerConcurrentHashMap;
    private ConcurrentHashMap<b, String> listenerUniqueKeyConcurrentHashMap;

    private ADownloadManager() {
        this.apkConcurrentHashMap = new ConcurrentHashMap<>();
        this.listenerConcurrentHashMap = new ConcurrentHashMap<>();
        this.listenerUniqueKeyConcurrentHashMap = new ConcurrentHashMap<>();
        register();
    }

    private void register() {
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ != null) {
            DownloadNetWorkChangeReceiver downloadNetWorkChangeReceiver = new DownloadNetWorkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            contextJ.registerReceiver(downloadNetWorkChangeReceiver, intentFilter);
        }
    }

    private static class a {
        private static final ADownloadManager a = new ADownloadManager();
    }

    public static ADownloadManager getInstance() {
        return a.a;
    }

    public String initAPKDownloadTask(CampaignEx campaignEx, String str, IDownloadListener iDownloadListener) {
        String taskUniqueKey = getTaskUniqueKey(campaignEx, str);
        if (!TextUtils.isEmpty(taskUniqueKey)) {
            e eVarA = e.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            int iC = eVarA.c(taskUniqueKey);
            if (iC == 1 || iC == 10 || iC == 11) {
                com.mbridge.msdk.click.c.a(com.mbridge.msdk.foundation.controller.a.f().j(), Uri.fromFile(new File(eVarA.i(taskUniqueKey))), str, taskUniqueKey);
                return taskUniqueKey;
            }
            if (iC == 0) {
                campaignEx.setAkdlui(taskUniqueKey);
                eVarA.a(campaignEx);
                g.b(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(campaignEx);
            }
            com.mbridge.msdk.mbdownload.manager.a aVar = null;
            if (!this.apkConcurrentHashMap.containsKey(taskUniqueKey)) {
                aVar = new com.mbridge.msdk.mbdownload.manager.a(taskUniqueKey, str);
                this.apkConcurrentHashMap.put(taskUniqueKey, aVar);
                addPreListener(aVar, taskUniqueKey);
            } else {
                com.mbridge.msdk.mbdownload.manager.a aVar2 = this.apkConcurrentHashMap.get(taskUniqueKey);
                int iD = aVar2.d();
                if (iD == 1 || iD == 10 || iD == 11) {
                    String strF = aVar2.f();
                    com.mbridge.msdk.click.c.a(com.mbridge.msdk.foundation.controller.a.f().j(), Uri.fromFile(new File(strF)), str, taskUniqueKey);
                    eVarA.b(taskUniqueKey, strF);
                    return taskUniqueKey;
                }
            }
            if (aVar != null && aVar.d() != 2) {
                startNotice(campaignEx, str);
            }
            addDownloadListener(taskUniqueKey, iDownloadListener);
        }
        return taskUniqueKey;
    }

    private void addPreListener(com.mbridge.msdk.mbdownload.manager.a aVar, String str) {
        if (this.listenerUniqueKeyConcurrentHashMap.containsValue(str)) {
            for (Map.Entry<b, String> entry : this.listenerUniqueKeyConcurrentHashMap.entrySet()) {
                String value = entry.getValue();
                if (str.equals(value)) {
                    aVar.addObserver(entry.getKey());
                    this.listenerUniqueKeyConcurrentHashMap.remove(entry.getKey(), value);
                }
            }
        }
    }

    public boolean addDownloadListener(String str, IDownloadListener iDownloadListener) {
        b bVar;
        if (iDownloadListener != null && !TextUtils.isEmpty(str)) {
            if (!this.listenerConcurrentHashMap.containsKey(iDownloadListener)) {
                bVar = new b(iDownloadListener);
                this.listenerConcurrentHashMap.put(iDownloadListener, bVar);
            } else {
                bVar = this.listenerConcurrentHashMap.get(iDownloadListener);
            }
            com.mbridge.msdk.mbdownload.manager.a aVar = this.apkConcurrentHashMap.get(str);
            if (aVar != null) {
                aVar.addObserver(bVar);
                return true;
            }
            if (this.listenerUniqueKeyConcurrentHashMap != null && bVar != null) {
                this.listenerUniqueKeyConcurrentHashMap.put(bVar, str);
            }
        }
        return false;
    }

    public boolean deleteDownloadListener(String str, IDownloadListener iDownloadListener) {
        if (iDownloadListener != null && !TextUtils.isEmpty(str)) {
            b bVarRemove = this.listenerConcurrentHashMap.containsKey(iDownloadListener) ? this.listenerConcurrentHashMap.remove(iDownloadListener) : null;
            if (bVarRemove != null) {
                bVarRemove.a(null);
            }
            com.mbridge.msdk.mbdownload.manager.a aVar = this.apkConcurrentHashMap.get(str);
            if (aVar != null) {
                aVar.deleteObserver(bVarRemove);
                return true;
            }
        }
        return false;
    }

    private void startNotice(CampaignEx campaignEx, String str) {
        f fVar = new f(getTaskUniqueKey(campaignEx, str), str);
        fVar.setCanPause(campaignEx.getNtbarpasbl() == 1);
        fVar.setOnGoingStatus(campaignEx.getNtbarpt() == 0);
        fVar.setTargetAppIconUrl(campaignEx.getIconUrl());
        fVar.setTitle(campaignEx.getAppName());
        fVar.start();
    }

    public void start(String str, String str2) {
        com.mbridge.msdk.mbdownload.manager.a aVar;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!this.apkConcurrentHashMap.containsKey(str)) {
            aVar = new com.mbridge.msdk.mbdownload.manager.a(str, str2);
            this.apkConcurrentHashMap.put(str, aVar);
        } else {
            aVar = this.apkConcurrentHashMap.get(str);
        }
        if (aVar != null) {
            aVar.a();
        }
    }

    public void installed(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.mbridge.msdk.mbdownload.manager.a aVar = this.apkConcurrentHashMap.containsKey(str) ? this.apkConcurrentHashMap.get(str) : null;
        if (aVar != null) {
            aVar.c();
        }
    }

    public void pause(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.mbridge.msdk.mbdownload.manager.a aVar = this.apkConcurrentHashMap.containsKey(str) ? this.apkConcurrentHashMap.get(str) : null;
        if (aVar != null) {
            aVar.b();
        }
    }

    private static String getTaskUniqueKey(CampaignEx campaignEx, String str) {
        if (campaignEx == null) {
            return "";
        }
        String akdlui = campaignEx.getAkdlui();
        return (!TextUtils.isEmpty(akdlui) || TextUtils.isEmpty(str)) ? akdlui : SameMD5.getMD5(str);
    }

    public int getTaskStatusByUniqueKey(String str) {
        com.mbridge.msdk.mbdownload.manager.a aVar;
        if (TextUtils.isEmpty(str) || !this.apkConcurrentHashMap.containsKey(str) || (aVar = this.apkConcurrentHashMap.get(str)) == null) {
            return -1;
        }
        return aVar.d();
    }

    public int getTaskProgressByUniqueKey(String str) {
        com.mbridge.msdk.mbdownload.manager.a aVar;
        if (TextUtils.isEmpty(str) || !this.apkConcurrentHashMap.containsKey(str) || (aVar = this.apkConcurrentHashMap.get(str)) == null) {
            return 0;
        }
        return aVar.e();
    }

    public void installApkByUniqueKey(String str, String str2) {
        com.mbridge.msdk.mbdownload.manager.a aVar;
        if (TextUtils.isEmpty(str) || !this.apkConcurrentHashMap.containsKey(str) || (aVar = this.apkConcurrentHashMap.get(str)) == null) {
            return;
        }
        String strF = aVar.f();
        if (TextUtils.isEmpty(strF) || !new File(strF).exists()) {
            return;
        }
        com.mbridge.msdk.click.c.a(com.mbridge.msdk.foundation.controller.a.f().j(), Uri.fromFile(new File(strF)), str2, str);
    }

    public void cancelAllTask() {
        if (this.apkConcurrentHashMap != null) {
            for (com.mbridge.msdk.mbdownload.manager.a aVar : this.apkConcurrentHashMap.values()) {
                if (aVar != null && aVar.d() == 2) {
                    aVar.b();
                }
            }
        }
    }

    public void reStartAllTask() {
        int iD;
        if (this.apkConcurrentHashMap != null) {
            for (com.mbridge.msdk.mbdownload.manager.a aVar : this.apkConcurrentHashMap.values()) {
                if (aVar != null && ((iD = aVar.d()) == 5 || iD == 6 || iD == -1 || iD == 8 || iD == 2)) {
                    aVar.a();
                }
            }
        }
    }
}
