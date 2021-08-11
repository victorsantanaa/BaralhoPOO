package br.com.victor.dao;

import br.com.victor.model.DeckModel;

import javax.persistence.EntityManager;

public class DeckDAO {

    private EntityManager em;

    public DeckDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(DeckModel deck) {
        this.em.persist(deck);
    }

    public void atualizar(DeckModel deck) {
        this.em.merge(deck);
    }

    public void remover(DeckModel deck) {
        deck = em.merge(deck);
        this.em.remove(deck);
    }
}
