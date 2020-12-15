<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" alt="Logo" width="200" height="200">
  </a>

  <h3 align="center">Spring Batch</h3>

  <p align="center">
    Application to demonstrate Spring Batch
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

The main goal is show how to import data from a comma delimited file to a Postgres Database.

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Flyway](https://flywaydb.org/)
* [PostgreSQL](https://www.postgresql.org/)



<!-- GETTING STARTED -->
## Getting Started

Let's see how to start use

### Prerequisites

You will need a Windows or Linux with Java/OpenJDK environment and a PostgreSQL server running.

The text file person to import must be tree fields separated with comma as follow:
<ID>,<NAME>,<CPF>

### Installation

Let's focus on Linux instalation, after all, Windows is just next, next, next... ;-)

1. Install OpenJDK 8 or newer
   ```aptget install ....
   ```
2. Install PostgreSQL
   ```aptget install ....
   ```
3. Create a diretory and copy JAR_FILE and the file with persons to import 
   

<!-- USAGE EXAMPLES -->
## Usage

To run just type the command below at your terminal:

`java -jar <JAR_FILE> <TEXT_FILE>`

Ex:`java -jar target/ImportCpf-0.0.1-SNAPSHOT.jar person.txt`

### Run with docker
docker run -v /tmp/list-cpf.txt:/cpfs.txt -v /var/run/docker.sock:/var/run/docker.sock projetquebec/projetquebec:0.0.1-SNAPSHOT cpfs.txt

#### dockerhub
https://hub.docker.com/repository/docker/projetquebec/projetquebec


Created by Ricardo Ribeiro