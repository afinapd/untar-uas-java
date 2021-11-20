import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class TabbedPane {
    public static String db_name = "untarjava";
    public static String db_user = "root";
    public static String db_password = "";

    private JFrame frm_jtpane;
    public static JButton btn_submit = new JButton("Submit");
    public static JButton btn_reset = new JButton("Reset");
    public static JButton btn_convert = new JButton("Convert to Customer");
    public static JButton btn_delete = new JButton("Delete");
    public static JButton btn_update = new JButton("Update");
    public static JTable tableLeads = new JTable();
    public static JTable tableCustomer = new JTable();
    public static JComboBox<String> cbo_electricity;
    JPanel jp = new JPanel();

    public static JPanel crudCustomer = new JPanel();
    JPanel convertLead = new JPanel();
    JPanel jp_center = new JPanel();
    JPanel jp_east = new JPanel();
    JPanel jp_calculator = new JPanel();
    JPanel jp_leads = new JPanel();
    JPanel jp_customer = new JPanel();
    JPanel jp_buttons = new JPanel();
    public static JTabbedPane tabbedPane = new JTabbedPane();
    //    calculator
    public static JTextField txt_name_calculator = new JTextField();
    public static JTextField txt_email_calculator = new JTextField();
    public static JTextField txt_hp_calculator = new JTextField();
    public static JTextField txt_area_calculator = new JTextField();
    public static JTextField txt_bills_calculator = new JTextField();
    public static JTextPane txt_info_calculator = new JTextPane();
    TitledBorder border_info = BorderFactory.createTitledBorder("Result Recomendation");
    String[] electricity = {"2200", "3500", "4400", "5500", "6600"};

    //    lead
    public static JComboBox<Object> cb_id_leads = new JComboBox<>(showLeadsId().toArray());

    public static JTextField txt_address_lead = new JTextField();
    public static JTextField txt_dp_lead = new JTextField();
    public static JTextField txt_fp_lead = new JTextField();
    public static JComboBox<Integer> cb_kwp_lead = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6});

    //    calculator
    public static JComboBox<Object> cb_id_customer = new JComboBox<>(showCustomerId().toArray());

    public static JTextField txt_email_customer = new JTextField();
    public static JTextField txt_hp_customer = new JTextField();
    public static JTextField txt_address_customer = new JTextField();
    public static JTextField txt_dp_customer = new JTextField();
    public static JTextField txt_fp_customer = new JTextField();
    public static JComboBox<Integer> cb_kwp_customer = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6});
    public static JComboBox<Object> cb_status_payment = new JComboBox<>(showMasterStatusPayment());

    /* Create the application. */
    public TabbedPane() {
        initialize();
    }

    /* Initialize the contents of the frame. */
    private void initialize() {
        frm_jtpane = new JFrame();
        frm_jtpane.setTitle("JTabbedPane ");
        frm_jtpane.setBounds(100, 100, 1000, 700);
        frm_jtpane.setResizable(false);
        frm_jtpane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm_jtpane.getContentPane().setLayout(null);
        frm_jtpane.setLocationRelativeTo(null);

//        Calculator
        jp_buttons.setLayout(new GridLayout(0, 1));
        jp_buttons.add(btn_submit);
        jp_buttons.add(btn_reset);
        cbo_electricity = new JComboBox<>(electricity);
        jp.add(new JLabel(" Name"));
        jp.add(txt_name_calculator);
        jp.add(new JLabel(" Email"));
        jp.add(txt_email_calculator);
        jp.add(new JLabel(" No Handphone"));
        jp.add(txt_hp_calculator);
        jp.add(new JLabel(" Area (m2)"));
        jp.add(txt_area_calculator);
        jp.add(new JLabel(" Bills per Month (Rp)"));
        jp.add(txt_bills_calculator);
        jp.add(new JLabel(" Electricity (VA)"));
        jp.add(cbo_electricity);
        jp.add(new JLabel());
        jp.setLayout(new GridLayout(0, 2));
        jp_center.setLayout(new BorderLayout());
        txt_info_calculator.setBorder(border_info);

        jp_calculator.setLayout(new BorderLayout());
        jp_center.add(jp, BorderLayout.NORTH);
        jp_center.add(new JScrollPane(txt_info_calculator), BorderLayout.CENTER);
        jp_east.add(jp_buttons);
        jp_calculator.add(jp_center, BorderLayout.CENTER);
        jp_calculator.add(jp_east, BorderLayout.EAST);

//        Leads
        showLeadsdata();
        showCustomerdata();

        updateCBDataIdCustomer();

        JScrollPane scrollPane = new JScrollPane(tableLeads);
        tableLeads.setFillsViewportHeight(true);
        tableLeads.setEnabled(false);
        tableLeads.setCellSelectionEnabled(true);
        tableLeads.setFocusable(false);

        jp_leads.setBorder(new EmptyBorder(10, 10, 0, 10));
        jp_leads.setLayout(new GridLayout(2, 1));
        jp_leads.add(scrollPane);


        // convertLead
        convertLead.setLayout(new GridLayout(7, 2));
        convertLead.add(new JLabel("ID"));
        convertLead.add(cb_id_leads);
        convertLead.add(new JLabel("Address"));
        convertLead.add(txt_address_lead);
        convertLead.add(new JLabel("KWP"));
        convertLead.add(cb_kwp_lead);
        convertLead.add(new JLabel("Down Payment (Rp)"));
        convertLead.add(txt_dp_lead);
        convertLead.add(new JLabel("Fullpayment (Rp)"));
        convertLead.add(txt_fp_lead);
        convertLead.add(new JLabel(""));
        convertLead.add(new JLabel(""));
        convertLead.add(new JLabel(""));
        convertLead.add(btn_convert);

        // Border convertLead
        Border lowerdetchedLead = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder borderLead = BorderFactory.createTitledBorder(lowerdetchedLead, "Convert to Customer");
        borderLead.setTitleFont(borderLead.getTitleFont().deriveFont(Font.BOLD));
        convertLead.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 2, 2, 10), borderLead));
        jp_leads.add(convertLead);

//        Customer

        JScrollPane scrollPane2 = new JScrollPane(tableCustomer);
        tableCustomer.setFillsViewportHeight(true);
        tableCustomer.setEnabled(false);

        jp_customer.setBorder(new EmptyBorder(10, 10, 0, 10));
        jp_customer.setLayout(new GridLayout(0, 1));
        jp_customer.add(scrollPane2);

        // crudCustomer
        crudCustomer.setLayout(new GridLayout(10, 2));
        crudCustomer.add(new JLabel("ID"));
        crudCustomer.add(cb_id_customer);
        crudCustomer.add(new JLabel("Email"));
        crudCustomer.add(txt_email_customer);
        crudCustomer.add(new JLabel("Phone"));
        field(crudCustomer, txt_hp_customer, txt_address_customer, cb_kwp_customer, txt_dp_customer, txt_fp_customer);
        crudCustomer.add(new JLabel("Status Payment"));
        crudCustomer.add(cb_status_payment);
        crudCustomer.add(new JLabel(""));
        crudCustomer.add(new JLabel(""));
        crudCustomer.add(btn_delete);
        crudCustomer.add(btn_update);

        // Border crudCustomer
        Border lowerdetchedCustomer = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder borderCustomer = BorderFactory.createTitledBorder(lowerdetchedCustomer, "Update or Delete Customer");
        borderCustomer.setTitleFont(borderCustomer.getTitleFont().deriveFont(Font.BOLD));
        crudCustomer.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 2, 2, 10), borderCustomer));
        jp_customer.add(crudCustomer);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(27, 21, 900, 600);
        tabbedPane.addTab("Calculator", jp_calculator);
        tabbedPane.addTab("Leads", jp_leads);
        tabbedPane.addTab("Customer", jp_customer);
        frm_jtpane.getContentPane().add(tabbedPane);
    }

    private void field(JPanel crudCustomer, JTextField txt_hp_customer, JTextField txt_address_customer, JComboBox<Integer> cb_kwp_customer, JTextField txt_dp_customer, JTextField txt_fp_customer) {
        crudCustomer.add(txt_hp_customer);
        crudCustomer.add(new JLabel("Address"));
        crudCustomer.add(txt_address_customer);
        crudCustomer.add(new JLabel("KWP"));
        crudCustomer.add(cb_kwp_customer);
        crudCustomer.add(new JLabel("Down Payment (Rp)"));
        crudCustomer.add(txt_dp_customer);
        crudCustomer.add(new JLabel("Fullpayment (Rp)"));
        crudCustomer.add(txt_fp_customer);
    }

    /* Launch the application. */
    public static void main(String[] args) {
        TabbedPane window = new TabbedPane();
        window.frm_jtpane.setVisible(true);

        btn_submit.addActionListener(e -> {
            String name = txt_name_calculator.getText();
            String email = txt_email_calculator.getText();
            String hp = txt_hp_calculator.getText();
            String area = txt_area_calculator.getText();
            String bills = txt_bills_calculator.getText();
            String electricity = Objects.requireNonNull(cbo_electricity.getSelectedItem()).toString();
            if (Objects.equals(name, "") || Objects.equals(email, "") || Objects.equals(bills, "") || Objects.equals(hp, "") || Objects.equals(area, "") || Objects.equals(electricity, "")) {
                JOptionPane.showMessageDialog(null, "Field must not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                runQuery(
                        "insert into leads set " +
                                "email = '" + email + "', " +
                                "no_hp = '" + hp + "', " +
                                "area = '" + area + "', " +
                                "bills_per_month = '" + bills + "', " +
                                "va = " + electricity
                );
                txt_info_calculator.setText(Integer.parseInt(electricity) / 1000 + " KWP");
                resetCalculator();
                tabbedPane.setSelectedIndex(1);

                reloadLeads();
            }
        });

        btn_reset.addActionListener(e -> resetCalculator());

        btn_convert.addActionListener(e -> {
            Object id = cb_id_leads.getSelectedItem();
            String address = txt_address_lead.getText();
            Object kwp = cb_kwp_lead.getSelectedItem();
            int DP = Integer.parseInt(txt_dp_lead.getText());
            int FP = Integer.parseInt(txt_fp_lead.getText());

            runQuery("update leads set status = 2 where id = " + id);
            ResultSet result = runShowQuery("select email, no_hp from leads where id = " + id);
            try {
                while (true) {
                    assert result != null;
                    if (!result.next()) break;

                    runQuery("insert into customer set " +
                            "email = '" + result.getString("email") + "'," +
                            "phone = '" + result.getString("no_hp") + "'," +
                            "address = '" + address + "'," +
                            "KWP = '" + kwp + "'," +
                            "DP = '" + DP + "'," +
                            "FP = '" + FP + "'"
                    );
                }

                reloadCustomer();
                reloadLeads();

                tabbedPane.setSelectedIndex(2);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cb_id_customer.addActionListener(e -> updateCBDataIdCustomer());

        btn_delete.addActionListener(e -> {
            runQuery("delete from customer where id = " + cb_id_customer.getSelectedItem());
            reloadCustomer();
            cb_id_customer.setSelectedIndex(0);
        });

        btn_update.addActionListener(e -> {
            Object id = cb_id_customer.getSelectedItem();
            String email = txt_email_customer.getText();
            String phone = txt_hp_customer.getText();
            String address = txt_address_customer.getText();
            Object KWP = cb_kwp_customer.getSelectedItem();
            String DP = txt_dp_customer.getText();
            String FP = txt_fp_customer.getText();
            Object statusPayment = cb_status_payment.getSelectedItem();

            runQuery("update customer " +
                    "set " +
                    "email = '" + email + "'," +
                    "phone = '" + phone + "'," +
                    "address = '" + address + "'," +
                    "KWP = '" + KWP + "'," +
                    "DP = '" + DP + "'," +
                    "FP = '" + FP + "'," +
                    "status_payment_id = (select id from master_status_payment where name = '" + statusPayment + "')" +
                    "where id = " + id
            );

            reloadCustomer();
        });
    }

    private static void reloadLeads() {
        showLeadsdata();
        reloadCBLeads();
        txt_address_lead.setText("");
        cb_kwp_lead.setSelectedIndex(0);
        txt_dp_lead.setText("");
        txt_fp_lead.setText("");
    }

    private static void reloadCBLeads() {
        cb_id_leads.setModel(new DefaultComboBoxModel(showLeadsId().toArray()));
    }

    private static void reloadCBCustomer() {
        cb_id_customer.setModel(new DefaultComboBoxModel(showCustomerId().toArray()));
    }

    public static void reloadCustomer() {
        showCustomerdata();

        reloadCBCustomer();

        updateCBDataIdCustomer();
    }

    public static void updateCBDataIdCustomer() {
        try {
            ResultSet res = runShowQuery(
                    "select " +
                            "email, " +
                            "phone, " +
                            "address, " +
                            "KWP, " +
                            "master_status_payment.name as `Status Payment`, " +
                            "DP, " +
                            "FP " +
                            "from customer " +
                            "left join " +
                            "master_status_payment " +
                            "on " +
                            "master_status_payment.id " +
                            "= " +
                            "customer.status_payment_id " +
                            "where customer.id = " + cb_id_customer.getSelectedItem()
            );

            while (res.next()) {
                txt_email_customer.setText(res.getString("email"));
                txt_hp_customer.setText(res.getString("phone"));
                txt_address_customer.setText(res.getString("address"));
                cb_kwp_customer.setSelectedItem(Integer.parseInt(res.getString("KWP")));
                txt_dp_customer.setText(res.getString("DP"));
                txt_fp_customer.setText(res.getString("FP"));
                cb_status_payment.setSelectedItem(res.getString("Status Payment"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void runQuery(String query) {
        try {
            Connection con = getConnection();
            assert con != null;
            Statement st = con.createStatement();

            st.executeUpdate(query);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + db_name, db_user, db_password);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static ArrayList<String> showLeadsId() {
        ResultSet rs = runShowQuery("select id from leads where status = 1");
        ArrayList<String> listData = new ArrayList<>();
        while (true) {
            assert rs != null;
            try {
                if (!rs.next()) break;
                listData.add(rs.getString("id"));
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listData;
    }

    public static ArrayList<String> showCustomerId() {
        ResultSet rs = runShowQuery("select id from customer order by id");
        ArrayList<String> listData = new ArrayList<>();
        while (true) {
            assert rs != null;
            try {
                if (!rs.next()) break;
                listData.add(rs.getString("id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listData;
    }

    public static Object[] showMasterStatusPayment() {
        ResultSet rs = runShowQuery("select name from master_status_payment");
        ArrayList<String> listData = new ArrayList<>();
        while (true) {
            assert rs != null;
            try {
                if (!rs.next()) break;
                listData.add(rs.getString("name"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listData.toArray();
    }

    public static void showCustomerdata() {
        String[] coloumnCustomer = {"ID", "Email", "Phone", "Address", "KWP", "Status Payment", "DP", "FP"};
        ResultSet rs = runShowQuery(
                "select " +
                        "customer.id as id, " +
                        "email, " +
                        "phone, " +
                        "address, " +
                        "KWP, " +
                        "master_status_payment.name as `Status Payment`, " +
                        "DP, " +
                        "FP " +
                        "from customer " +
                        "left join " +
                        "master_status_payment " +
                        "on " +
                        "master_status_payment.id " +
                        "= " +
                        "customer.status_payment_id " +
                        "order by customer.id ASC "
        );
        DefaultTableModel customerModel = new DefaultTableModel(coloumnCustomer, 0);
        while (true) {
            assert rs != null;
            try {
                if (!rs.next()) break;
                String[] listData = {
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("KWP"),
                        rs.getString("Status Payment"),
                        rs.getString("DP"),
                        rs.getString("FP"),
                };
                customerModel.addRow(listData);
                tableCustomer.setModel(customerModel);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static void showLeadsdata() {
        String[] columnLeads = {"ID", "Email", "Phone", "Status", "Recomendation", "Area", "Bills / Month", "VA"};
        ResultSet rs = runShowQuery(
                "select " +
                        "leads.id as id, " +
                        "email, " +
                        "no_hp, " +
                        "master_leads_status.name as status, " +
                        "area, " +
                        "bills_per_month, " +
                        "va " +
                        "from leads " +
                        "inner join " +
                        "master_leads_status " +
                        "on " +
                        "master_leads_status.id " +
                        "= " +
                        "leads.status " +
                        "order by leads.id ASC"
        );
        DefaultTableModel leadsModel = new DefaultTableModel(columnLeads, 0);
        while (true) {
            assert rs != null;
            try {
                if (!rs.next()) break;
                String[] listData = {
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("no_hp"),
                        rs.getString("status"),
                        Integer.parseInt(rs.getString("va")) / 1000 + " KWP",
                        rs.getString("area"),
                        rs.getString("bills_per_month"),
                        rs.getString("va"),
                };

                leadsModel.addRow(listData);
                tableLeads.setModel(leadsModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetCalculator() {
        txt_name_calculator.setText("");
        txt_email_calculator.setText("");
        txt_hp_calculator.setText("");
        txt_area_calculator.setText("");
        txt_bills_calculator.setText("");
        cbo_electricity.setSelectedIndex(0);
    }

    public static ResultSet runShowQuery(String query) {
        try {
            Connection con = getConnection();
            assert con != null;
            PreparedStatement st = con.prepareStatement(query);

            return st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Mysql error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}

