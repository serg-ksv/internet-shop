# Internet-shop

# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer)
* [Author](#author)

## <a name="purpose"></a>Project purpose

This project is a simple version of internet shop.

<hr>

It has the following functions:
- Login and registration forms.
- Shopping cart and order services
- Two roles: User and Admin

Functions for users with an ADMIN role only:
- add product to the store
- delete products from the store
- view a list of all users
- delete users from the store
  
Functions for users with a USER role only: 
- add products to user's shopping cart
- delete products from user's shopping cart
- view all user's orders
- complete orders

<hr>

## <a name="structure"></a>Project structure

- Java 11
- Maven 4.0.0
- javax.servlet-api 3.1.0
- jstl 1.2
- log4j 1.2.17
- mysql-connector-java 8.0.20
- maven-checkstyle-plugin 3.1.1

## <a name='developer'></a>For developer

To run this project you need to install:

- <a href="https://www.oracle.com/java/technologies/javase-jdk11-downloads.html">Java 11</a> or above
- <a href="https://tomcat.apache.org/download-90.cgi">Tomcat</a>
- <a href="https://www.mysql.com/downloads/">MySQL 8</a>

<hr>

Add this project to your IDE as Maven project.

Add Java SDK in project structure.

Configure Tomcat:
- Add artifact
- Add Java SDK

Change a path to your Log file in **src/main/resources/log4j.properties** on line 9.

<hr>

To work with MySQL you need to:
- Use file **src/main/resources/init_db.sql** to create schema and all the tables required by this app in MySQL DB
- Change username and password to match with MySQL in **src/main/java/com/ksv/internetshop/util/ConnectionUtil.java** 
class on 19-20 lines.

<hr>

Run the project. <br>
Register and log in with your login and password. <br>
On the home page, click once "Inject test data into the DB".
By default, two users will be generated:
- with an ADMIN role (login = "admin", password = "admin")
- with a USER role (login = "bob", password = "1234")

<hr>

## <a name='author'></a>Author
[Serhii Kinashchuk](https://github.com/serg-ksv)
