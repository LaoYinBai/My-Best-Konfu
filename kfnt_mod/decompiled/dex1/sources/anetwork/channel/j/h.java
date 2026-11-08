package anetwork.channel.j;

import anet.channel.thread.ThreadPoolExecutorFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f726a;

    h(g gVar) {
        this.f726a = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ThreadPoolExecutorFactory.submitPriorityTask(this.f726a, ThreadPoolExecutorFactory.Priority.HIGH);
    }
}
