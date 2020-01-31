import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Base {

    private int voltage;
    private double result = 0;
    private double dU = 0;
    private JTextArea results;
    private JCheckBox singlePhase;
    private JCheckBox triplePhase;
    private JCheckBox alum;
    private JCheckBox cuprum;
    private JTextField inputDist;
    private JTextField inputPower;
    private JTextField inputCos;

    private Cable cable = new Cable();
    private String[] profiles = {"1.5", "2.5", "4", "6", "10", "16", "25", "35", "50", "70", "95", "120", "150", "185", "240"};

    // Графический интерфейс
    void go() {

        Font font = new Font("Verdana", Font.PLAIN, 16);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainpanel = new JPanel();
        frame.getContentPane().add(BorderLayout.NORTH, mainpanel);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        mainpanel.add(panel1);
        mainpanel.add(panel2);
        mainpanel.add(panel3);
        mainpanel.add(panel4);
        mainpanel.add(panel5);
        mainpanel.add(panel6);


        //Панель выбора напряжения
        JLabel volt = new JLabel("Напряжение, В");
        volt.setFont(font);
        singlePhase = new JCheckBox("220 V");
        singlePhase.addItemListener(e -> {
            voltage = 220;
            triplePhase.setSelected(false);});
        triplePhase = new JCheckBox("380 V");
        triplePhase.addItemListener(e -> {
            voltage = 380;
            singlePhase.setSelected(false);});
        panel1.add(volt);
        panel1.add(singlePhase);
        panel1.add(triplePhase);

        // Панель выбора материала жилы
        JLabel material = new JLabel("Материал жилы");
        material.setFont(font);
        alum = new JCheckBox("Al");
        alum.addItemListener(e -> {
           cable.setMaterial("Al");
           cuprum.setSelected(false);
       });
        cuprum = new JCheckBox("Cu");
        cuprum.addItemListener(e -> {
           cable.setMaterial("Cu");
           alum.setSelected(false);
       });
        panel2.add(material);
        panel2.add(alum);
        panel2.add(cuprum);

        // Панель выбора сечения жилы из выпадающего списка
        JLabel profile = new JLabel("Сечение жилы, мм2");
        profile.setFont(font);
        JComboBox<String> choiceProfile = new JComboBox<>(profiles);
        choiceProfile.addActionListener(new ProfileChoice());
        panel3.add(profile);
        panel3.add(choiceProfile);

        // Панель указания длины кабеля
        JLabel dist = new JLabel("Длина участка, км");
        dist.setFont(font);
        inputDist = new JTextField(7);
        panel4.add(dist);
        panel4.add(inputDist);

        // Панель указания присоединяемой мощности
        JLabel pow = new JLabel("Присоединяемая мощность, кВт");
        pow.setFont(font);
        inputPower = new JTextField(7);
        panel5.add(pow);
        panel5.add(inputPower);

        // Панель указания коэффициента мощщности
        JLabel cos = new JLabel("Коэффициент мощности ");
        cos.setFont(font);
        inputCos = new JTextField(7);
        inputCos.setText("1.0");
        panel6.add(cos);
        panel6.add(inputCos);


        JButton button = new JButton("Расчет");
        button.addActionListener(new CurrentCalc());
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        // Поле вывода результата
        results = new JTextArea(10,30);
        results.setText("Введите данные");
        frame.getContentPane().add(BorderLayout.CENTER, results);

        frame.setSize(500,300);
        frame.setVisible(true);
     }

    // Вывод результатов расчета тока КЗ
    private String calcResult1() {
        return String.format("%.3f", result);
}

    // Вывод результатов расчета потери напряжения
    private String calcResult2() {
        return String.format("%.3f", dU);
    }

    // Расчет по нажатию кнопки
    class CurrentCalc implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            float distance = Float.parseFloat(inputDist.getText());
            double power = Double.parseDouble(inputPower.getText());
            double cosf = Double.parseDouble(inputCos.getText());
            cable.choiceResistance();
            if (voltage == 220) {
                result = voltage / (cable.getzLoop() * distance);
                dU = power * cable.getzLoop() * distance * 1000 * 100 / Math.pow(voltage,2);
            } else {
                result = voltage / (Math.sqrt(3)*Math.sqrt(Math.pow(cable.getrActiv(), 2) + Math.pow(cable.getxReactive(), 2)) * distance);
                dU = (power * cable.getrActiv() + power * Math.tan(Math.acos(cosf))*cable.getxReactive()) * distance * 1000 * 100 / Math.pow(voltage,2);
            }
            if (cosf < 1 || cosf > 0) {
                results.setText("Ток короткого замыкания, А - " + calcResult1() + "\n" +
                        "Потеря напряжения в кабеле, % - " + calcResult2());
            } else {
                results.setText("Ток короткого замыкания, А - " + calcResult1() + "\n" +
                        "Некорректная величина коэффициента мощности");
            }
            //System.out.println(cable.getrActiv());
            //System.out.println(cable.getxReactive());
            //System.out.println(cable.getzLoop());
        }
    }

    // Выбор сечения из выпадающего списка
    class ProfileChoice implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String item = (String)box.getSelectedItem();
            cable.setProfile(item);
           //System.out.println(cable.getMaterial());
           // System.out.println(cable.getProfile());
        }
    }
}

