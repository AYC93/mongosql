package mongosqlprac.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
/* 
    Using the form field, the following document will be inserted into the reviews collection
    {
    user: <name form field>,
    rating: <rating form field>,
    comment: <comment form field>,
    ID: <game id form field>,
    posted: <date>,
    name: <The board game’s name as per ID>
    }
    normally create separate model for new inputs, easier to handle
 */
import java.util.List;

import org.bson.Document;

import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

public class Reviews {
    private String user;
    private int rating;
    private String comment;
    private int id;
    private LocalDate date;
    private String name;
    private List<Edited> editedList;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edited> getEditedList() {
        return editedList;
    }

    public void setEditedList(List<Edited> editedList) {
        this.editedList = editedList;
    }

    public Reviews() {
    }

    public Reviews(String user, int rating, String comment, int id, LocalDate date, String name, List<Edited> editedList) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.id = id;
        this.date = date;
        this.name = name;
        this.editedList = editedList;
    }

    @Override
    public String toString() {
        return "Reviews [user=" + user + ", rating=" + rating + ", comment=" + comment + ", id=" + id + ", date=" + date
                + ", name=" + name + ", edited=" + editedList + "]";
    }

    // array builder for jsonobject, can build tgt with the rest, otherwise if
    // separated written
    // public JsonObject toJsonEdited() {
    // JsonArrayBuilder ab = Json.createArrayBuilder();
    // for (String s : edited) {
    // ab.add(s);
    // }
    // // JsonObject jObjEdited = Json.createObjectBuilder()
    // // .add("edited", ab)
    // // .build();
    // return Json.createObjectBuilder()
    // .add("edited", ab)
    // .build();
    // }

    // toJson with edited list
    public JsonObject toJsonWEdited() {

        JsonObjectBuilder jObj = Json.createObjectBuilder()
                .add("user", user)
                .add("rating", rating)
                .add("comment", comment)
                .add("id", id)
                .add("name", name)
                .add("posted", LocalDate.now().toString());

        // if values exists in edited comments array, build
        if(editedList.size()!=0){
            JsonArrayBuilder ab = Json.createArrayBuilder();
            for (Edited e : getEditedList())
                // toJson object to add inside array builder
                ab.add(e.toJson());
            // add into overall obj builder
            jObj.add("edited", ab);
        }
        return jObj.build();
    }

    public static Reviews createFromDocument(Document d){ // reading from Document
        Reviews r = new Reviews();
        Edited e = new Edited();
        r.setUser(d.getString("user"));
        r.setRating(d.getInteger("rating"));
        r.setComment(d.getString("comment"));
        r.setId(d.getInteger("id"));
        r.setName(d.getString("name"));
        r.setDate(LocalDate.parse(d.getString("posted")));
        
        // to parse obj into list
        List<Edited> editedList = new ArrayList<>();
        List<Document> editedDocs = d.getList("edited", Document.class);
        if (editedDocs != null) {
            for (Document editedDoc : editedDocs) {
                editedList.add(e.parseExistingDocument(editedDoc));
            }
        }
        r.setEditedList(editedList);
        return r;
    }

    public Document toDocument() {
        return Document.parse(toJsonWEdited().toString());
    }


}
