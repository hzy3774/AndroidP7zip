JNI_PATH := $(call my-dir)
LOCAL_PATH := $(JNI_PATH)/p7zip_9.38.1

MY_C_PATH := $(LOCAL_PATH)/C
MY_CPP_PATH := $(LOCAL_PATH)/CPP
MY_ARCHIVE_PATH := $(MY_CPP_PATH)/7zip/Archive
MY_JNI_PATH := $(LOCAL_PATH)/jni

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := \
	$(MY_C_PATH) \
	$(MY_CPP_PATH) \
	$(MY_JNI_PATH) \
	$(MY_CPP_PATH)/myWindows \
	$(MY_CPP_PATH)/include_windows \

LOCAL_MODULE := p7zip

LOCAL_CFLAGS := \
	-DANDROID_NDK \
	-fexceptions \
	-DNDEBUG \
	-D_REENTRANT \
	-DENV_UNIX \
	-DEXTERNAL_CODECS \
	-DUNICODE \
	-D_UNICODE
				
LOCAL_SRC_FILES := \
	$(addprefix jni/, $(notdir $(wildcard $(MY_JNI_PATH)/*.cpp)))
	
LOCAL_SRC_FILES += \
	$(addprefix C/, $(notdir $(wildcard $(MY_C_PATH)/*.c))) \
	$(addprefix CPP/7zip/Archive/Common/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Common/*.cpp))) \
	$(addprefix CPP/7zip/Archive/7z/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/7z/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Cab/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Cab/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Chm/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Chm/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Iso/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Iso/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Nsis/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Nsis/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Rar/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Rar/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Tar/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Tar/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Udf/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Udf/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Wim/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Wim/*.cpp))) \
	$(addprefix CPP/7zip/Archive/Zip/, $(notdir $(wildcard $(MY_ARCHIVE_PATH)/Zip/*.cpp))) 


	
LOCAL_SRC_FILES += \
	$(addprefix CPP/7zip/Common/, $(notdir $(wildcard $(MY_CPP_PATH)/7zip/Common/*.cpp))) \
	$(addprefix CPP/7zip/Crypto/, $(notdir $(wildcard $(MY_CPP_PATH)/7zip/Crypto/*.cpp))) \
	$(addprefix CPP/7zip/UI/Console/, $(notdir $(wildcard $(MY_CPP_PATH)/7zip/UI/Console/*.cpp))) \
	$(addprefix CPP/Common/, $(notdir $(wildcard $(MY_CPP_PATH)/Common/*.cpp))) \
	
LOCAL_SRC_FILES += \
	CPP/7zip/Archive/ApmHandler.cpp \
	CPP/7zip/Archive/ArHandler.cpp \
	CPP/7zip/Archive/ArjHandler.cpp \
	CPP/7zip/Archive/Bz2Handler.cpp \
	CPP/7zip/Archive/ComHandler.cpp \
	CPP/7zip/Archive/CpioHandler.cpp \
	CPP/7zip/Archive/CramfsHandler.cpp \
	CPP/7zip/Archive/DeflateProps.cpp \
	CPP/7zip/Archive/DmgHandler.cpp \
	CPP/7zip/Archive/ElfHandler.cpp \
	CPP/7zip/Archive/FatHandler.cpp \
	CPP/7zip/Archive/FlvHandler.cpp \
	CPP/7zip/Archive/GzHandler.cpp \
	CPP/7zip/Archive/HfsHandler.cpp \
	CPP/7zip/Archive/IhexHandler.cpp \
	CPP/7zip/Archive/LzhHandler.cpp \
	CPP/7zip/Archive/LzmaHandler.cpp \
	CPP/7zip/Archive/MachoHandler.cpp \
	CPP/7zip/Archive/MbrHandler.cpp \
	CPP/7zip/Archive/MslzHandler.cpp \
	CPP/7zip/Archive/MubHandler.cpp \
	CPP/7zip/Archive/NtfsHandler.cpp \
	CPP/7zip/Archive/PeHandler.cpp \
	CPP/7zip/Archive/PpmdHandler.cpp \
	CPP/7zip/Archive/RpmHandler.cpp \
	CPP/7zip/Archive/SplitHandler.cpp \
	CPP/7zip/Archive/SquashfsHandler.cpp \
	CPP/7zip/Archive/SwfHandler.cpp \
	CPP/7zip/Archive/UefiHandler.cpp \
	CPP/7zip/Archive/VhdHandler.cpp \
	CPP/7zip/Archive/XarHandler.cpp \
	CPP/7zip/Archive/XzHandler.cpp \
	CPP/7zip/Archive/ZHandler.cpp	

LOCAL_SRC_FILES += \
	CPP/7zip/Compress/ArjDecoder1.cpp \
	CPP/7zip/Compress/ArjDecoder2.cpp \
	CPP/7zip/Compress/Bcj2Coder.cpp \
	CPP/7zip/Compress/Bcj2Register.cpp \
	CPP/7zip/Compress/BcjCoder.cpp \
	CPP/7zip/Compress/BcjRegister.cpp \
	CPP/7zip/Compress/BitlDecoder.cpp \
	CPP/7zip/Compress/BranchCoder.cpp \
	CPP/7zip/Compress/BranchMisc.cpp \
	CPP/7zip/Compress/BranchRegister.cpp \
	CPP/7zip/Compress/ByteSwap.cpp \
	CPP/7zip/Compress/BZip2Crc.cpp \
	CPP/7zip/Compress/BZip2Decoder.cpp \
	CPP/7zip/Compress/BZip2Encoder.cpp \
	CPP/7zip/Compress/BZip2Register.cpp \
	CPP/7zip/Compress/CopyCoder.cpp \
	CPP/7zip/Compress/CopyRegister.cpp \
	CPP/7zip/Compress/Deflate64Register.cpp \
	CPP/7zip/Compress/DeflateDecoder.cpp \
	CPP/7zip/Compress/DeflateEncoder.cpp \
	CPP/7zip/Compress/DeflateRegister.cpp \
	CPP/7zip/Compress/DeltaFilter.cpp \
	CPP/7zip/Compress/ImplodeDecoder.cpp \
	CPP/7zip/Compress/ImplodeHuffmanDecoder.cpp \
	CPP/7zip/Compress/LzhDecoder.cpp \
	CPP/7zip/Compress/Lzma2Decoder.cpp \
	CPP/7zip/Compress/Lzma2Encoder.cpp \
	CPP/7zip/Compress/Lzma2Register.cpp \
	CPP/7zip/Compress/LzmaDecoder.cpp \
	CPP/7zip/Compress/LzmaEncoder.cpp \
	CPP/7zip/Compress/LzmaRegister.cpp \
	CPP/7zip/Compress/LzOutWindow.cpp \
	CPP/7zip/Compress/Lzx86Converter.cpp \
	CPP/7zip/Compress/LzxDecoder.cpp \
	CPP/7zip/Compress/PpmdDecoder.cpp \
	CPP/7zip/Compress/PpmdEncoder.cpp \
	CPP/7zip/Compress/PpmdRegister.cpp \
	CPP/7zip/Compress/PpmdZip.cpp \
	CPP/7zip/Compress/QuantumDecoder.cpp \
	CPP/7zip/Compress/Rar1Decoder.cpp \
	CPP/7zip/Compress/Rar2Decoder.cpp \
	CPP/7zip/Compress/Rar3Decoder.cpp \
	CPP/7zip/Compress/Rar3Vm.cpp \
	CPP/7zip/Compress/RarCodecsRegister.cpp \
	CPP/7zip/Compress/ShrinkDecoder.cpp \
	CPP/7zip/Compress/ZDecoder.cpp \
	CPP/7zip/Compress/ZlibDecoder.cpp \
	CPP/7zip/Compress/ZlibEncoder.cpp \
	
LOCAL_SRC_FILES += \
	CPP/7zip/UI/Common/ArchiveCommandLine.cpp \
	CPP/7zip/UI/Common/ArchiveExtractCallback.cpp \
	CPP/7zip/UI/Common/ArchiveOpenCallback.cpp \
	CPP/7zip/UI/Common/Bench.cpp \
	CPP/7zip/UI/Common/DefaultName.cpp \
	CPP/7zip/UI/Common/EnumDirItems.cpp \
	CPP/7zip/UI/Common/Extract.cpp \
	CPP/7zip/UI/Common/ExtractingFilePath.cpp \
	CPP/7zip/UI/Common/HashCalc.cpp \
	CPP/7zip/UI/Common/LoadCodecs.cpp \
	CPP/7zip/UI/Common/OpenArchive.cpp \
	CPP/7zip/UI/Common/PropIDUtils.cpp \
	CPP/7zip/UI/Common/SetProperties.cpp \
	CPP/7zip/UI/Common/SortUtils.cpp \
	CPP/7zip/UI/Common/TempFiles.cpp \
	CPP/7zip/UI/Common/Update.cpp \
	CPP/7zip/UI/Common/UpdateAction.cpp \
	CPP/7zip/UI/Common/UpdateCallback.cpp \
	CPP/7zip/UI/Common/UpdatePair.cpp \
	CPP/7zip/UI/Common/UpdateProduce.cpp \

LOCAL_SRC_FILES += \
	CPP/Windows/COM.cpp \
	CPP/Windows/DLL.cpp \
	CPP/Windows/ErrorMsg.cpp \
	CPP/Windows/FileDir.cpp \
	CPP/Windows/FileFind.cpp \
	CPP/Windows/FileIO.cpp \
	CPP/Windows/FileName.cpp \
	CPP/Windows/PropVariantConv.cpp \
	CPP/Windows/PropVariant.cpp \
	CPP/Windows/PropVariantUtils.cpp \
	CPP/Windows/Synchronization.cpp \
	CPP/Windows/System.cpp \
	CPP/Windows/TimeUtils.cpp \
	
LOCAL_SRC_FILES += \
	CPP/myWindows/myAddExeFlag.cpp \
	CPP/myWindows/mySplitCommandLine.cpp \
	CPP/myWindows/wine_date_and_time.cpp

LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)

