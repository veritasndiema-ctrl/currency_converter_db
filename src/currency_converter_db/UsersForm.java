package currency_converter_db;



/** Users screen (NetBeans form: see UsersForm.form). Behaviour lives in CrudController. */
public class UsersForm extends javax.swing.JFrame {

    private final CrudController ctl;

    public UsersForm() {
        initComponents();
        getContentPane().setBackground(UI.BG);
        TableSpec spec = Specs.users();
        lblHeading.setText(spec.title);
        lblWho.setText(Session.username == null ? "" : Session.username + " (" + Session.role + ")");
        ctl = new CrudController(this, spec, tblData, lblStatusBar,
                new javax.swing.JComponent[]{txtUserId, txtUsername, txtEmail, pwdPasswordHash, cmbRoleId, txtCreatedAt},
                new javax.swing.JLabel[]{lblUserId, lblUsername, lblEmail, lblPasswordHash, lblRoleId, lblCreatedAt});
        btnNew.setEnabled(ctl.canSave());
        btnSave.setEnabled(ctl.canSave());
        btnDelete.setEnabled(ctl.canDelete());
        pack();
        UI.center(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblWho = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        lblUserId = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPasswordHash = new javax.swing.JLabel();
        pwdPasswordHash = new javax.swing.JPasswordField();
        lblRoleId = new javax.swing.JLabel();
        cmbRoleId = new javax.swing.JComboBox<>();
        lblCreatedAt = new javax.swing.JLabel();
        txtCreatedAt = new javax.swing.JTextField();
        scrData = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPreview = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblStatusBar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Users");

        pnlHeader.setBackground(new java.awt.Color(0, 82, 147));
        lblHeading.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblHeading.setText("Users");
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

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));
        lblUserId.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblUserId.setText("ID");

        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblUsername.setText("Username");

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblEmail.setText("Email");

        lblPasswordHash.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblPasswordHash.setText("Password *");

        lblRoleId.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblRoleId.setText("Role");

        lblCreatedAt.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblCreatedAt.setText("Created At");

        javax.swing.GroupLayout pnlFieldsLayout = new javax.swing.GroupLayout(pnlFields);
        pnlFields.setLayout(pnlFieldsLayout);
        pnlFieldsLayout.setHorizontalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserId)
                    .addComponent(lblEmail)
                    .addComponent(lblRoleId))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(cmbRoleId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsername)
                    .addComponent(lblPasswordHash)
                    .addComponent(lblCreatedAt))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(pwdPasswordHash, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtCreatedAt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        pnlFieldsLayout.setVerticalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserId)
                    .addComponent(txtUserId)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail)
                    .addComponent(lblPasswordHash)
                    .addComponent(pwdPasswordHash))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRoleId)
                    .addComponent(cmbRoleId)
                    .addComponent(lblCreatedAt)
                    .addComponent(txtCreatedAt))
                .addGap(10, 10, 10)
                .addGap(2, 2, 2))
        );

        tblData.setRowHeight(24);

        scrData.setViewportView(tblData);

        btnNew.setBackground(new java.awt.Color(0, 130, 80));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnNew.setText("New");
        btnNew.setBorderPainted(false);
        btnNew.setFocusPainted(false);
        btnNew.setOpaque(true);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 82, 147));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnSave.setText("Save");
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setOpaque(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(180, 40, 40));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnDelete.setText("Delete");
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnFind.setBackground(new java.awt.Color(90, 100, 110));
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFind.setText("Find");
        btnFind.setBorderPainted(false);
        btnFind.setFocusPainted(false);
        btnFind.setOpaque(true);
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(90, 100, 110));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFirst.setText("|<  First");
        btnFirst.setBorderPainted(false);
        btnFirst.setFocusPainted(false);
        btnFirst.setOpaque(true);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(90, 100, 110));
        btnPrevious.setForeground(new java.awt.Color(255, 255, 255));
        btnPrevious.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnPrevious.setText("<  Prev");
        btnPrevious.setBorderPainted(false);
        btnPrevious.setFocusPainted(false);
        btnPrevious.setOpaque(true);
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(90, 100, 110));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnNext.setText("Next  >");
        btnNext.setBorderPainted(false);
        btnNext.setFocusPainted(false);
        btnNext.setOpaque(true);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(90, 100, 110));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnLast.setText("Last  >|");
        btnLast.setBorderPainted(false);
        btnLast.setFocusPainted(false);
        btnLast.setOpaque(true);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPreview.setBackground(new java.awt.Color(0, 130, 80));
        btnPreview.setForeground(new java.awt.Color(255, 255, 255));
        btnPreview.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnPreview.setText("Preview PDF");
        btnPreview.setBorderPainted(false);
        btnPreview.setFocusPainted(false);
        btnPreview.setOpaque(true);
        btnPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewActionPerformed(evt);
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

        lblStatusBar.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblStatusBar.setText(" ");
        lblStatusBar.setForeground(new java.awt.Color(90, 100, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
            .addComponent(pnlFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrData, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader)
                .addComponent(pnlFields)
                .addGap(8, 8, 8)
                .addComponent(scrData, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(lblStatusBar)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        ctl.newRecord();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        ctl.save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        ctl.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        ctl.find();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        ctl.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        ctl.previous();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        ctl.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        ctl.last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewActionPerformed
        ctl.preview();
    }//GEN-LAST:event_btnPreviewActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreview;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Item> cmbRoleId;
    private javax.swing.JLabel lblCreatedAt;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblPasswordHash;
    private javax.swing.JLabel lblRoleId;
    private javax.swing.JLabel lblStatusBar;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWho;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPasswordField pwdPasswordHash;
    private javax.swing.JScrollPane scrData;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtCreatedAt;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
