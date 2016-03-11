# "OWN IT"

#### Trick tracking application for extreme sports.

#### Contributors: Austin Minnon, Josh Gustafson, Kyle Derrick, Michael Schennum

## Description

This is an application for recording tricks. Each user has their own trick lists, that one can personalize. The more a user records, the more progress they are able to track.   

## Setup/Installation Requirements

Clone this repository:
```
$ cd ~/Desktop
$ git clone github address
$ cd folder-name
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and run this:
```
$ psql
create database own_it;
psql own_it < own_it.sql

```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```
## Known Bugs
*This application stores each user in a session; therefore navigating url's manually WILL BREAK, and result in a 500 error*

## Technologies Used

* Java
* junit
* Gradle
* Spark
* fluentlenium
* PostgreSQL
* Bootstrap

### License

Licensed under the GPL.

Copyright (c) 2016 **Austin Minnon, Josh Gustafson, Kyle Derrick, Michael Schennum**
