package mongosqlprac.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mongosqlprac.app.service.GamesService;

@Controller
@RequestMapping
public class MongoController {
    
    @Autowired
    GamesService gamesSvc;

    @GetMapping("/mongoFind")
    public String readAllGames(Model m, @RequestParam int page, @RequestParam int limit){
        m.addAttribute("page", page);
        m.addAttribute("limit", limit);
        // sorted in ascending order by gid
        m.addAttribute("games", gamesSvc.getAllGamesInPagesM(page, limit));

        return "page";
    }

}
