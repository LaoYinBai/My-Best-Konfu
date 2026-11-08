package com.mbridge.msdk.reward.player;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.reward.adapter.RewardUnitCacheManager;
import com.mbridge.msdk.video.bt.module.MBTempContainer;
import com.mbridge.msdk.video.bt.module.MBridgeBTContainer;
import com.mbridge.msdk.video.bt.module.b.h;
import com.mbridge.msdk.video.js.activity.AbstractJSActivity;
import com.mbridge.msdk.video.js.factory.b;
import com.mbridge.msdk.videocommon.a;
import com.mbridge.msdk.videocommon.b.d;
import com.mbridge.msdk.videocommon.d.c;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBRewardVideoActivity extends AbstractJSActivity {
    public static String INTENT_EXTRADATA = "extraData";
    public static String INTENT_ISBID = "isBid";
    public static String INTENT_ISBIG_OFFER = "isBigOffer";
    public static String INTENT_ISIV = "isIV";
    public static String INTENT_IVREWARD_MODETYPE = "ivRewardMode";
    public static String INTENT_IVREWARD_VALUE = "ivRewardValue";
    public static String INTENT_IVREWARD_VALUETYPE = "ivRewardValueType";
    public static String INTENT_MUTE = "mute";
    public static String INTENT_REWARD = "reward";
    public static String INTENT_UNITID = "unitId";
    public static String INTENT_USERID = "userId";
    public static String SAVE_STATE_KEY_REPORT = "hasRelease";
    private String a;
    private String b;
    private String c;
    private d d;
    private int h;
    private int i;
    private int j;
    private h l;
    private c m;
    private com.mbridge.msdk.videocommon.download.a p;
    private CampaignEx q;
    private List<com.mbridge.msdk.videocommon.download.a> r;
    private List<CampaignEx> s;
    private MBTempContainer t;
    private MBridgeBTContainer u;
    private WindVaneWebView v;
    private com.mbridge.msdk.video.bt.module.a.a w;
    private String x;
    private String y;
    private int e = 2;
    private boolean f = false;
    private boolean g = false;
    private boolean k = false;
    private boolean n = false;
    private boolean o = false;

    @Override // com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        RewardPlus rewardPlus;
        super.onCreate(bundle);
        MBridgeConstans.isRewardActivityShowing = true;
        try {
            int iFindLayout = findLayout("mbridge_more_offer_activity");
            if (iFindLayout < 0) {
                a("no mbridge_more_offer_activity layout");
                return;
            }
            setContentView(iFindLayout);
            Intent intent = getIntent();
            this.a = intent.getStringExtra(INTENT_UNITID);
            if (TextUtils.isEmpty(this.a)) {
                a("data empty error");
                return;
            }
            this.l = com.mbridge.msdk.reward.a.a.b.get(this.a);
            this.b = intent.getStringExtra(MBridgeConstans.PLACEMENT_ID);
            this.d = d.b(intent.getStringExtra(INTENT_REWARD));
            this.c = intent.getStringExtra(INTENT_USERID);
            this.e = intent.getIntExtra(INTENT_MUTE, 2);
            this.f = intent.getBooleanExtra(INTENT_ISIV, false);
            com.mbridge.msdk.foundation.controller.a.f().b(this.f ? 287 : 94);
            this.g = intent.getBooleanExtra(INTENT_ISBID, false);
            this.y = intent.getStringExtra(INTENT_EXTRADATA);
            if (this.f) {
                this.h = intent.getIntExtra(INTENT_IVREWARD_MODETYPE, 0);
                this.i = intent.getIntExtra(INTENT_IVREWARD_VALUETYPE, 0);
                this.j = intent.getIntExtra(INTENT_IVREWARD_VALUE, 0);
            }
            this.jsFactory = new b(this);
            registerJsFactory(this.jsFactory);
            if (this.l == null) {
                a("showRewardListener is null");
                return;
            }
            this.m = RewardUnitCacheManager.getInstance().get(this.b, this.a);
            if (this.m == null) {
                this.m = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.a);
                if (this.m == null) {
                    this.m = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.a, this.f);
                }
            }
            if (this.m != null) {
                this.d.a(this.m.l());
                this.d.a(this.m.m());
            }
            if (this.d != null && this.d.b() <= 0) {
                this.d.a(1);
            }
            int iA = m.a(this, "mbridge_reward_activity_open", "anim");
            int iA2 = m.a(this, "mbridge_reward_activity_stay", "anim");
            if (iA > 1 && iA2 > 1) {
                overridePendingTransition(iA, iA2);
            }
            if (bundle != null) {
                try {
                    this.o = bundle.getBoolean(SAVE_STATE_KEY_REPORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.r = com.mbridge.msdk.videocommon.download.b.getInstance().b(this.a);
            this.k = intent.getBooleanExtra(INTENT_ISBIG_OFFER, false);
            if (!this.k) {
                if (this.r != null && this.r.size() > 0) {
                    this.p = this.r.get(0);
                }
                if (this.p != null) {
                    this.q = this.p.g();
                    this.p.a(true);
                    this.p.b(false);
                }
                if (this.p == null || this.q == null || this.d == null) {
                    a("data empty error");
                }
                a();
                return;
            }
            this.s = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.a);
            String str = "";
            this.x = "";
            if (this.s != null && this.s.size() > 0) {
                CampaignEx campaignEx = this.s.get(0);
                String mof_template_url = campaignEx.getMof_template_url();
                this.x = campaignEx.getRequestId();
                str = mof_template_url;
            }
            a.C0146a c0146aA = com.mbridge.msdk.videocommon.a.a(this.a + "_" + this.x + "_" + str);
            this.v = c0146aA != null ? c0146aA.a() : null;
            if (this.v == null) {
                if (this.p == null && this.r != null && this.r.size() > 0) {
                    this.p = this.r.get(0);
                }
                if (this.p == null) {
                    com.mbridge.msdk.videocommon.download.b bVar = com.mbridge.msdk.videocommon.download.b.getInstance();
                    int i = this.f ? 287 : 94;
                    String str2 = this.a;
                    boolean z = this.g;
                    com.mbridge.msdk.videocommon.download.d dVarC = bVar.c(str2);
                    this.p = dVarC != null ? dVarC.b(i, z) : null;
                }
                if (this.p != null) {
                    this.q = this.p.g();
                    this.p.a(true);
                    this.p.b(false);
                }
                if (this.p == null || this.q == null || this.d == null) {
                    a("data empty error");
                }
                this.k = false;
                try {
                    com.mbridge.msdk.reward.b.a.a(this.q, com.mbridge.msdk.foundation.controller.a.f().j(), "showMoreOffer showBTOld", this.a, this.g, this.q.getRequestId(), this.q.getRequestIdNotice(), 0L);
                } catch (Exception unused) {
                }
                a();
                return;
            }
            int iFindID = findID("mbridge_bt_container");
            if (iFindID < 0) {
                a("no mbridge_webview_framelayout in mbridge_more_offer_activity layout");
            }
            this.u = (MBridgeBTContainer) findViewById(iFindID);
            if (this.u == null) {
                a("env error");
            }
            this.u.setVisibility(0);
            if (this.w == null) {
                this.w = new com.mbridge.msdk.video.bt.module.a.a() { // from class: com.mbridge.msdk.reward.player.MBRewardVideoActivity.1
                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a() {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a();
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a(boolean z2, d dVar) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a(z2, dVar);
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a(boolean z2, int i2) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a(z2, i2);
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a(String str3) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a(str3);
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a(boolean z2, String str3, String str4) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a(z2, str3, str4);
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a(String str3, String str4) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a(str3, str4);
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void b(String str3, String str4) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.b(str3, str4);
                        }
                    }

                    @Override // com.mbridge.msdk.video.bt.module.a.a
                    public final void a(int i2, String str3, String str4) {
                        if (MBRewardVideoActivity.this.l != null) {
                            MBRewardVideoActivity.this.l.a(i2, str3, str4);
                        }
                    }
                };
            }
            this.w = this.w;
            this.u.setBTContainerCallback(this.w);
            this.u.setShowRewardVideoListener(this.l);
            this.u.setCampaigns(this.s);
            this.u.setCampaignDownLoadTasks(this.r);
            this.u.setRewardUnitSetting(this.m);
            this.u.setUnitId(this.a);
            this.u.setPlacementId(this.b);
            this.u.setUserId(this.c);
            this.u.setActivity(this);
            this.u.setDeveloperExtraData(this.y);
            if (((this.q != null && (rewardPlus = this.q.getRewardPlus()) != null) || (this.s.get(0) != null && (rewardPlus = this.s.get(0).getRewardPlus()) != null)) && !TextUtils.isEmpty(rewardPlus.getName()) && rewardPlus.getAmount() > 0) {
                d dVar = new d(rewardPlus.getName(), rewardPlus.getAmount());
                if (dVar.b() < 0) {
                    dVar.a(1);
                }
                this.d = dVar;
            }
            this.u.setReward(this.d);
            this.u.setIVRewardEnable(this.h, this.i, this.j);
            this.u.setIV(this.f);
            this.u.setMute(this.e);
            this.u.setJSFactory((b) this.jsFactory);
            this.u.init(this);
            this.u.onCreate();
            try {
                if (this.r != null && this.r.size() > 0) {
                    com.mbridge.msdk.reward.b.a.a(this.r.get(0).g(), com.mbridge.msdk.foundation.controller.a.f().j(), "showMoreOffer", this.a, this.g, "", "", 0L);
                }
            } catch (Exception unused2) {
            }
            if (this.s == null || this.s.size() <= 0 || this.s.get(0) == null) {
                return;
            }
            a(this.s.get(0));
        } catch (Throwable th) {
            a("onCreate error" + th);
        }
    }

    private void a(String str) {
        s.d("MBRewardVideoActivity", str);
        if (this.l != null) {
            this.l.a(str);
        }
        finish();
    }

    private void a(CampaignEx campaignEx) {
        if (this.m != null) {
            c cVar = this.m;
            if (TextUtils.isEmpty(c.a)) {
                return;
            }
            e eVarA = e.a();
            c cVar2 = this.m;
            eVarA.a(c.a, campaignEx, (Context) this, this.a, (com.mbridge.msdk.mbjscommon.confirmation.c) null);
        }
    }

    private void a() {
        RewardPlus rewardPlus;
        int iFindID = findID("mbridge_temp_container");
        if (iFindID < 0) {
            a("no id mbridge_bt_container in mbridge_more_offer_activity layout");
        }
        this.t = (MBTempContainer) findViewById(iFindID);
        if (this.t == null) {
            a("env error");
        }
        this.t.setVisibility(0);
        this.t.setActivity(this);
        this.t.setBidCampaign(this.g);
        this.t.setBigOffer(this.k);
        this.t.setCampaign(this.q);
        this.t.setCampaignDownLoadTask(this.p);
        this.t.setIV(this.f);
        this.t.setIVRewardEnable(this.h, this.i, this.j);
        this.t.setMute(this.e);
        this.t.setDeveloperExtraData(this.y);
        if (((this.q != null && (rewardPlus = this.q.getRewardPlus()) != null) || (this.s != null && this.s.size() > 0 && this.s.get(0) != null && (rewardPlus = this.s.get(0).getRewardPlus()) != null)) && !TextUtils.isEmpty(rewardPlus.getName()) && rewardPlus.getAmount() > 0) {
            d dVar = new d(rewardPlus.getName(), rewardPlus.getAmount());
            if (dVar.b() < 0) {
                dVar.a(1);
            }
            this.d = dVar;
        }
        this.t.setReward(this.d);
        this.t.setRewardUnitSetting(this.m);
        this.t.setUnitId(this.a);
        this.t.setPlacementId(this.b);
        this.t.setUserId(this.c);
        this.t.setShowRewardListener(this.l);
        this.t.init(this);
        this.t.onCreate();
        try {
            com.mbridge.msdk.reward.b.a.a(this.q, com.mbridge.msdk.foundation.controller.a.f().j(), "showBTOld", this.a, this.g, "", "", 0L);
        } catch (Exception unused) {
        }
        a(this.q);
    }

    @Override // com.mbridge.msdk.video.js.activity.AbstractJSActivity, com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        com.mbridge.msdk.foundation.controller.a.f().a(this);
        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.reward.player.MBRewardVideoActivity.2
            @Override // java.lang.Runnable
            public final void run() {
                if (MBRewardVideoActivity.this.r == null || MBRewardVideoActivity.this.r.size() <= 0) {
                    return;
                }
                for (com.mbridge.msdk.videocommon.download.a aVar : MBRewardVideoActivity.this.r) {
                    if (aVar != null && aVar.g() != null) {
                        com.mbridge.msdk.videocommon.a.a.a().a(aVar.g(), MBRewardVideoActivity.this.a);
                    }
                }
            }
        });
        if (this.t != null) {
            this.t.onResume();
        }
        if (this.u != null) {
            this.u.onResume();
        }
    }

    @Override // com.mbridge.msdk.video.js.activity.AbstractJSActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.t != null) {
            this.t.onPause();
        }
        if (this.u != null) {
            this.u.onPause();
        }
    }

    @Override // com.mbridge.msdk.video.js.activity.AbstractJSActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.t != null) {
            this.t.onConfigurationChanged(configuration);
        }
        if (this.u != null) {
            this.u.onConfigurationChanged(configuration);
        }
    }

    @Override // com.mbridge.msdk.video.js.activity.AbstractJSActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.t != null) {
            this.t.onBackPressed();
        }
        if (this.u != null) {
            this.u.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        MBridgeConstans.isRewardActivityShowing = false;
        super.onStop();
        if (this.t != null) {
            this.t.onStop();
        }
        if (this.u != null) {
            this.u.onStop();
        }
    }

    @Override // com.mbridge.msdk.video.js.activity.AbstractJSActivity, com.mbridge.msdk.activity.MBBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.mbridge.msdk.video.module.b.a.a(this.a);
        if (this.t != null) {
            this.t.onDestroy();
            this.t = null;
        }
        if (this.u != null) {
            this.u.onDestroy();
            this.u = null;
        }
        com.mbridge.msdk.foundation.same.f.b.a().execute(new a(this.r, this.a, this.x));
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (this.t != null) {
            this.t.onRestart();
        }
        if (this.u != null) {
            this.u.onRestart();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.t != null) {
            this.t.onStart();
        }
        if (this.u != null) {
            this.u.onStart();
        }
    }

    private static final class a implements Runnable {
        private final List<com.mbridge.msdk.videocommon.download.a> a;
        private final String b;
        private final String c;

        public a(List<com.mbridge.msdk.videocommon.download.a> list, String str, String str2) {
            this.a = list;
            this.b = str;
            this.c = str2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (this.a == null || this.a.size() <= 0) {
                    return;
                }
                for (com.mbridge.msdk.videocommon.download.a aVar : this.a) {
                    if (aVar != null && aVar.g() != null) {
                        CampaignEx campaignExG = aVar.g();
                        String str = campaignExG.getRequestId() + campaignExG.getId() + campaignExG.getVideoUrlEncode();
                        com.mbridge.msdk.videocommon.download.d dVarC = com.mbridge.msdk.videocommon.download.b.getInstance().c(this.b);
                        if (dVarC != null) {
                            try {
                                dVarC.b(str);
                            } catch (Exception unused) {
                            }
                        }
                        if (campaignExG != null && campaignExG.getRewardTemplateMode() != null) {
                            if (!TextUtils.isEmpty(campaignExG.getRewardTemplateMode().d())) {
                                com.mbridge.msdk.videocommon.a.b(this.b + "_" + campaignExG.getId() + "_" + this.c + "_" + campaignExG.getRewardTemplateMode().d());
                                com.mbridge.msdk.videocommon.a.b(campaignExG.getAdType(), campaignExG);
                            }
                            if (!TextUtils.isEmpty(campaignExG.getMof_template_url())) {
                                com.mbridge.msdk.videocommon.a.b(this.b + "_" + this.c + "_" + campaignExG.getMof_template_url());
                            }
                            com.mbridge.msdk.videocommon.a.a.a().a(campaignExG);
                        }
                    }
                }
            } catch (Exception e) {
                s.a("MBRewardVideoActivity", e.getMessage());
            }
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        com.mbridge.msdk.foundation.controller.a.f().b(0);
        if (this.t != null) {
            this.t.onDestroy();
            this.t = null;
        }
        if (this.u != null) {
            this.u.onDestroy();
            this.u = null;
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(SAVE_STATE_KEY_REPORT, this.o);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.mbridge.msdk.activity.MBBaseActivity
    public void setTopControllerPadding(int i, int i2, int i3, int i4, int i5) {
        if (this.t != null) {
            this.t.setNotchPadding(i, i2, i3, i4, i5);
        }
        if (this.u != null) {
            this.u.setNotchPadding(i, i2, i3, i4, i5);
        }
    }

    public int findID(String str) {
        return m.a(getApplicationContext(), str, "id");
    }

    public int findLayout(String str) {
        return m.a(getApplicationContext(), str, TtmlNode.TAG_LAYOUT);
    }
}
