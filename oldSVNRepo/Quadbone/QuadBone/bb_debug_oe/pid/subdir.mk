################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../pid/PID.cpp 

OBJS += \
./pid/PID.o 

CPP_DEPS += \
./pid/PID.d 


# Each subdirectory must supply rules for building sources it contributes
pid/%.o: ../pid/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	/usr/local/oecore-x86_64/sysroots/x86_64-angstromsdk-linux/usr/bin/armv7a-angstrom-linux-gnueabi/arm-angstrom-linux-gnueabi-g++ -I/usr/local/oecore-x86_64/sysroots/armv7a-angstrom-linux-gnueabi/usr/include/ -I"/home/flitjesdev/workspace/QuadBone/interfaces" -I"/home/flitjesdev/workspace/QuadBone/communication" -I"/home/flitjesdev/workspace/QuadBone/actuators" -I"/home/flitjesdev/workspace/QuadBone/sensors" -I"/home/flitjesdev/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


