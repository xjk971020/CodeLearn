#Define the execution environment for the script
#!/usr/bin/bash

#Author: Cathetine
#Created Time: 2020/02/10 16:50
#Release: 1.0
#Script Description: nginx install script

yum -y install wget gcc pcre-devel zlib-devel
wget http://nginx.org/download/nginx-1.16.1.tar.gz
tar xf nginx-1.16.1.tar.gz
cd nginx-1.16.1
./configure --prefix=/usr/local/nginx
make
make install
