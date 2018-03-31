package org.jdesktop.swingx.demos.autocomplete;

import org.fluttercode.datafactory.impl.DataFactory;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.combobox.ListComboBoxModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AsyncAutoCompleteTest {
    public static void main(String[] args) {

        DataFactory df = new DataFactory();
        df.randomize(0);

        List<String> names = new ArrayList<>();

        System.out.println("Genrating names");
        for (int i = 0; i < 500_000; i++) {
            names.add(df.getFirstName() + " " + df.getLastName());
        }
        Collections.sort(names);
        System.out.println("Done");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JComboBox<String> comboBox = new JComboBox<>();
                comboBox.setEditable(true);
                ListComboBoxModel<String> model = new ListComboBoxModel<>(names);
                comboBox.setModel(model);
                comboBox.setPrototypeDisplayValue("00000000000000000000000000000000000000");

                AutoCompleteDecorator.decorate(comboBox, null, true);

                JPanel panel = new JPanel();
                panel.add(comboBox);

                JFrame frame = new JFrame("AutoCompleteTest");
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });





    }
}
