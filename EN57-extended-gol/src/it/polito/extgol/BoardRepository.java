package it.polito.extgol;

import jakarta.persistence.EntityManager;

public class BoardRepository  extends GenericExtGOLRepository<Board, Long> {
    public BoardRepository()  { super(Board.class);  }

    public Board load(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                            "SELECT DISTINCT b FROM Board b " +
                                    "LEFT JOIN FETCH b.tiles t " +
                                    "LEFT JOIN FETCH t.cell " +
                                    "WHERE b.id = :id", Board.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        finally{
            em.close();
        }
    }
}

