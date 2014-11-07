#!/bin/bash
sudo apt-get install -y apache2 sshpass
sudo a2enmod rewrite
sudo a2enmod proxy
sudo a2enmod proxy_http
sudo service apache2 start

sshpass -p 'vagrant' ssh -o StrictHostKeyChecking=no vagrant@deception uptime
sshpass -p 'vagrant' scp -r vagrant@deception:/home/deception/git/chef-tests/apache-conf .

sudo cp apache-conf/virtualbox.conf /etc/apache2/sites-available
sudo ln -s /etc/apache2/sites-available/virtualbox.conf /etc/apache2/sites-enabled/virtualbox.conf

sudo service apache2 restart
