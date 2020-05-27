package view.components;

import controller.helper.Resolution;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.MainView;

public class OptionMenuSTM extends JPanel {

    private final String text;

    private final JPanel jpanel1 = new JPanel();
    private final JPanel jpanel2 = new JPanel();
    private final JLabel jlabel1 = new JLabel();

    public OptionMenuSTM(String placeHolder, Point size, JPanel jpanel){
        this.text = placeHolder;
        createPanel(placeHolder,size,jpanel);
    }

    private void createPanel(String placeHolder, Point size, JPanel jpanel){

        //PAINELOUT
        this.setOpaque(false);
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setLayout(null);

        //FOCUSPANEL
        jpanel1.setBackground(Color.WHITE);
        jpanel1.setLocation(0, 0);
        Dimension jpanel1Size = new Dimension(Resolution.getInstance().getPointMarginX(size, 3).x, size.y);
        jpanel1.setSize(jpanel1Size);
        jpanel1.setLayout(null);
        this.add(jpanel1);

        //PAINEL CENTRAL
        jpanel2.setBackground(Color.WHITE);
        jpanel2.setLocation(jpanel1Size.width, 0);
        Dimension jpanel2Size = new Dimension(Resolution.getInstance().getPointMarginX(size, 97).x, size.y);
        jpanel2.setSize(jpanel2Size);
        jpanel2.setLayout(null);
        this.add(jpanel2);
        jpanel2.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            if(!jpanel2.isFocusOwner())
            {
                jpanel1.setBackground(new Color(245, 245, 245));
            }
                jpanel2.setBackground(new Color(245, 245, 245)); 
            }
        public void mouseExited(MouseEvent evt) {
            if(!jpanel2.isFocusOwner())
            {
                jpanel1.setBackground(new Color(255, 255, 255)); 
            }
            jpanel2.setBackground(new Color(255, 255, 255)); 
        }
        public void mouseClicked(MouseEvent evt) {
            jpanel2.requestFocus();
            MainView.getInstance().repaint();
            MainView.getInstance().switchPanels(jpanel);
        }});    
        jpanel2.addFocusListener(new FocusListener() {    
            public void focusGained(FocusEvent evt) {
                jpanel1.setBackground(Color.BLUE);
            }
            public void focusLost(FocusEvent evt) {
                jpanel1.setBackground(Color.WHITE);
            }
        });

        //PLACEHOLDER
        jlabel1.setText(placeHolder);
        jlabel1.setFont(new Font("Lucida Sans Unicode", 1, 14));
        jlabel1.setForeground(Color.GRAY);
        jlabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jlabel1.setBorder(null);
        jlabel1.setLocation(Resolution.getInstance().getPointMarginX(size, 15).x,0);
        jlabel1.setSize(Resolution.getInstance().getDimension(size));
        jlabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpanel2.add(jlabel1);

    }

}
