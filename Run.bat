@echo off

:: Compile all Java files in the User folder and place them in the 'bin' directory
javac -d bin User/*.java

:: Run the main class from the Landing_Page package (replace 'MainClass' with the actual class you want to run)
@REM java -cp bin Landing_Page.Dashboard

:: If you want to use MySQL Connector, uncomment and use the following line instead:
java -cp bin;lib/mysql-connector-j-9.1.0.jar Landing_Page.Dashboard

:: Change to the 'bin' directory (optional)
cd bin

:: Pause to keep the command window open
pause