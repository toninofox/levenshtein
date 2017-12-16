package dev.code.test.levenshtein.rest.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.code.test.levenshtein.common.IEnvironment;
import dev.code.test.levenshtein.common.PluginFactory;
import dev.code.test.levenshtein.common.PluginResourceManager;

public class CoreResourceManager implements PluginResourceManager<ICoreAPI> {

	
	private static final Logger log = LoggerFactory.getLogger(CoreResourceManager.class);
	@Override
	public PluginFactory<ICoreAPI> start(IEnvironment env) {
		/* understand if the plugin needs any resource or behavior different by environment.
		 * In this case, nothing to to to use the environment but the application is ready to be configured differently
		 */
		log.debug("nothing to do. No Resource has to be started");
		return new CoreAPIFactory();
	}

	@Override
	public void stop() {
		log.debug("nothing to do. No Resource needs to be stopped");

	}

}
