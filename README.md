# Band Manager App

#### An app for any bands tour information.

#### By Austin Minnon

## Description

This is an application for tracking a bands past, and upcoming shows. You will also have the ability to view a venues upcoming shows.  

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

Open a new tab in terminal and create the `band_venues` database:
```
$ psql
create database band_venues;
\c band_venues
create table venues (id serial PRIMARY KEY, name varchar, description varchar);
create table bands (id serial PRIMARY KEY, name varchar, description varchar);
create table bands_venues (id serial PRIMARY KEY, band_id int, venue_id int);
create database band_venues_test with template band_venues;

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

Copyright (c) 2016 **Austin Minnon**
