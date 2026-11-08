package com.huawei.hms.update.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.text.NumberFormat;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends b {
    private ProgressBar a;
    private TextView b;
    private int c = 0;
    private DialogInterface.OnKeyListener d = new a();

    @Override // com.huawei.hms.update.ui.b
    public AlertDialog a() {
        AlertDialog.Builder builder = new AlertDialog.Builder(f(), g());
        View viewInflate = View.inflate(f(), ResourceLoaderUtil.getLayoutId("hms_download_progress"), null);
        builder.setView(viewInflate);
        builder.setCancelable(false);
        builder.setOnKeyListener(this.d);
        this.a = (ProgressBar) viewInflate.findViewById(ResourceLoaderUtil.getIdId("download_info_progress"));
        this.b = (TextView) viewInflate.findViewById(ResourceLoaderUtil.getIdId("hms_progress_text"));
        b(this.c);
        return builder.create();
    }

    public void a(int i) {
        this.c = i;
    }

    void b(int i) {
        Activity activityF = f();
        if (activityF == null || activityF.isFinishing()) {
            HMSLog.w("DownloadProgress", "In setDownloading, The activity is null or finishing.");
        } else {
            if (this.b == null || this.a == null) {
                return;
            }
            this.a.setProgress(i);
            this.b.setText(NumberFormat.getPercentInstance().format(i / 100.0f));
        }
    }

    private static class a implements DialogInterface.OnKeyListener {
        private a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4 && keyEvent.getRepeatCount() == 0;
        }
    }
}
