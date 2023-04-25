package mongosqlprac.app.model;

import org.bson.Document;

public class Games {
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private int users_rated;

    public int getUsers_rated() {
        return users_rated;
    }

    public void setUsers_rated(int users_rated) {
        this.users_rated = users_rated;
    }

    private String url;
    private String image;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Games() {
    }

    public Games(int gid, String name, int year, int ranking, int users_rated, String url, String image) {
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.users_rated = users_rated;
        this.url = url;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Games [gid=" + gid + ", name=" + name + ", year=" + year + ", ranking=" + ranking + ", users_rated="
                + users_rated + ", url=" + url + ", image=" + image + "]";
    }

    public static Games createFromDocument(Document d){ // reading from Document
        Games g = new Games();
        g.setGid(d.getInteger("gid"));
        g.setName(d.getString("name"));
        g.setYear(d.getInteger("year"));
        g.setRanking(d.getInteger("ranking"));
        g.setUsers_rated(d.getInteger("users_rated"));
        g.setUrl(d.getString("url"));
        g.setImage(d.getString("image"));

        return g;
    }




}
