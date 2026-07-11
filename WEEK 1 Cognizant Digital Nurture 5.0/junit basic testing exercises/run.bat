@echo off
if not exist lib mkdir lib

if not exist lib\junit-4.13.2.jar (
    echo Downloading junit-4.13.2.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar' -OutFile 'lib\junit-4.13.2.jar'"
)

if not exist lib\hamcrest-core-1.3.jar (
    echo Downloading hamcrest-core-1.3.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar' -OutFile 'lib\hamcrest-core-1.3.jar'"
)

echo Compiling project...
if not exist bin mkdir bin
javac -cp "lib\*" -d bin src\main\java\com\example\Calculator.java src\test\java\com\example\CalculatorTest.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)

echo Compilation successful. Running JUnit tests...
java -cp "bin;lib\*" org.junit.runner.JUnitCore com.example.CalculatorTest
