package anet.channel.appmonitor;

import android.text.TextUtils;
import anet.channel.statist.AlarmObject;
import anet.channel.statist.CountObject;
import anet.channel.statist.Dimension;
import anet.channel.statist.Measure;
import anet.channel.statist.Monitor;
import anet.channel.statist.StatObject;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import anetwork.channel.b.b;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements IAppMonitor {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f397a = false;

    /* renamed from: b, reason: collision with root package name */
    private static Map<Class<?>, List<Field>> f398b = new ConcurrentHashMap();

    /* renamed from: c, reason: collision with root package name */
    private static Map<Class<?>, List<Field>> f399c = new ConcurrentHashMap();
    private static Map<Field, String> d = new ConcurrentHashMap();
    private static Random e = new Random();
    private static Set<Class<?>> f = Collections.newSetFromMap(new ConcurrentHashMap());

    @Override // anet.channel.appmonitor.IAppMonitor
    @Deprecated
    public void register() {
    }

    @Override // anet.channel.appmonitor.IAppMonitor
    @Deprecated
    public void register(Class<?> cls) {
    }

    public a() throws ClassNotFoundException {
        try {
            Class.forName("com.alibaba.mtl.appmonitor.AppMonitor");
            f397a = true;
        } catch (Exception unused) {
            f397a = false;
        }
    }

    @Override // anet.channel.appmonitor.IAppMonitor
    public void commitStat(StatObject statObject) {
        if (!f397a || statObject == null) {
            return;
        }
        Class<?> cls = statObject.getClass();
        Monitor monitor = (Monitor) cls.getAnnotation(Monitor.class);
        if (monitor == null) {
            return;
        }
        if (!f.contains(cls)) {
            a(cls);
        }
        if (statObject.beforeCommit()) {
            if (monitor.monitorPoint().equals("network")) {
                int i = b.i();
                if (i > 10000 || i < 0) {
                    i = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
                }
                if (i != 10000 && e.nextInt(NetCallback.ERROR_CODE_REQUEST_SUCCESS) >= i) {
                    return;
                }
            }
            try {
                DimensionValueSet dimensionValueSetCreate = DimensionValueSet.create();
                MeasureValueSet measureValueSetCreate = MeasureValueSet.create();
                List<Field> list = f398b.get(cls);
                HashMap map = ALog.isPrintLog(1) ? new HashMap() : null;
                if (list != null) {
                    for (Field field : list) {
                        Object obj = field.get(statObject);
                        dimensionValueSetCreate.setValue(d.get(field), obj == null ? "" : obj.toString());
                    }
                    for (Field field2 : f399c.get(cls)) {
                        Double dValueOf = Double.valueOf(field2.getDouble(statObject));
                        measureValueSetCreate.setValue(d.get(field2), dValueOf.doubleValue());
                        if (map != null) {
                            map.put(d.get(field2), dValueOf);
                        }
                    }
                }
                AppMonitor.Stat.commit(monitor.module(), monitor.monitorPoint(), dimensionValueSetCreate, measureValueSetCreate);
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.DefaultAppMonitor", "commit stat: " + monitor.monitorPoint(), null, "\nDimensions", dimensionValueSetCreate.getMap().toString(), "\nMeasures", map.toString());
                }
            } catch (Throwable th) {
                ALog.e("awcn.DefaultAppMonitor", "commit monitor point failed", null, th, new Object[0]);
            }
        }
    }

    @Override // anet.channel.appmonitor.IAppMonitor
    public void commitAlarm(AlarmObject alarmObject) {
        if (!f397a || alarmObject == null || TextUtils.isEmpty(alarmObject.module) || TextUtils.isEmpty(alarmObject.modulePoint)) {
            return;
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.DefaultAppMonitor", "commit alarm: " + alarmObject, null, new Object[0]);
        }
        if (!alarmObject.isSuccess) {
            AppMonitor.Alarm.commitFail(alarmObject.module, alarmObject.modulePoint, StringUtils.stringNull2Empty(alarmObject.arg), StringUtils.stringNull2Empty(alarmObject.errorCode), StringUtils.stringNull2Empty(alarmObject.errorMsg));
        } else {
            AppMonitor.Alarm.commitSuccess(alarmObject.module, alarmObject.modulePoint, StringUtils.stringNull2Empty(alarmObject.arg));
        }
    }

    @Override // anet.channel.appmonitor.IAppMonitor
    public void commitCount(CountObject countObject) {
        if (!f397a || countObject == null || TextUtils.isEmpty(countObject.module) || TextUtils.isEmpty(countObject.modulePoint)) {
            return;
        }
        if (ALog.isPrintLog(2)) {
            ALog.i("awcn.DefaultAppMonitor", "commit count: " + countObject, null, new Object[0]);
        }
        AppMonitor.Counter.commit(countObject.module, countObject.modulePoint, StringUtils.stringNull2Empty(countObject.arg), countObject.value);
    }

    synchronized void a(Class<?> cls) {
        if (cls != null) {
            if (f397a) {
                try {
                } catch (Exception e2) {
                    ALog.e("awcn.DefaultAppMonitor", "register fail", null, e2, new Object[0]);
                }
                if (f.contains(cls)) {
                    return;
                }
                Monitor monitor = (Monitor) cls.getAnnotation(Monitor.class);
                if (monitor == null) {
                    return;
                }
                Field[] fields = cls.getFields();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                DimensionSet dimensionSetCreate = DimensionSet.create();
                MeasureSet measureSetCreate = MeasureSet.create();
                for (Field field : fields) {
                    Dimension dimension = (Dimension) field.getAnnotation(Dimension.class);
                    if (dimension != null) {
                        field.setAccessible(true);
                        arrayList.add(field);
                        String name = dimension.name().equals("") ? field.getName() : dimension.name();
                        d.put(field, name);
                        dimensionSetCreate.addDimension(name);
                    } else {
                        Measure measure = (Measure) field.getAnnotation(Measure.class);
                        if (measure != null) {
                            field.setAccessible(true);
                            arrayList2.add(field);
                            String name2 = measure.name().equals("") ? field.getName() : measure.name();
                            d.put(field, name2);
                            if (measure.max() != Double.MAX_VALUE) {
                                measureSetCreate.addMeasure(new com.alibaba.mtl.appmonitor.model.Measure(name2, Double.valueOf(measure.constantValue()), Double.valueOf(measure.min()), Double.valueOf(measure.max())));
                            } else {
                                measureSetCreate.addMeasure(name2);
                            }
                        }
                    }
                }
                f398b.put(cls, arrayList);
                f399c.put(cls, arrayList2);
                com.alibaba.mtl.appmonitor.AppMonitor.register(monitor.module(), monitor.monitorPoint(), measureSetCreate, dimensionSetCreate);
                f.add(cls);
            }
        }
    }
}
