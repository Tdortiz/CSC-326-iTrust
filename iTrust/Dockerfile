FROM tomcat:7.0-jre8
MAINTAINER CSC326-202-01

# fetch update before installing mysql-server
RUN apt-get -y update

# install mysql-server to our container
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install mysql-server

# add our packaged webapp
COPY target/iTrust-22.0.0 /usr/local/tomcat/webapps/iTrust
