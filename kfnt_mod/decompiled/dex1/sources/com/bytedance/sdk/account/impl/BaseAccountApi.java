package com.bytedance.sdk.account.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.appcompat.widget.ActivityChooserView;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.api.pool.IApiController;
import com.bytedance.sdk.account.api.pool.IApiThreadPool;
import com.bytedance.sdk.account.api.pool.IBaseAccountJob;
import com.bytedance.sdk.account.api.pool.IJobController;
import com.bytedance.sdk.account.constants.UserInfoThreadConstants;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.AbsApiThread;
import com.bytedance.sdk.account.utils.NetUtils;
import com.bytedance.sdk.account.utils.NetworkUtils;
import com.bytedance.sdk.account.utils.UrlBuilder;
import com.ss.android.account.TTAccountInit;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class BaseAccountApi<R extends BaseApiResponse> implements IApiController, IBaseAccountJob {
    protected ApiRequest apiRequest;
    private ApiResponse apiResponse;
    private boolean executeStatus;
    protected AbsApiCall mApiCall;
    protected WeakReference<Context> mContextRef;
    private String mPath;
    private IJobController mPoolJobController;
    private static IApiThreadPool threadPool = new IApiThreadPool() { // from class: com.bytedance.sdk.account.impl.BaseAccountApi.1
        @Override // com.bytedance.sdk.account.api.pool.IApiThreadPool
        public IJobController start(IBaseAccountJob iBaseAccountJob) {
            AccountApiThread accountApiThread = new AccountApiThread(iBaseAccountJob);
            accountApiThread.start();
            return accountApiThread;
        }
    };
    private static IDispatchSender sDispatch = new IDispatchSender() { // from class: com.bytedance.sdk.account.impl.BaseAccountApi.2
        @Override // com.bytedance.sdk.account.impl.IDispatchSender
        public void dispatch(ResponseCallable responseCallable) {
            Handler dispatchHandler = ((BDAccountManager) BDAccountManager.instance(TTAccountInit.getConfig().getApplicationContext())).getDispatchHandler();
            Message messageObtain = Message.obtain(dispatchHandler, 100);
            messageObtain.obj = responseCallable;
            dispatchHandler.sendMessage(messageObtain);
        }
    };

    public abstract void onSendEvent(R r);

    protected abstract void onStatusError(JSONObject jSONObject, JSONObject jSONObject2);

    protected abstract void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception;

    protected abstract R transformResponse(boolean z, ApiResponse apiResponse);

    public BaseAccountApi(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        this.mContextRef = new WeakReference<>(context);
        this.mPath = apiRequest.url;
        this.apiRequest = apiRequest;
        this.mApiCall = absApiCall;
        if (absApiCall != null) {
            absApiCall.attachController(this);
        }
        this.apiResponse = new ApiResponse(apiRequest);
    }

    @Override // com.bytedance.sdk.account.api.pool.IJobController
    public void cancel() {
        releaseRef();
        IJobController iJobController = this.mPoolJobController;
        if (iJobController != null) {
            iJobController.cancel();
        }
    }

    @Override // com.bytedance.sdk.account.api.pool.IApiController
    public void releaseRef() {
        this.mApiCall = null;
    }

    private void onPostData(R r) {
        dispatchInvokeCallBack(new ResponseCallable(this.mApiCall, r));
    }

    private void run() {
        BaseApiResponse baseApiResponseExecute = execute();
        if (baseApiResponseExecute != null) {
            if (this.apiResponse != null) {
                baseApiResponseExecute.mRequestUrl = this.apiResponse.mRequestUrl;
            }
            if (TextUtils.isEmpty(baseApiResponseExecute.mRequestUrl)) {
                baseApiResponseExecute.mRequestUrl = this.mPath;
            }
            onSendEvent(baseApiResponseExecute);
            onPostData(baseApiResponseExecute);
        }
    }

    @Override // com.bytedance.sdk.account.api.pool.IBaseAccountJob
    public void runInPool() {
        run();
    }

    public final void start() {
        this.mPoolJobController = threadPool.start(this);
    }

    public R execute() {
        this.executeStatus = false;
        try {
            this.executeStatus = executeRequest();
        } catch (Throwable th) {
            th.printStackTrace();
            this.apiResponse.mError = NetUtils.checkApiException(this.mContextRef.get(), th);
            this.apiResponse.mDetailErrorMsg = th.getMessage();
        }
        R r = (R) transformResponse(this.executeStatus, this.apiResponse);
        applyExceptionError(r);
        return r;
    }

    private void applyExceptionError(BaseApiResponse baseApiResponse) {
        T t;
        if (baseApiResponse != null) {
            if (this.apiResponse.mError < 0) {
                if (baseApiResponse.error == 0) {
                    baseApiResponse.error = this.apiResponse.mError;
                }
                if ((baseApiResponse instanceof MobileApiResponse) && (t = ((MobileApiResponse) baseApiResponse).mobileObj) != 0 && t.mError == 0) {
                    t.mError = this.apiResponse.mError;
                }
            }
            if (this.apiResponse.mError == 0 || !TextUtils.isEmpty(baseApiResponse.mDetailErrorMsg)) {
                return;
            }
            baseApiResponse.mDetailErrorMsg = this.apiResponse.mDetailErrorMsg != null ? this.apiResponse.mDetailErrorMsg : "";
        }
    }

    private void notSuccess(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        if (("error".equals(str) || "exception".equals(str)) && jSONObject2 != null) {
            if (jSONObject2.has("error_code")) {
                this.apiResponse.mError = jSONObject2.optInt("error_code", this.apiResponse.mError);
            } else if (jSONObject2.has("code")) {
                this.apiResponse.mError = jSONObject2.optInt("code", this.apiResponse.mError);
            }
            this.apiResponse.mErrorMsg = jSONObject2.optString(UserInfoThreadConstants.DESCRIPTION);
            this.apiResponse.mDetailErrorMsg = this.apiResponse.mErrorMsg;
            onStatusError(jSONObject2, jSONObject);
        }
    }

    private boolean executeRequest() throws Exception {
        if (this.mContextRef.get() == null) {
            this.apiResponse.mError = -18;
            return false;
        }
        if (NetworkUtils.getNetworkType(this.mContextRef.get()) == NetworkUtils.NetworkType.NONE) {
            this.apiResponse.mError = -12;
            return false;
        }
        String strExecutePost = null;
        if (ApiRequest.METHOD_GET.equals(this.apiRequest.method)) {
            strExecutePost = executeGet(this.mPath, this.apiRequest.parms);
        } else if (ApiRequest.METHOD_POST.equals(this.apiRequest.method)) {
            strExecutePost = executePost(this.mPath, this.apiRequest.parms);
        }
        if (StringUtils.isEmpty(strExecutePost)) {
            this.apiResponse.mError = -18;
            return false;
        }
        JSONObject jSONObject = new JSONObject(strExecutePost);
        JSONObject data = parseData(jSONObject);
        String strOptString = jSONObject.optString("message");
        if (!TextUtils.isEmpty(strOptString)) {
            return parseInner(jSONObject, data, strOptString);
        }
        if (data != null) {
            return parseInner(data, parseData(data), data.optString("message"));
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0036, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject parseData(org.json.JSONObject r3) throws org.json.JSONException {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L37
            java.lang.String r1 = "data"
            boolean r1 = r3.has(r1)
            if (r1 != 0) goto Lc
            goto L37
        Lc:
            java.lang.String r1 = "data"
            java.lang.Object r3 = r3.opt(r1)     // Catch: java.lang.Exception -> L31
            if (r3 != 0) goto L15
            return r0
        L15:
            boolean r1 = r3 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> L31
            if (r1 == 0) goto L1c
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch: java.lang.Exception -> L31
            goto L36
        L1c:
            boolean r1 = r3 instanceof org.json.JSONArray     // Catch: java.lang.Exception -> L31
            if (r1 == 0) goto L35
            org.json.JSONArray r3 = (org.json.JSONArray) r3     // Catch: java.lang.Exception -> L31
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L31
            r1.<init>()     // Catch: java.lang.Exception -> L31
            java.lang.String r0 = "data"
            r1.put(r0, r3)     // Catch: java.lang.Exception -> L2e
            r3 = r1
            goto L36
        L2e:
            r3 = move-exception
            r0 = r1
            goto L32
        L31:
            r3 = move-exception
        L32:
            r3.printStackTrace()
        L35:
            r3 = r0
        L36:
            return r3
        L37:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.impl.BaseAccountApi.parseData(org.json.JSONObject):org.json.JSONObject");
    }

    private boolean parseInner(JSONObject jSONObject, JSONObject jSONObject2, String str) throws Exception {
        if (!"success".equals(str)) {
            notSuccess(str, jSONObject, jSONObject2);
            return false;
        }
        if (jSONObject2 == null) {
            return true;
        }
        parseData(jSONObject, jSONObject2);
        return true;
    }

    private final String executeGet(String str, Map<String, String> map) throws Exception {
        UrlBuilder urlBuilder = new UrlBuilder(str);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    urlBuilder.addParam(entry.getKey(), entry.getValue());
                }
            }
        }
        if (TTAccountInit.getConfig().isSupportMultiLogin()) {
            urlBuilder.addParam("multi_login", "1");
        }
        urlBuilder.addParam(AccountMonitorConstants.SDK_SOURCE, "app");
        urlBuilder.addParam("passport-sdk-version", 9);
        String string = urlBuilder.toString();
        this.apiResponse.mRequestUrl = string;
        return NetworkUtils.executeGet(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, string);
    }

    private final String executePost(String str, Map<String, String> map) throws Exception {
        HashMap map2 = new HashMap();
        UrlBuilder urlBuilder = new UrlBuilder(str);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    map2.put(entry.getKey(), entry.getValue());
                }
            }
        }
        map2.put(AccountMonitorConstants.SDK_SOURCE, "app");
        if (TTAccountInit.getConfig().isSupportMultiLogin()) {
            map2.put("multi_login", "1");
        }
        urlBuilder.addParam("passport-sdk-version", 9);
        String string = urlBuilder.toString();
        this.apiResponse.mRequestUrl = string;
        return NetworkUtils.executePost(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, string, map2);
    }

    private static class AccountApiThread extends AbsApiThread implements IJobController {
        private IBaseAccountJob thread;

        private AccountApiThread(IBaseAccountJob iBaseAccountJob) {
            this.thread = iBaseAccountJob;
        }

        @Override // com.bytedance.sdk.account.network.dispatcher.ApiThread, java.lang.Runnable
        public void run() {
            this.thread.runInPool();
        }
    }

    private static void dispatchInvokeCallBack(ResponseCallable responseCallable) {
        sDispatch.dispatch(responseCallable);
    }
}
