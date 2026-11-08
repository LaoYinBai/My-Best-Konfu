package com.mbridge.msdk.video.bt.module;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener;
import com.mbridge.msdk.playercommon.PlayerErrorConstant;
import com.mbridge.msdk.playercommon.PlayerView;
import com.mbridge.msdk.video.bt.a.c;
import com.mbridge.msdk.video.widget.SoundImageView;
import com.mbridge.msdk.videocommon.d.b;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeBTVideoView extends BTBaseView {
    private static boolean F = false;
    private int A;
    private boolean B;
    private int C;
    private int D;
    private String E;
    private boolean G;
    private boolean H;
    private boolean I;
    private FrameLayout J;
    private PlayerView p;
    private SoundImageView q;
    private TextView r;
    private View s;
    private WebView t;
    private com.mbridge.msdk.videocommon.download.a u;
    private int v;
    private int w;
    private int x;
    private a y;
    private int z;

    public void setCreateWebView(WebView webView) {
        this.t = webView;
    }

    public void setOrientation(int i) {
        this.D = i;
    }

    public MBridgeBTVideoView(Context context) {
        super(context);
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.z = 2;
        this.B = false;
        this.C = 2;
        this.D = 1;
        this.G = false;
        this.H = false;
        this.I = false;
    }

    public MBridgeBTVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.z = 2;
        this.B = false;
        this.C = 2;
        this.D = 1;
        this.G = false;
        this.H = false;
        this.I = false;
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void soundOperate(int i, int i2, String str) {
        if (this.h) {
            this.z = i;
            if (i == 1) {
                this.q.setSoundStatus(false);
                this.p.closeSound();
            } else if (i == 2) {
                this.q.setSoundStatus(true);
                this.p.openSound();
            }
            if (i2 == 1) {
                this.q.setVisibility(8);
            } else if (i2 == 2) {
                this.q.setVisibility(0);
            }
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void init(Context context) {
        int iFindLayout = findLayout("mbridge_reward_videoview_item");
        if (iFindLayout > 0) {
            this.f.inflate(iFindLayout, this);
            this.h = b();
            if (!this.h) {
                s.d(BTBaseView.TAG, "MBridgeVideoView init fail");
            }
            a();
        }
        F = false;
    }

    public void onResume() {
        if (this.p != null) {
            this.p.setDesk(true);
            this.p.setIsCovered(false);
            if (this.H) {
                this.p.start(true);
            }
        }
    }

    public void onPause() {
        if (this.p != null) {
            this.H = this.p.isPlayIng();
            this.p.setIsBTVideoPlaying(this.H);
            this.p.onPause();
        }
    }

    public void onStop() {
        if (this.p != null) {
            this.p.setIsCovered(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.I) {
            this.C = c.a().f(this.c);
        }
        if (this.s != null) {
            this.s.setVisibility(this.w == 0 ? 8 : 0);
        }
        if (this.q != null) {
            this.q.setVisibility(this.x == 0 ? 8 : 0);
        }
        if (this.r != null) {
            this.r.setVisibility(this.v == 0 ? 8 : 0);
        }
    }

    public boolean playMute() {
        try {
            if (this.p != null && this.t != null) {
                this.p.closeSound();
                this.q.setSoundStatus(false);
                this.z = 1;
                a(this.t, "onPlayerMute", this.d);
                return true;
            }
        } catch (Exception e) {
            s.d(BTBaseView.TAG, e.getMessage());
        }
        return false;
    }

    public int getMute() {
        return this.z;
    }

    public boolean playUnMute() {
        try {
            if (this.p == null || this.t == null) {
                return false;
            }
            this.p.openSound();
            this.q.setSoundStatus(true);
            this.z = 2;
            a(this.t, "onUnmute", this.d);
            return true;
        } catch (Exception e) {
            s.d(BTBaseView.TAG, e.getMessage());
            return false;
        }
    }

    public void play() {
        try {
            if (!this.I) {
                if (this.C == 1) {
                    playMute();
                } else {
                    playUnMute();
                }
                if (!this.p.playVideo()) {
                    s.d("MediaPlayer", "播放失败");
                    if (this.y != null) {
                        this.y.onPlayError("play video failed");
                    }
                }
                this.I = true;
                if (this.t != null) {
                    a(this.t, "onPlayerPlay", this.d);
                    return;
                }
                return;
            }
            if (this.B) {
                this.p.playVideo(0);
                this.B = false;
            } else {
                this.p.start(false);
            }
            if (this.t != null) {
                a(this.t, "onPlayerPlay", this.d);
            }
        } catch (Exception e) {
            s.b(BTBaseView.TAG, e.getMessage(), e);
        }
    }

    public void resume() {
        try {
            if (this.p != null) {
                if (this.B) {
                    this.p.playVideo(0);
                    this.B = false;
                } else {
                    this.p.onResume();
                }
                if (this.t != null) {
                    a(this.t, "onPlayerResume", this.d);
                }
            }
        } catch (Exception e) {
            s.d(BTBaseView.TAG, e.getMessage());
        }
    }

    public void pause() {
        try {
            if (this.p != null) {
                this.p.pause();
                if (this.t != null) {
                    a(this.t, "onPlayerPause", this.d);
                }
            }
        } catch (Exception e) {
            s.b(BTBaseView.TAG, e.getMessage(), e);
        }
    }

    public void stop() {
        try {
            if (this.p != null) {
                this.p.pause();
                this.p.stop();
                try {
                    this.p.prepare();
                    this.p.justSeekTo(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (this.t != null) {
                    a(this.t, "onPlayerStop", this.d);
                }
            }
        } catch (Exception e2) {
            s.b(BTBaseView.TAG, e2.getMessage(), e2);
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void onDestory() {
        try {
            if (this.p != null) {
                this.p.setOnClickListener(null);
                this.p.release();
                this.p = null;
            }
            if (this.q != null) {
                this.q.setOnClickListener(null);
            }
            if (this.s != null) {
                this.s.setOnClickListener(null);
            }
            if (this.t != null) {
                this.t = null;
            }
            setOnClickListener(null);
        } catch (Throwable th) {
            s.a(BTBaseView.TAG, th.getMessage());
        }
    }

    private boolean b() {
        try {
            this.p = (PlayerView) findViewById(findID("mbridge_vfpv"));
            this.q = (SoundImageView) findViewById(findID("mbridge_sound_switch"));
            this.r = (TextView) findViewById(findID("mbridge_tv_count"));
            this.s = findViewById(findID("mbridge_rl_playing_close"));
            this.J = (FrameLayout) findViewById(findID("mbridge_top_control"));
            this.p.setIsBTVideo(true);
            return isNotNULL(this.p, this.q, this.r, this.s);
        } catch (Throwable th) {
            s.b(BTBaseView.TAG, th.getMessage(), th);
            return false;
        }
    }

    private String c() {
        String videoUrlEncode;
        Throwable th;
        try {
            videoUrlEncode = this.b.getVideoUrlEncode();
            try {
                if (this.u != null && this.u.f() == 5) {
                    String strD = this.u.d();
                    if (!z.a(strD)) {
                        if (new File(strD).exists()) {
                            return strD;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                s.b(BTBaseView.TAG, th.getMessage(), th);
                return videoUrlEncode;
            }
        } catch (Throwable th3) {
            videoUrlEncode = "";
            th = th3;
        }
        return videoUrlEncode;
    }

    private int d() {
        try {
            com.mbridge.msdk.videocommon.d.a aVarB = b.a().b();
            if (aVarB == null) {
                b.a().c();
            }
            iF = aVarB != null ? (int) aVarB.f() : 5;
            s.b(BTBaseView.TAG, "MBridgeBaseView buffetTimeout:" + iF);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return iF;
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    protected final void a() {
        super.a();
        if (this.h) {
            this.q.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTVideoView.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws JSONException {
                    boolean zIsSilent = MBridgeBTVideoView.this.p.isSilent();
                    if (MBridgeBTVideoView.this.t != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                            jSONObject.put("id", MBridgeBTVideoView.this.d);
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_MUTE, MBridgeBTVideoView.this.z);
                            jSONObject.put("data", jSONObject2);
                            h.a().a(MBridgeBTVideoView.this.t, "onPlayerMuteBtnClicked", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                            s.a("OperateViews", "onPlayerMuteBtnClicked isMute = " + zIsSilent + " mute = " + MBridgeBTVideoView.this.z);
                        } catch (Exception e) {
                            c.a().a(MBridgeBTVideoView.this.t, e.getMessage());
                        }
                    }
                }
            });
            this.s.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTVideoView.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws JSONException {
                    if (MBridgeBTVideoView.this.t != null) {
                        BTBaseView.a(MBridgeBTVideoView.this.t, "onPlayerCloseBtnClicked", MBridgeBTVideoView.this.d);
                    }
                }
            });
            setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTVideoView.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws JSONException {
                    if (MBridgeBTVideoView.this.t != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                            jSONObject.put("id", MBridgeBTVideoView.this.d);
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("x", String.valueOf(view.getX()));
                            jSONObject2.put("y", String.valueOf(view.getY()));
                            jSONObject.put("data", jSONObject2);
                            h.a().a(MBridgeBTVideoView.this.t, "onClicked", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                        } catch (Exception unused) {
                            c.a().a(MBridgeBTVideoView.this.t, "onClicked", MBridgeBTVideoView.this.d);
                        }
                    }
                }
            });
        }
    }

    private static final class a extends DefaultVideoPlayerStatusListener {
        private MBridgeBTVideoView a;
        private WebView b;
        private String c;
        private String d;
        private int e;
        private int f;
        private boolean g;
        private int k;
        private int l;
        private boolean h = false;
        private boolean i = false;
        private boolean j = false;
        private boolean m = false;

        public a(MBridgeBTVideoView mBridgeBTVideoView, WebView webView) {
            this.a = mBridgeBTVideoView;
            this.b = webView;
            if (mBridgeBTVideoView != null) {
                this.c = mBridgeBTVideoView.d;
                this.d = mBridgeBTVideoView.c;
            }
        }

        public final void a(int i, int i2) {
            this.k = i;
            this.l = i2;
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayStarted(int i) throws JSONException {
            super.onPlayStarted(i);
            if (!this.g) {
                if (this.b != null) {
                    BTBaseView.a(this.b, "onPlayerPlay", this.c);
                }
                this.g = true;
            }
            boolean unused = MBridgeBTVideoView.F = false;
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayCompleted() throws JSONException {
            super.onPlayCompleted();
            this.a.r.setText(String.valueOf(PushConstants.PUSH_TYPE_NOTIFY));
            this.a.p.setClickable(false);
            if (this.b != null) {
                BTBaseView.a(this.b, "onPlayerFinish", this.c);
            }
            this.e = this.f;
            boolean unused = MBridgeBTVideoView.F = true;
            this.a.stop();
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayError(String str) throws JSONException {
            super.onPlayError(str);
            if (this.b != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(CommandMessage.CODE, BTBaseView.o);
                    jSONObject.put("id", this.c);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, str);
                    jSONObject2.put("id", this.c);
                    jSONObject.put("data", jSONObject2);
                    h.a().a(this.b, "onPlayerFailed", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                } catch (Exception e) {
                    c.a().a(this.b, e.getMessage());
                }
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayProgress(int i, int i2) throws JSONException {
            int i3;
            String str;
            super.onPlayProgress(i, i2);
            if (this.a.h) {
                int i4 = i2 - i;
                if (i4 <= 0) {
                    i4 = 0;
                }
                this.a.r.setText(String.valueOf(i4));
            }
            this.f = i2;
            this.e = i;
            if (this.b != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                    jSONObject.put("id", this.c);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("id", this.c);
                    jSONObject2.put("progress", MBridgeBTVideoView.b(i, i2));
                    jSONObject2.put("time", String.valueOf(i));
                    jSONObject2.put("duration", String.valueOf(i2));
                    jSONObject.put("data", jSONObject2);
                    h.a().a(this.b, "onPlayerProgressChanged", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                } catch (Exception e) {
                    c.a().a(this.b, e.getMessage());
                }
            }
            try {
                if (this.k == 100 || this.m || this.k == 0 || this.l < 0 || i < (i3 = (i2 * this.l) / 100)) {
                    return;
                }
                if (this.a.b.getAdType() == 94 || this.a.b.getAdType() == 287) {
                    str = this.a.b.getRequestId() + this.a.b.getId() + this.a.b.getVideoUrlEncode();
                } else {
                    str = this.a.b.getId() + this.a.b.getVideoUrlEncode() + this.a.b.getBidToken();
                }
                com.mbridge.msdk.videocommon.download.a aVarA = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.d, str);
                if (aVarA != null) {
                    aVarA.j();
                    this.m = true;
                    s.d("DefaultVideoPlayerStatusListener", "CDRate is : " + i3 + " and start download !");
                }
            } catch (Exception e2) {
                s.d("DefaultVideoPlayerStatusListener", e2.getMessage());
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onBufferingStart(String str) throws JSONException {
            try {
                super.onBufferingStart(str);
                if ((str.equals(PlayerErrorConstant.PREPARE_TIMEOUT) || str.equals(PlayerErrorConstant.PLAYERING_TIMEOUT)) && this.b != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(CommandMessage.CODE, BTBaseView.n);
                        jSONObject.put("id", this.c);
                        jSONObject.put("data", new JSONObject());
                        h.a().a(this.b, "onPlayerTimeout", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                    } catch (Exception e) {
                        c.a().a(this.b, e.getMessage());
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onBufferingEnd() {
            try {
                super.onBufferingEnd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlaySetDataSourceError(String str) {
            super.onPlaySetDataSourceError(str);
        }
    }

    public void setSoundImageViewVisble(int i) {
        this.q.setVisibility(i == 0 ? 4 : 0);
    }

    public void setCountDownTextViewVisable(int i) {
        this.r.setVisibility(i == 0 ? 4 : 0);
    }

    public void setCloseViewVisable(int i) {
        this.s.setVisibility(i == 0 ? 4 : 0);
    }

    public void setShowClose(int i) {
        this.w = i;
    }

    public void setShowMute(int i) {
        this.x = i;
    }

    public void setShowTime(int i) {
        this.v = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(int i, int i2) {
        if (i2 != 0) {
            try {
                return w.a(Double.valueOf(i / i2)) + "";
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return i2 + "";
    }

    public void setVolume(float f, float f2) {
        if (this.p != null) {
            this.p.setVolume(f, f2);
        }
    }

    public void setPlaybackParams(float f) {
        if (this.p != null) {
            this.p.setPlaybackParams(f);
        }
    }

    public void setNotchPadding(int i, int i2, int i3, int i4) {
        if (i <= 0) {
            i = this.J.getPaddingLeft();
        }
        if (i2 <= 0) {
            i2 = this.J.getPaddingRight();
        }
        if (i3 <= 0) {
            i3 = this.J.getPaddingTop();
        }
        if (i4 <= 0) {
            i4 = this.J.getPaddingBottom();
        }
        s.d(BTBaseView.TAG, "NOTCH BTVideoView " + String.format("%1s-%2s-%3s-%4s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        this.J.setPadding(i, i3, i2, i4);
    }

    public void preLoadData() {
        int iQ;
        List<com.mbridge.msdk.videocommon.download.a> listB = com.mbridge.msdk.videocommon.download.b.getInstance().b(this.c);
        if (listB != null && listB.size() > 0 && this.b != null) {
            Iterator<com.mbridge.msdk.videocommon.download.a> it = listB.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.mbridge.msdk.videocommon.download.a next = it.next();
                s.a(BTBaseView.TAG, "offer cid = " + this.b.getId() + " task offer cid = " + next.g().getId());
                if (next != null && next.g() != null && next.g().getId().equals(this.b.getId())) {
                    this.u = next;
                    break;
                }
            }
        }
        this.A = d();
        this.E = c();
        if (this.h && !TextUtils.isEmpty(this.E) && this.b != null) {
            this.y = new a(this, this.t);
            a aVar = this.y;
            CampaignEx campaignEx = this.b;
            if (campaignEx != null && campaignEx.getReady_rate() != -1) {
                iQ = campaignEx.getReady_rate();
            } else {
                iQ = b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.c, false).q();
            }
            aVar.a(iQ, b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.c, false).r());
            this.p.initBufferIngParam(this.A);
            this.p.initVFPData(this.E, this.b.getVideoUrlEncode(), this.y);
            soundOperate(this.z, -1, null);
        }
        F = false;
    }
}
