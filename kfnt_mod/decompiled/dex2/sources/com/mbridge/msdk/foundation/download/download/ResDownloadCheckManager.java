package com.mbridge.msdk.foundation.download.download;

import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.b.c;
import com.mbridge.msdk.foundation.same.b.e;
import com.mbridge.msdk.foundation.same.c.b;
import com.mbridge.msdk.foundation.tools.q;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ResDownloadCheckManager {
    private static Map<String, Boolean> recordImageMap = new HashMap();
    private Map<String, Boolean> campaignStateMap;
    private Map<String, Boolean> preLoadSuccessedMap;
    private Map<String, Boolean> recordVideoMap;
    private Map<String, Boolean> recordZipMap;

    private ResDownloadCheckManager() {
        this.recordVideoMap = new HashMap();
        this.recordZipMap = new HashMap();
        this.campaignStateMap = new HashMap();
        this.preLoadSuccessedMap = new HashMap();
    }

    private static final class Holder {
        public static ResDownloadCheckManager instance = new ResDownloadCheckManager();

        private Holder() {
        }
    }

    public static ResDownloadCheckManager getInstance() {
        return Holder.instance;
    }

    public boolean containsVideoKey(String str) {
        if (this.recordVideoMap == null || TextUtils.isEmpty(str) || !this.recordVideoMap.containsKey(str)) {
            return false;
        }
        return this.recordVideoMap.get(str).booleanValue();
    }

    public boolean containsZipKey(String str) {
        if (this.recordZipMap == null || TextUtils.isEmpty(str) || !this.recordZipMap.containsKey(str)) {
            return false;
        }
        return this.recordZipMap.get(str).booleanValue();
    }

    public void setCampaignList(List<CampaignEx> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (CampaignEx campaignEx : list) {
            if (campaignEx != null) {
                addUrlToMap(campaignEx);
                updateCampaignState(campaignEx.getId(), false);
            }
        }
    }

    public void setCampaignList(CampaignEx campaignEx) {
        if (campaignEx != null) {
            addUrlToMap(campaignEx);
            updateCampaignState(campaignEx.getId(), false);
        }
    }

    private void updateCampaignState(String str, boolean z) {
        if (this.campaignStateMap == null) {
            this.campaignStateMap = new HashMap();
        }
        this.campaignStateMap.put(str, Boolean.valueOf(z));
    }

    public void setVideoDownloadDone(String str, boolean z) {
        if (this.recordVideoMap == null) {
            this.recordVideoMap = new HashMap();
        }
        this.recordVideoMap.put(str, Boolean.valueOf(z));
    }

    public void setZipDownloadDone(String str, boolean z) {
        if (this.recordZipMap == null) {
            this.recordZipMap = new HashMap();
        }
        this.recordZipMap.put(str, Boolean.valueOf(z));
    }

    public void setImageDownloadDone(String str, boolean z) {
        if (recordImageMap == null) {
            recordImageMap = new HashMap();
        }
        recordImageMap.put(str, Boolean.valueOf(z));
    }

    public void setTemplatePreLoadDone(String str, boolean z) {
        if (this.preLoadSuccessedMap == null) {
            this.preLoadSuccessedMap = new HashMap();
        }
        this.preLoadSuccessedMap.put(str, Boolean.valueOf(z));
    }

    private void addUrlToMap(CampaignEx campaignEx) {
        List<CampaignEx.c.a> listE;
        if (campaignEx != null) {
            String videoUrlEncode = campaignEx.getVideoUrlEncode();
            if (this.recordVideoMap != null && !this.recordVideoMap.containsKey(videoUrlEncode)) {
                this.recordVideoMap.put(videoUrlEncode, false);
            }
            String str = campaignEx.getendcard_url();
            if (this.recordZipMap != null && !this.recordZipMap.containsKey(str)) {
                this.recordZipMap.put(str, false);
            }
            CampaignEx.c rewardTemplateMode = campaignEx.getRewardTemplateMode();
            if (rewardTemplateMode == null || (listE = rewardTemplateMode.e()) == null) {
                return;
            }
            for (CampaignEx.c.a aVar : listE) {
                if (aVar != null) {
                    addImageUrlToMap(aVar.b);
                }
            }
        }
    }

    private void addImageUrlToMap(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && recordImageMap != null && !recordImageMap.containsKey(str)) {
                recordImageMap.put(str, Boolean.valueOf(b.a(a.f().j()).b(str)));
            }
        }
    }

    private boolean checkState(Map<String, Boolean> map, String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (map != null) {
            if (map.containsKey(str)) {
                return map.get(str).booleanValue();
            }
            map.put(str, false);
        } else {
            new HashMap().put(str, false);
        }
        return false;
    }

    public boolean checkPreLoadState(String str) {
        if (!TextUtils.isEmpty(str) && this.preLoadSuccessedMap.containsKey(str)) {
            return this.preLoadSuccessedMap.get(str).booleanValue();
        }
        return false;
    }

    public void removePreLoadState(String str) {
        if (this.preLoadSuccessedMap.containsKey(str)) {
            this.preLoadSuccessedMap.remove(str);
        }
    }

    private boolean checkZipState(boolean z, String str) {
        boolean zCheckState = checkState(this.recordZipMap, str);
        if (zCheckState || !z || TextUtils.isEmpty(H5DownLoadManager.getInstance().getH5ResAddress(str))) {
            return zCheckState;
        }
        this.recordZipMap.put(str, true);
        return true;
    }

    public boolean checkImageState(boolean z, CampaignEx.c cVar) {
        List<CampaignEx.c.a> listE;
        if (cVar != null && (listE = cVar.e()) != null) {
            for (CampaignEx.c.a aVar : listE) {
                if (aVar != null && aVar.b != null) {
                    Iterator<String> it = aVar.b.iterator();
                    while (it.hasNext()) {
                        if (!checkImageUrlState(z, it.next())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean checkImageUrlState(boolean z, String str) {
        boolean zCheckState = checkState(recordImageMap, str);
        if (zCheckState || b.a(a.f().j()).b(str)) {
            return true;
        }
        return zCheckState;
    }

    private boolean getIsEffectivePath(String str) {
        File file = new File(e.b(c.MBRIDGE_VC) + File.separator + q.d(str));
        try {
            if (file.exists() && file.isFile()) {
                return file.canRead();
            }
            return false;
        } catch (Throwable th) {
            if (!MBridgeConstans.DEBUG) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }
}
