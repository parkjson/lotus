# Lotus: Anti Social Media API

## Background 

Recent degeneration of social media leaves demand for a less monetized alternative. Lotus is a micro-blogging messaging app's backend that manages persisted information. Any user can see all of the messages posted to the site, or they can see the messages posted by a particular user. Incldes logins, registrations, message creations, message updates, and message deletions.

## Database Tables 

The ConnectionUtil class runs the sql script.

### Account
```
account_id integer primary key auto_increment,
username varchar(255),
password varchar(255)
```

### Message
```
message_id integer primary key auto_increment,
posted_by integer,
message_text varchar(255),
time_posted_epoch long,
foreign key (posted_by) references Account(account_id)
```

# Requirements

1: User registration body creates account_id persisting to db if response status 200
2: Verify login for session token 
3: Create messages by user and validate user
4: GET retrieval of all messages
5: GET retrieval of message_id
6: DELETE message_id
7: PATCH message_id after fulfilling reqs
8: GET retrieve messages account_id