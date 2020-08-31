package carsale.store;

import org.hibernate.query.Query;
import carsale.model.Author;

import java.util.List;

public class AuthorDB {

    private AuthorDB() {
    }

    public static Author save(Author author) {
        ModelDB.tx(session -> session.save(author));
        return author;
    }

    public static Author update(Author author) {
        ModelDB.tx(session -> {session.update(author); return 0;});
        return author;
    }

    public static Author saveOrUpdate(Author author) {
        ModelDB.tx(session -> {session.saveOrUpdate(author); return 0;});
        return author;
    }

    public static Author get(int id) {
        return ModelDB.tx(session -> session.get(Author.class, id));
    }

    public static Author get(String email) {
        return ModelDB.tx(session -> {
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
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.Author").list());
    }
}
