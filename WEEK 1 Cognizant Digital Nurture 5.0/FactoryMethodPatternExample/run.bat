@echo off
echo Compiling project...
if not exist bin mkdir bin
javac -d bin src\com\pattern\factory\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)
echo Compilation successful. Running FactoryMethodTest...
java -cp bin com.pattern.factory.FactoryMethodTest
