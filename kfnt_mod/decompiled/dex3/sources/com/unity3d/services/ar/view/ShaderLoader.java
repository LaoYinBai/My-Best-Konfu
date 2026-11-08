package com.unity3d.services.ar.view;

import android.opengl.GLES20;
import com.unity3d.services.core.log.DeviceLog;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ShaderLoader {
    public static int load(String str, int i) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int iGlCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] == 1) {
            return iGlCreateShader;
        }
        DeviceLog.error("Error compiling shader: " + GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    public static boolean checkGLError(String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int i = 0;
        while (true) {
            int iGlGetError = GLES20.glGetError();
            if (iGlGetError == 0) {
                break;
            }
            DeviceLog.error(str + ": glError " + iGlGetError);
            i = iGlGetError;
        }
        return i != 0;
    }
}
