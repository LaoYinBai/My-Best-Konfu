package com.taobao.tlog.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.orange.OrangeConfig;
import com.taobao.orange.OrangeConfigListener;
import com.taobao.tao.log.LogLevel;
import com.taobao.tao.log.TLogController;
import com.taobao.tao.log.TLogInitializer;
import com.taobao.tao.log.TLogUtils;
import com.umeng.message.common.inter.ITagManager;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TLogConfigSwitchReceiver {
    public static final String REMOTE_DEBUGER_ANDROID = "remote_debug";
    private static final String TAG = "TLogConfigSwitchReceiver";

    public static void init(final Context context) {
        OrangeConfig.getInstance().registerListener(new String[]{REMOTE_DEBUGER_ANDROID}, new OrangeConfigListener() { // from class: com.taobao.tlog.adapter.TLogConfigSwitchReceiver.1
            public void onConfigUpdate(String str) {
                Map configs = OrangeConfig.getInstance().getConfigs(str);
                if (configs != null) {
                    SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                    String str2 = (String) configs.get("tlog_destroy");
                    String str3 = (String) configs.get("tlog_switch");
                    String str4 = (String) configs.get("tlog_level");
                    String str5 = (String) configs.get("tlog_module");
                    String str6 = (String) configs.get("tlog_start_up_sampling");
                    if (TLogController.getInstance() == null) {
                        return;
                    }
                    Log.e(TLogConfigSwitchReceiver.TAG, "tlogDestroy is: " + str2 + "tlogSwitch is : " + str3 + "  tlogLevel is : " + str4 + "  tlogModule is : " + str5);
                    if (!TextUtils.isEmpty(str2) && str2.equals(ITagManager.STATUS_TRUE)) {
                        Log.e(TLogConfigSwitchReceiver.TAG, "destroy tlog");
                        TLogController.getInstance().closeLog();
                        TLogUtils.cleanDir(new File(TLogInitializer.getInstance().getFileDir()));
                        editorEdit.putBoolean("tlog_destroy", true).apply();
                        return;
                    }
                    editorEdit.putBoolean("tlog_destroy", false).apply();
                    if (!TextUtils.isEmpty(str3) && str3.equals("false")) {
                        Log.e(TLogConfigSwitchReceiver.TAG, "switch tlog function");
                        TLogController.getInstance().closeLog();
                        editorEdit.putBoolean("tlog_switch", false).apply();
                    } else {
                        editorEdit.putBoolean("tlog_switch", true).apply();
                    }
                    if (!TextUtils.isEmpty(str4)) {
                        Log.e(TLogConfigSwitchReceiver.TAG, "change tlog level : " + str4);
                        LogLevel logLevelConvertLogLevel = TLogUtils.convertLogLevel(str4);
                        editorEdit.putString("tlog_level", str4).apply();
                        TLogController.getInstance().setLogLevel(logLevelConvertLogLevel);
                    }
                    if (!TextUtils.isEmpty(str5)) {
                        Log.e(TLogConfigSwitchReceiver.TAG, "tlog model : " + str5);
                        if ("off".equals(str5)) {
                            TLogController.getInstance().cleanModuleFilter();
                            editorEdit.remove("tlog_module").apply();
                        } else {
                            Map mapMakeModule = TLogUtils.makeModule(str5);
                            if (mapMakeModule != null && mapMakeModule.size() > 0) {
                                TLogController.getInstance().addModuleFilter(mapMakeModule);
                                editorEdit.putString("tlog_module", str5).apply();
                            }
                        }
                    }
                    if (TextUtils.isEmpty(str6)) {
                        return;
                    }
                    Log.e(TLogConfigSwitchReceiver.TAG, "change tlog start up sampling : " + str6);
                    try {
                        TLogInitializer.getInstance().startUpSampling(Integer.valueOf(Integer.parseInt(str6)));
                        return;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return;
                    }
                }
                Log.i(TLogConfigSwitchReceiver.TAG, "TLogConfigSwitchReceiver --> the config is null!");
            }
        });
    }
}
