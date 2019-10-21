package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_1_ValidaSite.class, Test_2_FecharModal.class, Test_3_ValidaLogin.class })
public class MySuite {

}
