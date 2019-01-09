#include <ndkhelper.h>
#include <cmd/command.h>

JNIEXPORT jstring JNICALL
JNI_FUNC(get7zVersionInfo)(JNIEnv *env, jclass type) {
    return env->NewStringUTF(MY_P7ZIP_VERSION_INFO);
}

JNIEXPORT jint JNICALL
JNI_FUNC(executeCommand)(JNIEnv *env, jclass type, jstring command_) {
    const char *command = env->GetStringUTFChars(command_, 0);
    LOGI("CMD:[%s]", command);
    int ret = executeCommand(command);
    env->ReleaseStringUTFChars(command_, command);
    return ret;
}




