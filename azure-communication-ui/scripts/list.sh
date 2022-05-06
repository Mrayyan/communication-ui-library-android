#!/usr/bin/env bash

export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
export ANDROID_HOME=~/Library/Android/sdk
export ANDROID_SDK_ROOT=~/Library/Android/sdk
export PATH=${PATH}:${ANDROID_HOME}/tools
export PATH=${PATH}:${ANDROID_HOME}/platform-tools
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
#export ANDROID_SDK_ROOT=$PWD/android-sdk
SYS_IMG_TAG=google_apis
CPU=x86_64
OS_VERSION=android-29
SYSTEM_IMAGE="system-images;${OS_VERSION};${SYS_IMG_TAG};${CPU}"

# Install AVD files
echo "y" | $ANDROID_HOME/tools/bin/sdkmanager --sdk_root=${ANDROID_HOME} --install ${SYSTEM_IMAGE}

# Create emulator
$ANDROID_HOME/tools/bin/avdmanager list
$ANDROID_HOME/tools/bin/avdmanager create --help
$ANDROID_HOME/tools/bin/avdmanager delete avd -n xamarin1