package carsale.model;

import java.util.GregorianCalendar;

public class BeautyItem {
    private Integer id;
    private String desc;
    private String created;
    private String done;
    private String author;
    private String brand;
    private String model;
    private String body;
    private String photo;

    public BeautyItem(Item item) {
        id = item.getId();
        desc = item.getDesc();
        created = getDate(item.getCreated());
        done = getDate(item.getDone());
        author = getRequisite(item.getAuthor());
        brand = getRequisite(item.getBrand());
        model = getRequisite(item.getModel());
        body = getRequisite(item.getBody());
        photo = getRequisite(item.getPhoto());
    }

    private String getDate(GregorianCalendar gc) {
        if (gc == null) {
            return "";
        }
        return gc.getTime().toString();
    }

    private <T> String getRequisite(T t) {
        if (t == null) {
            return "";
        }
        return t.toString();
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

    @Override
    public String toString() {
        return "BeautyItem{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", created='" + created + '\'' +
                ", done='" + done + '\'' +
                ", author='" + author + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", body='" + body + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
