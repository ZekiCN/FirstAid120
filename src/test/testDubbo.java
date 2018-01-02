//import com.hc.thrift.api.UserInfo.ReObj.ReListUserInfoPojo;
//import com.hc.thrift.api.UserInfo.ReObj.ReListUserRoleInfoPojo;
//import com.hc.thrift.api.UserInfo.UserInfoPojo;
//import com.hc.thrift.api.UserInfo.UserInfoService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.junit.Before;
///**
// * Created by Administrator on 2017/4/25.
// */
//public class testDubbo {
//
//    private UserInfoService.Iface userInfoService;
//
//    @Before
//    public void init() throws Exception{
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:/appConf/dubbo-consumer.xml", "classpath:/appConf/spring-contextSystemSet.xml"});
//        userInfoService = (UserInfoService.Iface) context.getBean("userInfoService");
//    }
//
//    @Test
//    public void testSelect()throws Exception{
//        String prjCode = "prjCode_PR_common";
//        UserInfoPojo pojo = new UserInfoPojo();
//        ReListUserInfoPojo reList = userInfoService.select(prjCode, pojo, 1, 5);
//        System.out.println(reList.toString());
//    }
//
//
//}
