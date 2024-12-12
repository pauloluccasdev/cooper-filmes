Para atender ao requisito de deixar usuários registrados no sistema, podemos configurar os usuários diretamente no banco de dados ou via uma funcionalidade de inicialização no backend. Seguem os passos detalhados para a abordagem mais comum:

---

### **1. Adicionar os usuários ao banco de dados**
Adicione uma configuração de inicialização no backend para que, ao iniciar o sistema, os usuários sejam automaticamente registrados. 

No backend, crie um arquivo `data.sql` (ou similar) dentro da pasta `resources` para que o Spring Boot insira os registros na tabela de usuários ao iniciar. Por exemplo:

#### **Arquivo `data.sql`**
```sql
INSERT INTO users (id, email, password, role) VALUES 
(1, 'analista@example.com', '$2a$10$hashedPassword1', 'ANALISTA'),
(2, 'revisor@example.com', '$2a$10$hashedPassword2', 'REVISOR'),
(3, 'aprovador1@example.com', '$2a$10$hashedPassword3', 'APROVADOR'),
(4, 'aprovador2@example.com', '$2a$10$hashedPassword4', 'APROVADOR'),
(5, 'aprovador3@example.com', '$2a$10$hashedPassword5', 'APROVADOR');
```

> ⚠️ **Nota**: Substitua `hashedPasswordX` por senhas criptografadas usando bcrypt. Você pode gerar hashes com bibliotecas em Java, Node.js ou ferramentas online. Exemplo:
> 
> ```java
> BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
> System.out.println(encoder.encode("minhaSenha123"));
> ```

---

### **2. Atualizar o `README`**
Inclua as credenciais no `README` do projeto para facilitar o acesso:

#### **Exemplo de seção no `README.md`**
```markdown
## Credenciais dos Usuários no Sistema

Os seguintes usuários já estão registrados no sistema com os papéis especificados:

| Usuário              | Papel      | Senha         |
|----------------------|------------|---------------|
| analista@example.com | Analista   | analista123   |
| revisor@example.com  | Revisor    | revisor123    |
| aprovador1@example.com | Aprovador | aprovador123  |
| aprovador2@example.com | Aprovador | aprovador123  |
| aprovador3@example.com | Aprovador | aprovador123  |
```

---

### **3. Garantir criação automática de dados no ambiente**
No `docker-compose.yml`, monte um volume para incluir o script SQL automaticamente na inicialização:

#### Atualização no `docker-compose.yml` (serviço `database`):
```yaml
  database:
    image: postgres:15
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: mydb
    volumes:
      - ./backend/src/main/resources/data.sql:/docker-entrypoint-initdb.d/data.sql
```

Isso garante que os dados sejam carregados sempre que o banco for recriado.

---

### **4. Testar o Sistema**
- Inicie o sistema com `docker-compose up`.
- Verifique se os usuários foram criados corretamente acessando o banco ou testando o login com as credenciais listadas no `README`.

Se precisar de mais ajustes, posso ajudar! 😊
