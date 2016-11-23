FROM tomcat:7.0-jre8
MAINTAINER CSC326-202-01

RUN apt-get -y update
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install mysql-server

# add our built files
COPY target/iTrust-22.0.0 /usr/local/tomcat/webapps/iTrust
