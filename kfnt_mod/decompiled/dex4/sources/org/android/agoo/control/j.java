package org.android.agoo.control;

import org.android.agoo.message.MessageService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class j implements Runnable {
    final /* synthetic */ BaseIntentService a;

    j(BaseIntentService baseIntentService) {
        this.a = baseIntentService;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.taobao.accs.client.a.g.incrementAndGet();
        this.a.notifyManager = new NotifManager();
        this.a.notifyManager.init(this.a.getApplicationContext());
        this.a.messageService = new MessageService();
        this.a.messageService.a(this.a.getApplicationContext());
        this.a.agooFactory = new AgooFactory();
        this.a.agooFactory.init(this.a.getApplicationContext(), this.a.notifyManager, this.a.messageService);
    }
}
