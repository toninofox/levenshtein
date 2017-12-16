package dev.code.test.levenshtein.rest.web.config.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.code.test.levenshtein.common.IEnvironment;
import dev.code.test.levenshtein.common.PluginFactory;
import dev.code.test.levenshtein.common.PluginResourceManager;
import dev.code.test.levenshtein.rest.core.CoreResourceManager;
import dev.code.test.levenshtein.rest.core.ICoreAPI;

@Service
public class PluginService {
	private static Logger log = LoggerFactory.getLogger(PluginService.class);
	private PluginFactory<ICoreAPI> apiFactory;
	private PluginResourceManager<ICoreAPI> coreResourceManager;
	
	@Autowired IEnvironment environment;
	
	@PostConstruct
	public void onStartup() {
		log.info("Initializing Resource Managers");
		coreResourceManager = new CoreResourceManager();
		apiFactory = coreResourceManager.start(environment);
		log.info("Application Initialized");
	}

	@PreDestroy
	public void onExit() {
		log.warn("Shutting down...");
		coreResourceManager.stop();
		log.warn("Terminated");
	}

	public ICoreAPI createPlugin() {
		log.debug("Plugin factory requested. Creating new one...");
		return apiFactory.createPlugin();
	}
	
}