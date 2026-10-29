import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HW1 extends JFrame {

    private int rollCount = 0;
    private int rollSum = 0;

    private JLabel topLabel;
    private JLabel centerLabel;
    private JButton rollButton;
    private Random random;

    public HW1() {
        // 1. 視窗基本設定
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉時結束程式
        setLocationRelativeTo(null); // 開啟時置中

        random = new Random();

        // 設定 Layout
        setLayout(new BorderLayout());

        // 2. 上方統計標籤
        topLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        topLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        topLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(topLabel, BorderLayout.NORTH);

        // 3. 中央點數標籤 (字體 60pt)
        centerLabel = new JLabel("?", SwingConstants.CENTER);
        centerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        add(centerLabel, BorderLayout.CENTER);

        // 4. 下方按鈕
        rollButton = new JButton("擲骰子");
        rollButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        rollButton.setPreferredSize(new Dimension(0, 60));
        
        // 按鈕點擊事件
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        add(rollButton, BorderLayout.SOUTH);
    }

    private void rollDice() {
        // 隨機產生 1-6
        int roll = random.nextInt(6) + 1;
        
        // 更新統計數據
        rollCount++;
        rollSum += roll;
        double average = (double) rollSum / rollCount;

        // 更新上方文字 (X.XX 格式)
        topLabel.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", rollCount, rollSum, average));

        // 更新中央點數
        centerLabel.setText(String.valueOf(roll));

        // 判斷顏色：6 為綠色(使用較深的綠色確保清晰度)，1 為紅色，其餘黑色
        if (roll == 6) {
            centerLabel.setForeground(new Color(0, 180, 0)); 
        } else if (roll == 1) {
            centerLabel.setForeground(Color.RED);
        } else {
            centerLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        // 確保 GUI 在 Event Dispatch Thread (EDT) 執行，這是 Swing 的安全規範
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HW1().setVisible(true);
            }
        });
    }
}