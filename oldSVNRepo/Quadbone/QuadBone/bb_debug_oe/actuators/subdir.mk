################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../actuators/Multiplex.cpp \
../actuators/PwmController.cpp 

OBJS += \
./actuators/Multiplex.o \
./actuators/PwmController.o 

CPP_DEPS += \
./actuators/Multiplex.d \
./actuators/PwmController.d 


# Each subdirectory must supply rules for building sources it contributes
actuators/%.o: ../actuators/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	/usr/local/oecore-x86_64/sysroots/x86_64-angstromsdk-linux/usr/bin/armv7a-angstrom-linux-gnueabi/arm-angstrom-linux-gnueabi-g++ -I/usr/local/oecore-x86_64/sysroots/armv7a-angstrom-linux-gnueabi/usr/include/ -I"/home/flitjesdev/workspace/QuadBone/interfaces" -I"/home/flitjesdev/workspace/QuadBone/communication" -I"/home/flitjesdev/workspace/QuadBone/actuators" -I"/home/flitjesdev/workspace/QuadBone/sensors" -I"/home/flitjesdev/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


