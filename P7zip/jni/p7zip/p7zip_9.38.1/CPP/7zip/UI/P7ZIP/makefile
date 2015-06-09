PROG=../../../../bin/p7zip$(BINSUFFIX)

LOCAL_FLAGS=\
	-DUNICODE -D_UNICODE \
	-I. \
	`wx-config --unicode=yes --cxxflags`

include ../../../../makefile.crc32
include ../../../../makefile.machine

PCH_NAME=$(PRE_COMPILED_HEADER)

LIBS=`wx-config --unicode=yes --libs` $(LOCAL_LIBS_DLL)

include ../../../../makefile.glb

