
QT += core gui

TARGET = util7zip

TEMPLATE = lib

CONFIG = staticlib
SOURCES += util7zip.cpp \
../../../myWindows/wine_date_and_time.cpp \
        ../../../Common/MyString.cpp \
        ../../../Common/MyVector.cpp \
        ../../../Common/IntToString.cpp \
        ../../../Common/NewHandler.cpp \
        ../../../Common/StringConvert.cpp \
        ../../../Common/StringToInt.cpp \
        ../../../Common/UTFConvert.cpp \
     ../../../Common/Wildcard.cpp \
 ../../../Common/MyWindows.cpp \
 ../../../Windows/ErrorMsg.cpp \
 ../../../Windows/FileDir.cpp \
 ../../../Windows/FileFind.cpp \
 ../../../Windows/FileIO.cpp \
 ../../../Windows/FileName.cpp \
 ../../../Windows/PropVariant.cpp \
../../UI/FileManager/RootFolder.cpp \
../../UI/FileManager/FSDrives.cpp \
../../UI/FileManager/FSFolder.cpp \
../../UI/FileManager/FSFolderCopy.cpp \
../../UI/FileManager/TextPairs.cpp \
../../UI/FileManager/ClassDefs.cpp \
 ../../../../C/Alloc.c \
 ../../../../C/Threads.c \



INCLUDEPATH += ../../../myWindows
INCLUDEPATH +=  ../../../
INCLUDEPATH += ../../../include_windows
INCLUDEPATH += ../../UI/FileManager

#DEFINES += _FILE_OFFSET_BITS=64 _LARGEFILE_SOURCE NDEBUG _REENTRANT
DEFINES +=  ENV_UNIX UNICODE _UNICODE
DEFINES += LANG NEW_FOLDER_INTERFACE EXTERNAL_CODECS

