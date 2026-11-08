package com.unity3d.services.purchasing.core;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class TransactionErrorDetailsUtilities {
    public static final String EXCEPTION_MESSAGE = "exceptionMessage";
    public static final String EXTRAS = "extras";
    public static final String STORE = "store";
    public static final String STORE_SPECIFIC_ERROR_CODE = "storeSpecificErrorCode";
    public static final String TRANSACTION_ERROR = "transactionError";

    public static JSONObject getJSONObjectForTransactionErrorDetails(TransactionErrorDetails transactionErrorDetails) throws JSONException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TRANSACTION_ERROR, transactionErrorDetails.getTransactionError().toString());
            jSONObject.put(EXCEPTION_MESSAGE, transactionErrorDetails.getExceptionMessage());
            jSONObject.put(STORE, transactionErrorDetails.getStore().toString());
            jSONObject.put(STORE_SPECIFIC_ERROR_CODE, transactionErrorDetails.getStoreSpecificErrorCode());
            jSONObject.put("extras", JSONUtilities.mapToJsonObject(transactionErrorDetails.getExtras()));
        } catch (Exception e) {
            DeviceLog.error("Could not generate JSON for Transaction Error Details: %s", e.getMessage());
        }
        return jSONObject;
    }
}
