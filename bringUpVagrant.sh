#!/bin/bash

pushd recipe-editor
gradle war
popd

pushd recipe-server
gradle war
popd

vagrant destroy -f
vagrant up
vagrant provision
