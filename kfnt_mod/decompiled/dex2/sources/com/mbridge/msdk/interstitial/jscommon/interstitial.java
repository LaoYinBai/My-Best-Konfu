package com.mbridge.msdk.interstitial.jscommon;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.h.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.interstitial.b.a;
import com.mbridge.msdk.interstitial.view.MBInterstitialActivity;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class interstitial extends AbsMbridgeDownload {
    private static final String a = "com.mbridge.msdk.interstitial.jscommon.interstitial";
    private Object b;

    public void getInfo(Object obj, String str) {
        MBInterstitialActivity mBInterstitialActivity;
        try {
            s.b(a, "======前端调用 getInfo() 获取campaign数据 params:" + str);
            this.b = obj;
            if (this.mContext == null) {
                s.d(a, "getInfo() context 为空 return");
                b();
                return;
            }
            int iA = a(this.mContext);
            String strC = c();
            if (TextUtils.isEmpty(strC)) {
                s.d(a, "getInfo() unitid is null");
                b();
                return;
            }
            s.b(a, "getInfo() mCurrentCallState:" + iA + " unitid:" + strC);
            if (iA == 1) {
                try {
                    s.b(a, "instersGetInfo hideLoading");
                    try {
                        if (this.mContext != null && a(this.mContext) == 1) {
                            try {
                                MBInterstitialActivity mBInterstitialActivity2 = (MBInterstitialActivity) this.mContext;
                                if (mBInterstitialActivity2 != null) {
                                    mBInterstitialActivity2.hideLoading();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        if (this.mContext != null && a(this.mContext) == 1 && (mBInterstitialActivity = (MBInterstitialActivity) this.mContext) != null) {
                            mBInterstitialActivity.showWebView();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    if (this.mContext == null) {
                        s.b(a, "instersGetInfo context 为空 通知前端没有数据");
                        b();
                        return;
                    }
                    if (a(this.mContext) != 1) {
                        s.d(a, "instersGetInfo mCurrentCallState!=INTERSTITIAL_CALL");
                        b();
                        return;
                    }
                    if (this.mContext instanceof MBInterstitialActivity) {
                        ((MBInterstitialActivity) this.mContext).mIsMBPage = true;
                        s.b(a, "set mIsMBPage true");
                    }
                    final String strC2 = c();
                    if (TextUtils.isEmpty(strC2)) {
                        s.d(a, "instersGetInfo unitid is null");
                        b();
                        return;
                    }
                    final List<CampaignEx> listA = a(strC2);
                    s.b(a, "instersGetInfo 开始从缓存里取数据");
                    if (listA != null) {
                        s.d(a, "instersGetInfo 从缓存里取到的数据 不为空 size：" + listA.size());
                        String strA = a(listA);
                        if (TextUtils.isEmpty(strA)) {
                            s.d(a, "instersGetInfo campListJson is null return");
                            b();
                            return;
                        }
                        h.a().a(obj, strA);
                        a(strC2, listA);
                        try {
                            if (!TextUtils.isEmpty(strC2) && listA != null && listA.size() != 0) {
                                new Thread(new Runnable() { // from class: com.mbridge.msdk.interstitial.jscommon.interstitial.2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        for (int i = 0; i < listA.size(); i++) {
                                            CampaignEx campaignEx = (CampaignEx) listA.get(i);
                                            if (campaignEx != null) {
                                                m.a(i.a(interstitial.this.mContext)).b(campaignEx.getId());
                                                s.b(interstitial.a, "======更新frequence：" + campaignEx.getId() + " sUnitId:" + strC2);
                                            }
                                        }
                                    }
                                }).start();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        try {
                            new Thread(new Runnable() { // from class: com.mbridge.msdk.interstitial.jscommon.interstitial.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    s.b(interstitial.a, "清除本地的InterstitialCampaign集合");
                                    a aVarA = a.a();
                                    if (aVarA != null) {
                                        aVarA.a(listA, strC2);
                                    }
                                }
                            }).start();
                            return;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            return;
                        }
                    }
                    b();
                } catch (Exception e6) {
                    e6.printStackTrace();
                    b();
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            b();
        }
    }

    public void install(Object obj, String str) {
        try {
            String strAddDownloaderListener = addDownloaderListener(obj, str);
            s.b(a, "======前端调用 install()  params:" + strAddDownloaderListener);
            if (this.mContext == null) {
                s.d(a, "install() context 为空 return");
                return;
            }
            s.b(a, "install() 开始tracking跳转");
            if (this.mContext instanceof MBInterstitialActivity) {
                ((MBInterstitialActivity) this.mContext).clickTracking(false, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openURL(Object obj, String str) {
        s.d(a, "openURL:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url");
            int iOptInt = jSONObject.optInt(Message.TYPE);
            if (iOptInt == 1) {
                c.a(this.mContext, strOptString);
            } else if (iOptInt == 2) {
                c.b(this.mContext, strOptString);
            }
        } catch (JSONException e) {
            s.d(a, e.getMessage());
        } catch (Throwable th) {
            s.d(a, th.getMessage());
        }
    }

    private void b() {
        try {
            s.b(a, "通知h5 没有数据");
            h.a().a(this.b, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CampaignEx> a(String str) {
        try {
            if (TextUtils.isEmpty(str) || a.a() == null) {
                return null;
            }
            return a.a().a(str, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String a(List<CampaignEx> list) throws JSONException {
        if (list == null) {
            return "";
        }
        try {
            if (list.size() <= 0) {
                return "";
            }
            JSONArray camplistToJson = CampaignEx.parseCamplistToJson(list);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("campaignList", camplistToJson);
            String string = jSONObject.toString();
            s.b(a, "===========campListJson:" + string);
            return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<String> getExcludeIdList(String str) {
        ArrayList arrayList = null;
        try {
        } catch (Exception e) {
            e = e;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strOptString = new JSONObject(str).optString(d.c);
        if (!TextUtils.isEmpty(strOptString)) {
            JSONArray jSONArray = new JSONArray(strOptString);
            if (jSONArray.length() > 0) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        if (!TextUtils.isEmpty(jSONArray.optString(i))) {
                            arrayList2.add(jSONArray.optString(i));
                        }
                    } catch (Exception e2) {
                        arrayList = arrayList2;
                        e = e2;
                        e.printStackTrace();
                        return arrayList;
                    }
                }
                return arrayList2;
            }
        }
        return arrayList;
    }

    private int a(Context context) {
        return (context == null || !(context instanceof MBInterstitialActivity)) ? -1 : 1;
    }

    private String c() {
        MBInterstitialActivity mBInterstitialActivity;
        try {
            if (this.mContext != null && a(this.mContext) == 1) {
                try {
                    if (this.mContext != null && (this.mContext instanceof MBInterstitialActivity) && (mBInterstitialActivity = (MBInterstitialActivity) this.mContext) != null) {
                        return mBInterstitialActivity.mUnitid;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private void a(String str, List<CampaignEx> list) {
        try {
            if (!TextUtils.isEmpty(str) && list != null && list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    CampaignEx campaignEx = list.get(i);
                    if (campaignEx != null) {
                        s.b(a, "======更新displayid：" + campaignEx.getId());
                        com.mbridge.msdk.foundation.same.a.d.b(str, campaignEx, "interstitial");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
        install(obj, str);
    }
}
