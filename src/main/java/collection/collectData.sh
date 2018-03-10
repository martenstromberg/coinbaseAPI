#!/bin/bash

echo "Bing!" >> /home/ec2-user/testCollect/bing.txt
currentDateTime=$(date '+%Y_%m_%d_%H%M%S')
echo $currentDateTime

/usr/bin/curl -S -o /home/ec2-user/testCollect/${currentDateTime}.json  https://api.coinbase.com/v2/prices/BTC-USD/spot

#/usr/bin/s3cmd-1.6.1/s3cmd put /home/ec2-user/testCollect/BTC_${currentDateTime}.json s3://cryptocurrency1111/bitcoin-raw/

#ETHEREUM
/usr/bin/curl -S -o /home/ec2-user/testCollect/ETH_${currentDateTime}.json  https://api.coinbase.com/v2/prices/ETH-USD/spot

#/usr/bin/s3cmd-1.6.1/s3cmd put /home/ec2-user/testCollect/ETH_${currentDateTime}.json s3://cryptocurrency1111/bitcoin-raw/

#LITECOIN
/usr/bin/curl -S -o /home/ec2-user/testCollect/LTC_${currentDateTime}.json  https://api.coinbase.com/v2/prices/LTC-USD/spot

#/usr/bin/s3cmd-1.6.1/s3cmd put /home/ec2-user/testCollect/LTC_${currentDateTime}.json s3://cryptocurrency1111/bitcoin-raw/

#BITCOIN CASH
/usr/bin/curl -S -o /home/ec2-user/testCollect/BCH_${currentDateTime}.json  https://api.coinbase.com/v2/prices/BCH-USD/spot

#/usr/bin/s3cmd-1.6.1/s3cmd put /home/ec2-user/testCollect/BCH_${currentDateTime}.json s3://cryptocurrency1111/bitcoin-raw/

