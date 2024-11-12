# PraticaSAEP
## Diagrama Entidade-Relacionamento:
```mermaid
erDiagram
    Tarefa {
        int id PK
        string descricao
        string prioridade
        string status
        string setor
        int usuario_id FK
    }

    Usuario {
        int id PK
        string nome
        string email
    }

    Status {
        string nome
    }

    Setor {
        string nome
    }

    Prioridade {
        string nivel
    }

    Tarefa ||--o{ Usuario : "Responsável"
    Tarefa ||--o{ Status : "Possui"
    Tarefa ||--o{ Setor : "Pertence"
    Tarefa ||--o{ Prioridade : "Tem"
```

## Diagrama de Casos de Uso:
```mermaid
flowchart TD
    A[Administrador] --> B[Cadastrar Tarefa]
    A --> C[Atualizar Status da Tarefa]
    A --> D[Excluir Tarefa]
    A --> E[Visualizar Tarefas]

    B --> E
    C --> E
    D --> E
```
