@echo off
echo Compiling project...
if not exist bin mkdir bin
javac -d bin src\com\financial\forecasting\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)
echo Compilation successful. Running FinancialForecasting...
java -cp bin com.financial.forecasting.FinancialForecasting
echo.
echo ==================================================
echo             RECURSION ANALYSIS SUMMARY
echo ==================================================
type analysis.md
