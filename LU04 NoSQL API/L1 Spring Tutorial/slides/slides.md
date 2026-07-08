---
marp: true
theme: default
paginate: true
size: 16:9
title: NoSQL with MongoDB
description: What MongoDB is, how it works, and how it compares to SQL
---

# NoSQL with MongoDB

Love Databases but hate SQL?

Luke - Java 3

---

## What Is NoSQL?

- NoSQL means "Not Only SQL"
- It is a group of non-relational database models
- Common types:
    - Document (MongoDB)
    - Key-value (Redis)
    - Wide-column (Cassandra)
    - Graph (Neo4j)

MongoDB is a **document database**.

---

## MongoDB Basics

- Data is stored as **documents** (JSON-**like** BSON)
- Documents are grouped into **collections**
- One database can have many collections
- Each document has a unique `_id`

Think of it like:

- SQL table row -> MongoDB document
- SQL table -> MongoDB collection

---

## SQL vs MongoDB Data Model

| SQL (Relational)          | MongoDB (Document)           |
| ------------------------- | ---------------------------- |
| Tables                    | Collections                  |
| Rows                      | Documents                    |
| Columns with fixed schema | Fields with flexible schema  |
| JOIN for relationships    | Embed or reference documents |
| Strong normalization      | Often denormalized for speed |

---

## Short Example: SQL Table Row

```sql
CREATE TABLE students (
	id INT PRIMARY KEY,
	name VARCHAR(100),
	major VARCHAR(50),
	gpa DECIMAL(3,2)
);

INSERT INTO students (id, name, major, gpa)
VALUES (1, 'Ana', 'CS', 3.80);
```

---

## Short Example: MongoDB Document

```json
{
    "_id": 1,
    "name": "Ana",
    "major": "CS",
    "gpa": 3.8
}
```

```javascript
db.students.insertOne({
    _id: 1,
    name: 'Ana',
    major: 'CS',
    gpa: 3.8,
});
```

---

## Query Example: SQL vs MongoDB

```sql
SELECT name, gpa
FROM students
WHERE gpa >= 3.5;
```

```javascript
db.students.find({ gpa: { $gte: 3.5 } }, { name: 1, gpa: 1, _id: 0 });
```

---

## Pros of MongoDB

- Fast development with flexible schema
- Good for JSON APIs and modern web apps
- Easy to scale horizontally (sharding)
- Handles large volumes of semi-structured data
- Good performance for read-heavy workloads

---

## Cons of MongoDB

- Less strict schema can cause inconsistent data
- Complex joins are harder than in SQL systems
- Data duplication can increase storage usage
- Transactions exist, but relational DBs are often simpler for highly relational data
- Query design requires careful indexing

---

## Why MongoDB Is Popular

- Matches how developers work with JSON in JavaScript/Node/Spring APIs
- Quick iteration for changing requirements
- Strong cloud ecosystem (MongoDB Atlas)
- Scales well for high-traffic applications
- Large community, docs, and tooling

---

# Install Time
