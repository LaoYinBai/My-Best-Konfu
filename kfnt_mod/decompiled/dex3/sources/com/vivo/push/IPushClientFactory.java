package com.vivo.push;

import android.content.Intent;
import com.vivo.push.c.o;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IPushClientFactory {
    o createReceiveTask(g gVar);

    g createReceiverCommand(Intent intent);

    e createTask(g gVar);
}
