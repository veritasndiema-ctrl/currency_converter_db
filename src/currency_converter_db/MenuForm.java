package currency_converter_db;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/** Main menu - one button per screen (NetBeans form: see MenuForm.form). */
public class MenuForm extends javax.swing.JFrame {

    public MenuForm() {
        initComponents();
        getContentPane().setBackground(UI.BG);
        lblWho.setText("Welcome, " + Session.username + " (" + Session.role + ")");
        UI.styleGroup(pnlConverter, UI.GREEN, "Converter");
        UI.styleGroup(pnlRates, UI.BLUE, "Rates & Fees");
        UI.styleGroup(pnlReference, UI.BLUE, "Reference Data");
        UI.styleGroup(pnlAdmin, UI.RED, "Administration");
        pnlAdmin.setVisible(Session.isAdmin());          // Administration is for admins only
        pack();
        UI.center(this);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy  |  hh:mm:ss a");
        new Timer(1000, e -> lblClock.setText(sdf.format(new Date()))).start();
        lblClock.setText(sdf.format(new Date()));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblWho = new javax.swing.JLabel();
        pnlConverter = new javax.swing.JPanel();
        btnConvert = new javax.swing.JButton();
        btnMyHistory = new javax.swing.JButton();
        btnMyFavorites = new javax.swing.JButton();
        btnMyAlerts = new javax.swing.JButton();
        pnlRates = new javax.swing.JPanel();
        btnExchangeRates = new javax.swing.JButton();
        btnRateHistory = new javax.swing.JButton();
        btnCurrencyPairs = new javax.swing.JButton();
        btnFeeStructures = new javax.swing.JButton();
        pnlReference = new javax.swing.JPanel();
        btnCountries = new javax.swing.JButton();
        btnCurrencies = new javax.swing.JButton();
        btnRateSources = new javax.swing.JButton();
        pnlAdmin = new javax.swing.JPanel();
        btnUsers = new javax.swing.JButton();
        btnRoles = new javax.swing.JButton();
        btnUserSessions = new javax.swing.JButton();
        btnTransactionLog = new javax.swing.JButton();
        btnAuditTrail = new javax.swing.JButton();
        btnErrorLog = new javax.swing.JButton();
        lblClock = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Currency Converter - Main Menu");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(0, 82, 147));
        lblHeading.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblHeading.setText("Currency Converter System");
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));

        lblWho.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblWho.setText(" ");
        lblWho.setForeground(new java.awt.Color(200, 220, 240));

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeading)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblWho)
                .addGap(20, 20, 20))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeading)
                    .addComponent(lblWho))
                .addGap(12, 12, 12))
        );

        pnlConverter.setBackground(new java.awt.Color(255, 255, 255));
        pnlConverter.setBorder(javax.swing.BorderFactory.createTitledBorder("Converter"));
        btnConvert.setBackground(new java.awt.Color(0, 130, 80));
        btnConvert.setForeground(new java.awt.Color(255, 255, 255));
        btnConvert.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnConvert.setText("Convert Currency");
        btnConvert.setBorderPainted(false);
        btnConvert.setFocusPainted(false);
        btnConvert.setOpaque(true);
        btnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertActionPerformed(evt);
            }
        });

        btnMyHistory.setBackground(new java.awt.Color(0, 130, 80));
        btnMyHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnMyHistory.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnMyHistory.setText("My Conversion History");
        btnMyHistory.setBorderPainted(false);
        btnMyHistory.setFocusPainted(false);
        btnMyHistory.setOpaque(true);
        btnMyHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyHistoryActionPerformed(evt);
            }
        });

        btnMyFavorites.setBackground(new java.awt.Color(0, 130, 80));
        btnMyFavorites.setForeground(new java.awt.Color(255, 255, 255));
        btnMyFavorites.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnMyFavorites.setText("My Favorites");
        btnMyFavorites.setBorderPainted(false);
        btnMyFavorites.setFocusPainted(false);
        btnMyFavorites.setOpaque(true);
        btnMyFavorites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyFavoritesActionPerformed(evt);
            }
        });

        btnMyAlerts.setBackground(new java.awt.Color(0, 130, 80));
        btnMyAlerts.setForeground(new java.awt.Color(255, 255, 255));
        btnMyAlerts.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnMyAlerts.setText("My Rate Alerts");
        btnMyAlerts.setBorderPainted(false);
        btnMyAlerts.setFocusPainted(false);
        btnMyAlerts.setOpaque(true);
        btnMyAlerts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyAlertsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlConverterLayout = new javax.swing.GroupLayout(pnlConverter);
        pnlConverter.setLayout(pnlConverterLayout);
        pnlConverterLayout.setHorizontalGroup(
            pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConverterLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConvert, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnMyAlerts, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMyHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMyFavorites, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlConverterLayout.setVerticalGroup(
            pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConverterLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyFavorites, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlConverterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMyAlerts, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnlRates.setBackground(new java.awt.Color(255, 255, 255));
        pnlRates.setBorder(javax.swing.BorderFactory.createTitledBorder("Rates & Fees"));
        btnExchangeRates.setBackground(new java.awt.Color(0, 82, 147));
        btnExchangeRates.setForeground(new java.awt.Color(255, 255, 255));
        btnExchangeRates.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnExchangeRates.setText("Exchange Rates");
        btnExchangeRates.setBorderPainted(false);
        btnExchangeRates.setFocusPainted(false);
        btnExchangeRates.setOpaque(true);
        btnExchangeRates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExchangeRatesActionPerformed(evt);
            }
        });

        btnRateHistory.setBackground(new java.awt.Color(0, 82, 147));
        btnRateHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnRateHistory.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnRateHistory.setText("Rate History");
        btnRateHistory.setBorderPainted(false);
        btnRateHistory.setFocusPainted(false);
        btnRateHistory.setOpaque(true);
        btnRateHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRateHistoryActionPerformed(evt);
            }
        });

        btnCurrencyPairs.setBackground(new java.awt.Color(0, 82, 147));
        btnCurrencyPairs.setForeground(new java.awt.Color(255, 255, 255));
        btnCurrencyPairs.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnCurrencyPairs.setText("Currency Pairs");
        btnCurrencyPairs.setBorderPainted(false);
        btnCurrencyPairs.setFocusPainted(false);
        btnCurrencyPairs.setOpaque(true);
        btnCurrencyPairs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurrencyPairsActionPerformed(evt);
            }
        });

        btnFeeStructures.setBackground(new java.awt.Color(0, 82, 147));
        btnFeeStructures.setForeground(new java.awt.Color(255, 255, 255));
        btnFeeStructures.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFeeStructures.setText("Fee Structures");
        btnFeeStructures.setBorderPainted(false);
        btnFeeStructures.setFocusPainted(false);
        btnFeeStructures.setOpaque(true);
        btnFeeStructures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeeStructuresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRatesLayout = new javax.swing.GroupLayout(pnlRates);
        pnlRates.setLayout(pnlRatesLayout);
        pnlRatesLayout.setHorizontalGroup(
            pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRatesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExchangeRates, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnFeeStructures, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRateHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCurrencyPairs, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlRatesLayout.setVerticalGroup(
            pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRatesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExchangeRates, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRateHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCurrencyPairs, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlRatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFeeStructures, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnlReference.setBackground(new java.awt.Color(255, 255, 255));
        pnlReference.setBorder(javax.swing.BorderFactory.createTitledBorder("Reference Data"));
        btnCountries.setBackground(new java.awt.Color(0, 82, 147));
        btnCountries.setForeground(new java.awt.Color(255, 255, 255));
        btnCountries.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnCountries.setText("Countries");
        btnCountries.setBorderPainted(false);
        btnCountries.setFocusPainted(false);
        btnCountries.setOpaque(true);
        btnCountries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountriesActionPerformed(evt);
            }
        });

        btnCurrencies.setBackground(new java.awt.Color(0, 82, 147));
        btnCurrencies.setForeground(new java.awt.Color(255, 255, 255));
        btnCurrencies.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnCurrencies.setText("Currencies");
        btnCurrencies.setBorderPainted(false);
        btnCurrencies.setFocusPainted(false);
        btnCurrencies.setOpaque(true);
        btnCurrencies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurrenciesActionPerformed(evt);
            }
        });

        btnRateSources.setBackground(new java.awt.Color(0, 82, 147));
        btnRateSources.setForeground(new java.awt.Color(255, 255, 255));
        btnRateSources.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnRateSources.setText("Rate Sources");
        btnRateSources.setBorderPainted(false);
        btnRateSources.setFocusPainted(false);
        btnRateSources.setOpaque(true);
        btnRateSources.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRateSourcesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlReferenceLayout = new javax.swing.GroupLayout(pnlReference);
        pnlReference.setLayout(pnlReferenceLayout);
        pnlReferenceLayout.setHorizontalGroup(
            pnlReferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReferenceLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlReferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCountries, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlReferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCurrencies, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlReferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRateSources, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlReferenceLayout.setVerticalGroup(
            pnlReferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReferenceLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlReferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCountries, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCurrencies, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRateSources, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnlAdmin.setBackground(new java.awt.Color(255, 255, 255));
        pnlAdmin.setBorder(javax.swing.BorderFactory.createTitledBorder("Administration"));
        btnUsers.setBackground(new java.awt.Color(180, 40, 40));
        btnUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnUsers.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnUsers.setText("Users");
        btnUsers.setBorderPainted(false);
        btnUsers.setFocusPainted(false);
        btnUsers.setOpaque(true);
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });

        btnRoles.setBackground(new java.awt.Color(180, 40, 40));
        btnRoles.setForeground(new java.awt.Color(255, 255, 255));
        btnRoles.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnRoles.setText("Roles");
        btnRoles.setBorderPainted(false);
        btnRoles.setFocusPainted(false);
        btnRoles.setOpaque(true);
        btnRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRolesActionPerformed(evt);
            }
        });

        btnUserSessions.setBackground(new java.awt.Color(180, 40, 40));
        btnUserSessions.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSessions.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnUserSessions.setText("User Sessions");
        btnUserSessions.setBorderPainted(false);
        btnUserSessions.setFocusPainted(false);
        btnUserSessions.setOpaque(true);
        btnUserSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSessionsActionPerformed(evt);
            }
        });

        btnTransactionLog.setBackground(new java.awt.Color(180, 40, 40));
        btnTransactionLog.setForeground(new java.awt.Color(255, 255, 255));
        btnTransactionLog.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnTransactionLog.setText("Transaction Log");
        btnTransactionLog.setBorderPainted(false);
        btnTransactionLog.setFocusPainted(false);
        btnTransactionLog.setOpaque(true);
        btnTransactionLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransactionLogActionPerformed(evt);
            }
        });

        btnAuditTrail.setBackground(new java.awt.Color(180, 40, 40));
        btnAuditTrail.setForeground(new java.awt.Color(255, 255, 255));
        btnAuditTrail.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnAuditTrail.setText("Audit Trail");
        btnAuditTrail.setBorderPainted(false);
        btnAuditTrail.setFocusPainted(false);
        btnAuditTrail.setOpaque(true);
        btnAuditTrail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuditTrailActionPerformed(evt);
            }
        });

        btnErrorLog.setBackground(new java.awt.Color(180, 40, 40));
        btnErrorLog.setForeground(new java.awt.Color(255, 255, 255));
        btnErrorLog.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnErrorLog.setText("Error Log");
        btnErrorLog.setBorderPainted(false);
        btnErrorLog.setFocusPainted(false);
        btnErrorLog.setOpaque(true);
        btnErrorLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnErrorLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAdminLayout = new javax.swing.GroupLayout(pnlAdmin);
        pnlAdmin.setLayout(pnlAdminLayout);
        pnlAdminLayout.setHorizontalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnTransactionLog, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRoles, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnAuditTrail, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUserSessions, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnErrorLog, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlAdminLayout.setVerticalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTransactionLog, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAuditTrail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnErrorLog, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        lblClock.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblClock.setText(" ");
        lblClock.setForeground(new java.awt.Color(90, 100, 110));

        btnLogout.setBackground(new java.awt.Color(90, 100, 110));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnLogout.setText("Logout");
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(180, 40, 40));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnExit.setText("Exit");
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setOpaque(true);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlConverter, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlRates, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlReference, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblClock)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader)
                .addGap(14, 14, 14)
                .addComponent(pnlConverter)
                .addGap(10, 10, 10)
                .addComponent(pnlRates)
                .addGap(10, 10, 10)
                .addComponent(pnlReference)
                .addGap(10, 10, 10)
                .addComponent(pnlAdmin)
                .addGap(10, 10, 10)
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClock)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertActionPerformed
        new ConverterForm().setVisible(true);
    }//GEN-LAST:event_btnConvertActionPerformed

    private void btnMyHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyHistoryActionPerformed
        CrudForms.create(Specs.conversions()).setVisible(true);
    }//GEN-LAST:event_btnMyHistoryActionPerformed

    private void btnMyFavoritesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyFavoritesActionPerformed
        CrudForms.create(Specs.favorites()).setVisible(true);
    }//GEN-LAST:event_btnMyFavoritesActionPerformed

    private void btnMyAlertsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyAlertsActionPerformed
        CrudForms.create(Specs.alerts()).setVisible(true);
    }//GEN-LAST:event_btnMyAlertsActionPerformed

    private void btnExchangeRatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExchangeRatesActionPerformed
        CrudForms.create(Specs.exchangeRates()).setVisible(true);
    }//GEN-LAST:event_btnExchangeRatesActionPerformed

    private void btnRateHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRateHistoryActionPerformed
        CrudForms.create(Specs.rateHistory()).setVisible(true);
    }//GEN-LAST:event_btnRateHistoryActionPerformed

    private void btnCurrencyPairsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurrencyPairsActionPerformed
        CrudForms.create(Specs.currencyPairs()).setVisible(true);
    }//GEN-LAST:event_btnCurrencyPairsActionPerformed

    private void btnFeeStructuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeeStructuresActionPerformed
        CrudForms.create(Specs.feeStructures()).setVisible(true);
    }//GEN-LAST:event_btnFeeStructuresActionPerformed

    private void btnCountriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountriesActionPerformed
        CrudForms.create(Specs.countries()).setVisible(true);
    }//GEN-LAST:event_btnCountriesActionPerformed

    private void btnCurrenciesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurrenciesActionPerformed
        CrudForms.create(Specs.currencies()).setVisible(true);
    }//GEN-LAST:event_btnCurrenciesActionPerformed

    private void btnRateSourcesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRateSourcesActionPerformed
        CrudForms.create(Specs.rateSources()).setVisible(true);
    }//GEN-LAST:event_btnRateSourcesActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        CrudForms.create(Specs.users()).setVisible(true);
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRolesActionPerformed
        CrudForms.create(Specs.roles()).setVisible(true);
    }//GEN-LAST:event_btnRolesActionPerformed

    private void btnUserSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSessionsActionPerformed
        CrudForms.create(Specs.userSessions()).setVisible(true);
    }//GEN-LAST:event_btnUserSessionsActionPerformed

    private void btnTransactionLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransactionLogActionPerformed
        CrudForms.create(Specs.transactionLog()).setVisible(true);
    }//GEN-LAST:event_btnTransactionLogActionPerformed

    private void btnAuditTrailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditTrailActionPerformed
        CrudForms.create(Specs.auditTrail()).setVisible(true);
    }//GEN-LAST:event_btnAuditTrailActionPerformed

    private void btnErrorLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnErrorLogActionPerformed
        CrudForms.create(Specs.errorLog()).setVisible(true);
    }//GEN-LAST:event_btnErrorLogActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        closeSession();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        closeSession();
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeSession();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void closeSession() {
        AppLog.audit("LOGOUT", "users", Session.userId);
        Session.end();
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAuditTrail;
    private javax.swing.JButton btnConvert;
    private javax.swing.JButton btnCountries;
    private javax.swing.JButton btnCurrencies;
    private javax.swing.JButton btnCurrencyPairs;
    private javax.swing.JButton btnErrorLog;
    private javax.swing.JButton btnExchangeRates;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFeeStructures;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyAlerts;
    private javax.swing.JButton btnMyFavorites;
    private javax.swing.JButton btnMyHistory;
    private javax.swing.JButton btnRateHistory;
    private javax.swing.JButton btnRateSources;
    private javax.swing.JButton btnRoles;
    private javax.swing.JButton btnTransactionLog;
    private javax.swing.JButton btnUserSessions;
    private javax.swing.JButton btnUsers;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblWho;
    private javax.swing.JPanel pnlAdmin;
    private javax.swing.JPanel pnlConverter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlRates;
    private javax.swing.JPanel pnlReference;
    // End of variables declaration//GEN-END:variables
}
