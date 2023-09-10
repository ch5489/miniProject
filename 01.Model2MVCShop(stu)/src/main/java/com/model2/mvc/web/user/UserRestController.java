package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public UserRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	//@RequestMapping(value = "addUser", method = RequestMethod.GET)
	public String addUser() throws Exception{
		
		System.out.println("/user/addUser : GET");
		
		return "redirect:/user/addUserView.jsp";
	}
	
	@RequestMapping(value = "json/addUser", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) throws Exception{
		
		System.out.println("/user/json/addUser : POST");
		
		userService.addUser(user);
		
		
		return userService.getUser(user.getUserId());
	}
	
	
	@RequestMapping(value="json/getUser/{userId}", method=RequestMethod.GET)
	public User getUser(@PathVariable String userId) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		
		return userService.getUser(userId);
	}
	
	
	@RequestMapping(value="json/updateUser/{userId}", method=RequestMethod.GET)
	public User updateUser(@PathVariable String userId) throws Exception{
		
		
		System.out.println("/user/json/updateUser : GET");
		
		User user = userService.getUser(userId);
		
		return user;
	}
	
	@RequestMapping(value="json/updateUser", method=RequestMethod.POST)
	public User updateUser( @RequestBody User user , HttpSession session) throws Exception{
		
		System.out.println("/user/json/updateUser : POST");
		//System.out.println("---------디버그그그그그11--------");
		userService.updateUser(user);
		//System.out.println("---------디버그그그그그22--------");
//		String sessionId = ((User)session.getAttribute("user")).getUserId();
//		if(sessionId.equals(user.getUserId())) {
//			session.setAttribute("user", user);
//			
//		}
		
		return userService.getUser(user.getUserId());
	}
	
	//@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() throws Exception{
		
		System.out.println("/user/logon : GET");
		
		return "redirect:/user/loginView.jsp";
	}
	
	@RequestMapping( value="json/login", method=RequestMethod.POST)
	public User login(@RequestBody User user , HttpSession session) throws Exception{
		
		System.out.println("/user/json/login : POST");
		
		System.out.println("::"+user);
		User dbUser = userService.getUser(user.getUserId());
		if(user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	
	//@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		System.out.println("/user/logout : POST");
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="json/checkDuplication", method=RequestMethod.POST)
	public Map checkDuplication( @RequestBody String userId ) throws Exception{
		
		System.out.println("/user/json/checkDuplication : POST");
		
		System.out.println(userId);
		
		Map map = new HashMap();
		//System.out.println("---------디버그그그그그11--------");
		boolean result = userService.checkDuplication(userId);
		//System.out.println("---------디버그그그그그22--------");
		map.put("result",new Boolean(result));
		//System.out.println("---------디버그그그그그33--------");
		map.put("userId",userId);
		
		return map;
	}
//	@RequestMapping(value="json/listUser/{search}")
	@RequestMapping(value="json/listUser")
	public Map listUser( @ModelAttribute("search")  Search searchGet ,@RequestBody Search searchPost, HttpServletRequest request) throws Exception{
		
		Search search = (searchGet.getCurrentPage() == 0? searchPost:searchGet);
		System.out.println("/user/json/listUser : GET / POST");
		//System.out.println("/////////////////"+search.getCurrentPage());
		System.out.println(search.getCurrentPage());
		if(search.getCurrentPage() ==0){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("---------디버그그그그그22--------");
		// Business logic 수행
		Map<String , Object> map=userService.getUserList(search);
		Map returnmap = new HashMap();
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		returnmap.put("list", map.get("list"));
		returnmap.put("resultPage", resultPage);
		returnmap.put("search", search);
		
		return returnmap;
	}
	
}
