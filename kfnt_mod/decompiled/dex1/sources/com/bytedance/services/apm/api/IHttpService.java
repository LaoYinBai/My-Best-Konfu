package com.bytedance.services.apm.api;

import com.bytedance.news.common.service.manager.IService;
import java.io.File;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IHttpService extends IService {
    d doGet(String str, Map<String, String> map) throws Exception;

    d doPost(String str, byte[] bArr, Map<String, String> map) throws Exception;

    d uploadFiles(String str, List<File> list, Map<String, String> map) throws Exception;
}
