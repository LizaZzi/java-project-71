 # Makefile
run-dist:
	./build/install/app/bin/app -hV

install:
	./gradlew clean install

install-dist:
	./gradlew installDist

build:
	./gradlew clean build

run:
	./gradlew run

test:
	./gradlew test

jacoco:
	./gradlew jacocoTestReport

check:
	./gradlew checkstyleMain checkstyleTest

build-run: build run

.PHONY: build