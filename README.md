Academia API
Descrição

A Academia API é uma aplicação backend construída em Spring Boot para gerenciar uma academia.
A solução permite:

Gerenciar alunos, planos e treinos

Registrar pagamentos e cobranças

Gerenciar vínculos aluno-treino

Registrar treinos avulsos

Validar CPF e evitar duplicidades

Documentar e testar todos os endpoints via Swagger/OpenAPI

A API utiliza H2 Database para testes locais, com suporte a inicialização rápida e reset de dados.

Tecnologias utilizadas

Java 21

Spring Boot

Spring Data JPA

H2 Database

Maven

Swagger/OpenAPI 3

JSON para requisições/respostas

Como rodar o projeto
Pré-requisitos

Java JDK 21+

Maven 3.8+

IDE para Java

Passos

1.Clone o repositório:
git clone https://github.com/LittleChikita/Prova-API-REST-de-Gest-o-de-Academia.git
Depois digite o comando : 
cd Prova-API-REST-de-Gest-o-de-Academia

2. Compile o projeto:
mvn clean install

3. Rode a aplicação
mvn spring-boot:run

4. Acesse a API via navegador:
http://localhost:8080/api/v1/

5. Acesse a documentação Swagger:
http://localhost:8080/swagger-ui/index.html

<img width="1403" height="888" alt="GetPagamentos" src="https://github.com/user-attachments/assets/e31492eb-98a9-4ac1-bb98-093c654e4c5d" />
<img width="1420" height="823" alt="GetCobrancaStatusPendente" src="https://github.com/user-attachments/assets/5dd7a2bc-9366-47d4-8eb3-813ad55e3060" />
<img width="1427" height="814" alt="GetCobrancaAlunoId" src="https://github.com/user-attachments/assets/11530011-bd0b-4f67-9e51-c9e1113a138f" />
<img width="1421" height="789" alt="GetAlunoTreinoVinculosTreinoId" src="https://github.com/user-attachments/assets/5e251433-1033-40d1-abb8-538f1dededc7" />
<img width="1412" height="786" alt="GetAlunoTreinoVinculoAlunoId" src="https://github.com/user-attachments/assets/36c72a24-1287-4d34-a641-4abf850bf67a" />
<img width="1403" height="459" alt="GetAlunosId" src="https://github.com/user-attachments/assets/2fc06db5-cfa8-4cd7-bcb2-1505bc6e9ca4" />
<img width="1402" height="784" alt="GetAlunos" src="https://github.com/user-attachments/assets/3b650aaa-a585-4e8b-b955-1240cf4655a3" />
<img width="1410" height="761" alt="DeleteTreino" src="https://github.com/user-attachments/assets/80d56fc0-17c5-47ae-9bbf-6a5bfa26c3ae" />
<img width="1389" height="340" alt="DeletePlanoSemAssociacao" src="https://github.com/user-attachments/assets/141a3147-edea-4737-b2b0-e17ee1415f4d" />
<img width="1399" height="800" alt="DeletePlanoComAssociacao" src="https://github.com/user-attachments/assets/4ee4e3ec-0dbf-42c1-8945-fa351b9ed99e" />
<img width="1400" height="428" alt="DeleteAlunoTreinoVinculo" src="https://github.com/user-attachments/assets/aedd529f-9887-4932-b06e-844e46776b37" />
<img width="1402" height="327" alt="DeleteAlunos" src="https://github.com/user-attachments/assets/85bdc180-a529-4057-a858-c4414b63fd5d" />
<img width="1472" height="884" alt="Swagger2" src="https://github.com/user-attachments/assets/9acc7229-32a0-4f90-8d65-1dfec0abeb96" />
<img width="1460" height="612" alt="Swagger1" src="https://github.com/user-attachments/assets/d1e51e76-9366-41d2-84b0-a543ec78408a" />
<img width="1480" height="502" alt="Swagger3" src="https://github.com/user-attachments/assets/d3d2f938-48a9-4423-a7d5-4fad0d5bb59e" />
<img width="1394" height="461" alt="PutTreino" src="https://github.com/user-attachments/assets/2bb32967-02a7-45da-ae18-23ed3eb6ee55" />
<img width="1400" height="554" alt="PutPlano" src="https://github.com/user-attachments/assets/a42f71c6-9794-4f47-ac6e-27514a8481dc" />
<img width="1397" height="579" alt="PostTreinos" src="https://github.com/user-attachments/assets/4b75c8d2-b97a-4251-835a-80b186d7cd63" />
<img width="1399" height="547" alt="PostPlano" src="https://github.com/user-attachments/assets/3325d8cc-b74b-457c-97f1-d96705e1d01d" />
<img width="1409" height="866" alt="PostPagamento" src="https://github.com/user-attachments/assets/5670eb6b-1743-4ab6-be5c-7c2cb7bd89d0" />
<img width="1416" height="813" alt="PostCobranca" src="https://github.com/user-attachments/assets/67a31f10-ff0d-4609-aa92-3b461db11469" />
<img width="1407" height="436" alt="PostAlunoTreinoVinculo" src="https://github.com/user-attachments/assets/047f9795-3f32-45d3-985e-157bf9c1dca0" />
<img width="1401" height="773" alt="PostAlunosFuncionando" src="https://github.com/user-attachments/assets/d7f61675-0271-45da-bde2-0d19bd3f28d2" />
<img width="1396" height="886" alt="PostAlunosCpfInvalido" src="https://github.com/user-attachments/assets/2e177ebb-31f7-4f4d-a444-c5c43abef8b1" />
<img width="1415" height="812" alt="GetTreinos" src="https://github.com/user-attachments/assets/85f0abbe-9916-4890-ba6f-5c0b888957f9" />
<img width="1406" height="471" alt="GetPlanoId" src="https://github.com/user-attachments/assets/8e55bd22-8f4e-4b53-9508-fdd0f6de2264" />
<img width="1397" height="632" alt="GetPanos" src="https://github.com/user-attachments/assets/f2403b67-fd4a-4406-adc0-225ac274c632" />
<img width="1410" height="852" alt="GetPagamentosAlunoId" src="https://github.com/user-attachments/assets/b32bc894-74be-4c29-9f8d-793466376898" />

