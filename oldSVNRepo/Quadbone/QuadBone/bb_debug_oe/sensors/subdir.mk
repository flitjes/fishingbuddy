################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../sensors/ADXL345.cpp \
../sensors/L3G4200D.cpp 

OBJS += \
./sensors/ADXL345.o \
./sensors/L3G4200D.o 

CPP_DEPS += \
./sensors/ADXL345.d \
./sensors/L3G4200D.d 


# Each subdirectory must supply rules for building sources it contributes
sensors/%.o: ../sensors/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	/usr/local/oecore-x86_64/sysroots/x86_64-angstromsdk-linux/usr/bin/armv7a-angstrom-linux-gnueabi/arm-angstrom-linux-gnueabi-g++ -I/usr/local/oecore-x86_64/sysroots/armv7a-angstrom-linux-gnueabi/usr/include/ -I"/home/flitjesdev/workspace/QuadBone/interfaces" -I"/home/flitjesdev/workspace/QuadBone/communication" -I"/home/flitjesdev/workspace/QuadBone/actuators" -I"/home/flitjesdev/workspace/QuadBone/sensors" -I"/home/flitjesdev/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


