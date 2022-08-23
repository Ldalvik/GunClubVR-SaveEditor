package root.gunclubvr.saveeditor;

import javax.swing.*;
import java.awt.*;

public class ViewCreator {

    public JList<String> makeList(JPanel root, DefaultListModel<String> list, int x, int y, int w, int h) {
        JList<String> jl = new JList<>(list);
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(jl);
        sp.setBounds(x, y, w, h);
        root.add(sp);
        return jl;
    }

    public JTextArea makeTextArea(JPanel root, int x1, int y1, int x, int y, int w, int h){
        JTextArea ta = new JTextArea(x1, y1);
        ta.setBounds(x,y,w,h);
        root.add(ta);
        return ta;
    }


    public JComboBox<String> makeComboBox(JPanel root, String[] list, int x, int y, int w, int h) {
        JComboBox<String> jl = new JComboBox<>(list);
        jl.setBounds(x, y, w, h);
        root.add(jl);
        return jl;
    }
    public JComboBox<String> makeComboBox(JPanel root, DefaultComboBoxModel<String> model, int x, int y, int w, int h) {
        JComboBox<String> jl = new JComboBox<>(model);
        jl.setBounds(x, y, w, h);
        root.add(jl);
        return jl;
    }


    DefaultListModel<String> model = new DefaultListModel<>(); //AUTO-UPDATING MODEL FOR LISTING TUNES


    public JLabel makeLabel(JPanel root, String text, int x, int y, int w, int h) {
        JLabel jl = new JLabel(text);
        jl.setBounds(x, y, w, h);
        root.add(jl);
        return jl;
    }

    public JLabel makeLabel(JPanel root, String text, int x, int y, int w, int h, Font font) {
        JLabel jl = new JLabel(text);
        jl.setBounds(x, y, w, h);
        jl.setFont(font);
        root.add(jl);
        return jl;
    }

    public JScrollPane makeScrollPane(JPanel root, JList l, int x, int y, int w, int h){
        JScrollPane sp = new JScrollPane();
        sp.setBounds(x,y,w,h);
        sp.setViewportView(l);
        root.add(sp);
        return sp;
    }

    public JLabel makeImage(JPanel root, ImageIcon image, int x, int y, int w, int h) {
        JLabel jl = new JLabel(image);
        jl.setBounds(x, y, w, h);
        root.add(jl);
        return jl;
    }

    public void version(JPanel root) {
        JLabel jl = new JLabel("VERSION_NAME");
        jl.setBounds(510, 255, 200, 25);
        root.add(jl);
        jl.setForeground(Color.DARK_GRAY);
        jl.setFont(new Font("", Font.PLAIN, 9));
    }

    public JButton makeButton(JPanel root, String text, int x, int y, int w, int h) {
        JButton jb = new JButton(text);
        jb.setBounds(x, y, w, h);
        root.add(jb);
        return jb;
    }

    public JTextField makeSelectableLabel(JPanel root, String text, int col, int x, int y, int w, int h) {
        JTextField jt = new JTextField(col);
        jt.setEditable(false);
        jt.setText(text);
        jt.setBackground(null);
        jt.setBorder(null);
        jt.setBounds(x, y, w, h);
        root.add(jt);
        return jt;
    }

    public JTextField makeTextField(JPanel root, int x, int y, int w, int h) {
        JTextField jt = new JTextField(5);
        jt.setBounds(x, y, w, h);
        root.add(jt);
        return jt;
    }

    public JSeparator makeDivider(JPanel root, int o, int x, int y, int w, int h){
        JSeparator js = new JSeparator(o);
        js.setBounds(x,y,w,h);
        root.add(js);
        return js;
    }
}
