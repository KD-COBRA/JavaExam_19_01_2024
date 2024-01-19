import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q1 extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskTextField;

    public Q1() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Koushik's:To-Do List Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        taskTextField = new JTextField(20);
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");
        JButton deleteButton = new JButton("Delete Task");
        JButton clearButton = new JButton("Clear All");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markCompletedTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllTasks();
            }
        });

        inputPanel.add(taskTextField);
        inputPanel.add(addButton);
        inputPanel.add(completeButton);
        inputPanel.add(deleteButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTask() {
        String task = taskTextField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskTextField.setText("");
        }
    }

    private void markCompletedTask() {
        int[] selectedIndices = taskList.getSelectedIndices();
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            int selectedIndex = selectedIndices[i];
            String completedTask = taskListModel.get(selectedIndex);
            taskListModel.remove(selectedIndex);
            taskListModel.addElement("[Completed] " + completedTask);
        }
    }

    private void deleteTask() {
        int[] selectedIndices = taskList.getSelectedIndices();
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            int selectedIndex = selectedIndices[i];
            taskListModel.remove(selectedIndex);
        }
    }

    private void clearAllTasks() {
        taskListModel.removeAllElements();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Q1();
            }
        });
    }
}
