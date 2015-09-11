# Doggie Dating Website

##### Create a dating profile for your dog, September 11, 2015

#### By Morgan Lutz, Yvonne Peng, Jocilyn Alsdorf, Teresa Fletcher

## Description

* Users create, read, update, and delete entries for both dogs and owners.
* Many to many relationship exists between dogs ands interests.

## Database Design
![Database image](https://raw.githubusercontent.com/morganlutz/doggie-dating-website/master/src/main/resources/public/img/SQL-ScreenShot-2015-09-10.png)

=======

## Site home screenshot

![Screenshot of site] (https://raw.githubusercontent.com/morganlutz/doggie-dating-website/master/src/main/resources/public/img/readme_scrennshot.png)
## Setup

Clone this repository:
```
$ cd ~/Desktop
$ git clone https://github.com/morganlutz/doggie-dating-website.git
$ cd doggie-dating-website/
```
=======

Open a second tab in terminal. Run postgres. Open a third tab. Run psql. In psql, create your database 'doggie_dating'.


```
$USER=# CREATE DATABASE doggie-dating;
```
Switch back to bash (first tab) to populate your database:
```

$ psql doggie-dating < doggie-dating.sql
```
Run gradle to start the program:
```
$ gradle run
```

## Technologies Used
* Java
* Spark
* SQL
<<<<<<< HEAD
* Testing with JUnit, FluentLenium, and Gradle
<<<<<<< HEAD

### Legal

Copyright (c) 2015 Morgan Lutz, Yvonne Peng, Jocilyn Alsdorf, Teresa Fletcher
=======
* bootstrap

### Legal

Copyright (c) 2015 Morgan Lutz, Yvonne Peng, Jocelyn Alsdorf, Teresa Fletcher
>>>>>>> 75c9480ed1dd93c8805dff5ef1100d7852eae91c
=======
* Bootsrap
* Apache Velocity
* Testing with JUnit, FluentLenium, and Gradle

### Legal

Copyright (c) 2015 Morgan Lutz, Yvonne Peng, Jocelyn Alsdorf, & Teresa Fletcher
>>>>>>> d56e8f3f536e00123441d7a94b98a9122491cc05

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
