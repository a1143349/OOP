import javax.swing.*;
import java.awt.*;

public class HW2 extends JFrame {

    public HW2() {
        // 1. 視窗基本設定
        setTitle("骰子模擬器");
        setSize(400, 320); // 尺寸 400x320
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉時結束程式
        setLayout(new BorderLayout(10, 10)); // 使用 BorderLayout，並加上一些間距

        // 2. 視窗上方：顯示統計資訊
        // 規格：「已擲 N 次，總和 M，平均 X.XX」
        JLabel statsLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        statsLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        // 為了美觀增加一點邊距
        statsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(statsLabel, BorderLayout.NORTH);

        // 3. 視窗中央：顯示目前點數
        // 規格：中央有一個 JLabel 顯示目前點數，字體 60pt
        JLabel diceLabel = new JLabel("-", SwingConstants.CENTER);
        diceLabel.setFont(new Font("Arial", Font.BOLD, 60)); // 設定字體為 60pt
        add(diceLabel, BorderLayout.CENTER);

        // 4. 視窗下方：按鈕
        // 規格：下方一個「擲骰子」按鈕
        JButton rollButton = new JButton("擲骰子");
        rollButton.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
        // 增加按鈕高度使其較好點擊
        rollButton.setPreferredSize(new Dimension(0, 50));
        add(rollButton, BorderLayout.SOUTH);

        // 5. 開啟時置中
        setLocationRelativeTo(null); 
    }

    public static void main(String[] args) {
        // 在 Event Dispatch Thread 中執行 UI 更新
        SwingUtilities.invokeLater(() -> {
            HW2 simulator = new HW2();
            simulator.setVisible(true);
        });
    }
}