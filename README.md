# My-Best-Konfu — 《我功夫特牛》单机化改造项目

对已停服手游《我功夫特牛》（Ohayoo/PeakX，v0.17.8）做逆向分析与纯单机化改造的个人保存项目。
游戏本体已随 Ohayoo 业务收缩停运下架，本项目目标是在本地把最后一个版本改造成可离线运行的自用存档。

> ⚠️ **版权声明**：游戏 APK、解包资源、反编译产物均**不入本仓库**（见 `.gitignore`）。
> 本仓库只包含自制工具脚本与分析文档，供个人学习/游戏保存用途。

## 项目结构

```
My-Best-Konfu/
├── README.md              本文件
├── 进度存档.md            实时进度快照（密钥/路径/下一步，断点续作用）
├── docs/
│   ├── 开发日志.md        按时间的完整开发记录
│   └── 闪退分析.md        当前 bug 的定位分析与排查清单
└── scripts/               自制工具链（Python，无第三方依赖框架）
    ├── build_analysis_pe.py   给"头加密"DLL重建可解析PE头（分析用）
    ├── il_dump.py             按类型/方法反汇编 CIL，解析字符串/成员引用
    ├── il_xref.py             MemberRef/MethodDef token 原始字节交叉引用
    ├── scan_types.py          类型名关键词扫描 + 元数据流抽取
    ├── dump_members.py        关键类成员地图
    ├── patch_dll.py           IL 原位补丁器（带字节级校验）
    ├── repack_apk.py          重打包（原APK替换单一DLL）
    ├── parse_axml.py          二进制 AndroidManifest 解析
    └── batch_decompile.sh     ILSpy 批量反编译脚本
```

## 核心技术结论（详见 docs/）

1. **目标版本**：v0.17.8 (versionCode 552)，Google Play 渠道版，`com.peakx.stickfight.and`，armeabi-v7a 单架构
2. **引擎**：Unity 2018.4.36f1 + **Mono 后端**（游戏逻辑是标准 .NET IL，可原位补丁）
3. **加固方式**：仅 `Assembly-CSharp.dll` / `Assembly-CSharp-firstpass.dll` **前 0x200 字节（PE头）被固定密钥 XOR 加密**，body 全明文，双文件同密钥
4. **绕过策略**：所有修改只做 **body 内 IL 原位编辑**，密文头一个字节不动 → mono 运行时照常解密加载，无需破解密钥

## 工作流

```
原版APK → 解包 → build_analysis_pe.py(重建分析头) → ILSpy/dnfile 分析
        → patch_dll.py(IL原位补丁, 密文头保留) → repack_apk.py(替换DLL)
        → uber-apk-signer 重签名 → 真机/模拟器测试
```

## 环境依赖

- Python 3.x（+ dnfile/pefile/dncil，纯 Python 库）
- ILSpy CLI / dnSpyEx（C# 反编译）
- JDK 17+、apktool、uber-apk-signer（打包签名）
- adb（真机/模拟器测试）

以上工具均不入库，路径与密钥等环境信息见 `进度存档.md`。

## 当前状态

v1 单机版已产出并实测：**加载页闪退**，定位排查进行中 → 见 `docs/闪退分析.md`。
