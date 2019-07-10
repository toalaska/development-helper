package cn.toalaska.development.ui;



import cn.toalaska.development.MyService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;


import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class DTODialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private MyTableModel dataModel;

    public DTODialog() {
        setContentPane(contentPane);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        init();

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void init() {

//        ArrayList<Param> paramList = new ArrayList<>();
//        paramList.add(new Param("姓名","name","String",true,""));
//        paramList.add(new Param("年龄","age","Integer",false,""));

        List<Param> paramList= MyService.getInstance().getParams();
        dataModel = new MyTableModel(paramList);
        table1.setModel(dataModel);

    }

    private void onOK() {
        for (Param param : dataModel.getParamList()) {
            System.out.println("data="+param);

        }

       dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DTODialog dialog = new DTODialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
