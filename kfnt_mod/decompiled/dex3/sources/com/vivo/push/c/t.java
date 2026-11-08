package com.vivo.push.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.ss.android.socialbase.appdownloader.constants.InstallErrorCode;
import com.vivo.push.b.w;
import com.vivo.push.cache.ClientConfigManagerImpl;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class t extends com.vivo.push.e {
    public t(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException {
        if (this.a == null) {
            com.vivo.push.util.n.d("SendCommandTask", "SendCommandTask " + gVar + " ; mContext is Null");
            return;
        }
        if (gVar == null) {
            com.vivo.push.util.n.d("SendCommandTask", "SendCommandTask pushCommand is Null");
            return;
        }
        com.vivo.push.model.b bVarA = com.vivo.push.util.r.a(this.a);
        int i = gVar.a;
        if (i != 0) {
            if (i == 2009) {
                com.vivo.push.util.n.a(ClientConfigManagerImpl.getInstance(this.a).isDebug());
                if (com.vivo.push.util.n.a()) {
                    com.vivo.push.d.a().g.a();
                    com.vivo.push.util.a aVar = new com.vivo.push.util.a();
                    aVar.a(this.a, "com.vivo.push_preferences.hybridapptoken_v1");
                    aVar.a();
                    com.vivo.push.util.a aVar2 = new com.vivo.push.util.a();
                    aVar2.a(this.a, "com.vivo.push_preferences.appconfig_v1");
                    aVar2.a();
                    if (!com.vivo.push.d.a().j) {
                        ClientConfigManagerImpl.getInstance(this.a).clearPush();
                    }
                }
            } else if (i == 2011) {
                com.vivo.push.util.n.a(ClientConfigManagerImpl.getInstance(this.a).isDebug(((w) gVar).c));
            } else {
                switch (i) {
                    case 2002:
                    case 2003:
                    case InstallErrorCode.ERROR_CANCELED /* 2004 */:
                    case 2005:
                        if (bVarA == null || bVarA.e) {
                            com.vivo.push.d.a().a(((com.vivo.push.b.c) gVar).e, 1005);
                            break;
                        } else {
                            com.vivo.push.b.c cVar = (com.vivo.push.b.c) gVar;
                            int iA = com.vivo.push.util.q.a(cVar);
                            if (iA != 0) {
                                com.vivo.push.d.a().a(cVar.e, iA);
                                return;
                            }
                        }
                        break;
                }
            }
        } else if (com.vivo.push.d.a().j) {
            Context context = this.a;
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
            List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
            if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
                com.vivo.push.util.n.d("ModuleUtil", "disableDeprecatedService is null");
            } else {
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, listQueryIntentServices.get(0).serviceInfo.name);
                if (packageManager.getComponentEnabledSetting(componentName) != 2) {
                    packageManager.setComponentEnabledSetting(componentName, 2, 1);
                }
            }
            Context context2 = this.a;
            Intent intent2 = new Intent();
            intent2.setPackage(context2.getPackageName());
            intent2.setClassName(context2.getPackageName(), "com.vivo.push.sdk.service.LinkProxyActivity");
            List<ResolveInfo> listQueryIntentActivities = context2.getPackageManager().queryIntentActivities(intent2, 128);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
                com.vivo.push.util.n.d("ModuleUtil", "disableDeprecatedActivity is null");
            } else {
                PackageManager packageManager2 = context2.getPackageManager();
                ComponentName componentName2 = new ComponentName(context2, listQueryIntentActivities.get(0).activityInfo.name);
                if (packageManager2.getComponentEnabledSetting(componentName2) != 2) {
                    packageManager2.setComponentEnabledSetting(componentName2, 2, 1);
                }
            }
        }
        if (bVarA == null) {
            com.vivo.push.util.n.d("SendCommandTask", "SendCommandTask " + gVar + " ; pushPkgInfo is Null");
            return;
        }
        String str = bVarA.a;
        if (bVarA.e) {
            com.vivo.push.d.a().a(((com.vivo.push.b.c) gVar).e, 1004);
            gVar = new com.vivo.push.b.e();
            com.vivo.push.util.n.d("SendCommandTask", "SendCommandTask " + gVar + " ; pkgName is InBlackList ");
        }
        com.vivo.push.a.a.a(this.a, str, gVar);
    }
}
