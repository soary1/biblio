@echo off


echo Arret de Tomcat...
call "D:\SOARY\Downloads\tomcat-10.1.28-windows-x64\apache-tomcat-10.1.28\bin\shutdown.bat"
echo Nettoyage et compilation du projet Maven...
mvn clean package


