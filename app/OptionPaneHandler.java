package app;

import javax.swing.*;
import java.awt.*;

public interface OptionPaneHandler {
    int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue);

    void showMessageDialog(Component parentComponent, Object message, String title, int messageType);

    class DefaultOptionPaneHandler implements OptionPaneHandler {
        @Override
        public int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
            return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
        }

        @Override
        public void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
            JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
        }
    }
}
