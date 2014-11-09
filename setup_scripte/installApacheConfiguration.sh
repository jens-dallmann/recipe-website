#!/bin/bash
ABSOLUTE_PATH=$0
BASE_NAME=`basename $1`
sudo cp $ABSOLUTE_PATH /etc/apache2/sites-available/
sudo ln -s /etc/apache2/sites-available/ /etc/apache2/sites_enabled/$BASE_NAME

