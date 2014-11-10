#!/bin/bash

pushd recipe-editor
gradle war
popd

pushd server
gradle assemble
popd

vagrant destroy -f
vagrant up
vagrant provision
