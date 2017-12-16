package dev.code.test.levenshtein.rest.core;

import dev.code.test.levenshtein.common.PluginFactory;

class CoreAPIFactory implements PluginFactory<ICoreAPI> {

	@Override
	public ICoreAPI createPlugin() {
		return new CoreAPI();
	}

}
