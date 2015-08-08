#!/bin/bash
CONTAINER_NAME="recipe-site"
echo "stop and remove old container: ${CONTAINER_NAME}"
sudo docker stop recipe-site
sudo docker rm recipe-site

echo "start new container with name '${CONTAINER_NAME}'"
sudo docker run --cap-add SYS_PTRACE -P -d --name $CONTAINER_NAME deception/recipe-site

#expose apache urls to /etc/hosts
sudo grep -v "recipe-" /etc/hosts > /tmp/hosts 
sudo mv /tmp/hosts /etc/hosts

#Collect Informations
IPADDRESS="$(sudo docker inspect recipe-site | grep \"IPAddress\" | sed -e 's/^[[:space:]]*//')"
IPADDRESS="$(echo -e "${IPADDRESS}" | sed -e 's/\"//g' | sed -e 's/,//g')"
IPADDRESS="$(echo -e "${IPADDRESS}" | sed -e 's/\"//g')"
IPADDRESS="$(echo -e "${IPADDRESS}" | sed -e 's/IPAddress://g')"
IPADDRESS="$(echo -e "${IPADDRESS}" | sed -e 's/^[[:space:]]*//')"

echo "${IPADDRESS} recipe-server.jd.de" >> /etc/hosts
echo "${IPADDRESS} recipe-website.jd.de" >> /etc/hosts
echo "${IPADDRESS} recipe-server-demodata.jd.de" >> /etc/hosts

echo "=======================Relevant Informations=============================="
echo "Container Name: ${CONTAINER_NAME}"
echo "IPAddress: ${IPADDRESS}"
echo "=======================SSH================================================"
echo "ssh: ssh root@${IPADDRESS}"
echo "password: docker"
echo "=======================Deployed Applications=============================="
echo "recipe-server: http://recipe-server.jd.de/"
echo "recipe-website: http://recipe-website.jd.de/"
echo "recipe-server-demodata: http://recipe-server-demodata.jd.de/"
