PROG=../../../../bin/Codecs/Rar29.so

LOCAL_FLAGS=$(CC_SHARED) -DUNICODE -D_UNICODE -DRegisterCodec=DllRegisterCodec

MY_WINDOWS=

include ../../../../makefile.crc32
include ../../../../makefile.machine

LOCAL_SHARED=$(LINK_SHARED)
LIBS=$(LOCAL_LIBS)

COMPRESS_OBJS = \
  CodecExports.o \
  DllExportsCompress.o \

RAR29_OPT_OBJS = \
  LzOutWindow.o \
  Rar1Decoder.o \
  Rar2Decoder.o \
  Rar3Decoder.o \
  Rar3Vm.o \
  RarCodecsRegister.o \

COMMON_OBJS = \
  CRC.o \
  MyVector.o \

P7ZIP_COMMON_OBJS = \
  InBuffer.o \
  OutBuffer.o \
  StreamUtils.o \

C_OBJS = \
  Alloc.o \
  CpuArch.o \

OBJS = \
MyWindows.o \
Ppmd7.o \
Ppmd7Dec.o \
$(COMPRESS_OBJS) \
$(RAR29_OPT_OBJS) \
$(COMMON_OBJS) \
$(P7ZIP_COMMON_OBJS) \
$(C_OBJS) \
7zCrc.o $(OBJ_CRC32) \

include ../../../../makefile.glb

