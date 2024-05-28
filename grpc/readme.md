## * Compile grpc-java:
- download protoc-gen-java at *https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java*
```shell
$ cd $GRPC_JAVA_HOME
$ nano gradle.properties
skipAndroid=true
skipCodegen=true

$ ./gradlew build
```
## 1. Generate directly:
- ref: *https://yidongnan.github.io/grpc-spring-boot-starter/en/server/getting-started.html*
```shell
$ protoc \
--plugin=protoc-gen-grpc-java=/Users/hiepnq/Working/tools/grpc-java-1.64.0 \
--grpc-java_out=./out \
--proto_path=./ auth.proto
```
```shell
$ protoc --plugin=protoc-gen-grpc-java=/Users/hiepnq/Working/tools/protoc-gen-grpc-java-1.64.0-osx-aarch_64.exe \
--grpc-java_out=../demo-communication-web/src/generated/main/grpc \
--java_out=../demo-communication-web/src/generated/main/java \
-I./ auth.proto
```

## 3. Gradle config:
```groovy
plugins {
    id "java"
    id "org.springframework.boot" version "${springbootVersion}"
    id "io.spring.dependency-management" version "${springDependencyVer}"
    id 'com.google.protobuf' version "0.9.4"
}
dependencies {
    implementation "io.grpc:grpc-protobuf:${grpcProtobufVer}"
    implementation "io.grpc:grpc-stub:${grpcProtobufVer}"
    runtimeOnly "io.grpc:grpc-netty-shaded:${grpcProtobufVer}"
    implementation "net.devh:grpc-server-spring-boot-starter:${grpcServerVer}"
}
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protobufProtocVer}"
    }
    plugins {
        grpc {
            artifact = "io.grpc:protoc-gen-grpc-java:1.64.0"
        }
    }
//    generateProtoTasks {
//        all()*.plugins {
//            grpc {}
//        }
//    }
    generateProtoTasks {
        all().each { task ->
            task.builtins { java {} }
            task.plugins { grpc {} }
        }
    }
}
compileJava.dependsOn generateProto
// option:
//sourceSets{
//    main{
//        proto{
//            srcDirs += 'src/main/proto'
//        }
//        java{
//            srcDirs += 'build/generated/source/proto/main/grpc'
//            srcDirs += 'build/generated/source/proto/main/java'
//        }
//    }
//}
sourceSets {
    generated {
        java {
            srcDirs += "$projectDir/src/generated/main/java"
            srcDirs += "$projectDir/src/generated/main/grpc"
        }
    }
}
```
```shell
$ ./gradlew generateProto
```

### 4. Run:
```shell
$ ./gradlew :demo-communication-web:bootRun -Denv=dev
```
