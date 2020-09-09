package carsale.store;

import org.hibernate.query.Query;
import carsale.model.Author;

import java.util.List;

public class AuthorDB {

    private AuthorDB() {
    }

    public static Author save(Author author) {
        ModelDB.execGet(session -> session.save(author));
        return author;
    }

    public static Author update(Author author) {
        ModelDB.exec(session -> session.update(author));
        return author;
    }

    public static Author saveOrUpdate(Author author) {
        ModelDB.exec(session -> session.saveOrUpdate(author));
        return author;
    }

    public static Author get(int id) {
        return ModelDB.execGet(session -> session.get(Author.class, id));
    }

    public static Author get(String email) {
        return ModelDB.execGet(session -> {
            Query<Author> q = session.createQuery("FROM carsale.model.Author WHERE email = ?1");
            q.setParameter(1, email);
            List<Author> list = q.list();
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        });
    }

    public static List<Author> getAll() {
        return ModelDB.execGet(session -> session.createQuery("FROM carsale.model.Author").list());
    }
}
