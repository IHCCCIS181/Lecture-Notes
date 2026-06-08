You need to build a .env file and configure your run config


<!-- TODO rebuild L2 and then back port it to L1 so they line up better for more code reuse for L2. Make it kind of part 1 and 2 -->
<!-- TODO actually use the passed in DBname and table names -->
<!-- Rename the interface -->
<!-- Handle the Major field better -->
<!-- Remove Gender replace with a enum of something like dorm student or something -->

config file

```env
DB_URL=jdbc:mysql://localhost:3306/
DB_USERNAME=root
DB_PASSWORD=root
```

## Temp

```java
ArrayList<Graduate> people;
people = new ArrayList<>();
Gson gson = new Gson();
Type listType = new TypeToken<List<Graduate>>() {}.getType();
try {
    people = gson.fromJson(new FileReader("src/main/java/com/example/service/Students.json"), listType);
} catch (Exception ex) {
    System.err.println("Parsing error: " + ex.getMessage());
}
for(Graduate g : people){
    System.out.println(g);
    System.out.println(g.getName());
    System.out.println(g.getAge());
}
```

```java
        try{
            conn = DriverManager.getConnection(url + dbName, user, pass);
            System.out.println("Connected to new database.");
        }catch(SQLException e) {
            System.err.println("Cannot connect to new database.");
            System.err.println(e.getMessage());
        }
```

