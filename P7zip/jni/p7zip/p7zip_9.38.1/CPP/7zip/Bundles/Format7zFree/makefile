PROG=../../../../bin/7z.so

LOCAL_FLAGS=$(CC_SHARED) -DEXTERNAL_CODECS -DUNICODE -D_UNICODE \
	-DRegisterArc=DllRegisterArc -DRegisterCodec=DllRegisterCodec

include ../../../../makefile.crc32
include ../../../../makefile.machine

PCH_NAME=$(PRE_COMPILED_HEADER)

LOCAL_SHARED=$(LINK_SHARED)
LIBS=$(LOCAL_LIBS_DLL)

include ../../../../makefile.glb

