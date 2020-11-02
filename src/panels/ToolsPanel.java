package panels;

import elements.Slider;
import tools.Utils;
import layout.GBC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ToolsPanel extends JPanel {

    private Utils utils;
    private JButton btnStart;
    private Slider sliderRule;
    private JTextField jTextFieldRule;
    private Slider sliderPosPixel;
    private JTextField jTextFieldPosPixel;
    private JComboBox jComboBoxCond;
    private String[] comboBoxData = {"periodic", "white cells"} ;

    public ToolsPanel(Utils utils) {
        this.setPreferredSize(new Dimension(621, 321));
        this.setLayout(new GridBagLayout());
        this.utils = utils;
    }

    public void addTools(){

          btnStart = new JButton("Start");
          sliderRule = new Slider(0,255, 1, 50);
          jTextFieldRule = new JTextField("0");
          jTextFieldRule.setFont(new Font("Serif", Font.PLAIN, 20));
          sliderPosPixel = new Slider(0,601, 1, 200);
          jTextFieldPosPixel = new JTextField("301");
          jTextFieldPosPixel.setFont(new Font("Serif", Font.PLAIN, 20));
          jComboBoxCond = new JComboBox(comboBoxData);

          //set grid
          this.add(sliderRule, new GBC(0, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(jTextFieldRule, new GBC(0, 1, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(sliderPosPixel, new GBC(0, 2, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(jTextFieldPosPixel, new GBC(0, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(jComboBoxCond, new GBC(0, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnStart, new GBC(0, 5, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));




        btnStart.addActionListener(e -> {
                boolean condition = true;
                utils.init(sliderPosPixel.getValue());
                if(jComboBoxCond.getSelectedIndex() == 0)
                    condition = !condition;
                    utils.CA(sliderRule.getValue(), condition);

        });

        sliderRule.addChangeListener(e -> {
            jTextFieldRule.setText(""+sliderRule.getValue());
        });


        jTextFieldRule.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    int val = Integer.parseInt(jTextFieldRule.getText());
                    if (val >= 0 && val <= 255) {
                        sliderRule.setValue(val);
                    } else {
                        sliderRule.setValue(0);
                    }
                }catch(Exception err){
                    sliderRule.setValue(0);
                }
            }
        });


        sliderPosPixel.addChangeListener(e -> {
            jTextFieldPosPixel.setText(""+sliderPosPixel.getValue());
        });


        jTextFieldPosPixel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    int val = Integer.parseInt(jTextFieldPosPixel.getText());
                    if (val >= 0 && val <= 601) {
                        sliderPosPixel.setValue(val);
                    } else {
                        sliderPosPixel.setValue(0);
                    }
                }catch(Exception err){
                    sliderPosPixel.setValue(0);
                }
            }
        });

    }

    private void setEnableBtn(boolean val){

    }
}
