# Entendendo melhor o Projeto


## Apache Maven

Ferramenta de automação e gerenciamento construção de projetos. Padronização de automação, construção e publicação de suas aplicações, agregando agilidade e qualidade no projeto.

## Arquivo pom.xml

Contém todas as configurações que o Maven necessita para nosso projeto. Relaciona as dependências, os repositórios, os plugins, entre outras configurações.


## Spring Framework Java

Facilita o desenvolvimento de aplicações baseado nos padrões de projeto de **inversão de controle** e **injeção de dependência**.

## Spring Boot

Cria aplicações standalone. Facilita o processo de configuração e publicação de aplicações que utilizem o ecossistema **Spring**. Faz toda a magia, deixando tudo configurado, com um servidor tomcat embarcado.


## Spring Batch

Faz parte do projeto **Spring**, utilizado para criar aplicações **batch**. Facilita no processo de criação de jobs, que processa um quantidade finita de dados sem interação ou interrupção.

- A solução é composta dos seguintes componentes:

**@EnableBatchProcessing**: Essa anotação permite que o Spring monte toda a estrutura necessária para executar o batch. Utilizado na classe **PersonBatchConfiguration**

**Job Launcher**: executa o job de fato. É executado no ImportRunner(ApplicationRunner), sendo passado como parâmetro "jobParameters" que é informado o arquivo que será importado.

	`jobLauncher.run(job, jobParameters)`

**jobBuilderFactory** e **stepBuilderFactory**: injeta esses componentes para construir o job e seus steps no componente **PersonBatchConfiguration**.

**Job**: é uma máquina de estados com sequência de etapas (steps). No nosso projeto foi implementado como "importPersonJob" dentro do componente **"PersonBatchConfiguration"**. Foi passado como parâmetro o lister e o step.

	`public Job importPersonJob(final PersonJobListener listener, final Step step1)`
	
**Step Listener**: permite de operações em alguns momentos do ciclo de vida do Job. No projeto foi implementado no componente **PersonJobListener** através dos métodos **beforeJob** a exclusão dos dados da tabela, e no **afterJob** alteração e consulta no banco de dados.

	`public void beforeJob(JobExecution jobExecution)`

	`public void afterJob(JobExecution jobExecution)`

**Step**: etapa que executa uma determinada lógica. São encadeados para obterem o resultado final do processamento. Cada step tem a etapa de leitura (ItemReader), processamento (ItemProcessor) e escrita (ItemWriter). Pode ser fatiado em pedaços utilizando o método **chunk**. Um job pode ter mais de um step também.

**ItemReader**: Parte do step onde é feita a leitura da fonte origem, no nosso projeto foi um aquivo txt, foi implementado **personReader** no **PersonBatchConfiguration**.

**ItemWriterr**: Parte do step onde é feita a excrita no destino, no nosso projeto foi utilizado um banco de dados. Foi implementado **personWriter** no **PersonBatchConfiguration**.

**ItemProcessor**: Parte do step onde é feita o processamento e validação dos dados, no nosso projeto foi verificado o CPF se é par. Foi implementado no componente **PersonItemProcessor**.

**Job Repository**: mantém o estado da execução do job (escritas, leituras, duração, status, erros, etc), que é compartilhado com os outros componentes da solução.


## Principais anotações utilizadas no projeto

**@SpringBootApplication**: Engloba a @Configuration, @ComponentScan e @EnableAutoConfiguration. Verificar as configurações necessárias para rodar o projeto, fazendo é uma varredura de componentes, mas, só verifica os **subpacotes**.

**@Component**: indica que a classe vai ser gerenciada pelo container do Spring.

**@Configuration**: indica que a classe possui métodos que expõe novos beans. (Derivada da @Component)

**@Bean**: utilizada com métodos de uma classe, indicando que o Spring deve invocar aquele método e gerenciar o objeto retornado por ele. Sendo assim, este objeto pode ser injetado em qualquer ponto da sua aplicação.

**@Autowired**: utilizada para marcar o ponto de injeção na sua classe. Pode ser usado ela sobre atributos ou sobre o seu construtor com argumentos.


- Algumas Outras anotações importantes, mas que não foram utilizadas no projeto

**@Controller**: associada com classes que possuem métodos que processam requests numa aplicação web. (Derivada da @Component)

**@Repository**: associada com classes que isolam o acesso aos dados da sua aplicação. (Derivada da @Component)

**@Service**: associada com classes que representam algum fluxo de negócio da aplicação. (Derivada da @Component)