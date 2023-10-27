#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_codingthoughtctl_utilities_Keys_getAssessToken(JNIEnv *env, jobject object) {
std::string access_token = "36a028fe54c8005f6136bebb108dc2caf650233f";
return env->NewStringUTF(access_token.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_codingthoughtctl_utilities_Keys_getRefreshToken(JNIEnv *env, jobject object) {
std::string refresh_token = "14f03f8234ca6760fe02ff258763c07ae37f3fe1";
return env->NewStringUTF(refresh_token.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_codingthoughtctl_utilities_Keys_getClientID(JNIEnv *env, jobject object) {
    std::string client_id = "2101165913c85e0";
    return env->NewStringUTF(client_id.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_codingthoughtctl_utilities_Keys_getClientSecret(JNIEnv *env, jobject object) {
    std::string client_secret = "03b7ec026c0fb1581c321f15e98b2c2f0b00748a";
    return env->NewStringUTF(client_secret.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_codingthoughtctl_utilities_Keys_getBaseUrl(JNIEnv *env, jobject object) {
    std::string base_url = "https://api.imgur.com/3/";
    return env->NewStringUTF(base_url.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_codingthoughtctl_utilities_Keys_getAccessTokenUrl(JNIEnv *env, jobject object) {
    std::string access_token_url = "https://api.imgur.com/oauth2/token";
    return env->NewStringUTF(access_token_url.c_str());
}

