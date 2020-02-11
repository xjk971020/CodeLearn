##############################
#Author:Cathetine                  
#Create_Time:2020-02-11
#Versios:1.0
#Mail:823840954@qq.com
#Description:
##############################
#!/bin/bash
DATE=$(date +%F" "%H:%M)
IP=$(ifconfig eth0 |awk -F '[ :]+' '/inet addr/{print $4}')  # 只支持CentOS6
MAIL="823840954@qq.com"
if ! which vmstat &>/dev/null; then
    echo "vmstat command no found, Please install procps package." 
    exit 1
fi
US=$(vmstat |awk 'NR==3{print $13}')
SY=$(vmstat |awk 'NR==3{print $14}')
IDLE=$(vmstat |awk 'NR==3{print $15}')
WAIT=$(vmstat |awk 'NR==3{print $16}')
USE=$(($US+$SY))
if [ $USE -gt 50 ]; then
    echo "
    Date: $DATE
    Host: $IP
    Problem: CPU utilization $USE
    " | mail -s "CPU Monitor" $MAIL
fi
