### Prepare for Running tests
###### Before run install JDK 1.8 on your pc/workstation and check if it's avalable in some cases you need to download and setup
###### JDK https://www.oracle.com/cis/java/technologies/javase/javase8-archive-downloads.html

JAVA_HOME point to your jdk version

##### for Linux or Mac
##### export JAVA_HOME=/path/to/jdk

#### Check

echo $JAVA_HOME

#### Install or download maven to your PC you need for build/run java projects
#### Add path to maven directory
#### Download maven from https://maven.apache.org/download.cgi
#### Add maven to your $PATH

export PATH=$PATH:/maven-dir

#### where maven-dir you download and unpack maven 

#### Check maven is avalable in the path
mvn

#### For Windows 
setx -m JAVA_HOME "C:\Progra~1\Java\jdk1.8.0_XX"

#### Check if it's correct
echo %JAVA_HOME%

#### Install or download maven to your PC you need for build/run java projects
#### Add path to maven directory
SET PATH=%PATH%;"c:\maven_dir"

#### Check mvn command line should be available
mvn


##### you need to install Docker on your PC/workstation to be able to run Selenium tests
##### Before run tests you need to start docker check if it's up and running
 
docker info

##### Build the project
mvn clean package

#### Optionally in the target dir I add record with video Selenium iteration
#### How to Run tests from command line
mvn test -Dtest=CheckWebPortal#testCase1

##### testCase2 Fill the Form
mvn test -Dtest=CheckWebPortal#testCase2

##### testCase3 is disabled due to Captcha reason

mvn test -Dtest=CheckWebPortal#testCase3

##### run all Selenium tests
this option disabled

##### run Api tests
mvn test -Dtest=CheckRestApi#testCase4_1

mvn test -Dtest=CheckRestApi#testCase4_2

##### run all Api tests
this option is disable
