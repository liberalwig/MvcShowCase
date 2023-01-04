package org.springframework.samples.mvc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JavaBean {

	private String foo ;

	private String fruit;

	public JavaBean() {
		// TODO Auto-generated constructor stub
	}




	public JavaBean(String foo, String fruit) {
		super();
		this.foo = foo;
		this.fruit = fruit;
	}




	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		System.out.println("foo호출");
		this.foo = foo;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		System.out.println("fruit호출");
		this.fruit = fruit;
	}

	@Override
	public String toString() {
		return "JavaBean {foo=[" + foo + "], fruit=[" + fruit + "]}";
	}

}
