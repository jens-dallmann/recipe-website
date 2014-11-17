#!/bin/bash

pushd recipe-editor
gradle war -Denv=prod
popd

pushd recipe-server
gradle war
popd

vagrant destroy -f
vagrant up
vagrant provision
