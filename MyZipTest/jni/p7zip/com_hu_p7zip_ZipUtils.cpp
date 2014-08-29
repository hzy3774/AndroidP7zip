/*
 * com_hu_p7zip_ZipUtils.cpp
 *
 * interface of jni
 *
 *  Created on: 2014-8-12
 *      Author: HZY
 */


#include "JniWrapper.h"


#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_hu_p7zip_ZipUtils
 * Method:    executeCommand
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_hu_p7zip_ZipUtils_executeCommand
  (JNIEnv *env, jclass thiz, jstring command)
{
	const char* ccommand = (const char*)env->GetStringUTFChars(command, NULL);
	//char* ccommand = Jstring2CStr(env, command);
	LOGI("start[%s]", ccommand);
	jint ret = executeCommand((const char*)ccommand);
	LOGI("end[%s]", ccommand);
	//free(ccommand);
	env->ReleaseStringUTFChars(command, ccommand);
	return ret;
}

/*
 * Class:     com_hu_p7zip_ZipUtils
 * Method:    freopenStdout
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_hu_p7zip_ZipUtils_freopenStdout
  (JNIEnv *env, jclass thiz, jstring filePath, jstring type)
{
	const char* cfilePath = (const char*)env->GetStringUTFChars(filePath, NULL);
	const char* ctype = (const char*)env->GetStringUTFChars(type, NULL);
	FILE* fp = freopen(cfilePath, ctype, stdout);
	LOGI("freopen[%s, %s, stdout]", cfilePath, ctype);
	env->ReleaseStringUTFChars(filePath, cfilePath);
	env->ReleaseStringUTFChars(type, ctype);
	return (fp != NULL);
}

/*
 * Class:     com_hu_p7zip_ZipUtils
 * Method:    freopenStderr
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_hu_p7zip_ZipUtils_freopenStderr
  (JNIEnv *env, jclass thiz, jstring filePath, jstring type)
{
	const char* cfilePath = (const char*)env->GetStringUTFChars(filePath, NULL);
	const char* ctype = (const char*)env->GetStringUTFChars(type, NULL);
	FILE* fp = freopen(cfilePath, ctype, stderr);
	LOGI("freopen[%s, %s, stderr]", cfilePath, ctype);
	env->ReleaseStringUTFChars(filePath, cfilePath);
	env->ReleaseStringUTFChars(type, ctype);
	return (fp != NULL);
}

#ifdef __cplusplus
}
#endif

