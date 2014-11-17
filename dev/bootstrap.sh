
#!/bin/bash

echo '\t-----------Remove Previous enabled sites--------'
sudo unlink /etc/apache2/sites-enabled/*
sudo rm -rf /etc/apache2/sites-available
sudo cp -R apache-conf/sites-available /etc/apache2/

echo '\t-----------Enable Sites------------'
sudo a2ensite jenkins
sudo a2ensite recipe-server
sudo a2ensite recipe-editor
sudo a2ensite recipe-editor-log
sudo a2ensite recipe-server-log
echo '---------------------------CONFIGURE TOMCAT------------------'
echo 'copy server.xml'
sudo mv -f /home/vagrant/tomcat/server.xml /etc/tomcat7/
echo '--------------------------- INSTALL recipe-editor webapp ....'
sudo mkdir /var/log/jd/
sudo chown -R tomcat7:tomcat7 /var/log/jd
echo 'create webapps dir and copy webapps'
sudo mv /home/vagrant/recipe-editor/recipe-editor.war /var/lib/tomcat7/webapps
sudo mv /home/vagrant/recipe-server/recipe-server.war /var/lib/tomcat7/webapps
echo '-------------------------- END INSTALL recipe-editor webapp...'

sudo service tomcat7 restart
sudo service apache2 restart


