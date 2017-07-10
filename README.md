# Overview

Parent project that holds module projects for the persistence of message data.

The project holds backend data for simple message management.

# ERD-Diagramm

The erd-diagramm for this database looks as follows: ![erd-diagramm](https://raw.githubusercontent.com/lightblueseas/message-system-data/develop/message-system-init/src/main/resources/erd/message-system-erd.jpa.png)

This erd-diagramm was created with [Jeddict plugin from netbeans as JPA Modeler](http://plugins.netbeans.org/plugin/53057/jpa-modeler)  and [Jeddict](https://jeddict.github.io/)

## License

The source code comes under the liberal MIT License, making message-system-data great for all types of applications that want to send and persist messages.

# Build status
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6846d666041c4848b3ab2b49f90feb95)](https://www.codacy.com/app/tatjana19/message-system-data?utm_source=github.com&utm_medium=referral&utm_content=lightblueseas/message-system-data&utm_campaign=badger)
[![Build Status](https://travis-ci.org/lightblueseas/message-system-data.svg?branch=master)](https://travis-ci.org/lightblueseas/message-system-data)

## Maven Central

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/message-system-data/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/message-system-data)

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~message-system-data~~~) for latest snapshots and releases.

Add the following maven dependencies to your project `pom.xml` if you want to import the core functionality:

You can first define the version properties:

	<properties>
			...
		<!-- message-system-data version -->
		<message-system-data.version>3.11.0</message-system-data.version>
		<message-system-business.version>${message-system-data.version}</message-system-business.version>
		<message-system-domain.version>${message-system-data.version}</message-system-domain.version>
		<message-system-entities.version>${message-system-data.version}</message-system-entities.version>
		<message-system-init.version>${message-system-data.version}</message-system-init.version>
		<message-system-rest-api.version>${message-system-data.version}</message-system-rest-api.version>
		<message-system-rest-client.version>${message-system-data.version}</message-system-rest-client.version>
		<message-system-rest-web.version>${message-system-data.version}</message-system-rest-web.version>
			...
	</properties>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-business:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-business</artifactId>
				<version>${message-system-business.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-domain:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-domain</artifactId>
				<version>${message-system-domain.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-entities:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-entities</artifactId>
				<version>${message-system-entities.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-init:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-init</artifactId>
				<version>${message-system-init.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-rest-api:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-rest-api</artifactId>
				<version>${message-system-rest-api.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-rest-client:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-rest-client</artifactId>
				<version>${message-system-rest-client.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of message-system-rest-web:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>message-system-rest-web</artifactId>
				<version>${message-system-rest-web.version}</version>
			</dependency>
			...
		</dependencies>
		 
## Open Issues
[![Open Issues](https://img.shields.io/github/issues/astrapi69/message-system-data.svg?style=flat)](https://github.com/astrapi69/message-system-data/issues) 

## Want to Help and improve it? ###

The source code for message-system-data are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [lightblueseas/message-system-data/fork](https://github.com/lightblueseas/message-system-data/fork)

To share your changes, [submit a pull request](https://github.com/lightblueseas/message-system-data/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer

Do not hesitate to contact the message-system-data developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/lightblueseas/message-system-data/issues).

## Note

No animals were harmed in the making of this library.

# Donate

If you like this library, please consider a donation through 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fmessage-system-data" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>
