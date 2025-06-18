FROM hdh/tasy_api

# Instalar Java 17
RUN apt-get update && apt-get install -y openjdk-17-jdk
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "/app.jar"]