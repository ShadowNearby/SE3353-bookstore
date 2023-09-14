#!/usr/bin/env bash
find ../ -name "*.zip" -exec rm -r {} +
zip -r -q "../src-$(date +%Y-%m-%d).zip" ../frontend/* ../src ../pom.xml ../demo.imi ../mvnw ../bookstore.imi -x '../frontend/node_modules/*'