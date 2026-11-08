package com.ycbjie.ycupdatelib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UpdateUtils {
    public static String APP_UPDATE_DOWN_APK_PATH = "apk" + File.separator + "downApk";
    private static String mApkName;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DownloadStatus {
        public static final int ERROR = 9;
        public static final int FINISH = 8;
        public static final int PAUSED = 10;
        public static final int START = 6;
        public static final int UPLOADING = 7;
    }

    static String getLocalApkDownSavePath(String str) {
        ArrayList<String> extSDCardPath;
        mApkName = str;
        String str2 = APP_UPDATE_DOWN_APK_PATH + File.separator;
        String innerSDCardPath = getInnerSDCardPath();
        if ((!isExistSDCard() || TextUtils.isEmpty(innerSDCardPath)) && (extSDCardPath = getExtSDCardPath()) != null && extSDCardPath.size() > 0 && !TextUtils.isEmpty(extSDCardPath.get(0))) {
            innerSDCardPath = extSDCardPath.get(0);
        }
        String str3 = innerSDCardPath + File.separator + str2;
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str3 + str + Constants.APK_SUFFIX;
    }

    public static void clearDownload() {
        if (mApkName == null || mApkName.length() <= 0) {
            return;
        }
        deleteFile(getLocalApkDownSavePath(mApkName));
    }

    private static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + str + "成功！");
                return true;
            }
            System.out.println("删除单个文件" + str + "失败！");
            return false;
        }
        System.out.println("删除单个文件失败：" + str + "不存在！");
        return false;
    }

    private static boolean isExistSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    private static ArrayList<String> getExtSDCardPath() throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("mount").getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("extSdCard")) {
                    String str = line.split(" ")[1];
                    if (new File(str).isDirectory()) {
                        arrayList.add(str);
                    }
                }
            }
            inputStreamReader.close();
        } catch (Exception unused) {
        }
        return arrayList;
    }

    static boolean installNormal(Context context, String str, String str2) {
        if (str == null) {
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            File file = new File(str);
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            if (Build.VERSION.SDK_INT >= 24) {
                Uri uriForFile = FileProvider.getUriForFile(context, str2 + ".provider", file);
                intent.addFlags(1);
                intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
