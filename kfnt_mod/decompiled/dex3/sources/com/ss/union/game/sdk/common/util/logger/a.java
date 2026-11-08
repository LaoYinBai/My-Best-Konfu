package com.ss.union.game.sdk.common.util.logger;

import com.ss.union.game.sdk.common.util.logger.PrettyFormatDiskStrategy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements e {
    private final d a;

    @Override // com.ss.union.game.sdk.common.util.logger.e
    public boolean a(int i, String str) {
        return true;
    }

    public a(boolean z) {
        this.a = PrettyFormatDiskStrategy.a().showThreadInfo(PrettyFormatDiskStrategy.a.EXPECT_MAIN_THREAD).tag("").openDisk(z).methodCount(0).build();
    }

    @Override // com.ss.union.game.sdk.common.util.logger.e
    public void a(int i, String str, String str2) {
        String str3;
        if (this.a != null) {
            if (str == null) {
                str3 = LogUtils.COMMON_TAG;
            } else {
                str3 = LogUtils.COMMON_TAG + " " + str;
            }
            this.a.a(i, str3, str2);
        }
    }
}
