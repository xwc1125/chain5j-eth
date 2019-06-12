# chain5j-eth

[![](https://jitpack.io/v/xwc1125/chain5j-eth.svg)](https://jitpack.io/#xwc1125/chain5j-eth)

Base on web3j-java.

## To get a Git project into your build:

* Maven:

    - Step 1. Add the JitPack repository to your build file

    ```
    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	```

    - Step 2. Add the dependency

    ```
	<dependency>
	    <groupId>com.github.xwc1125.chain5j-eth</groupId>
	    <artifactId>eth</artifactId>
	    <version>Tag</version>
	</dependency>
	```
	
* Gradle:

    - Step 1. Add the JitPack repository to your build file

    ```
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    ```
    
    - Step 2. Add the dependency
    
    ```
	dependencies {
	        implementation 'com.github.xwc1125.chain5j-eth:eth:4.2.0'
	}
    ```
    
    