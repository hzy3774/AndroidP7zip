
PROG=../../../../bin/LzmaCon$(BINSUFFIX)

LOCAL_FLAGS= \
  -DUNICODE -D_UNICODE

include ../../../../makefile.crc32
include ../../../../makefile.machine

PCH_NAME=$(PRE_COMPILED_HEADER)

LIBS=$(LOCAL_LIBS)

include ../../../../makefile.glb

test: $(PROG)
	cp LzmaAlone.cpp testfile
	./$(PROG) e testfile testfile.lzma
	./$(PROG) d testfile.lzma testfile.lzma.d 
	diff -s testfile testfile.lzma.d
	./$(PROG) e testfile testfile.lzma
	./$(PROG) d testfile.lzma testfile.lzma.d 
	diff -s testfile testfile.lzma.d
	./$(PROG) e -si -so < testfile > testfile.lzma.2
	./$(PROG) d -si -so < testfile.lzma.2 > testfile.lzma.2.d 
	diff -s testfile testfile.lzma.2.d
	@echo =========
	@echo All Done 
	@echo =========


clean2: clean
	rm -f testfile*

