# Сервис нотификации по email.

Стек:  **Java 17, Spring Boot, Maven, PostgreSQL 15.0, Mybatis, Docker, REST,  JUnit,  Mockito, RabbitMQ, Swagger, Liquibase, H2.**

### Запуск приложения

Чтобы запустить приложение необходимо
* Скачать или клонировать репозиторий

* Командой развернуть контейнеры в Docker
```bash
docker-compose up
```

* Запустить проект

* В application.yml указать данные для сервиса рассылки

* При необходимости в resources/application.yml указать данные для сервиса рассылки. (Без них приложение будет работать но рассылать email не сможет)

* В resouces/db/changelog/db.changelog-2.0.sql находятся скрипты которые заполнят бд данными, если они не нужны, нужно удалить блок с db.changelog-2.0.sql из resouces/db/changelog/db.changelog-main.yml

* Swagger-UI можно посмотреть по урлу http://localhost/swagger-ui/index.html#/

---

### EndPoints
```POST /api/v1/mails``` - Добавление новой рассылки и отправка ее в очередь;  
```GET /api/v1/mails/{$uniqueMessage}``` - Получение рассылки по уникальному ключу;  
```GET /api/v1/mails``` - Получение всех рассылок; 

