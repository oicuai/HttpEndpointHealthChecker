# Running the Health Checker

Once running, the health checker will ask for the absolute path of the yaml input file. If the yaml input file exists, the program will start running health checks until the program is exited.
Note: Program not tested on MacOS

## Requirements
JDK 21 installed (see instructions [here](https://docs.oracle.com/en/java/javase/21/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A))

## Option 1: (Only on Windows) Run the executable
1. Download the file "health-checker-windows.exe" in the executables directory
1. If Windows prevents the executable from executing, click "More Info", then click "Run anyways"

## Option 2: Run the jar file HttpEndpointHealthChecker-1.0.0-jar-with-dependencies.jar
1. Navigate to the root directory of the project and run the command ```mvnw clean install``` on Windows, or ```./mnvw clean install``` on Linux/Unix systems.  
1. There should now be a /target directory, which should contain a jar with the name "HttpEndpointHealthChecker-1.0.0-jar-with-dependencies.jar". You should be able to run the program from the terminal with the command ```java -jar HttpEndpointHealthChecker-1.0.0-jar-with-dependencies.jar```

## Option 3: Build with Maven before running jar file
1. This option is for if the previous option doesn't work. Note that this option has an additional requirement of installing Maven. See instructions [here](https://maven.apache.org/install.html).  
1. After Maven is installed, navigate to the root directory of the project and run the command ```mvn clean compile assembly:single```.  
1. Now there should be a /target directory, which should contain a jar with the name "HttpEndpointHealthChecker-1.0.0-jar-with-dependencies.jar". You should be able to run the program from the terminal with the command ```java -jar HttpEndpointHealthChecker-1.0.0-jar-with-dependencies.jar```

