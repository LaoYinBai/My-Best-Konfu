package com.huawei.updatesdk.service.otaupdate;

import android.content.Context;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i {
    public static int a(int i, int i2) {
        if (i2 <= 0) {
            return 0;
        }
        int iRound = (int) Math.round((i / i2) * 100.0d);
        if (iRound > 100) {
            return 100;
        }
        return iRound;
    }

    public static String a(int i) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(0);
        return percentInstance.format(i / 100.0d);
    }

    public static String a(Context context, long j) {
        if (j == 0) {
            return context.getString(g.b(context, "upsdk_storage_utils"), PushConstants.PUSH_TYPE_NOTIFY);
        }
        DecimalFormat decimalFormat = null;
        if (j > 104857) {
            decimalFormat = new DecimalFormat("###.#");
        } else if (j > 10485) {
            decimalFormat = new DecimalFormat("###.##");
        }
        return decimalFormat != null ? context.getString(g.b(context, "upsdk_storage_utils"), decimalFormat.format(j / 1048576.0d)) : context.getString(g.b(context, "upsdk_storage_utils"), "0.01");
    }

    public static boolean a(List list) {
        return list == null || list.size() <= 0;
    }
}
