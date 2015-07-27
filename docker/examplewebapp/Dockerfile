FROM deception/tomcat7-ssh
MAINTAINER Jens Dallmann

RUN apt-get -y install nano              
RUN apt-get -y install mongodb
RUN apt-get -y install apache2

#Enable mod_proxy
RUN ln /etc/apache2/mods-available/proxy.load /etc/apache2/mods-enabled/proxy.load
RUN ln /etc/apache2/mods-available/proxy_ajp.load /etc/apache2/mods-enabled/proxy_ajp.load
RUN ln /etc/apache2/mods-available/proxy_balancer.load /etc/apache2/mods-enabled/proxy_balancer.load
RUN ln /etc/apache2/mods-available/proxy_connect.load /etc/apache2/mods-enabled/proxy_connect.load
RUN ln /etc/apache2/mods-available/proxy_express.load /etc/apache2/mods-enabled/proxy_express.load
RUN ln /etc/apache2/mods-available/proxy_fcgi.load /etc/apache2/mods-enabled/proxy_fcgi.load
RUN ln /etc/apache2/mods-available/proxy_fdpass.load /etc/apache2/mods-enabled/proxy_fdpass.load
RUN ln /etc/apache2/mods-available/proxy_ftp.load /etc/apache2/mods-enabled/proxy_ftp.load
RUN ln /etc/apache2/mods-available/proxy_html.load /etc/apache2/mods-enabled/proxy_html.load
RUN ln /etc/apache2/mods-available/proxy_http.load /etc/apache2/mods-enabled/proxy_http.load
RUN ln /etc/apache2/mods-available/proxy_scgi.load /etc/apache2/mods-enabled/proxy_scgi.load
RUN ln /etc/apache2/mods-available/proxy_wstunnel.load /etc/apache2/mods-enabled/proxy_wstunnel.load
RUN ln /etc/apache2/mods-available/xml2enc.load /etc/apache2/mods-enabled/xml2enc.load
RUN ln /etc/apache2/mods-available/slotmem_shm.load /etc/apache2/mods-enabled/slotmem_shm.load

#cleanup default pages
RUN unlink /etc/apache2/sites-enabled/*
RUN rm -rf /etc/apache2/sites-available

ADD mongo.sv.conf /etc/supervisor/conf.d/
ADD apache.sv.conf /etc/supervisor/conf.d/
