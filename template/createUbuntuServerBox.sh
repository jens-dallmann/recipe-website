#!/bin/bash
packer build -only virtualbox-iso ubuntu_server.json
mv -f packer_virtualbox-iso_virtualbox.box ubuntu_server.box
