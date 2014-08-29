JNI_PATH := $(call my-dir)

LOCAL_PATH := $(JNI_PATH)/p7zip/CPP/

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/include_windows \
					$(LOCAL_PATH)/myWindows \
					$(LOCAL_PATH)/../C

LOCAL_MODULE := p7zip

LOCAL_CFLAGS := -D__linux__ \
				-D_FILE_OFFSET_BITS=64 \
				-D_LARGEFILE_SOURCE \
				-DNDEBUG \
				-D_REENTRANT \
				-DENV_UNIX \
				-DBREAK_HANDLER \
				-DUNICODE \
				-D_UNICODE \
				-fexceptions \
				-DDEBUG
				
LOCAL_SRC_FILES := \
		./myWindows/myGetTickCount.cpp \
        ./myWindows/mySplitCommandLine.cpp \
        ./myWindows/myAddExeFlag.cpp \
        ./myWindows/wine_date_and_time.cpp \
\
        ./7zip/UI/Console/BenchCon.cpp \
		./7zip/UI/Console/ConsoleClose.cpp \
		./7zip/UI/Console/ExtractCallbackConsole.cpp \
		./7zip/UI/Console/List.cpp \
		./7zip/UI/Console/Main.cpp \
		./7zip/UI/Console/MainAr.cpp \
		./7zip/UI/Console/OpenCallbackConsole.cpp \
		./7zip/UI/Console/PercentPrinter.cpp \
		./7zip/UI/Console/UpdateCallbackConsole.cpp \
		./7zip/UI/Console/UserInputUtils.cpp \
\
        ./Common/CommandLineParser.cpp \
        ./Common/CRC.cpp \
        ./Common/IntToString.cpp \
        ./Common/ListFileUtils.cpp \
        ./Common/StdInStream.cpp \
        ./Common/StdOutStream.cpp \
        ./Common/MyString.cpp \
        ./Common/MyWindows.cpp \
        ./Common/StringConvert.cpp \
        ./Common/StringToInt.cpp \
        ./Common/UTFConvert.cpp \
        ./Common/MyVector.cpp \
        ./Common/Wildcard.cpp \
        ./Common/MyXml.cpp \
\
        ./Windows/Error.cpp \
        ./Windows/FileDir.cpp \
        ./Windows/FileFind.cpp \
        ./Windows/FileIO.cpp \
        ./Windows/FileName.cpp \
        ./Windows/PropVariant.cpp \
        ./Windows/PropVariantConversions.cpp \
        ./Windows/PropVariantUtils.cpp \
        ./Windows/Synchronization.cpp \
        ./Windows/System.cpp \
        ./Windows/Time.cpp \
\
        ./7zip/Common/CreateCoder.cpp \
        ./7zip/Common/CWrappers.cpp \
        ./7zip/Common/FilePathAutoRename.cpp \
        ./7zip//Common/FileStreams.cpp \
        ./7zip/Common/FilterCoder.cpp \
        ./7zip/Common/InBuffer.cpp \
        ./7zip/Common/InOutTempBuffer.cpp \
        ./7zip/Common/LimitedStreams.cpp \
        ./7zip/Common/LockedStream.cpp \
        ./7zip/Common/MemBlocks.cpp \
        ./7zip/Common/MethodId.cpp \
        ./7zip/Common/MethodProps.cpp \
        ./7zip/Common/OffsetStream.cpp \
        ./7zip/Common/OutBuffer.cpp \
        ./7zip/Common/OutMemStream.cpp \
        ./7zip/Common/ProgressMt.cpp \
        ./7zip/Common/ProgressUtils.cpp \
        ./7zip/Common/StreamBinder.cpp \
        ./7zip/Common/StreamObjects.cpp \
        ./7zip/Common/StreamUtils.cpp \
        ./7zip/Common/VirtThread.cpp \
 \
        ./7zip/UI/Common/ArchiveCommandLine.cpp \
        ./7zip/UI/Common/ArchiveExtractCallback.cpp \
        ./7zip/UI/Common/ArchiveOpenCallback.cpp \
        ./7zip/UI/Common/Bench.cpp \
        ./7zip/UI/Common/DefaultName.cpp \
        ./7zip/UI/Common/EnumDirItems.cpp \
        ./7zip/UI/Common/Extract.cpp \
        ./7zip/UI/Common/ExtractingFilePath.cpp \
        ./7zip/UI/Common/LoadCodecs.cpp \
        ./7zip/UI/Common/OpenArchive.cpp \
        ./7zip/UI/Common/PropIDUtils.cpp \
        ./7zip/UI/Common/SetProperties.cpp \
        ./7zip/UI/Common/SortUtils.cpp \
        ./7zip/UI/Common/TempFiles.cpp \
        ./7zip/UI/Common/Update.cpp \
        ./7zip/UI/Common/UpdateAction.cpp \
        ./7zip/UI/Common/UpdateCallback.cpp \
        ./7zip/UI/Common/UpdatePair.cpp \
        ./7zip/UI/Common/UpdateProduce.cpp \
\
        ./7zip/Archive/Bz2Handler.cpp \
        ./7zip/Archive/DeflateProps.cpp \
        ./7zip/Archive/GzHandler.cpp \
        ./7zip/Archive/LzmaHandler.cpp \
        ./7zip/Archive/PpmdHandler.cpp \
        ./7zip/Archive/SplitHandler.cpp \
        ./7zip/Archive/XzHandler.cpp \
        ./7zip/Archive/ZHandler.cpp \
\
        ./7zip/Archive/Common/CoderMixer2.cpp \
        ./7zip/Archive/Common/CoderMixer2MT.cpp \
        ./7zip/Archive/Common/CrossThreadProgress.cpp \
        ./7zip/Archive/Common/DummyOutStream.cpp \
        ./7zip/Archive/Common/FindSignature.cpp \
        ./7zip/Archive/Common/HandlerOut.cpp \
        ./7zip/Archive/Common/InStreamWithCRC.cpp \
        ./7zip/Archive/Common/ItemNameUtils.cpp \
        ./7zip/Archive/Common/MultiStream.cpp \
        ./7zip/Archive/Common/OutStreamWithCRC.cpp \
        ./7zip/Archive/Common/OutStreamWithSha1.cpp \
        ./7zip/Archive/Common/ParseProperties.cpp \
\
        ./7zip/Archive/7z/7zCompressionMode.cpp \
        ./7zip/Archive/7z/7zDecode.cpp \
        ./7zip/Archive/7z/7zEncode.cpp \
        ./7zip/Archive/7z/7zExtract.cpp \
        ./7zip/Archive/7z/7zFolderInStream.cpp \
        ./7zip/Archive/7z/7zFolderOutStream.cpp \
        ./7zip/Archive/7z/7zHandler.cpp \
        ./7zip/Archive/7z/7zHandlerOut.cpp \
        ./7zip/Archive/7z/7zHeader.cpp \
        ./7zip/Archive/7z/7zIn.cpp \
        ./7zip/Archive/7z/7zOut.cpp \
        ./7zip/Archive/7z/7zProperties.cpp \
        ./7zip/Archive/7z/7zSpecStream.cpp \
        ./7zip/Archive/7z/7zUpdate.cpp \
        ./7zip/Archive/7z/7zRegister.cpp \
\
        ./7zip/Archive/Cab/CabBlockInStream.cpp \
        ./7zip/Archive/Cab/CabHandler.cpp \
        ./7zip/Archive/Cab/CabHeader.cpp \
        ./7zip/Archive/Cab/CabIn.cpp \
        ./7zip/Archive/Cab/CabRegister.cpp \
\
		./7zip/Archive/Chm/ChmHandler.cpp \
		./7zip/Archive/Chm/ChmHeader.cpp \
		./7zip/Archive/Chm/ChmIn.cpp \
		./7zip/Archive/Chm/ChmRegister.cpp \
\
		./7zip/Archive/Iso/IsoHandler.cpp \
		./7zip/Archive/Iso/IsoHeader.cpp \
		./7zip/Archive/Iso/IsoIn.cpp \
		./7zip/Archive/Iso/IsoRegister.cpp \
\
		./7zip/Archive/Rar/RarHandler.cpp \
		./7zip/Archive/Rar/RarHeader.cpp \
		./7zip/Archive/Rar/RarIn.cpp \
		./7zip/Archive/Rar/RarItem.cpp \
		./7zip/Archive/Rar/RarRegister.cpp \
		./7zip/Archive/Rar/RarVolumeInStream.cpp \
\
        ./7zip/Archive/Tar/TarHandler.cpp \
        ./7zip/Archive/Tar/TarHandlerOut.cpp \
        ./7zip/Archive/Tar/TarHeader.cpp \
        ./7zip/Archive/Tar/TarIn.cpp \
        ./7zip/Archive/Tar/TarOut.cpp \
        ./7zip/Archive/Tar/TarRegister.cpp \
        ./7zip/Archive/Tar/TarUpdate.cpp \
 \
		./7zip/Archive/Wim/WimHandler.cpp \
		./7zip/Archive/Wim/WimHandlerOut.cpp \
		./7zip/Archive/Wim/WimIn.cpp \
		./7zip/Archive/Wim/WimRegister.cpp \
\
        ./7zip/Archive/Zip/ZipAddCommon.cpp \
        ./7zip/Archive/Zip/ZipHandler.cpp \
        ./7zip/Archive/Zip/ZipHandlerOut.cpp \
        ./7zip/Archive/Zip/ZipHeader.cpp \
        ./7zip/Archive/Zip/ZipIn.cpp \
        ./7zip/Archive/Zip/ZipItem.cpp \
        ./7zip/Archive/Zip/ZipOut.cpp \
        ./7zip/Archive/Zip/ZipUpdate.cpp \
        ./7zip/Archive/Zip/ZipRegister.cpp \
\
        ./7zip/Compress/Bcj2Coder.cpp \
        ./7zip/Compress/Bcj2Register.cpp \
        ./7zip/Compress/BcjCoder.cpp \
        ./7zip/Compress/BcjRegister.cpp \
        ./7zip/Compress/BitlDecoder.cpp \
        ./7zip/Compress/BranchCoder.cpp \
        ./7zip/Compress/BranchMisc.cpp \
        ./7zip/Compress/BranchRegister.cpp \
        ./7zip/Compress/ByteSwap.cpp \
        ./7zip/Compress/BZip2Crc.cpp \
        ./7zip/Compress/BZip2Decoder.cpp \
        ./7zip/Compress/BZip2Encoder.cpp \
        ./7zip/Compress/BZip2Register.cpp \
        ./7zip/Compress/CopyCoder.cpp \
        ./7zip/Compress/CopyRegister.cpp \
        ./7zip/Compress/Deflate64Register.cpp \
        ./7zip/Compress/DeflateDecoder.cpp \
        ./7zip/Compress/DeflateEncoder.cpp \
        ./7zip/Compress/DeflateRegister.cpp \
        ./7zip/Compress/DeltaFilter.cpp \
        ./7zip/Compress/ImplodeDecoder.cpp \
        ./7zip/Compress/ImplodeHuffmanDecoder.cpp \
        ./7zip/Compress/Lzma2Decoder.cpp \
        ./7zip/Compress/Lzma2Encoder.cpp \
        ./7zip/Compress/Lzma2Register.cpp \
        ./7zip/Compress/LzmaDecoder.cpp \
        ./7zip/Compress/LzmaEncoder.cpp \
        ./7zip/Compress/LzmaRegister.cpp \
        ./7zip/Compress/LzOutWindow.cpp \
        ./7zip/Compress/Lzx86Converter.cpp \
        ./7zip/Compress/LzxDecoder.cpp \
        ./7zip/Compress/PpmdDecoder.cpp \
        ./7zip/Compress/PpmdEncoder.cpp \
        ./7zip/Compress/PpmdRegister.cpp \
        ./7zip/Compress/PpmdZip.cpp \
        ./7zip/Compress/QuantumDecoder.cpp \
        ./7zip/Compress/Rar1Decoder.cpp \
		./7zip/Compress/Rar2Decoder.cpp \
		./7zip/Compress/Rar3Decoder.cpp \
		./7zip/Compress/Rar3Vm.cpp \
		./7zip/Compress/RarCodecsRegister.cpp \
        ./7zip/Compress/ShrinkDecoder.cpp \
        ./7zip/Compress/ZDecoder.cpp \
 \
        ./7zip/Crypto/7zAes.cpp \
        ./7zip/Crypto/7zAesRegister.cpp \
        ./7zip/Crypto/HmacSha1.cpp \
        ./7zip/Crypto/MyAes.cpp \
        ./7zip/Crypto/Pbkdf2HmacSha1.cpp \
        ./7zip/Crypto/RandGen.cpp \
        ./7zip/Crypto/Sha1.cpp \
        ./7zip/Crypto/WzAes.cpp \
        ./7zip/Crypto/ZipCrypto.cpp \
        ./7zip/Crypto/ZipStrong.cpp  \
        ./7zip/Crypto/RarAes.cpp \
        ./7zip/Crypto/Rar20Crypto.cpp \
\
        ../C/Aes.c\
        ../C/7zStream.c \
		../C/7zCrc.c \
		../C/7zCrcOpt.c \
        ../C/Alloc.c \
        ../C/Bra.c \
        ../C/Bra86.c \
        ../C/BraIA64.c \
        ../C/BwtSort.c \
        ../C/Delta.c \
        ../C/HuffEnc.c \
        ../C/LzFind.c \
        ../C/LzFindMt.c \
        ../C/Lzma2Dec.c \
        ../C/Lzma2Enc.c \
        ../C/LzmaDec.c \
        ../C/LzmaEnc.c \
        ../C/MtCoder.c \
        ../C/Ppmd7.c \
        ../C/Ppmd7Dec.c \
        ../C/Ppmd7Enc.c \
        ../C/Ppmd8.c \
        ../C/Ppmd8Dec.c \
        ../C/Ppmd8Enc.c \
        ../C/Sha256.c \
        ../C/Sort.c \
        ../C/Threads.c \
        ../C/Xz.c \
        ../C/XzCrc64.c \
        ../C/XzDec.c \
        ../C/XzEnc.c \
        ../C/XzIn.c  \
\
        ../JniWrapper.cpp \
        ../com_hu_p7zip_ZipUtils.cpp
        
LOCAL_LDLIBS := -llog 

include $(BUILD_SHARED_LIBRARY)

