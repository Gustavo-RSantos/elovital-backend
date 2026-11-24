# EloVital – Back-end

API REST em Spring Boot 4.1 / Java 25 / PostgreSQL, em camadas:
`model` → `repository` → `dto` → `service` → `controller` (+ `exception` e `config`).

## 1. Criar o banco (uma vez)

```
psql -U postgres -c "CREATE DATABASE elovital ENCODING 'UTF8';"
```

## 2. Abrir no IntelliJ Community

1. **File > Open** e escolha o `pom.xml` > **Open as Project**. O IntelliJ usa o Maven embutido; não precisa instalar Maven.
2. **File > Project Structure > Project SDK**: JDK 25.
3. **Settings > Build, Execution, Deployment > Build Tools > Maven > Runner**: JDK 25.
4. Aguarde o Maven baixar as dependências (aba Maven > *Reload All Maven Projects* se precisar).

> O IntelliJ precisa ser uma versão que entenda Java 25 (2025.2.2 ou mais nova).

## 3. Rodar

1. Abra `ElovitalApplication` e execute o `main`.
2. **Run > Edit Configurations** e, em *Environment variables*, defina: `DB_PASSWORD=sua_senha`
   (opcionais: `DB_USER`, `DB_URL`; padrão `postgres` e `jdbc:postgresql://localhost:5432/elovital`).
3. Na primeira execução o Flyway cria as 19 tabelas (`V1__criar_schema.sql`).

## 4. Testar

```
curl -X POST http://localhost:8080/api/usuarios -H "Content-Type: application/json" \
  -d '{"nome":"Maria","email":"maria@email.com","senha":"senha1234","tipoPerfil":"IDOSO"}'

curl -X POST http://localhost:8080/api/idosos -H "Content-Type: application/json" \
  -d '{"usuarioId":1,"dataNascimento":"1950-03-10","tipoSanguineo":"O+"}'

curl -X POST http://localhost:8080/api/idosos/1/alergias -H "Content-Type: application/json" \
  -d '{"descricao":"Dipirona"}'

curl http://localhost:8080/api/idosos/1/alergias
```

## Estado atual

- Pronto: as 19 entidades, 7 enums, 19 repositórios, schema SQL, tratamento global de erros.
- CRUD completo (dto + service + controller): `Usuario`, `Idoso`, `Alergia`.
  `Alergia` é o modelo para os demais recursos filhos de idoso.
- **Ainda sem autenticação/autorização**: todos os endpoints estão abertos.
