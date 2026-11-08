package com.ss.union.game.sdk.core.base.account.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() { // from class: com.ss.union.game.sdk.core.base.account.model.User.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public User[] newArray(int i) {
            return new User[i];
        }
    };
    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_GALAXY_OPEN_ID = "galaxy_open_id";
    public static final String KEY_IS_ADULT = "is_adult";
    public static final String KEY_IS_FIRST_LOGIN = "first_login";
    public static final String KEY_IS_IDENTIFY_VALIDATED = "has_identify_validated";
    public static final String KEY_IS_LOGIN = "is_login";
    public static final String KEY_LOGIN_TOKEN = "auto_login_token";
    public static final String KEY_LOGIN_TYPE = "login_type";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_NICK_NAME = "nickname";
    public static final String KEY_OPEN_ID = "open_id";
    public static final String KEY_THIRD_PARTY_OPEN_ID = "third_party_open_id";
    public static final String KEY_THIRD_PARTY_SCOPE = "third_party_scope";
    public String avatar;
    public String galaxy_open_id;
    public boolean isFirstLogin;
    public boolean is_adult;
    public boolean is_identify_validated;
    public String login_type;
    public boolean mIsLogin;
    public String mobile;
    public String nick_name;
    public String open_id;
    public String third_party_open_id;
    public String token;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static User parseUser(JSONObject jSONObject) {
        User user = new User();
        user.token = jSONObject.optString(KEY_LOGIN_TOKEN, "");
        user.open_id = jSONObject.optString(KEY_OPEN_ID);
        user.third_party_open_id = jSONObject.optString(KEY_THIRD_PARTY_OPEN_ID, "");
        user.login_type = jSONObject.optString(KEY_LOGIN_TYPE, "");
        user.mIsLogin = jSONObject.optBoolean(KEY_IS_LOGIN, false);
        user.nick_name = jSONObject.optString(KEY_NICK_NAME, "");
        user.avatar = jSONObject.optString(KEY_AVATAR, "");
        user.mobile = jSONObject.optString("mobile", "");
        user.is_adult = jSONObject.optBoolean(KEY_IS_ADULT, false);
        user.is_identify_validated = jSONObject.optBoolean(KEY_IS_IDENTIFY_VALIDATED, false);
        user.isFirstLogin = jSONObject.optBoolean(KEY_IS_FIRST_LOGIN, false);
        user.galaxy_open_id = jSONObject.optString(KEY_GALAXY_OPEN_ID, "");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(KEY_THIRD_PARTY_SCOPE);
        if (LGAccountConstant.LoginType.LOGIN_TYPE_DY.getName().equals(user.login_type) && jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            SPUtils.getInstance().put(KEY_THIRD_PARTY_SCOPE, jSONObject.toString());
        }
        return user;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_IS_LOGIN, this.mIsLogin);
            jSONObject.put(KEY_OPEN_ID, this.open_id);
            jSONObject.put(KEY_THIRD_PARTY_OPEN_ID, this.third_party_open_id);
            jSONObject.put(KEY_LOGIN_TOKEN, this.token);
            jSONObject.put(KEY_LOGIN_TYPE, this.login_type);
            jSONObject.put(KEY_NICK_NAME, this.nick_name);
            jSONObject.put(KEY_AVATAR, this.avatar);
            jSONObject.put("mobile", this.mobile);
            jSONObject.put(KEY_IS_ADULT, this.is_adult);
            jSONObject.put(KEY_IS_IDENTIFY_VALIDATED, this.is_identify_validated);
            jSONObject.put(KEY_IS_FIRST_LOGIN, this.isFirstLogin);
            jSONObject.put(KEY_GALAXY_OPEN_ID, this.galaxy_open_id);
        } catch (JSONException e) {
            LogUtils.log(LogConstant.TAG_ACCOUNT, "toJson JSONException:" + Log.getStackTraceString(e));
        }
        return jSONObject;
    }

    public User() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.token);
        parcel.writeString(this.open_id);
        parcel.writeString(this.third_party_open_id);
        parcel.writeString(this.login_type);
        parcel.writeString(this.nick_name);
        parcel.writeString(this.avatar);
        parcel.writeByte(this.mIsLogin ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mobile);
        parcel.writeByte(this.is_identify_validated ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.is_adult ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isFirstLogin ? (byte) 1 : (byte) 0);
        parcel.writeString(this.galaxy_open_id);
    }

    protected User(Parcel parcel) {
        this.token = parcel.readString();
        this.open_id = parcel.readString();
        this.third_party_open_id = parcel.readString();
        this.login_type = parcel.readString();
        this.nick_name = parcel.readString();
        this.avatar = parcel.readString();
        this.mIsLogin = parcel.readByte() != 0;
        this.mobile = parcel.readString();
        this.is_identify_validated = parcel.readByte() != 0;
        this.is_adult = parcel.readByte() != 0;
        this.isFirstLogin = parcel.readByte() != 0;
        this.galaxy_open_id = parcel.readString();
    }

    public String toString() {
        return "User{token='" + this.token + "', open_id='" + this.open_id + "', galaxy_open_id='" + this.galaxy_open_id + "', third_party_open_id='" + this.third_party_open_id + "', login_type='" + this.login_type + "', nick_name='" + this.nick_name + "', avatar='" + this.avatar + "', mIsLogin=" + this.mIsLogin + ", mobile='" + this.mobile + "', is_identify_validated=" + this.is_identify_validated + ", is_adult=" + this.is_adult + ", isFirstLogin=" + this.isFirstLogin + '}';
    }
}
