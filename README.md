## Prepare for Running tests
### Before run install JDK 1.8 on your pc/workstation and check if it's avalable in some cases you neet to set
JAVA_HOME point to your jdk version

###for Linux or Mac
export JAVA_HOME=/usr/bin

###Check
echo $JAVA_HOME

###Install or download maven to your PC you need for build/run java projects
###Add path to maven directory
###Add maven to your $PATH

export PATH=$PATH:/maven-dir

### where maven-dir you download and unpack maven 

###Check maven is avalable in the path
mvn

##For Windows 
setx -m JAVA_HOME "C:\Progra~1\Java\jdk1.8.0_XX"

###Check if it's correct
echo %JAVA_HOME%

###Install or download maven to your PC you need for build/run java projects
###Add path to maven directory
SET PATH=%PATH%;"c:\maven_dir"

###Check mvn command line should be available
mvn


## you need to install Docker on your PC/workstation to be able to run Selenium tests
### Before run tests you need to start docker check if it's up and running

docker info
## How to Run tests from command line
mvn test -Dtest=TestWebPortal#testCase1

### testCase3 is disabled due to Captcha reason

mvn test -Dtest=TestWebPortal#testCase3
### run all Selenium tests
mvn test -Dtest=TestWebPortal

### run Api tests
mvn test -Dtest=TestRestApi#testCase4_1

mvn test -Dtest=TestRestApi#testCase4_2

### run all Api tests
mvn test -Dtest=TestRestApi