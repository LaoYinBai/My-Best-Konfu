package com.kwai.filedownloader;

import com.kwai.filedownloader.a;
import com.kwai.filedownloader.message.MessageSnapshot;
import com.kwai.filedownloader.message.e;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class aa implements e.b {
    private boolean a(List<a.b> list, MessageSnapshot messageSnapshot) {
        if (list.size() > 1 && messageSnapshot.b() == -3) {
            Iterator<a.b> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().G().c(messageSnapshot)) {
                    return true;
                }
            }
        }
        Iterator<a.b> it2 = list.iterator();
        while (it2.hasNext()) {
            if (it2.next().G().b(messageSnapshot)) {
                return true;
            }
        }
        if (-4 == messageSnapshot.b()) {
            Iterator<a.b> it3 = list.iterator();
            while (it3.hasNext()) {
                if (it3.next().G().d(messageSnapshot)) {
                    return true;
                }
            }
        }
        if (list.size() == 1) {
            return list.get(0).G().a(messageSnapshot);
        }
        return false;
    }

    @Override // com.kwai.filedownloader.message.e.b
    public void a(MessageSnapshot messageSnapshot) {
        synchronized (Integer.toString(messageSnapshot.m()).intern()) {
            List<a.b> listB = h.a().b(messageSnapshot.m());
            if (listB.size() > 0) {
                a aVarF = listB.get(0).F();
                if (com.kwai.filedownloader.e.d.a) {
                    com.kwai.filedownloader.e.d.c(this, "~~~callback %s old[%s] new[%s] %d", Integer.valueOf(messageSnapshot.m()), Byte.valueOf(aVarF.v()), Byte.valueOf(messageSnapshot.b()), Integer.valueOf(listB.size()));
                }
                if (!a(listB, messageSnapshot)) {
                    StringBuilder sb = new StringBuilder("The event isn't consumed, id:" + messageSnapshot.m() + " status:" + ((int) messageSnapshot.b()) + " task-count:" + listB.size());
                    for (a.b bVar : listB) {
                        sb.append(" | ");
                        sb.append((int) bVar.F().v());
                    }
                    com.kwai.filedownloader.e.d.b(this, sb.toString(), new Object[0]);
                }
            } else {
                com.kwai.filedownloader.e.d.b(this, "Receive the event %d, but there isn't any running task in the upper layer", Byte.valueOf(messageSnapshot.b()));
            }
        }
    }
}
