package org.springframework.samples.mvc.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
public class ConvertController {

	@GetMapping("primitive")
	public String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}

	// requires Joda-Time on the classpath
	@GetMapping("date/{value}")
	public String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;
		return "Converted date " + fmt.format(value.getTime());
	}

	@GetMapping("collection")
	public String collection(@RequestParam Collection<Integer> values) {
		return "Converted collection " + values;
	}

	@GetMapping("formattedCollection")
	//yyyy-MM-dd — for example, "2000-10-31".
	public String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
		return "Converted formatted collection " + values;
	}

	@GetMapping("bean")
	public String bean(JavaBean bean) {
		return "Converted " + bean;
	}

	@GetMapping("value")
	public String valueObject(@RequestParam SocialSecurityNumber value) {
		System.out.println(value.getValue());
		return "Converted value object " + value;
	}

	@GetMapping("custom")
	public String customConverter(@RequestParam @MaskFormat("###-##-###H") String value) {
		return "Converted '" + value + "' with a custom converter";
	} /** 2023-01-10-화 자현씨 테스트 결과
	 	1> 원래 문법에 맞게 @RequestParam + 생성자(x), setter(x) => @MaskFormat 작동
	 		원래 문법과 다르게 @RequestParam + 생성자(o), setter(o) => @MaskFormat 작동 x
 	 	2> 원래 문법에 맞게 @ModelAttributes + 생성자(o), setter(o) => @MaskFormat 작동
 	 	2> 원래 문법과 다르게 @ModelAttributes + 생성자(x), setter(x) => 되지도 않을 뿐더러 @MaskFormat 작동 x
	 **/
}
