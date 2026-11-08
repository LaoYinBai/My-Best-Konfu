"""构建分析用 PE: 给主体明文的被加密DLL重建可解析的PE头 (仅分析用途, 不回写游戏)"""
import struct, sys

def build(src_path, out_path):
    data = open(src_path, 'rb').read()
    body = data[0x200:]  # 明文主体 (含CLI头@0x208)

    # 解析CLI头 (body内偏移8, 即文件0x208)
    cli = body[8:8+0x48]
    cb, major_rt, minor_rt = struct.unpack_from('<IHH', cli, 0)
    meta_rva, meta_size = struct.unpack_from('<II', cli, 8)
    cli_flags, entry_token = struct.unpack_from('<II', cli, 16)
    sn_rva, sn_size = struct.unpack_from('<II', cli, 32)
    vtf_rva, vtf_size = struct.unpack_from('<II', cli, 40)
    res_rva, res_size = struct.unpack_from('<II', cli, 24)
    print('CLI: metaRVA=0x%x size=0x%x flags=0x%x entrytoken=0x%x sn=(0x%x,0x%x) res=(0x%x,0x%x)'
          % (meta_rva, meta_size, cli_flags, entry_token, sn_rva, sn_size, res_rva, res_size))

    # 元数据在文件内位置 -> 验证
    meta_file_off = 0x200 + (meta_rva - 0x2000)
    assert data[meta_file_off:meta_file_off+4] == b'BSJB', '元数据定位失败'
    print('元数据文件偏移 0x%x 验证通过' % meta_file_off)

    text_raw_size = len(body)              # .text 段吃掉剩余全部
    text_va_size = meta_rva - 0x2000 + meta_size  # 到元数据结束
    size_of_image = 0x2000 + ((text_va_size + 0x1FFF) & ~0x1FFF)

    # --- DOS header ---
    dos = bytearray(0x80)
    dos[0:2] = b'MZ'
    struct.pack_into('<I', dos, 0x3C, 0x80)  # e_lfanew
    # PE sig + COFF
    pe = struct.pack('<IHHIIIHH', 0x00004550, 0x014C, 1, 0, 0, 0, 0xE0, 0x2102)
    # Optional header PE32
    opt = bytearray(0xE0)
    struct.pack_into('<H', opt, 0, 0x010B)          # PE32
    struct.pack_into('<I', opt, 4, text_raw_size)   # SizeOfCode (约)
    struct.pack_into('<I', opt, 0x10, text_va_size) # BaseOfCode? 见下
    # PE32布局: magic(0) MJL(2) mnl(3) SizeOfCode(4) SizeOfInitData(8) SizeOfUninit(0xC)
    #           EntryRVA(0x10) BaseOfCode(0x14) BaseOfData(0x18) ImageBase(0x1C) ...
    struct.pack_into('<I', opt, 0x10, 0x2008)       # AddressOfEntryPoint -> CLI头
    struct.pack_into('<I', opt, 0x14, 0x2000)       # BaseOfCode
    struct.pack_into('<I', opt, 0x18, 0x2000)       # BaseOfData
    struct.pack_into('<I', opt, 0x1C, 0x400000)     # ImageBase
    struct.pack_into('<I', opt, 0x20, 0x2000)       # SectionAlignment
    struct.pack_into('<I', opt, 0x24, 0x200)        # FileAlignment
    struct.pack_into('<HH', opt, 0x28, 4, 0)        # OSVer 4.0
    struct.pack_into('<HH', opt, 0x2C, 0, 0)        # ImageVer
    struct.pack_into('<HH', opt, 0x30, 4, 0)        # SubSysVer
    struct.pack_into('<I', opt, 0x34, 0)            # Win32Ver
    struct.pack_into('<I', opt, 0x38, size_of_image)
    struct.pack_into('<I', opt, 0x3C, 0x200)        # SizeOfHeaders
    struct.pack_into('<I', opt, 0x40, 0)            # Checksum
    struct.pack_into('<H', opt, 0x44, 3)            # Subsystem CUI
    struct.pack_into('<H', opt, 0x46, 0x2100)       # DllChar
    struct.pack_into('<I', opt, 0x48, 0x100000)     # StackReserve
    struct.pack_into('<I', opt, 0x4C, 0x1000)
    struct.pack_into('<I', opt, 0x50, 0x100000)     # HeapReserve
    struct.pack_into('<I', opt, 0x54, 0x1000)
    struct.pack_into('<I', opt, 0x58, 0)            # LoaderFlags
    struct.pack_into('<I', opt, 0x5C, 16)           # NumberOfRvaAndSizes
    # DataDirectory[14] CLR (目录区从0x60起)
    struct.pack_into('<II', opt, 0x5C + 4 + 14*8, 0x2008, 0x48)

    # --- 节表: 单一 .text ---
    sec = bytearray(40)
    sec[0:8] = b'.text\x00\x00\x00'
    struct.pack_into('<I', sec, 8, text_va_size)   # VirtualSize
    struct.pack_into('<I', sec, 12, 0x2000)        # VirtualAddress
    struct.pack_into('<I', sec, 16, text_raw_size) # SizeOfRawData
    struct.pack_into('<I', sec, 20, 0x200)         # PointerToRawData
    struct.pack_into('<I', sec, 36, 0x60000020)    # code+exec+read

    header = bytes(dos) + pe + bytes(opt) + bytes(sec)
    header = header + b'\x00' * (0x200 - len(header))  # 对齐到 SizeOfHeaders=0x200
    out = header + body
    open(out_path, 'wb').write(out)
    print('写出: %s (%d 字节)' % (out_path, len(out)))

if __name__ == '__main__':
    build(sys.argv[1], sys.argv[2])
