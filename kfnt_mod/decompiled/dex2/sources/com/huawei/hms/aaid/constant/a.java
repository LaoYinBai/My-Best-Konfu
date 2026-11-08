package com.huawei.hms.aaid.constant;

import android.util.SparseArray;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.SendException;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum a {
    SUCCESS(0, 0, FirebaseAnalytics.Param.SUCCESS),
    ERROR_NO_TOKEN(800000000, SendException.ERROR_NO_TOKEN, "token missing"),
    ERROR_NO_TOKENSIGN(800000001, SendException.ERROR_TOKEN_INVALID, "token invalid"),
    ERROR_NO_NETWORK(800000002, SendException.ERROR_NO_NETWORK, "no network"),
    ERROR_SERVICE_NOT_AVAILABLE(800000003, SendException.ERROR_SERVICE_NOT_AVAILABLE, "service not available"),
    ERROR_PUSH_SERVER(800000004, SendException.ERROR_PUSH_SERVER, "push server error"),
    ERROR_UNKNOWN(800000005, SendException.ERROR_UNKNOWN, "unknown error"),
    ERROR_NO_RIGHT(800100000, 907122036, "no right"),
    ERROR_NO_CONNECTION_ID(800100001, 907122037, "get token error"),
    ERROR_LENGTH(800100002, 907122037, "get token error"),
    ERROR_NO_DEVICE_ID_TYPE(800100003, 907122037, "get token error"),
    ERROR_NOT_ALLOW_CROSS_APPLY(800100006, 907122053, "Failed to apply for the token. Cross-region application is not allowed."),
    ERROR_TOKEN_DECRYPT(800200001, SendException.ERROR_TOKEN_INVALID, "token invalid"),
    ERROR_TOKENSIGN_VALID(800200002, SendException.ERROR_TOKEN_INVALID, "token invalid"),
    ERROR_PUSH_ARGUMENTS_INVALID(807135000, CommonCode.ErrorCode.ARGUMENTS_INVALID, "arguments invalid"),
    ERROR_PUSH_INTERNAL_ERROR(807135001, CommonCode.ErrorCode.INTERNAL_ERROR, "internal error"),
    ERROR_PUSH_NAMING_INVALID(807135002, CommonCode.ErrorCode.NAMING_INVALID, "naming invalid"),
    ERROR_PUSH_CLIENT_API_INVALID(807135003, CommonCode.ErrorCode.CLIENT_API_INVALID, "client api invalid"),
    ERROR_PUSH_EXECUTE_TIMEOUT(807135004, CommonCode.ErrorCode.EXECUTE_TIMEOUT, "execute timeout"),
    ERROR_PUSH_NOT_IN_SERVICE(807135005, CommonCode.ErrorCode.NOT_IN_SERVICE, "not int service"),
    ERROR_SPUSH_ESSION_INVALID(807135006, CommonCode.ErrorCode.SESSION_INVALID, "session invalid"),
    ERROR_ARGUMENTS_INVALID(CommonCode.ErrorCode.ARGUMENTS_INVALID, CommonCode.ErrorCode.ARGUMENTS_INVALID, "arguments invalid"),
    ERROR_INTERNAL_ERROR(CommonCode.ErrorCode.INTERNAL_ERROR, CommonCode.ErrorCode.INTERNAL_ERROR, "internal error"),
    ERROR_NAMING_INVALID(CommonCode.ErrorCode.NAMING_INVALID, CommonCode.ErrorCode.NAMING_INVALID, "naming invalid"),
    ERROR_CLIENT_API_INVALID(CommonCode.ErrorCode.CLIENT_API_INVALID, CommonCode.ErrorCode.CLIENT_API_INVALID, "client api invalid"),
    ERROR_EXECUTE_TIMEOUT(CommonCode.ErrorCode.EXECUTE_TIMEOUT, CommonCode.ErrorCode.EXECUTE_TIMEOUT, "execute timeout"),
    ERROR_NOT_IN_SERVICE(CommonCode.ErrorCode.NOT_IN_SERVICE, CommonCode.ErrorCode.NOT_IN_SERVICE, "not int service"),
    ERROR_SESSION_INVALID(CommonCode.ErrorCode.SESSION_INVALID, CommonCode.ErrorCode.SESSION_INVALID, "session invalid"),
    ERROR_API_NOT_SPECIFIED(PushConstants.PUSHSERVICE_INFO_SEND_MESSAGE_BY_NOTIFICATION_SERVICE_ERROR, PushConstants.PUSHSERVICE_INFO_SEND_MESSAGE_BY_NOTIFICATION_SERVICE_ERROR, "API not specified"),
    ERROR_GET_SCOPE_ERROR(AuthCode.ErrorCode.GET_SCOPE_ERROR, AuthCode.ErrorCode.GET_SCOPE_ERROR, "get scope error"),
    ERROR_SCOPE_LIST_EMPTY(AuthCode.ErrorCode.SCOPE_LIST_EMPTY, AuthCode.ErrorCode.SCOPE_LIST_EMPTY, "scope list empty"),
    ERROR_CERT_FINGERPRINT_EMPTY(AuthCode.ErrorCode.CERT_FINGERPRINT_EMPTY, AuthCode.ErrorCode.CERT_FINGERPRINT_EMPTY, "certificate fingerprint empty"),
    ERROR_PERMISSION_LIST_EMPTY(AuthCode.ErrorCode.PERMISSION_LIST_EMPTY, AuthCode.ErrorCode.PERMISSION_LIST_EMPTY, "permission list empty"),
    ERROR_AUTH_INFO_NOT_EXIST(AuthCode.StatusCode.AUTH_INFO_NOT_EXIST, AuthCode.StatusCode.AUTH_INFO_NOT_EXIST, "auth info not exist"),
    ERROR_CERT_FINGERPRINT_ERROR(AuthCode.StatusCode.CERT_FINGERPRINT_ERROR, AuthCode.StatusCode.CERT_FINGERPRINT_ERROR, "certificate fingerprint error"),
    ERROR_PERMISSION_NOT_EXIST(AuthCode.StatusCode.PERMISSION_NOT_EXIST, AuthCode.StatusCode.PERMISSION_NOT_EXIST, "permission not exist"),
    ERROR_PERMISSION_NOT_AUTHORIZED(AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED, AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED, "permission not authorized"),
    ERROR_PERMISSION_EXPIRED(AuthCode.StatusCode.PERMISSION_EXPIRED, AuthCode.StatusCode.PERMISSION_EXPIRED, "permission expired"),
    ERROR_HMS_CLIENT_API(907122048, 907122048, "HMS client api invalid"),
    ERROR_NO_RIGHT_SELF_MAPPING(907122011, 907122036, "no push right"),
    ERROR_MAIN_THREAD(907122050, 907122050, "operation in MAIN thread prohibited"),
    ERROR_NO_NETWORK_OLD(907122005, SendException.ERROR_NO_NETWORK, "no network"),
    ERROR_NO_RIGHT_OLD(907122011, 907122036, "no right"),
    ERROR_NO_CONNECTION_ID_OLD(907122012, 907122037, "get token error"),
    ERROR_LENGTH_OLD(907122013, 907122037, "get token error"),
    ERROR_NO_DEVICE_ID_TYPE_OLD(907122014, 907122037, "get token error"),
    ERROR_PUSH_SERVER_OLD(907122017, SendException.ERROR_PUSH_SERVER, "push server error"),
    ERROR_NO_TOKEN_OLD(907122019, SendException.ERROR_NO_TOKEN, "token missing");

    private static final SparseArray<a> W = new SparseArray<>();
    private int X;
    private int Y;
    private String Z;

    static {
        for (a aVar : values()) {
            W.put(aVar.X, aVar);
        }
    }

    a(int i, int i2, String str) {
        this.X = i;
        this.Y = i2;
        this.Z = str;
    }

    public int a() {
        return this.Y;
    }

    public String b() {
        return this.Z;
    }

    public static a a(int i) {
        return W.get(i, ERROR_UNKNOWN);
    }

    public static ApiException a(a aVar) {
        return new ApiException(new Status(aVar.a(), aVar.b()));
    }
}
