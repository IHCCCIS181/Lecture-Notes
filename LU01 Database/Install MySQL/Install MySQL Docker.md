# MySQL In Docker

We will use different databases thought the class and to keep it easy to manage on your local machines we will be using containerization technology (Podman/Docker). 

## Windows

### Docker Desktop

Before you install you MUST have virtualization enabled at the BIOS level if you don't know how to enable this please do not go into your BIOS alone you can break your machine. [Download Docker Desktop](https://www.docker.com/products/docker-desktop/) allow it to install and reboot. After reboot it will install WSL automatically. 

### Setting up MySQL for Docker

Download the Image 

```
$ docker pull mysql:latest
```

See if it is now downloaded run the following command. You should see a list of images. 

```
$ docker images
```

Now run the following command to install the image into docker. **Make sure** you change the `strong_password` to something you won't forget (mine is just 'root'). You may have to change the port. 

```
$ docker run -d --name test-mysql -e MYSQL_ROOT_PASSWORD=strong_password -p 3306:3306 mysql
```

You should now 

## Linux / MacOS

I use [Podman](https://podman.io/docs/installation) on Fedora since it is a drop in replacement for Docker and it is more FOSS then Docker. Just install it (you can also install a GUI called Podman Desktop). For each command where it says "docker" just put "podman" ie. `docker pull mysql:latest` turns into `podman pull mysql:latest`


## Viewing the DB

You might want to download a tool to view your database. Jetbrains has a GUI tool built in but if you want a standalone tool you can download [MySQL Workbench](https://www.mysql.com/products/workbench/) or [DBeaver Community](https://dbeaver.io/download/). 