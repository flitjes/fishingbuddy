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
	/usr/local/angstrom/arm/bin/arm-angstrom-linux-gnueabi-g++ -I"/home/flitjes/workspace/QuadBone/interfaces" -I"/home/flitjes/workspace/QuadBone/communication" -I"/home/flitjes/workspace/QuadBone/actuators" -I"/home/flitjes/workspace/QuadBone/sensors" -I"/home/flitjes/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


