set TOMCAT_WEBAPPS_SOURCE="D:\SOARY\Downloads\tomcat-10.1.28-windows-x64\apache-tomcat-10.1.28\webapps"

call .\mvnw.cmd compile
call .\mvnw.cmd package
call .\mvnw.cmd test

copy target\bibliotheque-0.0.1-SNAPSHOT.war %TOMCAT_WEBAPPS_SOURCE%