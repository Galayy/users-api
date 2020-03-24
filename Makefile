PROJECT_NAME := users-api

clean:
ifeq ($(OS),Windows_NT)
	gradlew clean
else
	./gradlew clean
endif

build: clean
ifeq ($(OS),Windows_NT)
ifndef skip-test
	gradlew build
else
	gradlew build -x test
endif
else
ifndef skip-test
	./gradlew build
else
	./gradlew build -x test
endif
endif

docker-build: build
	docker image build -t $(PROJECT_NAME) .

heroku: docker-build
ifndef login
	heroku container:login
endif
	heroku container:push web --app=$(PROJECT_NAME)-service
	heroku container:release web --app=$(PROJECT_NAME)-service
	heroku stack:set heroku-18 --app=$(PROJECT_NAME)-service

