package tools;

import panels.JCanvasPanel;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.text.DecimalFormat;

public class Utils {

    private final DecimalFormat df = new DecimalFormat("###.##");
    private DataManager dm;
    private JCanvasPanel canvasPanel;


    public Utils(DataManager dm, JCanvasPanel canvasPanel) {
        this.dm = dm;
        this.canvasPanel = canvasPanel;
    }

    public void init(int initPixel){

        int width = 601;
        int height = 330;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY);
        for(int i =0; i< width; i++)
            for(int j =0; j<height; j++)
                img.setRGB(i, j, Color.white.getRGB());
        img.setRGB(initPixel, 0, Color.black.getRGB());
        dm.setImage(img);
        canvasPanel.repaint();

    }

    public void CA(int rule, boolean cond){

        int [] ruleset = parseBinary(rule);

        for(int i =0; i< dm.getHeight()-1; i++)
            for(int j =0; j<dm.getWidth(); j++){
                int prev, current, next;

                if(j-1 < 0) {
                    if(!cond)
                        prev = getBinaryCode(dm.getCopedImage().getRGB(dm.getWidth() - 1, i));
                     else
                        prev = getBinaryCode(Color.white.getRGB());

                }else
                    prev = getBinaryCode(dm.getCopedImage().getRGB(j-1, i));

                current = getBinaryCode(dm.getCopedImage().getRGB(j, i));

                if(j+1 == dm.getWidth()) {
                    if(!cond)
                        next= getBinaryCode(dm.getCopedImage().getRGB(0, i));
                    else
                        next= getBinaryCode(Color.white.getRGB());

                }else
                    next = getBinaryCode(dm.getCopedImage().getRGB(j+1, i));

                int state = checkState(prev, current, next, ruleset);
                int newColor;
                if(state == 0)
                    newColor = Color.white.getRGB();
                else
                    newColor = Color.black.getRGB();
                dm.getCopedImage().setRGB(j, i+1, newColor);

                canvasPanel.repaint();
            }


    }

    private int getBinaryCode(int color){
        if(Color.white.getRGB() == color)
            return 0;
        else
            return 1;

    }
    private int[] parseBinary(int num){
        String bin = Integer.toBinaryString(num);
        String []  strings = ("0".repeat(8 - bin.length()) +bin).split("");
        int [] rule = new int[strings.length];
        int i = 0;
        for(String str:strings){
            rule[i]=Integer.parseInt(str);//Exception in this line
            i++;
        }
        return rule;
    }

    private int checkState(int prev, int current, int next, int [] ruleset){
        if      (prev == 1 && current == 1 && next == 1) return ruleset[0];
        else if (prev == 1 && current == 1 && next == 0) return ruleset[1];
        else if (prev == 1 && current == 0 && next == 1) return ruleset[2];
        else if (prev == 1 && current == 0 && next == 0) return ruleset[3];
        else if (prev == 0 && current == 1 && next == 1) return ruleset[4];
        else if (prev == 0 && current == 1 && next == 0) return ruleset[5];
        else if (prev == 0 && current == 0 && next == 1) return ruleset[6];
        else if (prev == 0 && current == 0 && next == 0) return ruleset[7];
        return 0;
    }
    private BufferedImage copyImage(){
        ColorModel cm = dm.getCopedImage().getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = dm.getCopedImage().copyData(null);
        BufferedImage img = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


}
