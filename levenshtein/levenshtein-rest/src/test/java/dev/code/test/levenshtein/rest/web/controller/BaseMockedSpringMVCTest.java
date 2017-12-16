package dev.code.test.levenshtein.rest.web.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import dev.code.test.levenshtein.common.IEnvironment;
import dev.code.test.levenshtein.rest.web.config.AppConfiguration;
import dev.code.test.levenshtein.rest.web.config.MvcConfiguration;
import dev.code.test.levenshtein.rest.web.config.service.PluginService;

@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfiguration.class, AppConfiguration.class})
public class BaseMockedSpringMVCTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	PluginService pluginService;
	
	
	@Autowired
	protected IEnvironment environment;
	
	protected MockMvc mockMvc;


	@Before
	public void setUpClass() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void checkInitialization() throws Exception {

		assertNotNull(pluginService);
	}
}
