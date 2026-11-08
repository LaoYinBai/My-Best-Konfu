"""重打包: 原APK全部entry原样复制, 仅替换补丁后的 Assembly-CSharp.dll"""
import zipfile, shutil, os, sys

SRC_APK = r'D:\My-Best-Konfu\kfnt_apk\com.peakx.stickfight.and.apk'
PATCHED_DLL = r'D:\My-Best-Konfu\kfnt_mod\patched\Assembly-CSharp.dll'
OUT_APK = r'D:\My-Best-Konfu\kfnt_mod\kfnt_offline_unsigned.apk'
TARGET = 'assets/bin/Data/Managed/Assembly-CSharp.dll'

dll = open(PATCHED_DLL, 'rb').read()
print('补丁DLL大小:', len(dll))

zin = zipfile.ZipFile(SRC_APK, 'r')
zout = zipfile.ZipFile(OUT_APK, 'w', zipfile.ZIP_DEFLATED)
replaced = 0
for item in zin.infolist():
    if item.filename == TARGET:
        zout.writestr(item.filename, dll, compress_type=item.compress_type)
        replaced += 1
        print('已替换:', item.filename)
    else:
        zout.writestr(item, zin.read(item.filename))
zout.close()
zin.close()
assert replaced == 1, '替换条目数异常: %d' % replaced
print('完成:', OUT_APK, os.path.getsize(OUT_APK), 'bytes')
