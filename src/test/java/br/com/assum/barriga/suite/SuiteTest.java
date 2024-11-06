package br.com.assum.barriga.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

//@Suite
@SuiteDisplayName("Suite de Testes")
@SelectPackages(value = {
		"br.com.assum.barriga.domain.service",
		"br.com.assum.barriga.domain"
})
public class SuiteTest {

}
