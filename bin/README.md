# How to run:

1. Execute `executeToCreateDB.sql` query in MySQL (or another) DBMS. 
2. Define the connection attributes in the DBManager class.

You can find it here - src/main/java/mbuchatskyi/database/connection/DBManager.

Here is an example:
```
private static final String URL = "jdbc:mysql://localhost:3306/equations";
private static final String ACCESS = "?user=root&password=root";
```
3. Run `MainApplication.java` as Java application.