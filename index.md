## Simple Core Java application with Docker Container
Demonstration of execution of core Java application into Docker container.

### Overview:
This document explains step by step how to create docker image and execute it into Docker container. For demonstration purpose, we use simple core Java project which build using Maven tool.

### Technology Stack :

| Technology | Version |
| ------- | ------- |
| Core Java | Adopt Open JDK 11.0 |

### Tools :

| Tool | Version |
| ------- | ------- |
| Maven | Apache Maven 3.5.0 |

### Core Java project with Maven tool:

Command to create project from Maven tool, -
```markdown
mvn archetype:generate
	-DgroupId={project-packaging}
	-DartifactId={project-name}
	-DarchetypeArtifactId={maven-template}
	-DinteractiveMode=false
```

In a terminal Linux OR Mac OR command prompt (Windows), navigate to the folder you want to create the Java project and type above mentioned command. This tells Maven to generate a Java project from a Maven template. For example,

```markdown
mvn archetype:generate -DgroupId=com.ravaan.techky -DartifactId=simple-docker-project -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Docker:
You will get Docker informayion on [<i class="fa fa-external-link"></i>](/docker-documentation/) 

### Dockerfile:
Docker builds images by reading instructions from a Dockerfile. A Dockerfile is a simple text file that contains instructions that can be executed on the command line. Using **docker build** command, you can start a building image with the help of the command-line instructions which is their in the Dockerfile.

Common Dockerfile instructions start with RUN, ENV, FROM, MAINTAINER, ADD, and CMD, among others.

 - **FROM:** Specifies the base image that the Dockerfile will use to build a new image. For this post, we are using phusion/baseimage as our base image because it is a minimal Ubuntu-based image modified for Docker friendliness.
 
- **MAINTAINER:** Specifies the Dockerfile Author Name and his/her email.

- **RUN:** Runs any UNIX command to build the image.

- **ENV:** Sets the environment variables. For this post, JAVA_HOME is the variable that is set.

- **CMD:** Provides the facility to run commands at the start of container. This can be overridden upon executing the docker run command.

- **ADD:** This instruction copies the new files, directories into the Docker container file system at specified destination.

- **EXPOSE:** This instruction exposes specified port to the host machine.

Sample Dockerfile is, - 

```markdown
#Use an existing docker image as base 
FROM adoptopenjdk/openjdk11:ubi

#Create application directory
RUN mkdir /opt/app

# Copy executable jar file into above mentioned application directory
COPY target/simple-docker-project-1.0-SNAPSHOT.jar /opt/app

#Tell the image what to do when it starts as container
CMD ["java", "-jar", "/opt/app/simple-docker-project-1.0-SNAPSHOT.jar"]
```
### Execution

 - Create execution jar with help of Maven tool.

 ```markdown
	#run this command from project directory.
	mvn clean package
 ```

 - Create docker image

 ```markdown
	# '.' represent Dockerfile location.
	docker build .
	
 ```
 **OUTPUT:**
 ```markdown
	Sending build context to Docker daemon    148kB
	 
	Step 1/4 : FROM adoptopenjdk/openjdk11:ubi
	 ---> c7d2a4573c05
	 
	Step 2/4 : RUN mkdir /opt/app
	 ---> Using cache
	 ---> 7b07d3a43816
	 
	Step 3/4 : COPY target/simple-docker-project*.jar /opt/app
	 ---> 5bed2d9b6627
	 
	Step 4/4 : CMD ["java", "-jar", "/opt/app/simple-docker-project-1.0-SNAPSHOT.jar"]
	 ---> Running in edf96b6b5ecf
	 
	Removing intermediate container edf96b6b5ecf
	 ---> 4b491acbcca3
	 
	Successfully built 4b491acbcca3

	SECURITY WARNING: You are building a Docker image from Windows against a non-Windows 
	Docker host. All files and directories added to build context will have 
	'-rwxr-xr-x' permissions. It is recommended to double check and reset 
	permissions for sensitive files and directories.
 ```
 
 - Run docker image
 
 ```markdown
	#copy docker id from docker build command's output.
	docker run 4b491acbcca3
 ```
 
 - Tag docker image

 ```markdown
	#docker tag command use to give meaningful name to above docker image id.
	#copy docker id from docker build command's output.
	docker tag 4b491acbcca3 ravaantechky/simple-java-docker:latest
 ```
 **Note:** Docker image tag name does not have any standards to gave name to image,
 but industry wise it uses docker_hub_account_id/projectname
 
 - Run docker image with tag

 ```markdown
	#copy docker id from above mentioned command output and run docker image
	docker run ravaantechky/simple-java-docker
 ```
 **OUTPUT:**
 ```markdown
	Hello World
 ```

### Post Owner Information:

| Description | Github Source  | Github Profile Link  | LinkedIn Profile Link | Email Address
| -------- | -------- | -------- | -------- |
| Vaishali | [<i class="fa fa-download"></i>](https://github.com/ravaan-techky/simple-java-docker) | [<i class="fa fa-external-link"></i>](https://github.com/ravaan-techky/) | [<i class="fa fa-external-link"></i>](https://www.linkedin.com/in/vaishali-patil-4a6679143/) | [ravaan.techky@gmail.com](mailto:ravaan.techky@gmail.com) |

<br/><br/>
[<i class="fa fa-arrow-left"></i> **Back**](/documentation/)
