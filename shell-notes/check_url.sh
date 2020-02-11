##############################
#Author:Cathetine                  
#Create_Time:2020-02-11
#Versios:1.0
#Mail:823840954@qq.com
#Description:
##############################

HTTP_CODE=$(curl -o /dev/null --connect-timeout 3 -s -w "%{http_code}" $1)
    if [ $HTTP_CODE -eq 200 ]; then
        echo -e "\033[32mInfo\033[0m: \033[34m$1\033[0m Access \033[32m successful\033[0m!"
    else 
        echo -e "\033[31mWarning\033[0m: \033[33m$1\033[0m Access \033[31mfailure\033[0m!"
    fi
