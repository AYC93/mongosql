package mongosqlprac.app.model;

import java.time.LocalDate;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Edited {
    private String comment;
    private int rating;
    private LocalDate posted;

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public LocalDate getPosted() {
        return posted;
    }
    public void setPosted(LocalDate posted) {
        this.posted = posted;
    }

    public Edited() {
    }
    
    public Edited(String comment, int rating, LocalDate posted) {
        this.comment = comment;
        this.rating = rating;
        this.posted = posted;
    }
    
    @Override
    public String toString() {
        return "Edited [comment=" + comment + ", rating=" + rating + ", posted=" + posted + "]";
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
                    .add("comment", comment)
                    .add("rating", rating)
                    .add("posted", LocalDate.now().toString())
                    .build();
    }

    public Document toDocument(){
        return Document.parse(toJson().toString());
    }

    public Edited parseExistingDocument(Document d){
        return new Edited(
            d.getString("comment"),
            d.getInteger("rating"),
            LocalDate.parse(d.getString("posted")));
    }
   
}
