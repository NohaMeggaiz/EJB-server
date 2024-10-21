# EJB Server Project

This project contains an Enterprise JavaBeans (EJB) server implementation using WildFly. It is designed to showcase how to deploy an EJB on a WildFly server and interact with it via JNDI.


## Project Structure

The repository follows a standard Java project structure. Below is a breakdown of the project components:

- `src/main/java/org/example/`: Contains the EJB implementation.
  - `Hello.java`: Interface for the EJB, defines the business methods that the EJB exposes.
  - `HelloImpl.java`: Implements the `Hello` interface as a stateless EJB.

## Prerequisites

Before setting up the project, ensure you have the following tools installed:

- **Java Development Kit (JDK) 17 or later
- **Maven 3.6.0 or later
- **WildFly 31.0.1.Final ( any version above 27 will be compatible with jdk 17  )
## Setup

### Step 1: Clone the Repository
### Step 2: setup wildfly on your machine , here i used intllij idea .
### step 3: configure the admin setup in wildfly . you will endup getting a username + pass . refre to wildfly doc for this . 
### step 4 : run mvn clean package , this should generate a .jar file that you need to deploy to you wildfly deployement => you can ask chat gpt how to do that in intllij idea 
### step 5 : run the project + you should see something like this in your server logs 
`WFLYEJB0473: JNDI bindings for session bean named 'HelloImpl' in deployment unit 'deployment "EJB-1.0-SNAPSHOT.jar"' are as follows:

	java:global/EJB-1.0-SNAPSHOT/HelloImpl!org.example.Hello
	java:app/EJB-1.0-SNAPSHOT/HelloImpl!org.example.Hello
	java:module/HelloImpl!org.example.Hello
	java:jboss/exported/EJB-1.0-SNAPSHOT/HelloImpl!org.example.Hello
	ejb:/EJB-1.0-SNAPSHOT/HelloImpl!org.example.Hello
	java:global/EJB-1.0-SNAPSHOT/HelloImpl
	java:app/EJB-1.0-SNAPSHOT/HelloImpl
	java:module/HelloImpl
`
now we need to setup the client :  refere to my other repo : https://github.com/MINAWI0/EJB_CLIENT_WILDFLY





