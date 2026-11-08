package com.taobao.accs.net;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import java.util.Calendar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends f {
    private PendingIntent c;
    private AlarmManager d;

    public a(Context context) {
        super(context);
        try {
            this.d = (AlarmManager) this.a.getSystemService("alarm");
        } catch (Throwable th) {
            ALog.e("AlarmHeartBeatMgr", "AlarmHeartBeatMgr", th, new Object[0]);
        }
    }

    @Override // com.taobao.accs.net.f
    protected void a(int i) {
        if (this.d == null) {
            this.d = (AlarmManager) this.a.getSystemService("alarm");
        }
        if (this.d == null) {
            ALog.e("AlarmHeartBeatMgr", "setInner null", new Object[0]);
            return;
        }
        if (this.c == null) {
            Intent intent = new Intent();
            intent.setPackage(this.a.getPackageName());
            intent.addFlags(32);
            intent.setAction(Constants.ACTION_COMMAND);
            intent.putExtra(Constants.KEY_COMMAND, 201);
            this.c = PendingIntent.getBroadcast(this.a, 0, intent, 0);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(13, i);
        this.d.set(0, calendar.getTimeInMillis(), this.c);
    }
}
