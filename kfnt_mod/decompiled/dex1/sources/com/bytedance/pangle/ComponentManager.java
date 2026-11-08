package com.bytedance.pangle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.receiver.PluginBroadcastReceiver;
import com.bytedance.pangle.receiver.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ComponentManager {
    public static HashMap<String, String> target2StubActivity = new HashMap<>();
    public static HashMap<String, String> targetString2StubActivity = new HashMap<>();
    public static HashMap<String, String> targetString2PluginPkgName = new HashMap<>();
    public static HashMap<String, String> stubString2PluginPkgName = new HashMap<>();

    public static void registerActivity(String str, String str2, String... strArr) {
        if (strArr != null) {
            for (String str3 : strArr) {
                String str4 = targetString2PluginPkgName.get(str3);
                if (str4 != null && TextUtils.equals(str4, str)) {
                    throw new RuntimeException(String.format("%s is repeated in %s and %s", str3, str, str4));
                }
                target2StubActivity.put(str3, str2);
                targetString2StubActivity.put(str3, str2);
                targetString2PluginPkgName.put(str3, str);
                stubString2PluginPkgName.put(str2, str);
            }
        }
    }

    public static void startActivity(Context context, Intent intent, String str) {
        startActivity(context, intent, null, str);
    }

    public static void startActivity(Context context, Intent intent, Bundle bundle, String str) {
        if (context instanceof PluginContext) {
            context = ((PluginContext) context).mOriginContext;
        }
        if (intent.getComponent() != null) {
            String className = intent.getComponent().getClassName();
            Zeus.loadPlugin(str);
            String str2 = targetString2StubActivity.get(className);
            if (!TextUtils.isEmpty(str2)) {
                intent.setComponent(new ComponentName(context, str2));
                intent.putExtra("targetPlugin", className);
            }
            context.startActivity(intent, bundle);
            return;
        }
        try {
            context.startActivity(intent, bundle);
        } catch (Throwable th) {
            throw new RuntimeException("隐式意图 todo", th);
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, String str) {
        startActivityForResult(activity, intent, i, null, str);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle, String str) {
        if (intent.getComponent() != null) {
            String className = intent.getComponent().getClassName();
            Zeus.loadPlugin(str);
            String str2 = targetString2StubActivity.get(className);
            if (!TextUtils.isEmpty(str2)) {
                intent.setComponent(new ComponentName(activity, str2));
                intent.putExtra("targetPlugin", className);
            }
            activity.startActivityForResult(intent, i, bundle);
            return;
        }
        try {
            activity.startActivityForResult(intent, i, bundle);
        } catch (Throwable th) {
            throw new RuntimeException("隐式意图 todo", th);
        }
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, String str) {
        Zeus.loadPlugin(str);
        if (pluginBroadcastReceiver == null) {
            return null;
        }
        com.bytedance.pangle.receiver.c cVarA = com.bytedance.pangle.receiver.c.a();
        if (pluginBroadcastReceiver == null || intentFilter == null || intentFilter.actionsIterator() == null) {
            return null;
        }
        com.bytedance.pangle.receiver.a aVar = new com.bytedance.pangle.receiver.a();
        Intent intentRegisterReceiver = context.registerReceiver(aVar, intentFilter);
        cVarA.f4676b.put(pluginBroadcastReceiver, aVar);
        cVarA.a(intentFilter, pluginBroadcastReceiver);
        return intentRegisterReceiver;
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, String str, Handler handler, String str2) {
        Zeus.loadPlugin(str2);
        if (pluginBroadcastReceiver == null) {
            return null;
        }
        com.bytedance.pangle.receiver.c cVarA = com.bytedance.pangle.receiver.c.a();
        if (pluginBroadcastReceiver == null || intentFilter == null || intentFilter.actionsIterator() == null) {
            return null;
        }
        com.bytedance.pangle.receiver.a aVar = new com.bytedance.pangle.receiver.a();
        Intent intentRegisterReceiver = context.registerReceiver(aVar, intentFilter, str, handler);
        cVarA.f4676b.put(pluginBroadcastReceiver, aVar);
        if (handler != null) {
            cVarA.f4677c.add(Integer.valueOf(aVar.hashCode()));
        }
        cVarA.a(intentFilter, pluginBroadcastReceiver);
        return intentRegisterReceiver;
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, int i, String str) {
        Zeus.loadPlugin(str);
        if (pluginBroadcastReceiver == null) {
            return null;
        }
        com.bytedance.pangle.receiver.c cVarA = com.bytedance.pangle.receiver.c.a();
        if (pluginBroadcastReceiver == null || intentFilter == null || intentFilter.actionsIterator() == null || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        com.bytedance.pangle.receiver.a aVar = new com.bytedance.pangle.receiver.a();
        Intent intentRegisterReceiver = context.registerReceiver(aVar, intentFilter, i);
        cVarA.f4676b.put(pluginBroadcastReceiver, aVar);
        cVarA.a(intentFilter, pluginBroadcastReceiver);
        return intentRegisterReceiver;
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i, String str2) {
        Zeus.loadPlugin(str2);
        if (pluginBroadcastReceiver == null) {
            return null;
        }
        com.bytedance.pangle.receiver.c cVarA = com.bytedance.pangle.receiver.c.a();
        if (pluginBroadcastReceiver == null || intentFilter == null || intentFilter.actionsIterator() == null || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        com.bytedance.pangle.receiver.a aVar = new com.bytedance.pangle.receiver.a();
        Intent intentRegisterReceiver = context.registerReceiver(aVar, intentFilter, str, handler, i);
        cVarA.f4676b.put(pluginBroadcastReceiver, aVar);
        if (handler != null) {
            cVarA.f4677c.add(Integer.valueOf(aVar.hashCode()));
        }
        cVarA.a(intentFilter, pluginBroadcastReceiver);
        return intentRegisterReceiver;
    }

    public static void unregisterReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver) {
        boolean zRemove;
        if (pluginBroadcastReceiver != null) {
            com.bytedance.pangle.receiver.c cVarA = com.bytedance.pangle.receiver.c.a();
            Iterator<Map.Entry<String, c.a>> it = cVarA.f4675a.entrySet().iterator();
            while (it.hasNext()) {
                c.a value = it.next().getValue();
                if (value != null && pluginBroadcastReceiver != null) {
                    try {
                        if (value.f4679b.size() > 0 && (zRemove = value.f4679b.remove(pluginBroadcastReceiver))) {
                            ZeusLogger.d(ZeusLogger.TAG_RECEIVER, "unregisterReceiver->[移除Action]:" + value.f4678a + ",receiver.hashCode()=" + pluginBroadcastReceiver.hashCode() + ",是否成功，success:" + zRemove);
                        }
                    } catch (Throwable th) {
                        ZeusLogger.e(ZeusLogger.TAG_RECEIVER, "unregisterReceiver-plugin-receiver->action:" + value.f4678a + "[exception]:", th);
                    }
                }
                BroadcastReceiver broadcastReceiver = cVarA.f4676b.get(pluginBroadcastReceiver);
                if (broadcastReceiver != null) {
                    try {
                        cVarA.f4677c.remove(Integer.valueOf(broadcastReceiver.hashCode()));
                        cVarA.f4676b.remove(pluginBroadcastReceiver);
                        context.unregisterReceiver(broadcastReceiver);
                    } catch (Throwable th2) {
                        ZeusLogger.e(ZeusLogger.TAG_RECEIVER, "unregisterReceiver-移除系统注册的广播发生异常:", th2);
                    }
                }
            }
        }
    }
}
