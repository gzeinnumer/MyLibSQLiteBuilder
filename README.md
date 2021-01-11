<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibUtils/blob/master/preview/bg.jpg" width="400"/>
</p>

<h1 align="center">
    MyLibUtils
</h1>

<p align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Koltin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple function for <b>Date</b> and <b>String</b>.</p>
</p>

---
## Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:MyLibSQLiteBuilder:version'
}
```

## Feature List
- [x] **Convert Time Format.** example from `2020-10-14` to `14-10-2020`. ([docs](#convert-time-format))

## Tech stack and 3rd library
- SimpleDateFormat ([docs](https://developer.android.com/reference/java/text/SimpleDateFormat))

---
## USE

#### Make Class Table.

Example : Make class `Table1` and put your query **`CREATE TABLE table1 VALUE (...);`** to annotation `@CreateTableQuery(query = { ... })`.
> **Java**
```java
@CreateTableQuery(
    query = "CREATE TABLE table1 (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, rating REAL, descr TEXT, flag_active INTEGER, created_at TEXT)"
)
public class Table1{

    //your code

}
```

#### Make Instance Class `DBInstance` extends `SQLiteBuilder`
```java
public class DBInstance extends SQLiteBuilder {
}
```

### Define your Table Class on `@SQLiteDatabaseEntity( entities = { ... } )`
```java
@SQLiteDatabaseEntity(entities = {
        Table1.class
})
public class DBInstance extends SQLiteBuilder {

   ...

}
```

#### Make function instance in `DBInstance`.
```java
@SQLiteDatabaseEntity(entities = {
        Table1.class
})
public class DBInstance extends SQLiteBuilder {

    public static String DB_NAME = "MyLibSQLiteSimple.db";

    public static SQLiteDatabase getDataBase(Context context) {
        db = SQLiteBuilder.builder(DBInstance.class, context)
                .setDatabaseName(DB_NAME)
                .setDatabaseVersion(1)
                .build();
        return db;
    }

}
```

#### Usage
After you have defined the entity and make function `getDataBase(Context context)`, you can use the following code to create an instance of the database:
```java
SQLiteDatabase database = DBInstance.getDataBase(getApplicationContext());
```

#### Database and table created
<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibSQLiteBuilder/blob/master/preview/example1.jpg"/>
</p>
<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibSQLiteBuilder/blob/master/preview/example4.jpg" width="400"/>
</p>

---
### File Database On External

If you want to **PUT** your database `file` on **External** you can add and use function `backUpDatabaseToExternal(DB_PATH_BC)` in `SQLiteBuilder.builder(DBInstance.class, context)`.
```java
public static SQLiteDatabase getDataBase(Context context) {
    String DB_PATH_BC = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteBC/MyLibSQLiteSimple.db";

    db = SQLiteBuilder.builder(DBInstance.class, context)
            ...
            .backUpDatabaseToExternal(DB_PATH_BC)
            ...
            .build();
    return db;
}
```
File Database will be create on your folder Path.
<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibSQLiteBuilder/blob/master/preview/example2.jpg" width="400"/>
</p>

---
### Load Database From External

If you want to **Load** your database `file` **From External** you can add and use function `loadDatabaseFromExternal(DB_PATH_EXTERNAL)` in `SQLiteBuilder.builder(DBInstance.class, context)`.
```java
public static SQLiteDatabase getDataBase(Context context) {
    String DB_PATH_EXTERNAL = Environment.getExternalStorageDirectory().toString() + "/MyLibSQLiteExternal/MyLibSQLiteSimple.db";

    db = SQLiteBuilder.builder(DBInstance.class, context)
            ...
            .loadDatabaseFromExternal(DB_PATH_EXTERNAL)
            ...
            .build();
    return db;
}
```
File database will be **Load** from your `Database Path`.
<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibSQLiteBuilder/blob/master/preview/example3.jpg" width="400"/>
</p>

**Warning this method will ignore proses create `new Table`**



---

### Version
- **1.0.0**
  - First Release

---

### Contribution
You can sent your constibution to `branche` `open-pull`.

---

```
Copyright 2020 M. Fadli Zein
```
