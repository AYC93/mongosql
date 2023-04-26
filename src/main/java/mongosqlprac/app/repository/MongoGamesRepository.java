package mongosqlprac.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import mongosqlprac.app.model.Edited;
import mongosqlprac.app.model.Games;
import mongosqlprac.app.model.Reviews;

@Repository
public class MongoGamesRepository {
    
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
    }
    
    public List<Games> findGamesByName(String name) {
        Query query = new Query();

        Criteria cri = Criteria.where("name").is(name);

        query.addCriteria(cri);

        List<Games> result = mongoTemplate.find(query, Document.class, "games")
                            .stream()
                            .map(d -> Games.createFromDocument(d)).toList();

        return result;
    }

    public List<Games> findGamesByGid(int gid) {
        Query query = new Query();

        Criteria cri = Criteria.where("gid").is(gid);

        query.addCriteria(cri);

        List<Games> result = mongoTemplate.find(query, Document.class, "games")
                            .stream()
                            .map(d -> Games.createFromDocument(d)).toList();

        return result;
    }

    public Games updateGameByRankingAndInt(int ranking, int year){
        Query query = new Query();

        Criteria cri = Criteria.where("year").gt(year)
                        .and("ranking").gte(ranking);

        Update updateOps = new Update()
                        .set("name", "Julius")
                        .set("gid", 999)
                        .set("year", 1111)
                        .set("ranking", 101)
                        .set("image", "https://thumbor.forbes.com/thumbor/fit-in/x/https://www.forbes.com/uk/advisor/wp-content/uploads/2022/04/kanchanara-v31R8jY7eZg-unsplash-scaled.jpg");
        
        query.addCriteria(cri);

        System.out.println("before " + mongoTemplate.findOne(query, Document.class, "games").toString());

        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateOps, Document.class, "games");

        Document d = mongoTemplate.findOne(query, Document.class, "games");

        Games result = Games.createFromDocument(d);

        System.out.println("After " + mongoTemplate.findOne(query, Document.class, "games").toString());

        return result;
    }

    // create new review
    public String createNewReviews(Reviews reviews){
        Document result = mongoTemplate.insert(reviews.toDocument(), "reviews");
        return result.getObjectId("_id").toString();
    }

    public String updateReviews(String objId, Edited e){
        Query query = Query.query(Criteria.where("_id").is(objId));

        Reviews r = Reviews.createFromDocument(mongoTemplate.findOne(query, Document.class , "reviews"));
        /* 
                // r here is returning null object 
        */
        r.getEdited().add(e);
        System.out.println(">>>>" + r);

        // if (r.getEdited().isEmpty()){
        //     r.setEdited(new ArrayList<>());
        // }
        r.getEdited().add(e); // parse in list of edited
        Document result = mongoTemplate.findAndReplace(query, r.toDocument(), "reviews");
        
        return result.getObjectId("_id").toString();
    }

    public boolean checkIfReviewExist(String objId){
        Query query = Query.query(Criteria.where("_id").is(objId));

        Reviews r = mongoTemplate.findOne(query, Reviews.class, "reviews");

        if(r==null){
            return false;
        }
        return true;
    }

}
