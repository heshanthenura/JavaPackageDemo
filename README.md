# <h1 align="center">How To Package JavaFX Application</h1>


Discover how to package and distribute your JavaFX application seamlessly to clients or users, eliminating the need for them to pre-install the Java SDK

## Menu
### 1. [Prerequisites](#1-prerequisites-1)
### 2. [How to package and distribute](#2-how-to-package-and-distribute-1)
- ### [Windows](#21-windows)
- ### [Linux](#22-linux)
- ### [MacOS](#23-macos)

## 1. Prerequisites

### Read Below before start

* ### To proceed smoothly, ensure you have `Gradle` set up and configured for your project
* ### If Gradle isn't utilized, the process requires manually assembling a functional `JAR` file containing all essential dependencies, external JARs, as well as crucial assets and resources. This involves carefully bundling these components together to ensure the application operates seamlessly

### Make another java file to launch you JavaFX application

If your [main JavaFX application class](https://github.com/heshanthenura/JavaPackageDemo/blob/master/src/main/java/com/heshanthenura/packagedemo/MainApplication.java) is named ```MainApplication``` as seen here, create another Java file named [Launcher.java](https://github.com/heshanthenura/JavaPackageDemo/blob/master/src/main/java/com/heshanthenura/packagedemo/Launcher.java) and include the following code:
```
public class Launcher {
    public static void main(String[] args) {
        MainApplication.main(args);
    }
}
```
This [Launcher.java](https://github.com/heshanthenura/JavaPackageDemo/blob/master/src/main/java/com/heshanthenura/packagedemo/Launcher.java) file serves as an entry point to your JavaFX application

### Include the following code snippet in your [build.gradle](https://github.com/heshanthenura/JavaPackageDemo/blob/master/build.gradle) file to generate a standalone FatJar for your project

```
task customFatJar(type: Jar) {
    manifest {
        attributes 'Main-Class': 'com.heshanthenura.packagedemo.Launcher'
    }
    archiveBaseName = 'PackageDemoJAR'
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from { configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) } }
    with jar
}
```
| Attribute                                                                                      | Description                                                                                                                                                                                                             |
|------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ```task customFatJar(type: Jar)```                                                             | Defines a new task named customFatJar of type Jar, indicating that this task will generate a JAR file                                                                                                                   |
| ```manifest { attributes 'Main-Class': 'com.heshanthenura.packagedemo.Launcher' }```           | Sets the Main-Class attribute in the JAR's manifest file to point to the Launcher class, which serves as the entry point of your application                                                                            |
| ```archiveBaseName = 'PackageDemoJAR'```                                                       | Specifies the base name for the generated JAR file as ```PackageDemoJAR```                                                                                                                                              |
| ```duplicatesStrategy = DuplicatesStrategy.EXCLUDE```                                          | Handles duplicate files encountered during the JAR creation process by excluding them                                                                                                                                   |
| ```from { configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) } }``` | Collects all dependencies from the runtime classpath and includes them in the JAR. ```configurations.runtimeClasspath``` gathers the project's runtime dependencies, and ```zipTree``` adds each dependency to the JAR. |
| ```with jar```                                                                                 | Includes the existing JAR file (if any) generated by the default ```jar``` task along with the assembled JAR containing dependencies                                                                                    |

This ```customFatJar``` task amalgamates your project's code and its dependencies into a single JAR file.Upon execution, the generated JAR file will be located in the following path: ```build/libs```

To validate the functionality of your JAR file, execute it using the command: ```java -jar <path to your JAR file>```. This command verifies the proper execution of your Java application encapsulated within the JAR

### Once all prerequisites are fulfilled, we can proceed with the packaging of the application

## 2. How to package and distribute
### To successfully package and distribute the application, leveraging the ```jpackage``` utility becomes essential for streamlining the process

### find ```jpackage``` documentation [here](https://docs.oracle.com/en/java/javase/14/docs/specs/man/jpackage.html)

```jpackage``` is generally included as part of the JDK starting from Java 14. However, in certain cases or older versions where it might not be bundled or available by default, you can manually download the JDK that includes ```jpackage``` from the official [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/) website.
Ensure you download a JDK version that explicitly mentions support for ```jpackage```. After downloading and installing the JDK, you should have access to the jpackage tool in the JDK's bin directory

To confirm whether ```jpackage``` is installed on your system, run the command ```jpackage --help``` on CMD or Terminal. If installed, this command will display the help information for the ```jpackage``` utility, confirming its presence and functionality.
### 2.1 Windows
#### For Windows, it's necessary to install the [WiX Toolset](https://wixtoolset.org/docs/wix3/), version 3.0 or later
After completing the prerequisites, execute the following command
```shell
jpackage --input <direcory of jar file> --name <name> --main-jar <main jar file > --main-class <main class> --type <type> --win-dir-chooser
```
```
Example command
jpackage --input build/ --name PackageDemo --main-jar <mPackageDemoJAR.jar > --main-class <com.heshanthenura.packagedemo.Launcher> --type msi --win-dir-chooser
```

| Attribute                         | Description                                                                                                                                         |
|-----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| ```jpackage```                    | Invokes the ```jpackage``` tool, used for packaging Java applications                                                                               |
| ```--input <path to jar file>```  | Specifies the input directory containing the application files to be packaged. In this case, it's the target/ directory                             |
| ```--name <name>```               | Sets the name for the packaged application as you like                                                                                              |
| ```--main-jar <main jar file >``` | Specifies the main JAR file related to directory where it's located of the application.                                                             |
| ```--main-class <main class>```   | Defines the main class of the application, specifying the entry point for execution. specify what we enter as main class in ```customFatJar``` task |
| ```--type <msi / exe>```          | Specifies the type of package to create, in this instance, a ```exe``` or ```msi``` for Windows. Recommended to use type as ```msi```               |
| ```--win-dir-chooser```           | This enables users to choose where they want to install the application on their Windows system                                                     |                                                                                                                                               |

After generating the MSI (Microsoft Installer) file using this command, you can effortlessly share it with users or clients, ensuring a hassle-free installation without concerns about Java Runtime Environment (JRE) or other dependency errors

### 2.2 Linux
#### Updating soon
### 2.3 MacOS
#### Updating soon


### If you're interested in custom application development or would like to discuss a project, please don't hesitate to contact me.

<ul>
    <li><a href="https://twitter.com/Heshantk">Twitter</a></li>
    <li><a href="https://www.linkedin.com/in/heshanthenura">LinkedIn</a></li>
    <li><a href="https://www.instagram.com/heshan_thenura/">Instagram</a></li>
    <li><a href="https://youtube.com/@heshanthenura">YouTube</a></li>
    <li><a href="https://www.tiktok.com/@heshanthenura">TikTok</a></li>
</ul>

### [E-Mail](mailto:heshanthenura@protonmail.com) heshanthenura@protonmail.com