package edu.gdou.usermanage.controller;

import edu.gdou.usermanage.entity.Announcement;
import edu.gdou.usermanage.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @GetMapping("/queryAnnouncement")
    public String queryAnnouncement(){
        return "announcement_fine";
    }

    @RequestMapping("queryAnnouncementL")
    @ResponseBody
    public List<Announcement> queryAnnouncementL(){
        List<Announcement> list = announcementService.selectAll();
        System.out.println("!!!!!!!!!"+list);
        return list;
    }
}
