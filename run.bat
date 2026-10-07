@echo off
rem Compile and run without NetBeans (needs JDK 11+). Put the MySQL connector jar in lib\ first.
cd /d "%~dp0"
if not exist build\classes mkdir build\classes
javac -encoding UTF-8 -d build\classes -cp "lib\*" src\currency_converter_db\*.java
if errorlevel 1 pause & exit /b 1
java -cp "build\classes;lib\*" currency_converter_db.Currency_converter_db
pause
