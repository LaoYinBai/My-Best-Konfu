package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.google.vr.ndk.base.DaydreamApi;
import com.sigmob.sdk.common.Constants;
import com.umeng.commonsdk.framework.c;
import com.unity.purchasing.common.IStoreCallback;
import com.unity.purchasing.common.IUnityCallback;
import com.unity.purchasing.common.InitializationFailureReason;
import com.unity.purchasing.common.ProductDefinition;
import com.unity.purchasing.common.ProductDescription;
import com.unity.purchasing.common.ProductMetadata;
import com.unity.purchasing.common.ProductType;
import com.unity.purchasing.common.PurchaseFailureDescription;
import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.StoreDeserializer;
import com.unity.purchasing.common.UnityPurchasing;
import com.unity.purchasing.googleplay.IabHelper;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GooglePlayPurchasing extends StoreDeserializer {
    public static final int ACTIVITY_REQUEST_CODE = 999;
    protected static final String TAG = "UnityIAP";
    private static GooglePlayPurchasing instance;
    private static final boolean isDaydreamApiAvailable;
    public boolean activityPending;
    private Context context;
    public IabHelper helper;
    private Inventory inventory;
    private boolean isUnityVrEnabled;
    private IActivityLauncher launcher;
    private IBillingServiceManager manager;
    public String productJSON;
    private ProductDefinition productUnderPurchase;
    private IStoreCallback unityPurchasing;
    private HashSet<String> suspectFailedConsumableSkus = new HashSet<>();
    Features features = new Features();
    public IabHelper.OnIabPurchaseFinishedListener PurchaseListener = new IabHelper.OnIabPurchaseFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.3
        /* JADX WARN: Removed duplicated region for block: B:19:0x006f  */
        @Override // com.unity.purchasing.googleplay.IabHelper.OnIabPurchaseFinishedListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onIabPurchaseFinished(com.unity.purchasing.googleplay.IabResult r6, com.unity.purchasing.googleplay.Purchase r7) {
            /*
                r5 = this;
                com.unity.purchasing.googleplay.GooglePlayPurchasing r0 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                boolean r0 = com.unity.purchasing.googleplay.GooglePlayPurchasing.access$600(r0)
                if (r0 != 0) goto L9
                return
            L9:
                java.lang.String r0 = "onIabPurchaseFinished: %s"
                boolean r1 = r6.isSuccess()
                java.lang.String r1 = java.lang.Boolean.toString(r1)
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$000(r0, r1)
                java.lang.String r0 = r6.mMessage
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$100(r0)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r0 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                r1 = 0
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$602(r0, r1)
                boolean r0 = r6.isSuccess()
                if (r0 == 0) goto L33
                java.lang.String r6 = "Product purchased successfully!"
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$100(r6)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$700(r6, r7)
                goto Lb9
            L33:
                int r7 = r6.getResponse()
                java.lang.String r0 = "Purchase response code:%s"
                java.lang.String r1 = java.lang.Integer.toString(r7)
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$000(r0, r1)
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.Unknown
                com.unity.purchasing.googleplay.GooglePlayPurchasing r1 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                java.util.HashSet r1 = com.unity.purchasing.googleplay.GooglePlayPurchasing.access$900(r1)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r2 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.common.ProductDefinition r2 = com.unity.purchasing.googleplay.GooglePlayPurchasing.access$800(r2)
                java.lang.String r2 = r2.storeSpecificId
                r1.add(r2)
                r1 = -1005(0xfffffffffffffc13, float:NaN)
                if (r7 == r1) goto L6f
                r1 = 7
                if (r7 == r1) goto L64
                switch(r7) {
                    case 1: goto L6f;
                    case 2: goto L61;
                    case 3: goto L61;
                    case 4: goto L5e;
                    default: goto L5d;
                }
            L5d:
                goto L71
            L5e:
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.ItemUnavailable
                goto L71
            L61:
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.BillingUnavailable
                goto L71
            L64:
                com.unity.purchasing.googleplay.GooglePlayPurchasing r1 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.googleplay.GooglePlayPurchasing$Features r1 = r1.features
                boolean r1 = r1.supportsPurchaseFailureReasonDuplicateTransaction
                if (r1 == 0) goto L71
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.DuplicateTransaction
                goto L71
            L6f:
                com.unity.purchasing.common.PurchaseFailureReason r0 = com.unity.purchasing.common.PurchaseFailureReason.UserCancelled
            L71:
                com.unity.purchasing.common.PurchaseFailureDescription r1 = new com.unity.purchasing.common.PurchaseFailureDescription
                com.unity.purchasing.googleplay.GooglePlayPurchasing r2 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.common.ProductDefinition r2 = com.unity.purchasing.googleplay.GooglePlayPurchasing.access$800(r2)
                java.lang.String r2 = r2.storeSpecificId
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "GOOGLEPLAY_"
                r3.append(r4)
                java.lang.String r4 = r6.mMessage
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.util.HashMap<java.lang.Integer, java.lang.String> r4 = com.unity.purchasing.googleplay.IabHelper.billingResponseCodeNames
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                java.lang.Object r7 = r4.get(r7)
                java.lang.String r7 = (java.lang.String) r7
                r1.<init>(r2, r0, r3, r7)
                int r6 = r6.getResponse()
                r7 = -1002(0xfffffffffffffc16, float:NaN)
                if (r6 != r7) goto Lb0
                java.lang.String r6 = "Received bad response, polling for successful purchases to investigate failure more deeply"
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$100(r6)
                com.unity.purchasing.googleplay.GooglePlayPurchasing r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.googleplay.GooglePlayPurchasing.access$1000(r6, r1)
                goto Lb9
            Lb0:
                com.unity.purchasing.googleplay.GooglePlayPurchasing r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.this
                com.unity.purchasing.common.IStoreCallback r6 = com.unity.purchasing.googleplay.GooglePlayPurchasing.access$1100(r6)
                r6.OnPurchaseFailed(r1)
            Lb9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.GooglePlayPurchasing.AnonymousClass3.onIabPurchaseFinished(com.unity.purchasing.googleplay.IabResult, com.unity.purchasing.googleplay.Purchase):void");
        }
    };
    private BroadcastReceiver purchasesUpdatedReceiver = null;
    private int offlineBackOffTime = 5000;
    private volatile boolean purchaseInProgress = false;
    private volatile boolean subscriptionUpdateInProgress = false;

    static {
        boolean z;
        try {
            Class.forName("com.google.vr.ndk.base.DaydreamApi");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        isDaydreamApiAvailable = z;
    }

    class Features {
        public boolean supportsPurchaseFailureReasonDuplicateTransaction;

        Features() {
        }
    }

    public static GooglePlayPurchasing instance(IUnityCallback iUnityCallback) {
        if (instance == null) {
            BillingServiceManager billingServiceManager = new BillingServiceManager(UnityPlayer.currentActivity);
            instance = new GooglePlayPurchasing(new UnityPurchasing(iUnityCallback), new IabHelper(UnityPlayer.currentActivity, billingServiceManager, new ActivityLauncher()), billingServiceManager, UnityPlayer.currentActivity, new ActivityLauncher());
        }
        return instance;
    }

    public static boolean ContinuePurchase(Activity activity, String str, String str2) throws JSONException {
        if (instance == null) {
            return false;
        }
        instance.StartPurchase(activity, str, str2);
        return true;
    }

    public static boolean ContinueSubscriptionUpdate(Activity activity, String str, String str2) {
        if (instance == null) {
            return false;
        }
        instance.StartSubscriptionUpdate(activity, str, str2);
        return true;
    }

    public static void ProcessActivityResult(int i, int i2, Intent intent) {
        if (instance != null) {
            instance.onActivityResult(i, i2, intent);
        }
    }

    public GooglePlayPurchasing(IStoreCallback iStoreCallback, IabHelper iabHelper, IBillingServiceManager iBillingServiceManager, Context context, IActivityLauncher iActivityLauncher) {
        this.unityPurchasing = iStoreCallback;
        this.helper = iabHelper;
        this.helper.enableDaydreamApi(isDaydreamApiAvailable);
        this.manager = iBillingServiceManager;
        this.context = context;
        this.launcher = iActivityLauncher;
        instance = this;
        registerPurchasesUpdatedReceiver();
    }

    public void SetUnityVrEnabled(boolean z) {
        this.isUnityVrEnabled = z;
        log("isUnityVrEnabled = %s", String.valueOf(this.isUnityVrEnabled));
    }

    public void RestoreTransactions(final IGooglePlayStoreCallback iGooglePlayStoreCallback) {
        this.helper.queryInventoryAsync(true, new ArrayList(), new IabHelper.QueryInventoryFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.1
            @Override // com.unity.purchasing.googleplay.IabHelper.QueryInventoryFinishedListener
            public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) throws Exception {
                GooglePlayPurchasing.log("RestoreInventoryFinished: %s", Boolean.toString(iabResult.isSuccess()));
                GooglePlayPurchasing.log(iabResult.mMessage);
                if (!iabResult.isFailure()) {
                    GooglePlayPurchasing.this.inventory = inventory;
                    GooglePlayPurchasing.this.NotifyUnityOfProducts(inventory);
                    iGooglePlayStoreCallback.OnTransactionsRestored(true);
                } else {
                    GooglePlayPurchasing.log("Failed to Restore inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    iGooglePlayStoreCallback.OnTransactionsRestored(false);
                }
            }
        }, 0L);
    }

    public void UpgradeDowngradeSubscription(String str, String str2) {
        if (this.subscriptionUpdateInProgress) {
            log("Subscription update is in progress");
            return;
        }
        if (!this.helper.subscriptionUpgradeDowngradeSupported()) {
            log("UpgradeDowngradeSubscription is not supported, this service needs v5 and higher android in app billing api");
            return;
        }
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            log("Cannot update subscription. Subscription product identifiers(SKUs) must not be empty");
            return;
        }
        boolean z = (this.context instanceof UnityPlayerActivity) && this.isUnityVrEnabled && isDaydreamApiAvailable;
        final Intent intentCreatePurchaseIntent = createPurchaseIntent(z);
        intentCreatePurchaseIntent.putExtra("oldSkuMetadata", str);
        intentCreatePurchaseIntent.putExtra("newSku", str2);
        intentCreatePurchaseIntent.putExtra("type", "subscription_update");
        this.subscriptionUpdateInProgress = true;
        this.purchaseInProgress = true;
        this.activityPending = true;
        if (z) {
            new Handler(this.context.getMainLooper()).post(new Runnable() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.2
                @Override // java.lang.Runnable
                public void run() {
                    intentCreatePurchaseIntent.putExtra("vr", true);
                    DaydreamApi daydreamApiCreate = DaydreamApi.create(GooglePlayPurchasing.this.context);
                    daydreamApiCreate.launchInVr(intentCreatePurchaseIntent);
                    daydreamApiCreate.close();
                }
            });
        } else {
            this.launcher.startActivity(this.context, intentCreatePurchaseIntent);
        }
    }

    public void StartSubscriptionUpdate(Activity activity, String str, String str2) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jSONObject.has("productId") ? jSONObject.getString("productId") : null;
        if (string == null) {
            log("Error: the product that is going to be updated does not have a valid product id");
            return;
        }
        if (!this.inventory.hasDetails(string)) {
            log("Error: the product that is going to be updated is not in the current inventory");
            return;
        }
        if (!this.inventory.hasPurchase(string)) {
            log("Error: the product that is going to be updated has not been purchased yet.");
            return;
        }
        if (!this.inventory.hasDetails(str2)) {
            log("Error: the product that is going to be updated to is not in the current inventory");
            return;
        }
        this.productUnderPurchase = new ProductDefinition(str2, str2, ProductType.Subscription);
        ArrayList arrayList = new ArrayList();
        arrayList.add(string);
        this.helper.launchSubscriptionUpdateFlow(activity, str2, arrayList, 999, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload(null, str2, str));
    }

    public void StartPurchase(Activity activity, String str, String str2) throws JSONException {
        this.helper.enableUnityVr(this.isUnityVrEnabled);
        String strAddFreeTrialAndIntroPriceFlagToDeveloperPayload = addFreeTrialAndIntroPriceFlagToDeveloperPayload(str2, str, null);
        if (this.inventory.getSkuDetails(str).mItemType == "inapp") {
            this.helper.launchPurchaseFlow(activity, str, 999, this.PurchaseListener, strAddFreeTrialAndIntroPriceFlagToDeveloperPayload);
        } else {
            this.helper.launchSubscriptionPurchaseFlow(activity, str, 999, this.PurchaseListener, strAddFreeTrialAndIntroPriceFlagToDeveloperPayload);
        }
    }

    private String addFreeTrialAndIntroPriceFlagToDeveloperPayload(String str, String str2, String str3) throws JSONException {
        boolean z;
        boolean z2;
        String updateMetadata = null;
        SkuDetails skuDetails = this.inventory.hasDetails(str2) ? this.inventory.getSkuDetails(str2) : null;
        JSONObject jSONObject = new JSONObject();
        String str4 = new String();
        if (str == null) {
            str = "";
        } else {
            try {
                str4 = new JSONObject(str).getString("accountId");
            } catch (JSONException unused) {
            }
        }
        boolean z3 = false;
        String strEncodeToString = Base64.encodeToString(str.getBytes(), 0);
        if (skuDetails == null) {
            try {
                jSONObject.put("developerPayload", strEncodeToString);
                jSONObject.put("is_free_trial", false);
                jSONObject.put("has_introductory_price_trial", false);
                jSONObject.put("is_updated", false);
                jSONObject.put("update_subscription_metadata", (Object) null);
                jSONObject.put("accountId", str4);
            } catch (JSONException unused2) {
            }
            return jSONObject.toString();
        }
        if (str3 != null) {
            log("oldSkuMetadata is NOT null");
            updateMetadata = getUpdateMetadata(str3, skuDetails);
            z = true;
        } else {
            log("oldSkuMetadata is null");
            z = false;
        }
        String type = skuDetails.getType();
        String introductoryPricePeriod = skuDetails.getIntroductoryPricePeriod();
        String freeTrialPeriod = skuDetails.getFreeTrialPeriod();
        boolean zHasPurchaseHistory = this.inventory.hasPurchaseHistory(str2);
        Iterator<String> it = this.inventory.getAllSkus("subs").iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            String next = it.next();
            if (!this.inventory.getSkuDetails(next).getFreeTrialPeriod().isEmpty() && this.inventory.hasPurchaseHistory(next)) {
                z2 = true;
                break;
            }
        }
        boolean z4 = (type.equals("inapp") || freeTrialPeriod.isEmpty() || zHasPurchaseHistory || z2) ? false : true;
        if (!type.equals("inapp") && !introductoryPricePeriod.isEmpty() && !zHasPurchaseHistory) {
            z3 = true;
        }
        try {
            jSONObject.put("developerPayload", strEncodeToString);
            jSONObject.put("is_free_trial", z4);
            jSONObject.put("has_introductory_price_trial", z3);
            jSONObject.put("is_updated", z);
            jSONObject.put("update_subscription_metadata", updateMetadata);
            jSONObject.put("accountId", str4);
        } catch (JSONException unused3) {
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getUpdateMetadata(java.lang.String r13, com.unity.purchasing.googleplay.SkuDetails r14) throws org.json.JSONException {
        /*
            r12 = this;
            long r0 = r14.getPriceInMicros()
            r2 = 0
            r3 = 0
            r4 = 0
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> L34
            r6.<init>(r13)     // Catch: org.json.JSONException -> L34
            java.lang.String r13 = "productId"
            java.lang.String r13 = r6.getString(r13)     // Catch: org.json.JSONException -> L34
            java.lang.String r7 = "is_free_trial"
            boolean r7 = r6.getBoolean(r7)     // Catch: org.json.JSONException -> L30
            java.lang.String r8 = "is_introductory_price_period"
            boolean r8 = r6.getBoolean(r8)     // Catch: org.json.JSONException -> L2c
            java.lang.String r2 = "remaining_time_in_seconds"
            double r9 = r6.getDouble(r2)     // Catch: org.json.JSONException -> L27
            long r9 = (long) r9
            goto L3d
        L27:
            r2 = move-exception
            r11 = r2
            r2 = r13
            r13 = r11
            goto L38
        L2c:
            r6 = move-exception
            r2 = r13
            r13 = r6
            goto L37
        L30:
            r6 = move-exception
            r2 = r13
            r13 = r6
            goto L36
        L34:
            r13 = move-exception
            r2 = r3
        L36:
            r7 = 0
        L37:
            r8 = 0
        L38:
            r13.printStackTrace()
            r13 = r2
            r9 = r4
        L3d:
            if (r13 == 0) goto L8f
            com.unity.purchasing.googleplay.Inventory r2 = r12.inventory
            boolean r2 = r2.hasDetails(r13)
            if (r2 != 0) goto L48
            goto L8f
        L48:
            com.unity.purchasing.googleplay.Inventory r2 = r12.inventory
            com.unity.purchasing.googleplay.SkuDetails r13 = r2.getSkuDetails(r13)
            if (r7 == 0) goto L51
            r9 = r4
        L51:
            if (r8 == 0) goto L64
            long r2 = r14.getPriceInMicros()
            int r14 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r14 == 0) goto L64
            long r2 = r13.getIntroductoryPriceInMicros()
            java.lang.String r13 = r13.getIntroductoryPricePeriod()
            goto L6c
        L64:
            long r2 = r13.getPriceInMicros()
            java.lang.String r13 = r13.getSubscriptionPeriod()
        L6c:
            org.json.JSONObject r14 = new org.json.JSONObject
            r14.<init>()
            java.lang.String r4 = "old_sku_remaining_seconds"
            r14.put(r4, r9)     // Catch: org.json.JSONException -> L86
            java.lang.String r4 = "old_sku_price_in_micros"
            r14.put(r4, r2)     // Catch: org.json.JSONException -> L86
            java.lang.String r2 = "old_sku_period_string"
            r14.put(r2, r13)     // Catch: org.json.JSONException -> L86
            java.lang.String r13 = "new_sku_price_in_micros"
            r14.put(r13, r0)     // Catch: org.json.JSONException -> L86
            goto L8a
        L86:
            r13 = move-exception
            r13.printStackTrace()
        L8a:
            java.lang.String r13 = r14.toString()
            return r13
        L8f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity.purchasing.googleplay.GooglePlayPurchasing.getUpdateMetadata(java.lang.String, com.unity.purchasing.googleplay.SkuDetails):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pollForNewPurchases() {
        reconcileFailedPurchaseWithInventory(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconcileFailedPurchaseWithInventory(final PurchaseFailureDescription purchaseFailureDescription) {
        this.manager.workWith(new BillingServiceProcessor() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.4
            @Override // com.unity.purchasing.googleplay.BillingServiceProcessor
            public void workWith(IInAppBillingService iInAppBillingService) {
                boolean z = false;
                try {
                    boolean zHasPurchase = purchaseFailureDescription != null ? GooglePlayPurchasing.this.inventory.hasPurchase(purchaseFailureDescription.productId) : false;
                    if (GooglePlayPurchasing.this.helper.queryPurchases(GooglePlayPurchasing.this.inventory, "inapp", iInAppBillingService) != 0) {
                        GooglePlayPurchasing.log("Received bad response from queryPurchases");
                    }
                    boolean zHasPurchase2 = purchaseFailureDescription != null ? GooglePlayPurchasing.this.inventory.hasPurchase(purchaseFailureDescription.productId) : false;
                    if (purchaseFailureDescription != null) {
                        if ((!zHasPurchase && !zHasPurchase2) || (zHasPurchase && zHasPurchase2)) {
                            GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(purchaseFailureDescription);
                        } else if (!zHasPurchase && zHasPurchase2) {
                            Purchase purchase = GooglePlayPurchasing.this.inventory.getPurchase(purchaseFailureDescription.productId);
                            GooglePlayPurchasing.this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), GooglePlayPurchasing.this.encodeReceipt(purchase, GooglePlayPurchasing.this.inventory.getSkuDetails(purchaseFailureDescription.productId)), purchase.getOrderIdOrPurchaseToken());
                        }
                        z = true;
                    }
                    if (z) {
                        return;
                    }
                    GooglePlayPurchasing.this.NotifyUnityOfProducts(GooglePlayPurchasing.this.inventory);
                } catch (RemoteException | JSONException e) {
                    Log.e(GooglePlayPurchasing.TAG, c.c, e);
                    if (purchaseFailureDescription == null || z) {
                        return;
                    }
                    GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(purchaseFailureDescription);
                }
            }
        });
    }

    private void registerPurchasesUpdatedReceiver() {
        if (this.purchasesUpdatedReceiver == null) {
            this.purchasesUpdatedReceiver = new BroadcastReceiver() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.5
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    GooglePlayPurchasing.this.pollForNewPurchases();
                }
            };
            this.context.registerReceiver(this.purchasesUpdatedReceiver, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void QueryInventory(final List<String> list, long j) {
        log("QueryInventory: %s", Integer.toString(list.size()));
        this.helper.queryInventoryAsync(true, list, new IabHelper.QueryInventoryFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.6
            @Override // com.unity.purchasing.googleplay.IabHelper.QueryInventoryFinishedListener
            public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) throws Exception {
                GooglePlayPurchasing.log("onQueryInventoryFinished: %s", Boolean.toString(iabResult.isSuccess()));
                GooglePlayPurchasing.log(iabResult.mMessage);
                if (!iabResult.isFailure()) {
                    GooglePlayPurchasing.this.inventory = inventory;
                    for (String str : list) {
                        if (GooglePlayPurchasing.this.inventory.hasConsumablePurchaseHistory(str) && !GooglePlayPurchasing.this.inventory.hasPurchase(str)) {
                            GooglePlayPurchasing.this.helper.consumeAsync(GooglePlayPurchasing.this.inventory.getHistoryPurchase(str), (IabHelper.OnConsumeFinishedListener) null, true);
                        }
                    }
                    GooglePlayPurchasing.this.NotifyUnityOfProducts(inventory);
                    return;
                }
                GooglePlayPurchasing.log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                GooglePlayPurchasing.this.QueryInventory(list, (long) GooglePlayPurchasing.this.offlineBackOffTime);
                GooglePlayPurchasing.this.offlineBackOffTime = Math.min(Constants.PRECACHE_SIZE, GooglePlayPurchasing.this.offlineBackOffTime * 2);
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void NotifyUnityOfProducts(Inventory inventory) throws JSONException {
        String orderIdOrPurchaseToken;
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, SkuDetails> entry : inventory.mSkuMap.entrySet()) {
            SkuDetails value = entry.getValue();
            try {
                jSONObject.put(entry.getKey(), value.getOriginalJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ProductMetadata productMetadata = new ProductMetadata(value.getPrice(), value.getTitle(), value.getDescription(), value.getISOCurrencyCode(), new BigDecimal(value.getPriceInMicros()).divide(new BigDecimal(1000000)));
            String key = entry.getKey();
            String strEncodeReceipt = null;
            if (inventory.hasPurchase(key)) {
                Purchase purchase = inventory.getPurchase(key);
                strEncodeReceipt = encodeReceipt(purchase, inventory.getSkuDetails(key));
                orderIdOrPurchaseToken = purchase.getOrderIdOrPurchaseToken();
            } else {
                orderIdOrPurchaseToken = null;
            }
            arrayList.add(new ProductDescription(key, productMetadata, strEncodeReceipt, orderIdOrPurchaseToken));
        }
        this.productJSON = jSONObject.toString();
        this.unityPurchasing.OnProductsRetrieved(arrayList);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.helper != null) {
            log("onActivityResult");
            this.helper.handleActivityResult(i, i2, intent);
            this.purchaseInProgress = false;
            this.subscriptionUpdateInProgress = false;
        }
    }

    private Purchase findPurchaseByOrderId(String str) {
        for (Purchase purchase : this.inventory.getAllPurchases()) {
            if (purchase.getOrderIdOrPurchaseToken().equals(str)) {
                return purchase;
            }
        }
        log("No consumable with order %s", str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void NotifyUnityOfPurchase(Purchase purchase) {
        log("NotifyUnityOfPurchase");
        this.inventory.addPurchase(purchase);
        SkuDetails skuDetails = this.inventory.getSkuDetails(purchase.getSku());
        if (purchase.getItemType().equals("subs")) {
            this.inventory.addPurchaseToSubscriptionPurchaseHistory(purchase.getSku());
        }
        this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), encodeReceipt(purchase, skuDetails), purchase.getOrderIdOrPurchaseToken());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String encodeReceipt(Purchase purchase, SkuDetails skuDetails) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("json", purchase.getOriginalJson());
            jSONObject.put(InAppPurchaseMetaData.KEY_SIGNATURE, purchase.getSignature());
            jSONObject.put("skuDetails", skuDetails.getOriginalJSON());
            jSONObject.put("isPurchaseHistorySupported", this.helper.subscriptionPurchaseHistorySupported());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.i(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str, String str2) {
        log(String.format(str, str2));
    }

    @Override // com.unity.purchasing.common.IStore
    public void RetrieveProducts(List<ProductDefinition> list) {
        final ArrayList arrayList = new ArrayList();
        Iterator<ProductDefinition> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().storeSpecificId);
        }
        IabHelper.OnIabSetupFinishedListener onIabSetupFinishedListener = new IabHelper.OnIabSetupFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.7
            @Override // com.unity.purchasing.googleplay.IabHelper.OnIabSetupFinishedListener
            public void onIabSetupFinished(IabResult iabResult) {
                GooglePlayPurchasing.log("onIabSetupFinished: %s", Integer.toString(iabResult.mResponse));
                if (iabResult.isFailure()) {
                    GooglePlayPurchasing.log("Failed to setup IAB. Notifying Unity...");
                    GooglePlayPurchasing.this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
                } else {
                    GooglePlayPurchasing.log("Requesting %s products", Integer.toString(arrayList.size()));
                    GooglePlayPurchasing.this.QueryInventory(arrayList, 0L);
                }
            }
        };
        if (!this.helper.mSetupDone) {
            try {
                this.manager.initialise();
                this.helper.startSetup(onIabSetupFinishedListener);
                return;
            } catch (GooglePlayBillingUnAvailableException unused) {
                this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
                return;
            }
        }
        log("Requesting %s products", Integer.toString(arrayList.size()));
        QueryInventory(arrayList, 0L);
    }

    private void consumeSuspectFailedPurchase(final ProductDefinition productDefinition, final String str) {
        final String str2 = productDefinition.storeSpecificId;
        this.suspectFailedConsumableSkus.remove(str2);
        this.helper.queryInventoryAsync(false, this.inventory.getAllSkus("inapp"), new IabHelper.QueryInventoryFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.8
            @Override // com.unity.purchasing.googleplay.IabHelper.QueryInventoryFinishedListener
            public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) throws Exception {
                if (!iabResult.isFailure()) {
                    GooglePlayPurchasing.this.inventory = inventory;
                    if (GooglePlayPurchasing.this.inventory.hasConsumablePurchaseHistory(str2)) {
                        GooglePlayPurchasing.this.helper.consumeAsync(GooglePlayPurchasing.this.inventory.getHistoryPurchase(str2), new IabHelper.OnConsumeFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.8.1
                            @Override // com.unity.purchasing.googleplay.IabHelper.OnConsumeFinishedListener
                            public void onConsumeFinished(Purchase purchase, IabResult iabResult2) throws JSONException {
                                GooglePlayPurchasing.this.Purchase(productDefinition, str);
                            }
                        }, true);
                        return;
                    } else {
                        GooglePlayPurchasing.this.Purchase(productDefinition, str);
                        return;
                    }
                }
                GooglePlayPurchasing.log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                GooglePlayPurchasing.this.QueryInventory(GooglePlayPurchasing.this.inventory.getAllSkus("inapp"), (long) GooglePlayPurchasing.this.offlineBackOffTime);
                GooglePlayPurchasing.this.offlineBackOffTime = Math.min(Constants.PRECACHE_SIZE, GooglePlayPurchasing.this.offlineBackOffTime * 2);
            }
        }, 0L);
    }

    @Override // com.unity.purchasing.common.IStore
    public void Purchase(ProductDefinition productDefinition) {
        Purchase(productDefinition, (String) null);
    }

    @Override // com.unity.purchasing.common.IStore
    public void Purchase(ProductDefinition productDefinition, String str) {
        if (this.purchaseInProgress) {
            this.unityPurchasing.OnPurchaseFailed(new PurchaseFailureDescription(productDefinition.storeSpecificId, PurchaseFailureReason.ExistingPurchasePending));
            return;
        }
        if (productDefinition.type == ProductType.Consumable && this.suspectFailedConsumableSkus.contains(productDefinition.storeSpecificId) && !this.inventory.hasPurchase(productDefinition.storeSpecificId)) {
            consumeSuspectFailedPurchase(productDefinition, str);
            return;
        }
        String str2 = productDefinition.storeSpecificId;
        this.productUnderPurchase = productDefinition;
        log("onPurchaseProduct: %s", str2);
        SkuDetails skuDetails = this.inventory.getSkuDetails(str2);
        log("ITEM TYPE:%s", skuDetails.getType());
        boolean z = (this.context instanceof UnityPlayerActivity) && this.isUnityVrEnabled && isDaydreamApiAvailable;
        final Intent intentCreatePurchaseIntent = createPurchaseIntent(z);
        intentCreatePurchaseIntent.putExtra("productId", str2);
        intentCreatePurchaseIntent.putExtra("itemType", skuDetails.getType());
        intentCreatePurchaseIntent.putExtra("developerPayload", str);
        this.purchaseInProgress = true;
        this.activityPending = true;
        if (z) {
            new Handler(this.context.getMainLooper()).post(new Runnable() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.9
                @Override // java.lang.Runnable
                public void run() {
                    intentCreatePurchaseIntent.putExtra("vr", true);
                    DaydreamApi daydreamApiCreate = DaydreamApi.create(GooglePlayPurchasing.this.context);
                    daydreamApiCreate.launchInVr(intentCreatePurchaseIntent);
                    daydreamApiCreate.close();
                }
            });
        } else {
            this.launcher.startActivity(this.context, intentCreatePurchaseIntent);
        }
    }

    protected Intent createPurchaseIntent(boolean z) {
        return new Intent(this.context, (Class<?>) (z ? VRPurchaseActivity.class : PurchaseActivity.class));
    }

    @Override // com.unity.purchasing.common.IStore
    public void FinishTransaction(ProductDefinition productDefinition, String str) {
        Purchase purchaseFindPurchaseByOrderId;
        log("Finish transaction:%s", str);
        if (productDefinition == null) {
            log("Received FinishTransaction for unknown product with transaction %s. Not consuming.", str);
        } else {
            if (productDefinition.type != ProductType.Consumable || (purchaseFindPurchaseByOrderId = findPurchaseByOrderId(str)) == null) {
                return;
            }
            log("Consuming %s", purchaseFindPurchaseByOrderId.getSku());
            this.inventory.erasePurchase(purchaseFindPurchaseByOrderId.getSku());
            this.helper.consumeAsync(purchaseFindPurchaseByOrderId, new IabHelper.OnConsumeFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.10
                @Override // com.unity.purchasing.googleplay.IabHelper.OnConsumeFinishedListener
                public void onConsumeFinished(Purchase purchase, IabResult iabResult) throws JSONException {
                    GooglePlayPurchasing.log("onConsumeFinished:%s", Boolean.toString(iabResult.isSuccess()));
                    GooglePlayPurchasing.log(iabResult.mMessage);
                    GooglePlayPurchasing.log(String.valueOf(iabResult.getResponse()));
                }
            }, false);
        }
    }

    public void FinishAdditionalTransaction(String str, String str2) {
        log("Finish transaction:%s", str2);
        Purchase purchaseFindPurchaseByOrderId = findPurchaseByOrderId(str2);
        if (purchaseFindPurchaseByOrderId != null) {
            log("Consuming %s", purchaseFindPurchaseByOrderId.getSku());
            this.inventory.erasePurchase(purchaseFindPurchaseByOrderId.getSku());
            this.helper.consumeAsync(purchaseFindPurchaseByOrderId, new IabHelper.OnConsumeFinishedListener() { // from class: com.unity.purchasing.googleplay.GooglePlayPurchasing.11
                @Override // com.unity.purchasing.googleplay.IabHelper.OnConsumeFinishedListener
                public void onConsumeFinished(Purchase purchase, IabResult iabResult) throws JSONException {
                    GooglePlayPurchasing.log("onConsumeFinished:%s", Boolean.toString(iabResult.isSuccess()));
                    GooglePlayPurchasing.log(iabResult.mMessage);
                    GooglePlayPurchasing.log(String.valueOf(iabResult.getResponse()));
                }
            }, false);
        }
    }

    public void SetFeatures(String str) {
        for (String str2 : str.split(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP)) {
            if (str2.equals("supportsPurchaseFailureReasonDuplicateTransaction")) {
                this.features.supportsPurchaseFailureReasonDuplicateTransaction = true;
            }
        }
    }
}
