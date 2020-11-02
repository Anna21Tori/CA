import tools.DataManager;
import tools.Utils;
import panels.JCanvasPanel;
import panels.ToolsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main extends JFrame {

    private JPanel mainPanel;
    private JPanel mapPanel;
    private ToolsPanel toolsPanel;
    private JCanvasPanel canvas;
    private DataManager dm;
    private Utils utils;

    private void layoutApp(){
        //==============================================================================================================


        mainPanel = new JPanel();
        toolsPanel = new ToolsPanel(this.utils);

        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        //==============================================================================================================

        toolsPanel.addTools();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(canvas, BorderLayout.NORTH);
        mainPanel.add(toolsPanel, BorderLayout.SOUTH);
       // mainPanel.add(hist, BorderLayout.WEST);
        //==============================================================================================================

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(mainPanel);
        this.setSize(new Dimension(721, 800));
        this.setLocationRelativeTo(null);
    }

    public Main(String title) {

        super(title);


        this.dm = new DataManager();
        canvas = new JCanvasPanel(this.dm);
        utils = new Utils(dm, canvas);
        this.layoutApp();
        utils.init(301);

    }

    public static void main(String[] args) {

        Main mw = new Main("Mapa - Anna Dybel");
        mw.setVisible(true);

    }

}

