# Entendendo melhor o Projeto


## Apache Maven

Ferramenta de automação e construção de projetos. Padronização de automação, construção e publicação de suas aplicações, agregando agilidade e qualidade no projeto. Todas as configurações que o Maven necessita são realizadas no arquivo pom.xml. Relaciona as dependências, os repositórios, os plugins, entre outras configurações.


## Spring Framework

Facilita o desenvolvimento de aplicações baseado nos padrões de projeto de **inversão de controle** e **injeção de dependência**.

### Spring Boot

Cria aplicações standalone. Facilita o processo de configuração e publicação de aplicações que utilizem o ecossistema **Spring**. Faz toda a magia, deixando tudo configurado. Por exemplo, em uma aplicação web ele pode rodar um servidor tomcat embarcado.

### Spring Batch

Utilizado para criar aplicações **batch**. Facilita no processo de criação de jobs, que processa um quantidade finita de dados sem interação ou interrupção.

- A solução é composta dos seguintes componentes:

**Job Launcher**: responsável pela execução de jobs. É usado no ImportRunner(ApplicationRunner), utilizando como parâmetros o job propriamente dito e o "jobParameters", onde é informado o arquivo que será importado.

	`jobLauncher.run(job, jobParameters)`

**jobBuilderFactory** e **stepBuilderFactory**: componentes utilizados para construir o job e seus steps. Usados no componente **PersonBatchConfiguration**.

**Job**: é uma máquina de estados com sequência de etapas (steps). No nosso projeto foi implementado como "importPersonJob" e definido em **"PersonBatchConfiguration"**. Foi passado como parâmetro o listener e o step.

	`public Job importPersonJob(final PersonJobListener listener, final Step step1)`
	
**Step Listener**: permite a utilização de callbacks em alguns momentos do ciclo de vida do Job. No projeto foi implementado no componente **PersonJobListener** através dos métodos **beforeJob** a exclusão dos dados da tabela, e no **afterJob** alteração e consulta no banco de dados.

	`public void beforeJob(JobExecution jobExecution)`

	`public void afterJob(JobExecution jobExecution)`

**Step**: etapa que executa uma determinada lógica. São encadeados para obterem o resultado final do processamento. Cada step tem a etapa de leitura (ItemReader), processamento (ItemProcessor) e escrita (ItemWriter). Pode ser fatiado em pedaços utilizando o método **chunk**. Um job pode ter mais de um step.

**ItemReader**: Parte do step onde é feita a leitura da origem dos dados, no nosso projeto foi um aquivo txt, foi implementado **personReader** no **PersonBatchConfiguration**.

**ItemWriter**: Parte do step onde é feita a escrita dos dados processados, no nosso projeto foi utilizado um banco de dados Postgres. Foi implementado **personWriter** no **PersonBatchConfiguration**.

**ItemProcessor**: Parte do step onde é feita o processamento e validação dos dados, no caso deste projeto foi verificada cada linha do arquivo txt e inserido no Banco de Dados apenas as que tinham o numéro do *CPF par*. Foi implementado no componente **PersonItemProcessor**.

**Job Repository**: mantém o estado da execução do job (escritas, leituras, duração, status, erros, etc), que é compartilhado com os outros componentes da solução.


## Principais anotações utilizadas no projeto

**@SpringBootApplication**: Engloba a @Configuration, @ComponentScan e @EnableAutoConfiguration. Verifica as configurações necessárias para rodar o projeto, fazendo uma varredura de componentes, mas, só verifica os **subpacotes**.

**@Component**: indica que a classe vai ser gerenciada pelo container do Spring.

**@Configuration**: indica que a classe contém uma fonte de definições de novos beans. (Derivada da @Component)

**@Bean**: utilizada com métodos de uma classe, indicando que o Spring deve invocar aquele método e gerenciar o objeto retornado por ele. Sendo assim, este objeto pode ser injetado em qualquer ponto da sua aplicação.

**@Autowired**: instrui a injeção de dependência do Spring e "conecta" um bean especifico ao membro da classe. (Pode ser usado em atributos ou construtores)

**@EnableBatchProcessing**: Essa anotação permite que o Spring monte toda a estrutura necessária para executar o batch. Foi utilizado neste projeto no componente **PersonBatchConfiguration**

**@@StepScope**: o Spring Batch usará o container spring para instanciar uma nova instância desse componente para cada execução do Step. Para ter acesso ao **jobParameters** é necessário que o escopo do bean seja de Step. Isso porque o objeto só estará disponível no escopo de execução do step.

**@Value**: mostra ao spring que o valor a ser injetado desse atributo deve ser o passado como parâmetro.

**@Getter, @Setter, @NoArgsConstructor, @RequiredArgsConstructor, @Builder, @ToString**: Anotações do framework **Lombok**, deixando o código menos verboso, gerando em tempo de compilação os métodos getters, setters, consturutores, etc. 

**@Slf4j**: cria, por default, uma váriável **log** para ser usado no código e mostrar informações no log.
