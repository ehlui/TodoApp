# Context xml strcucture

```
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Resource name="jdbc/todoappDB" type="javax.sql.DataSource"
              maxActive="100" maxIdle="30" maxWait="5000"
              url="jdbc:mysql://db:3306/Tasks"
              driverClassName="com.mysql.cj.jdbc.Driver"
              username="USER" password="PASSWORD" />
</Context>
```

- type 
    - For connection polling we gotta use DataSource (JEE)
- url (Same as in JDBC connections)
     - **jdbc:mysql** Protocol and subprotocol we're using 
     - **db** stands for the service name defined by our docker-compose
     - **Tasks** is our Database
- **driverClassName** 
  - It should be the latest or the one we use in our webapp.
