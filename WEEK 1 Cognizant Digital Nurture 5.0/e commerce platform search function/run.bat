@echo off
echo Compiling project...
if not exist bin mkdir bin
javac -d bin src\com\ecommerce\search\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)
echo Compilation successful. Running SearchTest...
java -cp bin com.ecommerce.search.SearchTest
echo.
echo ==================================================
echo              BIG O ANALYSIS DETAILS
echo ==================================================
type analysis.md
