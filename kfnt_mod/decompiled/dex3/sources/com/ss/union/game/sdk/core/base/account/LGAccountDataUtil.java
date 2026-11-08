package com.ss.union.game.sdk.core.base.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.ss.union.game.sdk.common.util.AES;
import com.ss.union.game.sdk.common.util.FileUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.PermissionUtils;
import com.ss.union.game.sdk.common.util.StringEncryptUtils;
import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.b.b;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.account.model.VisitorUser;
import com.ss.union.game.sdk.core.realName.a.a;
import com.umeng.message.MsgConstant;
import java.io.File;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAccountDataUtil {
    private static final String a = "LG_USER_INFO";
    private static final String b = "LAST_OPEN_ID";
    private static final String c = "LAST_MOBILE";
    private static final String d = "b0458c2b262949b8b0458c2b262949b8";
    private static User e;

    public static SharedPreferences getUserInfoSp() {
        return GlobalApplicationUtils.getContext().getSharedPreferences(a, 0);
    }

    public static String getCurrentUserOpenId() {
        return getUserInfoSp().getString(b, "");
    }

    public static User getCurrentUserLocal() {
        if (e == null) {
            String currentUserOpenId = getCurrentUserOpenId();
            if (!TextUtils.isEmpty(currentUserOpenId)) {
                try {
                    e = User.parseUser(new JSONObject(getUserInfoSp().getString(currentUserOpenId, "")));
                    if (TextUtils.isEmpty(e.login_type) || TextUtils.isEmpty(e.token)) {
                        e.mIsLogin = false;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return e;
    }

    public static void clearLocalUserData() {
        SharedPreferences userInfoSp = getUserInfoSp();
        if (userInfoSp != null) {
            userInfoSp.edit().clear().apply();
        }
        e = null;
        b(GlobalApplicationUtils.getContext());
    }

    public static boolean isLogined() {
        User currentUserLocal = getCurrentUserLocal();
        return currentUserLocal != null && currentUserLocal.mIsLogin;
    }

    public static boolean isDyLogin() {
        return LGAccountConstant.LoginType.LOGIN_TYPE_DY.getName().equals(getLoginType());
    }

    public static boolean isVisitor() {
        return LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName().equals(getLoginType());
    }

    public static boolean isFirstLogin() {
        User currentUserLocal = getCurrentUserLocal();
        if (currentUserLocal == null) {
            return true;
        }
        return currentUserLocal.isFirstLogin;
    }

    public static String getLoginToken() {
        User currentUserLocal = getCurrentUserLocal();
        return currentUserLocal != null ? currentUserLocal.token : "";
    }

    public static boolean isAdult() {
        User currentUserLocal = getCurrentUserLocal();
        return currentUserLocal != null && currentUserLocal.is_adult;
    }

    public static boolean isIdentifyValidated() {
        User currentUserLocal = getCurrentUserLocal();
        return currentUserLocal != null && currentUserLocal.is_identify_validated;
    }

    public static String getLoginType() {
        User currentUserLocal = getCurrentUserLocal();
        return currentUserLocal != null ? currentUserLocal.login_type : "";
    }

    public static void saveUserData(User user) {
        if (user == null) {
            LogUtils.log(LogConstant.TAG_ACCOUNT, "saveUserData user is null...");
            return;
        }
        User currentUserLocal = getCurrentUserLocal();
        if (TextUtils.isEmpty(user.mobile) && currentUserLocal != null && !TextUtils.isEmpty(currentUserLocal.mobile)) {
            user.mobile = currentUserLocal.mobile;
        }
        if (e != null && user.open_id != null && user.open_id.equals(e.open_id) && TextUtils.isEmpty(user.galaxy_open_id)) {
            user.galaxy_open_id = e.galaxy_open_id;
        }
        user.mIsLogin = true;
        e = user;
        if (TextUtils.isEmpty(e.login_type) || TextUtils.isEmpty(e.token)) {
            e.mIsLogin = false;
        }
        SharedPreferences.Editor editorEdit = getUserInfoSp().edit();
        editorEdit.putString(user.open_id, user.toJson().toString());
        editorEdit.putString(b, user.open_id);
        if (!TextUtils.isEmpty(user.mobile)) {
            editorEdit.putString(c, user.mobile);
            if (user.login_type.equals(LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName())) {
                editorEdit.putString(user.mobile, user.open_id);
            }
        }
        editorEdit.apply();
        saveUserData2SD(GlobalApplicationUtils.getContext(), new VisitorUser(user, true));
        b.a(user.open_id);
        a.a(user);
    }

    public static void saveUserData2SD(Context context, VisitorUser visitorUser) {
        if (a(visitorUser) && Environment.getExternalStorageState().equals("mounted") && PermissionUtils.checkPermission(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) && LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName().equals(visitorUser.user.login_type)) {
            try {
                String strA = a(context);
                FileUtils.writeToExternal(AES.encrypt(visitorUser.toJSON().toString(), d), strA);
                LogUtils.log(LogConstant.TAG_ACCOUNT, "saveUserData2SD():filePath" + new File(strA).getName() + ",user:" + visitorUser.user.nick_name);
            } catch (Exception e2) {
                LogUtils.log(LogConstant.TAG_ACCOUNT, "Exception:" + Log.getStackTraceString(e2));
            }
        }
    }

    private static String a(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".light_game" + File.separator + StringEncryptUtils.encryptByMD5(context.getPackageName() + "_visitor_account_info");
    }

    private static boolean a(VisitorUser visitorUser) {
        return (visitorUser == null || visitorUser.user == null || TextUtils.isEmpty(visitorUser.user.token) || TextUtils.isEmpty(visitorUser.user.open_id)) ? false : true;
    }

    private static void b(Context context) {
        try {
            FileUtils.deleteFile(a(context));
        } catch (Exception e2) {
            LogUtils.log(LogConstant.TAG_ACCOUNT, "Exception:" + e2);
        }
    }

    public static VisitorUser readUserDataFromSD(Context context) {
        String file;
        if (!Environment.getExternalStorageState().equals("mounted") || !PermissionUtils.checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            return null;
        }
        LogUtils.log(LogConstant.TAG_ACCOUNT, "readUserDataFromSD()");
        try {
            file = FileUtils.readFile(a(context));
        } catch (Exception e2) {
            LogUtils.log(LogConstant.TAG_ACCOUNT, "Exception:" + Log.getStackTraceString(e2));
        }
        if (TextUtils.isEmpty(file)) {
            return null;
        }
        String strDecrypt = AES.decrypt(file, d);
        if (!TextUtils.isEmpty(strDecrypt)) {
            VisitorUser visitorUserFromJSON = VisitorUser.fromJSON(new JSONObject(strDecrypt));
            if (a(visitorUserFromJSON)) {
                return visitorUserFromJSON;
            }
        }
        return null;
    }

    public static void refreshRealNameInfoAndSave(boolean z, boolean z2) {
        if (e != null) {
            e.is_identify_validated = z;
            e.is_adult = z2;
            saveUserData(e);
        }
    }

    public static String getMobileWithRegular(String str) {
        try {
            return str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } catch (Exception unused) {
            return str;
        }
    }
}
