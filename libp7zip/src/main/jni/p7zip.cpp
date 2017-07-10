#include <ndkhelper.h>
#include <7zip/MyVersion.h>
#include <cmd/command.h>


JNIEXPORT jstring JNICALL
Java_com_hzy_libp7zip_P7ZipApi_get7zVersionInfo(JNIEnv *env, jclass type) {
    return env->NewStringUTF(MY_VERSION_COPYRIGHT_DATE);
}

JNIEXPORT jint JNICALL
Java_com_hzy_libp7zip_P7ZipApi_executeCommand(JNIEnv *env, jclass type, jstring command_) {
    const char *command = env->GetStringUTFChars(command_, 0);
    int ret = executeCommand(command);
    env->ReleaseStringUTFChars(command_, command);
    return ret;
}


