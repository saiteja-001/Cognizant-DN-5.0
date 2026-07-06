@echo off
setlocal enabledelayedexpansion

:: Define Maven Details
set MAVEN_VERSION=3.9.6
set MAVEN_DIR=%~dp0..\maven_home
set MAVEN_ZIP=%MAVEN_DIR%\apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_BIN=%MAVEN_DIR%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd

if not exist "%MAVEN_DIR%" mkdir "%MAVEN_DIR%"

:: Download Apache Maven if binary doesn't exist
if not exist "%MAVEN_BIN%" (
    if not exist "%MAVEN_ZIP%" (
        echo Downloading Apache Maven %MAVEN_VERSION%...
        powershell -Command "Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip' -OutFile '%MAVEN_ZIP%'"
    )
    echo Extracting Apache Maven...
    powershell -Command "Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath '%MAVEN_DIR%'"
)

echo.
echo ==================================================
echo   Building spring-learn using Maven
echo ==================================================
echo.

:: Try to build first without proxy (local sandbox execution)
call "%MAVEN_BIN%" clean package
if %errorlevel% neq 0 (
    echo.
    echo Default build failed. Trying with proxy parameters if configured...
    call "%MAVEN_BIN%" clean package -Dhttp.proxyHost=proxy.cognizant.com -Dhttp.proxyPort=6050 -Dhttps.proxyHost=proxy.cognizant.com -Dhttps.proxyPort=6050 -Dhttp.proxyUser=123456
    if %errorlevel% neq 0 (
        echo Compilation and packaging failed!
        exit /b %errorlevel%
    )
)

echo.
echo ==================================================
echo   Running SpringLearnApplication
echo ==================================================
echo.

java -jar target\spring-learn-0.0.1-SNAPSHOT.jar
