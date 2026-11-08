"""对原始加密DLL做body内IL原位补丁 (密文头不动)
补丁A: UserBaseDataHandler.AccountObject_LoginFailedEvent
       bne.un.s -> br.s : 登录失败不再无限重试guestLogin, 走本地register链
补丁B: LoadingNode.InitSDK
       brfalse.s -> nop nop : SDK初始化未完成也无条件StepComplete
"""
import sys, shutil

SRC = r'D:\My-Best-Konfu\kfnt_mod\extracted\assets\bin\Data\Managed\Assembly-CSharp.dll'
DST = r'D:\My-Best-Konfu\kfnt_mod\patched\Assembly-CSharp.dll'

# RVA -> 文件偏移 (.text: RVA 0x2000 = file 0x200)
def rva2off(rva):
    return rva - 0x2000 + 0x200

PATCHES = [
    {
        'name': 'A: AccountObject_LoginFailedEvent 分支翻转',
        'rva': 0xEE92A,
        'il_off': 0x08,           # bne.un.s 操作码的 body 相对偏移
        'expect': bytes([0x33, 0x0C]),    # bne.un.s +0x0C (字节级实测)
        'replace': bytes([0x2B, 0x0C]),   # br.s 同目标
    },
    {
        'name': 'B: LoadingNode.InitSDK 门控NOP',
        'rva': 0x14EE68,          # LoadingNode.InitSDK 实测RVA
        'il_off': 0xC3,
        'expect': bytes([0x2C]),  # brfalse.s, 操作数任意
        'replace': bytes([0x00, 0x00]),
    },
]

def main():
    import struct
    shutil.copyfile(SRC, DST)
    data = bytearray(open(DST, 'rb').read())
    for p in PATCHES:
        base = rva2off(p['rva'])
        off = base + p['il_off']
        cur = bytes(data[off:off + len(p['expect'])])
        ok = cur == p['expect'] or (p['name'].startswith('B') and cur[:1] == p['expect'])
        print('%s\n  base=0x%x off=0x%x 期望=%s 实际=%s  %s'
              % (p['name'], base, off, p['expect'].hex(), cur.hex(), 'OK' if ok else 'MISMATCH'))
        if not ok:
            sys.exit('补丁点校验失败, 中止 (不写入)')
        data[off:off + len(p['replace'])] = p['replace']
        print('  已写入: %s' % p['replace'].hex())
    open(DST, 'wb').write(bytes(data))
    print('补丁完成 ->', DST)

main()
