package com.taobao.accs.antibrush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.g;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.util.HttpRequest;
import java.util.concurrent.ScheduledFuture;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiBrush {
    private static final String ANTI_ATTACK_ACTION = "mtopsdk.mtop.antiattack.checkcode.validate.activity_action";
    private static final String ANTI_ATTACK_RESULT_ACTION = "mtopsdk.extra.antiattack.result.notify.action";
    public static final int STATUS_BRUSH = 419;
    private static final String TAG = "AntiBrush";
    private static String mHost;
    private static volatile boolean mIsInCheckCodeActivity;
    private static ScheduledFuture<?> mTimeoutTask;
    private BroadcastReceiver mAntiAttackReceiver = null;
    private Context mContext;

    /* JADX WARN: Removed duplicated region for block: B:25:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean checkAntiBrush(java.net.URL r7, java.util.Map<java.lang.Integer, java.lang.String> r8) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            if (r8 == 0) goto L8a
            android.content.Context r2 = r6.mContext     // Catch: java.lang.Throwable -> L87
            boolean r2 = com.taobao.accs.utl.UtilityImpl.j(r2)     // Catch: java.lang.Throwable -> L87
            if (r2 == 0) goto L8a
            com.taobao.accs.base.TaoBaseService$ExtHeaderType r2 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.TYPE_STATUS     // Catch: java.lang.Throwable -> L87
            int r2 = r2.ordinal()     // Catch: java.lang.Throwable -> L87
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L87
            java.lang.Object r2 = r8.get(r2)     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L87
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L87
            if (r3 == 0) goto L24
            r2 = 0
            goto L2c
        L24:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L87
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L87
        L2c:
            r3 = 419(0x1a3, float:5.87E-43)
            if (r2 != r3) goto L8a
            com.taobao.accs.base.TaoBaseService$ExtHeaderType r2 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.TYPE_LOCATION     // Catch: java.lang.Throwable -> L87
            int r2 = r2.ordinal()     // Catch: java.lang.Throwable -> L87
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L87
            java.lang.Object r8 = r8.get(r2)     // Catch: java.lang.Throwable -> L87
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L87
            boolean r2 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L87
            if (r2 != 0) goto L8a
            java.lang.String r2 = "AntiBrush"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
            r3.<init>()     // Catch: java.lang.Throwable -> L87
            java.lang.String r4 = "start anti bursh location:"
            r3.append(r4)     // Catch: java.lang.Throwable -> L87
            r3.append(r8)     // Catch: java.lang.Throwable -> L87
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L87
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L87
            com.taobao.accs.utl.ALog.e(r2, r3, r4)     // Catch: java.lang.Throwable -> L87
            r6.handleantiBrush(r8)     // Catch: java.lang.Throwable -> L87
            java.util.concurrent.ScheduledFuture<?> r8 = com.taobao.accs.antibrush.AntiBrush.mTimeoutTask     // Catch: java.lang.Throwable -> L87
            r2 = 0
            if (r8 == 0) goto L6d
            java.util.concurrent.ScheduledFuture<?> r8 = com.taobao.accs.antibrush.AntiBrush.mTimeoutTask     // Catch: java.lang.Throwable -> L87
            r8.cancel(r0)     // Catch: java.lang.Throwable -> L87
            com.taobao.accs.antibrush.AntiBrush.mTimeoutTask = r2     // Catch: java.lang.Throwable -> L87
        L6d:
            com.taobao.accs.antibrush.a r8 = new com.taobao.accs.antibrush.a     // Catch: java.lang.Throwable -> L87
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L87
            r3 = 60000(0xea60, double:2.9644E-319)
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L87
            java.util.concurrent.ScheduledFuture r8 = com.taobao.accs.common.ThreadPoolExecutorFactory.schedule(r8, r3, r5)     // Catch: java.lang.Throwable -> L87
            com.taobao.accs.antibrush.AntiBrush.mTimeoutTask = r8     // Catch: java.lang.Throwable -> L87
            if (r7 != 0) goto L80
            goto L84
        L80:
            java.lang.String r2 = r7.getHost()     // Catch: java.lang.Throwable -> L87
        L84:
            com.taobao.accs.antibrush.AntiBrush.mHost = r2     // Catch: java.lang.Throwable -> L87
            goto L8b
        L87:
            r7 = move-exception
            r0 = 0
            goto Laf
        L8a:
            r0 = 0
        L8b:
            java.lang.String r7 = com.taobao.accs.client.GlobalClientInfo.c     // Catch: java.lang.Throwable -> Lae
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lae
            if (r7 != 0) goto Lb8
            java.lang.String r7 = com.taobao.accs.antibrush.AntiBrush.mHost     // Catch: java.lang.Throwable -> Lae
            java.lang.String r7 = com.taobao.accs.antibrush.b.a(r7)     // Catch: java.lang.Throwable -> Lae
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lae
            if (r7 == 0) goto Lb8
            java.lang.String r7 = "AntiBrush"
            java.lang.String r8 = "cookie invalid, clear"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lae
            com.taobao.accs.utl.ALog.e(r7, r8, r2)     // Catch: java.lang.Throwable -> Lae
            android.content.Context r7 = r6.mContext     // Catch: java.lang.Throwable -> Lae
            com.taobao.accs.utl.UtilityImpl.q(r7)     // Catch: java.lang.Throwable -> Lae
            goto Lb8
        Lae:
            r7 = move-exception
        Laf:
            java.lang.String r8 = "AntiBrush"
            java.lang.String r2 = "checkAntiBrush error"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.taobao.accs.utl.ALog.e(r8, r2, r7, r1)
        Lb8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.antibrush.AntiBrush.checkAntiBrush(java.net.URL, java.util.Map):boolean");
    }

    public AntiBrush(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static void onResult(Context context, boolean z) {
        mIsInCheckCodeActivity = false;
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(Constants.KEY_COMMAND, 104);
        intent.putExtra(Constants.KEY_ANTI_BRUSH_RET, z);
        g.a(context, intent);
        if (mTimeoutTask != null) {
            mTimeoutTask.cancel(true);
            mTimeoutTask = null;
        }
        if (mHost != null) {
            UtilityImpl.b(context, b.a(mHost));
        }
    }

    private void handleantiBrush(String str) {
        if (mIsInCheckCodeActivity) {
            ALog.e(TAG, "handleantiBrush return", "mIsInCheckCodeActivity", Boolean.valueOf(mIsInCheckCodeActivity));
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction(ANTI_ATTACK_ACTION);
            intent.setPackage(this.mContext.getPackageName());
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent.putExtra(HttpRequest.HEADER_LOCATION, str);
            ALog.e(TAG, "handleAntiBrush:", new Object[0]);
            this.mContext.startActivity(intent);
            mIsInCheckCodeActivity = true;
            if (this.mAntiAttackReceiver == null) {
                this.mAntiAttackReceiver = new AntiReceiver();
            }
            this.mContext.registerReceiver(this.mAntiAttackReceiver, new IntentFilter(ANTI_ATTACK_RESULT_ACTION));
        } catch (Throwable th) {
            ALog.e(TAG, "handleantiBrush", th, new Object[0]);
        }
    }

    public static class AntiReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                try {
                    String stringExtra = intent.getStringExtra("Result");
                    ALog.e(AntiBrush.TAG, "anti onReceive result: " + stringExtra, new Object[0]);
                    AntiBrush.onResult(GlobalClientInfo.getContext(), stringExtra.equalsIgnoreCase("success"));
                } catch (Exception e) {
                    ALog.e(AntiBrush.TAG, "anti onReceive", e, new Object[0]);
                    AntiBrush.onResult(GlobalClientInfo.getContext(), false);
                }
            } catch (Throwable th) {
                AntiBrush.onResult(GlobalClientInfo.getContext(), false);
                throw th;
            }
        }
    }
}
