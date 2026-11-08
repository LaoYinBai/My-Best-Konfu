package com.qq.e.comm.managers.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import com.qq.e.comm.constants.CustomPkgConstants;
import com.qq.e.comm.constants.Sig;
import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static boolean a(Context context, File file, File file2) {
        AssetManager assets = context.getAssets();
        try {
            if (Arrays.binarySearch(assets.list(CustomPkgConstants.getAssetPluginDir()), CustomPkgConstants.getAssetPluginName()) < 0) {
                return false;
            }
            String str = CustomPkgConstants.getAssetPluginDir() + File.separator + CustomPkgConstants.getAssetPluginName();
            String str2 = Sig.ASSET_PLUGIN_SIG;
            if (str2 == null) {
                str2 = "";
            }
            StringUtil.writeTo("1300#####" + str2, file2);
            if (StringUtil.isEmpty(CustomPkgConstants.getAssetPluginXorKey())) {
                return FileUtil.copyTo(assets.open(str), file);
            }
            InputStream inputStreamOpen = assets.open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = CustomPkgConstants.getAssetPluginXorKey().getBytes(Charset.forName("UTF-8"));
            byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
            int length = bytes.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = inputStreamOpen.read(bArr);
                if (i3 <= 0) {
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    return true;
                }
                int i4 = i2;
                int i5 = i;
                int i6 = 0;
                while (i6 < i3) {
                    int i7 = i5 + 1;
                    if (i5 >= 64) {
                        bArr[i6] = (byte) (bArr[i6] ^ bytes[i4 % length]);
                        i4++;
                    }
                    i6++;
                    i5 = i7;
                }
                fileOutputStream.write(bArr, 0, i3);
                i = i5;
                i2 = i4;
            }
        } catch (Throwable th) {
            GDTLogger.e("插件加载失败", th);
            a.a(th, th.getMessage());
            return false;
        }
    }
}
