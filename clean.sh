mvn clean
STATUS=$?
if [ $STATUS -eq 0 ]; then
	echo "target Cleaned Successfully ..."
	rm -r out
	STATUS=$?
	if [ $STATUS -eq 0 ]; then
		echo "Out Cleaned Successfully ..."
	else
		"Error in Cleaning 'out' file ..."	
	fi
else
	echo "Error in Cleaning 'target' file ..."
fi
echo "Project Cleaned Successfully"