#!/bin/bash

path1="vendor/tpv/lib/"
path2="system/lib/"
path3="/home/okay"
last="libart.so"
echo  "PRODUCT_COPY_FILES +=" '\' >> $path3/Android.mk
for filename in $(find /home/okay/Decument/file -name *.so)
do
    base=`basename $filename` 
    if [ "$base" != "$last" ]
    then 
        echo $'\t'$path1$base:$path2$base'\' >> /home/okay/Android.mk
    else
	echo $'\t'$path1$base:$path2$base >> /home/okay/Android.mk
    fi
done



