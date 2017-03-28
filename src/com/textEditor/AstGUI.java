package com.textEditor;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;


/**
 * Created by anthony on 26/03/17.
 */
public class AstGUI extends JPanel{

    private JTree arbol;
    private DefaultMutableTreeNode root;

    public AstGUI(){

        root = new DefaultMutableTreeNode("Program");

        arbol = new JTree(root);

        this.setLayout(new BorderLayout());
        this.add(arbol, BorderLayout.CENTER);
        this.add(arbol);

        DefaultMutableTreeNode n0 = new DefaultMutableTreeNode("Declarations");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode("Statements");

        root.add(n0);
        root.add(n1);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) arbol.getCellRenderer();
        Icon closedIcon = new ImageIcon(getClass().getResource("imagens/redPoint.png"));
        Icon openIcon = new ImageIcon(getClass().getResource("imagens/redPoint.png"));
        Icon leafIcon = new ImageIcon(getClass().getResource("imagens/redPoint.png"));
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);

        expandeNodes();

    }

    private void expandeNodes(){
        for(int i = 0; i < arbol.getRowCount(); i++){
            arbol.expandRow(i);
        }
    }
}
