package com.mbridge.msdk.widget.custom.a;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.widget.custom.CustomViewMessageWrap;
import com.mbridge.msdk.widget.custom.DownloadMessageDialog;
import com.mbridge.msdk.widget.custom.a;
import com.mbridge.msdk.widget.custom.b.e;
import com.mbridge.msdk.widget.custom.baseview.MBButton;
import com.mbridge.msdk.widget.custom.baseview.MBImageView;
import com.mbridge.msdk.widget.custom.baseview.MBStarLevelLayoutView;
import com.mbridge.msdk.widget.custom.baseview.MBTextView;
import java.lang.reflect.InvocationTargetException;

/* compiled from: RenderEngine.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private CustomViewMessageWrap a;
    private com.mbridge.msdk.widget.custom.a b;
    private DownloadMessageDialog c;
    private int d;
    private int e;
    private ImageView f;
    private MBImageView g;
    private MBButton h;
    private MBImageView i;

    public d(CustomViewMessageWrap customViewMessageWrap, com.mbridge.msdk.widget.custom.a aVar, DownloadMessageDialog downloadMessageDialog, int i, int i2) {
        this.a = customViewMessageWrap;
        this.b = aVar;
        this.c = downloadMessageDialog;
        this.d = i;
        this.e = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.widget.ImageView, com.mbridge.msdk.widget.custom.baseview.MBImageView] */
    public final void a(ViewGroup viewGroup) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if ((viewGroup instanceof MBStarLevelLayoutView) && viewGroup.getContentDescription().equals("getStarCount")) {
            ((MBStarLevelLayoutView) viewGroup).setRating(Math.min(Math.max(this.a != null ? this.a.getStarCount() : 0, 0), 5));
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View childAt = viewGroup.getChildAt(i);
            if (!TextUtils.isEmpty(childAt.getContentDescription())) {
                String str = (String) childAt.getContentDescription();
                String[] strArrSplit = str.split("#");
                if (strArrSplit.length > 1) {
                    String str2 = strArrSplit[0];
                    String str3 = strArrSplit[1];
                    if (e.a(str3)) {
                        switch (Integer.parseInt(str3)) {
                            case 1:
                                childAt.setVisibility(TextUtils.isEmpty(CustomViewMessageWrap.getMessage(this.a, str2)) ? 8 : 0);
                                break;
                            case 2:
                                try {
                                    final View childAt2 = viewGroup.getChildAt(i + 1);
                                    ViewGroup viewGroup2 = (ViewGroup) childAt;
                                    final ImageView imageView = null;
                                    for (int i2 = 0; i2 < viewGroup2.getChildCount(); i2++) {
                                        if (viewGroup2.getChildAt(i2) instanceof ImageView) {
                                            imageView = (ImageView) viewGroup2.getChildAt(i2);
                                        }
                                    }
                                    childAt.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.custom.a.d.1
                                        @Override // android.view.View.OnClickListener
                                        public final void onClick(View view) {
                                            childAt2.setVisibility(childAt2.getVisibility() == 0 ? 8 : 0);
                                            imageView.setImageResource(m.a(d.this.f.getContext(), childAt2.getVisibility() == 0 ? "mbridge_download_message_dialog_hide" : "mbridge_download_message_dialog_expand", "drawable"));
                                        }
                                    });
                                    break;
                                } catch (Exception e) {
                                    if (MBridgeConstans.DEBUG) {
                                        e.printStackTrace();
                                        break;
                                    }
                                }
                                break;
                        }
                    }
                }
                if (str.equals(CampaignEx.JSON_NATIVE_VIDEO_CLOSE)) {
                    childAt.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.custom.a.d.2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            d.this.a.putView("image_close", d.this.i);
                            d.this.b.a(d.this.c, d.this.a, a.EnumC0147a.CLICK_EVENT_CLOSE);
                        }
                    });
                }
                if (str.startsWith("getApplicationPrivacy")) {
                    childAt.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.custom.a.d.3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            try {
                                View childAt3 = ((ViewGroup) childAt).getChildAt(1);
                                if (childAt3 != null) {
                                    childAt3.performClick();
                                }
                            } catch (Exception e2) {
                                s.a("render", e2.getMessage());
                            }
                        }
                    });
                }
            }
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt);
            } else if (childAt instanceof MBTextView) {
                MBTextView mBTextView = (MBTextView) childAt;
                String message = CustomViewMessageWrap.getMessage(this.a, String.valueOf(mBTextView.getContentDescription()));
                if (TextUtils.isEmpty(mBTextView.getText()) && !TextUtils.isEmpty(message)) {
                    mBTextView.setText(message);
                }
            } else if (childAt instanceof MBImageView) {
                final ?? r6 = (MBImageView) childAt;
                if (!TextUtils.isEmpty(r6.getContentDescription())) {
                    if (r6.getContentDescription().equals("imgbg")) {
                        this.g = r6;
                        if (!TextUtils.isEmpty(CustomViewMessageWrap.getMessage(this.a, "getApplicationImage"))) {
                            int iC = r6.getResources().getDisplayMetrics().widthPixels - b.a().c("20dp");
                            ViewGroup.LayoutParams layoutParams = r6.getLayoutParams();
                            layoutParams.width = iC;
                            layoutParams.height = (int) (iC * 0.5225f);
                            r6.setLayoutParams(layoutParams);
                        }
                    } else if (r6.getContentDescription().equals("getApplicationImage")) {
                        String message2 = CustomViewMessageWrap.getMessage(this.a, String.valueOf(r6.getContentDescription()));
                        if (!TextUtils.isEmpty(r6.getContentDescription()) && TextUtils.isEmpty(message2)) {
                            r6.setVisibility(8);
                            if (this.g != null) {
                                this.g.setVisibility(8);
                            }
                        } else {
                            com.mbridge.msdk.widget.custom.b.b.a(r6, this.g, message2, this.d, this.e, this.c);
                        }
                    } else if (r6.getContentDescription().equals("getApplicationLogo")) {
                        String message3 = CustomViewMessageWrap.getMessage(this.a, String.valueOf(r6.getContentDescription()));
                        if (!TextUtils.isEmpty(message3)) {
                            com.mbridge.msdk.widget.custom.b.b.a((MBImageView) r6, message3);
                        } else {
                            r6.setBackgroundColor(Color.parseColor("#e6e6e6"));
                        }
                    } else if (r6.getContentDescription().equals(CampaignEx.JSON_NATIVE_VIDEO_CLOSE)) {
                        this.i = r6;
                        r6.setImageResource(m.a(r6.getContext(), "mbridge_download_message_dialog_close", "drawable"));
                    } else if (r6.getContentDescription().equals("permissionexpand")) {
                        r6.setImageResource(m.a(r6.getContext(), "mbridge_download_message_dialog_expand", "drawable"));
                        this.f = r6;
                    } else if (r6.getContentDescription().equals("getApplicationPrivacy")) {
                        r6.setImageResource(m.a(r6.getContext(), "mbridge_download_message_dialog_expand", "drawable"));
                        r6.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.custom.a.d.4
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                if (d.this.a != null) {
                                    d.this.a.putView("image_privacy", r6);
                                }
                                d.this.b.a(d.this.c, d.this.a, a.EnumC0147a.CLICK_EVENT_OPEN_PRIVACY);
                            }
                        });
                    }
                }
            } else if (childAt instanceof MBButton) {
                final MBButton mBButton = (MBButton) childAt;
                mBButton.setIndeterminateProgressMode(true);
                mBButton.setBackgroundResource(m.a(mBButton.getContext(), "mbridge_download_message_dialog_button_shape", "drawable"));
                if (!TextUtils.isEmpty(mBButton.getContentDescription()) && String.valueOf(mBButton.getContentDescription()).startsWith("download")) {
                    this.h = mBButton;
                    mBButton.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.custom.a.d.5
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            if (d.this.a != null) {
                                d.this.a.putView("button_download", mBButton);
                            }
                            d.this.b.a(d.this.c, d.this.a, a.EnumC0147a.CLICK_EVENT_DOWNLOAD);
                        }
                    });
                }
            }
        }
    }

    public final MBButton a() {
        return this.h;
    }
}
