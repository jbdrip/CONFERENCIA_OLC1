/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferencia_olc1;

import Analyzers.parser;
import Analyzers.scanner;
import Enviroment.Enviroment;
import Enviroment.Sym;
import Tree.Expression;
import Tree.Instruction;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

/**
 *
 * @author Javier Bran
 */
public class Window extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Window() {
        initComponents();
    }
    
    int tabCounter = 0;
    String[] paths = new String[50];
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputConsole = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        createButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        analyzeButton = new javax.swing.JButton();
        lineLabel = new javax.swing.JLabel();
        columnLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(700, 650));

        tabs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabs.setAutoscrolls(true);
        tabs.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N

        outputConsole.setEditable(false);
        outputConsole.setColumns(20);
        outputConsole.setRows(5);
        outputConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(outputConsole);

        jLabel1.setText("CONSOLA DE SALIDA:");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        createButton.setText("   NUEVO   ");
        createButton.setFocusable(false);
        createButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(createButton);

        jButton1.setText("CERRAR");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        analyzeButton.setText("   ANALIZAR   ");
        analyzeButton.setFocusable(false);
        analyzeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        analyzeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(analyzeButton);

        lineLabel.setText("LINEA:");
        lineLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        columnLabel.setText("COLUMNA:");
        columnLabel.setPreferredSize(new java.awt.Dimension(50, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
            .addComponent(jScrollPane1)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(391, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(columnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        JTextPane newFile = new JTextPane();
        newFile.addCaretListener((CaretEvent e) -> {
            lineLabel.setText("LINEA: " + getRow(e.getDot(), (JTextPane)e.getSource()));
            columnLabel.setText("COLUMNA: " + getColumn(e.getDot(), (JTextPane)e.getSource()));
        });
        JScrollPane jsp = new JScrollPane(newFile);
        paths[tabs.getComponentCount()] = "";
        tabs.addTab("", jsp);
    }//GEN-LAST:event_createButtonActionPerformed

    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        try {
            outputConsole.setText("");
            analyze();
        }
        catch (Exception ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); }
    }//GEN-LAST:event_analyzeButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tabs.remove(tabs.getSelectedIndex());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void analyze() throws Exception {
        JScrollPane current = (JScrollPane) tabs.getComponentAt(tabs.getSelectedIndex());
        JViewport viewport = current.getViewport(); 
        JTextPane editor = (JTextPane)viewport.getView();
        String input = editor.getText();
        
        scanner scanner = new scanner(new BufferedReader(new StringReader(input)));
        parser parser = new parser(scanner);
        parser.parse();
        
        LinkedList<Instruction> root = parser.AST;
        Enviroment global = new Enviroment(null);
        executeAST(root, global);
    }
    
    void executeAST(LinkedList<Instruction> root, Enviroment global) {
        if(root != null) {
            root.forEach((ins) -> {
                ins.execute(global);
            });
            Iterator<String> sout = global.printList.iterator();
            while(sout.hasNext()) outputConsole.append(sout.next());
        }  
    }
    
    public LinkedList<Sym> executeParams(LinkedList<Expression> parameters, Enviroment env) {
        LinkedList<Sym> params = new LinkedList<>();
        if(parameters != null) {
            for(Expression exp : parameters) {
                executeParams(exp, env);
                Object r = exp.execute(env);
                if(r instanceof Sym) {
                    Sym s = (Sym) r;
                    params.add(s);
                }
            }
        }
        return params;
    }
    
    public void executeParams(Expression e, Enviroment env) {
        if(e != null && e.parameters != null) {
            e.paramsResult = new LinkedList<>();
            for(Expression exp : e.parameters) {
                executeParams(exp, env);
                Object r = exp.execute(env);
                if(r instanceof Sym) {
                    Sym s = (Sym) r;
                    e.paramsResult.add(s);
                }
            }
        }
    }
    
    public static int getRow(int pos, JTextPane editor) {
        int rn = (pos == 0) ? 1 : 0;
        try {
            int offs = pos;
            while(offs > 0) {
                offs = Utilities.getRowStart(editor, offs) - 1;
                rn++;
            }
        }
        catch (BadLocationException e) {}
        return rn;
    }

    public static int getColumn(int pos, JTextPane editor) {
        try {
            return pos-Utilities.getRowStart(editor, pos) + 1;
        }
        catch (BadLocationException e) {}
        return -1;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JLabel columnLabel;
    private javax.swing.JButton createButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lineLabel;
    private javax.swing.JTextArea outputConsole;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
