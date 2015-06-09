// Windows/FileName.cpp

#include "StdAfx.h"

#include "Windows/FileName.h"
#include "Common/Wildcard.h"

// FIXME
namespace NWindows {
namespace NFile {
namespace NDir {
bool MyGetFullPathName(CFSTR path, FString &resFullPath);
}}}

namespace NWindows {
namespace NFile {
namespace NName {

bool IsAbsolutePath(const wchar_t *s) { return s[0] == WCHAR_PATH_SEPARATOR; } // FIXME


void NormalizeDirPathPrefix(CSysString &dirPath)
{
  if (dirPath.IsEmpty())
    return;
  if (dirPath.ReverseFind(kDirDelimiter) != dirPath.Len() - 1)
    dirPath += kDirDelimiter;
}

#ifndef _UNICODE
void NormalizeDirPathPrefix(UString &dirPath)
{
  if (dirPath.IsEmpty())
    return;
  if (dirPath.ReverseFind(wchar_t(kDirDelimiter)) != dirPath.Len() - 1)
     dirPath += wchar_t(kDirDelimiter);
}
#endif

bool GetFullPath(CFSTR dirPrefix, CFSTR s, FString &res)
{
	res = FString(dirPrefix) + FString(s);

	return true;
}

}}}
