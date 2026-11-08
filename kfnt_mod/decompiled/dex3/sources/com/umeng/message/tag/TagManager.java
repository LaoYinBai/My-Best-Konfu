package com.umeng.message.tag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.d;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.util.HttpRequest;
import com.umeng.message.util.e;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TagManager {
    private static final String a = "com.umeng.message.tag.TagManager";
    private static final String b = "ok";
    private static final String c = "fail";

    @SuppressLint({"StaticFieldLeak"})
    private static TagManager d;
    private static ITagManager f;
    private Context e;

    public interface TCallBack {
        void onMessage(boolean z, ITagManager.Result result);
    }

    public interface TagListCallBack {
        void onMessage(boolean z, List<String> list);
    }

    public interface WeightedTagListCallBack {
        void onMessage(boolean z, Hashtable<String, Integer> hashtable);
    }

    private TagManager(Context context) {
        this.e = context.getApplicationContext();
    }

    public static synchronized TagManager getInstance(Context context) {
        if (d == null) {
            d = new TagManager(context.getApplicationContext());
            try {
                f = (ITagManager) Class.forName("com.umeng.message.common.impl.json.JTagManager").getConstructor(Context.class).newInstance(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    public void addTags(final TCallBack tCallBack, final String... strArr) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.1
            @Override // java.lang.Runnable
            public void run() throws JSONException, UnsupportedEncodingException {
                ITagManager.Result result = new ITagManager.Result();
                if (strArr != null && strArr.length != 0) {
                    if (TagManager.this.d()) {
                        if (!TagManager.this.e()) {
                            String strA = TagManager.this.a(MessageSharedPrefs.getInstance(TagManager.this.e).get_addTagsInterval(), strArr);
                            if (!TextUtils.isEmpty(strA)) {
                                UMLog uMLog = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(TagManager.a, 0, strA);
                                result.setErrors(strA);
                                tCallBack.onMessage(false, result);
                                return;
                            }
                            ArrayList arrayList = new ArrayList();
                            if (strArr.length > 0) {
                                for (String str : strArr) {
                                    if (!MessageSharedPrefs.getInstance(TagManager.this.e).isTagSet(str) && !arrayList.contains(str)) {
                                        byte[] bytes = null;
                                        try {
                                            bytes = str.getBytes("UTF-8");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        if (bytes.length <= 128 && bytes.length >= 0) {
                                            arrayList.add(str);
                                        }
                                    }
                                }
                            }
                            if (arrayList.size() != 0) {
                                try {
                                    JSONObject jSONObjectC = TagManager.this.c();
                                    jSONObjectC.put(MsgConstant.KEY_TAGS, e.a(arrayList));
                                    tCallBack.onMessage(true, TagManager.f.addTags(jSONObjectC, strArr));
                                    return;
                                } catch (Exception unused) {
                                    UMLog uMLog2 = UMConfigure.umDebugLog;
                                    UMLog.mutlInfo(TagManager.a, 0, "添加tag异常");
                                    return;
                                }
                            }
                            tCallBack.onMessage(true, TagManager.this.f());
                            return;
                        }
                        UMLog uMLog3 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(TagManager.a, 0, "Tag API is disabled by the server");
                        result.setErrors("Tag API is disabled by the server");
                        tCallBack.onMessage(false, result);
                        return;
                    }
                    UMLog uMLog4 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "No utdid or device_token");
                    result.setErrors("No utdid or device_token");
                    tCallBack.onMessage(false, result);
                    return;
                }
                UMLog uMLog5 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TagManager.a, 0, "No tags");
                result.setErrors("No tags");
                tCallBack.onMessage(false, result);
            }
        });
    }

    public void addWeightedTags(final TCallBack tCallBack, final Hashtable<String, Integer> hashtable) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.2
            @Override // java.lang.Runnable
            public void run() throws JSONException, UnsupportedEncodingException {
                ITagManager.Result result = new ITagManager.Result();
                if (!TagManager.this.d()) {
                    result.setErrors("No utdid or device token");
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "No utdid or device token");
                    tCallBack.onMessage(false, result);
                    return;
                }
                if (hashtable == null || hashtable.size() == 0) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "No weighted tags");
                    result.setErrors("No weighted tags");
                    tCallBack.onMessage(false, result);
                    return;
                }
                if (hashtable.size() <= 64) {
                    String strA = TagManager.this.a(MessageSharedPrefs.getInstance(TagManager.this.e).getAddWeightedTagsInterval(), (Hashtable<String, Integer>) hashtable);
                    if (!TextUtils.isEmpty(strA)) {
                        UMLog uMLog3 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(TagManager.a, 0, strA);
                        result.setErrors(strA);
                        tCallBack.onMessage(false, result);
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject();
                        for (String str : hashtable.keySet()) {
                            jSONObject.put(str, ((Integer) hashtable.get(str)).intValue());
                        }
                        JSONObject jSONObjectC = TagManager.this.c();
                        jSONObjectC.put(MsgConstant.KEY_TAGS, jSONObject);
                        tCallBack.onMessage(true, TagManager.f.addWeightedTags(jSONObjectC, hashtable));
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        UMLog uMLog4 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(TagManager.a, 0, "添加加权标签异常");
                        return;
                    }
                }
                UMLog uMLog5 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TagManager.a, 0, "The maximum number of adding weighted tags per request is 64");
                result.setErrors("The maximum number of adding weighted tags per request is 64");
                tCallBack.onMessage(false, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String... strArr) throws UnsupportedEncodingException {
        String str2;
        byte[] bytes;
        ArrayList arrayList = new ArrayList();
        if (strArr != null && strArr.length > 0) {
            for (String str3 : strArr) {
                if (!MessageSharedPrefs.getInstance(this.e).isTagSet(str3) && !arrayList.contains(str3)) {
                    try {
                        bytes = str3.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        bytes = null;
                    }
                    if (bytes.length > 128 || bytes.length < 0) {
                        return "部分Tags长度不在限制0到128个字符之间";
                    }
                    arrayList.add(str3);
                }
            }
        }
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), false);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (arrayList.size() <= 0) {
                return null;
            }
            if (result.remain < 0 || arrayList.size() > result.remain) {
                str2 = "Tags数量不能超过1024";
            } else {
                if (result.interval == 0) {
                    return null;
                }
                if ((jCurrentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                    return null;
                }
                str2 = "interval限制";
            }
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, Hashtable<String, Integer> hashtable) throws UnsupportedEncodingException {
        for (String str2 : hashtable.keySet()) {
            try {
                byte[] bytes = str2.getBytes("UTF-8");
                if (bytes.length > 128 && bytes.length <= 0) {
                    return "部分Tags长度不在限制0到128个字符之间";
                }
                if (hashtable.get(str2).intValue() < -10 || hashtable.get(str2).intValue() > 10) {
                    return "部分Tags权值不在-10到10之间";
                }
            } catch (UnsupportedEncodingException unused) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 0, "UnsupportedEncodingException");
                return "部分Tags长度不在限制0到128个字符之间";
            }
        }
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), true);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (result.interval == 0) {
                return null;
            }
            if ((jCurrentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                return null;
            }
            return "interval限制";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str, String... strArr) throws UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArr) {
            try {
                byte[] bytes = str2.getBytes("UTF-8");
                if (bytes.length > 128 || bytes.length < 0) {
                    return "部分Tags长度不在限制0到128个字符之间";
                }
                arrayList.add(str2);
            } catch (UnsupportedEncodingException unused) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 0, "UnsupportedEncodingException");
                return "部分Tags长度不在限制0到128个字符之间";
            }
        }
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), true);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (arrayList.size() <= 0 || result.interval == 0) {
                return null;
            }
            if ((jCurrentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                return null;
            }
            return "interval限制";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, boolean z) {
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), z);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (result.interval == 0) {
                return null;
            }
            if ((jCurrentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                return null;
            }
            return "interval限制";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void a(final TCallBack tCallBack, final String... strArr) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.3
            @Override // java.lang.Runnable
            public void run() throws Exception {
                ITagManager.Result result = null;
                if (!TagManager.this.e()) {
                    if (!TagManager.this.d()) {
                        try {
                            throw new Exception("No utdid or device_token");
                        } catch (Exception e) {
                            e.printStackTrace();
                            tCallBack.onMessage(false, null);
                            return;
                        }
                    }
                    if (strArr == null || strArr.length == 0) {
                        try {
                            throw new Exception("No tags");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            tCallBack.onMessage(false, null);
                            return;
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    for (String str : strArr) {
                        arrayList.add(str);
                    }
                    if (arrayList.size() == 0) {
                        tCallBack.onMessage(true, TagManager.this.f());
                        return;
                    }
                    try {
                        JSONObject jSONObjectC = TagManager.this.c();
                        jSONObjectC.put(MsgConstant.KEY_TAGS, e.a(arrayList));
                        ITagManager.Result resultUpdate = TagManager.f.update(jSONObjectC, strArr);
                        try {
                            tCallBack.onMessage(true, resultUpdate);
                        } catch (Exception e3) {
                            e = e3;
                            result = resultUpdate;
                            e.printStackTrace();
                            tCallBack.onMessage(true, result);
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                } else {
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                }
            }
        });
    }

    public void deleteTags(final TCallBack tCallBack, final String... strArr) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.4
            @Override // java.lang.Runnable
            public void run() throws Exception {
                ITagManager.Result resultDeleteTags;
                String strA = TagManager.this.a(MessageSharedPrefs.getInstance(TagManager.this.e).get_deleteTagsInterval(), strArr);
                ITagManager.Result result = null;
                if (TextUtils.isEmpty(strA)) {
                    if (!TagManager.this.e()) {
                        if (!TagManager.this.d()) {
                            try {
                                throw new Exception("No utdid or device_token");
                            } catch (Exception e) {
                                UMLog uMLog = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(TagManager.a, 0, "exception:" + e.getMessage());
                                tCallBack.onMessage(false, null);
                                return;
                            }
                        }
                        if (strArr != null && strArr.length != 0) {
                            try {
                                JSONObject jSONObjectC = TagManager.this.c();
                                jSONObjectC.put(MsgConstant.KEY_TAGS, e.a(strArr));
                                resultDeleteTags = TagManager.f.deleteTags(jSONObjectC, strArr);
                            } catch (Exception e2) {
                                e = e2;
                            }
                            try {
                                tCallBack.onMessage(true, resultDeleteTags);
                                return;
                            } catch (Exception e3) {
                                e = e3;
                                result = resultDeleteTags;
                                UMLog uMLog2 = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(TagManager.a, 0, "exception:" + e.getMessage());
                                tCallBack.onMessage(false, result);
                                return;
                            }
                        }
                        try {
                            throw new Exception("No tags");
                        } catch (Exception e4) {
                            UMLog uMLog3 = UMConfigure.umDebugLog;
                            UMLog.mutlInfo(TagManager.a, 0, "exception:" + e4.getMessage());
                            tCallBack.onMessage(false, null);
                            return;
                        }
                    }
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e5) {
                        UMLog uMLog4 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(TagManager.a, 0, "exception:" + e5.getMessage());
                        tCallBack.onMessage(false, null);
                        return;
                    }
                }
                try {
                    throw new Exception(strA);
                } catch (Exception e6) {
                    UMLog uMLog5 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "exception:" + e6.getMessage());
                    tCallBack.onMessage(false, null);
                }
            }
        });
    }

    public void deleteWeightedTags(final TCallBack tCallBack, final String... strArr) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.5
            @Override // java.lang.Runnable
            public void run() throws JSONException, UnsupportedEncodingException {
                ITagManager.Result result = new ITagManager.Result();
                if (!TagManager.this.d()) {
                    result.setErrors("缺少utdid或device token");
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "缺少utdid或device token");
                    tCallBack.onMessage(false, result);
                    return;
                }
                if (strArr == null || strArr.length == 0) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "没有加权标签");
                    result.setErrors("没有加权标签");
                    tCallBack.onMessage(false, result);
                    return;
                }
                if (strArr.length <= 64) {
                    String strB = TagManager.this.b(MessageSharedPrefs.getInstance(TagManager.this.e).getDeleteWeightedTagsInterval(), strArr);
                    if (TextUtils.isEmpty(strB)) {
                        try {
                            JSONObject jSONObjectC = TagManager.this.c();
                            jSONObjectC.put(MsgConstant.KEY_TAGS, e.a(strArr));
                            tCallBack.onMessage(true, TagManager.f.deleteWeightedTags(jSONObjectC, strArr));
                            return;
                        } catch (Exception unused) {
                            UMLog uMLog3 = UMConfigure.umDebugLog;
                            UMLog.mutlInfo(TagManager.a, 0, "删除加权标签异常");
                            return;
                        }
                    }
                    UMLog uMLog4 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, strB);
                    result.setErrors(strB);
                    tCallBack.onMessage(false, result);
                    return;
                }
                UMLog uMLog5 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TagManager.a, 0, "每次请求最多删除64个加权标签");
                result.setErrors("每次请求最多删除64个加权标签");
                tCallBack.onMessage(false, result);
            }
        });
    }

    private void a(final TCallBack tCallBack) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.6
            @Override // java.lang.Runnable
            public void run() throws Exception {
                ITagManager.Result resultReset;
                ITagManager.Result result = null;
                if (!TagManager.this.e()) {
                    if (TagManager.this.d()) {
                        try {
                            resultReset = TagManager.f.reset(TagManager.this.c());
                        } catch (Exception e) {
                            e = e;
                        }
                        try {
                            tCallBack.onMessage(true, resultReset);
                            return;
                        } catch (Exception e2) {
                            result = resultReset;
                            e = e2;
                            e.printStackTrace();
                            tCallBack.onMessage(false, result);
                            return;
                        }
                    }
                    try {
                        throw new Exception("缺少utdid或device token");
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        tCallBack.onMessage(false, null);
                        return;
                    }
                }
                try {
                    throw new Exception("Tag API被服务器禁止");
                } catch (Exception e4) {
                    e4.printStackTrace();
                    tCallBack.onMessage(false, null);
                }
            }
        });
    }

    public void getTags(final TagListCallBack tagListCallBack) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.7
            @Override // java.lang.Runnable
            public void run() throws Exception {
                List<String> tags;
                String strA = TagManager.this.a(MessageSharedPrefs.getInstance(TagManager.this.e).get_getTagsInterval(), false);
                List<String> list = null;
                if (TextUtils.isEmpty(strA)) {
                    if (!TagManager.this.e()) {
                        if (TagManager.this.d()) {
                            try {
                                tags = TagManager.f.getTags(TagManager.this.c());
                            } catch (Exception e) {
                                e = e;
                            }
                            try {
                                tagListCallBack.onMessage(true, tags);
                                return;
                            } catch (Exception e2) {
                                e = e2;
                                list = tags;
                                UMLog uMLog = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(TagManager.a, 0, "exception:" + e.getMessage());
                                tagListCallBack.onMessage(false, list);
                                return;
                            }
                        }
                        try {
                            throw new Exception("缺少utdid或device token");
                        } catch (Exception e3) {
                            UMLog uMLog2 = UMConfigure.umDebugLog;
                            UMLog.mutlInfo(TagManager.a, 0, "exception:" + e3.getMessage());
                            tagListCallBack.onMessage(false, null);
                            return;
                        }
                    }
                    try {
                        throw new Exception("Tag API被服务器禁止.");
                    } catch (Exception e4) {
                        UMLog uMLog3 = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(TagManager.a, 0, "exception:" + e4.getMessage());
                        tagListCallBack.onMessage(false, null);
                        return;
                    }
                }
                try {
                    throw new Exception(strA);
                } catch (Exception e5) {
                    UMLog uMLog4 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, "exception:" + e5.getMessage());
                    tagListCallBack.onMessage(false, null);
                }
            }
        });
    }

    public void getWeightedTags(final WeightedTagListCallBack weightedTagListCallBack) {
        d.a(new Runnable() { // from class: com.umeng.message.tag.TagManager.8
            @Override // java.lang.Runnable
            public void run() {
                Hashtable<String, Integer> weightedTags;
                Hashtable<String, Integer> hashtable = new Hashtable<>();
                if (TagManager.this.d()) {
                    String strA = TagManager.this.a(MessageSharedPrefs.getInstance(TagManager.this.e).getListWeightedTagsInterval(), true);
                    if (TextUtils.isEmpty(strA)) {
                        try {
                            weightedTags = TagManager.f.getWeightedTags(TagManager.this.c());
                        } catch (Exception unused) {
                        }
                        try {
                            weightedTagListCallBack.onMessage(true, weightedTags);
                            return;
                        } catch (Exception unused2) {
                            hashtable = weightedTags;
                            UMLog uMLog = UMConfigure.umDebugLog;
                            UMLog.mutlInfo(TagManager.a, 0, "获取加权标签列表异常");
                            weightedTagListCallBack.onMessage(false, hashtable);
                            return;
                        }
                    }
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TagManager.a, 0, strA);
                    weightedTagListCallBack.onMessage(false, hashtable);
                    return;
                }
                UMLog uMLog3 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TagManager.a, 0, "缺少utdid或device token");
                weightedTagListCallBack.onMessage(false, hashtable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.e).getHeader());
        jSONObject.put("utdid", UmengMessageDeviceConfig.getUtdid(this.e));
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, MessageSharedPrefs.getInstance(this.e).getDeviceToken());
        jSONObject.put("ts", System.currentTimeMillis());
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, String str) throws HttpRequest.HttpRequestException, JSONException {
        String strBody = HttpRequest.post(str).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).send(jSONObject.toString()).body("UTF-8");
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "postJson() url=" + str + "\n request = " + jSONObject + "\n response = " + strBody);
        return new JSONObject(strBody);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.e))) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "UTDID为空");
            return false;
        }
        if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.e).getDeviceToken())) {
            return true;
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, "Device token为空");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        boolean z = MessageSharedPrefs.getInstance(this.e).getTagSendPolicy() == 1;
        if (z) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "Tag API被服务器禁止");
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ITagManager.Result f() {
        ITagManager.Result result = new ITagManager.Result(new JSONObject(), false);
        result.remain = MessageSharedPrefs.getInstance(this.e).getTagRemain();
        result.status = "ok";
        result.jsonString = "status:" + result.status + Constants.ACCEPT_TIME_SEPARATOR_SP + " remain:" + result.remain + Constants.ACCEPT_TIME_SEPARATOR_SP + "description:" + result.status;
        return result;
    }
}
