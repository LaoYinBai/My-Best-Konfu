package com.ss.union.game.sdk.core.base.event;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements INetUploadStrategy {
    private volatile long a = 0;

    i() {
    }

    @Override // com.ss.union.game.sdk.core.base.event.INetUploadStrategy
    public int handle() throws JSONException {
        if (!NetworkUtils.isNetworkAvailable()) {
            return INetUploadStrategy.RESULT_NO_NETWORK;
        }
        if (!EventJSONHeaders.getInstance().isInitSuccess() && !EventJSONHeaders.getInstance().init()) {
            g.a("eventJSONHeaders初始化失败：", null);
            return INetUploadStrategy.RESULT_HEADER_NOT_READY;
        }
        c cVarA = e.a().a(1000, this.a);
        if (cVarA.a() <= 0) {
            g.a("历史事件为0，不上传，直接返回");
            return INetUploadStrategy.RESULT_NO_EVENT;
        }
        a(cVarA);
        g.a("发送事件数： " + cVarA.a());
        for (List<b> list : b(cVarA)) {
            a(a(list), list);
        }
        return 0;
    }

    private void a(c cVar) {
        if (cVar == null) {
            return;
        }
        List<b> listB = cVar.b();
        for (int i = 0; i < listB.size(); i++) {
            if (listB.get(i) != null && listB.get(i).e() > this.a) {
                this.a = listB.get(i).e();
            }
        }
    }

    public JSONArray a(List<b> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<b> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        return jSONArray;
    }

    private List<List<b>> b(c cVar) {
        List<b> listB = cVar.b();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < listB.size()) {
            int i2 = i + 200;
            arrayList.add(listB.subList(i, Math.min(i2, listB.size())));
            i = i2;
        }
        return arrayList;
    }

    private long[] c(c cVar) {
        List<b> listB = cVar.b();
        long[] jArr = new long[listB.size()];
        for (int i = 0; i < listB.size(); i++) {
            jArr[i] = listB.get(i).e();
        }
        return jArr;
    }

    private long[] b(List<b> list) {
        if (list == null) {
            return new long[0];
        }
        long[] jArr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            jArr[i] = list.get(i).e();
        }
        return jArr;
    }

    private void a(JSONArray jSONArray, List<b> list) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(INetUploadStrategy.KEY_MAGIC, "ss_app_log");
            jSONObject.put("header", EventJSONHeaders.getInstance().getJSONObject());
            jSONObject.put(INetUploadStrategy.KEY_EVENT, jSONArray);
            a(APP_EVENT_LOG_URL, jSONObject.toString(), 0, list);
        } catch (Exception e) {
            g.a("执行日志请求异常：", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<b> list, boolean z) {
        g.a("准备删除事件,上传成功=" + z);
        int iA = e.a().a(b(list), z);
        if (iA > 0) {
            g.a("删除事件成功: " + iA + "行");
        } else {
            g.a("删除事件失败");
        }
        StringBuilder sb = new StringBuilder("\n");
        for (b bVar : list) {
            sb.append(bVar.b());
            sb.append("\n");
            PageStater.b(iA > 0, bVar.b(), -1L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(final String str, final String str2, final int i, final List<b> list) {
        if (i > 1) {
            a(list, false);
            return;
        }
        ((ACoreRequestPost) ((ACoreRequestPost) ((ACoreRequestPost) ((ACoreRequestPost) CoreNetClient.post(str).enableCommonHeader(true)).header("Content-Type", "application/json;charset=utf-8")).urlParam(com.ss.union.game.sdk.redemptionCode.a.t, System.currentTimeMillis() + "")).enableCommonParam(false)).enableGzip(true).upJson(str2).json(a(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.base.event.i.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                g.a("发送日志失败: code=" + coreNetResponse.code() + ",message=" + coreNetResponse.message(), coreNetResponse.error);
                if (coreNetResponse.error instanceof IOException) {
                    i.this.a(str, str2, i + 1, list);
                } else {
                    i.this.a((List<b>) list, false);
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                JSONObject jSONObject = coreNetResponse.data;
                if (jSONObject != null && "ss_app_log".equals(jSONObject.optString(INetUploadStrategy.KEY_MAGIC)) && "success".equals(jSONObject.optString("message"))) {
                    g.a("发送日志成功");
                    i.this.a((List<b>) list, true);
                    return;
                }
                g.a("发送日志失败：" + (coreNetResponse.data == null ? "empty" : coreNetResponse.data.toString()));
                i.this.a(str, str2, i + 1, list);
            }
        }));
    }

    private ICoreNetCallback<JSONObject, ACoreRequestPost> a(final ICoreNetCallback<JSONObject, ACoreRequestPost> iCoreNetCallback) {
        return new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.base.event.i.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetStart(final ACoreRequestPost aCoreRequestPost) {
                super.onNetStart(aCoreRequestPost);
                if (iCoreNetCallback != null) {
                    TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.i.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            iCoreNetCallback.onNetStart(aCoreRequestPost);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetEnd(final ACoreRequestPost aCoreRequestPost) {
                super.onNetEnd(aCoreRequestPost);
                if (iCoreNetCallback != null) {
                    TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.i.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            iCoreNetCallback.onNetEnd(aCoreRequestPost);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(final ACoreRequestPost aCoreRequestPost, final CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (iCoreNetCallback != null) {
                    TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.i.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            iCoreNetCallback.onNetError(aCoreRequestPost, coreNetResponse);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(final ACoreRequestPost aCoreRequestPost, final CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (iCoreNetCallback != null) {
                    TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.i.2.4
                        @Override // java.lang.Runnable
                        public void run() {
                            iCoreNetCallback.onNetSuccess(aCoreRequestPost, coreNetResponse);
                        }
                    });
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetProgress(final ACoreRequestPost aCoreRequestPost, final float f, final float f2, final float f3) {
                super.onNetProgress(aCoreRequestPost, f, f2, f3);
                if (iCoreNetCallback != null) {
                    TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.i.2.5
                        @Override // java.lang.Runnable
                        public void run() {
                            iCoreNetCallback.onNetProgress(aCoreRequestPost, f, f2, f3);
                        }
                    });
                }
            }
        };
    }
}
