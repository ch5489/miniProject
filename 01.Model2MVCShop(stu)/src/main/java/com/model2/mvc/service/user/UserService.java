package com.model2.mvc.service.user;

import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.User;


public interface UserService {
	
	public void addUser(User user) throws Exception;
	
	public User loginUser(User user) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public Map<String, Object> getUserList(SearchVO searchVO) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
}