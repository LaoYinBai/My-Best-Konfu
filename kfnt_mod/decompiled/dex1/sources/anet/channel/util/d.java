package anet.channel.util;

import anet.channel.statist.NetTypeStat;
import anet.channel.thread.ThreadPoolExecutorFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f608a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ NetTypeStat f609b;

    d(String str, NetTypeStat netTypeStat) {
        this.f608a = str;
        this.f609b = netTypeStat;
    }

    @Override // java.lang.Runnable
    public void run() {
        ThreadPoolExecutorFactory.submitPriorityTask(new e(this), ThreadPoolExecutorFactory.Priority.LOW);
    }
}
