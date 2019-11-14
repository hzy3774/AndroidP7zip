#include <ndkhelper.h>
#include <cmd/command.h>

JNIEXPORT jstring JNICALL
JNI_FUNC(get7zVersionInfo)(JNIEnv *env, jclass type) {
    return env->NewStringUTF(MY_VERSION_COPYRIGHT_DATE);
}

JNIEXPORT jint JNICALL
JNI_FUNC(executeCommand)(JNIEnv *env, jclass type, jstring command_) {
    const char *command = env->GetStringUTFChars(command_, nullptr);
    LOGI("CMD:[%s]", command);
    int ret = executeCommand(command);
    env->ReleaseStringUTFChars(command_, command);
    return ret;
}




