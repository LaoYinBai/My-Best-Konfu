package com.bytedance.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    public static final b f3529a;

    public static class b {
        public /* synthetic */ b(a aVar) {
        }

        public JSONArray a(Context context) throws JSONException {
            if (context == null) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            JSONArray jSONArray = new JSONArray();
            try {
                List neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
                ArrayList arrayList = new ArrayList();
                if (neighboringCellInfo != null) {
                    for (int i = 0; i < neighboringCellInfo.size(); i++) {
                        try {
                            NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) neighboringCellInfo.get(i);
                            if (!arrayList.contains(Integer.valueOf(neighboringCellInfo2.getCid()))) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("cellId", neighboringCellInfo2.getCid());
                                jSONObject.put("lac", neighboringCellInfo2.getLac());
                                jSONObject.put("rssi", neighboringCellInfo2.getRssi());
                                jSONObject.put("psc", neighboringCellInfo2.getPsc());
                                jSONObject.put("networktype", neighboringCellInfo2.getNetworkType());
                                arrayList.add(Integer.valueOf(neighboringCellInfo2.getCid()));
                                jSONArray.put(jSONObject);
                            }
                        } catch (Exception e) {
                            de.a("", e);
                        }
                    }
                }
            } catch (SecurityException e2) {
                de.a("", e2);
            }
            return jSONArray;
        }
    }

    @TargetApi(23)
    public static class c extends b {
        public /* synthetic */ c(a aVar) {
            super(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.bytedance.a.i.b
        public JSONArray a(Context context) throws JSONException {
            List<CellInfo> allCellInfo;
            ArrayList arrayList;
            CellIdentityCdma cellIdentityCdma;
            if (context == null) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            JSONArray jSONArray = new JSONArray();
            try {
                allCellInfo = telephonyManager.getAllCellInfo();
                arrayList = new ArrayList();
            } catch (SecurityException e) {
                de.a("", e);
            }
            if (allCellInfo == null) {
                return jSONArray;
            }
            for (int i = 0; i < allCellInfo.size(); i++) {
                try {
                    CellInfo cellInfo = allCellInfo.get(i);
                    if (cellInfo instanceof CellInfoGsm) {
                        CellSignalStrengthGsm cellSignalStrength = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                        CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity)) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("cellId", cellIdentity.getCid());
                            jSONObject.put("lac", cellIdentity.getLac());
                            jSONObject.put("dbm", cellSignalStrength.getDbm());
                            jSONObject.put("mcc", cellIdentity.getMcc());
                            jSONObject.put(DispatchConstants.MNC, cellIdentity.getMnc());
                            jSONArray.put(jSONObject);
                            cellIdentityCdma = cellIdentity;
                            arrayList.add(cellIdentityCdma);
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellSignalStrengthLte cellSignalStrength2 = ((CellInfoLte) cellInfo).getCellSignalStrength();
                        CellIdentityLte cellIdentity2 = ((CellInfoLte) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity2)) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("cellId", cellIdentity2.getCi());
                            jSONObject2.put("tac", cellIdentity2.getTac());
                            jSONObject2.put("dbm", cellSignalStrength2.getDbm());
                            jSONObject2.put("mcc", cellIdentity2.getMcc());
                            jSONObject2.put(DispatchConstants.MNC, cellIdentity2.getMnc());
                            jSONArray.put(jSONObject2);
                            cellIdentityCdma = cellIdentity2;
                            arrayList.add(cellIdentityCdma);
                        }
                    } else if (cellInfo instanceof CellInfoWcdma) {
                        CellSignalStrengthWcdma cellSignalStrength3 = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                        CellIdentityWcdma cellIdentity3 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity3)) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("cellId", cellIdentity3.getCid());
                            jSONObject3.put("lac", cellIdentity3.getLac());
                            jSONObject3.put("dbm", cellSignalStrength3.getDbm());
                            jSONObject3.put("psc", cellIdentity3.getPsc());
                            jSONObject3.put("mcc", cellIdentity3.getMcc());
                            jSONObject3.put(DispatchConstants.MNC, cellIdentity3.getMnc());
                            jSONArray.put(jSONObject3);
                            cellIdentityCdma = cellIdentity3;
                            arrayList.add(cellIdentityCdma);
                        }
                    } else if (cellInfo instanceof CellInfoCdma) {
                        CellSignalStrengthCdma cellSignalStrength4 = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                        CellIdentityCdma cellIdentity4 = ((CellInfoCdma) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity4)) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("nid", cellIdentity4.getNetworkId());
                            jSONObject4.put("sid", cellIdentity4.getSystemId());
                            jSONObject4.put("bid", cellIdentity4.getBasestationId());
                            jSONObject4.put("dbm", cellSignalStrength4.getDbm());
                            jSONArray.put(jSONObject4);
                            cellIdentityCdma = cellIdentity4;
                            arrayList.add(cellIdentityCdma);
                        }
                    }
                } catch (Exception e2) {
                    de.a("", e2);
                }
            }
            return jSONArray;
        }
    }

    static {
        a aVar = null;
        f3529a = Build.VERSION.SDK_INT >= 23 ? new c(aVar) : new b(aVar);
    }
}
