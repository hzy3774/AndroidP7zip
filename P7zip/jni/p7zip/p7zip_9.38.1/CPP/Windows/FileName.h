// Windows/FileName.h

#ifndef __WINDOWS_FILENAME_H
#define __WINDOWS_FILENAME_H

#include "../../C/7zTypes.h"

#include "../Common/MyString.h"

namespace NWindows {
namespace NFile {
namespace NName {

const TCHAR kDirDelimiter = CHAR_PATH_SEPARATOR;
const TCHAR kAnyStringWildcard = '*';

void NormalizeDirPathPrefix(CSysString &dirPath); // ensures that it ended with '\\'
#ifndef _UNICODE
void NormalizeDirPathPrefix(UString &dirPath); // ensures that it ended with '\\'
#endif

bool IsAbsolutePath(const wchar_t *s);

bool GetFullPath(CFSTR dirPrefix, CFSTR path, FString &fullPath);
// FIXME bool GetFullPath(CFSTR path, FString &fullPath);

}}}

#endif
