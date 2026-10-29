import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HW2 extends JFrame {
    private JComboBox<String> typeComboBox;
    private JTextField inputField;
    private JTextField outputField;
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JButton convertButton;

    // 定義選項陣列
    private final String[] types = {"長度", "重量", "溫度"};
    private final String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    private final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    private final String[] tempUnits = {"攝氏", "華氏", "克氏"};

    public HW2() {
        // 設定視窗基本屬性
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== NORTH 區域 =====
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        northPanel.add(new JLabel("換算類型："));
        typeComboBox = new JComboBox<>(types);
        northPanel.add(typeComboBox);
        add(northPanel, BorderLayout.NORTH);

        // ===== CENTER 區域 =====
        // 使用 GridLayout(2, 1) 來放置兩列
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        
        // 第一列：來源輸入框與單位
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField = new JTextField(15);
        sourceUnitComboBox = new JComboBox<>();
        row1.add(inputField);
        row1.add(sourceUnitComboBox);
        
        // 第二列：目標輸出框與單位
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        outputField = new JTextField(15);
        outputField.setEditable(false); // 設定為不可編輯，純顯示結果
        targetUnitComboBox = new JComboBox<>();
        row2.add(outputField);
        row2.add(targetUnitComboBox);
        
        centerPanel.add(row1);
        centerPanel.add(row2);
        add(centerPanel, BorderLayout.CENTER);

        // ===== SOUTH 區域 =====
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        convertButton = new JButton("換算");
        southPanel.add(convertButton);
        add(southPanel, BorderLayout.SOUTH);

        // ===== 事件監聽設定 =====
        // 當換算類型改變時，更新下拉選單內容
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitComboBoxes();
            }
        });

        // 點擊換算按鈕進行計算
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        // 初始化第一次的單位下拉選單內容
        updateUnitComboBoxes();
    }

    // 更新單位下拉選單的副程式
    private void updateUnitComboBoxes() {
        int selectedType = typeComboBox.getSelectedIndex();
        
        // 清除舊選項
        sourceUnitComboBox.removeAllItems();
        targetUnitComboBox.removeAllItems();

        String[] currentUnits = null;
        if (selectedType == 0) {
            currentUnits = lengthUnits;
        } else if (selectedType == 1) {
            currentUnits = weightUnits;
        } else if (selectedType == 2) {
            currentUnits = tempUnits;
        }

        // 加入新選項
        if (currentUnits != null) {
            for (String unit : currentUnits) {
                sourceUnitComboBox.addItem(unit);
                targetUnitComboBox.addItem(unit);
            }
        }
        
        // 清空輸入與輸出框
        inputField.setText("");
        outputField.setText("");
    }

    // 執行換算邏輯的副程式
    private void performConversion() {
        try {
            String inputText = inputField.getText().trim();
            if (inputText.isEmpty()) return;
            
            double inputValue = Double.parseDouble(inputText);
            int typeIndex = typeComboBox.getSelectedIndex();
            int sourceIndex = sourceUnitComboBox.getSelectedIndex();
            int targetIndex = targetUnitComboBox.getSelectedIndex();

            if (sourceIndex == -1 || targetIndex == -1) return;

            double result = 0.0;

            if (typeIndex == 0) { 
                // 【長度換算】先統一轉為公尺，再轉為目標單位
                double inMeters = 0;
                switch (sourceIndex) {
                    case 0: inMeters = inputValue; break;                 // 公尺
                    case 1: inMeters = inputValue * 0.01; break;          // 公分
                    case 2: inMeters = inputValue * 0.0254; break;        // 英吋
                    case 3: inMeters = inputValue * 0.3048; break;        // 英尺
                }
                switch (targetIndex) {
                    case 0: result = inMeters; break;
                    case 1: result = inMeters / 0.01; break;
                    case 2: result = inMeters / 0.0254; break;
                    case 3: result = inMeters / 0.3048; break;
                }
            } else if (typeIndex == 1) { 
                // 【重量換算】先統一轉為公斤，再轉為目標單位
                double inKg = 0;
                switch (sourceIndex) {
                    case 0: inKg = inputValue; break;                      // 公斤
                    case 1: inKg = inputValue * 0.001; break;              // 公克
                    case 2: inKg = inputValue * 0.45359237; break;         // 磅
                    case 3: inKg = inputValue * 0.0283495231; break;       // 盎司
                }
                switch (targetIndex) {
                    case 0: result = inKg; break;
                    case 1: result = inKg / 0.001; break;
                    case 2: result = inKg / 0.45359237; break;
                    case 3: result = inKg / 0.0283495231; break;
                }
            } else if (typeIndex == 2) { 
                // 【溫度換算】先統一轉為攝氏，再轉為目標單位
                double inCelsius = 0;
                switch (sourceIndex) {
                    case 0: inCelsius = inputValue; break;                       // 攝氏
                    case 1: inCelsius = (inputValue - 32) * 5.0 / 9.0; break;    // 華氏
                    case 2: inCelsius = inputValue - 273.15; break;              // 克氏
                }
                switch (targetIndex) {
                    case 0: result = inCelsius; break;
                    case 1: result = inCelsius * 9.0 / 5.0 + 32; break;
                    case 2: result = inCelsius + 273.15; break;
                }
            }

            // 將結果顯示於輸出框，預設顯示小數點後 4 位
            outputField.setText(String.format("%.4f", result));
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "請在上方輸入框填入有效的數字", "格式錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // 使用事件分派執行緒(EDT)來啟動 GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HW2 frame = new HW2();
                // 置中顯示
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}