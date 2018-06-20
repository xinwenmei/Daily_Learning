#!/bin/bash
#echo  "PRODUCT_COPY_FILES +=" '\' >> Android.mk
path1="vendor/tpv/lib/"
path2="system/lib/"
path3="/home/okay"
last="libart.so"

for filename in $(find /home/okay/Decument/file -name *.so)
do
    base=`basename $filename` 
    if [ "$base" != "$last" ]
    then
        result1="\t$path1$base:$path2$base\\ \n"
    else
	result1="\t$path1$base:$path2$base"
    fi
result=${result}${result1}
done
echo -e "PRODUCT_COPY_FILES +=" '\'$'\n'$result  >> $path3/Android.mk
 

