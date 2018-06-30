package springBootFirst;

import hw.Application;
import hw.model.User;
import hw.service.interfaces.IUserMapperServ;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RunAs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("prod")
public class ApplicationTest {

    @Autowired
//    UserMapperServImpl userMapperServImpl;
    IUserMapperServ userMapperServ;
    private User user;
    SimpleDateFormat sdf;

//    @Before
//    @Test
    public void testAddUser() {
    	String birStr = "1991-05-09 11:49:45";
    	user=new User();
    	user.setAge(19);
    	user.setName("何伟");
//    	user.setBir(new Timestamp(new Date().getTime()));
    	user.setBir(Timestamp.valueOf(birStr));
    	Long startTime = System.currentTimeMillis();
//    	userMapperServImpl.addUser(user);
    	userMapperServ.addUser(user);
    	System.out.println(user.toString());
        System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testAllUsers() throws Exception {
    	Long startTime = System.currentTimeMillis();
        List<User> ulist1 = userMapperServ.allUsers();
        System.out.println(ulist1.toString());
        System.out.println("第一次耗时:" + (System.currentTimeMillis() - startTime));
        
        startTime = System.currentTimeMillis();
        ulist1 = userMapperServ.allUsers();
        System.out.println(ulist1.toString());
        System.out.println("第二次耗时:" + (System.currentTimeMillis() - startTime));
    }
//    @Test
    public void testGetAgeById() throws Exception {
    	Long startTime = System.currentTimeMillis();
//    	Integer age = userMapperServImpl.getAgeById(1);
    	Integer age = userMapperServ.getAgeById(1);
    	System.out.println(age);
    	System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
    }
//    @Test
    public void testGetUserById() throws Exception {
    	Long startTime = System.currentTimeMillis();
    	User user = userMapperServ.getUserById(1);
    	System.out.println(user.toString());
    	System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
    }
//    @Test
    public void testTotal() throws Exception {
    	Long startTime = System.currentTimeMillis();
//    	Integer count = userMapperServImpl.total();
    	Integer count = userMapperServ.total();
    	System.out.println(count);
    	System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
    }
//    @Test
    public void testGetUsersByName() throws Exception {
    	Long startTime = System.currentTimeMillis();
//    	List<User> users = userMapperServImpl.getUsersByName("he");
    	List<User> users = userMapperServ.getUsersByName("he");
    	for (User user : users) {
    		System.out.println(user.toString());
		}
    	System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
    }
//    @SuppressWarnings("deprecation")
//	@Test
    public void testUpdateUser() throws Exception {
//    	User user=new User(10,"hw",23,new Date("1991-08-25"));
    	Long startTime = System.currentTimeMillis();
//    	Boolean secced = userMapperServImpl.updateUser(1, "hhh");
    	Boolean secced = userMapperServ.updateUser(1, "hhh");
    	System.out.println(secced.toString());
    	System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
    }

}
