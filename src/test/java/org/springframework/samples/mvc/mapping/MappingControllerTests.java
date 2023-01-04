package org.springframework.samples.mvc.mapping;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.samples.mvc.AbstractContextControllerTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class MappingControllerTests extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}

	@Ignore
	@Test
	public void byPath() throws Exception {
		this.mockMvc.perform(get("/mapping/path")).andExpect(content().string("Mapped by path!"))
		.andDo(print());
	}

	@Ignore
	@Test
	public void byPathPattern() throws Exception {
		this.mockMvc.perform(get("/mapping/path/wildcard"))
		        .andDo(print())
				.andExpect(content().string("Mapped by path pattern ('/mapping/path/wildcard')"));
	}

	@Ignore
	@Test
	public void byMethod() throws Exception {
		this.mockMvc.perform(get("/mapping/method"))
				.andExpect(content().string("Mapped by path + method"))
				.andDo(print());
	}

	@Ignore
	@Test
	public void byParameter() throws Exception {
		this.mockMvc.perform(get("/mapping/parameter?foo=add"))
				.andExpect(content().string("Mapped by path + method + presence of query parameter!"))
				.andDo(print());
	}

	@Ignore
	@Test
	public void byNotParameter() throws Exception {
		this.mockMvc.perform(get("/mapping/parameter"))
				.andExpect(content().string("Mapped by path + method + not presence of query parameter!"))
				.andDo(print());
	}

	@Ignore
	@Test
	public void byHeader() throws Exception {
		this.mockMvc.perform(get("/mapping/header").header("FooHeader", "foo"))
		.andDo(print())
		.andExpect(content().string("Mapped by path + method + presence of header!"));
	}

	@Ignore
	@Test
	public void byHeaderNegation() throws Exception {
		this.mockMvc.perform(get("/mapping/header"))
				.andDo(print())
 				.andExpect(content().string("Mapped by path + method + absence of header!"));
	}

	@Ignore
	@Test
	public void byConsumes() throws Exception {
		this.mockMvc.perform(
				post("/mapping/consumes")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"foo\": \"bar\", \"fruit\": \"apple\" }".getBytes()))
		.andDo(print())
		.andExpect(content().string(startsWith("Mapped by path + method + consumable media type (javaBean")));
	}

	@Ignore
	@Test
	public void byProducesAcceptJson() throws Exception {
		this.mockMvc.perform(get("/mapping/produces").accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(jsonPath("$.foo").value("bar"))
				.andExpect(jsonPath("$.fruit").value("apple"));
	}

	@Ignore
	@Test
	public void byProducesAcceptXml() throws Exception {
		this.mockMvc.perform(get("/mapping/produces").accept(MediaType.APPLICATION_XML))
		.andDo(print())
		.andExpect(xpath("/javaBean/foo").string("bar"))
				.andExpect(xpath("/javaBean/fruit").string("apple"));
	}

	@Ignore
	@Test
	public void byProducesJsonExtension() throws Exception {
		this.mockMvc.perform(get("/mapping/produces.json"))
				.andDo(print())
				.andExpect(jsonPath("$.foo").value("bar"))
				.andExpect(jsonPath("$.fruit").value("apple"));
	}

	@Ignore
	@Test
	public void byProducesXmlExtension() throws Exception {
		this.mockMvc.perform(get("/mapping/produces.xml"))
				.andDo(print())
				.andExpect(xpath("/javaBean/foo").string("bar"))
				.andExpect(xpath("/javaBean/fruit").string("apple"));
	}

}
