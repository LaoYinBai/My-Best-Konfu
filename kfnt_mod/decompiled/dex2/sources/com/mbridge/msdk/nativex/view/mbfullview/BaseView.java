package com.mbridge.msdk.nativex.view.mbfullview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BaseView extends RelativeLayout {
    protected RelativeLayout a;
    protected RelativeLayout b;
    protected RelativeLayout c;
    protected ImageView d;
    protected TextView e;
    protected ProgressBar f;
    protected FrameLayout g;
    protected LinearLayout h;
    protected RelativeLayout i;
    public a style;

    public enum a {
        FULL_TOP_VIEW,
        FULL_MIDDLE_VIEW
    }

    public FrameLayout getmAnimationContent() {
        return this.g;
    }

    public LinearLayout getmAnimationPlayer() {
        return this.h;
    }

    public BaseView(Context context) {
        super(context);
        View viewInflate = LayoutInflater.from(getContext()).inflate(m.a(getContext(), "mbridge_nativex_fullbasescreen", TtmlNode.TAG_LAYOUT), this);
        this.i = (RelativeLayout) viewInflate;
        if (viewInflate != null) {
            this.a = (RelativeLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_full_rl_playcontainer", "id"));
            this.b = (RelativeLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_full_player_parent", "id"));
            this.c = (RelativeLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_full_rl_close", "id"));
            this.d = (ImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_full_iv_close", "id"));
            this.e = (TextView) viewInflate.findViewById(m.a(getContext(), "mbridge_full_tv_install", "id"));
            this.f = (ProgressBar) viewInflate.findViewById(m.a(getContext(), "mbridge_full_pb_loading", "id"));
            this.g = (FrameLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_full_animation_content", "id"));
            this.h = (LinearLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_full_animation_player", "id"));
            viewInflate.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    public RelativeLayout getMBridgeFullPlayContainer() {
        return this.a;
    }

    public RelativeLayout getMBridgeFullPlayerParent() {
        return this.b;
    }

    public RelativeLayout getMBridgeFullClose() {
        return this.c;
    }

    public ImageView getMBridgeFullIvClose() {
        return this.d;
    }

    public TextView getMBridgeFullTvInstall() {
        return this.e;
    }

    public ProgressBar getMBridgeFullPb() {
        return this.f;
    }

    public a getStytle() {
        return this.style;
    }

    public void setStytle(a aVar) {
        this.style = aVar;
    }
}
