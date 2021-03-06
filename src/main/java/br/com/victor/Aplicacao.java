package br.com.victor;

import br.com.victor.dao.CardDAO;
import br.com.victor.model.Card;
import br.com.victor.model.CardModel;
import br.com.victor.model.DeckModel;
import br.com.victor.service.CardService;
import br.com.victor.service.DeckService;
import br.com.victor.utils.Utils;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Aplicacao extends JFrame {
    private JButton button_getDeck = new JButton();
    private JLabel info_getDeck;
    private JPanel mainPanel = new JPanel();
    private JLabel info_getACard;
    private JButton button_drawACard = new JButton();
    private JLabel label_deck_id = new JLabel();
    private JLabel label_restantes = new JLabel();
    private JButton mostrarCompradasButton = new JButton();

    private String deck_id;
    private int restantes;


    public Aplicacao() {

        this.setTitle("BARALHO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        button_getDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeckModel deck = null;
                try {
                    deck = DeckService.pickADeck();
                    deck_id = deck.getDeckId();

                    String stringLabelResulSuccess;
                    String stringTextResult;

                    if (deck.isSuccess()) {
                        stringLabelResulSuccess = "SUCESSO!";
                        stringTextResult = "Sua requisição de um novo Deck foi concluída com sucesso. Aperte no fechar(x) para continuar.";
                        label_deck_id.setText("Deck id: " + deck_id);
                        restantes = deck.getRemaining();
                        label_restantes.setText("Restam: " + restantes + " cartas");
                    } else {
                        stringLabelResulSuccess = "FALHA";
                        stringTextResult = "Não foi possível buscar um baralho novo da API, verifique a internet e tente novamente!";
                    }

                    JFrame frameSucces = new JFrame(stringLabelResulSuccess);
                    JLabel labelSuccess = new JLabel(stringTextResult);
                    frameSucces.add(labelSuccess);
                    frameSucces.setDefaultCloseOperation
                            (JFrame.HIDE_ON_CLOSE);
                    frameSucces.pack();
                    frameSucces.setVisible(true);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println("Deck id: " + deck.getDeckId());
            }
        });
        button_drawACard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EntityManager em = Utils.getEntityManager();
                    CardDAO cardDAO = new CardDAO(em);
                    CardModel cardModel = CardService.drawACard(deck_id);
                    Card card = new Card(cardModel);
                    em.getTransaction().begin();
                    cardDAO.cadastrar(card);
                    Utils.mostraAImagemDaCarta(card.getImage(), card.getSuit(), card.getValue());
                    label_restantes.setText("Restam: " + --restantes + " cartas");
                    em.getTransaction().commit();
                    em.close();


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        mostrarCompradasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntityManager em = Utils.getEntityManager();
                CardDAO cardDAO = new CardDAO(em);
                CardModel cardModel = null;
                List<Card> todas = cardDAO.retornaTodasAsCartasCompradas();

                for (int i = 0; i < todas.size(); i++) {
                    Utils.mostraAImagemDaCarta(todas.get(i).getImage(), todas.get(i).getSuit(), todas.get(i).getValue());
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new Aplicacao();
        frame.setVisible(true);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
        button_getDeck = new JButton();
        button_getDeck.setText("Pegue um deck novo");
        mainPanel.add(button_getDeck, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        info_getDeck = new JLabel();
        info_getDeck.setText("Pressione o botão abaixo para começarmos. Ele irá gerar um baralho que virá embaralhado da API.");
        mainPanel.add(info_getDeck, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        info_getACard = new JLabel();
        info_getACard.setHorizontalAlignment(0);
        info_getACard.setHorizontalTextPosition(0);
        info_getACard.setText("Pressione o botão abaixo depois de criar um deck novo. O botão abaixo irá comprar um carta do Deck já existente.");
        mainPanel.add(info_getACard, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(2);
        label1.setHorizontalTextPosition(2);
        label1.setOpaque(true);
        label1.setText("Informações sobre o Baralho comprado");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 30), null, 0, false));
        label_deck_id = new JLabel();
        label_deck_id.setText("Deck id:");
        mainPanel.add(label_deck_id, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_restantes = new JLabel();
        label_restantes.setText("Restam:");
        mainPanel.add(label_restantes, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        mostrarCompradasButton = new JButton();
        mostrarCompradasButton.setLabel("Mostrar todas cartas compradas.");
        mostrarCompradasButton.setText("Mostrar todas cartas compradas.");
        panel1.add(mostrarCompradasButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button_drawACard = new JButton();
        button_drawACard.setText("Compre uma carta");
        panel1.add(button_drawACard, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
