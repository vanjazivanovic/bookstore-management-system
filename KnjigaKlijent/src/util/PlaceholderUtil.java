package util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class PlaceholderUtil {

    public static void postaviPlaceholder(JTextField polje, String tekst) {

        polje.setText(tekst);
        polje.setForeground(Color.GRAY);

        polje.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (polje.getText().equals(tekst)) {
                    polje.setText("");
                    polje.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (polje.getText().trim().isEmpty()) {
                    polje.setText(tekst);
                    polje.setForeground(Color.GRAY);
                }
            }
        });
    }
}