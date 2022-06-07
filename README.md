## _Prepare for Running tests_
#### _Before run the test Cases you need to have on your pc/ws docker, jdk 1.8, maven run install JDK 1.8 on your pc/workstation and check if it's avalable in some cases you need to download and setup_
#### [Java JDK](https://www.oracle.com/cis/java/technologies/javase/javase8-archive-downloads.html)
#### [Maven ](https://maven.apache.org/download.cgi)
#### [Docker](https://www.docker.com/products/docker-desktop/)

JAVA_HOME environment variable point to your jdk version
```sh
export JAVA_HOME=/usr/bin
```

Add PATH for running maven to your command line
```sh
export PATH=/tmp/maven:$PATH
```

#### for Linux or Mac
export JAVA_HOME=/path/to/jdk

#### Check
```sh
echo $JAVA_HOME
```

#### Add maven to your $PATH
#### where maven-dir you download and unpack maven
```sh
export PATH=$PATH:/maven-dir
```

#### Check maven is avalable in the path
```sh
mvn
```

#### Before start run tests you need to install Docke and runr on your PC/workstation to be able to run Selenium tests
```sh
docker info
```

#### How to build the project and run the tests
```sh
mvn clean package
```

#### Optionally in the target dir I add record with video Selenium iteration
#### How to Run tests from command line
```sh
mvn test -Dtest=CheckWebPortal#testCase1
mvn test -Dtest=CheckWebPortal#testCase2
mvn test -Dtest=CheckWebPortal#testCase3
```
#### run all Selenium tests
this option disabled

#### Run Api tests
```sh
mvn test -Dtest=CheckRestApi#testCase4_1
mvn test -Dtest=CheckRestApi#testCase4_2
```

