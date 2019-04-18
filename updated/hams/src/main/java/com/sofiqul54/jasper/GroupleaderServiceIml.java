package com.sofiqul54.jasper;



import com.sofiqul54.entity.Groupleader;
import com.sofiqul54.repo.GroupleaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("groupleaderService")
public class GroupleaderServiceIml implements GroupleaderService {

    @Autowired
   private GroupleaderRepo groupleaderRepo;



    @Override
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Groupleader groupleader : groupleaderRepo.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", groupleader.getId());
            item.put("leadername", groupleader.getLeaderName());
            item.put("commission", groupleader.getCommission());
            //item.put("taskCriticalLevel", task.getTaskCriticalLevel().getLevel());
            // item.put("projectModule", task.getProjectModule().getTitle());
            // item.put("company", task.getCompany().getCompanyName());
            item.put("district", groupleader.getDistrict());
            result.add(item);
        }
        return result;
    }
}
