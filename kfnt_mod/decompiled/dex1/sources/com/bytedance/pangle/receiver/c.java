package com.bytedance.pangle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {
    private static c d;

    /* renamed from: a, reason: collision with root package name */
    public final Map<String, a> f4675a = new ConcurrentHashMap();

    /* renamed from: b, reason: collision with root package name */
    public final Map<PluginBroadcastReceiver, BroadcastReceiver> f4676b = new ConcurrentHashMap();

    /* renamed from: c, reason: collision with root package name */
    public final Set<Integer> f4677c = new CopyOnWriteArraySet();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f4678a;

        /* renamed from: b, reason: collision with root package name */
        public final Set<PluginBroadcastReceiver> f4679b = new CopyOnWriteArraySet();

        public final void a(PluginBroadcastReceiver pluginBroadcastReceiver) {
            if (pluginBroadcastReceiver != null) {
                ZeusLogger.i(ZeusLogger.TAG_RECEIVER, "plugin-receiver:" + pluginBroadcastReceiver.getClass().getSimpleName() + ",action=" + this.f4678a + "[注册完成]");
                this.f4679b.add(pluginBroadcastReceiver);
            }
        }

        public final void a(Context context, Intent intent) {
            if (this.f4679b == null || this.f4679b.size() <= 0) {
                return;
            }
            for (PluginBroadcastReceiver pluginBroadcastReceiver : this.f4679b) {
                if (pluginBroadcastReceiver != null) {
                    try {
                        pluginBroadcastReceiver.onReceive(context, intent);
                    } catch (Throwable th) {
                        ZeusLogger.e(ZeusLogger.TAG_RECEIVER, "plugin-receiver->action:" + (intent != null ? intent.getAction() : "") + "[exception]:", th);
                    }
                }
            }
        }
    }

    private c() {
    }

    public static c a() {
        if (d == null) {
            synchronized (com.bytedance.pangle.service.a.a.class) {
                if (d == null) {
                    d = new c();
                }
            }
        }
        return d;
    }

    public final void a(IntentFilter intentFilter, PluginBroadcastReceiver pluginBroadcastReceiver) {
        if (intentFilter == null || intentFilter.actionsIterator() == null) {
            return;
        }
        Iterator<String> itActionsIterator = intentFilter.actionsIterator();
        while (itActionsIterator.hasNext()) {
            String next = itActionsIterator.next();
            if (next != null) {
                a aVar = this.f4675a.get(next);
                if (aVar != null) {
                    aVar.a(pluginBroadcastReceiver);
                } else {
                    a aVar2 = new a();
                    aVar2.f4678a = next;
                    aVar2.a(pluginBroadcastReceiver);
                    this.f4675a.put(next, aVar2);
                }
            }
        }
    }

    public final void a(Context context, Intent intent) {
        a value;
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        if (this.f4675a == null || this.f4675a.size() <= 0) {
            return;
        }
        for (Map.Entry<String, a> entry : this.f4675a.entrySet()) {
            if (action.equals(entry.getKey()) && (value = entry.getValue()) != null) {
                ZeusLogger.d(ZeusLogger.TAG_RECEIVER, "action[" + action + "] match success ！ invoke onReceiver");
                value.a(context, intent);
            }
        }
    }
}
