#!/bin/bash
#Download and install java
sudo apt-get remove -y openjdk-*

pushd /home/vagrant/toInstall
tar -zxf jdk-8u25-linux-x64.tar.gz
sudo mv jdk1.8.0_25 /opt
popd

echo '--------------------------- INSTALL JDK ---------------------'
echo 'JAVA_HOME=/opt/jdk1.8.0_25' >> /home/vagrant/.bashrc
echo 'export JAVA_HOME=$JAVA_HOME' >> /home/vagrant/.bashrc
echo 'PATH=$PATH:$JAVA_HOME/bin' >> /home/vagrant/.bashrc
echo 'export PATH=$PATH' >> /home/vagrant/.bashrc
export JAVA_HOME=/opt/jdk1.8.0_25
echo '-------------------------- END INSTALL JDK -----------------'

PATH=$PATH:$JAVA_HOME/bin
export PATH=$PATH
sudo update-alternatives --install "/usr/bin/java" "java" "/opt/jdk1.8.0_25/bin/java" 1
sudo update-alternatives --set java /opt/jdk1.8.0_25/bin/java

sudo apt-get install -y apache2 sshpass tomcat7
sudo a2enmod rewrite
sudo a2enmod proxy
sudo a2enmod proxy_http
sudo service apache2 start
sudo unlink /etc/apache2/sites-enabled/*
sudo rm -rf /etc/apache2/sites-available
sudo cp -R apache-conf/sites-available /etc/apache2/
sudo a2ensite jenkins
sudo a2ensite rest-service

sudo mv /home/vagrant/libs/jd-server-0.1.0.jar /opt/
sudo java -jar /opt/jd-server-0.1.0.jar&
echo '---------------------------CONFIGURE TOMCAT------------------'
sudo mv -f /home/vagrant/tomcat/server.xml /etc/tomcat7/
echo '--------------------------- INSTALL recipe-editor webapp ....'
sudo mkdir /usr/share/tomcat7/webapp
sudo mv /home/vagrant/recipe-editor/recipe-editor.war /var/lib/tomcat7/webapps
echo '-------------------------- END INSTALL recipe-editor webapp...'

sudo service tomcat7 restart
sudo service apache2 restart


