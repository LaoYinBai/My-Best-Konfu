package com.kwad.sdk.collector;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.t;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AppStatusRules extends BaseResultData implements com.kwad.sdk.core.b {
    public static final long DEFAULT_GRANULARITY = 60000;
    public static final long DEFAULT_START_TIME = 86400000;
    public static String SUFFIX_ALL_CHILDREN = "/*";
    public static String SUFFIX_ALL_FILE = "*";
    private static final long serialVersionUID = 3026909056348431027L;
    private AppStatusInfo data = new AppStatusInfo();

    private static class AppStatusInfo implements com.kwad.sdk.core.b, Serializable {
        private static final long serialVersionUID = -2403646317801179050L;
        private ArrayList<Strategy> namedStrategy;
        private Strategy strategy;
        private ArrayList<com.kwad.sdk.collector.model.d> target;
        private UploadConfig uploadConfig;
        private ArrayList<com.kwad.sdk.collector.model.d> uploadTarget;

        private AppStatusInfo() {
            this.strategy = new Strategy();
            this.target = new ArrayList<>();
            this.namedStrategy = new ArrayList<>();
            this.uploadTarget = new ArrayList<>();
            this.uploadConfig = new UploadConfig();
        }

        private void duplicateTarget() {
            Set<String> targetPackages = this.strategy.getTargetPackages();
            HashSet hashSet = new HashSet();
            Iterator<Strategy> it = this.namedStrategy.iterator();
            while (it.hasNext()) {
                hashSet.addAll(it.next().getTargetPackages());
            }
            targetPackages.retainAll(hashSet);
            this.strategy.removeTargetsByPackage(targetPackages);
        }

        public Strategy getStrategy() {
            return this.strategy;
        }

        public ArrayList<com.kwad.sdk.collector.model.d> getTarget() {
            return this.target;
        }

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            try {
                if (jSONObject.has("namedStrategy")) {
                    this.namedStrategy = Strategy.createFromJSONArray(jSONObject.getJSONArray("namedStrategy"));
                }
                if (jSONObject.has("uploadTarget")) {
                    this.uploadTarget = com.kwad.sdk.collector.model.c.a(jSONObject.optJSONArray("uploadTarget"));
                }
                if (jSONObject.has("uploadConfig")) {
                    this.uploadConfig.parseJson(jSONObject.optJSONObject("uploadConfig"));
                }
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("strategy");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("target");
                this.strategy.parseJson(jSONObjectOptJSONObject);
                this.target = com.kwad.sdk.collector.model.c.a(jSONArrayOptJSONArray);
                this.strategy.setTarget(this.target);
                duplicateTarget();
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public void setTarget(ArrayList<com.kwad.sdk.collector.model.d> arrayList) {
            this.target = arrayList;
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            t.a(jSONObject, "strategy", this.strategy);
            t.a(jSONObject, "target", this.target);
            t.a(jSONObject, "namedStrategy", this.namedStrategy);
            t.a(jSONObject, "uploadTarget", this.uploadTarget);
            t.a(jSONObject, "uploadConfig", this.uploadConfig);
            return jSONObject;
        }
    }

    public static class Strategy implements com.kwad.sdk.core.b, Serializable {
        public static Strategy LOCAL_DEFAULT = new Strategy();
        private static final long serialVersionUID = -1387498537762043285L;
        private long minLaunchInterval;
        private long scanInterval;
        private long startTime = AppStatusRules.DEFAULT_START_TIME;
        private long historyGranularity = 60000;
        private String name = null;
        private HashMap<String, com.kwad.sdk.collector.model.d> targetMap = new HashMap<>();
        private boolean needLaunch = true;
        private long needSaveLaunchTime = -1;

        static {
            LOCAL_DEFAULT.setStartTime(AppStatusRules.DEFAULT_START_TIME);
            LOCAL_DEFAULT.setHistoryGranularity(60000L);
        }

        public static ArrayList<Strategy> createFromJSONArray(JSONArray jSONArray) throws JSONException {
            ArrayList<Strategy> arrayList = new ArrayList<>();
            if (jSONArray != null && jSONArray.length() != 0) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = jSONArray.getJSONObject(i);
                    } catch (JSONException unused) {
                    }
                    if (jSONObject != null) {
                        Strategy strategy = new Strategy();
                        strategy.parseJson(jSONObject);
                        arrayList.add(strategy);
                    }
                }
            }
            return arrayList;
        }

        public long getHistoryGranularity() {
            return this.historyGranularity;
        }

        public long getMinLaunchIntervalWithMS() {
            return this.minLaunchInterval * 1000;
        }

        public String getName() {
            return this.name;
        }

        public long getNeedSaveLaunchTime() {
            return this.needSaveLaunchTime;
        }

        public long getScanInterval() {
            return this.scanInterval;
        }

        public long getStartTimeWithMS() {
            return this.startTime * 1000;
        }

        public ArrayList<com.kwad.sdk.collector.model.d> getTarget() {
            return new ArrayList<>(this.targetMap.values());
        }

        public Set<String> getTargetPackages() {
            Collection<com.kwad.sdk.collector.model.d> collectionValues = this.targetMap.values();
            HashSet hashSet = new HashSet();
            Iterator<com.kwad.sdk.collector.model.d> it = collectionValues.iterator();
            while (it.hasNext()) {
                hashSet.add(com.kwad.sdk.collector.model.c.a(it.next()));
            }
            return hashSet;
        }

        public boolean isNeedLaunch() {
            return this.needLaunch;
        }

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            try {
                this.startTime = jSONObject.optLong("startTime");
                this.scanInterval = jSONObject.optLong("scanInterval");
                if (jSONObject.optInt("historyGranularity") > 0) {
                    this.historyGranularity = r0 * 1000;
                }
                this.name = jSONObject.optString("name");
                this.minLaunchInterval = jSONObject.optLong("minLaunchInterval");
                this.needSaveLaunchTime = jSONObject.optLong("needSaveLaunchTime");
                this.needLaunch = jSONObject.optBoolean("needLaunch");
                setTarget(com.kwad.sdk.collector.model.c.a(jSONObject.optJSONArray("target")));
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        public void removeTargetsByPackage(Collection<String> collection) {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                this.targetMap.remove(it.next());
            }
        }

        public void setHistoryGranularity(long j) {
            this.historyGranularity = j;
        }

        public void setNeedLaunch(boolean z) {
            this.needLaunch = z;
        }

        public void setNeedSaveLaunchTime(long j) {
            this.needSaveLaunchTime = j;
        }

        public void setScanInterval(long j) {
            this.scanInterval = j;
        }

        public void setStartTime(long j) {
            this.startTime = j;
        }

        public void setTarget(ArrayList<com.kwad.sdk.collector.model.d> arrayList) {
            if (arrayList == null) {
                this.targetMap.clear();
                return;
            }
            Iterator<com.kwad.sdk.collector.model.d> it = arrayList.iterator();
            while (it.hasNext()) {
                com.kwad.sdk.collector.model.d next = it.next();
                this.targetMap.put(com.kwad.sdk.collector.model.c.a(next), next);
            }
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            t.a(jSONObject, "startTime", this.startTime);
            t.a(jSONObject, "scanInterval", this.scanInterval);
            t.a(jSONObject, "historyGranularity", this.historyGranularity / 1000);
            t.a(jSONObject, "name", this.name);
            t.a(jSONObject, "target", getTarget());
            t.a(jSONObject, "minLaunchInterval", this.minLaunchInterval);
            t.a(jSONObject, "needSaveLaunchTime", this.needSaveLaunchTime);
            t.a(jSONObject, "needLaunch", this.needLaunch);
            return jSONObject;
        }
    }

    public static class UploadConfig extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b, Serializable {
        public static final int DEFAULT_FILE_MAX_SIZE = 102400;
        private static final long serialVersionUID = 8541150615721258815L;
        public long fileMaxSize = 102400;

        @Override // com.kwad.sdk.core.response.kwai.a
        public void afterParseJson(@Nullable JSONObject jSONObject) {
            super.afterParseJson(jSONObject);
            if (this.fileMaxSize < 0) {
                this.fileMaxSize = 102400L;
            }
        }
    }

    @NonNull
    public static AppStatusRules createFromJson(String str) throws Throwable {
        AppStatusRules appStatusRules = new AppStatusRules();
        if (TextUtils.isEmpty(str)) {
            return appStatusRules;
        }
        try {
            appStatusRules.parseJson(new JSONObject(str));
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        return appStatusRules;
    }

    public static boolean isAppStatusTargetNotEmpty(AppStatusRules appStatusRules) {
        if (appStatusRules == null) {
            return false;
        }
        boolean zTargetNotEmpty = appStatusRules.targetNotEmpty();
        List<Strategy> listObtainNamedStrategyList = appStatusRules.obtainNamedStrategyList();
        return zTargetNotEmpty || (listObtainNamedStrategyList != null && listObtainNamedStrategyList.size() > 0);
    }

    public static boolean isUploadTargetNotEmpty(AppStatusRules appStatusRules) {
        List<com.kwad.sdk.collector.model.d> uploadTargets;
        return (appStatusRules == null || (uploadTargets = appStatusRules.getUploadTargets()) == null || uploadTargets.size() <= 0) ? false : true;
    }

    public ArrayList<Strategy> getAllStrategy() {
        ArrayList<Strategy> arrayList = new ArrayList<>();
        if (this.data.strategy != null) {
            arrayList.add(this.data.strategy);
        }
        if (this.data.namedStrategy != null) {
            arrayList.addAll(this.data.namedStrategy);
        }
        return arrayList;
    }

    @Nullable
    public List<com.kwad.sdk.collector.model.d> getUploadTargets() {
        if (this.data == null) {
            return null;
        }
        return this.data.uploadTarget;
    }

    public void initStatus(Context context) {
        Iterator<Strategy> it = getAllStrategy().iterator();
        while (it.hasNext()) {
            Strategy next = it.next();
            next.setNeedLaunch(h.a(context, next));
        }
    }

    public long obtainDefaultScanInterval() {
        if (this.data == null || this.data.getStrategy() == null) {
            return 0L;
        }
        long scanInterval = this.data.getStrategy().getScanInterval();
        return (scanInterval >= 0 ? scanInterval : 0L) * 1000;
    }

    public Strategy obtainDefaultStrategy() {
        if (this.data == null) {
            return null;
        }
        return this.data.strategy;
    }

    public List<Strategy> obtainNamedStrategyList() {
        if (this.data == null) {
            return null;
        }
        return this.data.namedStrategy;
    }

    public long obtainUploadConfigFileMaxSize() {
        if (this.data == null || this.data.uploadConfig == null) {
            return 102400L;
        }
        return this.data.uploadConfig.fileMaxSize;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            this.data.parseJson(new JSONObject(com.kwad.sdk.core.a.d.b(jSONObject.optString("data"))));
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    public boolean targetNotEmpty() {
        return (this.data == null || this.data.target == null || this.data.target.size() <= 0) ? false : true;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "data", this.data);
        return json;
    }
}
