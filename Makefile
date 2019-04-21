all:
	#sl
	infer run -- gradle build
	gradle assemble
	(cd ./build/libs/; java -jar PriceWatcherCS.jar)

