PROG=../../../../bin/7zFM$(BINSUFFIX)

LOCAL_FLAGS=\
	-DUNICODE -D_UNICODE \
	-DLANG \
	-DNEW_FOLDER_INTERFACE \
	-DEXTERNAL_CODECS \
	-I. \
	`wx-config --unicode=yes --cxxflags`

include ../../../../makefile.crc32
include ../../../../makefile.machine

PCH_NAME=$(PRE_COMPILED_HEADER)

LIBS=`wx-config --unicode=yes --libs` $(LOCAL_LIBS_DLL)

include ../../../../makefile.glb

