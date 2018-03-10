#!/bin/bash
echo "hello"
path=/home/ec2-user/CryptoProject/dataCollection/temporaryDataStorage/
currentDateTime=$(date '+%Y_%m_%d_%H%M%S')
echo $currentDateTime
echo $path

/usr/bin/curl -S -o ${path}BTC_${currentDateTime}.json  https://api.coinbase.com/v2/prices/BTC-USD/spot
