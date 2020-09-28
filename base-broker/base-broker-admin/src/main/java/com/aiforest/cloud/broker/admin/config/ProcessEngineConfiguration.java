package com.aiforest.cloud.broker.admin.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessEngineConfiguration {
//	@Bean
//	public DataSource dataSource() {
//		// Use a JNDI data source or read the properties from
//		// env or a properties file.
//		// Note: The following shows only a simple data source
//		// for In-Memory H2 database.
//
//		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		dataSource.setDriverClass(org.h2.Driver.class);
//		dataSource.setUrl("jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		return dataSource;
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		return new DataSourceTransactionManager(dataSource());
//	}
//
//	@Bean
//	public SpringProcessEngineConfiguration processEngineConfiguration() {
//		SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
//
//		config.setDataSource(dataSource());
//		config.setTransactionManager(transactionManager());
//
//		config.setDatabaseSchemaUpdate("true");
//		config.setHistory("audit");
//		config.setJobExecutorActivate(true);
//
//		return config;
//	}
//
//	@Bean
//	public ProcessEngineFactoryBean processEngine() {
//		ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
//		factoryBean.setProcessEngineConfiguration(processEngineConfiguration());
//		return factoryBean;
//	}
//
//	@Bean
//	public RepositoryService repositoryService(ProcessEngine processEngine) {
//		return processEngine.getRepositoryService();
//	}
//
//	@Bean
//	public RuntimeService runtimeService(ProcessEngine processEngine) {
//		return processEngine.getRuntimeService();
//	}
//
//	@Bean
//	public TaskService taskService(ProcessEngine processEngine) {
//		return processEngine.getTaskService();
//	}
//
//	// more engine services and additional beans ...
}
