@echo off

:: ---------
javac -d bin User/*.java

@REM java -cp bin Landing_Page.Dashboard

java -cp bin;lib/mysql-connector-j-9.1.0.jar Landing_Page.Dashboard

cd bin

pause