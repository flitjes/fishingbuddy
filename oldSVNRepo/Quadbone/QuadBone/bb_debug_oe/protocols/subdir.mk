################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../protocols/I2C.cpp \
../protocols/PwmDriver.cpp 

OBJS += \
./protocols/I2C.o \
./protocols/PwmDriver.o 

CPP_DEPS += \
./protocols/I2C.d \
./protocols/PwmDriver.d 


# Each subdirectory must supply rules for building sources it contributes
protocols/%.o: ../protocols/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	/usr/local/oecore-x86_64/sysroots/x86_64-angstromsdk-linux/usr/bin/armv7a-angstrom-linux-gnueabi/arm-angstrom-linux-gnueabi-g++ -I/usr/local/oecore-x86_64/sysroots/armv7a-angstrom-linux-gnueabi/usr/include/ -I"/home/flitjesdev/workspace/QuadBone/interfaces" -I"/home/flitjesdev/workspace/QuadBone/communication" -I"/home/flitjesdev/workspace/QuadBone/actuators" -I"/home/flitjesdev/workspace/QuadBone/sensors" -I"/home/flitjesdev/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


