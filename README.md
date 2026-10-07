# Currency Converter System  (Java OOP II + Database Programming)

Java Swing desktop app + MySQL database, built in the same style as the `rabbit_farm_db`
NetBeans project (Login -> Menu -> forms with Save / Delete / Find / First / Next / Last /
Preview PDF / Exit).

## 1. Database
Open `sql/schema.sql` in MySQL Workbench and run it (lightning-bolt button).
It creates the `currency_converter` database: **16 tables, normalised to 2NF**, with sample data.
(`ERD.md` has the diagram and the 1NF -> 2NF write-up.)

## 2. Connection settings
Edit the three values at the top of `src/currency_converter_db/DBConnection.java`
(URL, user, password) - defaults are `root` with an empty password.
The driver loaded is **`com.mysql.cj.jdbc.Driver`** (Connector/J 8.x).

## 3. Libraries
Put `mysql-connector-j-8.x.jar` in `lib/` (see `lib/PUT_MYSQL_CONNECTOR_HERE.txt`).
`itextpdf-5.5.5.jar` (PDF reports) is already in `lib/`.

## 4. Run
* **NetBeans:** File > Open Project > `currency_converter_db`, then Run (F6).
  If the connector jar has a different version, right-click *Libraries > Add JAR/Folder*.
* **Without NetBeans:** `run.bat` (Windows) or `./run.sh` (Linux/Mac).

## Logins
| user | password | role |
|---|---|---|
| admin | Admin@1234 | ADMIN - every screen incl. Users, Roles, Logs |
| demo_user | Demo@1234 | USER - converter, own history/favorites/alerts, read-only reference data |

New accounts can be made with **Register** (they get the USER role). Passwords are stored
as salted PBKDF2 hashes, never plain text.

## Screens
* **Convert Currency** - pick currencies + amount, optional fee, result, receipt PDF,
  add pair to favorites, set a rate alert (alert fires when a conversion reaches the target).
  Rate lookup order: direct rate -> inverse rate -> cross via USD.
* **Master/CRUD forms (all 16 tables)** - Countries, Currencies, Rate Sources, Currency Pairs,
  Exchange Rates, Rate History, Fee Structures, Favorites, Alerts, Conversions,
  Users, Roles, User Sessions, Transaction Log, Audit Trail, Error Log.
  Each has New, Save, Delete, Find, First/Prev/Next/Last, Preview PDF (saved in `reports/`), Exit.
* Every insert/update/delete/login/logout is written to `audit_trail`; failures go to `error_log`;
  each conversion goes to `transaction_log` and `rate_history`; each login/logout to `user_sessions`.

## Code map
| file | job |
|---|---|
| `DBConnection` | JDBC connection (`com.mysql.cj.jdbc.Driver`) |
| `RateService` | business rules for conversion (rates, fees) |
| `*Form.java` + `*Form.form` | **NetBeans drag-and-drop forms**: Login, Register, Menu, Converter and one per table (16). Open any `.form` in NetBeans and use the Design tab |
| `CrudController`, `CrudForms`, `TableSpec`, `Col`, `Specs` | shared behaviour behind the 16 table forms (load, Save, Delete, Find, navigate, PDF) and a table-to-form lookup |
| `PdfUtil` | iText PDF reports / receipt |
| `Session`, `AppLog`, `Security` | current user, audit/error/transaction logging, password hashing |
| `test/` | smoke + GUI tests used to verify the project |

## Tested
Run against a live MySQL-compatible server: schema loads (16 tables), login/hash check,
direct/inverse/cross rate conversion, fee clamping, CRUD insert/update/delete on forms,
validation, PDF creation, role restrictions, audit/transaction logging.

## Editing the forms in NetBeans
Every screen is a real Matisse form (`Xxx.form` + `Xxx.java`). In the Design tab you can move components, change text/colours and add buttons; double-click a button to write its handler. Do not edit code inside the grey *Generated Code* block. The table forms' fields are tied to the table description in `Specs.java` (one input per column, same order), so if you add a column to a table, add it to `Specs.java` and drop the matching label + field on that form.
