package mongosqlprac.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mongosqlprac.app.model.Games;
import mongosqlprac.app.repository.GamesRepository;

@Service
public class GamesService {
    
    @Autowired
    GamesRepository gamesRepository;

    public List<Games> getAllGamesInPagesM(int page, int limit){
        return gamesRepository.getAllGamesInPagesM(page, limit);
    }

    public List<Games> findGamesByName(String name) {
        return gamesRepository.findGamesByName(name);
    }
}
