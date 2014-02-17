################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../QuadBone.cpp 

OBJS += \
./QuadBone.o 

CPP_DEPS += \
./QuadBone.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	/usr/local/angstrom/arm/bin/arm-angstrom-linux-gnueabi-g++ -I"/home/flitjes/workspace/QuadBone/interfaces" -I"/home/flitjes/workspace/QuadBone/communication" -I"/home/flitjes/workspace/QuadBone/actuators" -I"/home/flitjes/workspace/QuadBone/sensors" -I"/home/flitjes/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


