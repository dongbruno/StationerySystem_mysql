package citi.service.user;

import citi.entity.User;

public interface UserService {
//servlet-api.jar
	User getUser(HttpSession session);

}
