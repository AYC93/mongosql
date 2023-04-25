package mongosqlprac.app.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import mongosqlprac.app.model.Games;

@Repository
public class GamesRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    /* pagination that works */
    public List<Games> getAllGamesInPagesM(int page, int limit){
        Query query = new Query();

        query.skip( (page - 1) * limit);
        query.limit(limit);

        // cannot use games.gid, just the field name is sufficient
        query.with(Sort.by(Direction.ASC,"gid"));

        List<Games> result = mongoTemplate.find(query, Document.class, "games").stream()
                    .map(d -> Games.createFromDocument(d)).toList();

        return result;

    public Ga createNewEntry() {
        
    }

    }

    
}
