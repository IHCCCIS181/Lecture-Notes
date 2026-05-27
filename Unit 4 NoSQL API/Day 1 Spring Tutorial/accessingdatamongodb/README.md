# Unit 2 Part 3 MongoDB day 1

Today we will follow [this](https://spring.io/guides/gs/accessing-data-mongodb/) tutorial from spring. 


## Download MongoDB Server

### DOCKER / PODMAN

If you are using podman replace the word `docker` with `podman`.

Pull image

```
docker pull mongodb/mongodb-community-server:latest
```

Install 

```
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
```

### Locally Download (DONT DO THIS)

First, we need to download the serve. 
[download link](https://www.mongodb.com/try/download/community)

If you are on linux, make sure you choose the right platform. Redhat for Fedora.

## Download Compass

This is optional but nice to see DB via a GUI. [Download link](https://www.mongodb.com/try/download/compass). 
For Linux, feel free to install this via flatpak or snap.

## Discord moved away from MongoDB

- [mongo](https://www.youtube.com/watch?v=lLrzoyU4BPc)
- [Blog](https://discord.com/blog/how-discord-stores-trillions-of-messages)

## Build the App


## Look at DB via Compass

EOD