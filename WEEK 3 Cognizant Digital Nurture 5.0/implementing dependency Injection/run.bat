@echo off
if not exist lib mkdir lib

if not exist lib\spring-context-5.3.30.jar (
    echo Downloading spring-context-5.3.30.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/spring-context/5.3.30/spring-context-5.3.30.jar' -OutFile 'lib\spring-context-5.3.30.jar'"
)

if not exist lib\spring-core-5.3.30.jar (
    echo Downloading spring-core-5.3.30.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/spring-core/5.3.30/spring-core-5.3.30.jar' -OutFile 'lib\spring-core-5.3.30.jar'"
)

if not exist lib\spring-beans-5.3.30.jar (
    echo Downloading spring-beans-5.3.30.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/spring-beans/5.3.30/spring-beans-5.3.30.jar' -OutFile 'lib\spring-beans-5.3.30.jar'"
)

if not exist lib\spring-expression-5.3.30.jar (
    echo Downloading spring-expression-5.3.30.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/spring-expression/5.3.30/spring-expression-5.3.30.jar' -OutFile 'lib\spring-expression-5.3.30.jar'"
)

if not exist lib\spring-aop-5.3.30.jar (
    echo Downloading spring-aop-5.3.30.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/spring-aop/5.3.30/spring-aop-5.3.30.jar' -OutFile 'lib\spring-aop-5.3.30.jar'"
)

if not exist lib\commons-logging-1.2.jar (
    echo Downloading commons-logging-1.2.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar' -OutFile 'lib\commons-logging-1.2.jar'"
)

echo Compiling project...
if not exist bin mkdir bin
javac -cp "lib\*" -d bin src\main\java\com\library\repository\*.java src\main\java\com\library\service\*.java src\main\java\com\library\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)

echo Copying Spring configuration xml to classpath...
copy src\main\resources\applicationContext.xml bin\ > nul

echo Compilation successful. Running LibraryManagementApplication...
java -cp "bin;lib\*" com.library.LibraryManagementApplication
