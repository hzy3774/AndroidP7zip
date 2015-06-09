# 
# build test_emul for armeabi and armeabi-v7a CPU
#


LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := test_emul
LOCAL_CFLAGS := -DANDROID_NDK  -fexceptions \
		-DNDEBUG -D_REENTRANT -DENV_UNIX \
  -DUNICODE \
  -D_UNICODE \
	-I../../../Windows \
	-I../../../Common \
	-I../../../../C \
-I../../../myWindows \
-I../../../ \
-I../../../include_windows


LOCAL_SRC_FILES := \
 ../../../myWindows/wine_date_and_time.cpp \
  ../../../myWindows/wine_GetXXXDefaultLangID.cpp \
 ../../../myWindows/myAddExeFlag.cpp \
 ../../../myWindows/mySplitCommandLine.cpp \
 ../../../myWindows/test_emul.cpp \
  ../../../Common/MyString.cpp \
 ../../../Common/MyWindows.cpp \
 ../../../Common/MyVector.cpp \

include $(BUILD_EXECUTABLE)
