package com.kwad.sdk.core.g;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private float a;
    private volatile boolean b = true;

    @Nullable
    private com.kwad.sdk.core.g.a c;

    @Nullable
    private a d;

    private class a implements SensorEventListener {
        private a() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            double dSqrt = Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
            if (!b.this.b || dSqrt < b.this.a || b.this.c == null) {
                return;
            }
            b.this.b = false;
            b.this.c.a(dSqrt);
        }
    }

    public b(float f) {
        this.a = f <= 0.0f ? 5.0f : f;
    }

    public synchronized void a() {
        this.b = true;
    }

    public void a(float f) {
        this.a = f;
    }

    public void a(Context context) {
        if (context == null) {
            com.kwad.sdk.core.d.a.a("ShakeDetector", "startDetect context is null");
            return;
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(10);
        if (defaultSensor == null) {
            com.kwad.sdk.core.d.a.a("ShakeDetector", "startDetect default linear acceleration is null");
            return;
        }
        if (this.d == null) {
            this.d = new a();
        }
        sensorManager.registerListener(this.d, defaultSensor, 2);
    }

    public void a(@Nullable com.kwad.sdk.core.g.a aVar) {
        this.c = aVar;
    }

    public void b(Context context) {
        if (context == null || this.d == null) {
            return;
        }
        ((SensorManager) context.getSystemService("sensor")).unregisterListener(this.d);
    }
}
