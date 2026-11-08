package com.facebook.stetho.inspector.domstorage;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DOMStoragePeerManager extends ChromePeerManager {
    private final Context mContext;
    private final PeerRegistrationListener mPeerListener = new PeersRegisteredListener() { // from class: com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager.1
        private final List<DevToolsSharedPreferencesListener> mPrefsListeners = new ArrayList();

        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        protected synchronized void onFirstPeerRegistered() {
            for (String str : SharedPreferencesHelper.getSharedPreferenceTags(DOMStoragePeerManager.this.mContext)) {
                SharedPreferences sharedPreferences = DOMStoragePeerManager.this.mContext.getSharedPreferences(str, 0);
                DevToolsSharedPreferencesListener devToolsSharedPreferencesListener = DOMStoragePeerManager.this.new DevToolsSharedPreferencesListener(sharedPreferences, str);
                sharedPreferences.registerOnSharedPreferenceChangeListener(devToolsSharedPreferencesListener);
                this.mPrefsListeners.add(devToolsSharedPreferencesListener);
            }
        }

        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        protected synchronized void onLastPeerUnregistered() {
            Iterator<DevToolsSharedPreferencesListener> it = this.mPrefsListeners.iterator();
            while (it.hasNext()) {
                it.next().unregister();
            }
            this.mPrefsListeners.clear();
        }
    };

    public DOMStoragePeerManager(Context context) {
        this.mContext = context;
        setListener(this.mPeerListener);
    }

    public void signalItemRemoved(DOMStorage.StorageId storageId, String str) {
        DOMStorage.DomStorageItemRemovedParams domStorageItemRemovedParams = new DOMStorage.DomStorageItemRemovedParams();
        domStorageItemRemovedParams.storageId = storageId;
        domStorageItemRemovedParams.key = str;
        sendNotificationToPeers("DOMStorage.domStorageItemRemoved", domStorageItemRemovedParams);
    }

    public void signalItemAdded(DOMStorage.StorageId storageId, String str, String str2) {
        DOMStorage.DomStorageItemAddedParams domStorageItemAddedParams = new DOMStorage.DomStorageItemAddedParams();
        domStorageItemAddedParams.storageId = storageId;
        domStorageItemAddedParams.key = str;
        domStorageItemAddedParams.newValue = str2;
        sendNotificationToPeers("DOMStorage.domStorageItemAdded", domStorageItemAddedParams);
    }

    public void signalItemUpdated(DOMStorage.StorageId storageId, String str, String str2, String str3) {
        DOMStorage.DomStorageItemUpdatedParams domStorageItemUpdatedParams = new DOMStorage.DomStorageItemUpdatedParams();
        domStorageItemUpdatedParams.storageId = storageId;
        domStorageItemUpdatedParams.key = str;
        domStorageItemUpdatedParams.oldValue = str2;
        domStorageItemUpdatedParams.newValue = str3;
        sendNotificationToPeers("DOMStorage.domStorageItemUpdated", domStorageItemUpdatedParams);
    }

    private class DevToolsSharedPreferencesListener implements SharedPreferences.OnSharedPreferenceChangeListener {
        private final Map<String, Object> mCopy;
        private final SharedPreferences mPrefs;
        private final DOMStorage.StorageId mStorageId = new DOMStorage.StorageId();

        public DevToolsSharedPreferencesListener(SharedPreferences sharedPreferences, String str) {
            this.mPrefs = sharedPreferences;
            this.mStorageId.securityOrigin = str;
            this.mStorageId.isLocalStorage = true;
            this.mCopy = DOMStoragePeerManager.prefsCopy(sharedPreferences.getAll());
        }

        public void unregister() {
            this.mPrefs.unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            Map<String, ?> all = sharedPreferences.getAll();
            boolean zContainsKey = this.mCopy.containsKey(str);
            boolean zContainsKey2 = all.containsKey(str);
            Object obj = zContainsKey2 ? all.get(str) : null;
            if (zContainsKey && zContainsKey2) {
                DOMStoragePeerManager.this.signalItemUpdated(this.mStorageId, str, SharedPreferencesHelper.valueToString(this.mCopy.get(str)), SharedPreferencesHelper.valueToString(obj));
                this.mCopy.put(str, obj);
            } else if (zContainsKey) {
                DOMStoragePeerManager.this.signalItemRemoved(this.mStorageId, str);
                this.mCopy.remove(str);
            } else if (zContainsKey2) {
                DOMStoragePeerManager.this.signalItemAdded(this.mStorageId, str, SharedPreferencesHelper.valueToString(obj));
                this.mCopy.put(str, obj);
            } else {
                LogUtil.i("Detected rapid put/remove of %s", str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Object> prefsCopy(Map<String, ?> map) {
        HashMap map2 = new HashMap(map.size());
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Set) {
                map2.put(key, shallowCopy((Set) value));
            } else {
                map2.put(key, value);
            }
        }
        return map2;
    }

    private static <T> Set<T> shallowCopy(Set<T> set) {
        HashSet hashSet = new HashSet();
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        return hashSet;
    }
}
