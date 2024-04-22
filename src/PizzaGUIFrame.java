import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame {
    JFrame frame;

    JPanel mainPanel;
    JPanel crust;
    JPanel size;
    JPanel toppings;
    JPanel order;
    JPanel bottomPanel;

    JComboBox comboBox;

    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepDish;

    JCheckBox pepperoni;
    JCheckBox sausage;
    JCheckBox ham;
    JCheckBox jalapeno;
    JCheckBox bacon;
    JCheckBox tomatoes;

    JTextArea orderDisplay;

    JButton orderButton;
    JButton clearButton;
    JButton quitButton;

    JOptionPane quitPane;

    ButtonGroup group;

    public PizzaGUIFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridwidth = GridBagConstraints.REMAINDER;
        grid.fill = GridBagConstraints.HORIZONTAL;

        createCrust();
        mainPanel.add(crust, grid);

        createSize();
        mainPanel.add(size, grid);

        createToppings();
        mainPanel.add(toppings, grid);

        createOrder();
        mainPanel.add(order, grid);

        createBottomPanel();
        mainPanel.add(bottomPanel, grid);

        add(mainPanel);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createCrust() {
        crust = new JPanel();
        crust.setBorder(new TitledBorder(new EtchedBorder(), "Crust"));

        thin = new JRadioButton();
        regular = new JRadioButton();
        deepDish = new JRadioButton();

        thin.setText("Thin Crust");
        thin.setFont(new Font("Arial", Font.PLAIN, 30));
        regular.setText("Regular Crust");
        regular.setFont(new Font("Arial", Font.PLAIN, 30));
        deepDish.setText("Deep Dish");
        deepDish.setFont(new Font("Arial", Font.PLAIN, 30));

        group = new ButtonGroup();
        group.add(thin);
        group.add(regular);
        group.add(deepDish);

        thin.setSelected(true);
        regular.setSelected(false);
        deepDish.setSelected(false);

        crust.add(thin);
        crust.add(regular);
        crust.add(deepDish);
    }

    private void createSize() {
        size = new JPanel();
        size.setBorder(new TitledBorder(new EtchedBorder(), "Pizza Size"));

        String[] sizes = { "Small", "Medium", "Large", "Super"};

        DefaultListCellRenderer listCellRenderer = new DefaultListCellRenderer();
        listCellRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        comboBox = new JComboBox(sizes);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBox.setPrototypeDisplayValue("AaAaAaAaAaAaAaAaAa");
        comboBox.setRenderer(listCellRenderer);
        size.add(comboBox, BorderLayout.CENTER);
    }

    private void createToppings() {
        toppings = new JPanel();
        toppings.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));

        pepperoni = new JCheckBox();
        sausage = new JCheckBox();
        ham = new JCheckBox();
        jalapeno = new JCheckBox();
        bacon = new JCheckBox();
        tomatoes = new JCheckBox();

        pepperoni.setText("Pepperoni");
        sausage.setText("Sausage");
        ham.setText("Ham");
        jalapeno.setText("Jalapeno");
        bacon.setText("Bacon");
        tomatoes.setText("Tomatoes");

        pepperoni.setFont(new Font("Arial", Font.PLAIN, 30));
        sausage.setFont(new Font("Arial", Font.PLAIN, 30));
        ham.setFont(new Font("Arial", Font.PLAIN, 30));
        jalapeno.setFont(new Font("Arial", Font.PLAIN, 30));
        bacon.setFont(new Font("Arial", Font.PLAIN, 30));
        tomatoes.setFont(new Font("Arial", Font.PLAIN, 30));

        toppings.add(pepperoni);
        toppings.add(sausage);
        toppings.add(ham);
        toppings.add(jalapeno);
        toppings.add(bacon);
        toppings.add(tomatoes);
    }

    private void createOrder() {
        order = new JPanel();
        order.setBorder(new TitledBorder(new EtchedBorder(), "Order"));

        orderDisplay = new JTextArea();
        orderDisplay.setFont(new Font("Arial", Font.ITALIC, 16));

        order.add(orderDisplay, BorderLayout.CENTER);
    }

    private void getOrder() {
        String selectedCrust = "";
        String selectedSize = "";
        String toppings = "Cheese";
        double total = 0.00;
        double toppingsPrice = 0.00;
        double sizePrice = 0.00;

        if (thin.isSelected()) {
            selectedCrust = "Thin";
        } else if (regular.isSelected()) {
            selectedCrust = "Regular";
        } else {
            selectedCrust = "Deep Dish";
        }

        selectedSize = (String) comboBox.getSelectedItem();
        assert selectedSize != null;
        sizePrice = switch (selectedSize) {
            case "Small" -> 8.00;
            case "Medium" -> 12.00;
            case "Large" -> 16.00;
            case "Super" -> 20.00;
            default -> 0.00;
        };

        if (pepperoni.isSelected()) {
            toppings += ", Pepperoni";
            toppingsPrice = 1.00;
        }

        if (sausage.isSelected()) {
            toppings += ", Sausage";
            toppingsPrice = 1.00;
        }

        if (ham.isSelected()) {
            toppings += ", Ham";
            toppingsPrice = 1.00;
        }

        if (jalapeno.isSelected()) {
            toppings += ", Jalapeno";
            toppingsPrice = 1.00;
        }

        if (bacon.isSelected()) {
            toppings += ", Bacon";
            toppingsPrice = 1.00;
        }

        if (tomatoes.isSelected()) {
            toppings += ", Tomatoes";
            toppingsPrice = 1.00;
        }

        orderDisplay.setText("=========================================================\n" + selectedCrust + ", " + selectedSize + "\t\t\t\t" + String.format("$%.2f", sizePrice) + "\n" + toppings + "\t\t" + String.format("$%.2f", toppingsPrice) + "\n\n" + "Sub-total:" + "\t\t\t\t" + String.format("$%.2f", total) + "\nTax:\t\t\t\t" + String.format("$%.2f", (total * .07)) + "\n-------------------------------------------------------------------------------------------------------\nTotal:\t\t\t\t" + String.format("$%.2f", total + (total * 0.07)) + "\n=========================================================");
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));

        orderButton = new JButton();
        orderButton.addActionListener((ActionEvent e) -> {getOrder();});

        clearButton = new JButton();
        clearButton.addActionListener((ActionEvent e1) -> {
            thin.setSelected(true);
            regular.setSelected(false);
            deepDish.setSelected(false);
            pepperoni.setSelected(false);
            sausage.setSelected(false);
            ham.setSelected(false);
            jalapeno.setSelected(false);
            bacon.setSelected(false);
            tomatoes.setSelected(false);
            orderDisplay.setText("");
        });

        quitButton = new JButton();
        quitButton.addActionListener((ActionEvent e) -> {
            quitPane = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(frame, "Do you want to quit?");
            switch (result) {
                case JOptionPane.YES_OPTION:
                    System.exit(0);
            }
        });

        orderButton.setText("Order");
        orderButton.setFont(new Font("Arial", Font.BOLD, 36));

        clearButton.setText("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 36));

        quitButton.setText("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 36));

        bottomPanel.add(orderButton, BorderLayout.CENTER);
        bottomPanel.add(clearButton, BorderLayout.CENTER);
        bottomPanel.add(quitButton, BorderLayout.CENTER);
    }
}
