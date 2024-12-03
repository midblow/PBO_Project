@echo off

:: Compile all Java files in the User folder
javac -d bin Landing_Page/*.java

:: Change to the bin directory
cd bin

:: Run the home_user class, ensuring the classpath includes the bin directory and MySQL connector
java -cp .;../lib/mysql-connector-j-9.1.0.jar Landing_Page.ULoginForm

:: Pause to keep the command window open
pause
