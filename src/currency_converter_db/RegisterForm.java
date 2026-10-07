package currency_converter_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

/** Create-account screen (NetBeans form: see RegisterForm.form). */
public class RegisterForm extends javax.swing.JFrame {

    public RegisterForm() {
        initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        getRootPane().setDefaultButton(btnRegister);
        UI.center(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblWho = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblConfirm = new javax.swing.JLabel();
        txtConfirm = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Currency Converter - Register");
        setResizable(false);

        pnlHeader.setBackground(new java.awt.Color(0, 82, 147));
        lblHeading.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblHeading.setText("Create Account");
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

        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblUsername.setText("Username");

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblEmail.setText("Email");

        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblPassword.setText("Password");

        lblConfirm.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblConfirm.setText("Confirm Password");

        btnRegister.setBackground(new java.awt.Color(0, 130, 80));
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnRegister.setText("Register");
        btnRegister.setBorderPainted(false);
        btnRegister.setFocusPainted(false);
        btnRegister.setOpaque(true);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(90, 100, 110));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnBack.setText("Back to Login");
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsername)
                    .addComponent(lblEmail)
                    .addComponent(lblPassword)
                    .addComponent(lblConfirm))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirm)
                    .addComponent(txtConfirm))
                .addGap(16, 16, 16)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        register();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void register() {
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String pw = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirm.getPassword());

        if (username.length() < 3) { warn("Username must be at least 3 characters."); return; }
        if (!email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) { warn("Enter a valid email address."); return; }
        if (pw.length() < 6) { warn("Password must be at least 6 characters."); return; }
        if (!pw.equals(confirm)) { warn("Passwords do not match."); return; }

        // New accounts always get the USER role (looked up by name, not hard-coded id)
        String sql = "INSERT INTO users (username, email, password_hash, role_id) "
                + "VALUES (?, ?, ?, (SELECT role_id FROM roles WHERE role_name = 'USER'))";
        try (Connection conn = DBConnection.connect()) {
            if (conn == null) return;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, Security.hash(pw));
                ps.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "Account created! You can now log in.");
            dispose();
            new LoginForm().setVisible(true);
        } catch (SQLIntegrityConstraintViolationException e) {
            warn("That username or email is already registered.");
        } catch (SQLException e) {
            AppLog.error("Register", e);
            JOptionPane.showMessageDialog(this, "Registration failed: " + e.getMessage());
        }
    }

    private void warn(String m) {
        JOptionPane.showMessageDialog(this, m, "Check your input", JOptionPane.WARNING_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblConfirm;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWho;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPasswordField txtConfirm;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
