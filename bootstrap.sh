#!/bin/bash
yum update
yum -y install wget

allInstalledService=$( { service --status-all; } 2>&1 )
isJenkinsInstalled=` echo "$allInstalledService" | grep jenkins | sed -e 's/^ *//' -e 's/ *$//'`

if [ -z "$isJenkinsInstalled" ]; then
	echo "Jenkins is not installed... Will install now...."
	wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
	rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
	yum -y install jenkins
	echo "Jenkins should be installed now... Will start it"
	service jenkins start
else
	echo "JENKINS IS INSTALLED... $isJenkinsInstalled"
fi
