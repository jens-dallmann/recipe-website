FROM deception/examplewebapp 
MAINTAINER Jens Dallmann


#enable debugging
#RUN sed s/#JAVA_OPTS/JAVA_OPTS/g /etc/default/tomcat7
RUN sed -i 's/#JAVA_OPTS="\$/JAVA_OPTS="\$/g' /etc/default/tomcat7

#Apache Config
ADD tmp/apache-conf/sites-available /etc/apache2/sites-available
ADD tmp/apache-conf/apache2.conf /etc/apache2/

RUN a2ensite recipe-server
RUN a2ensite recipe-website
RUN a2ensite recipe-server-demodata

#enable logging
RUN mkdir /var/log/jd
RUN chown -R tomcat7:tomcat7 /var/log/jd

ADD tmp/recipe-server.war /var/lib/tomcat7/webapps/
ADD tmp/recipe-website.war /var/lib/tomcat7/webapps/
ADD tmp/recipe-server-demodata.war /var/lib/tomcat7/webapps/

EXPOSE 8000
CMD supervisord -c /etc/supervisor.conf
