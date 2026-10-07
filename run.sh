#!/bin/sh
# Compile and run without NetBeans (needs JDK 11+). Put the MySQL connector jar in lib/ first.
cd "$(dirname "$0")"
mkdir -p build/classes
javac -d build/classes -cp "lib/*" src/currency_converter_db/*.java && java -cp "build/classes:lib/*" currency_converter_db.Currency_converter_db
