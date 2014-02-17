################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../communication/Ethernet.cpp \
../communication/Shell.cpp 

OBJS += \
./communication/Ethernet.o \
./communication/Shell.o 

CPP_DEPS += \
./communication/Ethernet.d \
./communication/Shell.d 


# Each subdirectory must supply rules for building sources it contributes
communication/%.o: ../communication/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	/usr/local/angstrom/arm/bin/arm-angstrom-linux-gnueabi-g++ -I"/home/flitjes/workspace/QuadBone/interfaces" -I"/home/flitjes/workspace/QuadBone/communication" -I"/home/flitjes/workspace/QuadBone/actuators" -I"/home/flitjes/workspace/QuadBone/sensors" -I"/home/flitjes/workspace/QuadBone/protocols" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


