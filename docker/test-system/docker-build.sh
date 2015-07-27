#!/bin/bash

mkdir tmp
cp ../../dev/recipe-server/target/recipe-server.war tmp/
cp ../../dev/recipe-website/target/recipe-website.war tmp/
cp -R ../../dev/apache-conf tmp/
docker build -t deception/recipe-site .
rm -rf tmp
