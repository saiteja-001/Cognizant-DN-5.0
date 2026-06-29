@echo off
if not exist lib mkdir lib

if not exist lib\junit-platform-console-standalone-1.9.3.jar (
    echo Downloading junit-platform-console-standalone-1.9.3.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar' -OutFile 'lib\junit-platform-console-standalone-1.9.3.jar'"
)

if not exist lib\mockito-core-5.3.1.jar (
    echo Downloading mockito-core-5.3.1.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/mockito/mockito-core/5.3.1/mockito-core-5.3.1.jar' -OutFile 'lib\mockito-core-5.3.1.jar'"
)

if not exist lib\byte-buddy-1.14.4.jar (
    echo Downloading byte-buddy-1.14.4.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.14.4/byte-buddy-1.14.4.jar' -OutFile 'lib\byte-buddy-1.14.4.jar'"
)

if not exist lib\byte-buddy-agent-1.14.4.jar (
    echo Downloading byte-buddy-agent-1.14.4.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy-agent/1.14.4/byte-buddy-agent-1.14.4.jar' -OutFile 'lib\byte-buddy-agent-1.14.4.jar'"
)

if not exist lib\objenesis-3.3.jar (
    echo Downloading objenesis-3.3.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/objenesis/objenesis/3.3/objenesis-3.3.jar' -OutFile 'lib\objenesis-3.3.jar'"
)

echo Compiling project...
if not exist bin mkdir bin
javac -cp "lib\*" -d bin src\main\java\com\example\*.java src\test\java\com\example\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)

echo Compilation successful. Running Mockito unit tests...
java -Dnet.bytebuddy.experimental=true -jar lib\junit-platform-console-standalone-1.9.3.jar -cp "bin;lib\mockito-core-5.3.1.jar;lib\byte-buddy-1.14.4.jar;lib\byte-buddy-agent-1.14.4.jar;lib\objenesis-3.3.jar" -c com.example.MyServiceTest
