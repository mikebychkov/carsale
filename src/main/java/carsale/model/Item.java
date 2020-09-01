package carsale.model;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="description")
    private String desc;
    private GregorianCalendar created;
    private GregorianCalendar done;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrand brand;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel model;

    @ManyToOne
    @JoinColumn(name = "body_id")
    private CarBodyType body;

    private String color;

    public Item() {
    }

    public Item(String desc) {
        this.desc = desc;
    }

    public Item(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
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

    public GregorianCalendar getCreated() {
        return created;
    }

    public void setCreated(GregorianCalendar created) {
        this.created = created;
    }

    public GregorianCalendar getDone() {
        return done;
    }

    public void setDone(GregorianCalendar done) {
        this.done = done;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public CarBodyType getBody() {
        return body;
    }

    public void setBody(CarBodyType body) {
        this.body = body;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
