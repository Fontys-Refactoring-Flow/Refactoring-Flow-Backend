@echo off
echo Java keystore genereren...
keytool -keystore keystore2.jks -genkey -keyalg RSA -alias dev
move keystore2.jks src\main\resources\keys\
