package br.com.victor.dao;

import br.com.victor.model.Card;
import br.com.victor.model.DeckModel;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class CardDAO {

    private EntityManager em;

    public CardDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Card card) {
        this.em.persist(card);
    }

    public void atualizar(Card card) {
        this.em.merge(card);
    }

    public void remover(Card card) {
        card = em.merge(card);
        this.em.remove(card);
    }

    public Card buscarPorId(Long id) {
        return em.find(Card.class, id);
    }

    public Card buscarPorCodigo(String code) {
        String jpql = "SELECT p FROM Card p WHERE p.code = :code";
        return em.createQuery(jpql, Card.class).setParameter("code", code).getSingleResult();
    }

    public List<Card> buscarPorSuit(String suit) {
        String jpql = "SELECT p FROM Card p WHERE p.suit = :suit";
        return em.createQuery(jpql, Card.class).setParameter("suit", suit).getResultList();
    }

    public List<Card> buscarPorValor(String value) {
        String jpql = "SELECT p FROM Card p WHERE p.value = :value";
        return em.createQuery(jpql, Card.class).setParameter("value", value).getResultList();
    }

    public String retornaUrlDaImagem(String code){
        String jpql = "SELECT p.image FROM Card p WHERE p.code = :code";
        return em.createQuery(jpql, String.class).setParameter("code", code).getSingleResult();
    }

    public int retornaQuantasCartasJaCompradas() {
        String jpql = "SELECT p FROM Card p ";
        return em.createQuery(jpql, Card.class).getResultList().size();
    }

    public List<Card> retornaTodasAsCartasCompradas() {
        String jpql = "SELECT p FROM Card p ";
        return em.createQuery(jpql, Card.class).getResultList();
    }

    public String[] retornaListaDeNomes(){
        List<Card> list = retornaTodasAsCartasCompradas();

        List<String> resultado = Arrays.asList("");
        for (int i = 0; i < list.size(); i++) {
            resultado.add(list.get(i).getValue() + " " + list.get(i).getSuit());
        }

        return resultado.toArray(new String[0]);
    }
}
