package com.sigmob.sdk.base.services;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.mta.PointEntitySensor;
import com.sigmob.sdk.common.mta.PointType;
import com.umeng.commonsdk.proguard.o;
import com.xiaomi.mipush.sdk.Constants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h implements SensorEventListener {
    private static Handler b = null;
    private static int c = 500;
    private List<g> e;
    private SensorManager f;
    DecimalFormat a = new DecimalFormat("#######.######");
    private g d = new g();
    private int g = 300;
    private int h = 50;

    private static class a {
        private static final h a = new h();
    }

    public static h a() {
        return a.a;
    }

    private String a(List<g> list) {
        ArrayList arrayList;
        String str;
        String str2;
        String str3;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        String strA;
        ArrayList arrayList6;
        ArrayList arrayList7;
        ArrayList arrayList8;
        ArrayList arrayList9;
        ArrayList arrayList10;
        String strA2;
        ArrayList arrayList11;
        String strA3;
        List<g> list2 = list;
        StringBuilder sb = new StringBuilder();
        if (list2 == null || list.size() == 0) {
            return sb.toString();
        }
        ArrayList arrayList12 = new ArrayList();
        ArrayList arrayList13 = new ArrayList();
        ArrayList arrayList14 = new ArrayList();
        ArrayList arrayList15 = new ArrayList();
        ArrayList arrayList16 = new ArrayList();
        ArrayList arrayList17 = new ArrayList();
        ArrayList arrayList18 = new ArrayList();
        ArrayList arrayList19 = new ArrayList();
        ArrayList arrayList20 = new ArrayList();
        ArrayList arrayList21 = new ArrayList();
        ArrayList arrayList22 = new ArrayList();
        ArrayList arrayList23 = new ArrayList();
        ArrayList arrayList24 = new ArrayList();
        ArrayList arrayList25 = new ArrayList();
        ArrayList arrayList26 = new ArrayList();
        ArrayList arrayList27 = new ArrayList();
        ArrayList arrayList28 = arrayList24;
        int i = 0;
        while (true) {
            ArrayList arrayList29 = arrayList23;
            if (i >= list.size()) {
                sb.append("{");
                sb.append(String.format("\"gravity\":{%s}", String.format("\"x\":%s,\"y\":%s,\"z\":%s", arrayList12.toString(), arrayList13.toString(), arrayList14.toString())));
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(String.format("\"gyroscope\":{%s}", String.format("\"x\":%s,\"y\":%s,\"z\":%s", arrayList15.toString(), arrayList16.toString(), arrayList17.toString())));
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(String.format("\"magnetic_field\":{%s}", String.format("\"x\":%s,\"y\":%s,\"z\":%s", arrayList18.toString(), arrayList19.toString(), arrayList20.toString())));
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(String.format("\"light\":%s", arrayList27.toString()));
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(String.format("\"linear_acceleration\":{%s}", String.format("\"x\":%s,\"y\":%s,\"z\":%s", arrayList28.toString(), arrayList25.toString(), arrayList26.toString())));
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(String.format("\"accelerometer\":{%s}", String.format("\"x\":%s,\"y\":%s,\"z\":%s", arrayList21.toString(), arrayList22.toString(), arrayList29.toString())));
                sb.append("}");
                return Base64.encodeToString(sb.toString().getBytes(), 2);
            }
            g gVar = list2.get(i);
            if (gVar.a == null || gVar.a.length <= 2) {
                arrayList = arrayList22;
                arrayList12.add(a(com.sigmob.sdk.common.Constants.FAIL));
                arrayList13.add(a(com.sigmob.sdk.common.Constants.FAIL));
                str = com.sigmob.sdk.common.Constants.FAIL;
            } else {
                arrayList = arrayList22;
                arrayList12.add(a(this.a.format(gVar.a[0])));
                arrayList13.add(a(this.a.format(gVar.a[1])));
                str = this.a.format(gVar.a[2]);
            }
            arrayList14.add(a(str));
            if (gVar.b == null || gVar.b.length <= 2) {
                arrayList15.add(a(com.sigmob.sdk.common.Constants.FAIL));
                arrayList16.add(a(com.sigmob.sdk.common.Constants.FAIL));
                str2 = com.sigmob.sdk.common.Constants.FAIL;
            } else {
                arrayList15.add(a(this.a.format(gVar.b[0])));
                arrayList16.add(a(this.a.format(gVar.b[1])));
                str2 = this.a.format(gVar.b[2]);
            }
            arrayList17.add(a(str2));
            if (gVar.c == null || gVar.c.length <= 2) {
                arrayList18.add(a(com.sigmob.sdk.common.Constants.FAIL));
                arrayList19.add(a(com.sigmob.sdk.common.Constants.FAIL));
                str3 = com.sigmob.sdk.common.Constants.FAIL;
            } else {
                arrayList18.add(a(this.a.format(gVar.c[0])));
                arrayList19.add(a(this.a.format(gVar.c[1])));
                str3 = this.a.format(gVar.c[2]);
            }
            arrayList20.add(a(str3));
            if (gVar.d == null || gVar.d.length <= 2) {
                arrayList2 = arrayList19;
                arrayList3 = arrayList20;
                arrayList4 = arrayList29;
                arrayList5 = arrayList;
                arrayList21.add(a(com.sigmob.sdk.common.Constants.FAIL));
                arrayList5.add(a(com.sigmob.sdk.common.Constants.FAIL));
                strA = a(com.sigmob.sdk.common.Constants.FAIL);
            } else {
                arrayList21.add(a(this.a.format(gVar.d[0])));
                arrayList5 = arrayList;
                arrayList5.add(a(this.a.format(gVar.d[1])));
                arrayList2 = arrayList19;
                arrayList3 = arrayList20;
                strA = a(this.a.format(gVar.d[2]));
                arrayList4 = arrayList29;
            }
            arrayList4.add(strA);
            if (gVar.f == null || gVar.f.length <= 2) {
                arrayList6 = arrayList4;
                arrayList7 = arrayList5;
                arrayList8 = arrayList25;
                arrayList9 = arrayList26;
                arrayList10 = arrayList28;
                arrayList10.add(a(com.sigmob.sdk.common.Constants.FAIL));
                arrayList8.add(a(com.sigmob.sdk.common.Constants.FAIL));
                strA2 = a(com.sigmob.sdk.common.Constants.FAIL);
            } else {
                arrayList6 = arrayList4;
                arrayList10 = arrayList28;
                arrayList10.add(a(this.a.format(gVar.f[0])));
                arrayList7 = arrayList5;
                arrayList8 = arrayList25;
                arrayList8.add(a(this.a.format(gVar.f[1])));
                strA2 = a(this.a.format(gVar.f[2]));
                arrayList9 = arrayList26;
            }
            arrayList9.add(strA2);
            if (gVar.e == null || gVar.e.length <= 0) {
                arrayList11 = arrayList27;
                strA3 = a(com.sigmob.sdk.common.Constants.FAIL);
            } else {
                strA3 = a(this.a.format(gVar.e[0]));
                arrayList11 = arrayList27;
            }
            arrayList11.add(strA3);
            i++;
            arrayList28 = arrayList10;
            arrayList25 = arrayList8;
            arrayList26 = arrayList9;
            arrayList27 = arrayList11;
            arrayList19 = arrayList2;
            arrayList20 = arrayList3;
            arrayList23 = arrayList6;
            arrayList22 = arrayList7;
            list2 = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<g> list, List<g> list2, String str, String str2) {
        PointEntitySensor pointEntitySensor = new PointEntitySensor();
        pointEntitySensor.setAc_type(PointType.ANTI_SPAM);
        pointEntitySensor.setCategory(str);
        pointEntitySensor.setMotion_before(a(list));
        pointEntitySensor.setMotion_after(a(list2));
        pointEntitySensor.setMotion_interval(String.valueOf(this.g));
        pointEntitySensor.setLoad_id(str2);
        pointEntitySensor.commit();
    }

    String a(String str) {
        return str;
    }

    public void a(Context context) {
        try {
            if (this.f != null) {
                if (SDKConfig.sharedInstance().enableAntiFraud()) {
                    return;
                }
                this.f.unregisterListener(this);
                b.removeCallbacksAndMessages(null);
                this.e.clear();
                this.f = null;
                return;
            }
            if (SDKConfig.sharedInstance().enableAntiFraud()) {
                SigmobLog.i("start anti spam motion");
                this.e = new ArrayList();
                this.h = SDKConfig.sharedInstance().getMotionCount();
                this.g = SDKConfig.sharedInstance().getMotionInterval();
                c = SDKConfig.sharedInstance().getMotionQueueMax();
                this.f = (SensorManager) context.getSystemService(o.Z);
                this.f.registerListener(this, this.f.getDefaultSensor(5), 3);
                SigmobLog.d("光线传感器 ");
                this.f.registerListener(this, this.f.getDefaultSensor(1), 3);
                SigmobLog.d("加速传感器 ");
                this.f.registerListener(this, this.f.getDefaultSensor(2), 3);
                SigmobLog.d("磁场传感器 ");
                this.f.registerListener(this, this.f.getDefaultSensor(4), 3);
                SigmobLog.d("陀螺仪 ");
                this.f.registerListener(this, this.f.getDefaultSensor(9), 3);
                SigmobLog.d("重力传感器 ");
                this.f.registerListener(this, this.f.getDefaultSensor(10), 3);
                SigmobLog.d("线性加速器 ");
                b = new Handler(Looper.getMainLooper()) { // from class: com.sigmob.sdk.base.services.h.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        if (message.what == f.SENSOR_EVENT_BEGIN.a()) {
                            i iVar = (i) message.obj;
                            int size = h.this.e.size() - 1;
                            if (size < 0) {
                                size = 0;
                            }
                            iVar.a = (g) h.this.e.get(size);
                            Message message2 = new Message();
                            message2.what = f.SENSOR_EVENT_END.a();
                            message2.obj = iVar;
                            h.b.sendMessageDelayed(message2, h.this.g * (h.this.h + 1));
                            return;
                        }
                        if (message.what != f.SENSOR_EVENT_END.a()) {
                            if (message.what == f.SENSOR_EVENT_PUSH.a()) {
                                try {
                                    h.this.d.g = System.currentTimeMillis();
                                    g gVarClone = h.this.d.clone();
                                    if (h.this.e.size() > h.c) {
                                        h.this.e.remove(0);
                                    }
                                    if (gVarClone != null) {
                                        h.this.e.add(gVarClone);
                                    }
                                } catch (CloneNotSupportedException e) {
                                    SigmobLog.e(e.getMessage());
                                }
                                Message message3 = new Message();
                                message3.what = f.SENSOR_EVENT_PUSH.a();
                                h.b.sendMessageDelayed(message3, h.this.g);
                                return;
                            }
                            return;
                        }
                        i iVar2 = (i) message.obj;
                        int iIndexOf = h.this.e.indexOf(iVar2.a);
                        int i = iIndexOf - h.this.h;
                        if (iIndexOf < 0 || iIndexOf < h.this.h) {
                            i = 0;
                        }
                        int size2 = h.this.h + iIndexOf;
                        if (size2 > h.this.e.size() - 1) {
                            size2 = h.this.e.size() - 1;
                        }
                        if (h.this.e.size() > 0) {
                            CopyOnWriteArrayList copyOnWriteArrayList = i < iIndexOf ? new CopyOnWriteArrayList(h.this.e.subList(i, iIndexOf)) : null;
                            CopyOnWriteArrayList copyOnWriteArrayList2 = iIndexOf < size2 ? new CopyOnWriteArrayList(h.this.e.subList(iIndexOf, size2)) : null;
                            if (copyOnWriteArrayList == null && copyOnWriteArrayList2 == null) {
                                return;
                            }
                            h.this.a(copyOnWriteArrayList, copyOnWriteArrayList2, iVar2.b, iVar2.c);
                        }
                    }
                };
                Message message = new Message();
                message.what = f.SENSOR_EVENT_PUSH.a();
                if (b != null) {
                    b.sendMessage(message);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void a(String str, String str2) {
        if (SDKConfig.sharedInstance().enableAntiFraud() && SDKConfig.sharedInstance().filterAntiEvent(str)) {
            a(com.sigmob.sdk.common.a.n().af());
            Message message = new Message();
            i iVar = new i();
            iVar.b = str;
            iVar.c = str2;
            message.what = f.SENSOR_EVENT_BEGIN.a();
            message.obj = iVar;
            if (b != null) {
                b.sendMessage(message);
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        SigmobLog.d(String.format("name :%s, accuracy %d", sensor.getName(), Integer.valueOf(i)));
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 1:
                this.d.d = (float[]) sensorEvent.values.clone();
                break;
            case 2:
                this.d.c = (float[]) sensorEvent.values.clone();
                break;
            case 4:
                this.d.b = (float[]) sensorEvent.values.clone();
                break;
            case 5:
                this.d.e = (float[]) sensorEvent.values.clone();
                break;
            case 9:
                this.d.a = (float[]) sensorEvent.values.clone();
                break;
            case 10:
                this.d.f = (float[]) sensorEvent.values.clone();
                break;
        }
    }
}
