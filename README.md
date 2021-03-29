# Crud Spring Boot - Pet Schedule

# Requisitos contemplados
 * CRUD de Recursos 
    * Recursos são como funcionários, veterinários, equipamentos, salas, etc. Um conjunto de recursos são alocados em cada agendamento, permitindo a distribuição sem conflitos e controle de custos no futuro.
 * CRUD de Clientes (Testes Unitários Repository)
 * CRUD de PETs (Testes Unitários Controller)
 * CRUD de Agendamentos (Testes Unitários Repository & Controller)
    * Validação de dados e de recursos não alocados (Recursos são funcionários ou equipamentos em geral)
 * Login com JWT baseado em Usuario (2 pré-cadastrado data.sql)
    * Um usuário pode ser também um recurso humano da clinica, mas não explorei o cadastro de usuário; 
    * Preparado para uso de roles quando quiser adicionar perfis como admin, etc;
 * Clinica pré cadastrada, se fosse um SaaS poderia pedir do ID dela no path ao invés do body;    
 * Profile de produção com variáveis de ambiente, profile de test usa o application.properties (default)
 * data.sql com uma base de testes e desenvolvimento
 * Documentação API: /swagger-ui.html    
  
# MER
![alt text](https://image.prntscr.com/image/317KEvpMRR2urzETKvAFCA.png)

   

