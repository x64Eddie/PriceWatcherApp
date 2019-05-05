all:
	#sl
	./gradlew build
	./gradlew assemble
	(cd ./build/libs/; java -jar PriceWatcherCS.jar)
clean:
	./gradlew clean
