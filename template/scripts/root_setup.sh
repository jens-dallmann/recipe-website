
#!/bin/bash

#add apt repositories
	#Jenkins
wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'

# Updating and Upgrading dependencies
sudo apt-get update -y
sudo apt-get upgrade -y

# Install necessary libraries for guest additions and Vagrant NFS Share
sudo apt-get -y install linux-headers-generic build-essential dkms nfs-common
# Install necessary dependencies
sudo apt-get -y install curl wget git tmux xvfb vim gdebi

# Install services
sudo apt-get -y install jenkins

# Install tools
sudo apt-get -y install nano
#wget http://192.168.178.28/virtualbox-4.3_4.3.18-96516~Ubuntu~raring_amd64.deb
#sudo gdebi virtualbox-4.3_4.3.18-96516~Ubuntu~raring_amd64.deb

#sudo apt-get -y install virtualbox-guest-additions-iso
# Setup sudo to allow no-password sudo for "admin"
groupadd -r admin
usermod -a -G admin vagrant
cp /etc/sudoers /etc/sudoers.orig
sed -i -e '/Defaults\s\+env_reset/a Defaults\texempt_group=admin' /etc/sudoers
sed -i -e 's/%admin ALL=(ALL) ALL/%admin ALL=NOPASSWD:ALL/g' /etc/sudoers
