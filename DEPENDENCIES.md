# Dependencies and Requirements

This document outlines all the dependencies, software versions, and requirements needed to run the microservices monitoring and logging setup.

## 🖥️ System Requirements

### Operating System
- **Windows**: Windows 10/11 (64-bit)
- **macOS**: macOS 10.15+ (Catalina or later)
- **Linux**: Ubuntu 18.04+, CentOS 7+, or similar

### Hardware Requirements
- **RAM**: Minimum 8GB, Recommended 16GB+
- **Storage**: Minimum 10GB free space
- **CPU**: Multi-core processor (2+ cores recommended)

## 📦 Required Software

### 1. Java Development Kit (JDK)
- **Version**: JDK 17 or higher
- **Download**: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
- **Verification**: `java -version`
- **Environment Variable**: `JAVA_HOME` should point to JDK installation directory

### 2. Apache Maven
- **Version**: 3.6.0 or higher
- **Download**: [Maven Official Site](https://maven.apache.org/download.cgi)
- **Verification**: `mvn -version`
- **Environment Variable**: `MAVEN_HOME` should be in `PATH`

### 3. Docker Desktop
- **Version**: 20.10.0 or higher
- **Download**: [Docker Desktop](https://www.docker.com/products/docker-desktop)
- **Verification**: `docker --version` and `docker-compose --version`
- **Requirements**: WSL2 on Windows, virtualization enabled in BIOS

### 4. Git
- **Version**: 2.20.0 or higher
- **Download**: [Git Official Site](https://git-scm.com/downloads)
- **Verification**: `git --version`

## 🔧 Maven Dependencies

### Core Dependencies (pom.xml)

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.5</version>
    <relativePath/>
</parent>

<properties>
    <java.version>17</java.version>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>

<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.5.5</version>
    </dependency>
    
    <!-- Spring Boot Actuator -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
        <version>3.5.5</version>
    </dependency>
    
    <!-- Micrometer Prometheus Registry -->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <version>1.12.0</version>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <version>3.5.5</version>
        <scope>test</scope>
    </dependency>
    
    <!-- SLF4J for Logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.9</version>
    </dependency>
</dependencies>
```

### Build Plugins

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>3.5.5</version>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
        
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>
        
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
    </plugins>
</build>
```

## 🐳 Docker Dependencies

### Base Images
- **Spring Boot Service**: `openjdk:21` (Alpine-based for smaller size)
- **Prometheus**: `prom/prometheus:latest`
- **Grafana**: `grafana/grafana:latest`

### Docker Compose Version
```yaml
version: '3.8'
```

### Network Configuration
```yaml
networks:
  monitoring:
    driver: bridge
    ipam:
      config:
        - subnet: 172.20.0.0/16
```

## 📊 Monitoring Dependencies

### Prometheus Configuration
- **Scrape Interval**: 15s (global), 5s (Spring Boot specific)
- **Evaluation Interval**: 15s
- **Retention**: Default (15 days)
- **Storage**: Local storage (configurable for production)

### Grafana Configuration
- **Default Port**: 3000
- **Default Credentials**: admin/admin
- **Data Source**: Prometheus
- **Dashboard**: Spring Boot 2.1+ (ID: 4701)

## 🔒 Security Dependencies

### Spring Boot Security (Optional)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>3.5.5</version>
</dependency>
```

### Docker Security
- **User**: Non-root user in containers
- **Ports**: Only necessary ports exposed
- **Networks**: Isolated network configuration
- **Volumes**: Read-only where possible

## 📝 Logging Dependencies

### Logback Configuration
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.11</version>
</dependency>
```

### Logging Levels
- **Application**: INFO
- **Spring Framework**: WARN
- **Hibernate**: WARN
- **Root**: INFO

## 🧪 Testing Dependencies

### Unit Testing
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>3.5.5</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

### Integration Testing
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>1.19.3</version>
    <scope>test</scope>
</dependency>
```

## 🔄 Development Dependencies

### Hot Reload (Optional)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <version>3.5.5</version>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

### Code Quality
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.3.0</version>
</plugin>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-pmd-plugin</artifactId>
    <version>3.21.2</version>
</plugin>
```

## 📋 Version Compatibility Matrix

| Component | Version | Java | Spring Boot | Notes |
|-----------|---------|------|-------------|-------|
| JDK | 17+ | ✅ | ✅ | LTS version |
| Maven | 3.6+ | ✅ | ✅ | Required for Spring Boot 3.x |
| Spring Boot | 3.5.5 | ✅ | ✅ | Latest stable |
| Micrometer | 1.12.0 | ✅ | ✅ | Compatible with Spring Boot 3.x |
| Prometheus | Latest | ✅ | ✅ | No version constraints |
| Grafana | Latest | ✅ | ✅ | No version constraints |
| Docker | 20.10+ | ✅ | ✅ | Required for containerization |

## 🚀 Performance Dependencies

### JVM Options
```bash
JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+UseStringDeduplication"
```

### Docker Resource Limits
```yaml
deploy:
  resources:
    limits:
      memory: 1G
      cpus: '1.0'
    reservations:
      memory: 512M
      cpus: '0.5'
```

## 🔍 Monitoring Dependencies

### Health Check Endpoints
- `/actuator/health` - Application health
- `/actuator/info` - Application information
- `/actuator/prometheus` - Metrics for Prometheus
- `/actuator/metrics` - All available metrics

### Custom Metrics
- **Counter**: `custom_operations_total`
- **Gauge**: `custom_gauge_value`
- **Timer**: `custom_operation_duration`
- **Histogram**: `custom_histogram_bucket`

## 📚 Additional Resources

### Documentation
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/3.5.5/reference/html/)
- [Micrometer Documentation](https://micrometer.io/docs)
- [Prometheus Configuration](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)
- [Grafana Dashboards](https://grafana.com/grafana/dashboards/)

### Community
- [Spring Community](https://spring.io/community)
- [Prometheus Community](https://prometheus.io/community/)
- [Grafana Community](https://community.grafana.com/)

---

**Note**: Keep all dependencies updated to their latest stable versions for security and performance improvements.
