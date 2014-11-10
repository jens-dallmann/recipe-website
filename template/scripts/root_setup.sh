
#!/bin/bash

groupadd -r admin
usermod -a -G admin vagrant
cp /etc/sudoers /etc/sudoers.orig
sed -i -e '/Defaults\s\+env_reset/a Defaults\texempt_group=admin' /etc/sudoers
sed -i -e 's/%admin ALL=(ALL) ALL/%admin ALL=NOPASSWD:ALL/g' /etc/sudoers

#add apt repositories
	#Jenkins
wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'

# Updating and Upgrading dependencies
apt-get update -y -q
apt-get upgrade -y -q

# Install necessary libraries for guest additions and Vagrant NFS Share
apt-get -y -q install linux-headers-generic build-essential dkms nfs-common
# Install necessary dependencies
apt-get -y -q install curl wget git tmux xvfb vim gdebi

# Install services
apt-get -y install jenkins

# Install tools
apt-get -y -q install nano
