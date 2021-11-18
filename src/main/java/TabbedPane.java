import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TabbedPane {
    private JFrame frm_jtpane;
    JButton btn_submit = new JButton("Submit");
    JButton btn_reset = new JButton("Reset");
    JButton btn_convert = new JButton("Convert to Customer");
    JButton btn_delete = new JButton("Delete");
    JButton btn_update = new JButton("Update");
    JComboBox cbo_electricity;
    JPanel jp = new JPanel();
    JPanel crudCustomer = new JPanel();
    JPanel convertLead = new JPanel();
    JPanel jp_center = new JPanel();
    JPanel jp_east = new JPanel();
    JPanel jp_calculator = new JPanel();
    JPanel jp_leads = new JPanel();
    JPanel jp_customer = new JPanel();
    JPanel jp_buttons = new JPanel();
    JTabbedPane tabbedPane = new JTabbedPane();
    
//    calculator
    JTextField txt_name_calculator = new JTextField();
    JTextField txt_email_calculator = new JTextField();
    JTextField txt_hp_calculator = new JTextField();
    JTextField txt_area_calculator = new JTextField();
    JTextField txt_bills_calculator = new JTextField();
    JTextPane txt_info_calculator = new JTextPane();
    TitledBorder border_info = BorderFactory.createTitledBorder("Result Recomendation");
    String electricity[] = {"2200 VA", "3500 VA", "4400 VA", "5500 VA", "6600 VA"};

//    lead
    JTextField txt_id_lead = new JTextField();
    JTextField txt_address_lead = new JTextField();
    JTextField txt_dp_lead = new JTextField();
    JTextField txt_fp_lead = new JTextField();
    JComboBox cb_kwp_lead = new JComboBox(new String[] {"2 KWP", "3 KWP", "4 KWP"});
    
//    calculator
    JComboBox cb_id_customer = new JComboBox(new String[] {"C001", "C002", "C003"});
    JTextField txt_email_customer = new JTextField();
    JTextField txt_hp_customer = new JTextField();
    JTextField txt_address_customer = new JTextField();
    JTextField txt_dp_customer = new JTextField();
    JTextField txt_fp_customer = new JTextField();
    JComboBox cb_kwp_customer = new JComboBox(new String[] {"2 KWP", "3 KWP", "4 KWP"});
    JComboBox cbStatusPayment = new JComboBox(new String[] {"Paid", "Reject", "Waiting DP", "Waiting FP"});


    /* Launch the application. */
    public static void main(String[] args) {
        TabbedPane window = new TabbedPane();
        window.frm_jtpane.setVisible(true);
    }

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
        jp_buttons.setLayout(new GridLayout(0,1));
        jp_buttons.add(btn_submit);
        jp_buttons.add(btn_reset);
        cbo_electricity = new JComboBox(electricity);
        jp.add( new JLabel(" Name"));
        jp.add(txt_name_calculator);
        jp.add( new JLabel(" Email"));
        jp.add(txt_email_calculator);
        jp.add( new JLabel(" No Handphone"));
        jp.add(txt_hp_calculator);
        jp.add( new JLabel(" Area (m2)"));
        jp.add(txt_area_calculator);
        jp.add( new JLabel(" Bills per Month (Rp)"));
        jp.add(txt_bills_calculator);
        jp.add( new JLabel(" Electricity (VA)"));
        jp.add(cbo_electricity);
        jp.add(new JLabel());
        jp.setLayout(new GridLayout(0,2));
        jp_center.setLayout(new BorderLayout());
        txt_info_calculator.setBorder(border_info);

        jp_calculator.setLayout(new BorderLayout());
        jp_center.add(jp, BorderLayout.NORTH);
        jp_center.add(new JScrollPane(txt_info_calculator), BorderLayout.CENTER);
        jp_east.add(jp_buttons);
        jp_calculator.add(jp_center, BorderLayout.CENTER);
        jp_calculator.add(jp_east, BorderLayout.EAST);

//        Leads
        String coloumnLeads[] = {"ID", "Email", "Phone", "Status", "Recomendation", "Area", "Bills / Month", "VA"};
        Object[][] dataLeads = {
                {"001", "afina@gmail.com", "085772610027", "New", "4 KWP", "24", "800.000", "2200"},
                {"002", "afina@gmail.com", "085772610027", "Customer", "3 KWP", "70", "1.000.000", "2200"},
                {"003", "afina@gmail.com", "085772610027", "New", "2 KWP", "21", "500.000", "4400"},
                {"004", "afina@gmail.com", "085772610027", "Customer", "4 KWP", "100", "2.000.000", "6600"},
                {"005", "afina@gmail.com", "085772610027", "New", "1 KWP", "64", "500.000", "2200"},
        };
        JTable tableLeads = new JTable(dataLeads, coloumnLeads);
        JScrollPane scrollPane = new JScrollPane(tableLeads);
        tableLeads.setFillsViewportHeight(true);
        tableLeads.setEnabled(false);

        jp_leads.setBorder(new EmptyBorder(10,10,0,10));
        jp_leads.setLayout(new GridLayout(2,1));
        jp_leads.add(scrollPane);

        // convertLead
        convertLead.setLayout(new GridLayout(7,2));
        convertLead.add(new JLabel("ID"));
        convertLead.add(txt_id_lead);
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
        convertLead.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder( 10, 2, 2,10), borderLead));
        jp_leads.add(convertLead);

//        Customer
        String coloumnCustomer[] = {"ID", "Email", "Phone", "Address", "KWP", "Status Payment", "DP", "FP"};
        Object[][] dataCustomer = {
                {"001", "afina@gmail.com", "085772610027", "Jl. Mawar 2", "4 KWP", "Paid", "500.000", "1.000.000"},
                {"002", "afina@gmail.com", "085772610027", "Jl. Mawar 2", "4 KWP", "Reject", "500.000", "1.000.000"},
                {"003", "afina@gmail.com", "085772610027", "Jl. Mawar 2", "4 KWP", "Waiting Payment", "500.000", "1.000.000"},
                {"004", "afina@gmail.com", "085772610027", "Jl. Mawar 2", "4 KWP", "Paid", "500.000", "1.000.000"},
                {"005", "afina@gmail.com", "085772610027", "Jl. Mawar 2", "4 KWP", "Paid", "500.000", "1.000.000"},
        };
        JTable tableCustomer = new JTable(dataCustomer, coloumnCustomer);
        JScrollPane scrollPane2 = new JScrollPane(tableCustomer);
        tableCustomer.setFillsViewportHeight(true);
        tableCustomer.setEnabled(false);

        jp_customer.setBorder(new EmptyBorder(10,10,0,10));
        jp_customer.setLayout(new GridLayout(0,1));
        jp_customer.add(scrollPane2);

        // crudCustomer
        crudCustomer.setLayout(new GridLayout(10,2));
        crudCustomer.add(new JLabel("ID"));
        crudCustomer.add(cb_id_customer);
        crudCustomer.add(new JLabel("Email"));
        crudCustomer.add(txt_email_customer);
        crudCustomer.add(new JLabel("Phone"));
        crudCustomer.add(txt_hp_customer);
        crudCustomer.add(new JLabel("Address"));
        crudCustomer.add(txt_address_customer);
        crudCustomer.add(new JLabel("KWP"));
        crudCustomer.add(cb_kwp_customer);
        crudCustomer.add(new JLabel("Down Payment (Rp)"));
        crudCustomer.add(txt_dp_customer);
        crudCustomer.add(new JLabel("Fullpayment (Rp)"));
        crudCustomer.add(txt_fp_customer);
        crudCustomer.add(new JLabel("Status Payment"));
        crudCustomer.add(cbStatusPayment);
        crudCustomer.add(new JLabel(""));
        crudCustomer.add(new JLabel(""));
        crudCustomer.add(btn_delete);
        crudCustomer.add(btn_update);

        // Border crudCustomer
        Border lowerdetchedCustomer = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder borderCustomer = BorderFactory.createTitledBorder(lowerdetchedCustomer, "Update or Delete Customer");
        borderCustomer.setTitleFont(borderCustomer.getTitleFont().deriveFont(Font.BOLD));
        crudCustomer.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder( 10, 2, 2,10), borderCustomer));
        jp_customer.add(crudCustomer);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(27, 21, 900, 600);
        tabbedPane.addTab("Calculator",jp_calculator);
        tabbedPane.addTab("Leads", jp_leads);
        tabbedPane.addTab("Customer", jp_customer);
        frm_jtpane.getContentPane().add(tabbedPane);
    }
}