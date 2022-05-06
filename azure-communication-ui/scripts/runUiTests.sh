#!/usr/bin/env bash

unset ANDROID_SERIAL
DEVICE=($(adb devices | grep "device$" | sed -e "s|device||g"))
if [ -z "$DEVICE" ]; then
  ./installEmulator.sh
fi

cd ..
#Replace ACS Token with expired token
cat ./local.properties | sed -e '/^ACS_TOKEN=/d' > ./temp_file
printf "ACS_TOKEN=\"$1\"\n" >> ./temp_file
mv -f ./temp_file ./local.properties

DEVICE1=($(adb devices | grep "device$" | head -1 | sed -e "s|device||g"))
echo $DEVICE1
DEVICE2=($(adb devices | grep "device$" | tail -1 | sed -e "s|device||g"))
echo $DEVICE2
export ANDROID_SERIAL=$DEVICE1
# run Ui tests with the required parameters
./gradlew clean build
./gradlew connectedAppCenterDebugAndroidTest --stacktrace -Pandroid.testInstrumentationRunnerArguments.class=com.azure.android.communication.ui.callingcompositedemoapp.CallingCompositeBaselineUiTest#testJoinGroupCall -Pandroid.testInstrumentationRunnerArguments.teamsUrl="$2" -Pandroid.testInstrumentationRunnerArguments.groupId="$3" -Pandroid.testInstrumentationRunnerArguments.acsToken=$4 &

export ANDROID_SERIAL=$DEVICE2

./gradlew connectedAppCenterDebugAndroidTest --stacktrace -Pandroid.testInstrumentationRunnerArguments.class=com.azure.android.communication.ui.callingcompositedemoapp.CallingCompositeBaselineUiTest#testJoinGroupCall -Pandroid.testInstrumentationRunnerArguments.teamsUrl="$2" -Pandroid.testInstrumentationRunnerArguments.groupId="$3" -Pandroid.testInstrumentationRunnerArguments.acsToken=eyJhbGciOiJSUzI1NiIsImtpZCI6IjEwNCIsIng1dCI6IlJDM0NPdTV6UENIWlVKaVBlclM0SUl4Szh3ZyIsInR5cCI6IkpXVCJ9.eyJza3lwZWlkIjoiYWNzOmI2YWFkYTFmLTBiMWQtNDdhYy04NjZmLTkxYWFlMDBhMWQwMV8wMDAwMDAxMS0zODFjLTJkNzUtZTNjNy01OTNhMGQwMDNhZmYiLCJzY3AiOjE3OTIsImNzaSI6IjE2NTE4MjE0MTQiLCJleHAiOjE2NTE5MDc4MTQsImFjc1Njb3BlIjoidm9pcCIsInJlc291cmNlSWQiOiJiNmFhZGExZi0wYjFkLTQ3YWMtODY2Zi05MWFhZTAwYTFkMDEiLCJpYXQiOjE2NTE4MjE0MTR9.HSn7p1CfY8OWGVaQVL-ErXxUHaepX9nw5YOr1OiOhpU-X7ZVxVRAFZG3BFF67896tC5xzEf-5r6v1eaCErDoEzWlppLAhr74bLK5oB6KydT50DBN0YVvw0MvPPs2B63pus6P1ngBRoTuJT2yiQeangOdoBX2lJigaqS19uAB8YECkSHPUcV2ydfoqIC12dka2NdBc1Glc2m1TpCFnKRqWxOiHDmt9SrYK9j0dncB3QrRY799Fl9-cANzWvpB7dBlTkwIh3WrUR7WKsBCO1vk9wfoxfWJA33_VmRR4QkWRCHk9gG6kN97l47RoUA6UarH1FelCrsL24EvVSbLwJNsUg

#adb emu kill
#$ANDROID_HOME/tools/bin/avdmanager delete avd -n xamarin1


#export ANDROID_SERIAL=$DEVICE1
#adb emu kill

#$ANDROID_HOME/tools/bin/avdmanager delete avd -n xamarin