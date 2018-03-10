#!/bin/bash

path=/home/ec2-user/CryptoProject/dataCollection/

/usr/bin/s3cmd-1.6.1/s3cmd put ${path}temporaryDataStorage/. s3://cryptocurrency1111/CryptoProject/data/unproccessedData/ --recursive

cp -a ${path}temporaryDataStorage/. ${path}allTimeStorage/

rm ${path}temporaryDataStorage/*
