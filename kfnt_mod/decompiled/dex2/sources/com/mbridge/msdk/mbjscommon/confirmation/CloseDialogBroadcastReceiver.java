package com.mbridge.msdk.mbjscommon.confirmation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.widget.custom.DownloadMessageDialog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CloseDialogBroadcastReceiver extends BroadcastReceiver {
    private DownloadMessageDialog a;

    public CloseDialogBroadcastReceiver(DownloadMessageDialog downloadMessageDialog) {
        this.a = downloadMessageDialog;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action) || !TextUtils.equals(action, "mb_dp_close_broadcast_receiver") || this.a == null) {
                return;
            }
            try {
                try {
                    try {
                        if (this.a.isShowing()) {
                            this.a.setRenderListener(null);
                            this.a.cancel();
                            this.a = null;
                        }
                        if (context != null) {
                            context.unregisterReceiver(this);
                        }
                    } catch (Exception e) {
                        s.a("CloseDialogBroadcastReceiver", e.getMessage());
                        if (context != null) {
                            context.unregisterReceiver(this);
                        }
                    }
                } catch (Exception e2) {
                    s.a("CloseDialogBroadcastReceiver", e2.getMessage());
                }
            } catch (Throwable th) {
                if (context != null) {
                    try {
                        context.unregisterReceiver(this);
                    } catch (Exception e3) {
                        s.a("CloseDialogBroadcastReceiver", e3.getMessage());
                    }
                }
                throw th;
            }
        }
    }
}
