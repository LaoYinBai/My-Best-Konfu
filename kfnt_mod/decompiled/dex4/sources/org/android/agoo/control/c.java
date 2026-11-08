package org.android.agoo.control;

import com.taobao.accs.utl.ALog;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.agoo.common.MsgDO;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class c implements Runnable {
    final /* synthetic */ AgooFactory a;

    c(AgooFactory agooFactory) {
        this.a = agooFactory;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        ArrayList<MsgDO> arrayListB = this.a.messageService.b();
        if (arrayListB == null || arrayListB.size() <= 0) {
            return;
        }
        ALog.e("AgooFactory", "reportCacheMsg", new Object[]{"size", Integer.valueOf(arrayListB.size())});
        Iterator<MsgDO> it = arrayListB.iterator();
        while (it.hasNext()) {
            MsgDO next = it.next();
            if (next != null) {
                next.isFromCache = true;
                this.a.notifyManager.report(next, null);
            }
        }
    }
}
