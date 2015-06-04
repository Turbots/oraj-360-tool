set MEMORY_PER_SERVICE=256m
START "Config Server" java -Xmx%MEMORY_PER_SERVICE% -jar config-server/target/config-server-1.0.0-SNAPSHOT.jar
START "Service Registry" java -Xmx%MEMORY_PER_SERVICE% -jar service-registry/target/service-registry-1.0.0-SNAPSHOT.jar
START "Admin" java -Xmx%MEMORY_PER_SERVICE% -jar admin/target/admin-1.0.0-SNAPSHOT.jar
START "Zuul Gateway" java -Xmx%MEMORY_PER_SERVICE% -jar zuul/target/zuul-1.0.0-SNAPSHOT.jar
START "Turbine" java -Xmx%MEMORY_PER_SERVICE% -jar turbine/target/turbine-1.0.0-SNAPSHOT.jar
START "Hystrix" java -Xmx%MEMORY_PER_SERVICE% -jar hystrix/target/hystrix-1.0.0-SNAPSHOT.jar
exit