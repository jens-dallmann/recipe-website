#!/bin/bash

#add apt repositories
	#Jenkins
wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'

# Updating and Upgrading dependencies
sudo apt-get update -y
sudo apt-get upgrade -y

# Install necessary libraries for guest additions and Vagrant NFS Share
sudo apt-get -y install linux-headers-generic build-essential dkms nfs-common xserver-xorg xserver-xorg-core

# Install necessary dependencies
sudo apt-get -y install curl wget git tmux xvfb vim

# Install services
sudo apt-get -y install jenkins

# Install tools
sudo apt-get -y install nano

# Setup sudo to allow no-password sudo for "admin"
groupadd -r admin
usermod -a -G admin vagrant
cp /etc/sudoers /etc/sudoers.orig
sed -i -e '/Defaults\s\+env_reset/a Defaults\texempt_group=admin' /etc/sudoers
sed -i -e 's/%admin ALL=(ALL) ALL/%admin ALL=NOPASSWD:ALL/g' /etc/sudoers

#Install Virtual Box Guest Additions
wget http://download.virtualbox.org/virtualbox/4.3.18/VBoxGuestAdditions_4.3.18.iso
sudo mkdir /media/VBoxGuestAdditions
sudo mount -o loop,ro VBoxGuestAdditions_4.3.18.iso /media/VBoxGuestAdditions
sudo sh /media/VBoxGuestAdditions/VBoxLinuxAdditions.run
rm VBoxGuestAdditions_4.3.18.iso
sudo umount /media/VBoxGuestAdditions
sudo rmdir /media/VBoxGuestAdditions
