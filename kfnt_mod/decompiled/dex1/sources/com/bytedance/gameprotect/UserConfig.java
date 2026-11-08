package com.bytedance.gameprotect;

import android.content.Context;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UserConfig {
    public final boolean accountless;
    public final Context appContext;
    public final String appId;
    public final boolean isSandbox;
    public final String productFlavor;
    public final int region;

    @Keep
    public static final class Builder {
        private static final int BRANCH_CHINA = 0;
        private static final int BRANCH_OTHER = 1;
        private final Context mAppContext;
        private final String mAppId;
        private String mProductFlavor;
        private final String DEFAULT_FLAVOR = "light_game_sdk";
        private final String ACCOUNTLESS_FLAVOR = "light_game_sdk_accountless";
        private boolean mIsSandbox = false;
        private boolean mAccountless = false;
        private int mRegion = 1;

        public Builder(Context context, String str) {
            this.mAppContext = context.getApplicationContext();
            this.mAppId = str;
            updateProductFlavor();
        }

        private void updateProductFlavor() {
            this.mProductFlavor = this.mAccountless ? "light_game_sdk_accountless" : "light_game_sdk";
        }

        public UserConfig build() {
            return new UserConfig(this);
        }

        public Builder setAccountless(boolean z) {
            this.mAccountless = z;
            updateProductFlavor();
            return this;
        }

        public Builder setRegion(int i) {
            this.mRegion = i;
            return this;
        }

        public Builder setSandbox(boolean z) {
            this.mIsSandbox = z;
            return this;
        }
    }

    private UserConfig(Builder builder) {
        this.appContext = builder.mAppContext;
        this.appId = builder.mAppId;
        this.region = builder.mRegion;
        this.isSandbox = builder.mIsSandbox;
        this.productFlavor = builder.mProductFlavor;
        this.accountless = builder.mAccountless;
    }
}
