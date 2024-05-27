## 1. Generate:
```shell
$ protoc \
--plugin=protoc-gen-grpc-java=/Users/hiepnq/Working/tools/grpc-java-1.64.0 \
--grpc-java_out=./out \
--proto_path=./ auth.proto
```
```shell
$ protoc --java_out=./out -I./ auth.proto
```