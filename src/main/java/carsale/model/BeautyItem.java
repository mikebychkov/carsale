package carsale.model;

import java.util.GregorianCalendar;

public class BeautyItem {
    private Integer id;
    private String desc;
    private String created;
    private String done;
    private String author;

    public BeautyItem(Item item) {
        id = item.getId();
        desc = item.getDesc();
        created = getDate(item.getCreated());
        done = getDate(item.getDone());
        author = getAuthor(item.getAuthor());
    }

    private String getDate(GregorianCalendar gc) {
        if (gc == null) {
            return "";
        }
        return gc.getTime().toString();
    }

    private String getAuthor(Author author) {
        if (author == null) {
            return "";
        }
        return author.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
