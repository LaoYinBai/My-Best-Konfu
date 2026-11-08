package com.tachikoma.core.utility;

import androidx.annotation.Keep;
import com.tachikoma.core.Tachikoma;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Console {
    private boolean mIsShowLog;

    public void err(String str) {
        if (this.mIsShowLog) {
            if (Tachikoma.getInstance().logger() != null) {
                Tachikoma.getInstance().logger().e("tachikoma[ERROR]", str);
                return;
            }
            System.out.println("[ERROR] " + str);
        }
    }

    public void log(String str) {
        if (this.mIsShowLog) {
            if (Tachikoma.getInstance().logger() != null) {
                Tachikoma.getInstance().logger().i("tachikoma[INFO]", str);
                return;
            }
            System.out.println("tachikoma [INFO] " + str);
        }
    }

    public void setShowLog(boolean z) {
        this.mIsShowLog = z;
    }
}
