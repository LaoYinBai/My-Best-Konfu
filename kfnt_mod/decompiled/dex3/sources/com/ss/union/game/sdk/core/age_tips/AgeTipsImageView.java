package com.ss.union.game.sdk.core.age_tips;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AgeTipsImageView extends ImageView {
    private View.OnClickListener a;

    public interface PageFrom {
        public static final int LOGIN = 2;
        public static final int SPLASH = 1;
    }

    public AgeTipsImageView(Context context) {
        this(context, null);
    }

    public AgeTipsImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AgeTipsImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.a = onClickListener;
    }

    public void init(Context context, final int i) {
        final b bVarA = b.a(context);
        if (bVarA.a()) {
            c.a(ConfigManager.AppConfig.appName());
            int i2 = bVarA.a;
            if (i2 == 8) {
                setVisibility(0);
                setImageResource(ResourceUtils.getDrawableIdByName("lg_age_8_tips"));
            } else if (i2 == 12) {
                setVisibility(0);
                setImageResource(ResourceUtils.getDrawableIdByName("lg_age_12_tips"));
            } else if (i2 == 16) {
                setVisibility(0);
                setImageResource(ResourceUtils.getDrawableIdByName("lg_age_16_tips"));
            } else {
                setVisibility(8);
            }
            if (i == 1) {
                int i3 = bVarA.a;
                if (i3 == 8) {
                    c.b("video_eight_show");
                } else if (i3 == 12) {
                    c.b("video_twelve_show");
                } else if (i3 == 16) {
                    c.b("video_sixteen_show");
                }
            } else if (i == 2) {
                int i4 = bVarA.a;
                if (i4 == 8) {
                    c.b("eight_show");
                } else if (i4 == 12) {
                    c.b("twelve_show");
                } else if (i4 == 16) {
                    c.b("sixteen_show");
                }
            }
            super.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.age_tips.AgeTipsImageView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AgeTipsImageView.this.a != null) {
                        AgeTipsImageView.this.a.onClick(view);
                    }
                    if (i == 2) {
                        int i5 = bVarA.a;
                        if (i5 == 8) {
                            c.c("eight_click");
                        } else if (i5 == 12) {
                            c.c("twelve_click");
                        } else {
                            if (i5 != 16) {
                                return;
                            }
                            c.c("sixteen_click");
                        }
                    }
                }
            });
        }
    }
}
