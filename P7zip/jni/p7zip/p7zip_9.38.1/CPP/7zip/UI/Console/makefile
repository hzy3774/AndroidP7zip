PROG=../../../../bin/7z$(BINSUFFIX)

LOCAL_FLAGS=\
  -DEXTERNAL_CODECS \
  -DBREAK_HANDLER \
  -DUNICODE -D_UNICODE

include ../../../../makefile.crc32
include ../../../../makefile.machine

PCH_NAME=$(PRE_COMPILED_HEADER)

LIBS=$(LOCAL_LIBS_DLL)

include ../../../../makefile.glb

