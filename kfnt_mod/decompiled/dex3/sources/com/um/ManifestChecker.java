package com.um;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.f;
import com.ss.android.push.b;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.j;
import com.umeng.message.MsgConstant;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ManifestChecker {
    static boolean checkManifest(String str, Context context) throws PackageManager.NameNotFoundException {
        return checkComponents(context, str) & checkKeys(str) & checkPermission(context, str);
    }

    private static boolean checkComponents(Context context, String str) throws PackageManager.NameNotFoundException {
        boolean zB = f.b(context, str, "UmengPush", Arrays.asList(a.a.d(j.channelService).a(context.getPackageName() + ":pushservice").a(new a.b(Arrays.asList("com.taobao.accs.intent.action.SERVICE"))).a(new a.b(Arrays.asList("com.taobao.accs.intent.action.ELECTION"))).a(), a.a.d(j.msgService).a(context.getPackageName()).a(new a.b(Arrays.asList(Constants.ACTION_RECEIVE))).a(), a.a.d("com.taobao.accs.internal.AccsJobService").a(context.getPackageName() + ":pushservice").b("android.permission.BIND_JOB_SERVICE").a(), a.a.d("com.taobao.accs.ChannelService$KernelService").a(context.getPackageName() + ":pushservice").a(), a.a.d("org.android.agoo.accs.AgooService").a(context.getPackageName()).a(new a.b(Arrays.asList(Constants.ACTION_RECEIVE))).a(), a.a.d("com.umeng.message.UmengIntentService").a(context.getPackageName() + ":pushservice").a(new a.b(Arrays.asList("org.agoo.android.intent.action.RECEIVE"))).a(), a.a.d("com.umeng.message.XiaomiIntentService").a(context.getPackageName() + ":pushservice").a(new a.b(Arrays.asList("org.agoo.android.intent.action.RECEIVE"))).a(), a.a.d("com.umeng.message.UmengMessageIntentReceiverService").a(context.getPackageName() + ":pushservice").a(new a.b(Arrays.asList("org.android.agoo.client.MessageReceiverService"))).a(), a.a.d("com.umeng.message.UmengMessageCallbackHandlerService").b("android.permission.BIND_JOB_SERVICE").a(context.getPackageName()).a(new a.b(Arrays.asList(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION))).a(new a.b(Arrays.asList(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION))).a(new a.b(Arrays.asList(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION))).a(new a.b(Arrays.asList(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION))).a(new a.b(Arrays.asList(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION))).a(), a.a.d("com.umeng.UmengMessageHandler").a(context.getPackageName()).b("android.permission.BIND_JOB_SERVICE").a(), a.a.d("com.umeng.message.UmengDownloadResourceService").a(context.getPackageName()).b("android.permission.BIND_JOB_SERVICE").a()));
        a.a aVarD = a.a.d("com.taobao.accs.EventReceiver");
        StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        sb.append(":pushservice");
        a.a aVarA = aVarD.a(sb.toString()).a(new a.b(Arrays.asList("android.intent.action.BOOT_COMPLETED"))).a(new a.b(Arrays.asList("android.net.conn.CONNECTIVITY_CHANGE")));
        List listAsList = Arrays.asList("android.intent.action.PACKAGE_REMOVED");
        a.a aVarD2 = a.a.d("com.taobao.accs.ServiceReceiver");
        a.a aVarD3 = a.a.d("com.taobao.agoo.AgooCommondReceiver");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(context.getPackageName());
        sb2.append(":pushservice");
        boolean zC = f.c(context, str, "UmengPush", Arrays.asList(aVarA.a(new a.b(listAsList, (List) null, Uri.parse("package://" + context.getPackageName()))).a(new a.b(Arrays.asList("android.intent.action.USER_PRESENT"))).a(), aVarD2.a(context.getPackageName() + ":pushservice").a(new a.b(Arrays.asList(Constants.ACTION_COMMAND))).a(new a.b(Arrays.asList(Constants.ACTION_START_FROM_AGOO))).a(), aVarD3.a(sb2.toString()).a(new a.b(Arrays.asList(context.getPackageName() + ".intent.action.COMMAND"))).a(new a.b(Arrays.asList("android.intent.action.PACKAGE_REMOVED"), (List) null, Uri.parse("package://" + context.getPackageName()))).a(), a.a.d("com.umeng.message.NotificationProxyBroadcastReceiver").a(context.getPackageName()).a()));
        a.a aVarA2 = a.a.d("com.umeng.message.provider.MessageProvider").a(context.getPackageName());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(context.getPackageName());
        sb3.append(".umeng.message");
        return zB && f.e(context, str, "UmengPush", Arrays.asList(aVarA2.c(sb3.toString()).a())) && zC;
    }

    private static boolean checkPermission(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.a(context, str, "UmengPush 错误,", Arrays.asList(MsgConstant.PERMISSION_INTERNET, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, MsgConstant.PERMISSION_ACCESS_WIFI_STATE, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, MsgConstant.PERMISSION_WAKE_LOCK, MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_BROADCAST_PACKAGE_ADDED, MsgConstant.PERMISSION_BROADCAST_PACKAGE_CHANGED, MsgConstant.PERMISSION_BROADCAST_PACKAGE_INSTALL, MsgConstant.PERMISSION_BROADCAST_PACKAGE_REPLACED, MsgConstant.PERMISSION_GET_TASKS, MsgConstant.PERMISSION_RECEIVE_BOOT_COMPLETED));
    }

    private static boolean checkKeys(String str) {
        b bVarA = i.m().a();
        if (bVarA != null && !TextUtils.isEmpty((CharSequence) bVarA.f()) && !TextUtils.isEmpty((CharSequence) bVarA.e())) {
            return true;
        }
        i.l().b(str, "UmPush错误，Um Key 配置有误");
        return false;
    }
}
