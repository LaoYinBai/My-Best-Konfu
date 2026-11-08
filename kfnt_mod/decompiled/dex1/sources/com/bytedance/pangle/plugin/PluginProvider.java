package com.bytedance.pangle.plugin;

import androidx.annotation.Keep;
import com.bytedance.pangle.download.PluginDownloadBean;
import java.io.File;
import java.util.List;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface PluginProvider {
    File provideBuiltInPlugin();

    List<PluginDownloadBean> providePluginConfig();

    boolean useLocalPlugin();
}
