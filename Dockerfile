FROM openjdk:8
ADD target/BackendCalculatorAPI.jar BackendCalculatorAPI.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "BackendCalculatorAPI.jar"]