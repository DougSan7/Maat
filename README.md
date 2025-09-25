# Maat

**Maat** é um projeto em desenvolvimento voltado para gerenciamento de projetos e equipes.

## ✨ Funcionalidades

- Cadastro e consulta de usuários, projetos e equipes  
- Integração com banco de dados (MySQL)  
- Interface gráfica construída com JavaFX  
- Estrutura organizada em Maven para facilitar manutenção e escalabilidade  

## 🚀 Tecnologias

| Tecnologia | Versão / Observações |
|------------|-----------------------|
| Java | 21 (ou versão utilizada) |
| JavaFX | Interface gráfica |
| MySQL | Banco de dados relacional |
| Maven | Build e gerenciamento de dependências |

## 🏗 Estrutura do Projeto

```
.
├── src/
│   └── main/
│       ├── java/        ← código-fonte
│       └── resources/   ← FXML, configs
├── pom.xml              ← dependências e build
├── .gitignore
└── README.md
```

## 📦 Instalação / Como rodar

```bash
# Clonar o repositório
git clone https://github.com/DougSan7/Maat.git
cd Maat

# Compilar com Maven
./mvnw clean install

# Executar
./mvnw exec:java -Dexec.mainClass="main.teste4.MainApplication"
```

> Obs: Configure previamente o banco de dados MySQL com as credenciais adequadas no DAO.  

## 🧪 Testes

```bash
# Executar testes
./mvnw test
```

## 👥 Autores e Colaboradores

- **Douglas Santos** — Desenvolvedor principal — [DougSan7](https://github.com/DougSan7)  
- **Pedro Parra** — Colaborador  [Peehnrq](https://github.com/Peehnrq)
- **Paloma Ferro** — Colaboradora [PalomaFerro](https://github.com/PalomaFerro)

## 🤝 Contribuição

Contribuições são bem-vindas!  

1. Faça um fork do projeto  
2. Crie sua branch: `git checkout -b feature/minha-feature`  
3. Commit suas mudanças: `git commit -m 'Adicionei minha feature'`  
4. Push para a branch: `git push origin feature/minha-feature`  
5. Abra um Pull Request  

## 📄 Licença

Este projeto está licenciado sob a **MIT License** – consulte o arquivo `LICENSE` para mais detalhes.
