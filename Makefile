all:
	#sl
	gradle build
	gradle assemble
	(cd ./build/libs/; java -jar PriceWatcherCS.jar)

