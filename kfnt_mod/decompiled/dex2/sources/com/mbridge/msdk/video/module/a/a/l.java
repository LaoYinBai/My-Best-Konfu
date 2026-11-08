package com.mbridge.msdk.video.module.a.a;

import android.os.Handler;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.video.module.MBridgeContainerView;
import com.mbridge.msdk.video.module.MBridgeVideoView;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: VideoViewDefaultListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class l extends n {
    private MBridgeVideoView k;
    private MBridgeContainerView l;
    private int m;
    private Timer n;
    private Handler o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;

    public l(MBridgeVideoView mBridgeVideoView, MBridgeContainerView mBridgeContainerView, CampaignEx campaignEx, com.mbridge.msdk.videocommon.b.d dVar, com.mbridge.msdk.videocommon.download.a aVar, String str, String str2, int i, int i2, com.mbridge.msdk.video.module.a.a aVar2, double d, int i3, boolean z, int i4) {
        super(campaignEx, dVar, aVar, str, str2, aVar2, d, i3, z);
        this.o = new Handler();
        this.p = false;
        this.q = false;
        this.r = 1;
        this.k = mBridgeVideoView;
        this.l = mBridgeContainerView;
        this.s = i;
        this.m = i2;
        this.r = i4;
        if (mBridgeVideoView != null) {
            this.p = mBridgeVideoView.getVideoSkipTime() == 0;
        }
        if (mBridgeVideoView == null || mBridgeContainerView == null) {
            this.a = false;
        }
    }

    @Override // com.mbridge.msdk.video.module.a.a.n, com.mbridge.msdk.video.module.a.a.j, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public final void a(int i, Object obj) {
        Integer num;
        if (this.a) {
            switch (i) {
                case 1:
                    if (!this.l.endCardShowing()) {
                        switch (this.l.getVideoInteractiveType()) {
                            case -2:
                                if (this.l.miniCardLoaded()) {
                                    this.l.showVideoClickView(2);
                                    break;
                                }
                                break;
                            case -1:
                                if (this.l.isLast()) {
                                    this.l.showVideoClickView(1);
                                    this.k.soundOperate(0, 1);
                                    try {
                                        j();
                                        this.n = new Timer();
                                        this.n.schedule(new TimerTask() { // from class: com.mbridge.msdk.video.module.a.a.l.1
                                            @Override // java.util.TimerTask, java.lang.Runnable
                                            public final void run() {
                                                try {
                                                    l.this.o.post(new Runnable() { // from class: com.mbridge.msdk.video.module.a.a.l.1.1
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            l.this.l.showVideoClickView(-1);
                                                            l.this.k.soundOperate(0, 2);
                                                        }
                                                    });
                                                } catch (Throwable th) {
                                                    if (MBridgeConstans.DEBUG) {
                                                        th.printStackTrace();
                                                    }
                                                }
                                            }
                                        }, 3000L);
                                        break;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        break;
                                    }
                                } else {
                                    this.l.showVideoClickView(-1);
                                    this.k.soundOperate(0, 2);
                                    j();
                                    break;
                                }
                        }
                    }
                    break;
                case 2:
                case 6:
                    this.k.dismissAllAlert();
                    this.k.videoOperate(3);
                    if (this.s == 2 && !this.l.endCardShowing()) {
                        this.l.showEndcard(this.b.getVideo_end_type());
                        break;
                    } else {
                        i = 16;
                        break;
                    }
                    break;
                case 5:
                    if (obj != null && (obj instanceof Integer)) {
                        if (((Integer) obj).intValue() == 1) {
                            num = 2;
                        } else {
                            num = 1;
                        }
                        this.k.soundOperate(num.intValue(), -1);
                        break;
                    }
                    break;
                case 8:
                    if (this.l != null) {
                        if (!this.l.showAlertWebView()) {
                            if (this.k != null) {
                                this.k.showAlertView();
                                break;
                            }
                        } else if (this.k != null) {
                            this.k.alertWebViewShowed();
                            break;
                        }
                    } else if (this.k != null) {
                        this.k.showAlertView();
                        break;
                    }
                    break;
                case 10:
                    this.q = true;
                    this.k.soundOperate(0, 2);
                    this.k.progressOperate(0, 2);
                    break;
                case 11:
                    this.k.videoOperate(3);
                    this.k.dismissAllAlert();
                    if (this.b.getVideo_end_type() != 3) {
                        this.k.setVisibility(8);
                    } else {
                        this.k.setVisibility(0);
                    }
                    this.l.showEndcard(this.b.getVideo_end_type());
                    break;
                case 12:
                    h();
                    this.k.videoOperate(3);
                    this.k.dismissAllAlert();
                    if (this.b.getVideo_end_type() != 3) {
                        this.k.setVisibility(8);
                    } else {
                        this.k.setVisibility(0);
                    }
                    if (this.q || this.r != 0) {
                        if (!this.q && this.r == 1) {
                            g();
                            f();
                            e();
                        }
                        this.l.showEndcard(this.b.getVideo_end_type());
                        break;
                    }
                    break;
                case 13:
                    this.k.closeVideoOperate(0, 2);
                    break;
                case 14:
                    if (!this.p) {
                        this.k.closeVideoOperate(0, 1);
                        break;
                    }
                    break;
                case 15:
                    if (obj != null && (obj instanceof MBridgeVideoView.a)) {
                        MBridgeVideoView.a aVar = (MBridgeVideoView.a) obj;
                        int videoInteractiveType = this.l.getVideoInteractiveType();
                        if (videoInteractiveType >= 0 && aVar.a >= videoInteractiveType) {
                            this.l.showVideoClickView(1);
                            this.k.soundOperate(0, 1);
                        }
                        this.p = aVar.c;
                        if (this.m >= 0 && aVar.a >= this.m && !this.p) {
                            this.k.closeVideoOperate(0, 2);
                            this.p = true;
                            break;
                        }
                    }
                    break;
            }
        }
        super.a(i, obj);
    }

    private void j() {
        try {
            if (this.n != null) {
                this.n.cancel();
                this.n = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
