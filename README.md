# Init & build project (JDK 17)
```
mvn install
```

# Run application
```
java -jar target/Calculator-1.0.0.jar
```

# calculator
```
curl http://localhost:8080/current

curl http://localhost:8080/add/{operand}
curl http://localhost:8080/divide/{operand}
curl http://localhost:8080/multiply/{operand}
curl http://localhost:8080/subtract/{operand}

curl http://localhost:8080/redo
curl http://localhost:8080/undo
```

# Lottery
```
curl http://localhost:8080/lottery/draw
```