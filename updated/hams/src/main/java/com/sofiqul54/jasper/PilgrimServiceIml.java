package com.sofiqul54.jasper;



import com.sofiqul54.entity.Groupleader;
import com.sofiqul54.entity.Pilgrim;
import com.sofiqul54.repo.GroupleaderRepo;
import com.sofiqul54.repo.PilgrimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pilgrimService")
public class PilgrimServiceIml implements PilgrimService {

    @Autowired
   private PilgrimRepo pilgrimRepo;



    @Override
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Pilgrim pilgrim: pilgrimRepo.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", pilgrim.getId());
            item.put("pilgrimNumber", pilgrim.getPilgrimNumber());
            item.put("name", pilgrim.getName());
            item.put("district", pilgrim.getGender());
            item.put("email", pilgrim.getEmail());
            item.put("district", pilgrim.getDistrict());
            item.put("bookingAmount", pilgrim.getBookingAmount());
            result.add(item);
        }
        return result;
    }
}
