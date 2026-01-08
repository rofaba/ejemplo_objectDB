package org.example;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
        //reemplazado por data provider

        Game g = new Game();
        g.setName("Next Game");
        g.setDescription("Game add User");
        g.setPlatform("XBOX");
        g.setYear(2022);
        g.setImage("my_game_image.png");

        User u = new User();
        u.setUsername("john_doe");
        u.setPassword("securepassword");
        u.setEmail("jd@mail.com");

        // EJEMPLO DE ASOCIACION
        User u2 = new User();
        u.setUsername("Carlos_Gamer");

        // Juegos
        Game g1 = new Game();
        g1.setName("Zelda: TotK");
        g1.setPlatform("Switch");
        g1.setDuenio(u2);

        Game g2 = new Game();
        g2.setName("Halo Infinite");
        g2.setPlatform("Xbox");
        g2.setDuenio(u2);

        // Añadimos los juegos a la lista del usuario
        u2.getJuegos().add(g1);
        u2.getJuegos().add(g2);


        EntityManager entityManager = DataProvider.getEntityManagerFactory().createEntityManager();

        try {
            entityManager.getTransaction().begin();
            //entityManager.persist(g);   para ejemplo de asociación, si guardan usuario se guardan los juegos

            entityManager.persist(u2);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Consulta para obtener todos los juegos
        try {

            TypedQuery<Game> query = entityManager.createQuery("SELECT g FROM Game g", Game.class);

            List<Game> listaJuegos = query.getResultList();
            System.out.println("LISTADO DE JUEGOS EN LA BASE DE DATOS");
            for (Game juego : listaJuegos) {
                System.out.println(juego);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // Cerramos el EntityManager
            entityManager.close();
        }
        DataProvider.close();
    }
}

