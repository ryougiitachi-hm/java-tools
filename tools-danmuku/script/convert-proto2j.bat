@echo off
rem protoc --experimental_allow_proto3_optional -I=SRC_DIR --java_out=src/main/java src/main/resources/protobuf/*.proto
protoc --java_out=src/main/java src/main/resources/protobuf/*.proto

