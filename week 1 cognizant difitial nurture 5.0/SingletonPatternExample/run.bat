@echo off
echo Compiling project...
if not exist bin mkdir bin
javac -d bin src\com\pattern\singleton\Logger.java src\com\pattern\singleton\SingletonTest.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)
echo Compilation successful. Running SingletonTest...
java -cp bin com.pattern.singleton.SingletonTest
