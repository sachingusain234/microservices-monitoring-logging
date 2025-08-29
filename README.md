# Microservices Monitoring and Logging Project

## 📋 Project Overview

This project demonstrates a complete microservices monitoring stack using:

- **Spring Boot** (Java microservice with health and metrics endpoints)
- **Prometheus** (for metrics collection)
- **Grafana** (for visualization)
- **Docker Compose** (for container orchestration)

**Optional**: Extendable for other services, alerting, and logging.

## 🛠️ Prerequisites

### Install Required Software

1. **Java JDK 17+**: For building and running the Spring Boot app
2. **Apache Maven 3.6+**: For Java project builds
3. **Docker Desktop**: For containerization and multi-service orchestration
4. **Git**: For source code management

## 🏗️ Project Structure Creation

### Directory Structure

```
microservices-monitoring-logging/
├── docker-compose.yml
├── services/
│   └── spring-boot-service/
│       └── service/
│           ├── src/
│           │   ├── main/
│           │   │   ├── java/com/spring/boot/service/Contoller/PublicController.java
│           │   │   ├── java/com/spring/boot/service/ServiceApplication.java
│           │   │   └── resources/application.yml
│           ├── pom.xml
│           └── Dockerfile
└── monitoring/
    ├── prometheus/prometheus.yml
    ├── grafana/
    ├── alertmanager/
    └── loki/
```

## ☕ Spring Boot Microservice

### Dependencies in pom.xml

- `spring-boot-starter-actuator`
- `spring-boot-starter-web`
- `micrometer-registry-prometheus` (for Prometheus metrics)

### Components

- **Main Application**: Spring Boot application entry point
- **REST Controller**: Provides `/api/health-check` endpoint
- **Configuration** (`application.yml`): Exposes actuator and Prometheus endpoints

## 🐳 Docker Configuration

### Spring Boot Dockerfile

Builds the app in a container, exposing port 8080.

### docker-compose.yml

Defines `springboot`, `prometheus`, and `grafana` services in a custom bridge network.

## 📊 Prometheus Configuration

### prometheus.yml in monitoring/prometheus/

- **Scrape interval**: 5s for the Spring Boot service
- **Target**: `springboot:8080` (service name in Compose)

## 🚀 Build and Run

### Build Spring Boot Application

```bash
mvn clean package -DskipTests
```

### Start All Containers

```bash
docker-compose up -d
```

### Verify Services

- **Spring Boot**: http://localhost:8080/api/health-check
- **Prometheus**: http://localhost:9090
- **Grafana**: http://localhost:3000

## 📈 Grafana Setup

### Initial Configuration

1. **Login**: Default is `admin/admin`. Change password if prompted.

2. **Add Data Source**:
   - Type: Prometheus
   - URL: `http://prometheus:9090`

3. **Import Dashboard**:
   - Go to 'Import'
   - Use ID `4701` for a default Spring Boot dashboard or use a dashboard specific to Micrometer/Actuator if desired
   - Assign Prometheus as the data source

## 🧪 Testing and Troubleshooting

### Generate Traffic

Access http://localhost:8080/api/health-check multiple times or loop calls to increase metric data.

### Prometheus Targets

- Visit http://localhost:9090/targets
- Ensure `springboot:8080` is UP

### Grafana No Data/N/A

If dashboards show "N/A" or "No data":

1. Go to Explore in Grafana
2. Try querying for `http_server_requests_seconds_count` or another metric visible at `/actuator/prometheus`
3. Edit dashboard queries to match actual metric names from Prometheus

### Example Metrics for Queries

- **HTTP request count**: `http_server_requests_seconds_count`
- **JVM memory usage**: `jvm_memory_used_bytes`
- **Uptime**: `process_uptime_seconds`

> **Note**: If your dashboard expects node-level/system metrics (CPU, memory, etc.), deploy the `node_exporter` as a sidecar and add it to Prometheus scrapes.

## 🚀 Advanced Steps and Next Actions

1. **Add custom business metrics** to your app and expose via Micrometer
2. **Set up Prometheus alerting/Alertmanager**
3. **Extend with more microservices** or a logging stack (e.g., Loki for logs, ELK)
4. **Implement CI/CD** for automated testing and deployment

## 🔧 Troubleshooting Commands

```bash
# Check all logs
docker-compose logs

# Check Prometheus health
curl http://localhost:9090/-/healthy

# Query Prometheus targets
curl http://localhost:9090/api/v1/targets

# Restart services
docker-compose restart springboot
docker-compose down
docker-compose up -d --build
```

## 📁 Quick Start

1. **Clone the repository**
2. **Navigate to the project directory**
3. **Build the Spring Boot application**: `mvn clean package -DskipTests`
4. **Start all services**: `docker-compose up -d`
5. **Access the services**:
   - Spring Boot: http://localhost:8080
   - Prometheus: http://localhost:9090
   - Grafana: http://localhost:3000

## 🔍 Monitoring Endpoints

### Spring Boot Service
- **Health Check**: `GET /api/health-check`
- **Actuator Health**: `GET /actuator/health`
- **Actuator Info**: `GET /actuator/info`
- **Prometheus Metrics**: `GET /actuator/prometheus`

### Prometheus
- **Targets**: `/targets`
- **Graph**: `/graph`
- **Status**: `/status`

## 📚 Additional Resources

- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Micrometer Documentation](https://micrometer.io/docs)
- [Prometheus Documentation](https://prometheus.io/docs/)
- [Grafana Documentation](https://grafana.com/docs/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## 📄 License


---

**Happy Monitoring! 🚀📊**
