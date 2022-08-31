package ds;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
// import javax.swing.event.ChangeEvent;
// import javax.swing.event.ChangeListener;

public class Main {
    final static String LINKEDLISTSTRING = "Linked List";
    final static String DYNAMICARRAYSTRING = "Dynamic Array";
    final static String QUEUESTRING = "Queue";
    final static String STACKSTRING = "Stack";
    final static String BSTSTRING = "Binary Search Tree";
    final static String GRAPHMATRIXSTRING = "Graph (Matrix)";
    // final static String GRAPHLISTSTRING = "Graph (List)";
    // final static String HASHSTRING = "Hash Map";

    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each data structure
        Gui linkedListPanel = new Gui(LINKEDLISTSTRING);
        Gui dynamicArrayPanel = new Gui(DYNAMICARRAYSTRING);
        Gui queuePanel = new Gui(QUEUESTRING);
        Gui stackPanel = new Gui(STACKSTRING);
        Gui bstPanel = new Gui(BSTSTRING);
        Gui graphMatrixPanel = new Gui(GRAPHMATRIXSTRING);
        // Gui graphListPanel = new Gui(GRAPHLISTSTRING);
        // Gui hashPanel = new Gui(HASHSTRING);

        tabbedPane.addTab(LINKEDLISTSTRING, linkedListPanel);
        tabbedPane.addTab(DYNAMICARRAYSTRING, dynamicArrayPanel);
        tabbedPane.addTab(QUEUESTRING, queuePanel);
        tabbedPane.addTab(STACKSTRING, stackPanel);
        tabbedPane.addTab(BSTSTRING, bstPanel);
        tabbedPane.addTab(GRAPHMATRIXSTRING, graphMatrixPanel);
        // tabbedPane.addTab(GRAPHLISTSTRING, graphListPanel);
        // tabbedPane.addTab(HASHSTRING, hashPanel);

        pane.add(tabbedPane, BorderLayout.CENTER);

        // tabbedPane.addChangeListener(this);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window
        JFrame frame = new JFrame("Data Structures (Java)");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));

        // Create and set up the content pane
        Main demo = new Main();
        demo.addComponentToPane(frame.getContentPane());

        // Display the window
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Set Java default Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    // public void stateChanged(ChangeEvent e) {
    //     JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
    //     System.out.println(tabbedPane.getSelectedIndex());
    //     cards.setDataStructure(tabbedPane.getSelectedIndex());
    // }
}
