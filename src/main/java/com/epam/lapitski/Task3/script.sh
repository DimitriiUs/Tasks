#!/bin/bash

cd ../../../../../../../..
if [ -e project_copy ] && [ -f log.txt ]
then
  rm -r log.txt project_copy
fi
mkdir "project_copy"
cp -a  Epam2020/* project_copy
cd project_copy
mvn clean package --log-file log.txt
mv log.txt ..
mv target/runner.jar .
java -jar runner.jar
read -rn1
