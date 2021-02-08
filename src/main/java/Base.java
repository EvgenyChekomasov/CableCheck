import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Base {

    private int voltage;
    private double result = 0;
    private double dU = 0;
    private Font font;
    private JFrame frame;
    private JPanel mainpanel;
    private JPanel buttonPane;
    private JPanel panel_U;
    private JPanel panel_P;
    private JPanel panel_Cos;
    private JPanel panel_Cab;
    private JTextArea results;
    private JCheckBox singlePhase;
    private JCheckBox triplePhase;
    private JCheckBox alum;
    private JCheckBox cuprum;
    private JTextField inputDist;
    private JTextField inputPower;
    private JTextField inputCos;
    private int fragmentNumber = 0;

    private List<Cable> cables = new ArrayList<>();
    private String[] profiles = {"", "1.5", "2.5", "4", "6", "10", "16", "25", "35", "50", "70", "95", "120", "150", "185", "240"};

    // Графический интерфейс
    void go() {

        font = new Font("Verdana", Font.PLAIN, 14);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Панели базовых характеристик
        basePanel();

        //Панель с участком кабеля
        addCable();

        //Панель кнопок Расчет и Сброс
        buttons();

        // Поле вывода результата
        res();

        frame.setSize(800,300);
        frame.setVisible(true);
    }


    void basePanel() {

        mainpanel = new JPanel();
        buttonPane = new JPanel();
        frame.getContentPane().add(BorderLayout.NORTH, mainpanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPane);
        panel_U = new JPanel(); //панель выбора напряжения
        panel_P = new JPanel(); // панель указания мощности
        panel_Cos = new JPanel(); // панель указания коэффициента мощности
        panel_Cab = new JPanel(); // панель кабеля
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        mainpanel.add(panel_U);
        mainpanel.add(panel_P);
        mainpanel.add(panel_Cos);
        mainpanel.add(panel_Cab);
        panel_Cab.setLayout(new BoxLayout(panel_Cab, BoxLayout.Y_AXIS));
        buttonPane.setLayout(new GridLayout(2,1));

        JLabel volt = new JLabel("Напряжение, В");
        volt.setFont(font);
        singlePhase = new JCheckBox("220 V");
        singlePhase.addItemListener(e -> {
            voltage = 220;
            triplePhase.setSelected(false);
        });
        triplePhase = new JCheckBox("380 V");
        triplePhase.addItemListener(e -> {
            voltage = 380;
            singlePhase.setSelected(false);
        });
        panel_U.add(volt);
        panel_U.add(singlePhase);
        panel_U.add(triplePhase);

        // Панель указания присоединяемой мощности
        JLabel pow = new JLabel("Присоединяемая мощность, кВт");
        pow.setFont(font);
        inputPower = new JTextField(7);
        panel_P.add(pow);
        panel_P.add(inputPower);

        // Панель указания коэффициента мощности
        JLabel cos = new JLabel("Коэффициент мощности ");
        cos.setFont(font);
        inputCos = new JTextField(7);
        inputCos.setText("1.0");
        panel_Cos.add(cos);
        panel_Cos.add(inputCos);
    }

    void addCable() {
        cables.add(new Cable());
        JPanel fragment = new JPanel();
        panel_Cab.add(fragment);
        JLabel fragmentNum = new JLabel("Участок " + (fragmentNumber + 1) + ":");
        fragmentNum.setFont(font);
        fragment.add(fragmentNum);

        // Панель выбора материала жилы
        JPanel mat1 = new JPanel();
        JPanel mat2 = new JPanel();
        mat1.setLayout(new BoxLayout(mat1, BoxLayout.Y_AXIS));
        fragment.add(mat1);
        JLabel material = new JLabel("Материал жилы");
        material.setFont(font);
        alum = new JCheckBox("Al");
        alum.addItemListener(e -> {
            cables.get(fragmentNumber).setMaterial("Al");
            cuprum.setSelected(false);
        });
        cuprum = new JCheckBox("Cu");
        cuprum.addItemListener(e -> {
            cables.get(fragmentNumber).setMaterial("Cu");
            alum.setSelected(false);
        });
        mat1.add(material);
        mat1.add(mat2);
        mat2.add(alum);
        mat2.add(cuprum);

        // Панель выбора сечения жилы из выпадающего списка
        JPanel prof = new JPanel();
        prof.setLayout(new BoxLayout(prof, BoxLayout.Y_AXIS));
        fragment.add(prof);
        JLabel profile = new JLabel("Сечение жилы, мм2");
        profile.setFont(font);
        JComboBox<String> choiceProfile = new JComboBox<>(profiles);
        choiceProfile.addActionListener(new ProfileChoice(fragmentNumber));
        prof.add(profile);
        prof.add(choiceProfile);

        // Панель указания длины кабеля
        JPanel dis = new JPanel();
        dis.setLayout(new BoxLayout(dis, BoxLayout.Y_AXIS));
        fragment.add(dis);
        JLabel dist = new JLabel("Длина участка, км");
        dist.setFont(font);
        inputDist = new JTextField(7);
        dis.add(dist);
        dis.add(inputDist);

        JButton addButton = new JButton("+ участок");
        addButton.addActionListener(new CableAddition());
        fragment.add(addButton);
    }

    void res() {
        results = new JTextArea(10,30);
        results.setText("Введите данные");
        frame.getContentPane().add(BorderLayout.CENTER, results);
    }

    void buttons() {
        JButton buttonCalc = new JButton("Расчет");
        buttonCalc.addActionListener(new CurrentCalc());
        buttonPane.add(buttonCalc);

        JButton buttonClear = new JButton("Очистить все");
        buttonClear.addActionListener(new ClearAll());
        buttonPane.add(buttonClear);
    }

    // Вывод результатов расчета тока КЗ
    private String calcResult1() {
        return String.format("%.3f", result);
}

    // Вывод результатов расчета потери напряжения
    private String calcResult2() {
        return String.format("%.3f", dU);
    }

    // добавление  кабеля
    class CableAddition implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cables.get(fragmentNumber).setDistance(Float.parseFloat(inputDist.getText()));
            cables.get(fragmentNumber).choiceResistance();
            fragmentNumber++;
            addCable();
            frame.validate();
            frame.repaint();
        }
    }

    // Расчет по нажатию кнопки
    class CurrentCalc implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            double power = Double.parseDouble(inputPower.getText());
            double cosf = Double.parseDouble(inputCos.getText());
            cables.get(fragmentNumber).setDistance(Float.parseFloat(inputDist.getText()));
            cables.get(fragmentNumber).choiceResistance();
            if (voltage == 220) {
                result = voltage / loopResistance();
                dU = power * loopResistance() * 1000 * 100 / Math.pow(voltage,2);
            } else {
                result = voltage / (Math.sqrt(3)*Math.sqrt(Math.pow(activeResistance(), 2) + Math.pow(reactiveResistance(), 2)));
                dU = (power * activeResistance() + power * Math.tan(Math.acos(cosf))*reactiveResistance()) * 1000 * 100 / Math.pow(voltage,2);
            }
            if (cosf <= 1 && cosf > 0) {
                results.setText("Ток короткого замыкания, А - " + calcResult1() + "\n" +
                        "Потеря напряжения в кабеле, % - " + calcResult2());
            } else {
                results.setText("Ток короткого замыкания, А - " + calcResult1() + "\n" +
                        "Некорректная величина коэффициента мощности");
            }
        }
    }

    double loopResistance() {
        double fullResistance = 0;
        for (Cable cable : cables) {
            fullResistance += cable.getzLoop() * cable.getDistance();
        }
        return fullResistance;
    }

    double activeResistance() {
        double fullResistance = 0;
        for (Cable cable : cables) {
            fullResistance += cable.getrActiv() * cable.getDistance();
        }
        return fullResistance;
    }

    double reactiveResistance() {
        double fullResistance = 0;
        for (Cable cable : cables) {
            fullResistance += cable.getxReactive() * cable.getDistance();
        }
        return fullResistance;
    }

    // Выбор сечения из выпадающего списка
    class ProfileChoice implements ActionListener {
        int fragmentNumber;

        public ProfileChoice(int fragmentNumber) {
            this.fragmentNumber = fragmentNumber;
        }

        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String item = (String)box.getSelectedItem();
            cables.get(fragmentNumber).setProfile(item);
           //System.out.println(cable.getMaterial());
           // System.out.println(cable.getProfile());
        }
    }

    class ClearAll implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(mainpanel);
            frame.remove(results);
            frame.remove(buttonPane);
            cables.clear();
            fragmentNumber = 0;
            basePanel();
            addCable();
            buttons();
            res();
            frame.validate();
            frame.repaint();
        }
    }
}

