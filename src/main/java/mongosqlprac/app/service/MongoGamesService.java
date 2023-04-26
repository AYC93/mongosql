package mongosqlprac.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mongosqlprac.app.model.Edited;
import mongosqlprac.app.model.Games;
import mongosqlprac.app.model.Reviews;
import mongosqlprac.app.repository.MongoGamesRepository;

@Service
public class MongoGamesService {
    
    @Autowired
    MongoGamesRepository mongoGamesRepository;

    public List<Games> getAllGamesInPagesM(int page, int limit){
        return mongoGamesRepository.getAllGamesInPagesM(page, limit);
    }

    public List<Games> findGamesByName(String name) {
        return mongoGamesRepository.findGamesByName(name);
    }

    public List<Games> findGamesByGid(int gid) {
        return mongoGamesRepository.findGamesByGid(gid);
    }

    public Games updateGameByRankingAndInt(int ranking, int year){
        return mongoGamesRepository.updateGameByRankingAndInt(ranking, year);
    }

    // create new review
    public String createNewReviews(Reviews reviews){
        return mongoGamesRepository.createNewReviews(reviews);
    }
    
    public String updateReviews(String objId, Edited e){
        return mongoGamesRepository.updateReviews(objId, e);
    }

    public boolean checkIfReviewExist(String objId){
        return mongoGamesRepository.checkIfReviewExist(objId);
    }
}
