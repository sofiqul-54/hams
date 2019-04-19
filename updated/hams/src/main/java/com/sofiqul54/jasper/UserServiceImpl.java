package com.sofiqul54.jasper;



import com.sofiqul54.entity.User;
import com.sofiqul54.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result= new ArrayList<Map<String, Object>>();
        for (User user : userRepo.findAll()){
            Map<String, Object> item=new HashMap<String, Object>();
            item.put("id", user.getId());
            item.put("firstName", user.getFirstName());
            item.put("mobile", user.getMobile());
            item.put("lastName", user.getLastName());
            item.put("userName", user.getUserName());

            result.add(item);
        }
        return result;
    }
}
