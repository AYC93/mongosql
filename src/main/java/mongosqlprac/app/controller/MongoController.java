package mongosqlprac.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mongosqlprac.app.model.Games;
import mongosqlprac.app.service.MongoGamesService;

@Controller
@RequestMapping
public class MongoController {
    
    @Autowired
    MongoGamesService mongoGamesSvc;

    @Autowired
    MongoGamesService mongoGamesService;

    @GetMapping("/mongoFind")
    public String readAllGames(Model m, @RequestParam int page, @RequestParam int limit){
        m.addAttribute("page", page);
        m.addAttribute("limit", limit);
        // sorted in ascending order by gid
        m.addAttribute("games", mongoGamesSvc.getAllGamesInPagesM(page, limit));

        return "page";
    }

    /* e.g. http://localhost:8080/name?name=Tal%20der%20K%C3%B6nige */
    @GetMapping("/name")
    public String readGamesWithName(Model m, @RequestParam String name){
        m.addAttribute("name", name);
        // sorted in ascending order by gid
        m.addAttribute("games", mongoGamesSvc.findGamesByName(name));

        return "name";
    }

    /* http://localhost:8080/id?gid=2 */
    @GetMapping("/id")
    public String readGamesWithId(Model m, @RequestParam int gid, @ModelAttribute Games g){
        m.addAttribute("gid", gid);
        // sorted in ascending order by gid
        m.addAttribute("games", mongoGamesSvc.findGamesByGid(gid));

        return "name";
    }

    // http://localhost:8080/update/?ranking=20&year=1998
    @GetMapping("/update/")
    public String updateGamesWithRankingAndYear(Model m, @RequestParam int ranking, 
                            @RequestParam int year){
        m.addAttribute("ranking", ranking);
        m.addAttribute("year", year);

        m.addAttribute("games", mongoGamesSvc.updateGameByRankingAndInt(ranking, year));

        return "update_yr_rank";
    }


}
