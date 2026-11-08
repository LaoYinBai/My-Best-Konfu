package com.umeng.message;

import android.content.Intent;
import android.os.Bundle;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.agoo.BaseNotifyClickActivity;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengNotifyClickActivity extends BaseNotifyClickActivity {
    private static final String a = "com.umeng.message.UmengNotifyClickActivity";

    protected void a(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void a() {
        super.onStart();
    }

    @Override // com.taobao.agoo.BaseNotifyClickActivity
    public void onMessage(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("body");
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "onMessage():[" + stringExtra + "]");
            try {
                UMessage uMessage = new UMessage(new JSONObject(stringExtra));
                uMessage.message_id = intent.getStringExtra(AdDownloadModel.JsonKey.ID);
                uMessage.task_id = intent.getStringExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID);
                UTrack.getInstance(this).trackMiPushMsgClick(uMessage);
            } catch (JSONException e) {
                e.printStackTrace();
                UMLog uMLog2 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 2, e.toString());
            }
        } catch (Throwable unused) {
        }
    }

    protected void a(Intent intent) {
        super.onNewIntent(intent);
    }
}
