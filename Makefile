.DEFAULT_GOAL := build-run

build-run: build run

build:
	./mvnw clean package

run:
	java -jar ./target/spring-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar

test:
	./mvnw test
#	java -jar ~/.m2/repository/org/junit/platform/junit-platform-console-standalone/1.4.0-M1/junit-platform-console-standalone-1.4.0-M1.jar [Options]

update:
	./mvnw versions:update-properties versions:display-plugin-updates
