#!/bin/bash

i=0

echo "Start ====  $(date) ======"

while [ $i -lt 40 ]; do
	echo "Snapshot at $(date) " 
	top -n 1 -bc 
	sleep 30 
	i=$(( i + 1 ))
done

echo "End ====  $(date) ======"

