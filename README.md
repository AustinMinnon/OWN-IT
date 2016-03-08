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
\c own_it
create table categories (id serial PRIMARY KEY, name varchar);
create table sports (id serial PRIMARY KEY, name varchar);
create table tricks (id serial PRIMARY KEY, name varchar, rating int, date varchar, category_id int, user_id int, sport_id int);
create table users (id serial PRIMARY KEY, name varchar);
create database own_it_test with template own_it;

```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```
## Known Bugs
no known bugs.

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
